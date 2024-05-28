This is template for a "Choose Your Own Adventure" style game. You will need to create your own local.properties file that points to your SDK.

The game establishes a "World" object composed of a 2D array of "Place" objects. The dimensions of the world are determined by the "descriptions" string-array of places in the strings.xml file. The MainActivity will check to see if all rows have the same number of columns by checking the length of each row in the descriptions array to avoid instantiating a jagged array with the goal of making the navigation of the world for the player simpler out of the box.

Included are some example images that I created using Sketchpad.io that assumes a 3 x 3 world to help illustrate how images can be changed as the user navigates the world.
