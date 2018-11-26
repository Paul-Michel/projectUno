package com.example.Uno.repositories;

import com.example.Uno.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findByPseudo(String pseudo);
}
