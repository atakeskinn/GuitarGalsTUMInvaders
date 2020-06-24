package controller;

import java.time.ZonedDateTime;

public class Data {
    private ZonedDateTime time;
    private String info;
    private String username;

    public Data(String info, String username) {
        this.time = ZonedDateTime.now();
        this.info = info;
        this.username = username;
    }

    public ZonedDateTime getTime() { return this.time; }

    public String getInfo() {
        return this.info;
    }

    public String getUsername() { return this.username; }
}
