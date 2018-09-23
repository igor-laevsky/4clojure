(ns four-clojure.problem-113
  (:require [clojure.test :refer :all]))

(defn weird-sort [& els]
  (reify
    java.lang.Object
    (toString [this] (apply str (drop-last (interleave (sort els) (repeat ", ") ))))
    clojure.lang.Seqable
    (seq [this] (seq (distinct els)))))

(def __ weird-sort)

(deftest problem-113-test
  (is (= "1, 2, 3" (str (__ 2 1 3))))
  (is (= '(2 1 3) (seq (__ 2 1 3))))
  (is (= '(2 1 3) (seq (__ 2 1 3 3 1 2))))
  (is (= '(1) (seq (apply __ (repeat 5 1)))))
  (is (= "1, 1, 1, 1, 1" (str (apply __ (repeat 5 1)))))
  (is (and (= nil (seq (__)))
           (=  "" (str (__)))))
  )
