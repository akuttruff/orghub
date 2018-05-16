(ns orghub.core
  (:require [reagent.core :as r]
            [orghub.login :as login]
            [orghub.navigation :as nav]))


(defn mountit []
  (r/render [login/form]
            (. js/document (getElementById "login")))
  (r/render [nav/navigation]
            (. js/document (getElementById "navigation"))))

(mountit)
