(ns game-of-life.core-test
  (:require [clojure.test :refer :all]
            [game-of-life.core :refer :all]))

(def step (make-step-function conways-rule))

(deftest test-conways-rule
  (testing "Rules for cell initiation / propagation / termination."

    ;; Live cells die with {0,1} neighbors die, as if by underpopulation.
    (is (false? (conways-rule true 0)))
    (is (false? (conways-rule true 1)))

    ;; Live cells with {2,3} neighbors propagate.
    (is (true? (conways-rule true 2)))
    (is (true? (conways-rule true 3)))

    ;; Live cells with {4,} neighbors die, as if by overpopulation.
    (is (false? (conways-rule true 4)))

    ;; Empty cells with 3 neighbors are born, as if by reproduction.
    (is (true? (conways-rule false 3)))))

(deftest test-step
  (testing "Test some basic game of life patterns"

    (let [next-square (step #{[1 1] [1 2] [2 1] [2 2]})]
      (is (= (count next-square) 4))
      (is (contains? next-square [1 1]))
      (is (contains? next-square [1 2]))
      (is (contains? next-square [2 1]))
      (is (contains? next-square [2 2])))

    (let [next-rod (step #{[7 9] [7 8] [7 7]})]
      (is (= (count next-rod) 3))
      (is (contains? next-rod [7 8]))
      (is (contains? next-rod [6 8]))
      (is (contains? next-rod [8 8])))))
