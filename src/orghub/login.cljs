(ns orghub.login
  (:require [reagent.core :as r]))

(enable-console-print!)

(defonce login-info (r/atom {:email "" :password ""}))

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
      {:on-click #(prn @email)}
      "Log In"]]))
