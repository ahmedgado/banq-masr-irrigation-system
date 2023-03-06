Automatic irrigation system

System will send message from each device each specific period (out of scope it’s related to Device ) message will contain
current level of water in plot and depends on plot pre-configured max water level for it and compare
it to current water level which is come from message based on pre-stored speed system could count seeding period needed 
For this plot and send it back to device.


System developed based on micro-services Springboot and Eureka for internal calls
consist of services are :

- Eureka Service

 - Alert service : Scheduled process to check all in active devices by check last update time for
    plot attributes and save alerts into DB could be message for shows or send via email in 
    live app.

 - Platform Service : Business logic which  handle devices’ messages and return commands to them send alert
	to alert service if check health check url of device before send command to it if fails for specific time
	and responsible for manage data of devices and plots

- Gateway Service : The intro service to our system from device prescriptive this service will receive message from device	
   then send it to platform

-Library (Maven) not service for Shared Dtos and Models

—--------------------------------------------------------------------------------------------------------------------------------------

Installation :-
	
Software needed : JDK 19 , maven

Steps .
Clone the whole project then run 

Run 
mvn clean install

Start (Dev Mode) for Code Review
Eureka project first of all then all other services 

—------------------------------------------------------------------------------------------------------------------------------------------

 public APIs for Test and Examples :

	-hint : Core service , Gateway service provided by swagger
		http://localhost:8080/swagger-ui/index.html
		http://localhost:8090/swagger-ui/index.html

Core Service

               will handle definition of plots and devices by urls as below

		- Create / update Device

http://localhost:8080/device/save (POST)

sample request for New
{
"id": "65c67be7-e1a8-4200-898f-d2c494c42f33",
"imei" : "1234202020",
"model" : "SAM-13",
"waterLevelPerMin" : 90
}

sample request for update
{
"id": "65c67be7-e1a8-4200-898f-d2c494c42f33",
"imei" : "1234202020",
"model" : "SAM-13",
"waterLevelPerMin" : 3 
}


----------------------------------------------------------------------------------

- return list of all device

  http://localhost:8080/device/save (GET)



------------------------------------------------------------------------------------------------------


- Create Plot

http://localhost:8080/plot/save (POST)

sample request for plot (Region must be stored into database I add it as init data when core
will handle definition of plots and devices by urls as below

{
"name" : "Plot 13",
"region" : "Alex",
"maxWaterLevel" : 60
}


------------------------------------------------------------------------------------------------------------

- list of plots

http://localhost:8080/plot/list (GET)

------------------------------------------------------------------------------------------------------------------------------------------

Assign Device to Plot :
sample request:

{
"deviceId": "65c67be7-e1a8-4200-898f-d2c494c42f33",
"plotId": "d6b61c7a-7e48-40b2-b802-fdd17289cac5"
}

—---------------------------------------------------

-Gateway Service :
Read Message from device :
device status:

IDE means ready to start work so must provide current water level

WORKING not important in this stage of project

ts:
current time stamp
device imei : must be saved before in system and assiged to plot ,


http://localhost:8090/post-reading(POST)

sample Request

{
"deviceImei" : "1234202020",
"waterLevel" : 10,
"status" : "IDLE",
"ts" : 1677773695
}


if you used all pre example data you must found result is
duration per min
(Don’t forget check ids..)

{
"command": "SEED",
"duration": 16
}

If Response is N/A duration Zero device won’t do anything

—----------------------------------------------------------

Alert Service :-

Scheduled service run each one min (Configurable from properties file) to check not
updated plot to get offline alert and save it into DB like screenshot below to use it in
mail service or something like that in real app.



