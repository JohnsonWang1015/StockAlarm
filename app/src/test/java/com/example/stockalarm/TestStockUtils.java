package com.example.stockalarm;

import com.example.stockalarm.utils.Stock;
import com.example.stockalarm.utils.StockUtils;

public class TestStockUtils {
    public static void main(String[] args){
        StockUtils utils = new StockUtils();
        Stock stock = utils.getStock("2317");
        System.out.println(stock);
    }
}
