package com.meister.opensource.service;

import com.meister.opensource.biz.OpensourceBiz;
import com.meister.opensource.biz.OpensourceBizImpl;
import com.meister.opensource.vo.OpensourceVO;

public class OpensourceServiceImpl implements OpensourceService {
	
	private OpensourceBiz opensourceBiz;
	
	public OpensourceServiceImpl() {
		opensourceBiz = new OpensourceBizImpl();
	}

	@Override
	public OpensourceVO getOneOpensource(String opensourceId) {
		return opensourceBiz.getOneOpensource(opensourceId);
	}

	@Override
	public boolean addOneOpensource(String opensourceId) {
		return opensourceBiz.addOneOpensource(opensourceId);
	}

	@Override
	public boolean updateLikeCount(String opensourceId) {
		return opensourceBiz.updateLikeCount(opensourceId);
	}

	@Override
	public OpensourceVO selectRankLikeCount() {
		return opensourceBiz.selectRankLikeCount();
	}
	
}
