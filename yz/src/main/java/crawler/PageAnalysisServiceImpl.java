package crawler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.alibaba.fastjson.JSONObject;

/**
 * 用于页面解析是否table
 */
public class PageAnalysisServiceImpl{
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PageAnalysisServiceImpl.class);

    
    @Autowired
	ThreadPoolTaskExecutor commonExecutor;
    /**
     * 不用全局变量 
     * 利用线程独立性 保证不发布
     */
    public void htmlAnalysis() {
        //获取抓取页面html

//        while (true) {
            try {
            	final String url = (String) BaiduSearchImpl.pageQueue.take();

                //ThreadPoolTaskExecutor executor = Constant.getThreadPoolExecutor(commonExecutor);
/*
                executor.execute(new Runnable() {
                    public void run() {}
                });*/
            	

                if (url == null || !url.startsWith("http")) {
                    return;
                }
                //jsoup 获取document
                Document doc = null;
                try {
                    //                                        doc = Jsoup.connect(url).timeout(5000).get();
                    String result;
                    result = HttpUtil.get(url);
                    if (StringUtils.isNotBlank(result)) {
                        doc = Jsoup.parse(result);
                    } else {
                        return;
                    }
                } catch (Exception e) {
                    //解析页面异常 有可能是被屏蔽了 可以更换代理ip再次访问 或者链接拒绝
                    //但是这么设置会是全局的设置不好用
                    //                    logger.error("解析页面异常" + url, e);
                    doc = null;
                    return;
                    //                    if (ContainerUtils.checkIps.size() > 0) {
                    //
                    //                        IpModel model = ContainerUtils.checkIps.take();
                    //                        System.setProperty("http.maxRedirects", "50");
                    //                        System.getProperties().setProperty("proxySet", "true");
                    //                        System.getProperties().setProperty("http.proxyHost", model.getIp());
                    //                        System.getProperties().setProperty("http.proxyPort", model.getPort() + "");
                    //                        logger.info("使用代理ip" + model.getIp() + "-port:" + model.getPort());
                    //                        ContainerUtils.checkIps.offer(model.getIp() + ":" + model.getPort(), model);
                    //                    }
                }

                final Document document = doc;

                String loca = document.location();
                if (StringUtils.isBlank(loca)) {
                    loca = url;
                }
                final String location = loca;
                /*ThreadPoolTaskExecutor executor1 = Constant
                    .getThreadPoolExecutor(commonExecutor);*/
                //另外启动线程判断是否 有用的url再进行抓取
                /*executor1.execute(new Runnable() {
                    public void run() {
                        urlAnalysis(location, document);
                    }
                });*/
                /*ThreadPoolTaskExecutor executor2 = Constant
                    .getThreadPoolExecutor(commonExecutor);
                //解析判断table标签
                executor2.execute(new Runnable() {
                    public void run() {
                        tableAnalysis(document);
                    }
                });*/
                tableAnalysis(document);
                /*ThreadPoolTaskExecutor executor3 = Constant
                    .getThreadPoolExecutor(commonExecutor);
                //另起线程判断是否是文本格式的
                executor3.execute(new Runnable() {
                    public void run() {
                        textAnalysis(document);
                    }
                });*/
                textAnalysis(document);
            
            } catch (Exception e) {
                logger.error("解析页面出现异常：", e);
            }
//        }

    
    }

    /**
     * 这里是关键 可以深度挖掘代理ip
     * @param document
     */
    public void urlAnalysis(String location, Document document) {
        //现获取所有的a标签
        Elements elements = document.select("a");
        //判断是否是翻页的a标签
        for (Element element : elements) {
            String link = element.attr("href");
            String text = element.html();
            //已经访问过的链接不再访问 或者是.开头的 或者不是html结尾的
            /*String urls = cao.hget(Constant.NOT_ANALYSIS_URLS, link);
            if (StringUtils.isNotBlank(urls) || link.startsWith(".") || !link.endsWith("html")) {
                continue;
            }*/
            int day = Calendar.getInstance().get(Calendar.DATE);
            //1.一般翻页的a标签都是显示 1 然后 href里面有指向这个1的链接 可以从这下手
            //2. 还有一种是页面没有table 但是在其他链接里面还有这种带table的页面
            //宁可多访问 也不要漏掉一个页面
            if ((StringUtils.isNumeric(text) && link.indexOf(text) >= 0)
                || StringUtils.indexOfIgnoreCase(text, "代理") >= 0) {
                //如果有显示年月日的
                if (text.contains("年") && text.contains("月") && text.contains("日")) {
                    String date = text.substring(0, text.indexOf("日") + 1);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
                    try {
                        if (sdf.parse(date).compareTo(new Date()) != 0) {
                            continue;
                        }
                    } catch (Exception e) {
                        //                        logger.error(text);
                        //发生转换异常就跳过
                        continue;
                    }
                    //或者显示月日的
                } else if (text.contains("月") && text.contains("日")) {
                    String date = text.substring(0, text.indexOf("日") + 1);
                    SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
                    try {
                        if (sdf.parse(date).compareTo(new Date()) != 0) {
                            continue;
                        }
                    } catch (Exception e) {
                        //发生转换异常就跳过
                        continue;
                    }
                }

                //翻页的 只显示5页
                //                logger.info(">>" + location + link + "<<" + ">>" + text + "<<");
                if (StringUtils.isBlank(text) || link.equals("/") || link.indexOf("java") >= 0
                    || (StringUtils.isNumeric(text) && Integer.valueOf(text) > 30)) {
                    //如果日期包含有今天 就进行分析
                    if (text.indexOf(day + "") < 0) {

                        continue;
                    }
                }

                //jsoup 获取localtin 可能带有参数 所以要去掉参数保持原来的location
                //比如可能会变成www.baidu.com/ww/cn
                //要还原成www.baidu.com/
                String temp = location;
                int index = -1;
                if (temp.startsWith("http://")) {
                    temp = temp.substring("http://".length(), temp.length());
                    index = "http://".length();
                } else if (temp.startsWith("https://")) {
                    temp = temp.substring("https://".length(), temp.length());
                    index = "https://".length();
                }
                if (temp.indexOf("/") != temp.lastIndexOf("/")) {
                    location = location.substring(0, index + temp.indexOf("/"));
                }

                //                    logger.info(location + link);
                //url 直接只http开头的 就直接访问 不用添加locaiton
                //                logger.info(location + "---" + link);
                if (link.indexOf("http") == 0) {
                    BaiduSearchImpl.pageQueue.add(link);
                } else {
                    if (location.indexOf(link) >= 0) {
                        BaiduSearchImpl.pageQueue.add(location);
                    } else {
                        if (StringUtils.isBlank(location)) {
                            continue;
                        }
                        BaiduSearchImpl.pageQueue.add(location + link);
                    }
                }

            }
        }

    }
    
    /**
     * 有的ip是1以文本方式进行的 例如12.12.12.12:8080@.....
     * @param document
     */
    public void textAnalysis(Document document) {
        String text = document.text();
        if (StringUtils.isBlank(text)) {
            return;
        }
        List<String> list = IpUtils.isIpPort(text);
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        for (String temp : list) {
            if (StringUtils.isNotBlank(temp) && temp.split(":").length > 0) {

                try {

                    IpModel model = new IpModel();
                    model.setIp(temp.split(":")[0]);
                    model.setPort(Integer.valueOf(temp.split(":")[1]));
                    System.out.println(JSONObject.toJSONString(model));
                } catch (Exception e) {
                    logger.error("解析IP PORT异常", e);
                }
            }
        }
    }

    /**
     * 判断是否有table
     * @param document
     */
    public void tableAnalysis(Document document) {
        //获取table的标签
        Elements element = document.select("table");
        //有的页面没有table 但是文本格式的这里要进行判断
        //判断是否table是否有ip port 等关键字信息
        final String text = element.text();
        //解析table
        if (StringUtils.isNotBlank(text) && judgeTableHtml(text)) {
            //                        logger.info("*******************table*************");
            for (Element element1 : element) {
                try {

                    int ipPos = -1;
                    int portPos = -1;

                    Elements elementTr = element1.select("tr");
                    if (ipPos == -1 && portPos == -1) {
                        Map<String, Integer> map = judgeIpPortPos(elementTr);
                        ipPos = map.get("ip") == null ? -1 : map.get("ip");
                        portPos = map.get("port") == null ? -1 : map.get("port");
                    }
                    //如果下标还是-1 那么就说明这个table没有想要的东西
                    if (ipPos == -1 || portPos == -1) {
                        continue;
                    }

                    for (Element element2 : elementTr) {
                        //获取TD
                        Elements elementsTd = element2.select("td");
                        try {
                            String ip = null;
                            String port = null;
                            //如果端口号和ip在一个td里面说明格式不同
                            if (ipPos != portPos) {

                                ip = elementsTd.get(ipPos).html();
                                port = elementsTd.get(portPos).html();
                            } else {
                                ip = elementsTd.get(ipPos).html().split(":")[0];
                                port = elementsTd.get(portPos).html().split(":")[1];
                            }
                            //放进队列
                            if (IpUtils.isIP(ip) && StringUtils.isNumeric(port)) {
                                IpModel model = new IpModel();
                                model.setIp(ip);
                                model.setPort(Integer.valueOf(port));
                                System.out.println(JSONObject.toJSONString(model));
                            }
                        } catch (Exception e) {
                            //                                        logger.error("这个td不包含有效信息，继续查找下一个td：" + elementsTd.text());
                            continue;
                        }

                        //                        for (Element td : elementsTd) {
                        //                            String ip = td
                        //                            String port = td.get(portPos).html();
                        //                            IpModel model = new IpModel();
                        //                            model.setIp(ip);
                        //                            model.setPort(Integer.valueOf(port));
                        //                            ContainerUtils.addQueue(model);
                        //                        }
                    }
                } catch (Exception e) {
                    continue;
                }

            }

        }
    }

    /**
     * 判断html是否包含有效的table
     * @param text
     * @return
     */
    private boolean judgeTableHtml(String text) {

        return StringUtils.indexOfIgnoreCase(text, "ip") >= 0
               || StringUtils.indexOfIgnoreCase(text, "port") >= 0
               || StringUtils.indexOfIgnoreCase(text, "端口") >= 0
               || StringUtils.indexOfIgnoreCase(text, "last") >= 0
               || StringUtils.indexOfIgnoreCase(text, "时间") >= 0;

    }

    /**
     * 判断ip和端口的位置
     * @param elementTr
     * @return
     */
    private Map<String, Integer> judgeIpPortPos(Elements elementTr) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        w: for (Element tr : elementTr) {
            Elements elements = tr.select("td");
            for (int i = 0; i < elements.size(); i++) {
                String textTh = elements.get(i).html();
                if (!textTh.contains(":")) {

                    if (IpUtils.isIP(textTh)) {
                        map.put("ip", i);

                    } else if (StringUtils.isNumeric(textTh)) {
                        map.put("port", i);
                    }
                    if (map.size() == 2) {
                        break w;
                    }
                }
            }
        }

        return map;
    }
}
