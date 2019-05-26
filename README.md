# SnowX 
Project to detect HPTorpedo and HPShip hidden in the space.    
## About the application 
This application is a command line application which is controlled with passed arguments, three of them are mandatory. _To learn about the arguments, just run the `SnowX-v1.0.jar` file_.  
  
 - **Note that**: arguments could be passed in any order.   
    
The output of the application for the number of detected _Ships_ and _Torpedos_ varies, based on **threshold** passed as an argument. And if not passed the default confidence level value is 0.6 which is 60%.
  
- The confidence level is calculated as the total number of "+" sign in the shape pattern *divided by* the total matched numbers of "+" *at the same position in the space* window, then finally  check the final value is **>** threshold to return if the shape is found or not in the space.  
    
The application will run, and prints the passed arguments, and the detected _Rejectos_ attackers, and for each attacker, it will provide information about its **type**, its space _coordinates_ as **X** and **Y**, and the original pattern size. And finally, the number if detected _Rejectos_.  
  
- **FYI**: If the space image dimension is less than the Rejectos attacker's dimension the application will not detect anything. because you can see it with your eyes at that moment. :)  
  
In order to run the application properly, you need the following software to be installed on your machine. This application is using lates **Java SE 12 features**, therefore maven `pom.xml` edited accordingly to allow new language features in **preview** state.

- For more about what is new in Java SE 11 and 12 check out my latest article for IBM Developer site [Explore new Java SE 11 and 12 APIs and language features]([https://developer.ibm.com/tutorials/java-theory-and-practice-1/](https://developer.ibm.com/tutorials/java-theory-and-practice-1/)), the whole series **"Java theory and practice"** is [here]([https://developer.ibm.com/series/java-theory-and-practice/](https://developer.ibm.com/series/java-theory-and-practice/)).
  
### Software requirements    
* **Maven 3.6.3** and could be downloaded from [here](http://maven.apache.org/download.cgi).    
 * **Java SE 12** JDK, and could be downloaded from [here](https://www.oracle.com/technetwork/java/javase/downloads/jdk12-downloads-5295953.html).  
  
After download all the required software, please make sure that _maven_ and _JDK_ `bin` folders are accessible from anywhere on your system.    
     
### Up and running SnowXScanner To run this application you need to follow the following steps:    
1. Clone the project using the following command:    
   ```bash    
   mohamed_taman:Projects$ git clone https://github.com/mohamed-taman/SnowX.git     
   ```    
2. From within SnowX run the following maven command to clean the project:  
   ```bash  
   mohamed_taman:SnowX$ mvn clean verify  
   ```  
   - This command also packages the application, and you will notice that all test cases run   
    successfully.  
		```bash  
		.............  
		[INFO] -------------------------------------------------------  
		[INFO]  T E S T S  
		[INFO] -------------------------------------------------------  
		[INFO] Running rs.tm.siriusx.snowx.space.RejectosDetectorTest  
		[INFO] Tests run: 10, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.15 s - in rs.tm.siriusx.snowx.space.RejectosDetectorTest  
		[INFO]  
		[INFO] Results:  
		[INFO]  
		[INFO] Tests run: 10, Failures: 0, Errors: 0, Skipped: 0  
		......  
		[INFO] -----------------------------------------------------------------------  
		[INFO] BUILD SUCCESS  
		[INFO] -----------------------------------------------------------------------  
		[INFO] Total time:  2.420 s  
		[INFO] Finished at: 2019-05-25T20:58:02+02:00  
		[INFO] -----------------------------------------------------------------------  
		```  
   The output of this process is 2 files as the following in the `target` folder:  
	   1. `SnowX-v1.0.jar`: as **uber jar**, this jar contains all of its dependencies to run, and its size is bigger. 
	   2. `original-SnowX-v1.0.jar`: as **thin jar**, this jar could be used as library inside any application. 
	   
4. Now it's time to run the application, run it as the following:  
	```bash  
	mohamed_taman:SnowX$ java -jar --enable-preview target/SnowX-v1.0.jar -s "resources/TestData.snw" -t 0.80 -sf "resources/HPship.snw" -tf "resources/HPTorpedo.snw"  
	Detected attackers:  
	--------------------  
	Rejectos Attacker { Type is: TORPEDO, found at Space coordinates [ X= 0, Y= 0], Pattern [Height = 13, width = 11]}  
	Rejectos Attacker { Type is: TORPEDO, found at Space coordinates [ X= 14, Y= 29], Pattern [Height = 13, width = 11]}  
	Rejectos Attacker { Type is: TORPEDO, found at Space coordinates [ X= 78, Y= 25], Pattern [Height = 13, width = 11]}  
	-----------------------------------------  
	Number of Rejectos attackers found = 3  
	``` 
   In this case our application returns only 3 TORPEDO with confidence level of 80%. if it is 60% the application will return 8 values and this time will be 5 Ships more.  
   - Try to give the application a swirl for different values of confidence level.