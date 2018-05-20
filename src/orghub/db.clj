(ns orghub.db
  (:require [clojure.java.jdbc :as j]))

;; TODO: pull from env vars; just for dev
(def db-spec {:dbtype "postgresql"
              :dbname "orghub"
              :host "localhost"
              :user "orghub"
              :password "orghub"})

(defn user-by [col val]
  (let [sql (str "SELECT * FROM users WHERE " (name col) " = ?")]
    (j/query db-spec [sql val])))

