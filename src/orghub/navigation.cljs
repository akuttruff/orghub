(ns orghub.navigation
  (:require [rum.core :as rum]
            [clojure.string :refer [capitalize]]))

(enable-console-print!)
(defonce navigation-state (atom {:email ""
                                 :password ""}))
(rum/defc input [name]
  (let [type (or (#{"email" "password"} name) "text")
        placeholder (capitalize name)]
    [:fieldset.pure-group
     [:input.pure-input-1.pure-input-rounded
      {:type type :placeholder placeholder}]]))


(rum/defc pure-link [name]
  [:li.pure-menu-item
   [:a.pure-menu-link {:href (str "/" name) }
    (capitalize name)]])

(rum/defc navigation []
  [:div#layout
   [:a.menu-link#menuLink {:href "#menu"}]
   [:div#menu
    [:div.pure-menu
     [:a.pure-menu-heading {:href "/"}
      OrgHub]
     [:ul.pure-menu-list
      (pure-link "groups")
      ]]]])
