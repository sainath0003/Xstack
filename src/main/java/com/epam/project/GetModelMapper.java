package com.epam.project;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
@Component
public class GetModelMapper {

	@Bean
	public ModelMapper getModelMapperObject() {
		return new ModelMapper();
	}
}
