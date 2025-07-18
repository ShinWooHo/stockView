package com.mycompany.stockview.controller;

import com.mycompany.stockview.dto.StockItemListDto;
import com.mycompany.stockview.repository.StockRepository;
import com.mycompany.stockview.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.management.monitor.StringMonitor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/stock")
public class StockController {

    private final StockService stockService;
    private final StockRepository stockRepository;

    @GetMapping("/list")
    public String list(Model model) {
        List<StockItemListDto> itemList = stockService.getStockItemList();

        model.addAttribute("itemList", itemList);

        return "stock/list";
    }

    @GetMapping("/search")
    @ResponseBody
    public Map<String, Object> search(
            @RequestParam(required = false) String market,
            @RequestParam(required = false) String searchType,
            @RequestParam(required = false) String industry,
            @RequestParam(required = false) String fisc_month,
            @RequestParam(required = false) String company_name,
            @RequestParam(required = false) String region,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        int offset = (page - 1) * size;

        List<StockItemListDto> items = stockService.getStockItemList(fisc_month, offset, size);
        int totalCount = stockService.countSearchResult(fisc_month);
        int totalPages = (int) Math.ceil((double) totalCount / size);

        Map<String, Object> result = new HashMap<>();
        result.put("items", items);
        result.put("totalPages", totalPages);

        return result;
    }
}
