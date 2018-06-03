(ns four-clojure.problem-71
  (:require [clojure.test :refer :all]))

(def __ last)

(deftest problem-71-tests
  (is
    (= (__ (sort (rest (reverse [2 5 4 1 3 6]))))
       (-> [2 5 4 1 3 6] (reverse) (rest) (sort) (__))
       5))
  )

;(defn __2 [coll]
;  (apply + coll))

(defn __2 [coll]
  (reduce + coll))

(deftest problem-72-tests
  (is
    (= (#(reduce + %) (map inc (take 3 (drop 2 [2 5 4 1 3 6]))))
       (->> [2 5 4 1 3 6] (drop 2) (take 3) (map inc) __2)
       11)))
