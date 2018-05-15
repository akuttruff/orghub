(ns orghub.login
  (:require [rum.core :as rum]))

(enable-console-print!)

(defonce ccount (atom 0))

(rum/defc input [name]
  (let [type (or (#{"email" "password"} name) "text")
        placeholder (clojure.string/capitalize name)]
    [:fieldset.pure-group
     [:input.pure-input-1.pure-input-rounded
      {:type type :placeholder placeholder}]]))

(rum/defc counter < rum/reactive []
  [:div { :on-click (fn [_] (swap! ccount inc))}
   "Clicks: " (rum/react ccount)])

(rum/defc login []
  [:form.pure-form
   (input "email")
   (input "password")
   [:button.pure-button.pure-input-1.pure-button-primary
    "Log In"]])


