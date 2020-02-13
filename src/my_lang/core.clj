(ns my-lang.core
  (:require [instaparse.core :as insta]))

(def calculator-parser
  (insta/parser
    "exp = sum-sub
<sum-sub> = sum | sub | mul | div
mul = num <'*'> num
div = num <'/'> num
sum = num <'+'> num
sub = num <'-'> num
number = #'[0-9]+'
<num> = number | sum-sub"))

(->> (calculator-parser "1+2+3")
     (insta/transform
       {:sum    +, :sub -, :mul *, :div /, :exp identity,
        :number clojure.edn/read-string}))

