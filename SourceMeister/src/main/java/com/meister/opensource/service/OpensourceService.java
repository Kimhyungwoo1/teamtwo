package com.meister.opensource.service;

import com.meister.opensource.vo.OpensourceVO;

public interface OpensourceService {
	
	public OpensourceVO getOneOpensource (String opensourceId);
	
	public boolean addOneOpensource (String opensourceId);
	
	public boolean updateLikeCount(String opensourceId);
	
	public OpensourceVO selectRankLikeCount();

}
