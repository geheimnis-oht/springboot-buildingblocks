package com.stacksimplify.restservices.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig { 
	
	/*
	 * TECH : Hotfix of beans conflicts when using Swagger in Spring RestAPI
	 */
	@Primary
	@Bean
	public LinkDiscoverers discoverers() {
	    List<LinkDiscoverer> plugins = new ArrayList<>();
	    plugins.add(new CollectionJsonLinkDiscoverer());
	    return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
	}
	
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .apiInfo(getApiInfo())	
          .select()                                  
          //.apis(RequestHandlerSelectors.any()) 
          .apis(RequestHandlerSelectors.basePackage("com.stacksimplify.restservices"))
          //.paths(PathSelectors.any())  
          .paths(PathSelectors.ant("/users/**"))
          .build();                                           
    }
    
    private ApiInfo getApiInfo() {
    	
		return new ApiInfoBuilder()
    			.title("Stack Simplify User Management App")
    			.description("This page list all API's of the App")
    			.version("2.0")
    			.termsOfServiceUrl("terms Of Service Url")
    			.license("licence 2.0")
    			.licenseUrl("https://app.userMgnt/licence.jsp")
    			.build();			
    }
}
