package com.knoldus.httpclient;

public class PredictGender {
    private String name;
    private String gender;
    private int count;
    private float probability;
    
    public PredictGender(String name, String gender, int count, float probability) {
        this.name = name;
        this.gender = gender;
        this.count = count;
        this.probability = probability;
    }
    public PredictGender() {
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public int getCount() {
        return count;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
    
    public float getProbability() {
        return probability;
    }
    
    public void setProbability(float probability) {
        this.probability = probability;
    }
    
    @Override
    public String toString() {
        return "PredictGender{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", count=" + count +
                ", probability=" + probability +
                '}';
    }
}
