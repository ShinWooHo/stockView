package com.mycompany.stockview.controller;

import com.mycompany.stockview.dto.StockItemListDto;
import com.mycompany.stockview.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/stock")
public class StockController {

    private final StockService stockService;

    @GetMapping("/list")
    public String list(Model model) {
        List<StockItemListDto> itemList = stockService.getStockItemList();

        model.addAttribute("itemList", itemList);

        return "stock/list";
    }

    @GetMapping("/search")
    @ResponseBody
    public List<StockItemListDto> search(
            @RequestParam(required = false) String market,
            @RequestParam(required = false) String searchType,
            @RequestParam(required = false) String industry,
            @RequestParam(required = false) String fisc_month,
            @RequestParam(required = false) String company_name,
            @RequestParam(required = false) String region
    ) {
        return stockService.searchItems(market, searchType, industry, fisc_month, company_name, region);
    }
}
