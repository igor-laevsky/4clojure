(ns four-clojure.problem-53
  (:require [clojure.test :refer :all]
            [spyscope.core :refer :all]))

(defn max-incr [coll]
  (loop [s (next coll)
         cur [(first coll)]
         max []]
    (cond
      (empty? s) (let [max (if (> (count cur) (count max)) cur max)]
                   (if (> (count max) 1) max []))
      (> (first s) (last cur)) (recur (next s) (conj cur (first s)) max)
      :else (recur (next s)
                   [(first s)]
                   (if (> (count cur) (count max)) cur max)))))

(def __ max-incr)

(deftest problem-53-test
  (is (= (__ [1 0 1 2 3 0 4 5]) [0 1 2 3]))
  (is (= (__ [5 6 1 3 2 7]) [5 6]))
  (is (= (__ [2 3 3 4 5]) [3 4 5]))
  (is (= (__ [7 6 5 4]) []))
  )
