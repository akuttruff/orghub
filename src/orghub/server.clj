(ns orghub.server
  (:require [buddy.hashers :as hashers]
            [cheshire.core :refer [generate-string parse-string]]
            [compojure.core :refer [GET POST defroutes]]
            [orghub.db :as db]))

(defn json-resp [body]
  {"Content-Type" "application/json; charset=utf-8"
   :status 200
   :body body})

(defn json-body [req]
  (parse-string (slurp (:body req)) true))

(defn login [login-info]
  (let [email   (:email login-info)
        pw      (:password login-info)
        db-user (first (db/user-by :email email))
        valid?  (hashers/check pw (:password_digest db-user))]

    ;; TODO: stick authenticated flag into session
    (println (str "User legit?: " valid?))
    (json-resp (generate-string {:authenticated valid?}))))

(defroutes app
  (GET "/" [] "Home")
  (POST "/login" req
        (let [login-info (json-body req)]
          (login login-info))))
