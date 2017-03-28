package com.meister.reply.vo;

import com.meister.commom.pager.Pager;
import com.meister.commom.pager.PagerFactory;

public class ReplySearchVO {
	Pager pager;

	public Pager getPager() {
		if( pager == null) {
			/*한페이지에 10개씩 ,한페이지그룹은 10개*/
			pager = PagerFactory.getPager(Pager.ORACLE, 10, 10);
		}
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

}
