(ns four-clojure.problem-119
  (:require [clojure.test :refer :all]
            [four-clojure.utils :refer :all]))

(defn  win-tic-tac-toe [sym board]
  (letfn [(win? [b]
            (->> (concat
                   b
                   (apply map vector b)
                   [(map #(get-in b %) [[0 0] [1 1] [2 2]])]
                   [(map #(get-in b %) [[0 2] [1 1] [2 0]])])
                 (some #(= % [sym sym sym]))))]
    (->> (for [y (range 0 3)
               x (range 0 3)
               :when (= (get-in board [y x]) :e)
               :when (win? (assoc-in board [y x] sym))]
           [y x])
         (set))))

(def __ win-tic-tac-toe)

(deftest problem-119-test
  (is (= (__ :x [[:o :e :e]
                 [:o :x :o]
                 [:x :x :e]])
         #{[2 2] [0 1] [0 2]}))

  (is (= (__ :x [[:x :o :o]
                 [:x :x :e]
                 [:e :o :e]])
         #{[2 2] [1 2] [2 0]}))

  (is (= (__ :x [[:x :e :x]
                 [:o :x :o]
                 [:e :o :e]])
         #{[2 2] [0 1] [2 0]}))

  (is (= (__ :x [[:x :x :o]
                 [:e :e :e]
                 [:e :e :e]])
         #{}))

  (is (= (__ :o [[:x :x :o]
                 [:o :e :o]
                 [:x :e :e]])
         #{[2 2] [1 1]}))
  )
