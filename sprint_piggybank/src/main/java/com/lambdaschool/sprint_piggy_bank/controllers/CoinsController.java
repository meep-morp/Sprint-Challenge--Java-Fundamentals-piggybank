package com.lambdaschool.sprint_piggy_bank.controllers;

import com.lambdaschool.sprint_piggy_bank.models.Coins;
import com.lambdaschool.sprint_piggy_bank.repositories.CoinRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
public class CoinsController {

    public List<Coins> coinList = new ArrayList<>();

    private String checkPlural(Coins coin) {
        if (coin.getQuantity() > 1) {
            return coin.getNameplural();
        } else return coin.getName();
    }

    @Autowired
    CoinRepo coinrepo;

    private List<Coins> findCountries (List<Coins> coinList, TestCoin tester) {
        List<Coins> tempList = new ArrayList<>();

        for (Coins c : coinList) {
            if (tester.test(c)) {
                tempList.add(c);
            }
        }

        return tempList;
    }

    @GetMapping(value = "/total",
            produces = {"application/json"})

    public ResponseEntity<?> listTotal() {
        coinList.clear();
        coinrepo.findAll().iterator().forEachRemaining(coinList::add);

        float count = 0;

        for (Coins coin : coinList) {
            count += coin.getValue() * coin.getQuantity();

            System.out.println(coin.getQuantity() + " " + checkPlural(coin));
        }

        System.out.print("The piggy bank holds $" + Math.round(count*100.0)/100.0);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
