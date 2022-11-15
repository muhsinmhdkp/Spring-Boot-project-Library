package com.student.ust;

import org.apache.coyote.Request;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * The type Ust application.
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableSwagger2
public class UstApplication {


	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(UstApplication.class, args);
	}

	/**
	 * Student book ap id docket.
	 *
	 * @return the docket
	 */
	@Bean
	public Docket studentBookAPId(){
		return  new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.student.ust")).build();
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}

