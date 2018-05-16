(ns orghub.navigation
  (:require [reagent.core :as r]
            [clojure.string :refer [capitalize]]))

(enable-console-print!)

(defn pure-link [name]
  [:li.pure-menu-item
   [:a.pure-menu-link {:href (str "/" name) }
    (capitalize name)]])

(defn navigation []
  [:div#layout
   [:a#menuLink.menu-link {:href "#menu"}]
   [:div#menu
    [:div.pure-menu
     [:a.pure-menu-heading {:href "/"}
      "OrgHub"]
     [:ul.pure-menu-list
      [pure-link "groups"]
      [pure-link "contacts"]]]]])
