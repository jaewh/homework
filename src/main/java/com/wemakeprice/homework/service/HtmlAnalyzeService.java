package com.wemakeprice.homework.service;

import com.wemakeprice.homework.domain.HtmlAnalyzeRequestT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

/**
 * Html 분석 Service
 *
 * @author rndlf104@gmail.com
 * @since 2022.06.11
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class HtmlAnalyzeService {

    private static final int TIMEOUT = 5000;

    /**
     * html 분석 서비스
     *
     * @param htmlAnalyzeRequestT
     * @return
     * @throws IOException
     */
    public HashMap<String, Object> analyze(HtmlAnalyzeRequestT htmlAnalyzeRequestT) throws IOException {
        log.info("analyze : {}", htmlAnalyzeRequestT);

        // url을 통한 데이터 검증
        Document document = Jsoup.connect(htmlAnalyzeRequestT.getUrl()).timeout(TIMEOUT).get();

        // 가져온 문서 파싱
        HashMap<String, Object> result = new HashMap<>();
        return result;
    }
}
