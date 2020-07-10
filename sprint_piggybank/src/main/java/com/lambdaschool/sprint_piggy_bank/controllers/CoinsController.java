package com.lambdaschool.sprint_piggy_bank.controllers;

import com.lambdaschool.sprint_piggy_bank.models.Coins;
import com.lambdaschool.sprint_piggy_bank.repositories.CoinRepo;
import org.h2.command.dml.MergeUsing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@RestController
public class CoinsController {

    public List<Coins> coinList = new ArrayList<>();

    private String checkPlural(Coins coin) {
        if (coin.getQuantity() > 1) {
            return coin.getNameplural();
        } else return coin.getName();
    }

    private float getTotal() {
        float total = 0;

        for (Coins coin : coinList) {
            total += coin.getValue() * coin.getQuantity();
        }

        return total;
    }

    @Autowired
    CoinRepo coinrepo;

    @GetMapping(value = "/total",
            produces = {"application/json"})

    public ResponseEntity<?> listTotal() {
        coinList.clear();
        coinrepo.findAll().iterator().forEachRemaining(coinList::add);

        for (Coins coin : coinList) {
            System.out.println(coin.getQuantity() + " " + checkPlural(coin));
        }

        System.out.print("The piggy bank holds $" + Math.round(getTotal()*100.0)/100.0);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Stretch Goal

    @DeleteMapping(path = "/{amount}")
    public ResponseEntity<?> deleteCountry(@PathVariable float amount) {
        coinList.clear();
        coinrepo.findAll().iterator().forEachRemaining(coinList::add);
        coinList.sort(Comparator.comparingInt(Coins::getQuantity));
        coinList.forEach(c -> System.out.println(c + "\n"));

        float count = 0;
        List<Coins> tempList = coinList;

        if (getTotal() >= amount) {

            Iterator<Coins> i = tempList.iterator();
            while (i.hasNext()) {
                Coins coin = i.next();
                System.out.println(count);
                count += coin.getValue() * coin.getQuantity();

                if (count <= amount) {
                    System.out.println("Withdrew " + coin.getQuantity() + " " + checkPlural(coin));
                    i.remove();
                }
            }

            for (Coins c : coinList) {
                System.out.println(c);
            }
            System.out.println("The piggy bank holds $" + Math.round(getTotal()*100.0)/100.0);

        } else System.out.println("Money not available");

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
