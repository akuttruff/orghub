(ns orghub.components.home
  (:require [orghub.components.navigation :as nav]
            [orghub.components.groups :as groups]
            [orghub.components.login :as login]))

(defn content [app-state]
  [:div
   [nav/navigation]
   (if (:logged-in? @app-state)
     "Hooray, you're now logged in"
     [login/content app-state])])
