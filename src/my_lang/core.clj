(ns my-lang.core
  (:require [instaparse.core :as insta :refer :all]))



(defparser calculator-parser (clojure.java.io/resource "calculatorParser.bnf"))
(defparser language-parser (clojure.java.io/resource "language-parser.bnf"))


(defn calculator [exp]
  (->> (calculator-parser exp)
       (insta/transform
        {:add +, :sub -, :mul *, :div /, :exp identity
         :number clojure.edn/read-string})))

(defn language [exp]
  (->> (language-parser exp)
       (insta/transform
        {:assignment (partial intern 'my-lang.core)
         :exp identity
         :value clojure.edn/read-string
         :variable clojure.edn/read-string})))

(calculator "2*2")
(language-parser "x=4")
(language "x=4")

(defn -main []
  (do []
      (println (language-parser "x=4"))))


