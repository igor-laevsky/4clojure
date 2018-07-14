(ns four-clojure.problem-141
  (:require [clojure.test :refer :all]))

(defn winner [trump]
  (fn [[leader & rest :as cards]]
    (let [suit-priority (fn [card] (condp = (:suit card)
                                     trump 2
                                     (:suit leader) 1
                                     0))
          card-compare (fn [a b]
                         (compare [(suit-priority b) (:rank b)]
                                  [(suit-priority a) (:rank a)]))]
      (first (sort card-compare cards)))))

(def __ winner)

(deftest problem-141-test
  (is (let [notrump (__ nil)]
        (and (= {:suit :club :rank 9}  (notrump [{:suit :club :rank 4}
                                                 {:suit :club :rank 9}]))
             (= {:suit :spade :rank 2} (notrump [{:suit :spade :rank 2}
                                                 {:suit :club :rank 10}])))))

  (is (= {:suit :club :rank 10} ((__ :club) [{:suit :spade :rank 2}
                                             {:suit :club :rank 10}])))

  (is (= {:suit :heart :rank 8}
         ((__ :heart) [{:suit :heart :rank 6} {:suit :heart :rank 8}
                       {:suit :diamond :rank 10} {:suit :heart :rank 4}])))
  )
