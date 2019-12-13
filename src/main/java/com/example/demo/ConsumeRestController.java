package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;

@RestController
public class ConsumeRestController {
	
	public static final String REST_SERVICE_URI = "http://xmljsonconv.shop-project.svc.cluster.local:8080";
	
	
	@GetMapping(path = "/callRESTForJSON")
	@ResponseBody
	public String callRestForJSON(@RequestParam("xml") String xml)
	{
		
		//converting xml to json
		RestTemplate restTemplate = new RestTemplate();
		System.out.println("Call Rest Service");
        String json= restTemplate.getForObject(REST_SERVICE_URI+"/xmlToJSON?xmlInp="+xml, String.class);
		return json;
	}

	@GetMapping(path = "/callRESTForXML")
	@ResponseBody
	public Document callRestForXML(@RequestParam("json") String json)
	{
		
		//converting xml to json
		
		String url= "http://xmljsonconv.shop-project.svc.cluster.local:8080/jsonToXML?jsonInp={json}";
		RestTemplate restTemplate = new RestTemplate();
		System.out.println("Call Rest Service");
        //String doc= restTemplate.getForObject(REST_SERVICE_URI+"/jsonToXML?jsonInp="+json, String.class);
        Document doc= restTemplate.getForObject(url, Document.class,json);
		return doc;
	}
}
