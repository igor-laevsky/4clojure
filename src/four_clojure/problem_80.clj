(ns four-clojure.problem-80
  (:require [clojure.test :refer :all]))

(defn perfect? [num]
  (->>
    (range 1 num)
    (filter #(= 0 (mod num %)))
    (reduce +)
    (= num)))

(def __ perfect?)

(deftest problem-80-test
  (is (= (__ 6) true))
  (is (= (__ 7) false))
  (is (= (__ 496) true))
  (is (= (__ 500) false))
  (is (= (__ 8128) true))
  )