package com.example.lotto;

import com.example.lotto.mainLotto.LottoDataProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LottoApplication extends LottoDataProvider {
    public static void main(String[] args) {
        SpringApplication.run(LottoApplication.class, args);
    }
}