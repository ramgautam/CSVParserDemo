package com.coda.test.datatype;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class PhoneNumber {
    private String phoneNumber;

    @Override
    public String toString(){
        return this.phoneNumber;
    }


}

