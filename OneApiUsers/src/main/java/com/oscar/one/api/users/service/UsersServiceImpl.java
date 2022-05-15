package com.oscar.one.api.users.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.oscar.one.api.users.data.UsersEntity;
import com.oscar.one.api.users.data.UsersRoles;
import com.oscar.one.api.users.data.UsersRepository;
import com.oscar.one.api.users.data.UsersRolesRepository;
import com.oscar.one.api.users.shared.UsersDto;

@Service
public class UsersServiceImpl implements UsersService {

	private UsersRepository usersRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UsersRolesRepository usersRolesRepository;

	@Autowired
	public UsersServiceImpl(UsersRepository usersRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
			UsersRolesRepository usersRolesRepository) {
		this.usersRepository = usersRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.usersRolesRepository = usersRolesRepository;
	}

	@Override
	public UsersDto createUser(UsersDto Users) {
		Users.setUserId(UUID.randomUUID().toString());
		Users.setEncrptedPassword(bCryptPasswordEncoder.encode(Users.getPassword()));
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UsersEntity usersEntity = modelMapper.map(Users, UsersEntity.class);
		usersEntity.setActive(true);
		// User Role
		usersEntity.setUsersRoles(usersRolesRepository.findById(2L).orElse(null));
		usersRepository.save(usersEntity);
		UsersDto returnValue = modelMapper.map(usersEntity, UsersDto.class);

		return returnValue;
	}

	@Override
	public UsersDto updateUser(UsersDto Users) {
		Users.setEncrptedPassword(bCryptPasswordEncoder.encode(Users.getPassword()));
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UsersEntity usersEntity = modelMapper.map(Users, UsersEntity.class);

		usersRepository.save(usersEntity);
		UsersDto returnValue = modelMapper.map(usersEntity, UsersDto.class);

		return returnValue;
	}

	@Override
	public List<UsersDto> findAll() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<UsersEntity> userList = usersRepository.findAll();

		System.out.print(userList.toString());
		List<UsersDto> returnValue = userList.stream().map(user -> modelMapper.map(user, UsersDto.class))
				.collect(Collectors.toList());
		return returnValue;
	}

	@Override
	public void deleteUser(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public UsersDto findByUserName(String username) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UsersDto returnValue = modelMapper.map(usersRepository.findByUserName(username), UsersDto.class);

		return returnValue;
	}

	@Override
	public UsersDto findByAccount(String account) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UsersDto returnValue = modelMapper.map(usersRepository.findByAccount(account), UsersDto.class);
		return returnValue;
	}

	@Override
	public UsersDto findByEmail(String email) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UsersDto returnValue = modelMapper.map(usersRepository.findByEmail(email), UsersDto.class);
		return returnValue;
	}

}
