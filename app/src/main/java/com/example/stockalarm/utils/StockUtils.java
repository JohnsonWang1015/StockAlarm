package com.example.stockalarm.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class StockUtils {
    public Stock getStock(String s){
        String source = "";
        String urlStr = "https://tw.stock.yahoo.com/q/q?s=" + s;

        try{
            URL url = new URL(urlStr);
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("User-agent", "IE/6.0");
            InputStream is = conn.getInputStream();
            Document doc = Jsoup.parse(is, "Big5", urlStr);
            String name = doc.select("a[href=/q/bc?s="+s+"]").text().trim().replace(s, "");
            source = doc.select("td[nowrap]").text().trim();
            System.out.println(name+"\n"+source);
            is.close();

            return parseStock(name, source);
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    private Stock parseStock(String name, String source){
        String[] data = source.split(" ");
        Stock stock = new Stock(name);
        stock.set時間(data[0]);
        stock.set成交(data[1]);
        stock.set買進(data[2]);
        stock.set賣出(data[3]);
        stock.set漲跌(data[4]);
        stock.set張數(data[5]);
        stock.set昨收(data[6]);
        stock.set開盤(data[7]);
        stock.set最高(data[8]);
        stock.set最低(data[9]);
        return stock;
    }
}
