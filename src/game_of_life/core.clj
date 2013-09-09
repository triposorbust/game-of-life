(ns game-of-life.core)

(defn conways-rule [alive number-of-neighbors]
  (or (and alive (or (= number-of-neighbors 2) (= number-of-neighbors 3)))
      (and (not alive) (= number-of-neighbors 3))))

(defn generate-neighbors [[x y]]
  (for [dx [-1 0 1] dy [-1 0 1] :when (not= [dx dy] [0 0])]
    [(+ x dx) (+ y dy)]))

(defn count-adjacent-cells [xys]
  (let [all-neighbors (mapcat generate-neighbors xys)
        increment-map-at-key (fn [m k] (assoc m k (inc (get m k 0))))]
    (reduce increment-map-at-key {} all-neighbors)))

(defn update-cells [f m s]
  (apply hash-set (for [[k n] m :let [a (contains? s k)] :when (f a n)] k)))

(defn step [s] (update-cells conways-rule (count-adjacent-cells s) s))

(defn -main [& args]
  nil)
