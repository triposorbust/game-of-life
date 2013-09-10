(ns game-of-life.rules-test
  (:require [clojure.test :refer :all]
            [game-of-life.rules :refer :all]))

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
