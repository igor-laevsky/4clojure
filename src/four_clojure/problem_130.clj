(ns four-clojure.problem-130
  (:require [clojure.test :refer :all]
            [spyscope.core :refer :all]))

(defn re-parent [new-root tree]
  (let [graph ((fn build [[cur-node & children :as subtree]]
                 (if (empty? children)
                   {cur-node '()}
                   (let [child-edges (apply merge (for [c children] (build c)))
                         this-edges {cur-node (map first children)}
                         back-edges (into {} (for [c children] [(first c) [cur-node]]))]
                     (merge-with concat child-edges this-edges back-edges)))) tree)
        dfs (fn dfs [cur-v visited]
              (let [children (get graph cur-v)
                    next-visited (conj visited cur-v)]
                (list* cur-v
                       (for [c children :when (nil? (visited c))]
                         (dfs c next-visited)))))]
    (dfs new-root #{})))

(re-parent 'a '(t (e) (a)))

(def __ re-parent)

(deftest problem-130-test
  (is (= '(n)
         (__ 'n '(n))))

  (is (= '(a (t (e)))
         (__ 'a '(t (e) (a)))))

  (is (= '(e (t (a)))
         (__ 'e '(a (t (e)))))
      )

  (is (= '(a (b (c)))
   (__ 'a '(c (b (a))))))

  (is (= '(d
      (b
        (c)
        (e)
        (a
          (f
            (g)
            (h)))))
  (__ 'd '(a
            (b
              (c)
              (d)
              (e))
            (f
              (g)
              (h))))))

  (is (= '(c
      (d)
      (e)
      (b
        (f
          (g)
          (h))
        (a
          (i
          (j
            (k)
            (l))
          (m
            (n)
            (o))))))
   (__ 'c '(a
             (b
               (c
                 (d)
                 (e))
               (f
                 (g)
                 (h)))
             (i
               (j
                 (k)
                 (l))
               (m
                 (n)
                 (o)))))))

  )
