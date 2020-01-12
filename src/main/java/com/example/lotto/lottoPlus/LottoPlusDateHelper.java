package com.example.lotto.lottoPlus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Y510p
 * @project lotto
 * @date 17.01.2020
 **/

public class LottoPlusDateHelper {
    private String date;
    private List<Integer> numbers;

    public LottoPlusDateHelper(String date, List<Integer> numbers) {
        this.date = date;
        this.numbers = numbers;
    }

    public LottoPlusDateHelper(String date){
        this(date, new ArrayList<>());
    }

    public String getDate() {
        return date;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
