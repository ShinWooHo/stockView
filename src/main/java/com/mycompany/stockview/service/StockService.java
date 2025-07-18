package com.mycompany.stockview.service;

import com.mycompany.stockview.dto.StockItemListDto;
import com.mycompany.stockview.entity.Stock;
import com.mycompany.stockview.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    public List<StockItemListDto> getStockItemList() {

        List<Stock> stockList = stockRepository.findAll();

        // dto변환 후 리턴
        return stockList.stream()
                .map(StockItemListDto::fromEntity)
                .toList();
    }


    public List<StockItemListDto> searchItems(String market, String searchType, String industry, String fisc_month, String company_name, String region) {

        return stockRepository.findByConditions(market, searchType, industry, fisc_month, company_name, region);
    }

    public List<StockItemListDto> getStockItemList(String fisc_month, int offset, int size) {
        List<Stock> stockList = stockRepository.searchWithPaging(fisc_month, offset, size);
        return stockList.stream()
                .map(StockItemListDto::fromEntity)
                .collect(Collectors.toList());
    }

    public int countSearchResult(String fisc_month) {
        return stockRepository.countSearchResult(fisc_month);
    }
}
