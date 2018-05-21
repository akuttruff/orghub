(ns orghub.server
  (:gen-class)
  (:require [buddy.hashers :as hashers]
            [cheshire.core :refer [generate-string parse-string]]
            [compojure.core :refer [GET POST defroutes]]
            [compojure.route :as route]
            [orghub.db :as db]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.util.response :as resp]))

(defn json-resp [data]
  {"Content-Type" "application/json; charset=utf-8"
   :status 200
   :body (generate-string data)})

(defn json-body [req]
  (parse-string (slurp (:body req)) true))

(defn login [login-info]
  (let [email  (:email login-info)
        pw     (:password login-info)
        user   (db/find-by :users :email email)
        valid? (hashers/check pw (:password_digest user))]

    ;; TODO: stick authenticated flag into session
    (println (str "User legit?: " valid?))
    (json-resp {:authenticated valid?})))

(defroutes app
  (GET "/" []
       (resp/content-type
        (resp/resource-response "index.html" {:root "public"})
        "text/html"))

  (POST "/login" req
        (let [login-info (json-body req)]
          (login login-info)))

  (route/resources "/")
  (route/not-found "Page not found"))

(defn -main [& args]
  (run-jetty app {:port 3000}))
