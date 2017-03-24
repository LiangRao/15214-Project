hw4(b) grading rubric 



hw4b Feedback
============

#### Implementation of Scrabble game (58/60)
	* 20/20, Your implementation seems to completely or almost completely implement the functionality related to the game’s board (squares, tiles, special tiles, drawing and exchanging tiles, purchasing special tiles, making moves, etc). Well done.
	* 7/7, Your implementation seems to completely or almost completely implement the functionality related to the validating a move in the game (checking placement, etc.). Well done!
	* 10/10, Your implementation seems to completely or almost completely implement the functionality related to scoring a move in the game (adding points for tiles and words, applying multipliers and scoring-related special tiles, etc). Well done.
	* 6/8, You implemented most of the functionality related to challenging a move in the game (validating words, reverting board state, etc), but we identified an issue:
		* You perform the actions of special tiles when the player makes a move (before the challenge), but we could not identify how you undo those effects. Namely, how are the tiles affected by (for example) a boom tile replaced?
	* 15/15, Your implementation seems to completely or almost completely implement the special tiles in the game (reverse order, boom, negative points, and own tile). Well done.

#### Program Design (15/25)
	* 15/25, The design aspects of your implementation are mostly reasonable, but we have some major suggestions for improvement. We are specifically concerned regarding the extensibility of your design:
		* The implementation of your special tiles is coupled heavily with the implementation of your game. You should minimize the need for methods like ([`makeBoomKeyWord`](https://github.com/CMU-15-214/lrao/blob/8893d68273f6adf9211ae010a7106624558cfd15/homework/4/src/main/java/edu/cmu/cs/cs214/hw4/core/Board.java#L629)) or fields like ([`boomFlag`](https://github.com/CMU-15-214/lrao/blob/8893d68273f6adf9211ae010a7106624558cfd15/homework/4/src/main/java/edu/cmu/cs/cs214/hw4/core/ScrabbleSystem.java#L37))
		* Many of the move-checking methods in your Board class contain code that is duplicated many times to account for checking along columns or rows. You should try to generalize this process to use a single method which is called differently for columns and rows. ([link](https://github.com/CMU-15-214/lrao/blob/8893d68273f6adf9211ae010a7106624558cfd15/homework/4/src/main/java/edu/cmu/cs/cs214/hw4/core/Board.java#L163))

#### Testing and Build Automation (20/25)
	* 5/10, Your tests seem mostly reasonable, but we have some suggestions for improvement:
		* You perform tests of large sequences of functionality instead of unit tests that would test the individual pieces. For example, instead of (or at least in addition to) testing a complete sequence of moves in the game, it would be preferable to test word validation and point counting separately on several individual moves.
		* It is difficult to identify what your test cases are testing, and there is no documentation about the purpose. You should write your test methods with more informative names and comments which specify what the expected behavior is.
		* Each test should correspond to the verification of a single behavior, not necessarily just one test per method. You may need to check multiple behaviors of a single method.
	* 10/10, Your testing coverage seems reasonable.
	* 5/5, Build automation using gradle and Travis CI seems to work fine.

#### Documentation and Style (5/10)
	* 5/10, There are several repeated issues regarding documentation and style that you might want to address:
		* Your commit messages are not very descriptive. Please write reasonable commit messages that describe the changes in each commit.
		* Using enums to return non-binary information (not just true/false) would be better than Strings. Using enums would allow the compiler to check for typos and make it more clear what values can be returned from this function ([link](https://github.com/CMU-15-214/lrao/blob/8893d68273f6adf9211ae010a7106624558cfd15/homework/4/src/main/java/edu/cmu/cs/cs214/hw4/core/Board.java#L285))
		* [minor] Instead of retrieving the Iterator and iterating using a while loop, you can just iterate using `for(Entry<Square, Tile> entry : move.getTileMap().entrySet())`
		* `Timer` is not a reasonable name for the Premium Square classes. This increases representational gap in your design.
		* [minor] It does no good to store your dictionary as a static variable if the method to retrieve from the dictionary is not static. ([link](https://github.com/CMU-15-214/lrao/blob/8893d68273f6adf9211ae010a7106624558cfd15/homework/4/src/main/java/edu/cmu/cs/cs214/hw4/core/Dictionary.java#L17))

---

#### Total (98/120)

Late days used: 0 (5 left)

---

#### Additional Notes

Graded by: Tim Brown (twbrown@andrew.cmu.edu)
