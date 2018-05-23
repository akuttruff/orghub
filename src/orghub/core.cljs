(ns orghub.core
  (:require [reagent.core :as r]
            [secretary.core :as secretary :refer-macros [defroute]]
            [orghub.components.home :as home]
            [orghub.components.groups :as groups]
            [orghub.components.navigation :as nav]))

(def app-state (r/atom {}))

(defn app-routes []
  (secretary/set-config! :prefix "#")

  (defroute "/" []
    (swap! app-state assoc :page :home))

  (defroute "/groups" []
    (swap! app-state assoc :page :groups))

  (nav/hook-browser-navigation!))

(defmulti current-page #(:page @app-state))
(defmethod current-page :home []    [home/content app-state])
(defmethod current-page :groups []  [groups/content])
(defmethod current-page :default [] [:div ])

(app-routes)
(r/render [current-page]
          (.getElementById js/document "app"))
