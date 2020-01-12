package com.example.lotto.mainLotto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Y510p
 * @project lotto
 * @date 07.01.2020
 **/

@Data
@Document(collection = "lottery")
public class LottoData {
    @Id
    private String id;
    private String date;
    private String numbers;

    public LottoData(String date, String numbers) {
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
