package ui;

import model.Market;
import persistence.Reader;
import persistence.Writer;

import java.io.FileNotFoundException;
import java.io.IOException;


// Controls how the GUI interacts with market object, including saving and writing
public class MarketManager {
    protected Market myMarket;
    protected String file = "./data/market.json";
    protected Writer jsonWriter;
    protected Reader jsonReader;

    //CONSTRUCTOR
    //EFFECTS: creates new MarketManager with new market and reader/writer
    public MarketManager() {
        myMarket = new Market();
        jsonWriter = new Writer(file);
        jsonReader = new Reader(file);

    }

    //GETTER
    public Market getMarket() {
        return myMarket;
    }

    // MODIFIES: this
    // EFFECTS: Saves all market objects at that instance and overwrites old Save File
    //          throws IOException if unable to save
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

    // MODIFIES: this
    // EFFECTS: Loads all market objects instance into new Market object
    //          throws IOException if unable to load and creates new Market if loading fails
    public void load() throws IOException {
        try {
            myMarket = jsonReader.read();
            System.out.println("Loaded " + "market" + " from " + file);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + file);
            myMarket = new Market();
        }
    }

}
