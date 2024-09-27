package com.woogleFX.engine.gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.woogleFX.editorObjects.Asset;
import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.objectCreators.BlankObjectGenerator;
import com.woogleFX.editorObjects.objectCreators.ObjectCreator;
import com.woogleFX.engine.fx.FXPropertiesView;
import com.woogleFX.engine.fx.editorButtons.FXEditorButtons;
import com.woogleFX.engine.fx.hierarchy.FXHierarchy;
import com.woogleFX.engine.fx.menu.FXMenu;
import com.woogleFX.file.FileManager;
import com.woogleFX.file.resourceManagers.BaseGameResources;
import com.woogleFX.gameData.level.WOG1Level;
import com.woogleFX.gameData.level.WOG2Level;
import com.woogleFX.gameData.level.levelOpening.AssetLoader;
import com.woogleFX.gameData.level.GameVersion;
import com.worldOfGoo2.level._2_Level;
import com.worldOfGoo2.misc._2_Point;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

public class LevelSelector extends AssetSelector {

    public LevelSelector(GameVersion version) {
        super(version);
    }

    @Override
    public String getTitle() {
        return "level";
    }

    @Override
    public List<String> getItems() {
        List<String> levels = new ArrayList<>();
        for (File child : Objects.requireNonNull(new File(FileManager.getGameDir(getVersion()) + "/res/levels").listFiles())) {
            if (getVersion() == GameVersion.VERSION_WOG1_OLD || getVersion() == GameVersion.VERSION_WOG1_NEW) {
                levels.add(child.getName());
            } else if (child.getName().endsWith(".wog2")) {
                levels.add(child.getName().substring(0, child.getName().length() - 5));
            }
        }
        return levels;
    }

    @Override
    public boolean isOriginal(String item) {
        return BaseGameResources.LEVELS.get(getVersion()).contains(item);
    }

