(ns orghub.components.navigation
  (:require [reagent.core :as r]
            [reagent.session :as session]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [secretary.core :as secretary :refer-macros [defroute]]
            [orghub.components.login :as login])
  (:import goog.history.Html5History
           goog.Uri))

(enable-console-print!)

(def app-state (r/atom {}))

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

(defn home []
  [:div
   [navigation]
   [login/form]])

(defn groups []
  [:div
   [navigation]
   "Groups"])

(defn hook-browser-navigation! []
  (doto (Html5History.)
    (events/listen
     EventType/NAVIGATE
     (fn [event]
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

(defn app-routes []
  (secretary/set-config! :prefix "#")

  (defroute "/" []
    (swap! app-state assoc :page :home))

  (defroute "/groups" []
    (swap! app-state assoc :page :groups))

  (hook-browser-navigation!))


(defmulti current-page #(:page @app-state))

(defmethod current-page :home []
  [home])
(defmethod current-page :groups []
  [groups])
(defmethod current-page :default []
  [:div ])

;; ------------------------------------------------------------
