(ns game-of-life.core)

(defn generate-neighbors [[x y]]
  (for [dx [-1 0 1] dy [-1 0 1] :when (not= [dx dy] [0 0])]
    [(+ x dx) (+ y dy)]))

(defn make-adjacency-counts [xys]
  (let [neighbors (mapcat generate-neighbors xys)
        adder (fn [m k] (assoc m k (inc (get m k 0))))]
    (reduce adder {} neighbors)))

(defn get-next-lives [m s]
  (let [adjacent-cells (fn [k] (get m k 0))
        alive?         (fn [k] (contains? s k))
        survives?      (fn [k] (let [n (adjacent-cells k)]
                                 (and (alive? k) (or (= n 2) (= n 3)))))
        newly-born?    (fn [k] (let [n (adjacent-cells k)]
                                 (and (not (alive? k)) (= n 3))))]
    (apply hash-set (for [[k _] m :when (or (survives? k) (newly-born? k))] k))))

(defn step [s] (get-next-lives (make-adjacency-counts s) s))

(defn -main [& args]
  nil)
