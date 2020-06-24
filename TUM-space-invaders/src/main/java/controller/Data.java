package controller;

import java.time.ZonedDateTime;

public class Data {
    private ZonedDateTime time;
    private String info;

    public Data(String info) {
        this.time = ZonedDateTime.now();
        this.info = info;
    }

    public ZonedDateTime getTime() { return this.time; }

    public String getInfo() {
        return this.info;
    }
}
