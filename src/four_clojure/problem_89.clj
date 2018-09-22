(ns four-clojure.problem-89
  (:require [clojure.test :refer :all]
            [spyscope.core :refer :all]))

(defn euler? [g]
  (let [connected? (fn [cur-vs cur-g]
                     (let [new-vs (->> cur-g
                                      (filter #(cur-vs (first %)))
                                      (map second))
                           new-g (->> cur-g
                                      (filter #(if (cur-vs (first %)) false true)))]
                       (cond
                         (empty? new-g) true
                         (empty? new-vs) false
                         :else (recur (set new-vs) new-g))))
        full-g (concat g (map (fn [[a b]] [b a]) g))
        is-connected (connected? #{(ffirst full-g)} full-g)
        euler-cnt (->> full-g
                       (group-by first)
                       (map #(count (second %)))
                       (filter odd?)
                       (count))]
    (and is-connected (even? euler-cnt) (<= euler-cnt 2))))

(def __ euler?)

(deftest problem-89-test
  (is (= true (__ [[:a :b]])))
  (is (= false (__ [[:a :a] [:b :b]])))
  (is (= false (__ [[:a :b] [:a :b] [:a :c] [:c :a]
                    [:a :d] [:b :d] [:c :d]])))
  (is (= true (__ [[1 2] [2 3] [3 4] [4 1]])))
  (is (= true (__ [[:a :b] [:a :c] [:c :b] [:a :e]
              [:b :e] [:a :d] [:b :d] [:c :e]
              [:d :e] [:c :f] [:d :f]])))
  (is (= false (__ [[1 2] [2 3] [2 4] [2 5]])))
  )
