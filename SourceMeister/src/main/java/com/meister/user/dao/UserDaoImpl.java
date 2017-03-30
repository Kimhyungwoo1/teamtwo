package com.meister.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.meister.user.vo.UserVO;

public class UserDaoImpl implements UserDao {

	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private final String ID = "TEST";
	private final String PWD = "test";

	private void openJDBC() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}
	
	@Override
	public int insertNewUser(UserVO newUserVO) {
		openJDBC();

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(URL, ID, PWD);
			StringBuffer query = new StringBuffer();
			query.append("	INSERT INTO USR (        ");
			query.append("		   ATHRZTN_ID      ");
			query.append("		   , EMAIL         ");
			query.append("		    , GNDR         ");
			query.append("		   , NCNM          ");
			query.append("		   , PWD           ");
			query.append("		   . USR_ID        ");
			query.append("		   , USR_NM        ");
			query.append("		   )               ");
			query.append("		VALUES (  , ?       ");
			query.append("		          , ?       ");
			query.append("		          , ?       ");
			query.append("		          , ?       ");
			query.append("		          , ?       ");
			query.append("		          , ?       ");
			query.append("		          , ?       ");
			query.append("		          )        ");

			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, newUserVO.getUserId());
			stmt.setString(2, newUserVO.getUserName());
			stmt.setString(3, newUserVO.getPassword());
			stmt.setString(4, newUserVO.getGender());
			stmt.setString(5, newUserVO.getEmail());
			stmt.setString(6, newUserVO.getNickName());

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
	public List<UserVO> selectAllUser() {
		openJDBC();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, ID, PWD);
			StringBuffer query = new StringBuffer();
			query.append(" SELECT 			U.USR_ID	");
			query.append("    			,	U.PWD	");
			query.append("    			,	U.USR_NM  	");
			query.append("    			,	U.EMAIL	");
			query.append("    			,	U.GNDRT	");
			query.append("    			,	U.NCNM	");
			query.append("    			, 	AT.ATHRZTN_ID	");
			query.append("    			, 	AT.ATHRZTN_NM ");
			query.append(" FROM			USR U	");
			query.append(" 				, ATHRZTN AT ");
			query.append(" WHERE		U.ATHRZTN_ID =	AT.ATHRZTN_ID(+) ");

			stmt = conn.prepareStatement(query.toString());
			rs = stmt.executeQuery();

			List<UserVO> userList = new ArrayList<UserVO>();
			UserVO userVO = null;

			while (rs.next()) {
				userVO = new UserVO();
				userVO.setIndex(rs.getInt("RNUM"));
				userVO.setUserId(rs.getString("USR_ID"));
				userVO.setUserName(rs.getString("USR_NM"));
				userVO.setPassword(rs.getString("PWD"));
				userVO.setEmail(rs.getString("EMAIL"));
				userVO.setGender(rs.getString("GNDRT"));
				userVO.setNickName(rs.getString(" USR_NM"));
				userVO.getAuthorizationVO().setAuthorizationId(rs.getString("ATHRZTN_ID"));
				userVO.getAuthorizationVO().setAuthorizationName(rs.getString("ATHRZTN_NM"));
				userList.add(userVO);
			}

			System.out.println("user id  = " + userVO.getUserId());
			System.out.println("user pwd = " + userVO.getPassword());
			System.out.println("user nm = " + userVO.getUserName());

			return userList;
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
			query.append("	SELECT  U.USR_ID                      ");
			query.append("                                      ");
			query.append("		, U.EMAIL                         ");
			query.append("		, U.GNDR                          ");
			query.append("		, U.NCNM                          ");
			query.append("		, U.PWD                           ");
			query.append("		, U.USR_ID                        ");
			query.append("		, U.USR_NM                        ");
			query.append("		, A.ATHRZTN_ID                    ");
			query.append("		, A.ATHRZTN_NM                    ");
			query.append("	FROM USR U                            ");
			query.append("	 , ATHRZTN A                        ");
			query.append("	WHERE U.ATHRZTN_ID = A.ATHRZTN_ID  ");
			query.append("	AND   U.USR_ID = ?                    ");

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
				userVO.setGender(rs.getString("GNDRT"));
				userVO.setNickName(rs.getString(" USR_NM"));
				userVO.getAuthorizationVO().setAuthorizationId(rs.getString("ATHRZTN_ID"));
				userVO.getAuthorizationVO().setAuthorizationName(rs.getString("ATHRZTN_NM"));
			}
			System.out.println("user pwd" + userVO.getPassword());
			System.out.println("user nm" + userVO.getUserName());

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
			query.append("	 , ATHRZTN A                        ");
			query.append("	WHERE U.ATHRZTN_ID = A.ATHRZTN_ID(+) ");
			query.append("	AND   U.USR_ID = ?                    ");

			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, userVO.getUserId());
			rs = stmt.executeQuery();

			if (rs.next()) {
				userVO.setUserId(rs.getString("USR_ID"));
				userVO.setPassword(rs.getString("PWD"));
				userVO.setUserName(rs.getString("USR_NM"));
				userVO.setEmail(rs.getString("EMAIL"));
				userVO.setGender(rs.getString("GNDR"));
				userVO.setNickName(rs.getString("NCNM"));
				userVO.getAuthorizationVO().setAuthorizationId(rs.getString("ATHRZTN_ID"));
				userVO.getAuthorizationVO().setAuthorizationName(rs.getString("ATHRZTN_NM"));

			}
			System.out.println("user pwd =" + userVO.getPassword());
			System.out.println("user nm =" + userVO.getUserName());
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
	public int updateUserInfo(UserVO userVO) {
		openJDBC();

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(URL, ID, PWD);
			StringBuffer query = new StringBuffer();
			query.append("	UPDATE USR                ");
			query.append("	SET                       ");
			query.append("	   ATHRZTN_ID = ?       ");
			query.append("       , EMAIL      = ?   ");
			query.append("       , GNDR       = ?   ");
			query.append("       , NCNM       = ?   ");
			query.append("       , PWD        = ?   ");
			query.append("       , USR_ID     = ?   ");
			query.append("       , USR_NM     = ?   ");
			query.append("	WHERE   USR_ID      = ?   ");

			stmt.setString(1, userVO.getAuthorizationId());
			stmt.setString(2, userVO.getEmail());
			stmt.setString(3, userVO.getGender());
			stmt.setString(4, userVO.getNickName());
			stmt.setString(5, userVO.getPassword());
			stmt.setString(6, userVO.getUserId());
			stmt.setString(7, userVO.getUserName());
			stmt.setString(8, userVO.getUserId());

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
			query.append("	DELETE 	");
			query.append("  FROM USR 	");
			query.append("	WHERE USR_ID = ? ");

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
			conn = DriverManager.getConnection(URL, ID, PWD);
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
	public int selectCountByUserId(String userId) {
		openJDBC();
		System.out.println("sssss"+userId);
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
