(ns four-clojure.problem-158
  (:require [clojure.test :refer :all]))

(defn de-curry [f]
  (fn [& args]
    (reduce #(%1 %2) f args)))

(def __ de-curry)

(deftest problem-158-test
  (is (= 10 ((__ (fn [a]
                   (fn [b]
                     (fn [c]
                       (fn [d]
                         (+ a b c d))))))
              1 2 3 4)))
  (is (= 24 ((__ (fn [a]
                   (fn [b]
                     (fn [c]
                       (fn [d]
                         (* a b c d))))))
              1 2 3 4)))
  (is (= 25 ((__ (fn [a]
                   (fn [b]
                     (* a b))))
              5 5)))
  )