package com.pancm.others;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.spider.SpiderBean;
import com.geccocrawler.gecco.request.HttpRequest;

/**
 * @Description: TODO
 * @Author: ArvinWoo
 * @Date: 2020/11/26 14:13
 * @ClassName: gecco
 * Blog实体类，运行主函数从这里开始解析
 * matchUrl:要抓包的目标地址
 * pipelines:跳转到下个pipelines
 **/
@Gecco(matchUrl="https://www.cnblogs.com/boychen/p/7226831.html",pipelines="blogPipelines")
public class GeccoTest implements SpiderBean {
    /**
     * 向指定URL发送GET方法的请求
     */
    @Request
    private HttpRequest request;

    /**
     * 抓去这个路径下所有的内容
     */
    @HtmlField(cssPath = "body div#cnblogs_post_body")
    private String content;

    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}

/*
 * @Description: 测试方法
 * @Author: ArvinWoo
 * @Date: 2020/11/26 14:39
 * @MethodName:
 * @Param: * @param null
 * @Return:
**/
class Test{
    public static void main(String[] args) {
        GeccoEngine.create()
                //工程的包路径
                .classpath("com.pancm.others")
                //开始抓取的页面地址
                .start("https://www.cnblogs.com/boychen/p/7226831.html")
                //开启几个爬虫线程
                .thread(10)
                //单个爬虫每次抓取完一个请求后的间隔时间
                .interval(5)
                //使用pc端userAgent
                .mobile(false)
                //开始运行
                .run();
    }
}

