package com.coda.test.model;

import com.coda.test.datatype.Email;
import com.coda.test.datatype.PhoneNumber;
import com.coda.test.datatype.RecordSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;


/**
 * * first_name:"Josephine",
 * last_name:Darakjy,
 * company_name:"Jeffrey A Chanay Esq",
 * address:"4 B Blue Ridge Blvd",
 * city:"Brighton",
 * county:"Livingston",
 * state:"MI",
 * zip:"48116",
 * phone1:"810-292-9388",
 * phone2:"810-374-9840",
 * email:"osephine_darakjy@darakjy.org",
 * web:"http://www.chanayjeffreyaesq.com"
 */

@Data
@JsonPropertyOrder({"first_name","last_name","company_name","address","city",
        "country","state","zip", "phone1","phone1", "email", "web" })
@JsonSerialize(using = RecordSerializer.class)
public class Record {
    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("company_name")
    private String companyName;

    @JsonProperty("address")
    private String address;

    @JsonProperty("city")
    private String city;

    @JsonProperty("country")
    private String county;

    @JsonProperty("state")
    private String state;

    @JsonProperty("zip")
    private String zip;

    @JsonProperty("phone1")
    private PhoneNumber phone1;

    @JsonProperty("phone2")
    private PhoneNumber phone2;

    @JsonProperty("email")
    private Email email;

    @JsonProperty("web")
    private String web;


}
