(ns game-of-life.vis-test
  (:require [clojure.test :refer :all]
            [game-of-life.vis :refer :all]))

(deftest test-wrap-positions
  (testing "Appropriate position wrapping."
    (let [n                 number-of-squares
          initial-positions [[(+ n 1) (+ (* n 2) 2)] [(* 3 n) (+ n 3)]]
          wrapped-positions (wrap-positions initial-positions)]
      (is (contains? wrapped-positions [1 2]))
      (is (contains? wrapped-positions [0 3])))))

