package com.patrickstar.blog.utils;
/*
 * @PackageName: com.patrickstar.blog.utils
 * @ClassName: MarkdownUtils
 * @Description:markdown工具类
 * @Author: PatrickStaR
 * @Date: 11/8/2019 3:49 PM
 */


import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TableBlock;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.*;

public class MarkdownUtils {

    /**
     * markdown格式转化为HTML格式
     * @param markdown markdown文本
    **/
    public static String markdownToHtml(String markdown){
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }

    /**
     * 增加扩展[标题锚点，表格生成]
     * Markdown转换成Html
     * @param markdown markdown文本
    **/
    public static String markdownToHtmlExtensions(String markdown){
        //h标题生成id
        Set<Extension> headingAnchorExtensions = Collections.singleton(HeadingAnchorExtension.create());
        //转换table的html
        List<Extension> tableExtension = Arrays.asList(TablesExtension.create());
        Parser parser = Parser.builder()
                .extensions(tableExtension)
                .build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder()
                .extensions(headingAnchorExtensions)
                .extensions(tableExtension)
                .attributeProviderFactory(new AttributeProviderFactory() {
                    @Override
                    public AttributeProvider create(AttributeProviderContext attributeProviderContext) {
                        return new CustomAttributeProvider();
                    }
                })
                .build();
        return renderer.render(document);
    }

    /**
     * 处理标签的属性
     **/
    public static class CustomAttributeProvider implements AttributeProvider{

        @Override
        public void setAttributes(Node node, String s, Map<String, String> map) {
            //改变a标签的target属性为_blank
            if(node instanceof Link){
                map.put("target", "_blank");
            }
            if(node instanceof TableBlock){
                map.put("class", "ui celled table");
            }
        }
    }

    /**
     * 测试
     **/
    public static void main(String[] args){
        String table = "| table | hi | 哈哈哈   |\n" +
                       "| --------- | --------- | --------     |\n" +
                       "| 斯维尔多 | 士大夫 | f啊     |\n" +
                       "| 阿斯蒂芬 | 范德萨 | 支持v小苏打     |\n" +
                       "\n";
        String a = "[im handsome]";
        System.out.println(markdownToHtmlExtensions(table));

    }
}
