(ns four-clojure.problem-114
  (:require [clojure.test :refer :all]
            [four-clojure.utils :refer :all]))

;(defn take-upto [n p coll]
;  (butlast
;    (reduce
;      (fn [[cur-n res] val]
;        (if (zero? cur-n)
;          (reduced res)
;          [(if (p val) (dec cur-n) cur-n) (conj res val)]))
;    [n []] coll)))

(defn take-upto [n p coll]
  (when-let [s (seq coll)]
    (if-not (p (first s))
      (cons (first s) (take-upto n p (rest s)))
      (when (> n 1)
        (cons (first s) (take-upto (dec n) p (rest s)))))))

;(defn take-upto [n p coll]
;  (->> (seq coll)
;       (map #(vector % (if (p %) 1 0)))
;       (inspect)
;       (reduce
;         (fn [[[_ prev-cnt] & rest :as acc] [val cnt]] (cons [val (+ cnt prev-cnt)] acc))
;         '([0 0]))
;       (reverse)
;       (take-while (fn [[val cnt]] (< cnt n)))
;       (map (fn [[val cnt]] val))
;       (drop 1)))

(def __ take-upto)

(deftest problem-114-test
  (is (= [2 3 5 7 11 13]
         (__ 4 #(= 2 (mod % 3))
             [2 3 5 7 11 13 17 19 23])))

  (is (= ["this" "is" "a" "sentence"]
         (__ 3 #(some #{\i} %)
             ["this" "is" "a" "sentence" "i" "wrote"])))

  (is (= ["this" "is"]
         (__ 1 #{"a"}
             ["this" "is" "a" "sentence" "i" "wrote"])))
  )