(ns problem-97
  (:require [clojure.test :refer :all]
            [clojure.tools.trace :refer :all]))

(defn get-row [row-n]
  (if (<= row-n 1)
    [1]
    (let [prev-row (get-row (dec row-n))
          prev-cnt (count prev-row)
          get-prev (fn [x] (if (< -1 x prev-cnt) (prev-row x) 0))]
      (vec
        (for [x (range (+ prev-cnt 1))]
          (+ (get-prev (- x 1)) (get-prev x)))))))

(def __ get-row)

(deftest problem-97-test
  (is (= (__ 1) [1]))
  (is (= (map __ (range 1 6))
   [     [1]
        [1 1]
       [1 2 1]
      [1 3 3 1]
     [1 4 6 4 1]]))

  (is (= (__ 11)
   [1 10 45 120 210 252 210 120 45 10 1])))