package com.example.lotto;

import com.example.lotto.lottoPlus.LottoPlusData;
import com.example.lotto.lottoPlus.LottoPlusDataProvider;
import com.example.lotto.lottoPlus.LottoPlusRepository;
import com.example.lotto.mainLotto.LottoData;
import com.example.lotto.mainLotto.LottoDataProvider;
import com.example.lotto.mainLotto.LottoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Y510p
 * @project lotto
 * @date 09.01.2020
 **/

@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private LottoRepository repository;
    @Autowired
    private LottoPlusRepository repositoryPlus;

    @Scheduled(fixedRate = 3600000)
    public void reportCurrentTime() {
        LottoDataProvider.getLottoData()
                .stream()
                .map(data -> new LottoData(data.getDate(), data.getNumbers()))
                .forEach(lottery -> {
                    boolean add = false;
                    if (repository.findFirstByDateAndNumbers(lottery.getDate(), lottery.getNumbers()) == null) {
                        repository.save(lottery);
                        add = true;
                    }
                    log.info("Did we update the database: " + add);
                });
        LottoPlusDataProvider.getLottoPlusData()
                .stream()
                .map(data -> new LottoPlusData(data.getDate(), data.getNumbers()))
                .forEach(lottery -> {
                    boolean add = false;
                    if (repositoryPlus.findFirstByDateAndNumbers(lottery.getDate(), lottery.getNumbers()) == null) {
                        repositoryPlus.save(lottery);
                        add = true;
                    }
                    log.info("Did we update the database: " + add);
                });
        log.info("The time is now {}", dateFormat.format(new Date()));
    }
}
