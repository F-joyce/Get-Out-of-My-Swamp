## Quickstart
Download GetOut.jar and execute it in your system. Requires an installation of Java in the environment where the runnable jar file is executed.
A window will appear asking for input. The number chosen will determine the size of the swamp. To begin click **Re/Start Game** and your character, *Hek*, will appear at random inside the swamp. 
To proceed in the game click the **Move** button. Hek will move in one of the nearby positions, and there is one chance in three a *enemy* will appear at the top left corner. The buttons **Grumpy** and **Passive** will change how Hek deals with enemies. When Hek is grumpy he can defeat up to two enemies when they are in the same position. When Hek is passive he will only be able to deal with one enemy at a time, or he will die... 
The game is set up to be unwinnable. Hek will wander until, eventually will find too many enemies. 

## Summary of the project
Project demonstrating knowledge and usage of key data structures and object oriented development concepts:
- Polymorphic programming and Inheritance
- Collections and Data Structures   
- Design patterns (Observer, Controller and Strategy patterns)
- Unit Testing
- Interfaces

## Project Structure

#### game
Code for the general game logic and GUI. The MessageHandler class implements the Observer pattern.
The GUI is built at run-time by the GuiGridBuilder class, this is possible due to the data structure selected for the Swamp class.
Changing the variable SIZE, which the user input at the start of the game, changes the size of the swamp and of the gui. 

#### players
All characters in the game inherit from GameCharacter, as they all share some attributes.
Fight Behaviour implement the command pattern to change the game logic at runtime.

#### swamp
The gridBuilder build the swamp at runtime depending on the input of the player. Movement are also managed by the Square class.

#### testSuite
The test suite test the most important units of the game: Square, Movement, Swamp, Ogre.

#### toDo
- Improve movement logic to allow different movements patterns for different character types.
- Make GUI more pleasant.


