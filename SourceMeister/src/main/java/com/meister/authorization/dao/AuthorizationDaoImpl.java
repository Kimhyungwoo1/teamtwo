package com.meister.authorization.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.meister.authorization.vo.AuthorizationVO;

public class AuthorizationDaoImpl implements AuthorizationDao {

	String oracleUrl = "jabc:oracle:thin:@192.168.201.14:1521:XE";

	@Override
	public int insertAuthorization(AuthorizationVO authorizationVO) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(oracleUrl, "TEAMTWO", "teamtwo");
			
			StringBuffer query = new StringBuffer();
			query.append(" INSERT		INTO	ATHRZTN ");
			query.append(" 								( ");
			query.append(" 								ATHRZTN_ID ");
			query.append(" 								, ATHRZTN_NM ");
			query.append(" 								) ");
			query.append(" VALUES						( ");
			query.append(" 								'AT-' || TO_CHAR(SYSDATE, 'YYYYMMDDHH24') || '-'"
														+ " || LPAD(ATHRZTN_ID_SEQ.NEXTVAL, 6, '0') ");
			query.append(" 								, ? ");
			query.append(" 								) ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, authorizationVO.getAuthorizationName());
			
			return stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
			}
		}
	}

	@Override
	public List<AuthorizationVO> allAuthorization() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(oracleUrl, "TEAMTWO", "teamtwo");
			
			StringBuffer query = new StringBuffer();
			query.append(" SELECT		ATHRZTN_ID ");
			query.append(" 				, ATHRZTN_NM ");
			query.append(" FROM			ATHRZTN ");
			query.append(" ORDER		BY	ATHRZTN_ID DESC ");
			
			stmt = conn.prepareStatement(query.toString());
			
			rs = stmt.executeQuery();
			
			AuthorizationVO authorizationVO = null;
			List<AuthorizationVO> authList = new ArrayList<AuthorizationVO>();
			while(rs.next()) {
				authorizationVO = new AuthorizationVO();
				authorizationVO.setAuthorizationId(rs.getString("ATHRZTN_ID"));
				authorizationVO.setAuthorizationName(rs.getString("ATHRZTN_NM"));
				
				authList.add(authorizationVO);
			}
			return authList;
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			try {
				rs.close();
			} catch (SQLException e1) {
			}
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
			}
		}
	}

	@Override
	public int deleteAuthorization(String authorizationId) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(oracleUrl, "TEAMTWO", "teamtwo");
			
			StringBuffer query = new StringBuffer();
			query.append(" DELETE ");
			query.append(" FROM		ATHRZTN ");
			query.append(" WHERE	ATHRZTN_ID = ? ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, authorizationId);
			
			return stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
			}
		}
	}

}
