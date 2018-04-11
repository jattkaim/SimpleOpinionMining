package com.jattkaim;

/***************************************

 * This is a Class that contains attributes of a rating
 * todo: find ways to create a more complex rating

 ***************************************/

public class Ratings {
    private double dRating;
    private double comRating;
    private double waitTimeRating;

    public Ratings(double dR, double cR, double wT){
        this.comRating = cR;
        this.dRating = dR;
        this.waitTimeRating = wT;
    }

    public double getComRating() {
        return comRating;
    }

    public double getdRating() {
        return dRating;
    }

    public double getWaitTimeRating() {
        return waitTimeRating;
    }

    public void setComRating(double comRating) {
        this.comRating = comRating;
    }

    public void setdRating(double dRating) {
        this.dRating = dRating;
    }

    public void setWaitTimeRating(double waitTimeRating) {
        this.waitTimeRating = waitTimeRating;
    }
}
