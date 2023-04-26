# Project: Nine Men's Morris Application
**FIT3077 Software Engineering: Architecture and Design** <br>
By Team 5: Gan Jia Horng | Ravindu Santhush Ratnayake | Danesh Carmel Domingo Mariapan

### Sprint 2 Submission
**Design Rationale**: `docs/Sprint 2/sprint2.pdf`

**Git Tag:** sprint_2

---

## Setup Guide
To run the project, the user can either build an executable or just run `morris.jar` in the repository.


### Build the executable
1. Download and install **Java** and **IntelliJ IDE** on your system.
3. Download or Clone the Project Repository <br>
   Project Link: https://git.infotech.monash.edu/fit3077-s1-2023/MA_Friday2pm_Team5/project <br>
4. In IntelliJ, navigate to the location of the cloned repository is cloned and open `project\morris\pom.xml`. Select
`Open as Project` at the prompt.
5. Go to `project\src\main\java\com.nineman.morris\MenuApplication.java` and run the static `main` function to launch the Application.

#### Links to Required Downloads / Additional Info:
- Java (Latest Version): https://www.java.com/en/download/manual.jsp
- IDE (Intellij IDEA Recommended): https://www.jetbrains.com/idea/download/#section=windows
- JavaFX: https://gluonhq.com/products/javafx/
- SceneBuilder: https://gluonhq.com/products/scene-builder/
- Guide to Downloading / Cloning a repository: https://docs.gitlab.com/ee/user/project/repository/
---

## Game Instructions
**Updated to: Sprint Two Implementation**

**Current State of implementation:**
1. Player is able to place, move, and jump tokens according to the game rules.
   1. To place a token, click on any empty position. 
   2. To move a token, click on 1 of your token, then the adjacent empty position that you want to move.
      1. If an invalid token is pressed anytime in the process
      (e.g. Selecting opposing token color to move/ Move to occupied position)
      the initial token to move has to be reselected again.
   3. To jump a token, click on 1 of your token, then any empty position that you want to move.
      1. If an invalid token is pressed anytime in the process
         (e.g. Selecting opposing token color to move/ Move to occupied position)
         the initial token to jump has to be reselected again.
3. When a mill is formed, the player can remove **any** of another player's token. The turn indicator will not 
alternate the player's turn yet.

## Not implemented yet
1. Disable removing tokens that form a mill
2. Game Victory Screen when one of the player has less than two tokens
3. Better prompts so the player understand what to do without referencing `README.md`

[//]: # (***Game Objective:** <br>)

[//]: # (The game ends and you are the WINNER when you have reduced your opponent to only)

[//]: # (two &#40;2&#41; tokens or when the opponent can no longer make any valid moves on the board. <br>)

[//]: # (This is achieved by trying to create "mills" &#40;three &#40;3&#41; tokens in a row&#41; to remove the opponents tokens from the board)

[//]: # (while preventing the opponent from doing the same.*)

[//]: # ()
[//]: # (**Initial Phase:** <br>)

[//]: # (Each player takes turns placing nine &#40;9&#41; of their own respective tokens on the board. <br>)

[//]: # (Players can only place their tokens on vacant / empty positions on the board. <br>)

[//]: # (The goal is to position your tokens well so that it sets you up for success in the upcoming phases. <br>)

[//]: # (*Click a vacant location on the board when the display shows it is your turn*)

[//]: # (1. Player 1 &#40;White&#41; places a token)

[//]: # (2. Player 2 &#40;Black&#41; places a token)

[//]: # (3. The cycle repeats until both players have nine &#40;9&#41; tokens each on the board)

[//]: # ()
[//]: # (**Mid-Game Phase** <br>)

[//]: # (Players take turns moving their tokens along the lines on the board.)

[//]: # (When a player forms a mill &#40;three &#40;3&#41; tokens in a row&#41;, he/she removes one of the opponent's tokens from the board. <br>)

[//]: # (The Mid-Game Phase ends when a player is reduced to only three &#40;3&#41; tokens on the board <br>)

[//]: # (1. Player 1 &#40;White&#41; selects his/her token on the board)

[//]: # (2. Player 1 &#40;White&#41; selects a valid move for their token to move to)

[//]: # (3. Player 2 &#40;Black&#41; selects his/her token on the board)

[//]: # (4. Player 2 &#40;Black&#41; selects a valid move for their token to move to)

[//]: # (5. The cycle repeats)

[//]: # (6. If an invalid move is made, the player has to re-select a token again &#40;from Step 1&#41;)

[//]: # ()
[//]: # (*Forming Mills:*)

[//]: # (1. Player 1 &#40;White&#41; places moves a token that forms a mill)

[//]: # (2. Player 1 &#40;White&#41; selects an opponents token to remove &#40;Black&#41;)

[//]: # (3. Player 2's &#40;Black&#41; turn to move)

[//]: # (4. The cycle repeats)

[//]: # ()
[//]: # (**End-Game Phase** <br>)

[//]: # (Players who are reduced to only three &#40;3&#41; tokens can jump a token to any vacant position on the board. <br>)

[//]: # (The game ends when a player is reduced to only two &#40;2&#41; tokens or is unable to move. <br>)

[//]: # (The opposing player is then declared as the winner. <br>)

[//]: # (1. Player 1 &#40;White&#41; creates a mill and removes an opponent's token)

[//]: # (2. Player 2 &#40;Black&#41; is reduced to two tokens - the game ends)

[//]: # (3. Player 1 &#40;White&#41; wins)

[//]: # (---)