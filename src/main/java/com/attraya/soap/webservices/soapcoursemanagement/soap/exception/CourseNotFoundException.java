package com.attraya.soap.webservices.soapcoursemanagement.soap.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM, customFaultCode = "{http://attraya.com/courses}001_COURSE_NOT_FOUND")
public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String s) {
        super(s);
    }
}
