package com.acin.graphql.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.acin.graphql.entity.GraphQlDemo;
import com.acin.graphql.entity.GraphQlRequestDto;
import com.acin.graphql.repository.GraphQlRespository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GraphQlController {

	private final GraphQlRespository graphQlRespository;

	@QueryMapping
	public List<GraphQlDemo> getData() {
		return graphQlRespository.findAll();
	}

	@MutationMapping
	public GraphQlDemo addData(@Argument String id, @Argument String name, @Argument String age) {
		return graphQlRespository.save(new GraphQlDemo(id, name, age)); // You may want to use UUID here
	}

	@QueryMapping
	public GraphQlDemo getDataById(@Argument String id) {
		return graphQlRespository.findById(id).orElseThrow(() -> new RuntimeException("Record not found"));
	}

	@MutationMapping
	public boolean deleteDataById(@Argument String id) {
	    if (graphQlRespository.existsById(id)) {
	        graphQlRespository.deleteById(id);
	        return true;
	    }
	    return false;
	}
	
	@MutationMapping
	public GraphQlDemo addUser(@Argument("input") GraphQlRequestDto qlDemo) {
		return graphQlRespository.save(new GraphQlDemo(qlDemo.getId(), qlDemo.getName(), qlDemo.getAge()));
	}
	
	@MutationMapping
	public GraphQlDemo updateUser(@Argument("input") GraphQlRequestDto requestDto) {
		
		Optional<GraphQlDemo> byId = graphQlRespository.findById(requestDto.getId());
		if(byId.isPresent()) {
			GraphQlDemo graphQlDemo = byId.get();
			graphQlDemo.setName(requestDto.getName());
			graphQlDemo.setAge(requestDto.getAge());
			graphQlRespository.save(graphQlDemo);
			return graphQlDemo;
		}else {
			throw new RuntimeException("Record not found");
		}
		
	}
	
	@QueryMapping
	public List<GraphQlDemo> findUserByNameSearch(@Argument String name) {
		return graphQlRespository.findByNameContainingIgnoringCase(name);
	}

}
