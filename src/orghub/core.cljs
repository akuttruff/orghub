(ns orghub.core
  (:require [rum.core :as rum]
            [orghub.login :as login]
            [orghub.navigation :as navigation]
            [garden.core :refer [css]]))

;; TODO: comment out for prod
(enable-console-print!)

(let [components [["navigation" navigation/navigation]
                  ["login" login/login]]]

  (doseq [[name func] components]
    (rum/mount (func)
               (. js/document (getElementById name)))))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
