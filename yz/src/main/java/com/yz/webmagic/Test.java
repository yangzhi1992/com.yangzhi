package com.yz.webmagic;

import us.codecraft.webmagic.model.OOSpider;

public class Test {
	public static void main(String[] args) {
//        Spider.create(new GithubRepoPageProcessor()).addUrl("https://github.com/code4craft").thread(1).run();
		ProxyIPPipeline proxyIPPipeline = new ProxyIPPipeline();
		OOSpider.create(new ProxyIPSpider())
		.addUrl("http://tieba.baidu.com/f?kw=%B5%E7%D3%B0&fr=ala0&tpl=5").addPipeline(proxyIPPipeline)
		.thread(1)
		.run();
    }
}
