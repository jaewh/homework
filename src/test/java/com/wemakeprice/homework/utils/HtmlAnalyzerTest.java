package com.wemakeprice.homework.utils;

import com.wemakeprice.homework.domain.AnalyzeType;
import com.wemakeprice.homework.domain.HtmlAnalyzeRequestT;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class HtmlAnalyzerTest {

    @Autowired
    private HtmlAnalyzer htmlAnalyzer;

    @Test
    public void testAnalyze() throws Exception {
        Document document = Jsoup.connect("https://front.wemakeprice.com/main").timeout(5000).get();
        HtmlAnalyzeRequestT htmlAnalyzeRequestT = new HtmlAnalyzeRequestT();
        htmlAnalyzeRequestT.setUrl("https://front.wemakeprice.com/main");
        htmlAnalyzeRequestT.setAnalyzeType(AnalyzeType.WHLTXT);
        htmlAnalyzeRequestT.setOutputGroupUnit(4);

        HashMap<String, Object> result = htmlAnalyzer.analyze(document, htmlAnalyzeRequestT);
    }

    @Test
    public void testCalculateGroupingUnit() {
    }

    @Test
    public void testParse() {
    }

    @Test
    public void testMerge() {
    }
}