Tamagotchi
==========

Ant Build
---------

To build this with the ant build script:

1. Make sure you have generated a local.properties file first. This can be done by executing the following from the project's root directory.

    `android update project --path .`


2. Make sure you have also done the same with each (if any) library project.

    `lib\[library]> android update lib-project --path .`

3. Build! When you build from the project's root, the ant built script will also take care of building library projects (if any) automatically.



JUnit tests
-----------

The project also sets up an Android JUnit test project under tests/ directory.
To set it up in eclipse:

1. `File > Import... > General > Existing Project into Workspace`
2. Import tests/ as a project. Give it a meaningful name (e.g. TamagotchiTest).
3. To run the tests therein, right click on the test project, then select `Run As > Android JUnit Test`

The ant equivalent is left to the reader as an exercise. ;-)



IRC
---

* Channel ##RAD_Tamagotchi (double ## is not a typo)
* Network on irc.freenode.net



Useful links
------------

* [RedditAndroidDevelopers page](http://www.redditandroiddevelopers.com)
* [Redmine page](http://rad.lc8n.com/projects/tamagotchi)
* [Code style for eclipse](http://rad.lc8n.com/boards/5/topics/36)
* [Requirements specifications](https://docs.google.com/document/d/1sLDQkcoDs5xYPKr-luaSfaY2cG0M-Nm8a_jZ80wK-d0/edit)
* [Issue tracker](http://rad.lc8n.com/projects/tamagotchi/issues)