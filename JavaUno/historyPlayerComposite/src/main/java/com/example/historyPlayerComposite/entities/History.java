package com.example.historyPlayerComposite.entities;


import lombok.Data;

import java.util.Date;

@Data
public class History {

    private Long id;
    private Date datePlayed;
    private Long firstWinnerId;
    private Long secondWinnerId;
    private Long thirdWinnerId;
    private Long fourthWinnerId;

    public History(Long firstWinnerId, Long secondWinnerId, Long thirdWinnerId, Long fourthWinnerId) {
        this.datePlayed = new Date();
        this.firstWinnerId = firstWinnerId;
        this.secondWinnerId = secondWinnerId;
        this.thirdWinnerId = thirdWinnerId;
        this.fourthWinnerId = fourthWinnerId;
    }
}
