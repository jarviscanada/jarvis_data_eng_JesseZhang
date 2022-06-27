# Introduction
This is a simple Java app that mimics Linux grep command. It allows users to search matching strings recursively from files in a given directory, and output matched lines to a specified file.
Users give three input arguments: regex, rootPath and outFilePath, then obtain an output file.

Technologies used: Java 8, slf4j, JUnit 4, lambda expression, docker, IntelliJ.
# Quick Start
```
#1. Use Jar file to run the APP
java -jar grep.jar {regex} {rootPath} {outFile}
#2. Use the docker image to run the APP 
docker run --rm -v `pwd`/data:/data -v `pwd`/log:/log natsumeqi/grep {regex} {rootPath} {outFilePath} 
# -regex: the desired search pattern
# -rootPath: the root directory path in which you want to do the search
# -outFilePath: the output file path 
``` 
#Implemenation
The APP contains a JavaGrep interface and a corresponding implementation class.

## Pseudocode
```
# The pseudocode for the process method
# In the main method, the process method will be called
matchedLines = []
for file in listFilesRecursively(rootDir)
  for line in readLines(file)
      if containsPattern(line)
        matchedLines.add(line)
writeToFile(matchedLines)
```

## Performance Issue

The APP works fine if the file that is used for searching is not too big. However, when dealing with a large file,
the APP may encounter a memory leak issue depending on the size of the heap of the Java Virtual Machine. 
Hence, an optimized solution which uses stream APIs empowers the APP to process huge data because stream APIs will not be executed until a terminal operation happens.

# Test
JUnit 4 is used to do unit tests for methods in the APP. 
Also, a sample text file [shakespeare.txt](https://gist.githubusercontent.com/jarviscanada/c5408fc8f8ad0aa9606f76c8d4fde269/raw/c04d4f5d5adea39fff35ba3b9ec889d667292a0e/shakespeare.txt) is used to test the APP.
```
java -cp target/grep-1.0-SNAPSHOT.jar ca.jrvs.apps.grep.JavaGrepImp .*Romeo.*Juliet.* ./data ./out/grep.txt
```
The result is the same as using the command `egrep`.
# Deployment
A Dockerfile is configured to simplify the usage of the APP. The docker image for the APP is available on Docker Hub under the name : natsumeqi/grep 

# Improvement
Things that can be improved in this project.
1. Similar to `egrep` command, output directly to the console if the output file is not provided;
2. Improve test coverage;
3. Add more tests using JUnit.