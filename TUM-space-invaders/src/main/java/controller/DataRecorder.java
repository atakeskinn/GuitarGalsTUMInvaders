package controller;

import javax.sql.RowSetInternal;
import javax.sql.rowset.WebRowSet;
import javax.sql.rowset.spi.XmlWriter;
import javax.xml.stream.XMLStreamWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.ArrayList;

public class DataRecorder {
	private List<Data> recordedData;
	private String username;

	public DataRecorder() {
		recordedData = new ArrayList<Data>();
		this.username = System.getProperty("user.name");
	}

	public void recordData(String info) {
		this.recordedData.add(new Data(info, this.username));
		//You use this by adding:
		//"this.gameUI.getGameInternal().getDataRecorder().recordData(String info);"
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

	public static void main(String[] args) {
		DataRecorder data = new DataRecorder();
		data.recordData("Test");
		System.out.println(data.recordedData.get(0).getUsername() + " " + data.recordedData.get(0).getInfo() + " " + data.recordedData.get(0).getTime());
	}
}
