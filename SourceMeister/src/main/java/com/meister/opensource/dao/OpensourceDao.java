package com.meister.opensource.dao;

import com.meister.opensource.vo.OpensourceVO;

public interface OpensourceDao {
	
	public OpensourceVO selectOneOpensource(String opensourceId);
	
	public int insertOneOpensource(String opensourceId);
	
	public int updateOneOpensource(String opensourceId);

}
