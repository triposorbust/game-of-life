(ns game-of-life.core)

(defn generate-neighbors [[x y]]
  (for [dx [-1 0 1] dy [-1 0 1] :when (not= [dx dy] [0 0])]
    [(+ x dx) (+ y dy)]))

(defn count-adjacent-cells [xys]
  (let [all-neighbors (mapcat generate-neighbors xys)
        increment-map-at-key (fn [m k] (assoc m k (inc (get m k 0))))]
    (reduce increment-map-at-key {} all-neighbors)))

(defn update-cells [survival-rule neighbor-count-map live-cell-set]
  (apply hash-set (for [[cell number-of-neighbors] neighbor-count-map
                        :let [alive (contains? live-cell-set cell)]
                        :when (survival-rule alive number-of-neighbors)] cell)))

(defn make-step-function [survival-rule]
  (fn [s] (update-cells survival-rule (count-adjacent-cells s) s)))
