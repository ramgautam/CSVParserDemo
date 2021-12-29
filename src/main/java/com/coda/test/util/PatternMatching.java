package com.coda.test.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatching {

   final Pattern emailPattern=  Pattern.compile(".+@.+\\.[a-z]+");
   final Pattern phoneNumberPattern= Pattern.compile("^[0-9]{3}-[0-9]{3}-[0-9]{4}$");

   public boolean matchEmail(String email){
       Matcher m = this.emailPattern.matcher(email);
       try{
           return m.matches();
       }catch(Exception e){
           return false;
       }
    }
    public boolean matchPhoneNumber(String phoneNumber){
        Matcher m = this.phoneNumberPattern.matcher(phoneNumber);
        try{
            return m.matches();
        }catch(Exception e){
            return false;
        }


    }

}
