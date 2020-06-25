package controller;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public class Data {
    private ZonedDateTime time;
    private String info;
    private ArrayList<Data> recordedData;

    public Data(String info) {
        this.time = ZonedDateTime.now();
        this.info = info;
        this.recordedData = null;
    }

    public Data(ArrayList<Data> data) {
        this.recordedData = data;
        this.info = "This Data Object contains a List of recordedData";
        this.time = ZonedDateTime.now();
    }

    public ZonedDateTime getTime() { return this.time; }

    public String getInfo() {
        return this.info;
    }

    public ArrayList<Data> getRecordedData() {
        return recordedData;
    }

    public void addNewData(Data data){
        this.recordedData.add(data);
    }
}
