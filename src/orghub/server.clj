(ns orghub.server
  (:require [cheshire.core :refer [generate-string parse-string]]
            [compojure.core :refer [GET POST defroutes]]
            [compojure.route :as route]
            [ring.middleware.cors :refer [wrap-cors]]
            [ring.util.response :refer [response content-type]]))

;; add to headers
;; Access-Control-Allow-Origin: http://localhost:3000

(defn json-resp [handler]
  (fn [req]
    (let [resp (handler req)]
      (-> resp
          (assoc-in [:headers "Content-Type"] "application/json; charset=utf-8")
          (assoc-in [:status] 200)))))

(defn json-body [req]
  (parse-string (slurp (:body req)) true))

(defn login [login-info]
  (println login-info)
  (println (:email login-info))
  (println (:password login-info))

  {:authenticated true})

(defroutes app-routes
  (GET "/" [] "Home")
  (POST "/login" req
        (println "INCOMING REQUEST:")
        (println req)
        (let [login-info (json-body req)]
          (login login-info))))

(def app (-> app-routes
             (wrap-cors  :access-control-allow-origin [#"^(http(s)?://)?localhost:(\d){4}$"]
                         :access-control-allow-credentials "true"
                         :access-control-allow-methods [:get :post :options]
                         :access-control-allow-headers ["Content-Type" "Accept" "Cache-Control" "Origin" "User-Agent"])))
