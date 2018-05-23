(ns orghub.components.home
  (:require [orghub.components.navigation :as nav]
            [orghub.components.login :as login]))

(defn content []
  [:div
   [nav/navigation]
   [login/form]])
