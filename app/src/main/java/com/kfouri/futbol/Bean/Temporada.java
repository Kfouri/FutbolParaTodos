package com.kfouri.futbol.Bean;

public class Temporada
{
    private int id;
    private String caption;
    private String league;
    private int currentMatchday;
    private int numberOfTeams;
    private int numberOfGames;
    private String lastUpdated;
    private String self;
    private String teams;
    private String fixtures;
    private String leagueTable;


    public Temporada (int id,String caption,String league,int currentMatchday,int numberOfTeams,int numberOfGames,String lastUpdated,String self,String teams,String fixtures,String leagueTable)
    {
        this.id = id;
        this.caption = caption;
        this.league = league;
        this.currentMatchday = currentMatchday;
        this.numberOfTeams = numberOfTeams;
        this.numberOfGames = numberOfGames;
        this.lastUpdated = lastUpdated;
        this.self = self;
        this.teams = teams;
        this.fixtures = fixtures;
        this.leagueTable = leagueTable;
    }

    public Temporada() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public int getCurrentMatchday() {
        return currentMatchday;
    }

    public void setCurrentMatchday(int currentMatchday) {
        this.currentMatchday = currentMatchday;
    }

    public int getNumberOfTeams() {
        return numberOfTeams;
    }

    public void setNumberOfTeams(int numberOfTeams) {
        this.numberOfTeams = numberOfTeams;
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames(int numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getTeams() {
        return teams;
    }

    public void setTeams(String teams) {
        this.teams = teams;
    }

    public String getFixtures() {
        return fixtures;
    }

    public void setFixtures(String fixtures) {
        this.fixtures = fixtures;
    }

    public String getLeagueTable() {
        return leagueTable;
    }

    public void setLeagueTable(String leagueTable) {
        this.leagueTable = leagueTable;
    }
}
