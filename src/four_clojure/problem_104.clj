(ns four-clojure.problem-104
  (:require [clojure.test :refer :all]))

(defn to-roma [num]
  (let [syms ["M" "CM" "D" "CD" "C" "XC" "L" "XL" "X" "IX" "V" "IV" "I"]
        vals [1000 900 500 400 100 90 50 40 10 9 5 4 1]
        v2s (zipmap vals syms)]
    (when (>= num 1)
      (let [off (some #(when (<= % num) %) vals)]
        (str (v2s off) (to-roma (- num off)))))))

(let [m [1 \I 4 "IV" 5 "V" 9 "IX" 10 \X 40 "XL" 50 \L 90
           "XC" 100 \C 400 "CD" 500 \D 900 "CM" 1000 \M 4000]
        pm (partition-all 2 m)
        mm (map cons (map first pm) (cons nil pm))]
  (prn #spy/p m #spy/p pm #spy/p mm))

(def __ to-roma)

(deftest problem-104-test
  (is (= "I" (__ 1)))
  (is (= "XXX" (__ 30)))
  (is (= "IV" (__ 4)))
  (is (= "CXL" (__ 140)))
  (is (= "DCCCXXVII" (__ 827)))
  (is (= "MMMCMXCIX" (__ 3999)))
  (is (= "XLVIII" (__ 48)))
  )
