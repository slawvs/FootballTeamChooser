package pl.slawek.controller;

import pl.slawek.model.Player;

import java.util.List;

public class TeamsRest {
    private List<Player> blackTeam;
    private List<Player> whiteTeam;

    public TeamsRest(List<Player> blackTeam, List<Player> whiteTeam) {
        this.blackTeam = blackTeam;
        this.whiteTeam = whiteTeam;
    }

    public List<Player> getBlackTeam() {
        return blackTeam;
    }

    public void setBlackTeam(List<Player> blackTeam) {
        this.blackTeam = blackTeam;
    }

    public List<Player> getWhiteTeam() {
        return whiteTeam;
    }

    public void setWhiteTeam(List<Player> whiteTeam) {
        this.whiteTeam = whiteTeam;
    }
}
