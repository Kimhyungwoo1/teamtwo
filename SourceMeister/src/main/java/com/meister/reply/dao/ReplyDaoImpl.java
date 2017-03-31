package com.meister.reply.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.meister.reply.vo.ReplySearchVO;
import com.meister.reply.vo.ReplyVO;

public class ReplyDaoImpl implements ReplyDao{

	@Override
	public int selectAllRepliesCount(ReplySearchVO replySearchVO) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			conn = DriverManager.getConnection(url, "TEAMTWO", "teamtwo");
			
			StringBuffer query  = new StringBuffer();
			
		
			query.append(" SELECT  COUNT(1) CNT          ");
			query.append(" FROM    RPLY R,               ");
			query.append("         USR U                 ");
			query.append(" WHERE   R.USR_ID = U.USR_ID   ");
			
			stmt = conn.prepareStatement(query.toString());
			
			rs = stmt.executeQuery();
		
			if( rs.next() ){
				return rs.getInt("CNT");
			}
			return 0;
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally {
			try {
				if( rs!= null ){
					rs.close();
				}
			} catch (SQLException e) {
			}
			try {
				if ( stmt != null ) {
					stmt.close();
				}
			} catch (SQLException e) {
			}
			try {
				if ( conn != null ) {
					conn.close();
				}
			} catch (SQLException e) {
			}

		}
	}

	@Override
	public List<ReplyVO> selectAllReplies(ReplySearchVO replySearchVO) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			conn = DriverManager.getConnection(url, "TEAMTWO", "teamtwo");
			
			StringBuffer query  = new StringBuffer();
			
			query.append(" SELECT   *                                    ");
			query.append(" FROM    (                                     ");
			query.append("         SELECT  ROWNUM RNUM                   ");
			query.append("                 ,A.*                          ");
			query.append("          FROM   (                             ");
			query.append("                 SELECT  LEVEL                 ");
			query.append("                   	   , R.RPLY_ID           ");		
			query.append("                         , R.OPNSRC_ID         ");
			query.append("                         , R.CMNT              ");
			query.append("                         , TO_CHAR(R.WRT_DT,'YYYY-MM-DD HH:MM')   WRT_DT ");
			query.append("                         , R.USR_ID R_USR_ID   ");
			query.append("                         , R.PRNT_RPLY_ID      ");
			query.append("                         , U.USR_ID            ");
			query.append("                         , U.USR_NM            ");
			query.append("                         , U.PWD               ");
			query.append("                         , U.GNDR              ");
			query.append("                         , U.EMAIL             ");
			query.append("                         , U.NCNM              ");
			query.append("                         , U.ATHRZTN_ID        ");
			query.append("                         , NVL(C.CNT,0) CHILD_CNT ");					          			     
			query.append("                 FROM    RPLY R                   ");
			query.append("                         , USR U                  ");
			query.append("						   , (SELECT COUNT(1)  CNT  ");
			query.append("						             , R.RPLY_ID    ");
			query.append("							  FROM   RPLY R         ");
			query.append("							         , RPLY P       ");
			query.append("							  WHERE  R.RPLY_ID = NVL(P.PRNT_RPLY_ID,'X') ");
			query.append("							  GROUP BY R.RPLY_ID                         ");
			query.append("							  ) C                                        ");
			query.append("                 WHERE   R.USR_ID = U.USR_ID   						 ");
			query.append("			       AND     R.RPLY_ID = C.RPLY_ID (+)                     ");
			query.append("			       AND     R.OPNSRC_ID = ?                     			 ");
			query.append("                 START WITH R.PRNT_RPLY_ID IS NULL   ");
			query.append("                 CONNECT BY PRIOR R.RPLY_ID = R.PRNT_RPLY_ID   ");
			query.append("                 ORDER SIBLINGS BY R.WRT_DT DESC   ");
			query.append("                 ) A                           ");
			query.append("         WHERE   ROWNUM <= ?                   ");
			query.append("  )                                            ");
			query.append("  WHERE  RNUM >= ?                             ");
		
			System.out.println("End" + replySearchVO.getPager().getEndArticleNumber());
			System.out.println("Start" + replySearchVO.getPager().getStartArticleNumber());
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, replySearchVO.getOpenSourceId());
			stmt.setInt(2, replySearchVO.getPager().getEndArticleNumber());
			stmt.setInt(3, replySearchVO.getPager().getStartArticleNumber());
			
			rs = stmt.executeQuery();
		
			List<ReplyVO> replies = new ArrayList<ReplyVO>();
			ReplyVO replyVO  = null;
		
			while( rs.next() ){
				replyVO = new ReplyVO();
				replyVO.setLevel(rs.getInt("LEVEL"));
				replyVO.setReplyId(rs.getString("RPLY_ID"));
				replyVO.setOpenSourceId(rs.getString("OPNSRC_ID"));
				replyVO.setComment(rs.getString("CMNT"));
				replyVO.setWriteDate(rs.getString("WRT_DT"));
				replyVO.setUserId(rs.getString("R_USR_ID"));
				replyVO.setParentReplyId(rs.getString("PRNT_RPLY_ID"));
				replyVO.getUser().setUserId(rs.getString("USR_ID"));
				replyVO.getUser().setUserName(rs.getString("USR_NM"));
				replyVO.getUser().setPassword(rs.getString("PWD"));
				replyVO.getUser().setGender(rs.getString("GNDR"));
				replyVO.getUser().setEmail(rs.getString("EMAIL"));
				replyVO.getUser().setNickName(rs.getString("NCNM"));
				replyVO.getUser().setAuthorizationId(rs.getString("ATHRZTN_ID"));
				replyVO.setChildCnt(rs.getInt("CHILD_CNT"));
				
				replies.add(replyVO);
			}
			return replies;
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally {
			try {
				if( rs!= null ){
					rs.close();
				}
			} catch (SQLException e) {
			}
			try {
				if ( stmt != null ) {
					stmt.close();
				}
			} catch (SQLException e) {
			}
			try {
				if ( conn != null ) {
					conn.close();
				}
			} catch (SQLException e) {
			}

		}
	}

	@Override
	public ReplyVO selectOneReply(String replyId) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			conn = DriverManager.getConnection(url, "TEAMTWO", "teamtwo");
			
			StringBuffer query  = new StringBuffer();
			
			query.append("                 SELECT  	 R.RPLY_ID           ");		
			query.append("                         , R.OPNSRC_ID         ");
			query.append("                         , R.CMNT              ");
			query.append("                         , TO_CHAR(R.WRT_DT,'YYYY-MM-DD HH:MM:SS')    WRT_DT ");
			query.append("                         , R.USR_ID R_USR_ID   ");
			query.append("                         , R.PRNT_RPLY_ID      ");
			query.append("                         , U.USR_ID            ");
			query.append("                         , U.USR_NM            ");
			query.append("                         , U.PWD               ");
			query.append("                         , U.GNDR              ");
			query.append("                         , U.EMAIL             ");
			query.append("                         , U.NCNM              ");
			query.append("                         , U.ATHRZTN_ID        ");
			query.append("                 FROM    RPLY R,               ");
			query.append("                         USR U                 ");
			query.append("                 WHERE   R.USR_ID = U.USR_ID   ");
			query.append("                         R.USR_ID = ?   		 ");
		
			stmt = conn.prepareStatement(query.toString());
			
			stmt.setString(1, replyId);
			
			rs = stmt.executeQuery();
		
			ReplyVO replyVO  = null;
		
			if( rs.next() ){
				replyVO = new ReplyVO();
				replyVO.setReplyId(rs.getString("RPLY_ID"));
				replyVO.setOpenSourceId(rs.getString("OPNSRC_ID"));
				replyVO.setComment(rs.getString("CMNT"));
				replyVO.setWriteDate(rs.getString("WRT_DT"));
				replyVO.setUserId(rs.getString("R_USR_ID"));
				replyVO.setParentReplyId(rs.getString("PRNT_RPLY_ID"));
				replyVO.getUser().setUserId(rs.getString("USR_ID"));
				replyVO.getUser().setUserName(rs.getString("USR_NM"));
				replyVO.getUser().setPassword(rs.getString("PWD"));
				replyVO.getUser().setGender(rs.getString("GNDR"));
				replyVO.getUser().setEmail(rs.getString("EMAIL"));
				replyVO.getUser().setNickName(rs.getString("NCNM"));
				replyVO.getUser().setAuthorizationId(rs.getString("ATHRZTN_ID"));
			
			}
			return replyVO;
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally {
			try {
				if( rs!= null ){
					rs.close();
				}
			} catch (SQLException e) {
			}
			try {
				if ( stmt != null ) {
					stmt.close();
				}
			} catch (SQLException e) {
			}
			try {
				if ( conn != null ) {
					conn.close();
				}
			} catch (SQLException e) {
			}

		}
	}

	@Override
	public int insertReply(ReplyVO replyVO) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			conn = DriverManager.getConnection(url, "TEAMTWO", "teamtwo");
			
			StringBuffer query  = new StringBuffer();
			
			query.append(" INSERT INTO RPLY (                                  ");
			query.append(" 				RPLY_ID                                ");
			query.append(" 				, OPNSRC_ID                            ");
			query.append(" 				, CMNT                                 ");
			query.append(" 				, WRT_DT                               ");
			query.append(" 				, USR_ID                               ");
			query.append(" 				, PRNT_RPLY_ID                         ");
			query.append(" 		        )                                      ");
			query.append(" VALUES			(                                  ");
			query.append(" 				 'RP-' ||                              ");
			query.append(" 				 TO_CHAR(SYSDATE,'YYYYMMDDHH24') ||    ");
			query.append(" 				 '-'||	                               ");
			query.append(" 				 LPAD(RPLY_ID_SEQ.NEXTVAL,6,'0')       ");
			query.append(" 				, ?                                    ");
			query.append(" 				, ?                                    ");
			query.append(" 				, SYSDATE      						   ");
			query.append(" 				, ?                                    ");
			query.append(" 				, ?                                    ");
			query.append(" 				)                                      ");
				
			stmt = conn.prepareStatement(query.toString());
			
			stmt.setString(1, replyVO.openSourceId);
			stmt.setString(2, replyVO.comment);
			stmt.setString(3, replyVO.userId);
			stmt.setString(4, replyVO.parentReplyId);
			
			return stmt.executeUpdate();
		
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally {
			try {
				if ( stmt != null ) {
					stmt.close();
				}
			} catch (SQLException e) {
			}
			try {
				if ( conn != null ) {
					conn.close();
				}
			} catch (SQLException e) {
			}

		}
	}

	@Override
	public int deleteReply(String replyId) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			conn = DriverManager.getConnection(url, "TEAMTWO", "teamtwo");
			
			StringBuffer query  = new StringBuffer();
			
			query.append(" DELETE    		");
			query.append(" FROM   	RPLY 	");
			query.append(" WHERE  	RPLY_ID = ? ");
			
				
			stmt = conn.prepareStatement(query.toString());
			
			stmt.setString(1, replyId);
			
			return stmt.executeUpdate();
		
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally {
			try {
				if ( stmt != null ) {
					stmt.close();
				}
			} catch (SQLException e) {
			}
			try {
				if ( conn != null ) {
					conn.close();
				}
			} catch (SQLException e) {
			}

		}
	}

	@Override
	public int updateReply(ReplyVO replyVO) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			conn = DriverManager.getConnection(url, "TEAMTWO", "teamtwo");
			
			StringBuffer query  = new StringBuffer();
			
			query.append(" UPDATE   RPLY              		");
            query.append(" SET		OPNSRC_ID	= ?   		");
            query.append("    	   	, CMNT		= ?   		");
            query.append("    	    , WRT_DT	= SYSDATE   ");
            query.append("    	    , USR_ID	= ?   		");
            query.append("    	    , PRNT_RPLY_ID	=  ?  	");
            query.append(" WHERE    RPLY_ID 	= ?        	");
			
			stmt = conn.prepareStatement(query.toString());
			
			stmt.setString(1, replyVO.openSourceId);
			stmt.setString(2, replyVO.comment);
			stmt.setString(3, replyVO.userId);
			stmt.setString(4, replyVO.parentReplyId);
			stmt.setString(5, replyVO.replyId);
			
			
			return stmt.executeUpdate();
		
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally {
			try {
				if ( stmt != null ) {
					stmt.close();
				}
			} catch (SQLException e) {
			}
			try {
				if ( conn != null ) {
					conn.close();
				}
			} catch (SQLException e) {
			}

		}
	}

}
