(ns four-clojure.problem-73
  (:require [clojure.test :refer :all]))

(defn winner [board]
  (let [all-rows (set (concat
                        [(map get board [0 1 2]) (map get board [2 1 0])]
                        (apply map vector board)
                        board))
        check-win (fn [player row] (every? #(= % player) row))]
    (cond
      (some #(check-win :x %) all-rows) :x
      (some #(check-win :o %) all-rows) :o
      :else nil)))

(def __ winner)

(deftest problem-73-test
  (is (= nil (__ [[:e :e :e]
                  [:e :e :e]
                  [:e :e :e]])))

  (is (= :x (__ [[:x :e :o]
                 [:x :e :e]
                 [:x :e :o]])))

  (is (= :o (__ [[:e :x :e]
                 [:o :o :o]
                 [:x :e :x]])))

  (is (= nil (__ [[:x :e :o]
                  [:x :x :e]
                  [:o :x :o]])))

  (is (= :x (__ [[:x :e :e]
                 [:o :x :e]
                 [:o :e :x]])))

  (is (= :o (__ [[:x :e :o]
                 [:x :o :e]
                 [:o :e :x]])))

  (is (= nil (__ [[:x :o :x]
                  [:x :o :x]
                  [:o :x :o]])))
  )
