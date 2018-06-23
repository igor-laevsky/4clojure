(ns four-clojure.problem-65
  (:require [clojure.test :refer :all]))

(defn coll-type [coll]
  (cond
    (= (get (conj coll [::probe 1]) ::probe) 1) :map
    (= (get (conj coll [::probe 1]) [::probe 1]) [::probe 1]) :set
    (= (first (conj coll 1 ::probe)) ::probe) :list
    (= (last (conj coll 1 ::probe)) ::probe) :vector
    :default (assert false)))

(def __ coll-type)

(deftest problem-65-test
  (is (= :map (__ {:a 1, :b 2})))
  (is (= :list (__ (range (rand-int 20)))))
  (is (= :vector (__ [1 2 3 4 5 6])))
  (is (= :set (__ #{10 (rand-int 5)})))
  (is (= [:map :set :vector :list] (map __ [{} #{} [] ()])))
  )