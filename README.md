# Basic File Operations

In this Project, we have two java files : CopyFile.java and MoveFile.java.
As name suggests CopyFile.java copies file from source to destination and MoveFile moves file from source to destination.
Pre-requsite : JDK(Java Compiler/Interpreter)

CopyFile.java

Input :
Takes two commmand line arguements, first one for Source File/Folder and Other for Destination Folder.

Command :
To Compile -
             javac CopyFile.java
To Run - 
             java CopyFile source destination 


MoveFile.java

Input :
Takes two commmand line arguements, first one for Source File/Folder and Other for Destination Folder.

Command :
To Compile -
             javac MoveFile.java
To Run - 
             java MoveFile source destination 

Note :
1. If you provide only one arguement, then the destination will be initialized to current directory.
2. If destination already have same name as file/folder trying to copy then it changes the name of copied file.
3. If destination already have same name as file/folder trying to move then it does not overwrite the previous name instead copy the file with changed name.
4. It handles most of the errors which can occur during Copy/Move Operation but still some cases may be missed. So, do it carefully.   