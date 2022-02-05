package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class utils {
 
	public static RequestSpecification req;
	ResponseSpecification res;
	PrintStream stream;
	public RequestSpecification commonrequestspecification() throws IOException
	{
		if(req==null)
		{
		try {
			stream=new PrintStream(new FileOutputStream(System.getProperty("user.dir")+"//LogFiles//logging.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req=new RequestSpecBuilder().setBaseUri(getGlobalValue("baseurl")).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(stream))
				.addFilter(ResponseLoggingFilter.logResponseTo(stream))
				.setContentType(ContentType.JSON).build();
		}
		return req;
	}
	public ResponseSpecification commonresponsespecification()
	{
		res=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	    return res;
	}
	public String getGlobalValue(String key) throws IOException
	{
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\globals.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	public String jsonpathparse(Response response,String key)
	{
		String res=response.asString();
		JsonPath js=new JsonPath(res);
		return js.get(key).toString();
	}
}
