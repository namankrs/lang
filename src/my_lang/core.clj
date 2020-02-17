(ns my-lang.core
  (:require [instaparse.core :as insta :refer :all]))

(defparser calculator-parser (clojure.java.io/resource "myparser.bnf"))

(defn transformer [exp]
  (->> (calculator-parser exp)
       (insta/transform
         {:add +, :sub -, :mul *, :div /, :exp identity,
          :number clojure.edn/read-string})))

(calculator-parser "")
(transformer "2*2")
;(defn -main []
;  (println (transformer)))


