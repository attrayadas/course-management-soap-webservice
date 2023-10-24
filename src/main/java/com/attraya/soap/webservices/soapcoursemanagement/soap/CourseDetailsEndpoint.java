package com.attraya.soap.webservices.soapcoursemanagement.soap;

import com.attraya.soap.webservices.soapcoursemanagement.soap.bean.Course;
import com.attraya.soap.webservices.soapcoursemanagement.soap.service.CourseDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.attraya.courses.*;

import java.util.List;

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
		return mapCourseDetails(course);
	}

	@PayloadRoot(namespace = "http://attraya.com/courses", localPart = "GetAllCourseDetailsRequest")
	@ResponsePayload // to convert the response to XML
	public GetAllCourseDetailsResponse processAllCourseDetailsRequest(@RequestPayload GetAllCourseDetailsRequest request) {
		List<Course> courses = service.findAll();
		return mapAllCourseDetails(courses);
	}

	@PayloadRoot(namespace = "http://attraya.com/courses", localPart = "DeleteCourseDetailsRequest")
	@ResponsePayload // to convert the response to XML
	public DeleteCourseDetailsResponse deleteCourseDetailsRequest(@RequestPayload DeleteCourseDetailsRequest request) {
		int status = service.deleteById(request.getId());
		DeleteCourseDetailsResponse response=new DeleteCourseDetailsResponse();
		response.setStatus(status);
		return response;
	}
	private GetCourseDetailsResponse mapCourseDetails(Course course){
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		response.setCourseDetails(mapCourse(course));
		return response;
	}
	private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses){
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		for (Course course:courses) {
			CourseDetails mapCourse = mapCourse(course);
			response.getCourseDetails().add(mapCourse);
		}
		return response;
	}

	private CourseDetails mapCourse(Course course){
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDescription());
		return courseDetails;
	}
}
