package com.coda.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
public class UserRecord {

    @JsonProperty("user_info")
    @JacksonXmlElementWrapper(useWrapping = false)
    List<UserRow> userInfo= new ArrayList<>();
}
