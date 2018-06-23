(ns four-clojure.problem-86
  (:require [clojure.test :refer :all]
            [spyscope.core :refer :all]
            [four-clojure.utils :refer :all]))

(defn happy?
  ([n] (happy? #{n} n))
  ([seen n]
    (let [trans (->> (str n)
                     (map #(Character/getNumericValue %))
                     (map #(* % %))
                     (apply +))]
      (cond
        (= trans 1) true
        (seen trans) false
        :else (recur (conj seen trans) trans)))))

(def __ happy?)

(deftest problem-86-test
  (is (= (__ 7) true))
  (is (= (__ 986543210) true))
  (is (= (__ 2) false))
  (is (= (__ 3) false)))