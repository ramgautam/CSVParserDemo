package com.coda.test.csvprocessor;

import com.coda.test.model.OutputType;

import java.io.File;
import java.net.URL;
import java.util.Scanner;

public class CSVParserTestDemo {

    private final static String TEST_DATA_IN_RESOURCE_DIRECTORY="data/test_data.csv";
    private final static String TEST_DATA_OUTPUT_DIRECTORY="src/test/resources/output";
    public static void main(String args[]) {

        System.out.print("Enter the absolute path with filename  and  extension : ");
        Scanner input = new Scanner(System.in);

//        System.out.print("Enter the CSV Outpute Type - JSON or XML: ");
//        input = new Scanner(System.in);
        String fileName=input.next();

        File file = new File(fileName);
        if (file.exists()) {
           //CSVProcessor.parseCSV("/Users/ramgautam/project/CSVProcessor/src/test/resources/data/test_data.csv");

            CSVParserConfiguration csvParserConfiguration = new CSVParserConfiguration();
            csvParserConfiguration.setOutputType(OutputType.JSON);
            csvParserConfiguration.setStaticHeaderPosition(true);
            csvParserConfiguration.setWriteOutputOnFileSequentially(true);

            File outputDirectory = new File(TEST_DATA_OUTPUT_DIRECTORY);
            csvParserConfiguration.setOutputLocation(outputDirectory.getAbsolutePath());
            CSVProcessor.parseCSV(fileName, csvParserConfiguration);

        } else {
            System.out.println("Provided file does not exists on given location ");
        }


    }
}
