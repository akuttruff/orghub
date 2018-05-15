(ns orghub.server
  (:require [cheshire.core :refer [generate-string]]
            [compojure.core :refer [GET defroutes]]
            [compojure.route :as route]
            [ring.util.response :refer [response]]
            [ring.middleware.json :refer [wrap-json-response]]))


(defroutes app-routes
  (GET "/" [] (response {:foo "bar"}))
  (route/not-found "<h1>Page not found</h1>"))

(def app (-> app-routes
             (wrap-json-response)))
