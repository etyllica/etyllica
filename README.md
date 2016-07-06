# Etyllica (v 5.0) [![Circle CI](https://circleci.com/gh/yuripourre/etyllica.svg?style=svg)](https://circleci.com/gh/yuripourre/etyllica)

[Etyllica](http://yuripourre.github.com/etyllica) is a pure Java Game Engine to make games really fast.

## Features

- Keyboard and Mouse handling (and Multiple Joysticks Handling [for Linux])
- Image Loader that supports (TGA, PCX, PNG, BMP, JPG, GIF [yes, animated])
- True Type Fonts loader (.ttf)
- Animation Tween System
- Animation Pivot System
- FullScreen Support (even in unsigned applets)
- GUI System
- Theme Manager
- Multilanguage Support (GUI changes on the fly)
- Sound Loader that supports (MP3, OGG, WAV)
- Audio Capture
- WaveForm Drawing
- OpenGL simulation with software rendering (even in unsigned applets with [abby-jgl](https://github.com/yuripourre/abby-jgl)
- Basic Particle System
- Basic Light Map Logic
- 2D Physics (with [etyllica-physics](https://github.com/yuripourre/etyllica-physics))

## Related Projects
### Games
- [Akigawa Training](https://github.com/yuripourre/akigawa) - A Ninja Game
- [Etyllic Pong](http://yuripourre.github.com/etyllic-pong) - Pong Clone
- [Etyllic Mario](http://yuripourre.github.com/etyllic-mario) - Mario Clone
- [Hard to Beat Me](http://yuripourre.github.com/hardtobeatme) - A Game developed at [GGJ 2013](http://globalgamejam.org/)
- [House Defense](https://github.com/yuripourre/childage) - A Game developed at [GGJ 2014](http://globalgamejam.org/)
- [Marvel Quest](https://github.com/yuripourre/marvel-quest) - A beat 'em up game
- [Ninja Robot](https://github.com/yuripourre/ninja-robot) - A casual game
- [Tomb Runaway](https://github.com/yuripourre/runaway) - A maze game
- [Helicoptr](https://github.com/yuripourre/helicoptr) - A flappy bird clone

### Non-Games
- [Etyllic Paint](http://yuripourre.github.com/etyllic-paint) - Paint Clone
- [Etyllic Animator](http://yuripourre.github.com/etyllic-animator) - Pivot Animator (2D and 3D)
- [Etyllica Mobile](https://github.com/yuripourre/etyllica-mobile) - Etyllica port to Android (deprecated).
- [Etyllica Physics](https://github.com/yuripourre/etyllica-physics) - Etyllica extension to handle 2D physics.
- [EtyllicTTS](https://github.com/yuripourre/etyllic-tts) - A FreeTTS fork
- [Etyllic GameBoy](https://github.com/yuripourre/etyllic-gameboy) - A [Javaboy](http://www.millstone.demon.co.uk/download/javaboy/) fork with faster rendering.
- [E-Motion](https://github.com/yuripourre/e-motion) - An Image Processing Library made in pure Java.
- [Veete](https://github.com/yuripourre/veete) - A VEry Easy Tilemap Editor
- [Abby](https://github.com/yuripourre/abby) - A Etyllica 3D fork.
- [Abby-jGL](https://github.com/yuripourre/abby-jgl) - An Abby fork using [jGL](http://www.cmlab.csie.ntu.edu.tw/~robin/jGL/) for CPU rendering.
- [Luvia](https://github.com/yuripourre/luvia) - An Abby fork using [JoGL](http://jogamp.org/jogl/www/) and [glg2d](https://github.com/brandonborkholder/glg2d) for GPU rendering.
 rendering.
- [IP-Fun](https://github.com/yuripourre/ip-fun) - An Application to help children to remember IP Addresses.

### Other Non-Games
- [Farthest in Future Algorithm](https://github.com/UNIRIO-SI/farthest-in-future-algorithm) - A Farthest in Future Algorithm implementation with animations.
- [Rubik Solver](https://github.com/UNIRIO-SI/rubik-solver) - A Simple Rubik Cube Application.

## Dependencies (optional)

If you don't need Sound nor Physics, you just have to include the [etyllica-5.0.jar](https://github.com/yuripourre/etyllica/releases/download/v5.0/etyllica-5.0.jar) (stable version with 326kB)

- [SoundSystem](http://www.paulscode.com/forum/index.php?topic=4.0) (Audio Library made by [Paul Lamb](http://www.paulscode.com))
- [WAV, JOgg, JOrbis and MP3 codec plug-in](http://www.paulscode.com/forum/index.php?topic=496.0) (Audio Codecs made by [Paul Lamb](http://www.paulscode.com))


## How to Start
You can follow the [HelloWorld](https://github.com/yuripourre/etyllica/wiki/Hello-World) example.

## Maven Integration
```
<dependency>
  <groupId>br.com.etyllica</groupId>
  <artifactId>etyllica</artifactId>
  <version>5.0</version>
</dependency>
```

##License
- [LGPL](http://www.gnu.org/copyleft/lesser.html)
