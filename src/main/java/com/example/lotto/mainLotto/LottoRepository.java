package com.example.lotto.mainLotto;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Y510p
 * @project lotto
 * @date 05.01.2020
 **/

public interface LottoRepository extends CrudRepository<LottoData, String> {
    LottoData findFirstByDate(String date);

    LottoData findFirstByDateAndNumbers(String date, String numbers);
}
