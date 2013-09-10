(ns game-of-life.core-test
  (:require [clojure.test :refer :all]
            [game-of-life.rules :refer :all]
            [game-of-life.core :refer [make-step-function]]))

(def step (make-step-function conways-rule))

(deftest test-step
  (testing "Test some basic game of life patterns"

    ;; 2x2 box is a static structure.
    (let [next-square (step #{[1 1] [1 2] [2 1] [2 2]})]
      (is (= (count next-square) 4))
      (is (contains? next-square [1 1]))
      (is (contains? next-square [1 2]))
      (is (contains? next-square [2 1]))
      (is (contains? next-square [2 2])))

    ;; 1x3 beam is a rotation structure.
    (let [next-rod (step #{[7 9] [7 8] [7 7]})]
      (is (= (count next-rod) 3))
      (is (contains? next-rod [7 8]))
      (is (contains? next-rod [6 8]))
      (is (contains? next-rod [8 8])))

    ;; 3 cells on a diagonal perish in two turns.
    (let [next-diag (step #{[3 3] [4 4] [5 5]})
          next-next-diag (step next-diag)]
      (is (= (count next-diag) 1))
      (is (contains? next-diag [4 4]))
      (is (empty? next-next-diag)))))
