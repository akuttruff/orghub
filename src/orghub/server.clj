(ns orghub.server
  (:gen-class)
  (:require [buddy.hashers :as hashers]
            [cheshire.core :refer [generate-string parse-string]]
            [compojure.core :refer [GET POST defroutes]]
            [compojure.route :as route]
            [orghub.db :as db]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.util.response :as resp]
            [ring.middleware.anti-forgery :refer :all]
            [ring.middleware.session :refer [wrap-session]]))

(defn json-body [req]
  (parse-string (slurp (:body req)) true))

(defn login [login-info]
  (let [email  (:email login-info)
        pw     (:password login-info)
        user   (db/find-by :users :email email)]
    ;; check that user-provided password matches digest in db
    (hashers/check pw (:password_digest user))))

(defroutes app-routes
  (POST "/login" req
        (let [session (:session req {})
              authenticated? (login (json-body req))]
          (-> (resp/response (str authenticated?))
              (assoc-in [:session :authenticated?] authenticated?))))

  (GET "/csrf" []
       (resp/response *anti-forgery-token*))

  (route/not-found "Page not found")
  (route/resources "/"))

(def app (-> app-routes
             wrap-anti-forgery
             wrap-session))

(defn -main [& args]
  (run-jetty app {:port 3000}))
