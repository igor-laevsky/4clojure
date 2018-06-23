(ns four-clojure.problem-60
  (:require [clojure.test :refer :all]
            [spyscope.core :refer :all]))

(defn my-reductions
  ([f coll]
   (let [init (f (first coll))]
     (my-reductions f init (rest coll))))
  ([f init coll]
   (letfn [(step [acc c]
             (lazy-seq
               (when-let [s (seq c)]
                 (let [new-acc (f acc (first s))]
                   (cons new-acc (step new-acc (rest s)))))))]

     (cons init (step init coll))
     )))

(def __ my-reductions)

(deftest problem-60-test
  (is (= (take 5 (__ + (range))) [0 1 3 6 10]))
  (is (= (__ conj [1] [2 3 4]) [[1] [1 2] [1 2 3] [1 2 3 4]]))
  (is (= (last (__ * 2 [3 4 5])) (reduce * 2 [3 4 5]) 120))
  )
