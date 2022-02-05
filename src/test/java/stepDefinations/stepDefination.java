package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.Assert;

import resources.APIResources;
import resources.TestDataBuild;
import resources.utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class stepDefination extends utils {
	
	RequestSpecification reqspec;
	Response response;
	TestDataBuild payloads=new TestDataBuild();
	static String place_id;
	@Given("pass the AddPlace api payload {string} {string} {string}")
	public void pass_the_add_place_api_payload(String name, String language, String address) throws IOException
	{
		reqspec=given().spec(commonrequestspecification()).body(payloads.addplace_payload(name,language,address));
		
	}
	@When("user hit the {string} api server with {string} httprequest")
	public void user_hit_the_api_server_with_httprequest(String resourcename, String httprequest) {
		APIResources apiresource=APIResources.valueOf(resourcename);   //enum class objects
		if(httprequest.equalsIgnoreCase("POST"))
		  response=reqspec.when().post(apiresource.getResource());
		else if(httprequest.equalsIgnoreCase("GET"))
			response=reqspec.when().get(apiresource.getResource());
		else if(httprequest.equalsIgnoreCase("DELETE"))
			response=reqspec.when().delete(apiresource.getResource());
		else if(httprequest.equalsIgnoreCase("PUT"))
			response=reqspec.when().put(apiresource.getResource());
	}
	@Then("API call got success with expected response code {int}")
	public void api_call_got_success_with_expected_response_code(Integer int1) 
	{
		//response.then().spec(commonresponsespecification()).extract().response();
		assertEquals(response.getStatusCode(),200);
	}
	@Then("{string} in response body as {string}")
	public void in_response_body_as(String string, String string2) {
	    Assert.assertEquals(jsonpathparse(response,string),string2);
	}
	
	@Then("verify newly added {string} in AddPlaceAPI is able to get using {string} api server with {string} httprequest and verify {string} is matched")
	public void verify_newly_added_in_add_place_api_is_able_to_get_using_api_server_with_httprequest_and_verify_is_matched(String placeid, String Getplaceapi, String httprequest, String Expectedname) throws IOException {
		place_id=jsonpathparse(response,placeid);
		reqspec=given().spec(commonrequestspecification()).queryParam("place_id", place_id);
		user_hit_the_api_server_with_httprequest(Getplaceapi,httprequest);
		String actualname=jsonpathparse(response,"name");
		Assert.assertEquals(actualname,Expectedname);
	}
	
	@Given("pass the DeletePlace api payload")
	public void pass_the_delete_place_api_payload() throws IOException {
		reqspec=given().spec(commonrequestspecification()).body(payloads.Deleteplace_payload(place_id));
	}
	
}
