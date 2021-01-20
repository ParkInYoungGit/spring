package com.springlec.base0703.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.springlec.base0703.dto.BDto;
import com.springlec.base0703.util.Constant;

public class BDao {

	
	JdbcTemplate template;
	
	public BDao() {
		this.template = Constant.template; //db와 연결 끝
		
	}
	
	public void write(final String bName, final String bTel, final String bAddress, final String bEmail, final String bRelation) {
		
		this.template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String query = "insert into address_board (bName, bTel, bAddress, bEmail, bRelation) values (?, ?, ?, ?, ?)";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, bName);
				pstmt.setString(2, bTel);
				pstmt.setString(3, bAddress);
				pstmt.setString(4, bEmail);
				pstmt.setString(5, bRelation);
				return pstmt;
			}
		});
	}
	
	public ArrayList<BDto> list() {
		 String query = "select bId, bName, bTel, bAddress, bEmail, bRelation from address_board";
		 return (ArrayList<BDto>) template.query(query, new BeanPropertyRowMapper<BDto>(BDto.class));

	}
	
	
	public BDto contentView(String strID) {
		String query = "select * from address_board where bId = " + strID;
		return template.queryForObject(query, new BeanPropertyRowMapper<BDto>(BDto.class));
	
	}
	
	public void modify(final String bId, final String bName, final String bTel, final String bAddress, final String bEmail, final String bRelation) {
		String query = "update address_board set bName = ?, bTel = ?, bAddress = ?, bEmail = ?, bRelation = ? where bId = ?";
		this.template.update(query, new   PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, bName);
				ps.setString(2, bTel);
				ps.setString(3, bAddress);
				ps.setString(4, bEmail);
				ps.setString(5, bRelation);
				ps.setString(6, bId);
				
			}
		});

	}
	
	public void delete(final String bId) {
		String query = "delete from address_board where bId = ?";
		this.template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, bId);
			}
		});

	}
	
	
}
