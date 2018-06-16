(ns four-clojure.problem-135
  (:require [clojure.test :refer :all]
            [clojure.tools.trace :refer [trace-forms trace-vars]]))

(defn calc
  [& x]
  (loop [acc (first x)
         [op num & xs] (next x)]
    (if-not op
      acc
      (recur (op acc num) xs))))

  ;([x]
  ; (if-not (number? x)
  ;   (print "error")
  ;   x))
  ;
  ;([x op & xs]
  ; (if-not (number? x)
  ;   (print "error")
  ;   (cond
  ;     (nil? op) x
  ;     :else (op x (apply calc xs)))))

(def __ (trace-vars calc))

(deftest problem-135-test
  (is (= 7  (__ 2 + 5)))
  (is (= 42 (__ 38 + 48 - 2 / 2)))
  (is (= 8  (__ 10 / 2 - 1 * 2)))
  (is (= 72 (__ 20 / 2 + 2 + 4 + 8 - 6 - 10 * 9)))
  )