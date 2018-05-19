(ns orghub.core
  (:require [reagent.core :as r]
            [orghub.components.navigation :as nav]))

(nav/app-routes)
(r/render [nav/current-page]
          (.getElementById js/document "app"))
