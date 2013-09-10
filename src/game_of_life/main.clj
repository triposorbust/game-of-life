(ns game-of-life.main
  (:gen-class :main true)
  (:require [game-of-life.core :refer [make-step-function]]
            [game-of-life.rules :refer :all]
            [game-of-life.vis :refer [run]]))

(defn -main [& args]
  (run (make-step-function conways-rule)))
