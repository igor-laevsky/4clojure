(ns four-clojure.problem-15
  (:require [clojure.test :refer :all]))

(defn __ [x] (* 2 x))

(deftest problem-15-test
  (is (= (__ 2) 4))
  (is (= (__ 3) 6))
  (is (= (__ 11) 22))
  (is (= (__ 7) 14)))
