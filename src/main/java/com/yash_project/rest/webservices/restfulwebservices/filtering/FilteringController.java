package com.yash_project.rest.webservices.restfulwebservices.filtering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {
	
	@RequestMapping("/filtering")
	public SomeBean someFiltering() {
		return new SomeBean("field1","field2","field3");
	}
		
		@RequestMapping("/filtering-list")
		public List<SomeBean> someFilteringList() {
			return Arrays.asList(new SomeBean("field1","field2","field3")
					,new SomeBean("field4","field5","field6"));
	}

}
