(ns four-clojure.problem-115
  (:require [clojure.test :refer :all]))

(defn balanced? [n]
  (let [d (map #(Character/getNumericValue %) (str n))
        cnt (count d)
        mid (int (/ cnt 2))
        lhs (take mid d)
        rhs (drop (+ mid (mod cnt 2)) d)]
    (= (apply + lhs) (apply + rhs))))

(def __ balanced?)

(deftest problem-115-test
  (is (= true (__ 11)))
  (is (= true (__ 121)))
  (is (= false (__ 123)))
  (is (= true (__ 0)))
  (is (= false (__ 88099)))
  (is (= true (__ 89098)))
  (is (= true (__ 89089)))
  (is (= (take 20 (filter __ (range)))
         [0 1 2 3 4 5 6 7 8 9 11 22 33 44 55 66 77 88 99 101])  )
  )