# Project: Nine Men's Morris Application
**FIT3077 Software Engineering: Architecture and Design** <br>
By Team 5: Gan Jia Horng | Ravindu Santhush Ratnayake | Danesh Carmel Domingo Mariapan

## Sprint 4
### Sprint 4 Submission
**Design Rationale and diagrams**: `docs/Sprint 4/sprint4.pdf`

## Setup Guide


To run the project, the user can either build or 
just run the pre-compiled executable in the repository.
### Compiled Sprint 4 executable
Prerequisites: Windows 10 OS is required for the game to run. 

The executable is self-contained and able 
to run without any other dependencies. It is under the directory `.\game\game.exe`.
Ignore the other files.


![img_1.png](img_1.png)

If you see the above message when opening the executable,
click <u>More Info</u> and click <u>Run Anyway</u>


### Building the executable
Prerequisites: IntelliJ, Minimum version of Java JDK 19, Windows 10 or above

The executable requires minimum JDK 19 to run. Please ensure the correct
version is in your PATH variable. Use your terminal to check the environment version of Java
and ensure it is at least 19.
![img.png](img.png)


JDK 19 Download:https://www.oracle.com/java/technologies/javase/jdk19-archive-downloads.html

For windows: To add to the Path manually, locate
the directory where JDK 19 is installed. Go to Menu > Edit the system environment variables >
Environment Variables... Select Path and add another directory of the form
`<YOUR DIRECTORY>\Java\jdk-19\bin\`.


### Steps
1. Download and install **JDK 19** and **IntelliJ IDE** on your system.
2. Download or Clone the Project Repository <br>
   Project Link: https://git.infotech.monash.edu/fit3077-s1-2023/MA_Friday2pm_Team5/project <br>
3. In IntelliJ, navigate to the location of the cloned repository and open `.\morris\pom.xml`. Select
`Open as Project` at the prompt.
4. Go to File > project structure. Under the project tab make sure that for SDK at least version 19 is selected.
5. Go to `project\src\main\java\com.nineman.morris\MenuApplication.java` and run the static `main` function to 
build and launch the Application.

## Game Instructions

**Current State of implementation (Basic game + Advanced Features):**
1. Player is able to place, move, and jump tokens according to the game rules.
   1. To place a token, click on any empty position. 
   2. To move a token, click on 1 of your token, proceeded by the adjacent empty position that you want to move.
      1. If an invalid token is pressed anytime in the process
      (e.g. Selecting opposing token color to move/ Move to occupied position)
      the initial token to move has to be reselected again.
   3. To jump a token, click on 1 of your token, proceeded by any empty positions that you want to move.
      1. If an invalid token is pressed anytime in the process
         (e.g. Selecting opposing token color to jump/ Jump to occupied position)
         the initial token to jump has to be reselected again.
2. When a mill is formed, the player can remove another player's token
that is not part of a mill or any of it if all of them are a mill. 
The turn indicator will not alternate the player's turn.
3. Whoever has 2 tokens left or no legal moves loses.

For the advanced feature, the team has implemented Advanced Requirement C:

_A single player may play against the computer, where the computer will randomly play a move among 
all of the currently valid moves for the computer, or any other set of heuristics of your choice_

The feature can be selected on the game menu, where the player has the choice to play as white or black.
After the player plays a move, the computer will immediately play the next random valid move.
<br/> <br/>

## Game Screenshots:

![Screenshot 2024-01-07 020459](https://github.com/DaneshCDM/FIT3077/assets/66300163/8740b102-baa3-462a-bf5e-6dbbebbfacc8) <br/>
[Game: Start Menu] <br/> <br/>

![Screenshot 2024-01-07 020553](https://github.com/DaneshCDM/FIT3077/assets/66300163/e45c640a-8474-4975-9ce8-ea16e82f6119) <br/>
[Game: Empty Board] <br/> <br/>

![Screenshot 2024-01-07 020705](https://github.com/DaneshCDM/FIT3077/assets/66300163/5ef5e6f1-dbcb-4866-8c6a-9022af7f5a1a) <br/>
[Sample Game: Early Game (Place Moves)] <br/> <br/>

![Screenshot 2024-01-07 020831](https://github.com/DaneshCDM/FIT3077/assets/66300163/45104f8d-1ab3-4a0c-ab50-0aaa1f175a45) <br/>
[Sample Game: Mid Game (Slide Moves)] <br/> <br/>

![Screenshot 2024-01-07 022727](https://github.com/DaneshCDM/FIT3077/assets/66300163/dffd72cc-21bb-40cf-8f56-444bf975728c) <br/>
[Sample Game: Late Game (Fly Moves)] <br/> <br/>

![Screenshot 2024-01-07 022757](https://github.com/DaneshCDM/FIT3077/assets/66300163/08718dff-8bd8-4baf-9d8d-902b039b51ad) <br/>
[Game: Game Over - Victory Screen] <br/> <br/>
