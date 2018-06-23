(ns four-clojure.problem-75
  (:require [clojure.test :refer :all]))

(defn totient [x]
  (if (= x 1)
    1
    (letfn [(gcd [a b] (.gcd (biginteger a) (biginteger b)))]
      (->> (range 1 x)
           (filter #(= (gcd x %) 1))
           (count)))))

(def __ totient)

(deftest problem-75-test
  (is (= (__ 1) 1))
  (is (= (__ 10) (count '(1 3 7 9)) 4))
  (is (= (__ 40) 16))
  (is (= (__ 99) 60))
  )