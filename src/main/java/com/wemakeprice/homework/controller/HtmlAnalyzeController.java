package com.wemakeprice.homework.controller;

import com.wemakeprice.homework.domain.HtmlAnalyzeRequestT;
import com.wemakeprice.homework.service.HtmlAnalyzeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;

/**
 * Html 분석 controller
 *
 * @author rndlf104@gmail.com
 * @since 2022.06.11
 */
@Controller
@RequestMapping("/api/html")
@RequiredArgsConstructor
public class HtmlAnalyzeController {
    private final HtmlAnalyzeService htmlAnalyzeService;

    /**
     * Html 분석
     *
     * @param htmlAnalyzeRequestT : 요청 파라미터
     *                  url(String) : 분석 대상 url
     *                  analyzeType(AnalyzeType Enum) : 분석 타입 형태
     *                      > HTMLTGRMV : HTML 태그 제거
     *                      > WHLTXT : Text전체
     * @return 데이터 타입 HashMap
     *                  key : quotient value : 몫
     *                  key : remainder value : 나머지
     */
    @PostMapping("text")
    public ResponseEntity<HashMap<String, Object>> analyze(@Valid HtmlAnalyzeRequestT htmlAnalyzeRequestT) throws IOException {
        return ResponseEntity.ok(htmlAnalyzeService.analyze(htmlAnalyzeRequestT));
    }
}
