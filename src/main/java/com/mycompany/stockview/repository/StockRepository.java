package com.mycompany.stockview.repository;

import com.mycompany.stockview.dto.StockItemListDto;
import com.mycompany.stockview.entity.Stock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface StockRepository {
    // 증시 목록 조회
    List<Stock> findAll();

    List<StockItemListDto> findByConditions(@Param("market") String market, @Param("searchType") String searchType, @Param("industry") String industry, @Param("fisc_month") String fisc_month, @Param("company_name") String company_name, @Param("region") String region);
}
