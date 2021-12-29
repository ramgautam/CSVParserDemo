# CSVParserDemo
This is the sample CSVParser demo application which parses given csv file and capable to export as each file of each row present in csve in the form of JSON or XML.

This project has two form of CSV file parser. They are
1. Static header with fix column
2. Dynamic column in csv file


**Prject to import**
1. Import the java project as gradle project
2. choose the build.gradle file to import project in development IDE
3. Used test csv under test/resources/data/test_data.csv during development and unit testing purpose
4. Test methods create test output under test/resources/data/output/*


**CSVParserTestDemo** is the main class which has main method and csv parser starts with filename as input parameter through Scanner class. **CSVProcessor** is another java class which has diffent csv parsing **funcution overloaded method**. parser method has fours paramaters. They are:

  1. Absolute path of csv file which actually parsed this method
  2. Output file name pattern which used to generated the files of parsed csv in the form of either json or xml of each row present in csv file
  3. Third pramamter is the **CSVParserConfiguration** coniguration class uses configu paramter for csv parsring and writing logic in the file
  

**CSVParserConfiguration** is the configuratoin class which contains following information
