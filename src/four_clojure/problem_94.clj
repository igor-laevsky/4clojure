(ns four-clojure.problem-94
  (:require [clojure.test :refer :all]))

(defn life-step [field]
  (let [nbs (fn [f x y]
              (for [dx [-1 1 0] dy [-1 1 0] :when (not= dx dy 0)]
                (get-in f [(+ y dy) (+ x dx)])))
        size-y (count field)
        size-x (count (field 0))]
    (->>
      (for [cur-y (range 0 size-y)]
        (for [cur-x (range 0 size-x)]
          (let [cur-nbs (nbs field cur-x cur-y)
                live (->> (filter #(= \# %) cur-nbs) count)]
            (cond
              (or (< live 2) (> live 3)) " "
              (= live 3) "#"
              :default (get-in field [cur-y cur-x])))))
      (map #(apply str %)))))

(def __ life-step)

(deftest problem-94-test
  (is
    (= (__
       ["      "
        " ##   "
        " ##   "
        "   ## "
        "   ## "
        "      "])
   ["      "
    " ##   "
    " #    "
    "    # "
    "   ## "
    "      "]))

  (is
    (= (__ ["     "
        "     "
        " ### "
        "     "
        "     "])
   ["     "
    "  #  "
    "  #  "
    "  #  "
    "     "]))

  (is (= (__ ["      "
        "      "
        "  ### "
        " ###  "
        "      "
        "      "])
   ["      "
    "   #  "
    " #  # "
    " #  # "
    "  #   "
    "      "]))
  )