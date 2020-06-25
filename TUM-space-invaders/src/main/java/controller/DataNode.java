package controller;

import java.util.Collections;
import java.util.List;

public class DataNode extends Data {
    public DataNode(String info) {
        super();
        super.info = info;
    }

    @Override
    public List<Data> getData() {
        return Collections.singletonList(this);
    }
}
