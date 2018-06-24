(ns four-clojure.problem-110
  (:require [clojure.test :refer :all]
            [four-clojure.utils :refer :all]))

(defn pron [coll]
  (next
    (iterate
      (fn [val] (->> (partition-by identity val)
                     (mapcat #(list (count %) (first %)))))
      coll)))

(def __ pron)

(deftest problem-110-test
  (is (= [[1 1] [2 1] [1 2 1 1]] (take 3 (__ [1]))))
  (is (= [3 1 2 4] (first (__ [1 1 1 4 4]))))
  (is (= [1 1 1 3 2 1 3 2 1 1] (nth (__ [1]) 6)))
  (is (= 338 (count (nth (__ [3 2]) 15))))
  )
