(ns game-of-life.vis
  (:require [clojure.set :refer [union intersection]]
            [quil.core :as qc]))

(def stall-threshold 0.02)
(def initial-density 0.25)
(def pixels-per-square 14)
(def number-of-squares 35)

(defn initialize-world []
  (let [random (java.util.Random.)]
    (set (for [x (range number-of-squares) y (range number-of-squares)
               :when (<= (.nextDouble random) initial-density)] [x y]))))

(def world (atom (initialize-world)))

(defn wrap-positions [xys]
  (set (for [[x y] xys] [(mod x number-of-squares) (mod y number-of-squares)])))

(defn setup []
  (qc/smooth)
  (qc/frame-rate 5)
  (qc/background 220))

(defn draw-world [xys]
  (qc/background 220)
  (qc/stroke 0)
  (qc/stroke-weight 2)
  (qc/fill 150)
  
  (let [diam   (/ pixels-per-square 2)
        offset (/ pixels-per-square 2)]
    (doseq [[x y] xys :let [px (+ offset (* x pixels-per-square))
                            py (+ offset (* y pixels-per-square))]]
      (qc/ellipse px py diam diam))))

(defn run [step-function]
  (qc/defsketch cellular-automata
    :title "Cellular Automata!"
    :setup setup
    :draw (fn draw-function []
            (draw-world @world)
            (let [new-world (step-function @world)]
              (if (> (- (count (union new-world @world))
                        (count (intersection new-world @world)))
                     (* stall-threshold number-of-squares number-of-squares))
                (reset! world (wrap-positions new-world))
                (reset! world (initialize-world)))))
    :size [(* pixels-per-square number-of-squares)
           (* pixels-per-square number-of-squares)]))

