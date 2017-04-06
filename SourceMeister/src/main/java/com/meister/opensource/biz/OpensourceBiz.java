package com.meister.opensource.biz;

import com.meister.opensource.vo.OpensourceVO;

public interface OpensourceBiz {
	
	public OpensourceVO getOneOpensource (String opensourceId);
	
	public boolean addOneOpensource (String opensourceId);
	
	public boolean updateLikeCount(String opensourceId);
	
	public OpensourceVO selectRankLikeCount();

}
