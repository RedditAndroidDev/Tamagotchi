# Ant Build
To build this with the ant build script:

1. Make sure you have generated a local.properties file first. This can be done by executing the following from the project's root directory.

    android update project --path .


2. Make sure you have also done the same with each (if any) library project.

    lib\[library]> android update lib-project --path .

3. Build! When you build from the project's root, the ant built script will also take care of building library projects (if any) automatically.

# Usefull links
Irc channel - ##RAD_Tamagotchi on irc.freenode.net
[RedditAndroidDevelopers page](http://www.redditandroiddevelopers.com)
[Redmine page](http://rad.lc8n.com/projects/tamagotchi)
[Code style for eclipse](http://rad.lc8n.com/boards/5/topics/36)
[Requirements specifications](https://docs.google.com/document/d/1sLDQkcoDs5xYPKr-luaSfaY2cG0M-Nm8a_jZ80wK-d0/edit)
[Issue tracker](http://rad.lc8n.com/projects/tamagotchi/issues)