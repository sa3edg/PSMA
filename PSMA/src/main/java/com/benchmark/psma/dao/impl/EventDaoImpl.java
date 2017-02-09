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

import com.benchmark.psma.dao.model.Event;
import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;

@Repository
public class EventDaoImpl extends JdbcRepository<Event, Integer> {

	private static final String TABLE_NAME = "events";
	private JdbcTemplate jdbcTemplate;
	
	public EventDaoImpl()
	{
		this(TABLE_NAME);
	}
	
	public EventDaoImpl(DataSource dataSource)
	{
		this(TABLE_NAME);
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}
	
	public EventDaoImpl(String tableName) {
		super(MAPPER, ROW_UNMAPPER, tableName);
	}

	public EventDaoImpl(RowMapper<Event> rowMapper, RowUnmapper<Event> rowUnmapper, String tableName, String idColumn) {
		super(rowMapper, rowUnmapper, tableName, idColumn);
	}

	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		setJdbcOperations(jdbcTemplate);
	}
	public static final RowMapper<Event> MAPPER = new RowMapper<Event>() {

		@Override
		public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
			Event event = new Event();
			event.setId(rs.getInt("id"));
			event.setTitle(rs.getString("title"));
			event.setDetails(rs.getString("details"));
			event.setStartDate(rs.getDate("start_date"));
			event.setEndDate(rs.getDate("end_date"));
			event.setImage(rs.getBytes("image"));
			return event;
		}
	};

	private static final RowUnmapper<Event> ROW_UNMAPPER = new RowUnmapper<Event>() {
		@Override
		public Map<String, Object> mapColumns(Event Event) {
			Map<String, Object> mapping = new LinkedHashMap<String, Object>();
			mapping.put("id", Event.getId());
			mapping.put("title", Event.getTitle());
			mapping.put("details", Event.getDetails());
			mapping.put("start_date", Event.getStartDate());
			mapping.put("end_date", Event.getEndDate());
			mapping.put("image", Event.getImage());
			return mapping;
		}
	};

	@Override
	protected <S extends Event> S postCreate(S entity, Number generatedId) {
		entity.setId(generatedId.intValue());
		return entity;
	}
	
	public List<Event> getValidEvents(){
		return (List<Event>)jdbcTemplate.query("SELECT * from events where CURDATE() <= end_date", MAPPER);
	}
}
