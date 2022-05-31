package com.example.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {
	@GetMapping("v1/person")
	public Person personV1() {
		return new Person("Bob Charlie");
	}
	@GetMapping("v2/person")
	public PersonV2 personV2() {
		Name name = new Name("Mubeen", "Musthafa");
		return new PersonV2(name);
	}
	
	//Swagger UI is thinking the below as duplicates. Hence commented
	
	
	@GetMapping(path="/v1/person", params="version=1")
	public Person paramV1() {
		return new Person("Bob Charlie");
	}
	@GetMapping(path="/v2/person", params="version=2")
	public PersonV2 paramV2() {
		Name name = new Name("Mubeen", "Musthafa");
		return new PersonV2(name);
	}
//	
//	//header versoning
	@GetMapping(path="/v1/person", headers ="version=1")
	public Person headerParamV1() {
		return new Person("Bob Charlie");
	}
	@GetMapping(path="/v2/person", headers="version=2")
	public PersonV2 headerParamV2() {
		Name name = new Name("Mubeen", "Musthafa");
		return new PersonV2(name);
	}
	
	
	//accept header versoning
	@GetMapping(path="/v1/person", produces ="application/vnd.company.app-v1+json")
	public Person producesParamV1() {
		return new Person("Bob Charlie");
	}
	@GetMapping(path="/v2/person", produces="application/vnd.company.app-v2+json")
	public PersonV2 producesParamV2() {
		Name name = new Name("Mubeen", "Musthafa");
		return new PersonV2(name);
	}
}
