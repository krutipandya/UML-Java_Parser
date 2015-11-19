# UML-Java_Parser
This project parses Java source code into UML class diagram with the help of yUML API

Tools used for UML Parser

1. Eclipse IDE Mars: Used the Eclipse IDE to write the java code for the UML Parser project 
2. JavaParser: For parsing, used the javaparser(https://github.com/javaparser/javaparser (Links to an external site.)
Used the library: javaparser-core-2.1.0.jar to parse the java source files.
3. yUML: For generating the UML class diagram, used the yUML generator that takes the url generated from the javaparser and creates the corresponding class diagram with class and interface definitions, variable and method declarations and relations(extends, implements, uses, cardinality) between them. 
(http://yuml.me/diagram/scruffy/class/draw (Links to an external site.)

Instructions to run the umlparser code

1. Download the umlparser to your desired directory.

2. Open command prompt and go to the directory where you have downloaded the folder.

3. When you are in the root directory, give the following command:

java –jar umlparser.jar <testcase folder Name> <ImageName>.png
Eg. java –jar umlparser.jar testcase1 TestCaseResult1.png (as shown in the image below)



![How to run umlparser](https://github.com/krutipandya/UML-Java_Parser/blob/master/umlparser/howtorun.png "How to run umlparser")

Output Image File : 



![Output Image](https://github.com/krutipandya/UML-Java_Parser/blob/master/umlparser/img1.png "output image file for testcase1")

