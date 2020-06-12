# AA-Assign2 - Sudoku Solver
Assignment submission for Algorithms and Analysis 2020 by Liam Turner

## How to Run the code

Compile the code using
```
javac *.java grid/*.java solver/*.java
```

To run the framework:
```
java RmitSudoku [puzzle fileName] [game type] [solver type] [visualization] <output fileName>
```

Where:
   - puzzle fileName: name of file containing the input puzzle/grid to solve. i.e. sampleGames/easy-std-99-01.in
   - game type: type of sudoku game, one of {sudoku, killer}
   - solver type: Type of solver to use, depends on the game type
      - If (standard) Sudoku is specfied (sudoku), then solver should be on of {backtracking, algorx, dancing}, where backtracking is the backtracking algorithm for standard Sudoku, *algorx* and *dancing* are the exact cover approaches for standard Sudoku.
      - If Killer Sudoku is specfied (killer), then solver should be one of {backtracking, advanced} where *backtracking* is the backtracking algorithm for Killer Sudoku and ~~*advanced*~~ is the most efficient algorithm you can devise for solving Killer Sudoku.
   - visualization: whether to output grid before and another after solving, one of {n, y}
   - output fileName: (optional) If specified, the solved grid will be outputted to this file

EXAMPLE
```
java RmitSudoku sampleGames/easy-std-99-01.in sudoku backtracking y output.out
```
