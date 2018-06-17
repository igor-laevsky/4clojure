(ns four-clojure.problem-50
  (:require [clojure.test :refer :all]))

;(defn group-type [coll]
;  (vals (group-by class coll)))

(defn group-type [coll]
  (->> coll (group-by type) (vals)))

;(defn group-type [coll]
;  (vals (reduce
;    (fn [acc val]
;      (let [k (class val)]
;        (assoc acc k (conj (get acc k []) val))))
;    {}
;    coll))
;  )

(def __ group-type)

(deftest problem-50-test
  (is (= (set (__ [1 :a 2 :b 3 :c])) #{[1 2 3] [:a :b :c]}))
  (is (= (set (__ [:a "foo"  "bar" :b])) #{[:a :b] ["foo" "bar"]}))
  (is (= (set (__ [[1 2] :a [3 4] 5 6 :b])) #{[[1 2] [3 4]] [:a :b] [5 6]}))
  )