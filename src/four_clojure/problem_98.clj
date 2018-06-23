(ns four-clojure.problem-98
  (:require [clojure.test :refer :all]))

(defn equiv [f s]
  (->> s
       (group-by f)
       (vals)
       (map set)
       (set)))

(def __ equiv)

(deftest problem-98-test
  (is (= (__ #(* % %) #{-2 -1 0 1 2})
         #{#{0} #{1 -1} #{2 -2}}))
  (is (= (__ #(rem % 3) #{0 1 2 3 4 5 })
         #{#{0 3} #{1 4} #{2 5}}))
  (is (= (__ identity #{0 1 2 3 4})
         #{#{0} #{1} #{2} #{3} #{4}}))
  (is (= (__ (constantly true) #{0 1 2 3 4})
         #{#{0 1 2 3 4}}))
  )