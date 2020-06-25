
package controller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import view.GameUI;

import javax.sql.RowSetInternal;
import javax.sql.rowset.WebRowSet;
import javax.sql.rowset.spi.XmlWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.ArrayList;

public class DataRecorder {
    private List<Data> recordedData;
    private String username;
    private Transformer transformer;
    private Document documentWriter;
    private Element eventList;
    private static final String RECORDED_DATA = "recordedData.xml";
    private boolean newData; //boolean that is true when new Data needs to be saved

    public DataRecorder() {
        recordedData = new ArrayList<Data>();
        this.username = System.getProperty("user.name");
        this.newData = false;

        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            this.transformer = transformerFactory.newTransformer();
        } catch (Exception e) {
            System.out.println("Error when creating DataRecorder: Transformer");
        }
        File tempFile = new File(RECORDED_DATA);
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            if (!tempFile.exists()) {
                //Creates new Document if it doesnt exist
                this.documentWriter = db.newDocument();
            } else {
                this.documentWriter = db.parse(RECORDED_DATA);
            }
        } catch (Exception e) {
            System.out.println("Error when creating DataRecorder: Document Writer");
        }

        Element root;
        if (!tempFile.exists()) {
            //Creates new Document if it doesnt exist
            root = this.documentWriter.createElement("recordedData");
            this.documentWriter.appendChild(root);
        } else {
            root = this.documentWriter.getDocumentElement();
        }

        Element player = this.documentWriter.createElement("Player");
        player.setAttribute("name", this.username);
        root.appendChild(player);


        this.eventList = this.documentWriter.createElement("EventList");
        this.eventList.setAttribute("date", Date.from(java.time.ZonedDateTime.now().toInstant()).toString());
        this.eventList.setAttribute("timezone", java.time.ZonedDateTime.now().getZone().toString());
        player.appendChild(this.eventList);

        DOMSource source = new DOMSource(this.documentWriter);
        StreamResult result = new StreamResult(RECORDED_DATA);//new StreamResult(System.getProperty("user.dir")  + "/src/main/resources/recordedData.xml");

        try {
            this.transformer.transform(source, result);
        } catch (Exception e) {
            //if you get this Error try exchanging StreamResult a few lines above with
            // = new StreamResult(RECORDED_DATA)
            //if you get this Error and the Code already looks like the suggestion one line above try exchanging it with
            // = new StreamResult(System.getProperty("user.dir")  + "/src/main/resources/recordedData.xml");
            //This is an Error that occurs because StreamResult reads out directories differently when using maven / when not using maven
            System.out.println("Error when creating DataRecorder: Could not write System Name and Date");
            System.out.println("Check last Exception Catch Block in DataRecorder for FIX/EXPLANATION");
            System.out.println(e);
        }
    }

    /**Record data to this DataRecorder object
     *
     * @param info data to be recorded
     */
    public void recordData(String info) {
        this.newData = true;
        this.recordedData.add(new Data(info));
        //You use this by adding:
        //"this.gameUI.getGameInternal().getDataRecorder().recordData(String info);"
        //to your Moment/Event in Code that you want to record
    }

    public void saveData() {
        if (newData) {
            try {
                this.recordedData.forEach(n -> {
                    Element event = this.documentWriter.createElement("Event");
                    if(n.getTime().getMinute() < 10) {
                        event.setAttribute("Time", n.getTime().getHour() + ":0" + n.getTime().getMinute());
                    } else {
                        event.setAttribute("Time", n.getTime().getHour() + ":" + n.getTime().getMinute());
                    }
                    event.setTextContent(n.getInfo());
                    this.eventList.appendChild(event);
                });

                DOMSource source = new DOMSource(this.documentWriter);
                StreamResult result = new StreamResult(RECORDED_DATA);
                System.out.println("TRYING TO SAVE!");
                this.transformer.transform(source, result);

            } catch (TransformerException e) {
                e.printStackTrace();
                System.out.println("An Error occurred when trying to save the recorded Data of your most recent Game");
            }

            this.recordedData.clear();
            //clears recorded Data to avoid writing the same data twice
            //using clear() because of better perfomance over removeAll()
            this.newData = false;
            //Sets newData to false as all Data has been saved
        } else {
            //Does Nothing when there is no new Data to record and this Method is called up for performance Reasons
        }
    }

}
