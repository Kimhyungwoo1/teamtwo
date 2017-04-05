package com.meister.opensource.biz;

import com.meister.opensource.dao.OpensourceDao;
import com.meister.opensource.dao.OpensourceDaoImpl;
import com.meister.opensource.vo.OpensourceVO;

public class OpensourceBizImpl implements OpensourceBiz {
	
	private OpensourceDao opensourceDao;
	
	public OpensourceBizImpl() {
		opensourceDao = new OpensourceDaoImpl();
	}

	@Override
	public OpensourceVO getOneOpensource(String opensourceId) {
		return opensourceDao.selectOneOpensource(opensourceId);
	}

	@Override
	public boolean addOneOpensource(String opensourceId) {
		return opensourceDao.insertOneOpensource(opensourceId) > 0;
	}

	@Override
	public boolean updateLikeCount(String opensourceId) {
		return opensourceDao.updateOneOpensource(opensourceId) > 0;
	}

	@Override
	public OpensourceVO selectRankLikeCount() {
		return opensourceDao.selectRankLikeCount();
	}

}
