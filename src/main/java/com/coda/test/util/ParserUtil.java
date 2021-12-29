package com.coda.test.util;

import com.coda.test.model.Record;
import com.coda.test.model.UserInfo;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@UtilityClass
@Slf4j
public class ParserUtil {

    private static PatternMatching patternMatching;


    public static PatternMatching getPatternMatchingInstance() {
        if (patternMatching == null) {
            patternMatching = new PatternMatching();
        }
        return patternMatching;
    }

    /**
     * used to write text content into the given filename
     * @param fileName
     * @param content
     */
    public static void writeIntoFile(String fileName ,String content) {
        try {
            // default, create and write to it.
            Files.write(
                    Paths.get(fileName),
                    content.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            log.error("File Creation exception {}, {}",fileName, e.getMessage());
        }
    }

    /**
     *
     * Provide the JSONArray object based on given Record Object
     * @param record
     * @return
     */
    public static JSONArray customJsonString(Record record){
        JSONArray userRecord = new JSONArray();
        Field[] formFields = record.getClass().getDeclaredFields();
        for (Field formField : formFields) {
            Class type = formField.getType();
            formField.setAccessible(true);

            Object fieldValue = null;
            try {
                fieldValue = formField.get(record);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            JSONObject jsonRecord = new JSONObject();

            jsonRecord.put("field_name", formField.getName());
            jsonRecord.put("value", fieldValue.toString());
            jsonRecord.put("data_type", type.getSimpleName());

            userRecord.put(jsonRecord);
        }

        return userRecord;
    }

    /**
     * Provide the json String based on given list on parameter
     * @param recordList
     * @return
     */
    public static String customWholeJsonString(List<Record> recordList) {
        JSONArray userInfo = new JSONArray();
        for (Record record : recordList) {
            JSONArray userRecord = new JSONArray();
            Field[] formFields = record.getClass().getDeclaredFields();
            if (formFields != null) {
                for (Field formField : formFields) {
                    Class type = formField.getType();
                    formField.setAccessible(true);

                    Object fieldValue = null;
                    try {
                        fieldValue = formField.get(record);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    JSONObject jsonRecord = new JSONObject();
                    jsonRecord.put("field_name", formField.getName());
                    jsonRecord.put("value", fieldValue.toString());
                    jsonRecord.put("data_type", type.getSimpleName());
                 userRecord.put(jsonRecord);
                }
                userInfo.put(userRecord);
            }

        }
        return userInfo.toString();
    }
}