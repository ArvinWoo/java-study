package com.pancm.others;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.pancm.others.GeccoTest;

/**
 * @Description: 运行完GeccoTest.java 根据@PipelineName 来这里
 * @Author: ArvinWoo
 * @Date: 2020/11/26 14:27
 * @ClassName: BlogPipelines
 **/
@PipelineName(value="blogPipelines")
public class BlogPipelines implements Pipeline<GeccoTest> {

    /**
     * 将抓取到的内容进行处理  这里是打印在控制台
     */
    @Override
    public void process(GeccoTest blog) {
        System.out.println(blog.getContent());
    }

}