(require '[clojure.string :as str])
(def shapes {\X {:score 1
                 \A 3
                 \B 1
                 \C 2}
             \Y {:score 2
                 \A (+ 3 1)
                 \B (+ 3 2)
                 \C (+ 3 3)}
             \Z {:score 3
                 \A (+ 6 2)
                 \B (+ 6 3)
                 \C (+ 6 1)}})
(defn getScore [hand]
  (let [neededOutcome (.charAt hand 2)
        otherHand (.charAt hand 0)]
    (get-in shapes [neededOutcome otherHand])))
(def hands
  (str/split
   (slurp "/home/jimmy/src/advent-of-code-2022/02-input.txt")
   #"\n"))
(def totalScore (reduce #(+ %1 (getScore %2)) 0 hands))