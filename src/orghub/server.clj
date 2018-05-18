(ns orghub.server
  (:require [cheshire.core :refer [generate-string parse-string]]
            [compojure.core :refer [GET POST defroutes]]
            [compojure.route :as route]
            [ring.middleware.cors :refer [wrap-cors]]
            [ring.util.response :refer [response content-type]]))

(defn json-resp [data]
  (content-type (response (generate-string data))
                "application/json"))

(defn json-body [req]
  (parse-string (slurp (:body req)) true))

(defn login [login-info]
  (println (:email login-info))
  (println (:password login-info)))

(defroutes app
  (GET "/" [] (json-resp {:foo "bars"}))
  (POST "/login" req
        (let [login-info (json-body req)]
          (login (json-body req))))
  (route/not-found "<h1>Page not found</h1>"))
