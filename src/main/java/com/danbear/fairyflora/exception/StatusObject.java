package com.danbear.fairyflora.exception;

import lombok.Data;

import java.util.Date;

@Data
public class StatusObject {
  private Integer statusCode;
  private String message;
  private Date timestamp;
}
