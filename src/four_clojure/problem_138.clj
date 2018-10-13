(ns four-clojure.problem-138
  (:require [clojure.test :refer :all]
            [spyscope.core :refer :all]))

(defn sqsq [start end]
  (let [digits (->> (iterate #(* % %) start)
                    (take-while #(<= % end))
                    (mapcat str))
        side (int (Math/ceil (Math/sqrt (count digits))))
        start-pos (- (int (Math/ceil (/ side 2))) 1)
        in-bounds? (fn [[y x]] (and (>= y 0) (>= x 0) (< y side) (< x side)))
        steps (->> (range)
                   (next)
                   (map #(if (odd? %) (apply str (concat (repeat % \r) (repeat % \d)))
                                      (apply str (concat (repeat % \l) (repeat % \u)))))
                   (apply concat))
        move (fn [pos step]
               (map + pos (case step \r [0 1] \l [0 -1] \u [-1 0] \d [1 0])))
        square (loop [cur-d (seq digits)
                      cur-s (seq steps)
                      cur-square (vec (repeat side (vec (repeat side \*))))
                      cur-pos [start-pos start-pos]]
                 (if-not cur-d
                   cur-square
                   (recur (next cur-d) (next cur-s)
                          (assoc-in cur-square cur-pos (first cur-d))
                          (move cur-pos (first cur-s)))))
        diagonal (fn [start-pos] (->> (iterate #(map + % [-1 1]) start-pos)
                                      (take-while in-bounds?)
                                      (map #(get-in square %))))
        diagonals (->> (concat (map #(vector % 0) (range 0 side))
                               (map #(vector (- side 1) %) (range 1 side)))
                       (map diagonal))
        diagonal-to-line (fn [d] (str
                                   (apply str (repeat (- side (count d)) \space))
                                   (apply str (butlast (interleave d (repeat \space))))
                                   (apply str (repeat (- side (count d)) \space))))]
    (map diagonal-to-line diagonals)))

(def __ sqsq)

(deftest problem-138-test
  (is (= (__ 2 2) ["2"]))
  (is (= (__ 2 4) [" 2 "
                   "* 4"
                   " * "]))

  (is (= (__ 3 81) [" 3 "
                    "1 9"
                    " 8 "]))

  (is (= (__ 4 20) [" 4 "
                    "* 1"
                    " 6 "]))

  (is (= (__ 2 256) ["  6  "
                     " 5 * "
                     "2 2 *"
                     " 6 4 "
                     "  1  "]))

  (is (= (__ 10 10000) ["   0   "
                        "  1 0  "
                        " 0 1 0 "
                        "* 0 0 0"
                        " * 1 * "
                        "  * *  "
                        "   *   "]))
  )
