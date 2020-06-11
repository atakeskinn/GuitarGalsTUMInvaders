package controller;

import java.util.List;

public class DataRecorder {
	private List<Data> recordedData;
	
	public void recordData(Data data) {
		recordedData.add(data);
	}

	public void saveData() {
		//TODO: save data to xml file
	}
}
