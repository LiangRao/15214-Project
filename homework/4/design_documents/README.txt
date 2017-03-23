******************************************************************************************
How to play the game
1. Add player to the game: 1. Input your name, and click on “ADD” button to enroll into the game. The game should be at least 2 players, at most 4 players. And then click on “START GAME” button

2. Add a tile to the board: Click a random tile button on the tile rack, and then click a           
random square button on the board.

3. Remove a tile from the board: double-click a specific square

4. Add a special tile to the board: It is the same with add a tile to the board. But you can place only one special tile on the board on your turn.

5. Play a move: After you placing your tiles and a special tile to the board, click the “submit” button, so that you can play a move on your turn.

6. Buy a special tile: click on the “buy-a-Special-Tile” button

8. Pass this turn: Click on “pass” button

9. Exchange some tiles: Click on “exchange” button, and you will lose this turn

10. If a player invokes a boom special tile, this turn would not be challenged by others.

******************************************************************************************
Design changes
1. In domain model: Adding the price variable to SpecialTile class, and Changing the name of “name” to “letter” in the LetterTile class

2.In system sequence diagram: Reorganizing the sequence of event invoking.

3.In interaction_challenge: Removing some useless methods.

4.In interacion_move: Removing some useless methods, adding check alignment method called moveOnSameLine and clearing describing how to construct a word

5. In object model: Adding all method in the interaction diagram to the object model, but  not including any get and set method

                                    