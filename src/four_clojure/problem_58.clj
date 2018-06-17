(ns four-clojure.problem-58
  (:require [clojure.test :refer :all]))

(defn my-comp [& funcs]
  (fn [& args]
    (letfn [(apply-all [f & fs]
              (if-not fs
                (apply f args)
                (f (apply apply-all fs))))]
      (apply apply-all funcs))))

(def __ my-comp)

(deftest problem-58-test
  (is (= [3 2 1] ((__ rest reverse) [1 2 3 4])))
  (is (= 5 ((__ (partial + 3) second) [1 2 3 4])))
  (is (= true ((__ zero? #(mod % 8) +) 3 5 7 9)))
  (is (= "HELLO" ((__ #(.toUpperCase %) #(apply str %) take) 5 "hello world")))
  )