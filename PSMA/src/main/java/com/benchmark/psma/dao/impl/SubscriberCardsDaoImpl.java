package com.benchmark.psma.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.benchmark.psma.dao.model.Subscriber;
import com.benchmark.psma.dao.model.SubscriberCards;
import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;

@Repository
public class SubscriberCardsDaoImpl extends JdbcRepository<SubscriberCards, String> {

	private static final String TABLE_NAME = "subscriber_cards";
	private JdbcTemplate jdbcTemplate;
		
	public SubscriberCardsDaoImpl(DataSource dataSource)
	{
		this(TABLE_NAME);
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}
	
	public SubscriberCardsDaoImpl(String tableName) {
		super(MAPPER, ROW_UNMAPPER, tableName, "card_number");
	}
	public SubscriberCardsDaoImpl()
	{
		this(TABLE_NAME);
	}
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}
	
	
	public static final RowMapper<SubscriberCards> MAPPER = new RowMapper<SubscriberCards>() {
		@Override
		public SubscriberCards mapRow(ResultSet rs, int rowNum) throws SQLException {
			SubscriberCards subscriberCards = new SubscriberCards();
			subscriberCards.setCardNumber(rs.getString("card_number"));
			subscriberCards.setSubscriberId(rs.getInt("subscriber_id"));
			subscriberCards.setPoints(rs.getInt("points"));
			subscriberCards.setRegisterationDate(rs.getDate("registeration_date"));
			subscriberCards.setLastUpdateDate(rs.getDate("last_update"));
			subscriberCards.setStatus(rs.getString("status"));
			subscriberCards.withPersisted(true);
			return subscriberCards;
		}
	};

	public static final RowUnmapper<SubscriberCards> ROW_UNMAPPER = new RowUnmapper<SubscriberCards>() {
		@Override
		public Map<String, Object> mapColumns(SubscriberCards SubscriberCards) {
			final LinkedHashMap<String, Object> columns = new LinkedHashMap<String, Object>();
			columns.put("card_number", SubscriberCards.getCardNumber());
			columns.put("subscriber_id", SubscriberCards.getSubscriberId());
			columns.put("points", SubscriberCards.getPoints());
			columns.put("registeration_date", SubscriberCards.getRegisterationDate());
			columns.put("last_update", SubscriberCards.getLastUpdateDate());
			columns.put("status", SubscriberCards.getStatus());
			
			
			return columns;
		}
	};

	@Override
	protected <S extends SubscriberCards> S postUpdate(S entity) {
		entity.withPersisted(true);
		return entity;
	}

	@Override
	protected <S extends SubscriberCards> S postCreate(S entity, Number generatedId) {
		entity.withPersisted(true);
		return entity;
	}
	public String getLastSubscriberCardsCode(){
		if(count() > 0){
			SubscriberCards subscriberCards = jdbcTemplate.queryForObject("SELECT * FROM " + TABLE_NAME + " ORDER BY SubscriberCards_code DESC LIMIT 1", MAPPER);
			return subscriberCards != null ? subscriberCards.getCardNumber() : "";
		}
		return "";
	}
	public void updateSubscriberCards(SubscriberCards SubscriberCards){
		update(SubscriberCards);
	}
	
	public boolean isExistCardNumber(String cardNumber){
		SubscriberCards subscriberCards = jdbcTemplate.queryForObject("SELECT * FROM " + TABLE_NAME + " WHERE national_id_no=?",new Object[] { cardNumber}, MAPPER);
		return subscriberCards != null;
	}
	
	public SubscriberCards getCardByCardNumber(String cardNumber){
		return  jdbcTemplate.queryForObject("SELECT * FROM " + TABLE_NAME + " WHERE national_id_no=?",new Object[] { cardNumber}, MAPPER);
	}
}
