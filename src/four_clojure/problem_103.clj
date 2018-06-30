(ns four-clojure.problem-103
  (:require [clojure.test :refer :all]))

(defn combinations [k ss]
  (if (= k 1)
    (set (map hash-set ss))
    (let [prev (combinations (dec k) ss)]
      (->> (for [x prev y ss] (conj x y))
           (filter #(= (count %) k))
           (set)))))

(def __ combinations)

(deftest problem-103-test
  (is (= (__ 1 #{4 5 6}) #{#{4} #{5} #{6}}))
  (is (= (__ 10 #{4 5 6}) #{}))
  (is (= (__ 2 #{0 1 2}) #{#{0 1} #{0 2} #{1 2}}))
  (is (= (__ 3 #{0 1 2 3 4}) #{#{0 1 2} #{0 1 3} #{0 1 4} #{0 2 3} #{0 2 4}
                               #{0 3 4} #{1 2 3} #{1 2 4} #{1 3 4} #{2 3 4}}))
  (is (= (__ 4 #{[1 2 3] :a "abc" "efg"}) #{#{[1 2 3] :a "abc" "efg"}}))
  (is (= (__ 2 #{[1 2 3] :a "abc" "efg"}) #{#{[1 2 3] :a} #{[1 2 3] "abc"} #{[1 2 3] "efg"}
                                            #{:a "abc"} #{:a "efg"} #{"abc" "efg"}}))
  )
