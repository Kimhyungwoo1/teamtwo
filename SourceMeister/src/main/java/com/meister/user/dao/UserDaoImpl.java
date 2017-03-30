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

	@/*Override
	public List<UserVO> selectAllUser(UserSearchVO userSearchVO) {
		openJDBC();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, ID, PWD);
			StringBuffer query = new StringBuffer();
			query.append("		SELECT 		*	");
			query.append("		FROM		( 	");
			query.append("					SELECT	ROWNUM AS RNUM 	");
			query.append("							, A.* 	");
			query.append("					FROM				( 	");
			query.append("		      				SELECT 			U.USR_ID	");
			query.append("		       			       			,	U.PWD	");
			query.append("		       			       			,	U.USR_NM  	");
			query.append("		       			       			,	U.EMAIL	");
			query.append("		       			       			,	U.GNDRT	");
			query.append("		       			       			,	U.NCNM	");
			query.append("		       			       			, 	AT.ATHRZTN_ID	");
			query.append("		       			       			, 	AT.ATHRZTN_NM ");
			query.append("		       			       			, 	AT.PRNT_ATHRZTN_ID ");
			query.append("		       				FROM		USR U	");
			query.append("		       							, ATHRZTN AT ");
			query.append("		       				WHERE		U.ATHRZTN_ID =	AT.ATHRZTN_ID(+) ");
			query.append("		       							) 	A 	");
			query.append("		       				WHERE	ROWNUM <= ? ");
			query.append("		       				) ");
			query.append("						WHERE	RNUM >= ? ");

			stmt = conn.prepareStatement(query.toString());
			stmt.setInt(1, userSearchVO.getPager().getEndArticleNumber());
			stmt.setInt(2, userSearchVO.getPager().getStartArticleNumber());
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

	}*/

	Override
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
			query.append("				, A.ATHRZTN_ID                    ");
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
				userVO.setGender(rs.getString("GNDRT"));
				userVO.setNickName(rs.getString(" USR_NM"));
				userVO.getAuthorizationVO().setAuthorizationId(rs.getString("ATHRZTN_ID"));
				userVO.getAuthorizationVO().setAuthorizationName(rs.getString("ATHRZTN_NM"));
			}
			//System.out.println("user pwd" + userVO.getPassword());
			//System.out.println("user nm" + userVO.getUserName());

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

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(URL, ID, PWD);
			StringBuffer query = new StringBuffer();
			query.append("	UPDATE  USR                ");
			query.append("	SET                       ");
			//query.append("	   	   	ATHRZTN_ID = ?        ");
			query.append("       	  EMAIL      = ?     ");
			query.append("       	, GNDR       = ?     ");
			query.append("       	, NCNM       = ?     ");
			query.append("       	, PWD        = ?     ");
			query.append("       	, USR_ID     = ?     ");
			query.append("       	, USR_NM     = ?     ");
			query.append("	WHERE     USR_ID      = ?    ");

			//stmt.setString(1, userVO.getAuthorizationId());
			stmt.setString(2, userVO.getEmail());
			stmt.setString(3, userVO.getGender());
			stmt.setString(4, userVO.getNickName());
			stmt.setString(5, userVO.getPassword());
			stmt.setString(6, userVO.getUserId());
			stmt.setString(7, userVO.getUserName());

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

	/*@Override
	public int selectAllUserCount(UserSearchVO userSearchVO) {
		openJDBC();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, ID, PWD);
			StringBuffer query = new StringBuffer();
			query.append(" SELECT COUNT(1) CNT  ");
			query.append(" FROM	USR U  ");
			query.append(" 	, ATHRZTN A  ");
			query.append("  WHERE U.ATHRZTN_ID = A.ATHRZTN_ID(+) ");

			stmt = conn.prepareStatement(query.toString());

			rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getInt("CNT");
			}

			return 0;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {

				}
			}
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

	}*/

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
	public int selectCountByUserId(String userId) {
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


