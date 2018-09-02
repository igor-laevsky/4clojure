(ns four-clojure.problem-91
  (:require [clojure.test :refer :all]
            [spyscope.core :refer :all]))

(defn connected? [g]
  (letfn [(dfs [cur-v cur-g]
            (cons cur-v
                  (flatten
                    (for [e cur-g :when (= (first e) cur-v)]
                      (dfs (second e) (disj cur-g e))))))]
    (let [full-g (into g (map (fn [[a b]] [b a]) g))
          all-vs (reduce #(into %1 %2) #{} full-g)
          dfs-vs (set (dfs (ffirst full-g) full-g))]
      (= all-vs dfs-vs))))

(def __ connected?)

(deftest problem-91-test
  (is (= true (__ #{[:a :a]})))
  (is (= true (__ #{[:a :b]})))
  (is (= false (__ #{[1 2] [2 3] [3 1]
                     [4 5] [5 6] [6 4]})))
  (is (= true (__ #{[1 2] [2 3] [3 1]
                    [4 5] [5 6] [6 4] [3 4]})))
  (is (= false (__ #{[:a :b] [:b :c] [:c :d]
                     [:x :y] [:d :a] [:b :e]})))
  (is (= true (__ #{[:a :b] [:b :c] [:c :d]
                    [:x :y] [:d :a] [:b :e] [:x :a]})))
  )
