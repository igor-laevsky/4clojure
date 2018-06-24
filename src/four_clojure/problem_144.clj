(ns four-clojure.problem-144
  (:require [clojure.test :refer :all]
            [four-clojure.utils :refer :all]))

;(defn oscilate [x & funcs]
;  (letfn [(step [idx prev-val & funcs]
;            (lazy-seq
;              (let [op (nth funcs idx)
;                    val (op prev-val)
;                    next-idx (mod (inc idx) (count funcs))]
;                (cons val (apply step (list* next-idx val funcs))))))]
;    (cons x (apply step (list* 0 x funcs)))))

(defn oscilate [x op & rest]
  (lazy-seq
    (let [val (op x)
          rot (conj (vec rest) op)]
      (cons x (apply oscilate val rot)))))

(def __ oscilate)

(deftest problem-144-test
  (is (= (take 3 (__ 3.14 int double)) [3.14 3 3.0]))
  ;(is (= (take 5 (__ 3 #(- % 3) #(+ 5 %))) [3 0 5 2 7]))
  ;(is (= (take 12 (__ 0 inc dec inc dec inc)) [0 1 0 1 0 1 2 1 2 1 2 3]))
  )