(ns four-clojure.problem-88
  (:require [clojure.test :refer :all]
            [clojure.tools.trace :refer [trace-forms trace-vars]]
            [clojure.set]))

(defn __
  [a b]
  (letfn [(minus [a b] (into #{} (remove (partial contains? b) a)))]
    (into (minus a b) (minus b a))))

(deftest problem-88-test
  (is (= (__ #{1 2 3 4 5 6} #{1 3 5 7}) #{2 4 6 7}))
  (is (= (__ #{:a :b :c} #{}) #{:a :b :c}))
  (is (= (__ #{} #{4 5 6}) #{4 5 6}))
  (is (= (__ #{[1 2] [2 3]} #{[2 3] [3 4]}) #{[1 2] [3 4]}))
  )