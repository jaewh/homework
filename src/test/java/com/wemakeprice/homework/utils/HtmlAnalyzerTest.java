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
        log.info(result.toString());
    }

    @Test
    public void testCalculateGroupingUnit() {
        HashMap<String, Object> answer = new HashMap<String, Object>();
        answer.put("quotient", "A0a1a2a3B4b5b6bccccccccDDDDDdd");
        answer.put("remainder", "");

        HashMap<String, Object> result =  htmlAnalyzer.calculateGroupingUnit("A0a1a2a3B4b5b6bccccccccDDDDDdd", 3);
        log.info(result.toString());
        Assert.assertEquals(answer, result);
    }

    @Test
    public void testParse() {
        String orginalHtml = "aABc012cabab34bcccDdcccD5DDDd6";
        String result = htmlAnalyzer.parse(orginalHtml);
        log.info(result);
        Assert.assertEquals("A0a1a2a3B4b5b6bccccccccDDDDDdd", result);
    }

    @Test
    public void testParseEmpty() {
        String orginalHtml = "";
        String result = htmlAnalyzer.parse(orginalHtml);
        log.info(result);
        Assert.assertEquals("", result);
    }

    @Test
    public void testMerge() {
        String sortedAlphabet = "AaaaBbbbccccccccDDDDDdd";
        String sortedNumber = "0123456";
        String result = htmlAnalyzer.merge(sortedAlphabet, sortedNumber);
        log.info(result);
        Assert.assertEquals("A0a1a2a3B4b5b6bccccccccDDDDDdd", result);
    }

    @Test
    public void testMergeAlphabetEmpty() {
        String sortedAlphabet = "";
        String sortedNumber = "0123456";
        String result = htmlAnalyzer.merge(sortedAlphabet, sortedNumber);
        log.info(result);
        Assert.assertEquals("0123456", result);
    }

    @Test
    public void testMergeNumberEmpty() {
        String sortedAlphabet = "AaaaBbbbccccccccDDDDDdd";
        String sortedNumber = "";
        String result = htmlAnalyzer.merge(sortedAlphabet, sortedNumber);
        log.info(result);
        Assert.assertEquals("AaaaBbbbccccccccDDDDDdd", result);
    }

    @Test
    public void testMergeBothEmpty() {
        String sortedAlphabet = "";
        String sortedNumber = "";
        String result = htmlAnalyzer.merge(sortedAlphabet, sortedNumber);
        log.info(result);
        Assert.assertEquals("", result);
    }
}