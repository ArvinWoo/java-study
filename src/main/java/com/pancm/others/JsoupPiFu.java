package com.pancm.others;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 
* @Title: JsoupHtml
* @Description: 爬虫测试
* 使用 jsoup 
* @Version:1.0.0  
* @author pancm
* @date 2018年3月21日
 */
public class JsoupPiFu {

    public static String html = "http://news.4399.com/gonglue/wzlm/pifu/";  //获取皮肤图片


    //建立连接，获取HTMLDom
    @SuppressWarnings("null")
    public static  String getHtml (String html){
        //CloseableHttpClient 会默认创建一个大小为5的连接池
        CloseableHttpClient createDefalt = HttpClients.createDefault();
        //创建http请求
        HttpGet get = new HttpGet(html);
        String rHtml = "";
        CloseableHttpResponse response = null;
        try {
            response = createDefalt.execute(get);
            if(response.getStatusLine().getStatusCode() == 200){//请求成功
                rHtml = EntityUtils.toString(response.getEntity(),"GB2312");
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }finally {
            try {
                if(null == response){
                    response.close();
                }
                createDefalt.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return  rHtml;
    }

    //解析Dom
    private static void parsHtml(String htmlDom) {
        //使用Jsoup解析获取到的html
        Document document = Jsoup.parse(htmlDom);
        Element list = document.getElementById("hreo_list");
        Elements select = list.select("img");
        System.out.println(select.size());
        for(Element element : select){
            String url = element.attr("lz_src");
            String fileName = element.attr("alt");
            //下载图片
            downloadFile(url,fileName);
        }

    }

    protected static String path = "C://Users//86182//Desktop//pifu";//下载的目标路径

    //下载附件图片
    private static void downloadFile(String imgUrl, String fileName) {
        //判断目标文件是否存在
        File files = new File(path);
        if(!files.exists()){
            files.mkdirs();
        }

        InputStream is;
        FileOutputStream out;
        try {
            URL url = new URL(imgUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            is = connection.getInputStream();
            //获取文件后缀名
            String stuff = imgUrl.substring(imgUrl.lastIndexOf("."),imgUrl.length());
            //创建文件
            File fileImg = new File(path + File.separator + fileName + stuff);
            out = new FileOutputStream(fileImg);
            int i = 0;
            // is.read() : 从输入流中读取数据的下一个字节。返回 0 到 255 范围内的 int 字节值。如果因为已经到达流末尾而没有可用的字节，则返回值 -1
            while((i = is.read()) != -1){
                out.write(i);
            }
            is.close();
            out.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        //获取网页Dom
        String htmlDom = getHtml(JsoupPiFu.html);
//        System.out.println("=====================");
//        System.out.println(htmlDom);
//        System.out.println("=====================");
        parsHtml(htmlDom);
    }


}