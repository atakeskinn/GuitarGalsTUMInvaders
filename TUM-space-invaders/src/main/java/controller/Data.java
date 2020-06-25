package controller;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Data {
    protected ZonedDateTime time;
    protected String info;

    public Data() {
        time = ZonedDateTime.now();
        info = "";
    }

    public ZonedDateTime getTime() {
        return this.time;
    }

    public String getInfo() {
        return this.info;
    }

    public abstract List<Data> getData();
}
