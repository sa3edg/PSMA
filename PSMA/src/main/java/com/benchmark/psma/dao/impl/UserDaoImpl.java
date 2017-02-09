package com.benchmark.psma.dao.impl;

import com.benchmark.psma.dao.model.User;
import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

@Repository
public class UserDaoImpl extends JdbcRepository<User, String> {

	private static final String USERS_TABLE_NAME = "users";
	private JdbcTemplate jdbcTemplate;
		
	public UserDaoImpl(DataSource dataSource)
	{
		this(USERS_TABLE_NAME);
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}
	
	public UserDaoImpl()
	{
		this(USERS_TABLE_NAME);
	}
	public UserDaoImpl(String tableName) {
		super(MAPPER, ROW_UNMAPPER, tableName, "username");
	}

	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}
	
	public static final RowMapper<User> MAPPER = new RowMapper<User>() {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setUserName(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setEnabled(rs.getBoolean("enabled"));
			user.withPersisted(true);
			return user;
		}
	};

	public static final RowUnmapper<User> ROW_UNMAPPER = new RowUnmapper<User>() {
		@Override
		public Map<String, Object> mapColumns(User User) {
			final LinkedHashMap<String, Object> columns = new LinkedHashMap<String, Object>();
			columns.put("username", User.getUserName());
			columns.put("password", User.getPassword());
			columns.put("enabled", User.getEnabled());
			return columns;
		}
	};

	@Override
	protected <S extends User> S postUpdate(S entity) {
		entity.withPersisted(true);
		return entity;
	}

	@Override
	protected <S extends User> S postCreate(S entity, Number generatedId) {
		entity.withPersisted(true);
		return entity;
	}
	
	public List<User> getUsers(){
		List<User> users = findAll();
		return users;
		
	}
}
