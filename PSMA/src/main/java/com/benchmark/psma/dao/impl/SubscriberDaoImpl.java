package com.benchmark.psma.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.benchmark.psma.dao.model.Subscriber;
import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;

@Repository
public class SubscriberDaoImpl extends JdbcRepository<Subscriber, Integer> {

	private static final String TABLE_NAME = "subscriber";
	private JdbcTemplate jdbcTemplate;
	
	public SubscriberDaoImpl()
	{
		this(TABLE_NAME);
	}
	
	public SubscriberDaoImpl(DataSource dataSource)
	{
		this(TABLE_NAME);
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}
	
	public SubscriberDaoImpl(String tableName) {
		super(MAPPER, ROW_UNMAPPER, tableName);
	}

	public SubscriberDaoImpl(RowMapper<Subscriber> rowMapper, RowUnmapper<Subscriber> rowUnmapper, String tableName, String idColumn) {
		super(rowMapper, rowUnmapper, tableName, idColumn);
	}

	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}
	public static final RowMapper<Subscriber> MAPPER = new RowMapper<Subscriber>() {

		@Override
		public Subscriber mapRow(ResultSet rs, int rowNum) throws SQLException {
			Subscriber subscriber = new Subscriber();
			subscriber.setId(rs.getInt("id"));
			subscriber.setName(rs.getString("name"));
			subscriber.setGender(rs.getString("gender"));
			subscriber.setAge(rs.getInt("age"));
			subscriber.setAddress(rs.getString("address"));
			subscriber.setMobileNumber(rs.getString("mobile_number"));
			subscriber.setFacebookAccount(rs.getString("facebook_account"));
			subscriber.setEmailAddress(rs.getString("email_address"));
			subscriber.setNationalIdNumber(rs.getString("national_id_no"));
			subscriber.setRegisterationDate(rs.getDate("registeration_date"));
			return subscriber;
		}
	};

	private static final RowUnmapper<Subscriber> ROW_UNMAPPER = new RowUnmapper<Subscriber>() {
		@Override
		public Map<String, Object> mapColumns(Subscriber subscriber) {
			Map<String, Object> mapping = new LinkedHashMap<String, Object>();
			mapping.put("id", subscriber.getId());
			mapping.put("name", subscriber.getName());
			mapping.put("gender", subscriber.getGender());
			mapping.put("age", subscriber.getAge());
			mapping.put("address", subscriber.getAddress());
			mapping.put("mobile_number", subscriber.getMobileNumber());
			mapping.put("facebook_account", subscriber.getFacebookAccount());
			mapping.put("email_address", subscriber.getEmailAddress());
			mapping.put("national_id_no", subscriber.getNationalIdNumber());
			mapping.put("registeration_date", subscriber.getRegisterationDate());
			return mapping;
		}
	};

	@Override
	protected <S extends Subscriber> S postCreate(S entity, Number generatedId) {
		entity.setId(generatedId.intValue());
		return entity;
	}
	
	public List<Subscriber> getValidSubscribers(){
		return jdbcTemplate.queryForList("SELECT * from Subscribers where CURDATE() <= end_date", Subscriber.class);
	}
	
	public Integer getNextSubscriberId(){
		if(count() > 0){
			Subscriber subscriber = jdbcTemplate.queryForObject("SELECT * FROM " + TABLE_NAME + " ORDER BY id DESC LIMIT 1", MAPPER);
			return subscriber != null ? subscriber.getId() + 11 : 11;
		}
		return 11;
	}
	
	public Subscriber getSubscriberByMobileNumber(String mobilenumber){
		return jdbcTemplate.queryForObject("SELECT * FROM " + TABLE_NAME + " WHERE mobile_number=?",new Object[] { mobilenumber}, MAPPER);
	}
	
	public boolean isExistMobileNumber(String mobilenumber){
		Subscriber subscriber = jdbcTemplate.queryForObject("SELECT * FROM " + TABLE_NAME + " WHERE mobile_number=?",new Object[] { mobilenumber}, MAPPER);
		return subscriber != null;
	}
	
	public boolean isExistEmail(String email){
		Subscriber subscriber = jdbcTemplate.queryForObject("SELECT * FROM " + TABLE_NAME + " WHERE email_address=?",new Object[] { email}, MAPPER);
		return subscriber != null;
	}
	
	public boolean isExistNationalNumber(String nationalNumber){
		Subscriber subscriber = jdbcTemplate.queryForObject("SELECT * FROM " + TABLE_NAME + " WHERE national_id_no=?",new Object[] { nationalNumber}, MAPPER);
		return subscriber != null;
	}
}
