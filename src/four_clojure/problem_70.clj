(ns four-clojure.problem-70
  (:require [clojure.test :refer :all]))

;(defn split-sort [s]
;  (as-> s v
;      (clojure.string/replace v #"(\.|\?|!|:|;)" "")
;      (clojure.string/split v #"\s+")
;      (sort #(compare (clojure.string/lower-case %1) (clojure.string/lower-case %2)) v)))

(defn split-sort [s]
  (-> s
      (clojure.string/replace #"(\.|\?|!|:|;)" "")
      (clojure.string/split #"\s+")
      (->>
        (sort
          #(compare (clojure.string/lower-case %1) (clojure.string/lower-case %2))))))

(def __ split-sort)

(deftest problem-70-test
  (is (= (__  "Have a nice day.")
         ["a" "day" "Have" "nice"]))

  (is (= (__  "Clojure is a fun language!")
         ["a" "Clojure" "fun" "is" "language"]))

  (is (= (__  "Fools fall for foolish follies.")
         ["fall" "follies" "foolish" "Fools" "for"]))
  )