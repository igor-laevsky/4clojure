(ns four-clojure.problem-178
  (:require [clojure.test :refer :all]))

(defn best-hand [inp-cards]
  (let [parse-card (fn [[s r]] {:suit (case s \H :heart \D :diamond \S :spade \C :club)
                                :rank (case r \2 0 \3 1 \4 2 \5 3 \6 4 \7 5 \8 6
                                              \9 7 \T 8 \J 9 \Q 10 \K 11 \A 12)})
        cards (map parse-card inp-cards)
        ranks (->> (map :rank cards) (sort))
        alt-ranks (->> (map :rank cards) (map #(if (= % 12) -1 %)) (sort))
        by-rank (->> (group-by :rank cards) (vals) (map count) (sort))
        by-suit (->> (group-by :suit cards) (vals) (map count) (sort))
        conseq? (fn [r] (->> (cons (first r) r) (map - r) (next) (every? #(= % 1))))
        straight-flush? (fn [] (and (= [5] by-suit)
                                    (= (count by-rank) 5)
                                    (conseq? ranks)))
        four-of-a-kind? (fn [] (= [1 4] by-rank))
        full-house? (fn [] (= [2 3] by-rank))
        flush? (fn [] (= [5] by-suit))
        straight? (fn [] (or (conseq? ranks) (conseq? alt-ranks)))
        three-of-a-kind? (fn [] (some #{3} by-rank))
        two-pair? (fn [] (= [1 2 2] by-rank))
        pair? (fn [] (some #{2} by-rank))]
    (cond
      (straight-flush?) :straight-flush
      (four-of-a-kind?) :four-of-a-kind
      (full-house?) :full-house
      (flush?) :flush
      (straight?) :straight
      (three-of-a-kind?) :three-of-a-kind
      (two-pair?) :two-pair
      (pair?) :pair
      :default :high-card)))

(best-hand ["HA" "HK" "HQ" "HJ" "HJ"])

(def __ best-hand)

(deftest problem-178-test
  (is (= :high-card (__ ["HA" "D2" "H3" "C9" "DJ"])))
  (is (= :pair (__ ["HA" "HQ" "SJ" "DA" "HT"])))
  (is (= :two-pair (__ ["HA" "DA" "HQ" "SQ" "HT"])))
  (is (= :three-of-a-kind (__ ["HA" "DA" "CA" "HJ" "HT"])))
  (is (= :straight (__ ["HA" "DK" "HQ" "HJ" "HT"])))
  (is (= :straight (__ ["HA" "H2" "S3" "D4" "C5"])))
  (is (= :flush (__ ["HA" "HK" "H2" "H4" "HT"])))
  (is (= :full-house (__ ["HA" "DA" "CA" "HJ" "DJ"])))
  (is (= :four-of-a-kind (__ ["HA" "DA" "CA" "SA" "DJ"])))
  (is (= :straight-flush (__ ["HA" "HK" "HQ" "HJ" "HT"])))
  )
