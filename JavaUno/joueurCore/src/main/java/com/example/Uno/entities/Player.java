package com.example.Uno.entities;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@SequenceGenerator(name="PRIVATE_SEQ_PLAYER", sequenceName = "private_sequence")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRIVATE_SEQ_PLAYER")
        private String _id;
        private String username;
        private Long createdAt;
        private Long updatedAt;
        private String email;


    public Player(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public Player() {
    }
}
