(ns game-of-life.core)

;; render :: set of live cells -> void
;;
;; Renders the live cells.
;; 

(defn next-lives [m s]
  (let [adjacent-cells  (fn [k] (get m k 0))
        alive?          (fn [k] (contains? s k))
        survives?       (fn [k] (let [n (adjacent-cells k)]
                                  (and (alive? k) (or (= n 2) (= n 3)))))
        newly-born?     (fn [k] (let [n (adjacent-cells k)]
                                  (and (not (alive? k)) (= n 3))))]
    (apply hash-set (for [[k _] m :when (or (survives? k) (newly-born? k))] k))))

(defn -main [& args]
  nil)
