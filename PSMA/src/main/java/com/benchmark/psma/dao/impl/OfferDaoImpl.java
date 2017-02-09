package com.benchmark.psma.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.benchmark.psma.common.util.DateUtil;
import com.benchmark.psma.dao.model.Offer;
import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;

@Repository
public class OfferDaoImpl extends JdbcRepository<Offer, Integer> {

	private static final String TABLE_NAME = "offers";
	private JdbcTemplate jdbcTemplate;
	
	public OfferDaoImpl()
	{
		this(TABLE_NAME);
	}
	
	public OfferDaoImpl(DataSource dataSource)
	{
		this(TABLE_NAME);
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}
	
	public OfferDaoImpl(String tableName) {
		super(MAPPER, ROW_UNMAPPER, tableName);
	}

	public OfferDaoImpl(RowMapper<Offer> rowMapper, RowUnmapper<Offer> rowUnmapper, String tableName, String idColumn) {
		super(rowMapper, rowUnmapper, tableName, idColumn);
	}

	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}
	public static final RowMapper<Offer> MAPPER = new RowMapper<Offer>() {

		@Override
		public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Offer offer = new Offer();
			offer.setId(rs.getInt("id"));
			offer.setTitle(rs.getString("title"));
			offer.setDetails(rs.getString("details"));
			offer.setItem(rs.getString("item"));
			offer.setOldPrice(rs.getFloat("old_price"));
			offer.setNewPrice(rs.getFloat("new_price"));
			offer.setDiscount(rs.getFloat("discount"));
			offer.setSale(rs.getBoolean("sale"));
			offer.setStartDate(rs.getDate("start_date"));
			offer.setEndDate(rs.getDate("end_date"));
			offer.setImage(rs.getBytes("image"));
			return offer;
		}
	};

	private static final RowUnmapper<Offer> ROW_UNMAPPER = new RowUnmapper<Offer>() {
		@Override
		public Map<String, Object> mapColumns(Offer Offer) {
			Map<String, Object> mapping = new LinkedHashMap<String, Object>();
			mapping.put("id", Offer.getId());
			mapping.put("title", Offer.getTitle());
			mapping.put("details", Offer.getDetails());
			mapping.put("item", Offer.getItem());
			mapping.put("old_price", Offer.getOldPrice());
			mapping.put("new_price", Offer.getNewPrice());
			mapping.put("discount", Offer.getDiscount());
			mapping.put("sale", Offer.isSale());
			mapping.put("start_date", Offer.getStartDate());
			mapping.put("end_date", Offer.getEndDate());
			mapping.put("image", Offer.getImage());
			return mapping;
		}
	};

	@Override
	protected <S extends Offer> S postCreate(S entity, Number generatedId) {
		entity.setId(generatedId.intValue());
		return entity;
	}
	
	public List<Offer> getValidOffers(){
		
		return (List<Offer>)jdbcTemplate.query("SELECT * from offers where CURDATE() <= end_date", MAPPER);
	}
}
