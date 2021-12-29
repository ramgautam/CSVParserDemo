package com.coda.test.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class UserInfo {

    @JsonProperty("user_info")
    private List<Record> userData  = new ArrayList<Record>();

}


