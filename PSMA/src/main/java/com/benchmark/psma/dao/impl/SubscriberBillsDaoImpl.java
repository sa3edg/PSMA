package com.benchmark.psma.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.benchmark.psma.dao.model.SubscriberBills;
import com.benchmark.psma.dao.model.SubscriberCards;
import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;

@Repository
public class SubscriberBillsDaoImpl extends JdbcRepository<SubscriberBills, String> {

	private static final String TABLE_NAME = "subscriber_bills";
	private JdbcTemplate jdbcTemplate;
		
	public SubscriberBillsDaoImpl(DataSource dataSource)
	{
		this(TABLE_NAME);
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}
	
	public SubscriberBillsDaoImpl(String tableName) {
		super(MAPPER, ROW_UNMAPPER, tableName, "bill_number");
	}
	public SubscriberBillsDaoImpl()
	{
		this(TABLE_NAME);
	}
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}
	
	
	public static final RowMapper<SubscriberBills> MAPPER = new RowMapper<SubscriberBills>() {
		@Override
		public SubscriberBills mapRow(ResultSet rs, int rowNum) throws SQLException {
			SubscriberBills SubscriberBills = new SubscriberBills();
			SubscriberBills.setBillNumber(rs.getString("bill_number"));
			SubscriberBills.setSubscriberId(rs.getInt("subscriber_id"));
			SubscriberBills.setAmount(rs.getInt("amount"));
			SubscriberBills.setBillDate(rs.getDate("bill_date"));
			SubscriberBills.setStatus(rs.getString("status"));
			SubscriberBills.withPersisted(true);
			return SubscriberBills;
		}
	};

	public static final RowUnmapper<SubscriberBills> ROW_UNMAPPER = new RowUnmapper<SubscriberBills>() {
		@Override
		public Map<String, Object> mapColumns(SubscriberBills SubscriberBills) {
			final LinkedHashMap<String, Object> columns = new LinkedHashMap<String, Object>();
			columns.put("bill_number", SubscriberBills.getBillNumber());
			columns.put("subscriber_id", SubscriberBills.getSubscriberId());
			columns.put("amount", SubscriberBills.getAmount());
			columns.put("bill_date", SubscriberBills.getBillDate());
			columns.put("status", SubscriberBills.getStatus());
			
			
			return columns;
		}
	};

	@Override
	protected <S extends SubscriberBills> S postUpdate(S entity) {
		entity.withPersisted(true);
		return entity;
	}

	@Override
	protected <S extends SubscriberBills> S postCreate(S entity, Number generatedId) {
		entity.withPersisted(true);
		return entity;
	}
	public String getLastSubscriberBillsCode(){
		if(count() > 0){
			SubscriberBills SubscriberBills = jdbcTemplate.queryForObject("SELECT * FROM " + TABLE_NAME + " ORDER BY SubscriberBills_code DESC LIMIT 1", MAPPER);
			return SubscriberBills != null ? SubscriberBills.getBillNumber() : "";
		}
		return "";
	}
	public void updateSubscriberBills(SubscriberBills SubscriberBills){
		update(SubscriberBills);
	}
	
	public boolean isExistBillNumber(String billNumber){
		SubscriberBills subscriberBills = jdbcTemplate.queryForObject("SELECT * FROM " + TABLE_NAME + " WHERE national_id_no=?",new Object[] { billNumber}, MAPPER);
		return subscriberBills != null;
	}
}
