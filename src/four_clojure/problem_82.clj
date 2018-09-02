(ns four-clojure.problem-82
  (:require [clojure.test :refer :all]
             [spyscope.core :refer :all]))

(defn chain?
  ([words] (boolean (some #(chain? #spy/p % (disj words %)) words)))
  ([cur-w words]
   (let [adj?
         (fn [a b]
           (let [inter-cnt (count (clojure.set/union
                                    (clojure.set/difference (set a) (set b))
                                    (clojure.set/difference (set b) (set a))))
                 len-diff (- (count a) (count b))]
             (cond
               (= (Math/abs len-diff) 1) (<= inter-cnt 1)
               (= len-diff 0) (= inter-cnt 2)
               :default false)))]
     (if (empty? words)
       true
       (some #(if (adj? cur-w %) (chain? % (disj words %))) words)))))

(def __ chain?)

(deftest problem-82-test
  (is (= true (__ #{"hat" "coat" "dog" "cat" "oat" "cot" "hot" "hog"})))
  (is (= false (__ #{"cot" "hot" "bat" "fat"})))
  (is (= false (__ #{"to" "top" "stop" "tops" "toss"})))
  (is (= true (__ #{"spout" "do" "pot" "pout" "spot" "dot"})))
  (is (= true (__ #{"share" "hares" "shares" "hare" "are"})))
  (is (= false (__ #{"share" "hares" "hare" "are"})))
  )