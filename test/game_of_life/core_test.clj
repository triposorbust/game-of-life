(ns game-of-life.core-test
  (:require [clojure.test :refer :all]
            [game-of-life.core :refer :all]))

(deftest test-next-lives
  (testing "Basic rules for cell initiation / propagation / termination."

    ;; Termination (as if by underpopulation).
    (is (empty? (next-lives {:cell 0} #{:cell})))
    (is (empty? (next-lives {:cell 1} #{:cell})))

    ;; Propagation of living cells.
    (is (contains? (next-lives {:cell 2} #{:cell}) :cell))
    (is (contains? (next-lives {:cell 3} #{:cell}) :cell))

    ;; Termination (as if by overpopulation).
    (is (empty? (next-lives {:cell 4} #{:cell})))

    ;; Initiation of new cells.
    (is (contains?  (next-lives {:cell 3} #{}) :cell))))
