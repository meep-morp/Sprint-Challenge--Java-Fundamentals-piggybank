package com.lambdaschool.sprint_piggy_bank.repositories;

import com.lambdaschool.sprint_piggy_bank.models.Coins;
import org.springframework.data.repository.CrudRepository;

public interface CoinRepo extends CrudRepository<Coins, Long> {
}
