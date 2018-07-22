(ns four-clojure.problem-84
  (:require [clojure.test :refer :all]
            [four-clojure.utils :refer :all]))

(defn  trans-closure
  ([g] (into #{} (mapcat #(trans-closure g %) g)))
  ([g e]
   (let [[u v] e
         adj (filter #(= (first %) v) g)
         v-closed (mapcat #(trans-closure g %) adj)
         u-closed (for [x v-closed] [u (second x)])]
     (into #{} (concat [e] u-closed v-closed)))))

(def __ trans-closure)

(deftest problem-84-test
  (is (let [divides #{[8 4] [9 3] [4 2] [27 9]}]
        (= (__ divides) #{[4 2] [8 4] [8 2] [9 3] [27 9] [27 3]})))

  (is (let [more-legs
            #{["cat" "man"] ["man" "snake"] ["spider" "cat"]}]
        (= (__ more-legs)
           #{["cat" "man"] ["cat" "snake"] ["man" "snake"]
             ["spider" "cat"] ["spider" "man"] ["spider" "snake"]})))

  (is (let [progeny
            #{["father" "son"] ["uncle" "cousin"] ["son" "grandson"]}]
        (= (__ progeny)
           #{["father" "son"] ["father" "grandson"]
             ["uncle" "cousin"] ["son" "grandson"]})))
  )
