(ns game-of-life.rules)

(defn conways-rule [alive number-of-neighbors]
  (or (and alive (or (= number-of-neighbors 2) (= number-of-neighbors 3)))
      (and (not alive) (= number-of-neighbors 3))))
