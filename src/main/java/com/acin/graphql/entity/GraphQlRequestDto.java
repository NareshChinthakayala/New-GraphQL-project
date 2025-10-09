package com.acin.graphql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GraphQlRequestDto {

	private String id;
	private String name;
	private String age;
	
}
