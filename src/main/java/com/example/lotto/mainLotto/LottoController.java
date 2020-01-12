package com.example.lotto.mainLotto;

import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

/**
 * @author Y510p
 * @project lotto
 * @date 09.01.2020
 **/

@RestController
@RequestMapping("/lotto")
public class LottoController {
    private LottoRepository lottoRepository;

    public LottoController(LottoRepository lottoRepository) {
        this.lottoRepository = lottoRepository;
    }

    @GetMapping
    public Iterable<LottoData> findAllLottoDraw() {
        Iterable<LottoData> dbResult = lottoRepository.findAll();
        List<LottoData> result = new ArrayList<>();
        dbResult.forEach(result::add);
        System.out.println("Sorted");
        result.sort(Comparator.comparing(o -> getDate(o.getDate())));
        System.out.println("Sorted");
        Collections.reverse(result);
        return result;
    }

    private Date getDate(String str) {
        try {
            return new SimpleDateFormat("dd-MM-yyyy").parse(str);
        } catch (ParseException e) {
            return null;
        }
    }

    @GetMapping("/{id}")
    public Optional<LottoData> findByLottoDrawId(@PathVariable String id) {
        return lottoRepository.findById(id);
    }

    @GetMapping("/search")
    public LottoData getLottoDrawByDate(@RequestParam(required = false) String date) {
        if (date == null) {
            return lottoRepository.findAll().iterator().next();
        } else {
            return lottoRepository.findFirstByDate(date);
        }
    }

    @PostMapping("/check")
    public LottoDateHelper getLotteryNumbers(@RequestBody LottoDateHelper date){
        LottoData firstByDate = lottoRepository.findFirstByDate(date.getDate());
        if (firstByDate == null) {
            return null;
        }
        LottoDateHelper result = new LottoDateHelper(date.getDate());
        String[] split = firstByDate.getNumbers().split(" ");
        for (String s : split) {
            int number = Integer.parseInt(s);
            if (date.getNumbers().contains(number)){
                result.getNumbers().add(number);
            }
        }
        return result;
    }
}

