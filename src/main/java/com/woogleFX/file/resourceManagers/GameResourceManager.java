package com.woogleFX.file.resourceManagers;

import com.woogleFX.engine.fx.FXEditorButtons;
import com.woogleFX.engine.fx.FXMenu;
import com.woogleFX.engine.gui.alarms.ErrorAlarm;
import com.woogleFX.file.FileManager;
import com.woogleFX.gameData.ball.PaletteManager;
import com.woogleFX.gameData.level.GameVersion;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class GameResourceManager {

    private static final Logger logger = LoggerFactory.getLogger(GameResourceManager.class);


    /** Reloads all the resources in a World of Goo version. */
    public static void reloadWorldOfGoo(GameVersion version) {

        PaletteManager.clear();

        try {
            FileManager.readWOGdirs();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            ErrorAlarm.show(e);
        }

    }


    /** Changes the location of a World of Goo version. */
    public static boolean changeWorldOfGooDirectory(GameVersion version, boolean editorJustLaunched) {
        FileChooser findWorldOfGoo = new FileChooser();

        if (version == GameVersion.VERSION_WOG1_OLD || version == GameVersion.VERSION_WOG1_NEW) {
            findWorldOfGoo.getExtensionFilters()
                    .add(new FileChooser.ExtensionFilter("World of Goo executable", "WorldOfGoo.exe"));
        } else if (version == GameVersion.VERSION_WOG2) {
            findWorldOfGoo.getExtensionFilters()
                    .add(new FileChooser.ExtensionFilter("World of Goo 2 executable", "World Of Goo 2.exe"));
        }

        File worldOfGoo = findWorldOfGoo.showOpenDialog(new Stage());
        if (worldOfGoo == null) {
            return false;
        } else {
            if (version == GameVersion.VERSION_WOG1_OLD) {
                FileManager.setOldWOG1dir(worldOfGoo.getParent());
                try {
                    FileManager.saveProperties();
                } catch (IOException e) {
                    ErrorAlarm.show(e);
                }
                reloadWorldOfGoo(GameVersion.VERSION_WOG1_OLD);
            } else if (version == GameVersion.VERSION_WOG1_NEW) {
                FileManager.setNewWOG1dir(worldOfGoo.getParent() + "\\game");
                try {
                    FileManager.saveProperties();
                } catch (IOException e) {
                    ErrorAlarm.show(e);
                }
                reloadWorldOfGoo(GameVersion.VERSION_WOG1_NEW);
            } else if (version == GameVersion.VERSION_WOG2) {
                FileManager.setWOG2dir(worldOfGoo.getParent() + "\\game");
                try {
                    FileManager.saveProperties();
                } catch (IOException e) {
                    ErrorAlarm.show(e);
                }
                reloadWorldOfGoo(GameVersion.VERSION_WOG2);
            }
            if (FXEditorButtons.getOldGooballsToolbar() != null) {
                FXEditorButtons.getOldGooballsToolbar().getItems().clear();
            }
            if (FXEditorButtons.getNewGooballsToolbar() != null) {
                FXEditorButtons.getNewGooballsToolbar().getItems().clear();
            }
            if (FXEditorButtons.getSequelGooballsToolbar() != null) {
                FXEditorButtons.getSequelGooballsToolbar().getItems().clear();
            }
            if (!editorJustLaunched) FXEditorButtons.addBallsTo();
            if (!editorJustLaunched) FXEditorButtons.updateAllButtons();
            if (!editorJustLaunched) FXMenu.updateAllButtons();
            return true;
        }
    }




}
