package com.mycompany.stockview.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

    private Long itemId;            // 주식일련번호
    private String stad_code;       // 표준코드
    private String item_code;       // 종목코드
    private String comp_name;       // 회사명
    private Date founded;           // 설립일
    private Date list_date;          // 상장일
    private String fisc_month;      // 결산월
    private String ceo_name;        // 대표자명
    private String industry;        // 업종
    private String market_type;     // 시장구분
    private String region;          // 지역
    private String adderss;         // 주소
    private String main_prod;       // 주요제품
    private String website;         // 홈페이지주소
    private Long capital;           // 자본금
    private int emps_count;         // 종업원수
    private String phone_num;       // 전화번호
    private String old_comp_name;   // 변경 전 상호명
    private Date rgst_date;         // 등록일시
    private Date last_date;         // 마지막 수정일시
    private int rgst_userId;        // 등록자일련번호
    private String rgst_name;       // 등록자 이름
    private String rgst_disp;       // 등록자 상세 이름
    private int auth_userId;        // 작성자 일련번호
    private String auth_name;       // 작성자 이름
    private String auth_disp;       // 작성자 상세 이름
}
