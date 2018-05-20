(ns orghub.server
  (:require [cheshire.core :refer [generate-string parse-string]]
            [compojure.core :refer [GET POST defroutes]]))

(defn json-resp [body]
  {"Content-Type" "application/json; charset=utf-8"
   :status 200
   :body body})

(defn json-body [req]
  (parse-string (slurp (:body req)) true))

(defn login [login-info]
  (println "server login -------------------")
  (println login-info)

  (json-resp (generate-string {:authenticated true})) )

(defroutes app
  (GET "/" [] "Home")
  (POST "/login" req
        (let [login-info (json-body req)]
          (login login-info))))
