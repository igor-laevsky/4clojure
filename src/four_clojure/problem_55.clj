(ns four-clojure.problem-55
  (:require [clojure.test :refer :all]))

(defn my-freq [coll]
  (reduce
    (fn [acc val] (assoc acc val (inc (get acc val 0))))
    {} coll))

(def __ my-freq)

(deftest problem-55-test
  (is (= (__ [1 1 2 3 2 1 1]) {1 4, 2 2, 3 1}))
  (is (= (__ [:b :a :b :a :b]) {:a 2, :b 3}))
  (is (= (__ '([1 2] [1 3] [1 3])) {[1 2] 1, [1 3] 2}))
  )