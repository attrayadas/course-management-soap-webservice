package com.attraya.soap.webservices.soapcoursemanagement.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.attraya.courses.*;

@Endpoint
public class CourseDetailsEndpoint {

	// method
	// input - GetCourseDetailsRequest
	// output - GetCourseDetailsResponse

	// http://attraya.com/courses
	// GetCourseDetailsRequest

	@PayloadRoot(namespace = "http://attraya.com/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload // to convert the response to XML
	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(request.getId());
		courseDetails.setName("Microservices Course");
		courseDetails.setDescription("That would be a wonderful course!");

		response.setCourseDetails(courseDetails);

		return response;
	}
}
