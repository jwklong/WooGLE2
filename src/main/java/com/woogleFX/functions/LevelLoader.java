package com.woogleFX.functions;

import com.woogleFX.editorObjects.objectCreators.ObjectCreator;
import com.woogleFX.editorObjects.ObjectUtil;
import com.woogleFX.editorObjects._Ball;
import com.woogleFX.engine.fx.*;
import com.woogleFX.file.FileManager;
import com.woogleFX.editorObjects.objectCreators.BlankObjectGenerator;
import com.woogleFX.file.resourceManagers.BallManager;
import com.woogleFX.engine.gui.Alarms;
import com.woogleFX.engine.gui.LevelSelector;
import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.structures.GameVersion;
import com.woogleFX.structures.simpleStructures.LevelTab;
import com.woogleFX.structures.WorldLevel;
import com.worldOfGoo.level.BallInstance;
import com.worldOfGoo.level.Signpost;
import com.worldOfGoo.resrc.Resources;
import com.worldOfGoo.resrc.ResrcImage;
import com.worldOfGoo.resrc.Sound;
import com.worldOfGoo.scene.SceneLayer;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class LevelLoader {

    private static final Logger logger = LoggerFactory.getLogger(LevelLoader.class);


    public static void newLevel(GameVersion version) {
        Alarms.askForLevelName("new", version);
    }


    public static void openLevel(GameVersion version) {
        new LevelSelector(version).start(new Stage());
    }


    /** Creates a new, default level. */
    public static void newLevel(String name, GameVersion version) {
        logger.debug("New level");

        FXLevelSelectPane.getLevelSelectPane().setMinHeight(30);
        FXLevelSelectPane.getLevelSelectPane().setMaxHeight(30);

        ArrayList<EditorObject> sceneList = new ArrayList<>();
        sceneList.add(ObjectCreator.create("scene", null));
        ArrayList<EditorObject> levelList = new ArrayList<>();
        levelList.add(ObjectCreator.create("level", null));
        ArrayList<EditorObject> resourcesList = new ArrayList<>();
        resourcesList.add(ObjectCreator.create("ResourceManifest", null));
        ArrayList<EditorObject> addinList = new ArrayList<>();
        addinList.add(BlankObjectGenerator.generateBlankAddinObject(name));
        ArrayList<EditorObject> textList = new ArrayList<>();
        textList.add(ObjectCreator.create("strings", null));

        WorldLevel level = new WorldLevel(sceneList, levelList, resourcesList, addinList, textList, version);
        level.setLevelName(name);
        FXEditorButtons.updateAllButtons();
        FXMenu.updateAllButtons();

        level.getSceneObject().setAttribute("backgroundcolor", "255,255,255");
        level.getSceneObject().setAttribute("minx", "-500");
        level.getSceneObject().setAttribute("miny", "0");
        level.getSceneObject().setAttribute("maxx", "500");
        level.getSceneObject().setAttribute("maxy", "1000");

        level.getLevelObject().setAttribute("ballsrequired", "1");
        level.getLevelObject().setAttribute("letterboxed", "false");
        level.getLevelObject().setAttribute("visualdebug", "false");
        level.getLevelObject().setAttribute("autobounds", "false");
        level.getLevelObject().setAttribute("textcolor", "255,255,255");
        level.getLevelObject().setAttribute("timebugprobability", "0");
        level.getLevelObject().setAttribute("strandgeom", "false");
        level.getLevelObject().setAttribute("allowskip", "true");

        EditorObject resourcesThing = ObjectCreator.create("Resources", level.getResrcObject());
        assert resourcesThing != null;
        resourcesThing.setAttribute("id", "scene_" + level.getLevelName());
        resourcesThing.getTreeItem().setExpanded(true);
        level.getResrc().add(resourcesThing);

        EditorObject linearForceField = ObjectCreator.create("linearforcefield", level.getSceneObject());
        assert linearForceField != null;
        linearForceField.setAttribute("type", "gravity");
        linearForceField.setAttribute("force", "0,-10");
        linearForceField.setAttribute("dampeningfactor", "0");
        linearForceField.setAttribute("antigrav", "true");
        level.getScene().add(linearForceField);

        EditorObject lineRight = ObjectCreator.create("line", level.getSceneObject());
        assert lineRight != null;
        lineRight.setAttribute("id", "right");
        lineRight.setAttribute("anchor", "500,300");
        lineRight.setAttribute("normal", "-1,0");
        lineRight.setAttribute("tag", "detaching");
        level.getScene().add(lineRight);

        EditorObject lineLeft = ObjectCreator.create("line", level.getSceneObject());
        assert lineLeft != null;
        lineLeft.setAttribute("id", "left");
        lineLeft.setAttribute("anchor", "-500,300");
        lineLeft.setAttribute("normal", "1,0");
        lineLeft.setAttribute("tag", "detaching");
        level.getScene().add(lineLeft);

        EditorObject lineGround = ObjectCreator.create("line", level.getSceneObject());
        assert lineGround != null;
        lineGround.setAttribute("id", "ground");
        lineGround.setAttribute("anchor", "0,20");
        lineGround.setAttribute("normal", "0,1");
        level.getScene().add(lineGround);

        EditorObject cameraNormal = ObjectCreator.create("camera", level.getLevelObject());
        assert cameraNormal != null;
        cameraNormal.setAttribute("aspect", "normal");
        cameraNormal.setAttribute("endpos", "0,0");
        cameraNormal.setAttribute("endzoom", "1");
        level.getLevel().add(cameraNormal);

        EditorObject poiNormal = ObjectCreator.create("poi", cameraNormal);
        assert poiNormal != null;
        poiNormal.setAttribute("pos", "0,0");
        poiNormal.setAttribute("zoom", "1");
        poiNormal.setAttribute("pause", "0");
        poiNormal.setAttribute("traveltime", "0");
        level.getLevel().add(poiNormal);

        EditorObject cameraWidescreen = ObjectCreator.create("camera", level.getLevelObject());
        assert cameraWidescreen != null;
        cameraWidescreen.setAttribute("aspect", "widescreen");
        cameraWidescreen.setAttribute("endpos", "0,0");
        cameraWidescreen.setAttribute("endzoom", "1");
        level.getLevel().add(cameraWidescreen);

        EditorObject poiWidescreen = ObjectCreator.create("poi", cameraWidescreen);
        assert poiWidescreen != null;
        poiWidescreen.setAttribute("pos", "0,0");
        poiWidescreen.setAttribute("zoom", "1");
        poiWidescreen.setAttribute("pause", "0");
        poiWidescreen.setAttribute("traveltime", "0");
        level.getLevel().add(poiWidescreen);

        EditorObject levelExit = ObjectCreator.create("levelexit", level.getLevelObject());
        assert levelExit != null;
        levelExit.setAttribute("id", "theExit");
        levelExit.setAttribute("pos", "0,0");
        levelExit.setAttribute("radius", "75");
        level.getLevel().add(levelExit);


        for (EditorObject object : level.getScene()) {
            object.update();
        }

        for (EditorObject object : level.getLevel()) {
            object.update();
        }

        for (EditorObject object : level.getResrc()) {
            object.update();
        }

        // Put everything in the hierarchy
        level.getSceneObject().getTreeItem().setExpanded(true);
        FXHierarchy.getHierarchy().setRoot(level.getSceneObject().getTreeItem());

        // Add items from the Scene to it
        FXPropertiesView.getPropertiesView().setRoot(FXPropertiesView.makePropertiesViewTreeItem(level.getSceneObject()));

        LevelTab levelSelectButton = FXLevelSelectPane.levelSelectButton(level);
        FXLevelSelectPane.getLevelSelectPane().getTabs().add(levelSelectButton);

        int numTabs = FXLevelSelectPane.getLevelSelectPane().getTabs().size();
        double tabSize = 1 / (numTabs + 1.0);
        FXLevelSelectPane.getLevelSelectPane().setTabMaxWidth(tabSize * (FXLevelSelectPane.getLevelSelectPane().getWidth() - 15) - 15);
        FXLevelSelectPane.getLevelSelectPane().setTabMinWidth(tabSize * (FXLevelSelectPane.getLevelSelectPane().getWidth() - 15) - 15);

        LevelUpdater.saveLevel(level);

        level.setLevelTab(levelSelectButton);
        level.setEditingStatus(LevelTab.NO_UNSAVED_CHANGES, true);
        FXLevelSelectPane.getLevelSelectPane().getSelectionModel().select(levelSelectButton);
        LevelManager.onSetLevel(level);
    }


    public static final ArrayList<String> failedResources = new ArrayList<>();


    public static void openLevel(String levelName, GameVersion version) {
        // Don't open a level if none selected
        if (levelName == null || levelName.isEmpty()) {
            return;
        }
        // Don't open a level if it's already open
        for (Tab tab : FXLevelSelectPane.getLevelSelectPane().getTabs()) {
            if (tab.getText().equals(levelName) && ((LevelTab)tab).getLevel().getVersion() == version) {
                FXLevelSelectPane.getLevelSelectPane().getSelectionModel().select(tab);
                return;
            }
        }
        failedResources.clear();

        LevelManager.setVersion(version);

        WorldLevel level;

        try {
            level = FileManager.openLevel(levelName, version);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            Alarms.errorMessage(e);
            return;
        }

        level.setLevelName(levelName);
        FXEditorButtons.updateAllButtons();
        FXMenu.updateAllButtons();

        FXLevelSelectPane.getLevelSelectPane().setMinHeight(30);
        FXLevelSelectPane.getLevelSelectPane().setMaxHeight(30);

        for (EditorObject object : level.getLevel()) {
            if (object instanceof BallInstance) {
                boolean alreadyIn = false;
                for (_Ball ball : BallManager.getImportedBalls()) {
                    if (ball.getObjects().get(0).getAttribute("name").stringValue().equals(object.getAttribute("type").stringValue())
                            && ball.getVersion() == level.getVersion()) {
                        alreadyIn = true;
                        break;
                    }
                }
                if (alreadyIn) continue;
                try {

                    _Ball ball2 = FileManager.openBall(object.getAttribute("type").stringValue(), version);

                    if (ball2 != null) {
                        ball2.setVersion(version);
                        BallManager.getImportedBalls().add(ball2);
                    }

                } catch (ParserConfigurationException | SAXException | IOException e) {
                    if (!failedResources.contains("Ball: " + object.getAttribute("type").stringValue() + " (version " + version + ")")) {
                        failedResources.add("Ball: " + object.getAttribute("type").stringValue() + " (version " + version + ")");
                    }
                }
            }
        }

        for (EditorObject object : level.getScene()) {
            object.update();
        }

        for (EditorObject object : level.getLevel()) {
            object.update();
        }

        for (EditorObject object : level.getResrc()) {
            object.update();
        }

        // Put everything in the hierarchy
        level.getSceneObject().getTreeItem().setExpanded(true);
        FXHierarchy.getHierarchy().setRoot(level.getSceneObject().getTreeItem());

        // Add items from the Scene to it
        FXPropertiesView.getPropertiesView().setRoot(FXPropertiesView.makePropertiesViewTreeItem(level.getSceneObject()));

        LevelTab levelSelectButton = FXLevelSelectPane.levelSelectButton(level);
        FXLevelSelectPane.getLevelSelectPane().getTabs().add(levelSelectButton);

        int numTabs = FXLevelSelectPane.getLevelSelectPane().getTabs().size();
        double tabSize = 1 / (numTabs + 1.0);
        FXLevelSelectPane.getLevelSelectPane().setTabMaxWidth(tabSize * (FXLevelSelectPane.getLevelSelectPane().getWidth() - 15) - 15);
        FXLevelSelectPane.getLevelSelectPane().setTabMinWidth(tabSize * (FXLevelSelectPane.getLevelSelectPane().getWidth() - 15) - 15);

        if (!failedResources.isEmpty()) {
            StringBuilder fullError = new StringBuilder();
            for (String resource : failedResources) {
                fullError.append("\n").append(resource);
            }
            Alarms.loadingResourcesError(fullError.substring(1));
        }

        level.setLevelTab(levelSelectButton);
        level.setEditingStatus(LevelTab.NO_UNSAVED_CHANGES, true);
        FXLevelSelectPane.getLevelSelectPane().getSelectionModel().select(levelSelectButton);
        LevelManager.onSetLevel(level);
    }


    public static void cloneLevel(String name, GameVersion version) {
        FXLevelSelectPane.getLevelSelectPane().setMinHeight(30);
        FXLevelSelectPane.getLevelSelectPane().setMaxHeight(30);

        ArrayList<EditorObject> sceneList = new ArrayList<>();
        ArrayList<EditorObject> levelList = new ArrayList<>();
        ArrayList<EditorObject> resourcesList = new ArrayList<>();
        ArrayList<EditorObject> addinList = new ArrayList<>();
        ArrayList<EditorObject> textList = new ArrayList<>();

        FileManager.supremeAddToList(resourcesList, ObjectUtil.deepClone(LevelManager.getLevel().getResrcObject(), null));
        FileManager.supremeAddToList(sceneList, ObjectUtil.deepClone(LevelManager.getLevel().getSceneObject(), null));
        FileManager.supremeAddToList(levelList, ObjectUtil.deepClone(LevelManager.getLevel().getLevelObject(), null));
        // Generate new addin object. idk why cloning it doesn't work, but this is arguably better anyway
        FileManager.supremeAddToList(addinList, BlankObjectGenerator.generateBlankAddinObject(name));
        FileManager.supremeAddToList(textList, ObjectUtil.deepClone(LevelManager.getLevel().getTextObject(), null));

        String oldLevelName = LevelManager.getLevel().getLevelName();
        WorldLevel level = new WorldLevel(sceneList, levelList, resourcesList, addinList, textList, version);

        level.setLevelName(name);
        FXEditorButtons.updateAllButtons();
        FXMenu.updateAllButtons();

        for (EditorObject object : level.getResrc()) {
            if (object instanceof Resources) {
                object.setAttribute("id", "scene_" + name);
            } else if (object instanceof ResrcImage || object instanceof Sound) {
                object.setAttribute("id", object.getAttribute("id").stringValue().replaceAll(oldLevelName.toUpperCase(), name.toUpperCase()));
            }
            object.update();
        }

        for (EditorObject object : level.getScene()) {
            if (object instanceof SceneLayer) {
                object.setAttribute("image", object.getAttribute("image").stringValue().replaceAll(oldLevelName, name));
            }
            object.update();
        }

        for (EditorObject object : level.getLevel()) {
            if (object instanceof Signpost) {
                object.setAttribute("image", object.getAttribute("image").stringValue().replaceAll(oldLevelName, name));
            }
            object.update();
        }

        // Put everything in the hierarchy
        level.getSceneObject().getTreeItem().setExpanded(true);
        FXHierarchy.getHierarchy().setRoot(level.getSceneObject().getTreeItem());

        // Add items from the Scene to it
        FXPropertiesView.getPropertiesView().setRoot(FXPropertiesView.makePropertiesViewTreeItem(level.getSceneObject()));

        LevelTab levelSelectButton = FXLevelSelectPane.levelSelectButton(level);
        FXLevelSelectPane.getLevelSelectPane().getTabs().add(levelSelectButton);

        int numTabs = FXLevelSelectPane.getLevelSelectPane().getTabs().size();
        double tabSize = 1 / (numTabs + 1.0);
        FXLevelSelectPane.getLevelSelectPane().setTabMaxWidth(tabSize * (FXLevelSelectPane.getLevelSelectPane().getWidth() - 15) - 15);
        FXLevelSelectPane.getLevelSelectPane().setTabMinWidth(tabSize * (FXLevelSelectPane.getLevelSelectPane().getWidth() - 15) - 15);

        LevelUpdater.saveLevel(level);

        level.setLevelTab(levelSelectButton);
        level.setEditingStatus(LevelTab.NO_UNSAVED_CHANGES, true);
        FXLevelSelectPane.getLevelSelectPane().getSelectionModel().select(levelSelectButton);
        LevelManager.onSetLevel(level);
    }


    public static void cloneLevel() {
        GameVersion version = LevelManager.getVersion();
        Alarms.askForLevelName("clone", version);
    }


}
