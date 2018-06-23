(ns four-clojure.problem-78
  (:require [clojure.test :refer :all]))

(defn my-tramp [f & args]
  (loop [cur-val (apply f args)]
    (if (function? cur-val)
      (recur (cur-val))
      cur-val)))

(def __ my-tramp)

(deftest problem-78-test
  (is (= (letfn [(triple [x] #(sub-two (* 3 x)))
                 (sub-two [x] #(stop?(- x 2)))
                 (stop? [x] (if (> x 50) x #(triple x)))]
           (__ triple 2))
         82))

  (is (= (letfn [(my-even? [x] (if (zero? x) true #(my-odd? (dec x))))
                 (my-odd? [x] (if (zero? x) false #(my-even? (dec x))))]
           (map (partial __ my-even?) (range 6)))
         [true false true false true false]))

  )