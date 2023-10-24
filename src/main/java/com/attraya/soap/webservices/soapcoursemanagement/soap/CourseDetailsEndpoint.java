package com.attraya.soap.webservices.soapcoursemanagement.soap;

import com.attraya.soap.webservices.soapcoursemanagement.soap.bean.Course;
import com.attraya.soap.webservices.soapcoursemanagement.soap.service.CourseDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.attraya.courses.*;

@Endpoint
public class CourseDetailsEndpoint {

	@Autowired
	CourseDetailsService service;

	// method
	// input - GetCourseDetailsRequest
	// output - GetCourseDetailsResponse

	// namespace - http://attraya.com/courses
	// name - GetCourseDetailsRequest

	@PayloadRoot(namespace = "http://attraya.com/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload // to convert the response to XML
	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
		Course course = service.findById(request.getId());
		return mapCourse(course);
	}
	private GetCourseDetailsResponse mapCourse(Course course){
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDescription());
		response.setCourseDetails(courseDetails);
		return response;
	}
}
