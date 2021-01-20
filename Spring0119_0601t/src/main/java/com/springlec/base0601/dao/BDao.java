package com.springlec.base0601.dao;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.swing.text.html.parser.DTD;

import com.springlec.base0601.dto.BDto;

public class BDao {

	DataSource dataSource;
	
	public BDao() {
		
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/mvc");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<BDto> list (){
		
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select bId, bName, bTitle, bContent, bDate from mvc_board";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate);
				dtos.add(dto);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;
	}

	
	public void write(String bName, String bTitle, String bContent) {
		
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "insert into mvc_board (bName, bTitle, bContent, bDate) values (?,?,?,now())";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			
			preparedStatement.executeUpdate();
			
				
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	public BDto contentView(String strbId) {
		BDto dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			// 위에 선언된 dataSource 사용
			connection = dataSource.getConnection();
			String query = "select * from mvc_board where bId = ?";
			preparedStatement = connection.prepareStatement(query); // query 문장 연결
			preparedStatement.setInt(1, Integer.parseInt(strbId));
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				int bId = resultSet.getInt("bId"); // index 또는 컬럼명을 작성할 수 있다. (컬럼명으로 뜨는걸 적어줘야한다. ex. cnt)
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				
				dto = new BDto(bId, bName, bTitle, bContent, bDate);
				
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { // 처음 선언된 부분 닫아준다.
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dto;
	}
	
	
	public void modify (String bId, String bName, String bTitle, String bContent) {

		//선언자.  --> 무조건 맨 위에/!
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		//--->
		
		try {
			connection = dataSource.getConnection();
			String query = "update mvc_board set bName = ?, bTitle = ?, bContent = ? where bId = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.setInt(4, Integer.parseInt(bId));
			
			preparedStatement.executeUpdate();
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {   //error가 걸렸든 안걸렸든 일로 마지막에는 온다. 쓰레기가 안쌓이도록. 다 close해서 
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e) {
				e.printStackTrace(); //화면상에 보이는 에러는 여기서 찍히는 것이다.  
			}
		}
		
		
	}
	
}//--------------
