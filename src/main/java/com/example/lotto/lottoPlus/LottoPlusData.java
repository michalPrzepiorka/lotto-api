package com.example.lotto.lottoPlus;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Y510p
 * @project lotto
 * @date 17.01.2020
 **/
@Data
@Document(collection = "lotteryPlus")
public class LottoPlusData {
    @Id
    private String id;
    private String date;
    private String numbers;

    public LottoPlusData(String date, String numbers) {
        this.date = date;
        this.numbers = numbers;
    }

    public String getDate() {
        return date;
    }

    public String getNumbers() {
        return numbers;
    }
}