    @Override
    public Asset newInstance(String name) {
        if (getVersion() == GameVersion.VERSION_WOG1_OLD || getVersion() == GameVersion.VERSION_WOG1_NEW) {
            ArrayList<EditorObject> sceneList = new ArrayList<>();
            sceneList.add(ObjectCreator.create("scene", null, getVersion()));
            ArrayList<EditorObject> levelList = new ArrayList<>();
            levelList.add(ObjectCreator.create("level", null, getVersion()));
            ArrayList<EditorObject> resourcesList = new ArrayList<>();
            resourcesList.add(ObjectCreator.create("ResourceManifest", null, getVersion()));
            ArrayList<EditorObject> addinList = new ArrayList<>();
            addinList.add(BlankObjectGenerator.generateBlankAddinObject(name, getVersion()));
            ArrayList<EditorObject> textList = new ArrayList<>();
            textList.add(ObjectCreator.create("strings", null, getVersion()));

            WOG1Level level = new WOG1Level(sceneList, levelList, resourcesList, addinList, textList, getVersion());
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

            EditorObject resourcesThing = ObjectCreator.create("Resources", level.getResrcObject(), getVersion());
            assert resourcesThing != null;
            resourcesThing.setAttribute("id", "scene_" + level.getLevelName());
            resourcesThing.getTreeItem().setExpanded(true);
            level.getResrc().add(resourcesThing);

            EditorObject linearForceField = ObjectCreator.create("linearforcefield", level.getSceneObject(), getVersion());
            assert linearForceField != null;
            linearForceField.setAttribute("type", "gravity");
            linearForceField.setAttribute("force", "0,-10");
            linearForceField.setAttribute("dampeningfactor", "0");
            linearForceField.setAttribute("antigrav", "true");
            level.getScene().add(linearForceField);

            EditorObject lineRight = ObjectCreator.create("line", level.getSceneObject(), getVersion());
            assert lineRight != null;
            lineRight.setAttribute("id", "right");
            lineRight.setAttribute("anchor", "500,300");
            lineRight.setAttribute("normal", "-1,0");
            lineRight.setAttribute("tag", "detaching");
            level.getScene().add(lineRight);

            EditorObject lineLeft = ObjectCreator.create("line", level.getSceneObject(), getVersion());
            assert lineLeft != null;
            lineLeft.setAttribute("id", "left");
            lineLeft.setAttribute("anchor", "-500,300");
            lineLeft.setAttribute("normal", "1,0");
            lineLeft.setAttribute("tag", "detaching");
            level.getScene().add(lineLeft);

            EditorObject lineGround = ObjectCreator.create("line", level.getSceneObject(), getVersion());
            assert lineGround != null;
            lineGround.setAttribute("id", "ground");
            lineGround.setAttribute("anchor", "0,20");
            lineGround.setAttribute("normal", "0,1");
            level.getScene().add(lineGround);

            EditorObject cameraNormal = ObjectCreator.create("camera", level.getLevelObject(), getVersion());
            assert cameraNormal != null;
            cameraNormal.setAttribute("aspect", "normal");
            cameraNormal.setAttribute("endpos", "0,0");
            cameraNormal.setAttribute("endzoom", "1");
            level.getLevel().add(cameraNormal);

            EditorObject poiNormal = ObjectCreator.create("poi", cameraNormal, getVersion());
            assert poiNormal != null;
            poiNormal.setAttribute("pos", "0,0");
            poiNormal.setAttribute("zoom", "1");
            poiNormal.setAttribute("pause", "0");
            poiNormal.setAttribute("traveltime", "0");
            level.getLevel().add(poiNormal);

            EditorObject cameraWidescreen = ObjectCreator.create("camera", level.getLevelObject(), getVersion());
            assert cameraWidescreen != null;
            cameraWidescreen.setAttribute("aspect", "widescreen");
            cameraWidescreen.setAttribute("endpos", "0,0");
            cameraWidescreen.setAttribute("endzoom", "1");
            level.getLevel().add(cameraWidescreen);

            EditorObject poiWidescreen = ObjectCreator.create("poi", cameraWidescreen, getVersion());
            assert poiWidescreen != null;
            poiWidescreen.setAttribute("pos", "0,0");
            poiWidescreen.setAttribute("zoom", "1");
            poiWidescreen.setAttribute("pause", "0");
            poiWidescreen.setAttribute("traveltime", "0");
            level.getLevel().add(poiWidescreen);

            EditorObject levelExit = ObjectCreator.create("levelexit", level.getLevelObject(), getVersion());
            assert levelExit != null;
            levelExit.setAttribute("id", "theExit");
            levelExit.setAttribute("pos", "0,0");
            levelExit.setAttribute("radius", "75");
            level.getLevel().add(levelExit);


            for (EditorObject object : level.getScene()) {
                object.update();
                object.onLoaded();
            }

            for (EditorObject object : level.getLevel()) {
                object.update();
                object.onLoaded();
            }

            for (EditorObject object : level.getResrc()) {
                object.update();
                object.onLoaded();
            }

            // Put everything in the hierarchy
            level.getSceneObject().getTreeItem().setExpanded(true);
            FXHierarchy.getHierarchy().setRoot(level.getSceneObject().getTreeItem());

            // Add items from the Scene to it
            FXPropertiesView.getPropertiesView().setRoot(FXPropertiesView.makePropertiesViewTreeItem(new EditorObject[]{level.getSceneObject()}));

            return level;

        } else {

            ArrayList<EditorObject> objects = new ArrayList<>();
            EditorObject levelObject = ObjectCreator.create2(_2_Level.class, null, getVersion());
            EditorObject topRight = ObjectCreator.create2(_2_Point.class, levelObject, getVersion());
            topRight.setAttribute("x", 5);
            topRight.setAttribute("y", 5);
            topRight.setTypeID("boundsTopRight");
            EditorObject bottomLeft = ObjectCreator.create2(_2_Point.class, levelObject, getVersion());
            bottomLeft.setAttribute("x", -5);
            bottomLeft.setAttribute("y", -5);
            bottomLeft.setTypeID("boundsBottomLeft");
            objects.add(levelObject);
            objects.add(topRight);
            objects.add(bottomLeft);
            levelObject.setAttribute("boundsBottomLeft", "-5,-5");
            levelObject.setAttribute("boundsTopRight", "5,5");
            levelObject.setAttribute("version", 2);
            levelObject.setAttribute("type", 0);
            levelObject.setAttribute("uuid", "12345");
            levelObject.setAttribute("title", "My Test Level");
            levelObject.setAttribute("environmentId", 0);
            levelObject.setAttribute("backgroundId", "");

            EditorObject gravity = ObjectCreator.create2(_2_Point.class, levelObject, getVersion());
            gravity.setAttribute("x", 0);
            gravity.setAttribute("y", -10);
            gravity.setTypeID("gravity");
            levelObject.setAttribute("gravity", "0,-10");

            EditorObject initialCameraPos = ObjectCreator.create2(_2_Point.class, levelObject, getVersion());
            initialCameraPos.setAttribute("x", 0);
            initialCameraPos.setAttribute("y", 0);
            initialCameraPos.setTypeID("initialCameraPos");
            levelObject.setAttribute("initialCameraPos", "0,0");

            levelObject.setAttribute("initialCameraZoom", 1);
            levelObject.setAttribute("cameraAutoBounds", "false");
            levelObject.setAttribute("ballsRateRequired", 50);
            levelObject.setAttribute("musicId", "");
            levelObject.setAttribute("ambienceId", "");
            levelObject.setAttribute("musicOffset", 0);
            levelObject.setAttribute("ambienceOffset", 0);
            levelObject.setAttribute("pretickSeconds", 0);
            levelObject.setAttribute("enableTimeBugs", false);

            ArrayList<EditorObject> addinList = new ArrayList<>();
            addinList.add(BlankObjectGenerator.generateBlankAddinObject(name, getVersion()));

            WOG2Level level = new WOG2Level(objects, addinList);
            level.setLevelName(name);
            FXEditorButtons.updateAllButtons();
            FXMenu.updateAllButtons();

            for (EditorObject object : level.getObjects()) {
                object.update();
                object.onLoaded();
            }

            // Put everything in the hierarchy
            level.getLevel().getTreeItem().setExpanded(true);
            FXHierarchy.getHierarchy().setRoot(level.getLevel().getTreeItem());

            // Add items from the Scene to it
            FXPropertiesView.getPropertiesView().setRoot(FXPropertiesView.makePropertiesViewTreeItem(new EditorObject[]{level.getLevel()}));

            return level;

        }
    }

    @Override
    public Asset openInstance(String name) {
        try {
            return FileManager.openLevel(name, getVersion());
        } catch (ParserConfigurationException | SAXException | IOException e) {
            return null;
        }
    }

}
