package com.yz.webmagic;

import java.util.ArrayList;
import java.util.List;

import com.yz.utils.FileUtil;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

public class ProxyIPSpider implements PageProcessor {

	public Site getSite() {
		Site site = Site.me().setTimeOut(6000).setRetryTimes(3).setSleepTime(1000).setCharset("UTF-8").addHeader("Accept-Encoding", "/").setUserAgent(UserAgentUtils.radomUserAgent());		
		return site;
	}

	public void process(Page page) {
		page.addTargetRequests(page.getHtml().links().all());
		List<Request> list = page.getTargetRequests();
		for(Request str : list){
			FileUtil.WriteFile(str.getUrl(), "D:\\", "xcip.txt", "UTF-8");
		}
	    /*List<String> ipList = page.getHtml().xpath("//table[@id='ip_list']/tbody/tr").all();
		List<ProxyIp> result = new ArrayList();
	
		if(ipList != null && ipList.size() > 0){
			ipList.remove(0);
			for(String tmp : ipList){
				Html html = Html.create(tmp);
				ProxyIp proxyIp = new ProxyIp();
				String[] data = html.xpath("//body/text()").toString().trim().split("\\s+");
				
				proxyIp.setIp(data[0]);
				proxyIp.setPort(Integer.valueOf(data[1]));
				proxyIp.setAddr(html.xpath("//a/text()").toString());
				proxyIp.setType(data[3]);
				
				result.add(proxyIp);
			} 
		}
		page.putField("result", result);*/
	}

}