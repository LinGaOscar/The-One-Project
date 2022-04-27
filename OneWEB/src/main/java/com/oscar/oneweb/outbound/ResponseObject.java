package com.oscar.oneweb.outbound;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseObject implements Serializable {
protected String statusCode;
protected String statusDesc;
protected Object resultData;

}
