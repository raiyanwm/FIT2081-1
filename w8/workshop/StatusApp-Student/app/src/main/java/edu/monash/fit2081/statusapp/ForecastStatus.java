package edu.monash.fit2081.statusapp;

public class ForecastStatus {
    String timeStamp;
    String status;

    public ForecastStatus() {
    }

    public ForecastStatus(String timeStamp, String status) {
        this.timeStamp = timeStamp;
        this.status = status;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString(){
        String text = "";
        text = getStatus() + getTimeStamp();
        return text;
    }

    public String notifyDeletion(){
        return toString() + "is deleted";
    }
}
