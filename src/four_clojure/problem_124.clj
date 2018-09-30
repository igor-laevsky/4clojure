(ns four-clojure.problem-124
  (:require [clojure.test :refer :all]
            [spyscope.core :refer :all]))

(defn reversi-turn [field my-color]
  (let [size-y 4
        size-x 4
        enemy-color ({'w 'b 'b 'w} my-color)
        in-bounds? (fn [[y x]] (and (>= y 0) (< y size-y)
                                    (>= x 0) (< x size-x)))
        empty? (fn [p] (= (get-in field p) 'e))
        line (fn [start off] (->> (iterate #(map + % off) start) (take-while in-bounds?) (next)))
        adj-lines (fn [start] (map #(line start %) [[0 1] [0 -1] [1 0] [-1 0] [1 1] [-1 -1] [1 -1] [-1 1]]))
        get-colors (fn [cells] (map #(get-in field %) cells))
        flipped (fn [line]
                  (let [[enemy-cells my-cells & rest] (partition-by #(get-in field %) line)
                        enemy-colors (get-colors enemy-cells)
                        my-colors (get-colors my-cells)]
                    (if (and (not-empty enemy-colors)
                             (not-empty my-colors)
                             (every? #(= % enemy-color) enemy-colors)
                             (every? #(= % my-color) my-colors))
                      enemy-cells
                      [])))]
    (into {} (for [y (range 0 size-y)
                   x (range 0 size-x)
                   :when (empty? [y x])
                   :let [lines (adj-lines [y x])
                         flips (mapcat flipped lines)]
                   :when (not-empty flips)]
               [[y x] (set flips)]))))

(def __ reversi-turn)

(deftest problem-124-test
  (is (= {[1 3] #{[1 2]}, [0 2] #{[1 2]}, [3 1] #{[2 1]}, [2 0] #{[2 1]}}
         (__ '[[e e e e]
               [e w b e]
               [e b w e]
               [e e e e]] 'w)))
  (is (= {[3 2] #{[2 2]}, [3 0] #{[2 1]}, [1 0] #{[1 1]}}
         (__ '[[e e e e]
               [e w b e]
               [w w w e]
               [e e e e]] 'b)))
  (is (= {[0 3] #{[1 2]}, [1 3] #{[1 2]}, [3 3] #{[2 2]}, [2 3] #{[2 2]}}
         (__ '[[e e e e]
               [e w b e]
               [w w b e]
               [e e b e]] 'w)))
  (is (= {[0 3] #{[2 1] [1 2]}, [1 3] #{[1 2]}, [2 3] #{[2 1] [2 2]}}
         (__ '[[e e w e]
               [b b w e]
               [b w w e]
               [b w w w]] 'b)))
  )
