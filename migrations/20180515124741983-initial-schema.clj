;; migrations/20180515124741983-initial-schema.clj

(defn up []
  ["CREATE TABLE foo(bar text)"])

(defn down []
  ["DROP TABLE foo"])
