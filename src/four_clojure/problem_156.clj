(ns four-clojure.problem-156
  (:require [clojure.test :refer :all]))

;(defn __ [d l]
;  (if-let [s (seq l)]
;    (assoc (__ d (next s)) (first s) d)
;    {}))

;(defn __ [d l]
;  (loop [s (seq l)
;         m {}]
;    (if-not s
;      m
;      (recur (next s) (assoc m (first s) d)))))

;(defn __ [d l]
;  (reduce #(conj %1 [%2 d]) {} l))

;(defn __ [d l]
;  (if-not (empty? l)
;    (apply assoc {} (interleave l (repeat d)))
;    {}))

(defn __ [d l]
  (if-not (empty? l)
    (apply conj {} (map vec (partition 2 (interleave l (repeat d)))))
    {}))

(deftest problem-156-test
  (is (= (__ 0 nil) {}))
  (is (= (__ 0 {}) {}))
  (is (= (__ 0 [:a :b :c]) {:a 0 :b 0 :c 0}))
  (is (= (__ "x" [1 2 3]) {1 "x" 2 "x" 3 "x"}))
  (is (= (__ [:a :b] [:foo :bar]) {:foo [:a :b] :bar [:a :b]})))
