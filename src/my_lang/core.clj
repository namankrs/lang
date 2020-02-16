(ns my-lang.core
  (:require [instaparse.core :as insta :refer :all]))

(defparser calculator-parser (clojure.java.io/resource "myparser.bnf"))

(defn transformer []
  (->> (calculator-parser "3*3+3")
       (insta/transform
         {:sum    +, :sub -, :mul *, :div /, :exp identity,
          :number clojure.edn/read-string})))

(defn -main []
  (println (transformer)))


