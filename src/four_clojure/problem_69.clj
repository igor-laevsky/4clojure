(ns four-clojure.problem-69
  (:require [clojure.test :refer :all]))

;(defn my-merge-with
;  ([op m] m)
;  ([op dest src]
;   (reduce-kv
;     (fn [res key val] (if-let [old-val (res key)]
;                         (assoc res key (op old-val val))
;                         (assoc res key val)))
;     dest src))
;  ([op dest src & ms]
;   (let [res (my-merge-with op dest src)]
;     (apply my-merge-with (list* op res ms)))))

(defn my-merge-with
  [op & ms]
  (letfn [(merge-vals [old-val val]
            (if (nil? old-val) val (op old-val val)))
          (merge-entry [res key val]
            (update-in res [key] #(merge-vals % val)))
          (join [a b]
            (reduce-kv merge-entry a b))]
    (reduce join {} ms)))

(def __ my-merge-with)

(deftest problem-69-test
  (is (= (__ * {:a 2, :b 3, :c 4} {:a 2} {:b 2} {:c 5})
         {:a 4, :b 6, :c 20}))
  (is (= (__ - {1 10, 2 20} {1 3, 2 10, 3 15})
         {1 7, 2 10, 3 15}))
  (is (= (__ concat {:a [3], :b [6]} {:a [4 5], :c [8 9]} {:b [7]})
         {:a [3 4 5], :b [6 7], :c [8 9]}))
  )
