package crawler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.LoggerFactory;

public class BaiduSearchImpl{

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BaiduSearchImpl.class);
    
    public static BlockingQueue pageQueue = new LinkedBlockingQueue(5000);

    public void search(final String keyWord, final int page) {
        final int pageSize = 10;
        //百度搜索结果每页大小为10，pn参数代表的不是页数，而是返回结果的开始数
        //如获取第一页则pn=0，第二页则pn=10，第三页则pn=20，以此类推，抽象出模式：(page-1)*pageSize
        int i = 1;
        while (i <= page) {
            final String url = "http://www.baidu.com/s?pn=" + (i - 1) * pageSize + "&wd=" + keyWord;
            Document document = null;
            try {
            	document = Jsoup.parse(HttpUtil.get(url));
            	dealBaiduSearchResult(document);
            } catch (Exception e) {
                logger.error("搜索出错:" + url, e);
            }
            i++;
            
            
        }
    }

    private void dealBaiduSearchResult(Document document) {
    	//System.out.println(document.getAllElements());
        //先获取content一层  打开浏览器点击F12看每条数据的源代码 #http://www.open-open.com/jsoup/
        Elements elements = document.select("h3>a");
        //解析结果层 获取url
        for (int i = 0; i < elements.size(); i++) {
            Element elementA = elements.get(i);

            if (StringUtils.isNotBlank(elementA.text()) && elementA.text().indexOf("百度") < 0) {
                final String href = elementA.attr("href");
                if (StringUtils.isBlank(href)) {
                    continue;
                }
                pageQueue.add(href);
            }
        }
    }
}
