package ui;

import model.Market;
import persistence.Reader;
import persistence.Writer;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MarketManager {
    protected Market myMarket;
    protected String file = "./data/market.json";
    protected Writer jsonWriter;
    protected Reader jsonReader;

    public MarketManager() {
        myMarket = new Market();
        jsonWriter = new Writer(file);
        jsonReader = new Reader(file);

    }

    // returns the market object
    public Market getMarket() {
        return myMarket;
    }

    // MODIFIES: this
    //  EFFECTS: Creates new Writer Object and Writes current market Object to savefile
    // Saves all market objects at that instance and overwrites old Save File
    public void save() throws IOException {
        try {
            jsonWriter.open();
            jsonWriter.write(myMarket);
            jsonWriter.close();
            System.out.println("Saved " + "market" + " to " + file);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + file);
        }
    }

    public void load() throws IOException {
        try {
            myMarket = jsonReader.read();
            System.out.println("Loaded " + "market" + " from " + file);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + file);
            myMarket = new Market();  // Initialize a new Market if reading fails
        }
    }


    public void load(String str) throws IOException {
        Reader reader = new Reader(str);
        myMarket = reader.read();
    }

    public void userRequestReload() throws IOException {
        load();
    }

}
