package com.coda.test.csvprocessor;

import com.coda.test.datatype.DataType;
import com.coda.test.datatype.Email;
import com.coda.test.datatype.PhoneNumber;
import com.coda.test.model.OutputType;
import com.coda.test.model.Record;
import com.coda.test.model.UserColumn;
import com.coda.test.model.UserRow;
import com.coda.test.util.OutputResultRunnableTask;
import com.coda.test.util.ParserUtil;
import com.coda.test.util.PatternMatching;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.XML;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CSVProcessor {

    private final static char CSV_RECORD_SEPARATOR = ',';
    private final static short EXECUTOR_FIXED_THREAD_POOL_COUNT=15;
    private final static PatternMatching patternMatching = ParserUtil.getPatternMatchingInstance();
    private final static ExecutorService executorService = Executors.newFixedThreadPool(EXECUTOR_FIXED_THREAD_POOL_COUNT);
    private final static String DEFAULT_OUTPUT_FILENAME = "row_";

    public static void parseCSV(final String fileName) {
        parseCSV(fileName, DEFAULT_OUTPUT_FILENAME, CSVParserConfiguration.DEFAULT_CSV_PARSER_CONFIGURATION);
    }

    public static void parseCSV(final String fileName, final String outputFileNamePattern) {
        parseCSV(fileName, outputFileNamePattern, CSVParserConfiguration.DEFAULT_CSV_PARSER_CONFIGURATION);
    }

    public static void parseCSV(final String fileName, final CSVParserConfiguration csvParserConfiguration) {
        parseCSV(fileName, DEFAULT_OUTPUT_FILENAME, csvParserConfiguration);
    }

    public static void parseCSV(final String fileName, final String outputFileNamePattern, final CSVParserConfiguration config) {
        if (config.isStaticHeaderPosition()) {
            com.opencsv.CSVParser csvParser = new CSVParserBuilder().
                    withSeparator(CSV_RECORD_SEPARATOR).build(); // custom separator
            try (CSVReader reader = new CSVReaderBuilder(
                    new FileReader(fileName))
                    .withCSVParser(csvParser)   // custom CSV parser
                    .withSkipLines(1)           // skip the first line, header info
                    .build()) {
                String[] lineInArray;
                List<Record> userInfos = new ArrayList<>();
                int row = 0;
                while ((lineInArray = reader.readNext()) != null) {
                    if (lineInArray.length <= 12) {
                        Record record = new Record();
                        record.setFirstName(lineInArray[0]);
                        record.setLastName(lineInArray[1]);
                        record.setCompanyName(lineInArray[2]);
                        record.setAddress(lineInArray[3]);
                        record.setCity(lineInArray[4]);
                        record.setCounty(lineInArray[5]);
                        record.setState(lineInArray[6]);
                        record.setZip(lineInArray[7]);
                        record.setPhone1(PhoneNumber.builder().phoneNumber(lineInArray[8]).build());
                        record.setPhone2(PhoneNumber.builder().phoneNumber(lineInArray[9]).build());
                        record.setEmail(Email.builder().email(lineInArray[10]).build());
                        record.setWeb(lineInArray[11]);
                        userInfos.add(record);
                        JSONArray userInfoJsonArray = ParserUtil.customJsonString(record);
                        row++;
                        String outputContent = "";
                        String outputFileExtension = "";
                        if (config.getOutputType() == OutputType.XML) {
                            outputContent = json2xml(userInfoJsonArray);
                            outputFileExtension = ".xml";
                        } else {
                            outputContent = userInfoJsonArray.toString(2);
                            outputFileExtension = ".json";
                        }
                        final String actualOutputFileName = config.getOutputLocation()+ System.getProperty("file.separator")
                                +outputFileNamePattern + row + outputFileExtension;
                        if (config.isWriteOutputOnFileSequentially()) {
                            ParserUtil.writeIntoFile(actualOutputFileName, outputContent);
                        } else {
                            executorService.submit(new OutputResultRunnableTask(actualOutputFileName, outputContent));
                        }
                    } else {
                        log.error("Column mismatch  at row {}", row);
                    }
                }
                executorService.shutdown();
            } catch (IOException | CsvValidationException e) {
                log.error("Error on CSV File {}", e.getMessage());
            }
        } else {
            com.opencsv.CSVParser csvParser = new CSVParserBuilder().withSeparator(CSV_RECORD_SEPARATOR).build(); // custom separator
            try (CSVReader reader = new CSVReaderBuilder(
                    new FileReader(fileName))
                    .withCSVParser(csvParser)   // custom CSV parser
                    // .withSkipLines(1)           // skip the first line, header info
                    .build()) {
                String[] headerArray = reader.readNext();
                String[] lineInArray;
                int row = 0;
                while ((lineInArray = reader.readNext()) != null) {
                    row++;
                    UserRow userRow = new UserRow();
                    List<UserColumn> userColumnList = new ArrayList<>();
                    for (int i = 0; i < headerArray.length; i++) {
                        UserColumn userColumn = getUserColumn(headerArray[i], lineInArray[i]);
                        userColumnList.add(userColumn);
                        userRow.setUserColumns(userColumnList);
                    }
                    String outputContent = "";
                    String outputFileExtension = "";
                    if (config.getOutputType() == OutputType.XML) {
                        ObjectMapper xmlMapper = new XmlMapper();
                        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
                        outputContent = xmlMapper.writeValueAsString(userRow);
                        outputFileExtension = ".xml";
                    } else {
                        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
                        DefaultPrettyPrinter printer = new DefaultPrettyPrinter().
                                withObjectIndenter(new DefaultIndenter("    ", "\n"));
                        outputContent = mapper.writer(printer).writeValueAsString(userRow);
                        outputFileExtension = ".json";
                    }
                    final String actualOutputFileName = outputFileNamePattern + row + outputFileExtension;
                    if (config.isWriteOutputOnFileSequentially()) {
                        ParserUtil.writeIntoFile(actualOutputFileName, outputContent);
                    } else {
                        executorService.submit(new OutputResultRunnableTask(actualOutputFileName, outputContent));
                    }
                }
                executorService.shutdown();

            } catch (IOException | CsvValidationException e) {
                log.error("Error on CSV File {}", e.getMessage());
            }
        }

    }

    public static UserColumn getUserColumn(String headerColumn, String data) {
        if (patternMatching.matchEmail(data)) {
            return UserColumn.builder().fieldName(headerColumn).
                    fieldValue(data).dataType(DataType.EMAIL.getName()).build();
        } else if (patternMatching.matchPhoneNumber(data)) {
            return UserColumn.builder().fieldName(headerColumn).
                    fieldValue(data).dataType(DataType.PHONE_NUMBER.getName()).build();
        } else {
            return UserColumn.builder().fieldName(headerColumn).
                    fieldValue(data).dataType(String.class.getSimpleName()).build();
        }
    }


    public static String json2xml(JSONArray jsonArray) {
        String xml = XML.toString(jsonArray, "user_info");
        xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<user_infos>" + xml + "</user_infos>";
        Transformer transformer = null;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException ex) {
            log.error("TransformerConfiguration error {}", ex.getMessage());
        }
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        //initialize StreamResult with File object to save to file
        StreamResult result = new StreamResult(new StringWriter());
        StreamSource source = new StreamSource(new StringReader(xml));
        try {
            transformer.transform(source, result);
        } catch (TransformerException ex) {
                log.error("XML transformation error {}",ex.getMessage());
        }
        return result.getWriter().toString();
    }
}
