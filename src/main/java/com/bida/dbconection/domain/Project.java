package com.bida.dbconection.domain;

public class Project {

    private String startDate;
    private String name;
    private int developersAmount;

    public Project(String startDate, String name, int developersAmount) {
        this.startDate = startDate;
        this.name = name;
        this.developersAmount = developersAmount;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDevelopersAmount() {
        return developersAmount;
    }

    public void setDevelopersAmount(int developersAmount) {
        this.developersAmount = developersAmount;
    }

    @Override
    public String toString() {
        return "Project{" +
                "startDate='" + startDate + '\'' +
                ", name='" + name + '\'' +
                ", developersAmount=" + developersAmount +
                '}';
    }
}
