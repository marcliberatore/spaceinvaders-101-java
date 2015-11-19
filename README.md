# Space Invaders 104


## Description

Space Invaders 104 is the last stage of [Kevin Glass] ' famous Series of Java2D Game Programming Tutorials.
See the [original tutorial] for explanations of how the game was originally designed and implemented.
This Version extends the [101 maven tutorial] done by [marcliberatore]. 
It uses Maven as complete Dependency and Build Tool handling native Dependencies. 
The [maven natives] plugin as demonstrated by the [maven-nativedependencies-example] creates a Distribution containing Native Libraries for Windows and *nix-Plattforms that are required by [LWJGL 2.x Framework]. For more Details read about [LWJGL Maven Integration].

[Kevin Glass]:http://www.cokeandcode.com/
[original tutorial]:http://www.cokeandcode.com/info/tut2d-4.html
[101 maven tutorial]:https://github.com/marcliberatore/spaceinvaders-101-java
[maven natives]:https://code.google.com/p/mavennatives/
[maven-nativedependencies-example]:http://mavennatives.googlecode.com/svn/trunk/maven-nativedependencies-example/
[LWJGL 2.x Framework]:http://legacy.lwjgl.org/
[LWJGL Maven Integration]:http://wiki.lwjgl.org/index.php?title=LWJGL_use_in_Maven


## Build Instructions
You'll need Apache Maven 3.0+ and JDK 1.8+. Clone the repository locally and run afterwards in your Terminal:
```bash 
cd spaceinvaders-104-java 
mvn package
```
to create an archive file called `spaceinvaders-java-104-1.0.4.zip` in your `target` folder. 

## Running the Game
Execute the Game by first unpacking the created archive, then step into the extracted Directory and pick the proper start script File. 
For Example, on *nix-Plattforms, type 
```bash 
./run.sh
```
to start. The Script will load everything needed to run the Game (LWJGL Libs+Natives)


## License

All 
Java code placed in the public domain by Kevin Glass.

Sprites taken from [SpriteLib], 
licensed under the Common Public License 1.0.


[SpriteLib]:http://www.widgetworx.com/widgetworx/portfolio/spritelib.html
