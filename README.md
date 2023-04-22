# Project: Nine Men's Morris Application
**FIT3077 Software Engineering: Architecture and Design** <br>
By Team 5: Gan Jia Horng | Ravindu Santhush Ratnayake | Danesh Carmel Domingo Mariapan
---

## Setup Guide
1. Download and install **Java** and a suitable **IDE** on your system
2. Download and install **JavaFX** and **SceneBuilder** on your system
3. Download or Clone the Project Repository <br>
   Project Link: https://git.infotech.monash.edu/fit3077-s1-2023/MA_Friday2pm_Team5/project <br>
4. In your local Project Repository folder, delete the outermost ".idea" folder <br>
   This is to allow for automatic setup and configuration of the project files and dependencies
5. Open the Project in your IDE and run the "HelloApplication Class" to run the Application

#### Links to Required Downloads / Additional Info:
- Java (Latest Version): https://www.java.com/en/download/manual.jsp
- IDE (Intellij IDEA Recommended): https://www.jetbrains.com/idea/download/#section=windows
- JavaFX: https://gluonhq.com/products/javafx/
- SceneBuilder: https://gluonhq.com/products/scene-builder/
- Guide to Downloading / Cloning a repository: https://docs.gitlab.com/ee/user/project/repository/
---

## Game Instructions
**Updated to: Sprint Two Implementation**

***Game Objective:** <br>
The game ends and you are the WINNER when you have reduced your opponent to only
two (2) tokens or when the opponent can no longer make any valid moves on the board. <br>
This is achieved by trying to create "mills" (three (3) tokens in a row) to remove the opponents tokens from the board
while preventing the opponent from doing the same.*

**Initial Phase:** <br>
Each player takes turns placing nine (9) of their own respective tokens on the board. <br>
Players can only place their tokens on vacant / empty positions on the board. <br>
The goal is to position your tokens well so that it sets you up for success in the upcoming phases. <br>
*Click a vacant location on the board when the display shows it is your turn*
1. Player 1 (White) places a token
2. Player 2 (Black) places a token
3. The cycle repeats until both players have nine (9) tokens each on the board

**Mid-Game Phase** <br>
Players take turns moving their tokens along the lines on the board.
When a player forms a mill (three (3) tokens in a row), he/she removes one of the opponent's tokens from the board. <br>
The Mid-Game Phase ends when a player is reduced to only three (3) tokens on the board <br>
1. Player 1 (White) selects his/her token on the board
2. Player 1 (White) selects a valid move for their token to move to
3. Player 2 (Black) selects his/her token on the board
4. Player 2 (Black) selects a valid move for their token to move to
5. The cycle repeats
6. If an invalid move is made, the player has to re-select a token again (from Step 1)

*Forming Mills:*
1. Player 1 (White) places moves a token that forms a mill
2. Player 1 (White) selects an opponents token to remove (Black)
3. Player 2's (Black) turn to move
4. The cycle repeats

**End-Game Phase** <br>
Players who are reduced to only three (3) tokens can jump a token to any vacant position on the board. <br>
The game ends when a player is reduced to only two (2) tokens or is unable to move. <br>
The opposing player is then declared as the winner. <br>
1. Player 1 (White) creates a mill and removes an opponent's token
2. Player 2 (Black) is reduced to two tokens - the game ends
3. Player 1 (White) wins
---