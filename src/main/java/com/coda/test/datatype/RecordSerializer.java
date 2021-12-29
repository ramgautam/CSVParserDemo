package com.coda.test.datatype;

import com.coda.test.model.Record;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.lang.reflect.Field;

public class RecordSerializer extends StdSerializer<Record> {

    public RecordSerializer() {
        this(null);
    }

    public RecordSerializer(Class<Record> t) {
        super(t);
    }

    @Override
    public void serialize(Record record, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {


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
                jgen.writeStartObject();
                jgen.writeStringField("field_name", formField.getName());
                jgen.writeStringField("value", fieldValue.toString());
                jgen.writeStringField("data_type",type.getSimpleName() );
                jgen.writeEndObject();

            }
        }

    }
}