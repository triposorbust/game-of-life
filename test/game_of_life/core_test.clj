(ns game-of-life.core-test
  (:require [clojure.test :refer :all]
            [game-of-life.core :refer :all]))

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
      (is (contains? next-rod [8 8]))) ))))

(deftest test-get-next-lives
  (testing "Basic rules for cell initiation / propagation / termination."

    ;; Termination (as if by underpopulation).
    (is (empty? (get-next-lives {:cell 0} #{:cell})))
    (is (empty? (get-next-lives {:cell 1} #{:cell})))

    ;; Propagation of living cells.
    (is (contains? (get-next-lives {:cell 2} #{:cell}) :cell))
    (is (contains? (get-next-lives {:cell 3} #{:cell}) :cell))

    ;; Termination (as if by overpopulation).
    (is (empty? (get-next-lives {:cell 4} #{:cell})))

    ;; Initiation of new cells.
    (is (contains?  (get-next-lives {:cell 3} #{}) :cell))))
