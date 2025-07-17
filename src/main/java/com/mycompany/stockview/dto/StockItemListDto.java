package com.mycompany.stockview.dto;

import com.mycompany.stockview.entity.Stock;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StockItemListDto {

    private Long itemId;

    private String comp_name;   // 회사명
    private String industry;    // 업종
    private String main_prod;   // 주요제품
    private Date list_date;     // 상장일
    private String fisc_month;  // 결산월
    private String ceo_name;    // 대표자명
    private String website;     // 홈페이지주소
    private String region;      // 지역

    // dto -> entity 변환
    public static Stock toEntity(StockItemListDto stockItemListDto) {
        return Stock.builder()
                .itemId(stockItemListDto.getItemId())
                .comp_name(stockItemListDto.getComp_name())
                .industry(stockItemListDto.getIndustry())
                .main_prod(stockItemListDto.getMain_prod())
                .list_date(stockItemListDto.getList_date())
                .fisc_month(stockItemListDto.getFisc_month())
                .ceo_name(stockItemListDto.getCeo_name())
                .website(stockItemListDto.getWebsite())
                .region(stockItemListDto.getRegion())
                .build();
    }
    
    // Entity -> Dto 변환
    public static StockItemListDto fromEntity(Stock stock) {
        return StockItemListDto.builder()
                .itemId(stock.getItemId())
                .comp_name(stock.getComp_name())
                .industry(stock.getIndustry())
                .main_prod(stock.getMain_prod())
                .list_date(stock.getList_date())
                .fisc_month(stock.getFisc_month())
                .ceo_name(stock.getCeo_name())
                .website(stock.getWebsite())
                .region(stock.getRegion())
                .build();
    }

}
