package controller;

import java.util.List;
import java.util.ArrayList;

public class DataRecorder {
	private List<Data> recordedData;

	public DataRecorder() {
		recordedData = new ArrayList<Data>();
	}

	public void recordData(Data data) {
		recordedData.add(data);
		//You use this by adding:
		//"DataRecorder.recordData(Data.recordData(String Info));"
		//to your Moment/Event in Code that you want to record
	}

	public void saveData() {
		//TODO: save data to xml file
	}
}
