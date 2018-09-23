(ns four-clojure.problem-117
  (:require [clojure.test :refer :all]
            [four-clojure.utils :refer :all]
            [spyscope.core :refer :all]))

;(defn can-exit? [field]
;  (let [size-y (count field)
;        size-x (count (first field))
;        find-pos (fn [target]
;                   (for [y (range 0 size-y)
;                         x (range 0 size-x)
;                         :let [cell (get-in field [y x])]
;                         :when (= cell target)]
;                     [y x]))
;        in-bounds? (fn [[y x]] (and (>= y 0) (< y size-y)
;                                    (>= x 0) (< x size-x)))
;        get-neighbors (fn [cords]
;                        (for [diff [[0 1] [0 -1] [1 0] [-1 0]]
;                              :let [new-cords (mapv + cords diff)]
;                              :when (in-bounds? new-cords)
;                              :when (not= (get-in field new-cords) \#)]
;                          new-cords))
;        next-iter (fn [prev] (into prev (mapcat get-neighbors prev)))
;        start-pos (first (find-pos \M))
;        end-pos (first (find-pos \C))]
;    (->> (iterate next-iter #{start-pos})
;         (reduce (fn [prev new] (if (= prev new) (reduced prev) new)) [])
;         (some #{end-pos})
;         (boolean))))

(defn can-exit? [field]
  (let [size-y (count field)
        size-x (count (first field))
        find-pos (fn [target]
                   (for [y (range 0 size-y)
                         x (range 0 size-x)
                         :let [cell (get-in field [y x])]
                         :when (= cell target)]
                     [y x]))
        in-bounds? (fn [[y x]] (and (>= y 0) (< y size-y)
                                    (>= x 0) (< x size-x)))
        get-neighbors (fn [cords]
                        (for [diff [[0 1] [0 -1] [1 0] [-1 0]]
                              :let [new-cords (mapv + cords diff)]
                              :when (in-bounds? new-cords)
                              :when (not= (get-in field new-cords) \#)]
                          new-cords))
        next-iter (fn [prev] (into prev (mapcat get-neighbors prev)))
        start-pos (first (find-pos \M))
        end-pos (first (find-pos \C))]
    (->> (iterate next-iter #{start-pos})
         (partition 2 1)
         (take-while (fn [[prev next]] (not= prev next)))
         (last)
         (second)
         (some #{end-pos})
         (boolean))))


(def __ can-exit?)

(deftest problem-117-test
  (is (= true  (__ ["M   C"])))
  (is (= false (__ ["M # C"])))
  (is (= true  (__ ["#######"
                    "#     #"
                    "#  #  #"
                    "#M # C#"
                    "#######"])))
  (is (= false (__ ["########"
                    "#M  #  #"
                    "#   #  #"
                    "# # #  #"
                    "#   #  #"
                    "#  #   #"
                    "#  # # #"
                    "#  #   #"
                    "#  #  C#"
                    "########"])))
  (is (= false (__ ["M     "
                    "      "
                    "      "
                    "      "
                    "    ##"
                    "    #C"])))
  (is (= true  (__ ["C######"
                    " #     "
                    " #   # "
                    " #   #M"
                    "     # "])))
  (is (= true  (__ ["C# # # #"
                    "        "
                    "# # # # "
                    "        "
                    " # # # #"
                    "        "
                    "# # # #M"])))
  )
