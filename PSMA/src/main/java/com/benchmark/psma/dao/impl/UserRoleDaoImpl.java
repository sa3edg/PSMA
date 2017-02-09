package com.benchmark.psma.dao.impl;

import com.benchmark.psma.dao.model.UserRole;
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
public class UserRoleDaoImpl extends JdbcRepository<UserRole, Integer> {

	private static final String USERS_ROLES_TABLE_NAME = "user_roles";
	private JdbcTemplate jdbcTemplate;
	
	public UserRoleDaoImpl(DataSource dataSource)
	{
		this(USERS_ROLES_TABLE_NAME);
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}
	public UserRoleDaoImpl()
	{
		this(USERS_ROLES_TABLE_NAME);
	}
	public UserRoleDaoImpl(String tableName) {
		super(MAPPER, ROW_UNMAPPER, tableName);
	}
	
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}

	public UserRoleDaoImpl(RowMapper<UserRole> rowMapper, RowUnmapper<UserRole> rowUnmapper, String tableName, String idColumn) {
		super(rowMapper, rowUnmapper, tableName, idColumn);
	}

	public static final RowMapper<UserRole> MAPPER = new RowMapper<UserRole>() {

		@Override
		public UserRole mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserRole UserRole = new UserRole();
			UserRole.setId(rs.getInt("user_role_id"));
			UserRole.setUserName(rs.getString("username"));
			UserRole.setRole(rs.getString("role"));
			return UserRole;
		}
	};

	private static final RowUnmapper<UserRole> ROW_UNMAPPER = new RowUnmapper<UserRole>() {
		@Override
		public Map<String, Object> mapColumns(UserRole comment) {
			Map<String, Object> mapping = new LinkedHashMap<String, Object>();
			mapping.put("user_role_id", comment.getId());
			mapping.put("username", comment.getUserName());
			mapping.put("role", comment.getRole());
			return mapping;
		}
	};

	@Override
	protected <S extends UserRole> S postCreate(S entity, Number generatedId) {
		entity.setId(generatedId.intValue());
		return entity;
	}
	public List<UserRole> getNotAdminUsers(){
		Object[] args = new Object[] {UserRole.ADMIN_ROLE};
        List<UserRole> users = jdbcTemplate.query("SELECT * FROM " + USERS_ROLES_TABLE_NAME + " WHERE role != ?;", args, MAPPER);
        return users;
	}
	public void insert(UserRole userRole){
		jdbcTemplate.update("INSERT INTO "+USERS_ROLES_TABLE_NAME+" (username, role) VALUES (?, ?)", new Object[] {
				userRole.getUserName(), userRole.getRole()  
			});
	}
	public void delete(String id){
		jdbcTemplate.update("DELETE FROM "+USERS_ROLES_TABLE_NAME + " WHERE username = ?", id);
	}
}
