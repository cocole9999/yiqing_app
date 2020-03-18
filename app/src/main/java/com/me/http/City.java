package com.me.http;


public class City {
    private int id;
    private String name;
    private String confirm;
    private String suspect;
    private String heal;
    private String dead;
    private String severe;
    private String lastUpdateTime;

    @Override
    public String toString() {
        return "CityDate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", confirm='" + confirm + '\'' +
                ", suspect='" + suspect + '\'' +
                ", heal='" + heal + '\'' +
                ", dead='" + dead + '\'' +
                ", severe='" + severe + '\'' +
                ", lastupdatetime='" + lastUpdateTime + '\'' +
                '}';
    }

    public City(int id, String name, String confirm, String suspect, String heal, String dead, String severe,String lastUpdateTime) {
        this.id = id;
        this.name = name;
        this.confirm = confirm;
        this.suspect = suspect;
        this.heal = heal;
        this.dead = dead;
        this.severe = severe;
        this.lastUpdateTime = lastUpdateTime;
    }

    public City() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getSuspect() {
        return suspect;
    }

    public void setSuspect(String suspect) {
        this.suspect = suspect;
    }

    public String getHeal() {
        return heal;
    }

    public void setHeal(String heal) {
        this.heal = heal;
    }

    public String getDead() {
        return dead;
    }

    public void setDead(String dead) {
        this.dead = dead;
    }

    public String getSevere() {
        return severe;
    }

    public void setSevere(String severe) {
        this.severe = severe;
    }



    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}

