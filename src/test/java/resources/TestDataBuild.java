package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlacePojo;
import pojo.Location;

public class TestDataBuild {

	public AddPlacePojo addplace_payload(String name, String language, String address)
	{
		AddPlacePojo p=new AddPlacePojo();
		p.setAccuracy(50);
		p.setName(name);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		p.setLanguage(language);
		p.setAddress(address);
		List<String> l=new ArrayList<String>();
		l.add("shoe park");
		l.add("shop");
		p.setTypes(l);
		Location loc=new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		p.setLocation(loc);
		return p;
	}
	public String Deleteplace_payload(String placeid)
	{
		return "{\r\n" + 
				"    \"place_id\":\""+placeid+"\"\r\n" + 
				"}";
		
	}
}
