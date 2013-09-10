(ns game-of-life.vis
  (:gen-class :main true)
  (:require [game-of-life.core :refer [make-step-function]]
            [game-of-life.rules :refer :all]
            [quil.core :as qc]))

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
  (qc/background 200))

(defn draw-world [xys]
  (qc/background 200)
  (qc/stroke 0)
  (qc/stroke-weight 0)
  (qc/fill 20)
  
  (let [diam   (/ pixels-per-square 2)
        offset (/ pixels-per-square 2)]
    (doseq [[x y] xys :let [px (+ offset (* x pixels-per-square))
                            py (+ offset (* y pixels-per-square))]]
      (qc/ellipse px py diam diam))))

(defn run [survival-rule]
  (let [step (make-step-function survival-rule)]
    (qc/defsketch cellular-automata
      :title "Cellular Automata!"
      :setup setup
      :draw (fn draw-function[]
              (draw-world @world)
              (reset! world (wrap-positions (step @world)))
              (when (= 0 (count @world)) (reset! world (initialize-world))))
      :size [(* pixels-per-square number-of-squares)
             (* pixels-per-square number-of-squares)])))

(defn -main [& args]
  (run conways-rule))
