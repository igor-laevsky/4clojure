(ns four-clojure.problem-92
  (:require [clojure.test :refer :all]
            [spyscope.core :refer :all]
            [four-clojure.utils :refer :all]))

(defn from-roma [s]
  (letfn [(to-dec [c] (case c
                        \I 1 \V 5 \X 10 \L 50 \C 100 \D 500 \M 1000
                        (assert false)))]
    (let [decs (reverse (map to-dec s))
          maxes (rest (reduce (fn [acc val] (conj acc (max (last acc) val))) [0] decs))]
      (->> decs
           (map (fn [m val] (if (> m val) (- 0 val) val)) maxes)
           (apply +)))))

(def __ from-roma)

(deftest problem-92-test
  (is (= 14 (__ "XIV")))
  (is (= 827 (__ "DCCCXXVII")))
  (is (= 3999 (__ "MMMCMXCIX")))
  (is (= 48 (__ "XLVIII"))))

  ;(is (= "I" (__ 1)))
  ;(is (= "XXX" (__ 30)))
  ;(is (= "IV" (__ 4)))
  ;(is (= "CXL" (__ 140)))
  ;(is (= "DCCCXXVII" (__ 827)))
  ;(is (= "MMMCMXCIX" (__ 3999)))
  ;(is (= "XLVIII" (__ 48)))
