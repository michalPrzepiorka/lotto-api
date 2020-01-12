package com.example.lotto.lottoPlus;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Y510p
 * @project lotto
 * @date 17.01.2020
 **/

public interface LottoPlusRepository extends CrudRepository<LottoPlusData, String> {
    LottoPlusData findFirstByDate(String date);

    LottoPlusData findFirstByDateAndNumbers(String date, String numbers);
}
