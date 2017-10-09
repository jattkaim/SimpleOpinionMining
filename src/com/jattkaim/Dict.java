package com.jattkaim;

public class Dict {
    private String Word;
    private int freq;
    private String polarity;
    private double score;

    public Dict(){

    }

    public Dict(String Word, int freq, String polarity, float score){
        this.Word = Word;
        this.freq = freq;
        this.polarity = polarity;
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public int getFreq() {
        return freq;
    }

    public String getWord() {
        return Word;
    }


    public void setScore(double score) {
        this.score = score;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public void setWord(String word) {
        this.Word = word;
    }

    public String getPol() {
        return polarity;
    }

    public void setPol(String pol) {
        this.polarity = pol;
    }
}
