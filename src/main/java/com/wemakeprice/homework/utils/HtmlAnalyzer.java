package com.wemakeprice.homework.utils;

import com.wemakeprice.homework.domain.HtmlAnalyzeRequestT;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

@Component
public class HtmlAnalyzer {

    private static final String ALPHABET_PATTERN = "[^a-zA-Z]";
    private static final String NUMBER_PATTERN = "[^0-9]";

    /**
     * jsoup 에서 받아온 html 분석
     *
     * @param document            Jsoup html
     * @param htmlAnalyzeRequestT 요청 파라미터
     * @return 응담 데이터
     */
    public HashMap<String, Object> analyze(Document document, HtmlAnalyzeRequestT htmlAnalyzeRequestT) {
        StringBuilder originHtml = new StringBuilder();

        // 타입에 따른 텍스트 검색
        switch (htmlAnalyzeRequestT.getAnalyzeType()) {
            case WHLTXT: {
                originHtml.append(document.outerHtml());
                break;
            }
            case HTMLTGRMV: {
                originHtml.append(document.text());
                break;
            }
        }

        // 텍스트 파싱
        String mergedText = parse(originHtml.toString());
        if(StringUtils.isEmpty(mergedText)) {
            return null;
        }

        HashMap<String, Object> result = calculateGroupingUnit(mergedText, htmlAnalyzeRequestT.getOutputGroupUnit());
        return result;
    }

    public HashMap<String, Object> calculateGroupingUnit(String mergedText, int outputGroupUnit) {
        HashMap<String, Object> result = new HashMap<>();
        int quotientLength = mergedText.length() - mergedText.length() % outputGroupUnit;
        result.put("quotient", mergedText.substring(0, quotientLength));
        result.put("remainder", mergedText.substring(quotientLength));
        return  result;
    }

    /**
     * 추출된 텍스트 파싱하기
     *
     * @param originHtml
     * @return
     */
    public String parse(String originHtml) {
        // Empty 처리
        if (StringUtils.isEmpty(originHtml)) {
            return "";
        }

        String alphabet = originHtml.replaceAll(ALPHABET_PATTERN, "").trim();
        String number = originHtml.replaceAll(NUMBER_PATTERN, "").trim();

        // 알파멧 AaBbCc 순으로 정렬
        String sortedAlphabet = Arrays.stream(alphabet.split("")).sorted((a1, a2) -> {
            char c1 = a1.charAt(0);
            char c2 = a2.charAt(0);
            if (Character.toUpperCase(c1) != Character.toUpperCase(c2)) {
                return Character.toLowerCase(c1) - Character.toLowerCase(c2);
            } else {
                return c1 - c2;
            }
        }).collect(Collectors.joining());

        // 숫자 오름차순 정렬
        String sortedNumber = number.chars().sorted().collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

        // 알파벳, 숫자 병합
        return merge(sortedAlphabet, sortedNumber);
    }

    /**
     * @param sortedAlphabet
     * @param sortedNumber
     * @return
     */
    public String merge(String sortedAlphabet, String sortedNumber) {
        return "";
    }
}
