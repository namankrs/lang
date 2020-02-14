(ns my-lang.core
  (:require [instaparse.core :as insta :refer :all]))

(defparser calculator-parser (clojure.java.io/resource "myparser.bnf"))

(->> (calculator-parser "1+2+3")
     (insta/transform
       {:sum    +, :sub -, :mul *, :div /, :exp identity,
        :number clojure.edn/read-string}))

