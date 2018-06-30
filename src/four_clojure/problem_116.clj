(ns four-clojure.problem-116
  (:require [clojure.test :refer :all]
            [clojure.tools.trace :refer :all]))

(defn balanced? [p]
  (letfn [(is-prime [x] (not (some #(= (mod x %) 0) (range 2 (dec x)))))
          (primes [x] (lazy-seq (if (is-prime x)
                                  (cons x (primes (inc x)))
                                  (primes (inc x)))))]
    (let [[lhs rhs] (split-with #(< % p) (primes 2))]
      (when (seq lhs)
        (if (not= (first rhs) p)
          false
          (= (/ (+ (last lhs) (second rhs)) 2) p))))))

(def __ balanced?)

(take 15 (filter __ (range)))

(deftest problem-116-test
  (is (= false (__ 4)))
  (is (= true (__ 563)))
  (is (= 1103 (nth (filter __ (range)) 15)))
  )
