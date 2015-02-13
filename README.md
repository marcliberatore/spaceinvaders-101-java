# Space Invaders 104

## Description

Space Invaders 101 is a Java2D tutorial game, written and placed in the public domain by [Kevin Glass].

See the [tutorial] for a nuts-and-bolts explanation of how the game was designed and implemented.

[Kevin Glass]:http://www.cokeandcode.com/
[tutorial]:https://github.com/marcliberatore/spaceinvaders-101-java/blob/master/TUTORIAL.md

## Build Instructions

You'll need Apache Maven 3.0+ and a recent JDK. Clone the repository, and use:

```bash
cd spaceinvaders-101-java
mvn package
```

to create an executable jar in `target`. 

## Running the Game

You can execute the jar by double-clicking it in your GUI, or using

```bash
java -jar spaceinvaders-java-101-1.0.1.jar
```

after navigating to the `target` directory.

## License

All Java code placed in the public domain by Kevin Glass.

Sprites taken from [SpriteLib], licensed under the Common Public License 1.0.

[SpriteLib]:http://www.widgetworx.com/widgetworx/portfolio/spritelib.html
