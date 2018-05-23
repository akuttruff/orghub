(ns orghub.components.login
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as r]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]))

(enable-console-print!)

(defonce login-info (r/atom {:email "" :password "" :authenticated? false}))

(defn login [email pw app-state]
  (go (let [csrf (:body (<! (http/get "/csrf")))
            opts {:json-params {:email email :password pw}
                  :headers {"X-CSRF-Token" csrf}}
            resp (<! (http/post "/login" opts))
            authd? (= "true" (:body resp))]
        (if authd?
          (swap! app-state assoc :logged-in? authd?)))))

(defn input [name val]
  (let [type (or (#{"email" "password"} name) "text")
        placeholder (clojure.string/capitalize name)]
    [:fieldset.pure-group
     [:input.pure-input-1.pure-input-rounded
      {:type type
       :placeholder placeholder
       :value @val
       :on-change #(reset! val (-> % .-target .-value))}]]))

(defn content [app-state]
  (let [email (r/atom "")
        password (r/atom "")]
    [:form.pure-form
     [input "email" email]
     [input "password" password]
     [:button.pure-button.pure-input-1.pure-button-primary
      {:on-click (fn [_] (login @email @password app-state))}
      "Log In"]]))
