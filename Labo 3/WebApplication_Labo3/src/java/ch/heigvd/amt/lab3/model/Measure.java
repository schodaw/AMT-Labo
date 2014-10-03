package ch.heigvd.amt.lab3.model;

public class Measure {
    private String sensorId;
    private long timeStamp;
    private double value;
    
    public Measure(String sensorId, long timeStamp, double value) {
        this.sensorId = sensorId;
        this.timeStamp = timeStamp;
        this.value = value;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getSensorId() {
        return sensorId;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public double getValue() {
        return value;
    }
}
