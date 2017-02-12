package com.kfouri.futbol.Bean;

public class Fixture implements itemFixture{

    private int idHome;
    private int idAway;
    private String soccerseason;
    private String homeTeam;
    private String awayTeam;
    private String date;
    private String status;
    private int matchday;
    private String homeTeamName;
    private String awayTeamName;
    private String goalsHomeTeam;
    private String goalsAwayTeam;
    private String hora;

    public Fixture(int idHome, int idAway, String soccerseason, String homeTeam, String awayTeam, String date, String status, int matchday, String homeTeamName, String awayTeamName, String goalsHomeTeam, String goalsAwayTeam, String hora) {
        this.idHome = idHome;
        this.idAway = idAway;
        this.soccerseason = soccerseason;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.date = date;
        this.status = status;
        this.matchday = matchday;
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.goalsHomeTeam = goalsHomeTeam;
        this.goalsAwayTeam = goalsAwayTeam;
        this.hora = hora;
    }


    public String getSoccerseason() {
        return soccerseason;
    }

    public void setSoccerseason(String soccerseason) {
        this.soccerseason = soccerseason;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMatchday() {
        return matchday;
    }

    public void setMatchday(int matchday) {
        this.matchday = matchday;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public String getGoalsHomeTeam() {
        return goalsHomeTeam;
    }

    public void setGoalsHomeTeam(String goalsHomeTeam) {
        this.goalsHomeTeam = goalsHomeTeam;
    }

    public String getGoalsAwayTeam() {
        return goalsAwayTeam;
    }

    public void setGoalsAwayTeam(String goalsAwayTeam) {
        this.goalsAwayTeam = goalsAwayTeam;
    }

    public int getIdHome() {return idHome;}

    public void setIdHome(int idHome) {this.idHome = idHome;}

    public int getIdAway() {return idAway;}

    public void setIdAway(int idAway) {this.idAway = idAway;}

    @Override
    public boolean isSection() {
        return false;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
