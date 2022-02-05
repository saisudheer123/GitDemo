package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		stepDefination sd=new stepDefination();
		if(stepDefination.place_id==null)
		{
		 sd.pass_the_add_place_api_payload("sai", "english", "ongole");
		 sd.user_hit_the_api_server_with_httprequest("AddPlaceAPI", "POST");
		 sd.verify_newly_added_in_add_place_api_is_able_to_get_using_api_server_with_httprequest_and_verify_is_matched("place_id", "GetPlaceAPI", "GET", "sai");
		}
	}
}
