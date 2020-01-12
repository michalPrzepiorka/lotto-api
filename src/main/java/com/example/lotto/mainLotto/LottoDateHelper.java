package com.example.lotto.mainLotto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Y510p
 * @project lotto
 * @date 12.01.2020
 **/

public class LottoDateHelper {
    private String date;
    private List<Integer> numbers;

    public LottoDateHelper(String date, List<Integer> numbers) {
        this.date = date;
        this.numbers = numbers;
    }

    public LottoDateHelper(String date) {
        this(date, new ArrayList<>());
    }

    public String getDate() {
        return date;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
