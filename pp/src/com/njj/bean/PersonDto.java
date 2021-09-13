package com.njj.bean;

public class PersonDto {
    private int gender;
    private double vagScore;

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public double getVagScore() {
        return vagScore;
    }

    public void setVagScore(double vagScore) {
        this.vagScore = vagScore;
    }

    @Override
    public String toString() {
        return "PersonDto{" +
                "gender=" + gender +
                ", vagScore=" + vagScore +
                '}';
    }
}
