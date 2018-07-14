(ns four-clojure.problem-112
  (:require [clojure.test :refer :all]
            [clojure.tools.trace :as tr]
            [spyscope.core :refer :all]))

(defn seqs-horr [top coll]
  (letfn [(consume [top coll]
            (loop [[cur-el & tail] coll
                   cur-top top
                   cur-coll []]
              (cond
                (not cur-el) [cur-top cur-coll]
                (sequential? cur-el) (let [[t c] (consume cur-top cur-el)]
                                       (recur tail t (conj cur-coll c)))
                (< (- cur-top cur-el) 0) [cur-top cur-coll]
                (number? cur-el) (recur tail (- cur-top cur-el) (conj cur-coll cur-el)))))]
    (second (consume top coll))))

(def __ seqs-horr)

(deftest problem-112-test
  (is (=  (__ 10 [1 2 [3 [4 5] 6] 7])
          '(1 2 (3 (4)))))

  (is (=  (__ 30 [1 2 [3 [4 [5 [6 [7 8]] 9]] 10] 11])
          '(1 2 (3 (4 (5 (6 (7))))))))

  (is (=  (__ 9 (range))
          '(0 1 2 3)))

  (is (=  (__ 1 [[[[[1]]]]])
          '(((((1)))))))

  (is (=  (__ 0 [1 2 [3 [4 5] 6] 7])
          '()))

  (is (=  (__ 0 [0 0 [0 [0]]])
          '(0 0 (0 (0)))))

  (is (=  (__ 1 [-10 [1 [2 3 [4 5 [6 7 [8]]]]]])
          '(-10 (1 (2 3 (4))))))
  )
