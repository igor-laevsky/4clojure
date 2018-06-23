(ns four-clojure.problem-67
  (:require [clojure.test :refer :all]))

;(defn primes [n]
;  (let [is-prime (fn [x] (not-any? #(= 0 (mod x %)) (range 2 (- x 1))))
;        s (fn s [start]
;            (lazy-seq
;              (if (is-prime start)
;                (cons start (s (inc start)))
;                (s (inc start)))))]
;    (take n (s 2))))


(defn primes [n]
  (let [is-prime (fn [x] (not-any? #(= 0 (mod x %)) (range 2 (- x 1))))]
    (->>
      (range)
      (drop 2)
      (filter is-prime)
      (take n))))

(def __ primes)

(deftest problem-67-test
  (is (= (__ 2) [2 3]))
  (is (= (__ 5) [2 3 5 7 11]))
  (is (= (last (__ 100)) 541))
  )