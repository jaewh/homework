package com.wemakeprice.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Slf4j
public class HtmlAnalyzeControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Test
    public void analyzeTest() throws Exception {
        this.mockMvc.perform(post("http://localhost:8080/api/html/text")
                        .param("url", "https://front.wemakeprice.com/main")
                        .param("analyzeType", "WHLTXT")
                        .param("outputGroupUnit", "3")
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void 타입이_HTML_태그_제거_인경우() throws Exception {
        this.mockMvc.perform(post("http://localhost:8080/api/html/text")
                        .param("url", "https://front.wemakeprice.com/main")
                        .param("analyzeType", "HTMLTGRMV")
                        .param("outputGroupUnit", "3")
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void URL_비어있는_경우() throws Exception {
        this.mockMvc.perform(post("http://localhost:8080/api/html/text")
                        .param("url", "")
                        .param("analyzeType", "WHLTXT")
                        .param("outputGroupUnit", "4")
                )
                .andExpect(status().is5xxServerError())
                .andDo(print());
    }

    @Test
    public void URL_형식이_아닌_경우() throws Exception {
        this.mockMvc.perform(post("http://localhost:8080/api/html/text")
                        .param("url", "abcdefghijklmnop")
                        .param("analyzeType", "WHLTXT")
                        .param("outputGroupUnit", "4")
                )
                .andExpect(status().is5xxServerError())
                .andDo(print());
    }

    @Test
    public void HTTP가_아닌_URL_입력인_경우() throws Exception {
        this.mockMvc.perform(post("http://localhost:8080/api/html/text")
                        .param("url", "ftp://")
                        .param("analyzeType", "WHLTXT")
                        .param("outputGroupUnit", "4")
                )
                .andExpect(status().is5xxServerError())
                .andDo(print());
    }

    @Test
    public void HTTP가_없는_URL_입력인_경우() throws Exception {
        this.mockMvc.perform(post("http://localhost:8080/api/html/text")
                        .param("url", "front.wemakeprice.com/main")
                        .param("analyzeType", "WHLTXT")
                        .param("outputGroupUnit", "4")
                )
                .andExpect(status().is5xxServerError())
                .andDo(print());
    }

    @Test
    public void 출력_묶음단위가_0이하_경우() throws Exception {
        this.mockMvc.perform(post("http://localhost:8080/api/html/text")
                        .param("url", "https://front.wemakeprice.com/main")
                        .param("analyzeType", "WHLTXT")
                        .param("outputGroupUnit", "-1")
                )
                .andExpect(status().is5xxServerError())
                .andDo(print());
    }
}