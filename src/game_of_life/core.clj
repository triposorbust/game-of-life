(ns game-of-life.core)

(defn conways-rule [a n]
  (or (and a (or (= n 2) (= n 3)))
      (and (not a) (= n 3))))

(defn generate-neighbors [[x y]]
  (for [dx [-1 0 1] dy [-1 0 1] :when (not= [dx dy] [0 0])]
    [(+ x dx) (+ y dy)]))

(defn count-adjacent-cells [xys]
  (let [neighbors (mapcat generate-neighbors xys)
        adder (fn [m k] (assoc m k (inc (get m k 0))))]
    (reduce adder {} neighbors)))

(defn get-next-lives [f m s]
  (apply hash-set (for [[k n] m :let [a (contains? s k)] :when (f a n)] k)))

(defn step [s] (get-next-lives conways-rule (count-adjacent-cells s) s))

(defn -main [& args]
  nil)
