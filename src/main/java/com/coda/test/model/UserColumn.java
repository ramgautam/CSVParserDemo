package com.coda.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonRootName("record")
public class UserColumn {

    @JsonProperty("field_name")
    private String fieldName;

    @JsonProperty("value")
    private String fieldValue;

    @JsonProperty("data_type")
    private String dataType;
}
