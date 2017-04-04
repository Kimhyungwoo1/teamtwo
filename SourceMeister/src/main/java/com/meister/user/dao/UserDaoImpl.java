package com.meister.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.meister.user.vo.UserVO;

public class UserDaoImpl implements UserDao {


	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private final String ID = "Test";
	private final String PWD = "test";


	@Override
	public int insertNewUser(UserVO newUserVO) {
		openJDBC();

		Connection conn = null;
		PreparedStatement stmt = null;
	
		try {
			conn = DriverManager.getConnection(URL, ID, PWD);
			StringBuffer query = new StringBuffer();
		
			query.append("	INSERT INTO USR (        ");
			query.append("		      EMAIL         ");
			query.append("		    , PWD         ");
			query.append("		   ,  GNDR          ");
			query.append("		   , NCNM           ");
			query.append("		   , USR_NM        ");
			query.append("		   , USR_ID        ");
			query.append("		   )               ");
			query.append("		VALUES (           ");
			query.append("		            ?       ");
			query.append("		          , ?       ");
			query.append("		          , ?       ");
			query.append("		          , ?       ");
			query.append("		          , ?       ");
			query.append("		          , ?       ");
			query.append("		          )        ");

			stmt = conn.prepareStatement(query.toString());
			
			stmt.setString(1, newUserVO.getEmail());
			stmt.setString(2, newUserVO.getPassword());
			stmt.setString(3, newUserVO.getGender());
			stmt.setString(4, newUserVO.getNickName());
			stmt.setString(5, newUserVO.getUserName());
			stmt.setString(6, newUserVO.getUserId());

			return stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}

		}

	}

	@Override
	public UserVO selectOneUser(String userId) {
		openJDBC();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, ID, PWD);
			StringBuffer query = new StringBuffer();
			query.append("	SELECT  	U.USR_ID                      ");
			query.append("				, U.EMAIL                         ");
			query.append("				, U.GNDR                          ");
			query.append("				, U.NCNM                          ");
			query.append("				, U.PWD                           ");
			query.append("				, U.USR_ID                        ");
			query.append("				, U.USR_NM                        ");
			query.append("				, U.ATHRZTN_ID ATHRZTN_ID                        ");
			query.append("				, A.ATHRZTN_ID A_ATHRZTN_ID                    ");
			query.append("				, A.ATHRZTN_NM                    ");
			query.append("	FROM 		USR U                            ");
			query.append("	 			, ATHRZTN A                        ");
			query.append("	WHERE 		U.ATHRZTN_ID = A.ATHRZTN_ID   ");
			query.append("	AND   		U.USR_ID = ?                    ");

			stmt = conn.prepareStatement(query.toString());
			
			stmt.setString(1, userId);
			
			rs = stmt.executeQuery();
			
			UserVO userVO = null;

			if (rs.next()) {
				userVO = new UserVO();
				userVO.setUserId(rs.getString("USR_ID"));
				userVO.setUserName(rs.getString("USR_NM"));
				userVO.setPassword(rs.getString("PWD"));
				userVO.setEmail(rs.getString("EMAIL"));
				userVO.setGender(rs.getString("GNDR"));
				userVO.setNickName(rs.getString("USR_NM"));
				userVO.getAuthorizationVO().setAuthorizationId(rs.getString("ATHRZTN_ID"));
				userVO.getAuthorizationVO().setAuthorizationName(rs.getString("ATHRZTN_NM"));
			}
			
			return userVO;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}

		}

	}

	@Override

	public UserVO selectOneUser(UserVO userVO) {
		openJDBC();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		System.out.println("user pwd =" + userVO.getPassword());
		System.out.println("user id =" + userVO.getUserId());
		
		try {
			conn = DriverManager.getConnection(URL, ID, PWD);

			StringBuffer query = new StringBuffer();

			query.append("	SELECT  U.USR_ID                      ");
			query.append("			, U.PWD                           ");
			query.append("			, U.USR_NM                        ");
			query.append("			, U.EMAIL                         ");
			query.append("			, U.GNDR                          ");
			query.append("			, U.NCNM                          ");
			query.append("			, A.ATHRZTN_ID                    ");
			query.append("			, A.ATHRZTN_NM                    ");
			query.append("	FROM 	USR U                            ");
			query.append("	 , ATHRZTN A                        	");
			query.append("	WHERE U.ATHRZTN_ID = A.ATHRZTN_ID(+) 	");
			query.append("	AND   U.USR_ID = ?                    ");
			query.append("	AND   U.PWD = ?                    ");

			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, userVO.getUserId());
			stmt.setString(2 , userVO.getPassword());
			rs = stmt.executeQuery();

			UserVO user  = null;
			
			if (rs.next()) {
				user = new UserVO();
				user.setUserId(rs.getString("USR_ID"));
				user.setPassword(rs.getString("PWD"));
				user.setUserName(rs.getString("USR_NM"));
				user.setEmail(rs.getString("EMAIL"));
				user.setGender(rs.getString("GNDR"));
				user.setNickName(rs.getString("NCNM"));
				user.getAuthorizationVO().setAuthorizationId(rs.getString("ATHRZTN_ID"));
				user.getAuthorizationVO().setAuthorizationName(rs.getString("ATHRZTN_NM"));

			}
			
			return user;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);

		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {

				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

				}
			}
		}

	}

	
	
	
	@Override
	public int updateUserInfo(UserVO userVO) {
		openJDBC();
		System.out.println(userVO.getEmail());
		System.out.println(userVO.getNickName());
		System.out.println(userVO.getPassword());
		System.out.println(userVO.getUserName());
		System.out.println(userVO.getUserId());
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(URL, ID, PWD);
			StringBuffer query = new StringBuffer();
			query.append("	UPDATE  USR                ");
			query.append("	SET                       ");
			query.append("       	  EMAIL      = ?     ");
			query.append("       	, NCNM       = ?     ");
			query.append("       	, PWD        = ?     ");
			query.append("       	, USR_NM     = ?     ");
			query.append("	WHERE     USR_ID      = ?    ");
			
			stmt = conn.prepareStatement(query.toString());
			
			
			stmt.setString(1, userVO.getEmail());
			stmt.setString(2, userVO.getNickName());
			stmt.setString(3, userVO.getPassword());
			stmt.setString(4, userVO.getUserName());
			stmt.setString(5, userVO.getUserId());

			return stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {

				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

				}
			}
		}

	}

	@Override
	public int deleteOneUser(String userId) {
		openJDBC();
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(URL, ID, PWD);
			StringBuffer query = new StringBuffer();
			query.append("	DELETE 	 ");
			query.append("  FROM USR 	 ");
			query.append("	WHERE USR_ID = ?  ");

			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, userId);

			return stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {

				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

				}
			}
		}

	}

	
	@Override
	public int changeUser(String beforeAuthriztion, String afterAuthriztion) {
		openJDBC();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(URL,ID, PWD);
			StringBuffer query = new StringBuffer();
			query.append("	UPDATE USR                     ");
			query.append(" 	SET                           ");
			query.append(" 				ATHRZTN_ID = ?       ");
			query.append(" 	WHERE		ATHRZTN_ID = ?   ");
			
			stmt = conn.prepareStatement(query.toString());
			 
			stmt.setString(1, afterAuthriztion);
			stmt.setString(2, beforeAuthriztion);
			
			return stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage() , e);
		}finally {
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		
		
		
	}

	private void openJDBC() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	

	@Override
	public int selectCountByUserId(String userId ) {
		openJDBC();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, ID, PWD);
			
			StringBuffer query = new StringBuffer();
			
			query.append(" SELECT	COUNT(1) CNT ");
			query.append(" FROM		USR            ");
			query.append(" WHERE	USR_ID = ? ");
			
		
			
			stmt = conn.prepareStatement(query.toString());
			
			stmt.setString(1, userId);
			
			
			
			rs = stmt.executeQuery();
			
			if(rs.next()){
				return rs.getInt("CNT");
			}
			return 0;
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {	}
			}
			if(stmt != null){
				try {
					stmt.close();
				} catch (SQLException e) {	}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {	}
			}
			
		}
	}

	
	
}


