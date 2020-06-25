package controller;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataCollection extends Data {

    private ArrayList<Data> recordedData;

    public DataCollection(String info) {
        super();
        this.recordedData = new ArrayList<>();
        this.info = "DataCollection: " + info;
        this.time = ZonedDateTime.now();
    }

    public DataCollection(String info, ArrayList<Data> data) {
        super();
        this.recordedData = data;
        this.info = "DataCollection: " + info;
        this.time = ZonedDateTime.now();
    }

    @Override
    public List<Data> getData() {
        return recordedData;
    }

    public void addData(Data data) {
        recordedData.add(data);
    }
}
