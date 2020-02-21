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
        {
         :exp vector
         :function (partial intern 'my-lang.core)
         ;:function-body (conj clojure.core/fn vector)
         :arguments vector
         :assignment (partial intern 'my-lang.core)
         :defined-var (comp eval clojure.edn/read-string )
         :variable clojure.edn/read-string
         :number clojure.edn/read-string
         :add +, :sub -, :mul *, :div /
         })))

;(calculator "2*2")
;(language-parser "x a b c=a+b+c")
;(language "x=4\ny=3\nx+y\ny+2")

(defn repl []
  (ns my-lang.core)
  (do
    (print "my-lang> ")
    (flush))
  (let [input (read-line)]
    (println (first (language input)))
    (recur)))

(defn -main []
  (do []
  (println "Namaskara")
  (println "===============")
  (flush)
  (repl)))


