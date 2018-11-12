package restAPI.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayedGame {

    private Long id;
    private Date datePlayed;
    private String firstWinner;
    private String secondWinner;
    private String thirdWinner;
    private String fourthWinner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatePlayed() {
        return datePlayed;
    }

    public void setDatePlayed(Date datePlayed) {
        this.datePlayed = datePlayed;
    }

    public String getFirstWinner() {
        return firstWinner;
    }

    public void setFirstWinner(String firstWinner) {
        this.firstWinner = firstWinner;
    }

    public String getSecondWinner() {
        return secondWinner;
    }

    public void setSecondWinner(String secondWinner) {
        this.secondWinner = secondWinner;
    }

    public String getThirdWinner() {
        return thirdWinner;
    }

    public void setThirdWinner(String thirdWinner) {
        this.thirdWinner = thirdWinner;
    }

    public String getFourthWinner() {
        return fourthWinner;
    }

    public void setFourthWinner(String fourthWinner) {
        this.fourthWinner = fourthWinner;
    }
}
