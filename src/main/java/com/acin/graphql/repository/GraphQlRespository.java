package com.acin.graphql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acin.graphql.entity.GraphQlDemo;

public interface GraphQlRespository extends JpaRepository<GraphQlDemo,String> {

	List<GraphQlDemo> findByNameContainingIgnoringCase(String name);
}
