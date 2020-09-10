An Android app called Twitterr.

Technologies Used

1.	GDX library
2.	Local data storage
3.	Objects creations using GDX
4.	Shaperendrer

Functions

1.	Play n times
2.	Score turns to 0 as soon as you die


Details

This game is inspired from the famous game Flappy Bird. 

Here I used Gdx library to use to develop this game. I used shaperender to create the shapes for bird, tubes.

The tube and bird are just the images.

The wings of birds seems to move as two images are used having different positions of the wings.

Images have been set on the screen using simple mathematics calulations.

Only 4 tubes move and gap between them keeps changing.

To randomize the position of the tubes I used randomgenerator.

The bird dies as soon as it touches any tube or the ground. To detect a collision I used Intersector.overlap function.

The game have 2 gamestates- 
1 - when the player has not started playing yet, in this state bird stays at one place and tube do not move
2 - when player starts playing the gravity comes in place and bird starts falling. You have to keep tapping to keep it in the air.

Game Over message pops up as soon as the bird die, and you can play again by tapping anywhere on the screen.
