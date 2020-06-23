package controller;

import javax.sql.RowSetInternal;
import javax.sql.rowset.WebRowSet;
import javax.sql.rowset.spi.XmlWriter;
import javax.xml.stream.XMLStreamWriter;
import java.io.Writer;
import java.sql.SQLException;
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
		//"this.gameUI.getGameInternal().getDataRecorder().recordData(Data.recordData(String Info));"
		//to your Moment/Event in Code that you want to record
	}

	public void saveData() {
		//parsing Data to XML file
		this.recordedData.forEach(n ->{

		});
		//Clearing the List so we dont save any Data twice in the XML file
		//Using .clear for better performance
		this.recordedData.clear();
		//TODO: save data to xml file
	}
}
