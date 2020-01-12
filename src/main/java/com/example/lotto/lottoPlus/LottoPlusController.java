package com.example.lotto.lottoPlus;

import com.example.lotto.mainLotto.LottoDateHelper;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Y510p
 * @project lotto
 * @date 17.01.2020
 **/

@RestController
@RequestMapping("/lottoPlus")
public class LottoPlusController {
    private LottoPlusRepository lottoPlusRepository;

    public LottoPlusController(LottoPlusRepository lottoPlusRepository) {
        this.lottoPlusRepository = lottoPlusRepository;
    }

    @GetMapping
    public Iterable<LottoPlusData> findAllLottoDraw() {
        Iterable<LottoPlusData> dbResult = lottoPlusRepository.findAll();
        List<LottoPlusData> result = new ArrayList<>();
        dbResult.forEach(result::add);
        result.sort(Comparator.comparing(o -> getDate(o.getDate())));
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
    public Optional<LottoPlusData> findByLottoDrawId(@PathVariable String id) {
        return lottoPlusRepository.findById(id);
    }

    @GetMapping("/search")
    public LottoPlusData getLottoDrawByDate(@RequestParam(required = false) String date) {
        if (date == null) {
            return lottoPlusRepository.findAll().iterator().next();
        } else {
            return lottoPlusRepository.findFirstByDate(date);
        }
    }

    @PostMapping("/check")
    public LottoDateHelper getLotteryNumbers(@RequestBody LottoDateHelper date) {
        LottoPlusData firstByDate = lottoPlusRepository.findFirstByDate(date.getDate());
        if (firstByDate == null) {
            return null;
        }
        LottoDateHelper result = new LottoDateHelper(date.getDate());
        String[] split = firstByDate.getNumbers().split(" ");
        for (String s : split) {
            int number = Integer.parseInt(s);
            if (date.getNumbers().contains(number)) {
                result.getNumbers().add(number);
            }
        }
        return result;
    }
}
