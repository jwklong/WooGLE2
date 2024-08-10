package com.woogleFX.file.fileImport;
import com.woogleFX.file.FileManager;
import com.woogleFX.gameData.ball.PaletteManager;
import com.woogleFX.gameData.level.GameVersion;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class PropertiesOpener extends DefaultHandler {

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch (qName) {
            case "oldWOG" -> {
                if (!attributes.getValue(attributes.getIndex("filepath")).isEmpty()) {
                    FileManager.setOldWOG1dir(attributes.getValue(attributes.getIndex("filepath")));
                }
            }
            case "newWOG" -> {
                if (!attributes.getValue(attributes.getIndex("filepath")).isEmpty()) {
                    FileManager.setNewWOG1dir(attributes.getValue(attributes.getIndex("filepath")));
                }
            }
            case "WOG2" -> {
                if (!attributes.getValue(attributes.getIndex("filepath")).isEmpty()) {
                    FileManager.setWOG2dir(attributes.getValue(attributes.getIndex("filepath")));
                }
            }
            case "Ball" -> {
                PaletteManager.addPaletteBall(attributes.getValue(attributes.getIndex("ball")));
                PaletteManager.addPaletteVersion(switch(attributes.getValue(attributes.getIndex("version"))) {
                    case "1.3" -> GameVersion.VERSION_WOG1_OLD;
                    case "1.5" -> GameVersion.VERSION_WOG1_NEW;
                    default -> GameVersion.VERSION_WOG2;
                });
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
    }

    @Override
    public void characters(char[] ch, int start, int length) {
    }
}