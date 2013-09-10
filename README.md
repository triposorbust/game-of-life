# Conway's Game of Life
## And other such curios

A Clojure library for running cellular automata simulations!

## Usage

From leiningen, project can be run directly using `lein run` or built into a jar `lein jar` or standalone _uber_-jar `lein uberjar`.

## Conway's Rules

 - Live cells with fewer than 2 neighbors die, as if by loneliness. 
 - Live cells with 2 or 3 neighbors propagate happily.
 - Live cells with more than 3 neighbors die, as if by overpopulation.
 - Dead cells with exactly 3 neighbors come to life, as if by reproduction.

## References

 - "[The fantastic combinations of John Conway's new solitaire game 'life'][fcjc]"
 - "[Conway's Game of Life][cgol]" (Wikipedia)
 - "[Cellular Automata][cell]" (conwaylife.com)

## Dependencies

 - Java
 - Leiningen / Clojure
 - quil

## Authors

 - [Andy Chiang][andy]

## License

Copyright © 2013 [Andy Chiang][andy].

Distributed under the Eclipse Public License, the same as Clojure.

[fcjc]: http://web.archive.org/web/20090603015231/http://ddi.cs.uni-potsdam.de/HyFISCH/Produzieren/lis_projekt/proj_gamelife/ConwayScientificAmerican.htm
[cgol]: http://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
[cell]: http://www.conwaylife.com/wiki/Cellular_automaton
[andy]: http://www.andy-chiang.com/