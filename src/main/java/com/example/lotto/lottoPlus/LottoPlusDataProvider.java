package com.example.lotto.lottoPlus;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Y510p
 * @project lotto
 * @date 17.01.2020
 **/

public class LottoPlusDataProvider {
    public static List<LottoPlusData> getLottoPlusData() {
        final Document document;
        List<LottoPlusData> lottoPlusDataList = new LinkedList<>();

        try {
            document = Jsoup.connect("http://megalotto.pl/wyniki/lotto-plus/100-ostatnich-losowan").get();

            Element table = document.getElementsByClass("lista_ostatnich_losowan").get(0);
            Elements rows = table.getElementsByTag("ul");

            for (Element element : rows) {
                Elements dateOfTheDraw = element.getElementsByClass("date_in_list");
                Elements lotteryNumbers = element.getElementsByClass("numbers_in_list ");

                lottoPlusDataList.add(new LottoPlusData(dateOfTheDraw.text(), lotteryNumbers.text()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lottoPlusDataList;
    }
}
