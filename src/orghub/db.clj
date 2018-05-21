(ns orghub.db
  (:require [clojure.java.jdbc :as jdbc]))

;; TODO: pull from env vars; just for dev
(def db-spec {:dbtype "postgresql"
              :dbname "orghub"
              :host "localhost"
              :user "orghub"
              :password "orghub"})

(defn all [tbl]
  (let [sql (str "SELECT * FROM " (name tbl))]
    (jdbc/query db-spec [sql])))

(defn find-by [tbl col val]
  (let [sql (str "SELECT * FROM " (name tbl)
                 " WHERE " (name col) " = ?"
                 " LIMIT 1")]
    (first (jdbc/query db-spec [sql val]))))
