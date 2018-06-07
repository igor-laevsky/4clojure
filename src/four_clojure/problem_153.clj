(ns four-clojure.problem-153
  (:require [clojure.test :refer :all]))

;(defn __ [s]
;  (let [set-flatten (fn [ss]
;                      (filter (complement set?)
;                              (rest (tree-seq set? seq ss))))
;        fs (set-flatten s)]
;   (= (count fs) (count (set fs)))))

;(defn __ [s]
;  (let [fs (for [subs (seq s)
;                 y (seq subs)]
;             y)]
;    (= (count fs) (count (set fs)))))

(defn __ [s]
  (apply distinct? (apply concat (seq s))))



(deftest problem-153-tests
  (is (= (__ #{#{\U} #{\s} #{\e \R \E} #{\P \L} #{\.}})
         true))

  (is (= (__ #{#{:a :b :c :d :e}
         #{:a :b :c :d}
         #{:a :b :c}
         #{:a :b}
         #{:a}})
   false))

  (is (= (__ #{#{[1 2 3] [4 5]}
         #{[1 2] [3 4 5]}
         #{[1] [2] 3 4 5}
         #{1 2 [3 4] [5]}})
   true))

  (is (= (__ #{#{'a 'b}
         #{'c 'd 'e}
         #{'f 'g 'h 'i}
         #{''a ''c ''f}})
   true))

  (is (= (__ #{#{'(:x :y :z) '(:x :y) '(:z) '()}
         #{#{:x :y :z} #{:x :y} #{:z} #{}}
         #{'[:x :y :z] [:x :y] [:z] [] {}}})
   false))

  (is (= (__ #{#{(= "true") false}
         #{:yes :no}
         #{(class 1) 0}
         #{(symbol "true") 'false}
         #{(keyword "yes") ::no}
         #{(class '1) (int \0)}})
   false))

  (is (= (__ #{#{distinct?}
         #{#(-> %) #(-> %)}
         #{#(-> %) #(-> %) #(-> %)}
         #{#(-> %) #(-> %) #(-> %)}})
   true))

  (is (= (__ #{#{(#(-> *)) + (quote mapcat) #_ nil}
         #{'+ '* mapcat (comment mapcat)}
         #{(do) set contains? nil?}
         #{, , , #_, , empty?}})
   false))
  )