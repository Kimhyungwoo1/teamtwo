package com.meister.opensource.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.meister.opensource.vo.OpensourceVO;

public class OpensourceDaoImpl implements OpensourceDao {

	@Override
	public OpensourceVO selectOneOpensource(String opensourceId) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			conn = DriverManager.getConnection(url, "TEST", "test");

			StringBuffer query = new StringBuffer();
			query.append(" SELECT  OPNSRC_ID "                    );
			query.append("         , LK_CNT "      );
			query.append(" FROM    OPNSRC "                       );
			query.append(" WHERE   OPNSRC_ID = ? "      );

			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, opensourceId);
			
			rs = stmt.executeQuery();
			
			OpensourceVO opensourceVO = null;
			
			if (rs.next()) {
				opensourceVO = new OpensourceVO();
				
				opensourceVO.setOpensourceId(opensourceId);
				opensourceVO.setLikeCount(rs.getInt("LK_CNT"));
			}
			
			return opensourceVO;
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			try {
				if (rs != null) {
					rs.close();
				}
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
	public int insertOneOpensource(String opensourceId) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			conn = DriverManager.getConnection(url, "TEST", "test");
			
			StringBuffer query = new StringBuffer();
			query.append(" INSERT  INTO    ATHRZTN     ( "                                                                      );
			query.append("                             OPNSRC_ID "                                                             );
			query.append("                             , LK_CNT "                                                           );
			query.append("                         ) "                                                                      );
			query.append("                 VALUES  ( "                                                                      );
			query.append("            					 ? "                                                                ); 
			query.append("                             , 0 "                                                                );
			query.append("                         ) "                                                                      );

			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, opensourceId);
			
			return stmt.executeUpdate();
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
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
	public int updateOneOpensource(String opensourceId) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			conn = DriverManager.getConnection(url, "TEST", "test");
			
			StringBuffer query = new StringBuffer();
			query.append(" UPDATE	OPNSRC   "          );
			query.append(" SET		 ");
			query.append(" 			LK_CNT = LK_CNT + 1 ");
			query.append(" WHERE	OPNSRC_ID = ? "); 
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, opensourceId);
			
			return stmt.executeUpdate();
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
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
