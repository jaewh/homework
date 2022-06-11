package com.wemakeprice.homework.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;

/**
 * Html 분석 요청 파라미터 모델
 *
 * @author rndlf104@gmail.com
 * @since 2022.06.11
 */
@Getter
@Setter
@ToString
public class HtmlAnalyzeRequestT {

    @URL
    private String url;

    private AnalyzeType analyzeType;

    @Min(1)
    private int outputGroupUnit;
}
