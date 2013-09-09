(ns game-of-life.core)

(defn generate-neighbors [[x y]]
  "Displaces one square in each direction, generating 8 neighbours on a 
   square grid."
  (for [dx [-1 0 1] dy [-1 0 1] :when (or (not= dx 0) (not= dy 0))]
    [(+ x dx) (+ y dy)]))


(defn get-next-lives [m s]
  "Applies a set of rules to determine whether a cell is alive or dead
   in the next step, based on the adjacency counts in map and the status
   s in the current time step."
  (let [adjacent-cells  (fn [k] (get m k 0))
        alive?          (fn [k] (contains? s k))
        survives?       (fn [k] (let [n (adjacent-cells k)]
                                  (and (alive? k) (or (= n 2) (= n 3)))))
        newly-born?     (fn [k] (let [n (adjacent-cells k)]
                                  (and (not (alive? k)) (= n 3))))]
    (apply hash-set (for [[k _] m :when (or (survives? k) (newly-born? k))] k))))

(defn -main [& args]
  nil)
