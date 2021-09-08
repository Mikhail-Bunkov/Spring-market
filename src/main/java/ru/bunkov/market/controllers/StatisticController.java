package ru.bunkov.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bunkov.market.utils.AOPStatistic;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/statistic")
@RequiredArgsConstructor
public class StatisticController {

    private final AOPStatistic statistic;

    @GetMapping
    public Map<String, Long> getStatistic(){
        return statistic.getTimeStatistic();
    }
}
