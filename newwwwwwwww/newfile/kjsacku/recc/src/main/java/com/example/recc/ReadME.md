Project Details:

Team Members:

--BAGADI ABHINAV - 2022131--
--CHAWAN SRUJEETH - 2022141--

--Game Controls:

->To start the game, click the mouse on the screen to extend the length of the stick.
Release the stick, and it will fall onto the next pillar.
->While moving to the next pillar, press the spacebar to collect cherries, causing the stick hero to rotate upside down.

--Functionalities:

If the distance between two blocks is less than the stick's length, the stick hero dies.
If the stick's length exceeds the distance between two blocks, the stick hero dies.
Collision with any block during the game results in the stick hero's death and the end of the game.
If the stick hero is rotated and collides with a block, the stick hero dies.
The highest score is stored after each game.
The stick hero can play again after dying if he has three cherries; otherwise, he starts a new game.
Program Code Details:

->The game starts with an entry.fxml file, controlled by the Controller class. It contains three methods:

mainMenu: Starts the game when the application is opened.
startGame: Restarts the game after the stick hero dies.
revive: Allows the stick hero to revive if he has three cherries; otherwise, he needs to restart the game.

->The game itself is played in helloview.fxml, controlled by the HelloController class. This class includes methods for moving the rectangle, stick hero, and stick using transitions in parallel for two seconds. It also incorporates collision detection methods to ensure the stick hero does not collide with any block.

Additionally, if a cherry is collected, the cherry count increases, and the current score is displayed on the screen.

error cases - dont press anything while some trasntion is happeing becaus ethe error case for keyevents are not defined

THANK YOU.