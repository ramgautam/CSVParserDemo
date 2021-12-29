package com.coda.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonRootName("user_infos")
public class UserRow {
    @JsonProperty("user_info")
    @JacksonXmlElementWrapper(useWrapping = false)
    List<UserColumn> userColumns = new ArrayList<>();
}
