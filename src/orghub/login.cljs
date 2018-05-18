(ns orghub.login
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as r]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]))

(enable-console-print!)

(defonce login-info (r/atom {:email "" :password ""}))

(defn login [email pw]
  (go (let [response (<! (http/post "http://localhost:3000/login"
                                    {:json-params {:email email
                                                   :password pw}}))]
        (prn response)
        (prn (:status response))
        (prn (map :login (:body response))))))


(defn input [name val]
  (let [type (or (#{"email" "password"} name) "text")
        placeholder (clojure.string/capitalize name)]
    [:fieldset.pure-group
     [:input.pure-input-1.pure-input-rounded
      {:type type
       :placeholder placeholder
       :value @val
       :on-change #(reset! val (-> % .-target .-value))}]]))

(defn form []
  (let [email (r/atom "")
        password (r/atom "")]
    [:form.pure-form
     [input "email" email]
     [input "password" password]
     [:button.pure-button.pure-input-1.pure-button-primary
      {:on-click (fn [_] (login @email @password))}
      "Log In"]]))
