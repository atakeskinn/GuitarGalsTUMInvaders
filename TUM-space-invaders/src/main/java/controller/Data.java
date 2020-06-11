package controller;

import java.time.ZonedDateTime;

public class Data {
    private ZonedDateTime time;
    private String info;

    public Data(ZonedDateTime time, String info) {
        this.time = time;
        this.info = info;
    }
}
