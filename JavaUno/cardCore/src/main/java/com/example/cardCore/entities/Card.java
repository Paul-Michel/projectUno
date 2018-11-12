package com.example.cardCore.entities;

import com.example.cardCore.enums.Color;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@SequenceGenerator(name="PRIVATE_SEQ_CARD", sequenceName = "private_sequence")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRIVATE_SEQ_CARD")
    private Long id;
    private String value;
    private Color color;

    public Card(String value, Color color) {
        this.value = value;
        this.color = color;
    }
    public Card(){}

}
