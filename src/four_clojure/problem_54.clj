(ns four-clojure.problem-54
  (:require [clojure.test :refer :all]))

(defn my-partition [n coll]
  (lazy-seq
    (when-let [s (seq coll)]
      (when (<= n (count coll))
        (cons (doall (take n s)) (my-partition n (nthrest s n)))))))

(def __ my-partition)

(deftest problem-54-test
  (is (= (__ 3 (range 9)) '((0 1 2) (3 4 5) (6 7 8))))
  (is (= (__ 2 (range 8)) '((0 1) (2 3) (4 5) (6 7))))
  (is (= (__ 3 (range 8)) '((0 1 2) (3 4 5))))
  )