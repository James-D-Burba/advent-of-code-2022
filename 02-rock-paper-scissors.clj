(require '[clojure.string :as str])
(def shapes {\X {:score 1
                 \A 3
                 \B 0
                 \C 6}
             \Y {:score 2
                 \A 6
                 \B 3
                 \C 0}
             \Z {:score 3
                 \A 0
                 \B 6
                 \C 3}})
(defn getScore [hand]
  (let [myHand (.charAt hand 2)
        otherHand (.charAt hand 0)]
    (+
     (get-in shapes [myHand :score])
     (get-in shapes [myHand otherHand]))))
(def hands
  (str/split
   (slurp "/home/jimmy/src/advent-of-code-2022/02-input.txt")
   #"\n"))
(def totalScore (reduce #(+ %1 (getScore %2)) 0 hands))