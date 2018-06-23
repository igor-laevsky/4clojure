(ns four-clojure.problem-77
  (:require [clojure.test :refer :all]
            [spyscope.core :refer :all]
            [four-clojure.utils :refer :all]))

(defn anagrams [coll]
  (->>
    (group-by set coll)
    (vals)
    (filter #(> (count %) 1))
    (map set)
    (set)))

(def __ anagrams)

(deftest problem-77-test
  (is (= (__ ["meat" "mat" "team" "mate" "eat"])
         #{#{"meat" "team" "mate"}}))

  (is (= (__ ["veer" "lake" "item" "kale" "mite" "ever"])
         #{#{"veer" "ever"} #{"lake" "kale"} #{"mite" "item"}}))
  )
