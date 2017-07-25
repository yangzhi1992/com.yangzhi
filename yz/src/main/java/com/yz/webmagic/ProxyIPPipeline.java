package com.yz.webmagic;

import java.util.List;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class ProxyIPPipeline implements Pipeline {
	
	/**
	 * 保存数据
	 */
	public void process(ResultItems resultItems, Task task) {
		List<ProxyIp> list = resultItems.get("result");
		
		if(list != null && list.size() > 0){
			for(ProxyIp proxyIp : list){
				System.out.println(proxyIp);
				//检测任务添加到队列中
			}
		}

	}

}