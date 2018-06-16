(ns four-clojure.problem-122
  (:require [clojure.test :refer :all]
            [clojure.tools.trace :refer [trace-forms]]))

;(defn to-binary
;  [s]
;  (loop [val 0
;         ss (seq s)]
;    (if ss
;      (recur (+ (* val 2) (Integer. (str (first ss)))) (next ss))
;      val)))

;(defn to-binary
;  [s] (Integer/parseInt s 2))

(defn to-binary
  [s]
  (->> s
       (map #(Integer. (str %)))
       (reduce #(+ (* 2 %1) %2) 0)))

(def __ (trace-forms to-binary))

(deftest problem-122-test
  (is (= 0     (__ "0")))
  (is (= 7     (__ "111")))
  (is (= 8     (__ "1000")))
  (is (= 9     (__ "1001")))
  (is (= 255   (__ "11111111")))
  (is (= 1365  (__ "10101010101")))
  (is (= 65535 (__ "1111111111111111")))
  )