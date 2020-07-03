package com.stacksimplify.restservices.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.stacksimplify.restservices.dtos.UserMsDto;
import com.stacksimplify.restservices.entities.User;

@Mapper(componentModel = "Spring")
public interface UserMapper {
	
	/*
	 * TECH : UserMapper Interface (MapStruct Mapping)
	 */
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	// User to UserMsDto
	@Mappings({
	@Mapping(source = "email", target = "emailaddress"),
	@Mapping(source = "role", target = "roleName")}
	)
	UserMsDto userToUserMsDto(User user);
	
	// List<User> to List<UserMsDto>
	List<UserMsDto> usersToUserMsDtos(List<User> user);
	
}
