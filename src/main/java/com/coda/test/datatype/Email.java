package com.coda.test.datatype;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Email {
   private String email;

   @Override
   public String toString(){
      return this.email;
   }
}
