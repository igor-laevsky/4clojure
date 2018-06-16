(ns four-clojure.porblem-44
  (:require [clojure.test :refer :all]
            [clojure.tools.trace :refer [trace-vars]]))

;(defn rotate [n coll]
;  (when-let [s (seq coll)]
;    (if (= n 0)
;      s
;      (let [dir (if (> n 0) -1 1)
;            prev-step (rotate (+ n dir) s)]
;        (if (= dir -1)
;          (conj (vec (next prev-step)) (first prev-step))
;          (cons (last prev-step) (butlast prev-step)))))))

;(defn rotate
;  ([n coll]
;   (when-let [s (seq coll)]
;     (let [fwd (fn [ss] (conj (vec (next ss)) (first ss)))
;           bcwd (fn [ss] (cons (last ss) (butlast ss)))
;           step (if (> n 0) fwd bcwd)]
;       (nth (iterate step coll) (Math/abs n))))))

(defn rotate [n coll]
  (when-let [s (seq coll)]
    (let [pos (mod n (count coll))]
      (concat (drop pos coll) (take pos coll)))))

(def __ rotate)

(deftest problem-44-test
  (is (= (__ 2 nil) nil))
  (is (= (__ 2 [1 2 3 4 5]) '(3 4 5 1 2)))
  (is (= (__ -2 [1 2 3 4 5]) '(4 5 1 2 3)))
  (is (= (__ 6 [1 2 3 4 5]) '(2 3 4 5 1)))
  (is (= (__ 1 '(:a :b :c)) '(:b :c :a)))
  (is (= (__ -4 '(:a :b :c)) '(:c :a :b)))
  )
