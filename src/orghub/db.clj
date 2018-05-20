(ns orghub.db
  (:require [clojure.java.jdbc :as j]))


(def db {:dbtype "postgresql"
         :dbname "orghub"
         :host "localhost"
         :user "orghub"
         ;; TODO: pull from env var; just for dev
         :password "orghub"})

(j/query db ["select * from groups"])
