Hello.

This is a quick reference to my BookingTask project.

This is a RESTful web-service which implements meeting booking functionality.

It represents a Schedule resource (/schedule) which has two subresources: 
	meetings (/meetings)
	working hours (/workinghours)

First of all you need to initialize office’s working hours with two parameters:
	opening time (openingTime)
	closing time (closing Time)

To do that make POST request to (/schedule/workinghours) URI.

After that you can start adding meetings with specified parameters:
	meeting date (meetingDate)	
	start time (startTime)
	duration (duration)
	employee id (employeeId)

To do that make POST request to (/schedule/meetings) uri.

To get complete schedule make GET request to (/schedule) uri.
	