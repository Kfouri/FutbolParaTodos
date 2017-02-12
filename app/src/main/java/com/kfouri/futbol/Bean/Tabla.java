package com.kfouri.futbol.Bean;

public class Tabla
{
    private String _links;
    private int position;
    private String teamName;
    private String crestURI;
    private int playedGames;
    private int points;
    private int goals;
    private int goalsAgainst;
    private int goalDifference;
    private int wins;
    private int draws;
    private int losses;
    private int idTeam;

    public Tabla(int idTeam,String _links, int position, String teamName, String crestURI, int playedGames, int points, int goals, int goalsAgainst, int goalDifference, int wins, int draws, int losses) {
        this._links = _links;
        this.position = position;
        this.teamName = teamName;
        this.crestURI = crestURI;
        this.playedGames = playedGames;
        this.points = points;
        this.goals = goals;
        this.goalsAgainst = goalsAgainst;
        this.goalDifference = goalDifference;
        this.wins = wins;
        this.draws = draws;
        this.losses = losses;
        this.idTeam = idTeam;
    }

    public String get_links() {
        return _links;
    }

    public void set_links(String _links) {
        this._links = _links;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCrestURI() {
        return crestURI;
    }

    public void setCrestURI(String crestURI) {
        this.crestURI = crestURI;
    }

    public int getPlayedGames() {
        return playedGames;
    }

    public void setPlayedGames(int playedGames) {
        this.playedGames = playedGames;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }
}
