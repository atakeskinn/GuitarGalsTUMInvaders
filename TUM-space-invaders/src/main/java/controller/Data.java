package controller;

import java.time.ZonedDateTime;

public class Data {
    private ZonedDateTime time;
    private String info;

    public Data(ZonedDateTime time, String info) {
        this.time = time;
        this.info = info;
    }

    public Data recordData(String info) {
        return new Data(ZonedDateTime.now(), info);
    }

    public ZonedDateTime getTime() {
        return this.time;
    }

    public String getInfo() {
        return this.info;
    }
}
