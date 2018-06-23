(ns four-clojure.problem-105
  (:require [clojure.test :refer :all]
            [spyscope.core :refer :all]
            [four-clojure.utils :refer :all]))

(defn to-map [coll]
  (loop [cur coll
         res {}]
    (if-let [s (seq cur)]
      (let [kv (first s)
            vals (take-while number? (next s))
            cont (drop-while number? (next s))]
        (recur cont (assoc res kv vals)))
      res)))

(def __ to-map)

(deftest problem-105-test
  (is (= {} (__ [])))
  (is (= {:a [1]} (__ [:a 1])))
  (is (= {:a [1], :b [2]} (__ [:a 1, :b 2])))
  (is (= {:a [1 2 3], :b [], :c [4]} (__ [:a 1 2 3 :b :c 4])))
  )