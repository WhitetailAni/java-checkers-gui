# java-checkers-gui
GUI version of my CLI checkers, written in Java. Uses StdDraw for graphics.

It uses [java-checkers-cli](https://github.com/RealKGB/java-checkers-cli) as a base, and adds GUI elements on top of it.

Plan:
Use StdDraw to represent CheckerBoard[][] passively only - no interaction. CLI will still be used to play the game.
Once that's done, start switching CLI inputs to GUI inputs using mouseX, mouseY, and similar
