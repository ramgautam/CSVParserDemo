# CSVParserDemo
This is the sample CSVParser demo application which parses given csv file and capable to export as each file of each row present in csve in the form of JSON or XML.

This project has two form of CSV file parser. They are
1. Static header with fix column
2. Dynamic column in csv file


**Project to import**
1. Import the java project as gradle project
2. choose the build.gradle file to import project in development IDE
3. Used test csv under test/resources/data/test_data.csv during development and unit testing purpose
4. Test methods create test output under test/resources/data/output/*


**CSVParserTestDemo** is the main class which has main method and csv parser starts with filename as input parameter through Scanner class. 
**CSVProcessor** is another java class which has diffent csv parsing **funcution overloaded method**. parser method has fours paramaters. 
They are:
  1. Absolute path of csv file which actually parsed this method
  2. Output file name pattern which used to generated the files of parsed csv in the form of either json or xml of each row present in csv file
  3. Third pramamter is the **CSVParserConfiguration** coniguration class uses configu paramter for csv parsring and writing logic in the file
  

**CSVParserConfiguration** is the configuratoin class which contains following information
1. OutputType- It represents the whether exported output in the form of JSON or XML- **default** value is JSON
2. StaticHeaderPosition- It represents the there is pre defined coloumns at header row- **default** value is true
3. writeOutputOnFileSequentially - It represents the where writing parsed content either sequentially or parallelly into the file - **default** value is true
4. outputLocation- It represents the output location where actual output will be created. - **default** is '.'(current context)

**Sample content of csv file with header:**
first_name, last_name,company_name,address,city,country,state,zip,phone1,phone2,email,web
"Josephine","Darakjy","Jeffrey A Chanay Esq","4 B Blue Ridge Blvd","Brighton","Livingston","MI","48116","810-292-9388","810-374-9840","osephine_darakjy@darakjy.org","http://www.chanayjeffreyaesq.com"

**Sample generated XML file with data type of each column :**
<?xml version="1.0" encoding="UTF-8"?>
<user_infos>
<user_info>
<data_type>String</data_type>
<value>Josephine</value>
<field_name>firstName</field_name>
</user_info>
<user_info>
<data_type>String</data_type>
<value>Darakjy</value>
<field_name>lastName</field_name>
</user_info>
<user_info>
<data_type>String</data_type>
<value>Jeffrey A Chanay Esq</value>
<field_name>companyName</field_name>
</user_info>
<user_info>
<data_type>String</data_type>
<value>4 B Blue Ridge Blvd</value>
<field_name>address</field_name>
</user_info>
<user_info>
<data_type>String</data_type>
<value>Brighton</value>
<field_name>city</field_name>
</user_info>
<user_info>
<data_type>String</data_type>
<value>Livingston</value>
<field_name>county</field_name>
</user_info>
<user_info>
<data_type>String</data_type>
<value>MI</value>
<field_name>state</field_name>
</user_info>
<user_info>
<data_type>String</data_type>
<value>48116</value>
<field_name>zip</field_name>
</user_info>
<user_info>
<data_type>PhoneNumber</data_type>
<value>810-292-9388</value>
<field_name>phone1</field_name>
</user_info>
<user_info>
<data_type>PhoneNumber</data_type>
<value>810-374-9840</value>
<field_name>phone2</field_name>
</user_info>
<user_info>
<data_type>Email</data_type>
<value>osephine_darakjy@darakjy.org</value>
<field_name>email</field_name>
</user_info>
<user_info>
<data_type>String</data_type>
<value>http://www.chanayjeffreyaesq.com</value>
<field_name>web</field_name>
</user_info>
</user_infos>

