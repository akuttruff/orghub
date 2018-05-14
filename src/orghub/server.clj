(ns orghub.server
  (:require  [compojure.core :refer [GET defroutes]]
             [compojure.route :as route]
             [ring.util.response :refer [resource-response response]]
             [ring.middleware.json :as json]
             [ring.util.response :refer [response]]
             [ring.middleware.defaults :refer [wrap-defaults api-defaults]]))



(defroutes app-routes
  (GET "/" [] {:foo "bar"})
  (route/not-found "<h1>Page not found</h1>"))


(def app
  (-> app-routes
      (json/wrap-json-body)
      (json/wrap-json-response)
      (wrap-defaults api-defaults)))
