(ns four-clojure.problem-43
  (:require [clojure.test :refer :all]
            [spyscope.core :refer :all]))

;(defn rev-inter [coll x]
;  (when (> x 0)
;    (loop [s (seq coll)
;           idx 0
;           parts (vec (repeat x []))]
;      (if-not s
;        parts
;        (recur
;          (next s)
;          (inc idx)
;          (update parts (mod idx x) #(conj % (first s))))))))

(defn rev-inter [coll x]
  (when (> x 0)
    (reduce-kv
      (fn [acc idx val] (update-in acc [(mod idx x)] #(conj % val)))
      (vec (take x (repeat [])))
      (vec coll))))

(def __ rev-inter)

(deftest problem-43-test
  (is (= (__ nil 2) '(() ())))
  (is (= (__ [1 2 3 4 5] 0) nil))
  (is (= (__ [1 2 3 4 5] -10) nil))

  (is (= (__ [1 2 3 4 5 6] 2) '((1 3 5) (2 4 6))))
  (is (= (__ (range 9) 3) '((0 3 6) (1 4 7) (2 5 8))))
  (is (= (__ (range 10) 5) '((0 5) (1 6) (2 7) (3 8) (4 9))))
  )
