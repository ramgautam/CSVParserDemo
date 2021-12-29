package com.coda.test.csvprocessor;

import com.coda.test.datatype.DataType;
import com.coda.test.model.OutputType;
import com.coda.test.model.UserColumn;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

public class CSVProcessorTest {

    private final static String TEST_DATA_IN_RESOURCE_DIRECTORY = "src/test/resources/data/test_data.csv";
    private final static String TEST_DATA_OUTPUT_DIRECTORY = "src/test/resources/output";


    @AfterEach
    public void cleanTestDataOutputFolder() throws IOException {
        Files.walk(Paths.get(TEST_DATA_OUTPUT_DIRECTORY))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .forEach(File::delete);
    }

    @Test
    public void testParseCSVToXAMLWithDefaultConfigurationWithNumberOfFileGenereatedBasedOnRowsInCSVFIle
            () throws IOException {

        File fileName = new File(TEST_DATA_IN_RESOURCE_DIRECTORY);
        File outputDirectory = new File(TEST_DATA_OUTPUT_DIRECTORY);
        CSVParserConfiguration csvParserConfiguration = new CSVParserConfiguration();
        csvParserConfiguration.setOutputType(OutputType.XML);
        csvParserConfiguration.setStaticHeaderPosition(true);
        csvParserConfiguration.setWriteOutputOnFileSequentially(true);
        csvParserConfiguration.setOutputLocation(outputDirectory.getAbsolutePath());

        CSVProcessor.parseCSV(fileName.getAbsolutePath(), csvParserConfiguration);
        long actual = Files.list(Paths.get(outputDirectory.getAbsolutePath()))
                .filter(p -> p.toFile().isFile())
                .count();
        Assertions.assertEquals(3, actual);
    }
    @Test
    public void testParseCSVWithDefaultConfigurationWithNumberOfFileGenereatedBasedOnRowsInCSVFIle() throws IOException {

        File fileName = new File(TEST_DATA_IN_RESOURCE_DIRECTORY);
        File outputDirectory = new File(TEST_DATA_OUTPUT_DIRECTORY);
        CSVParserConfiguration csvParserConfiguration = new CSVParserConfiguration();
        csvParserConfiguration.setOutputType(OutputType.JSON);
        csvParserConfiguration.setStaticHeaderPosition(true);
        csvParserConfiguration.setWriteOutputOnFileSequentially(true);
        csvParserConfiguration.setOutputLocation(outputDirectory.getAbsolutePath());

        CSVProcessor.parseCSV(fileName.getAbsolutePath(), csvParserConfiguration);
        long actual = Files.list(Paths.get(outputDirectory.getAbsolutePath()))
                .filter(p -> p.toFile().isFile())
                .count();
        Assertions.assertEquals(3, actual);
    }

    @Test
    public void testGetUserColumnForFirstName() throws Exception {
        UserColumn expectUserColumn = UserColumn.builder().
                fieldName("first_name").
                fieldValue("Micheal").
                dataType(DataType.STRING.getName()).build();

        UserColumn actualUserColumn = CSVProcessor.getUserColumn("first_name", "Micheal");
        Assertions.assertEquals(expectUserColumn.getDataType(), (actualUserColumn.getDataType()));

    }

    @Test
    public void testGetUserColumnForPhoneNumber() throws Exception {

        UserColumn expectUserColumn = UserColumn.builder().
                fieldName("phone_number1").
                fieldValue("999-989-1234").
                dataType(DataType.PHONE_NUMBER.getName()).build();

        UserColumn actualUserColumn = CSVProcessor.getUserColumn("phone_number1", "999-989-1234");
        Assertions.assertEquals(expectUserColumn.getDataType(), (actualUserColumn.getDataType()));
    }



    @Test
    public void testParseCSV1() {
    }

    @Test
    public void testParseCSV2() {
    }


}