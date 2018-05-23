(ns orghub.components.navigation
  (:require [goog.events :as events]
            [goog.history.EventType :as EventType]
            [secretary.core :as secretary])
  (:import goog.history.Html5History
           goog.Uri))

(enable-console-print!)

(defn pure-link [name path]
  [:li.pure-menu-item
   [:a.pure-menu-link {:href path} name]])

(defn navigation []
  [:div#layout
   [:a#menuLink.menu-link {:href "#menu"}]
   [:div#menu
    [:div.pure-menu
     [:a.pure-menu-heading {:href "#/"}
      "OrgHub"]
     [:ul.pure-menu-list
      [pure-link "Home"   "#/"]
      [pure-link "Groups" "#/groups"]]]]])

(defn hook-browser-navigation! []
  (doto (Html5History.)
    (events/listen
     EventType/NAVIGATE
     (fn [event]
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))


;; ------------------------------------------------------------
