package com.woogleFX.gameData.level;

import com.woogleFX.editorObjects.Asset;
import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.ObjectUtil;
import com.woogleFX.editorObjects.objectComponents.ObjectComponent;
import com.woogleFX.editorObjects.objectCreators.BlankObjectGenerator;
import com.woogleFX.editorObjects.objectCreators.ObjectCreator;
import com.woogleFX.engine.AssetManager;
import com.woogleFX.engine.fx.editorButtons.FXEditorButtons;
import com.woogleFX.engine.fx.FXPropertiesView;
import com.woogleFX.engine.fx.FXStage;
import com.woogleFX.engine.fx.assetSelectPane.FXAssetSelectPane;
import com.woogleFX.engine.fx.hierarchy.FXHierarchy;
import com.woogleFX.engine.fx.hierarchy.FXHierarchySwitcherButtons;
import com.woogleFX.engine.fx.menu.FXMenu;
import com.woogleFX.engine.gui.alarms.ErrorAlarm;
import com.woogleFX.file.FileManager;
import com.woogleFX.file.fileExport.GoomodExporter;
import com.woogleFX.file.resourceManagers.BaseGameResources;
import com.woogleFX.file.resourceManagers.ResourceManager;
import com.woogleFX.gameData.ball._Ball;
import com.woogleFX.gameData.level.levelSaving.AssetVerifier;
import com.woogleFX.gameData.level.levelSaving.AssetUpdater;
import com.worldOfGoo.level.*;
import com.worldOfGoo.resrc.Resources;
import com.worldOfGoo.resrc.ResrcImage;
import com.worldOfGoo.resrc.Sound;
import com.worldOfGoo.scene.Label;
import com.worldOfGoo.scene.Scene;
import com.worldOfGoo.scene.SceneLayer;
import com.worldOfGoo.text.TextString;
import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.FileChooser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/** A level from World of Goo 1.
 * Has scene, level, and resrc as different files. */
public class WOG1Level extends _Level {

    private static final Logger logger = LoggerFactory.getLogger(WOG1Level.class);


    private final ArrayList<EditorObject> scene;
    public ArrayList<EditorObject> getScene() {
        return scene;
    }
    public EditorObject getSceneObject() {
        return scene.get(0);
    }

    private final ArrayList<EditorObject> level;
    public ArrayList<EditorObject> getLevel() {
        return level;
    }
    public EditorObject getLevelObject() {
        return level.get(0);
    }

    private final ArrayList<EditorObject> resrc;
    public ArrayList<EditorObject> getResrc() {
        return resrc;
    }
    public EditorObject getResrcObject() {
        return resrc.get(0);
    }

    private final ArrayList<EditorObject> text;
    public ArrayList<EditorObject> getText() {
        return text;
    }
    public EditorObject getTextObject() {
        return text.get(0);
    }


    public WOG1Level(ArrayList<EditorObject> scene,
                  ArrayList<EditorObject> level,
                  ArrayList<EditorObject> resrc,
                  ArrayList<EditorObject> addin,
                  ArrayList<EditorObject> text,
                  GameVersion version) {

        super(version, addin);

        this.scene = scene;
        this.level = level;
        this.resrc = resrc;
        this.text = text;

        AssetManager.setAsset(this);

        for (EditorObject object : addin) object.getTreeItem().setExpanded(true);
        for (EditorObject object : text) object.getTreeItem().setExpanded(true);

        reAssignSetDefaultsToAllResources();
        resetCamera();

        for (EditorObject editorObject : scene)
            if (editorObject instanceof Label label) {
                tryToAddText(label.getAttribute("text").stringValue());
            }

        for (EditorObject editorObject : level)
            if (editorObject instanceof Signpost signpost) {
                tryToAddText(signpost.getAttribute("text").stringValue());
            }

    }


    private void tryToAddText(String id) {
        TextString textString;
        try {
            textString = ResourceManager.getText(null, id, getVersion());
        } catch (FileNotFoundException ignored) {
            return;
        }
        boolean notAlreadyHere = false;
        for (EditorObject object : text) {
            if (object instanceof TextString) {
                if (object.getAttribute("id").stringValue().equals(id)) {
                    notAlreadyHere = true;
                    break;
                }
            }
        }
        if (!notAlreadyHere) {
            ObjectUtil.deepClone(textString, text.get(0));
            text.add(textString);
        }
    }

    @Override
    public boolean verifyAll() {
        return AssetVerifier.verifyAllObjects(scene) &&
                AssetVerifier.verifyAllObjects(level) &&
                AssetVerifier.verifyAllObjects(resrc) &&
                AssetVerifier.verifyAllObjects(text) &&
                AssetVerifier.verifyAllObjects(getAddin());
    }

    @Override
    public boolean save() {
        try {
            LevelWriter.saveAsXML(this, FileManager.getGameDir(getVersion()) + "/res/levels/" + getLevelName(),
                    getVersion(), false, true);
            return true;
        } catch (IOException e) {
            ErrorAlarm.show(e);
            return false;
        }
    }

    @Override
    public void export(boolean includeAddinInfo) {
        String dir = FileManager.getGameDir(getVersion());

        FileChooser fileChooser = new FileChooser();
        if (!Files.exists(Path.of((dir + "/res/levels/" + getLevelName() + "/goomod")))) {
            try {
                Files.createDirectories(Path.of((dir + "/res/levels/" + getLevelName() + "/goomod")));
            } catch (Exception e) {
                ErrorAlarm.show(e);
            }
        }
        fileChooser.setInitialDirectory(new File((dir + "/res/levels/" + getLevelName() + "/goomod")));
        fileChooser.setInitialFileName(getLevelName());

        FileChooser.ExtensionFilter goomodFilter = new FileChooser.ExtensionFilter("World of Goo mod (*.goomod)", "*.goomod");
        fileChooser.getExtensionFilters().add(goomodFilter);
        File export = fileChooser.showSaveDialog(FXStage.getStage());

        ArrayList<_Level> levels = new ArrayList<>();
        levels.add(this);

        ArrayList<_Ball> balls = new ArrayList<>();
        for (EditorObject object : level)
            if (object instanceof BallInstance ballInstance)
                if (!balls.contains(ballInstance.getBall())) balls.add(ballInstance.getBall());

        if (export != null) {
            try {
                GoomodExporter.exportGoomod(export, levels, balls, getVersion(), includeAddinInfo);
            } catch (IOException e) {
                logger.error("", e);
            }
        }
    }

    @Override
    public void play() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    FileManager.getGameDir(GameVersion.VERSION_WOG1_OLD) + "/WorldOfGoo.exe", getLevelName());
            processBuilder.directory(new File(FileManager.getGameDir(GameVersion.VERSION_WOG1_OLD)));
            processBuilder.start();
        } catch (Exception e) {
            ErrorAlarm.show(e);
        }
    }

    @Override
    public void delete() {
        try {
            AssetUpdater.nuke(new File(FileManager.getGameDir(getVersion()) + "/res/levels/" + getLevelName()));
            TabPane levelSelectPane = FXAssetSelectPane.getAssetSelectPane();
            if (levelSelectPane.getTabs().size() == 1) {
                FXAssetSelectPane.getAssetSelectPane().setMinHeight(0);
                FXAssetSelectPane.getAssetSelectPane().setMaxHeight(0);
                // If all tabs are closed, clear the side pane
                FXHierarchy.getHierarchy().setRoot(null);
                // Clear the properties pane too
                FXPropertiesView.changeTableView(new EditorObject[]{});
            }
            levelSelectPane.getTabs().remove(levelSelectPane.getSelectionModel().getSelectedItem());
        } catch (IOException e) {
            ErrorAlarm.show(e);
        }
    }

    @Override
    public boolean isScaleTooFar(double scaleX, double scaleY) {
        return (scaleX < 0.001 || scaleX > 1000 || scaleY < 0.001 || scaleY > 1000);
    }

    @Override
    public void setUpTabs() {

        TabPane hierarchySwitcherButtons = FXHierarchySwitcherButtons.getHierarchySwitcherButtons();
        hierarchySwitcherButtons.getTabs().clear();

        // Create the three buttons.
        Tab scene = new Tab("Scene");
        Tab level = new Tab("Level");
        Tab resrc = new Tab("Resrc");
        Tab text = new Tab("Text");
        Tab addin = new Tab("Addin");

        hierarchySwitcherButtons.getTabs().addAll(scene, level, resrc, text, addin);
        hierarchySwitcherButtons.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        hierarchySwitcherButtons.setMinHeight(30);
        hierarchySwitcherButtons.setMaxHeight(30);
        hierarchySwitcherButtons.setPrefHeight(30);
        hierarchySwitcherButtons.setPadding(new Insets(-6, -6, -6, -6));

        hierarchySwitcherButtons.getSelectionModel().selectedItemProperty().addListener((observableValue, tab, t1) -> {

            if (AssetManager.getAsset() == null) return;

            WOG1Level _level = (WOG1Level) AssetManager.getAsset();

            EditorObject rootObject;
            if (t1 == scene) rootObject = _level.getScene().get(0);
            else if (t1 == level) rootObject = _level.getLevel().get(0);
            else if (t1 == resrc) rootObject = _level.getResrc().get(0);
            else if (t1 == text) rootObject = _level.getText().get(0);
            else if (t1 == addin) rootObject = _level.getAddin().get(0);
            else return;

            FXHierarchy.getHierarchy().setRoot(rootObject.getTreeItem());
            FXHierarchy.getHierarchy().refresh();
            FXHierarchy.getHierarchy().getRoot().setExpanded(true);
            _level.setCurrentlySelectedSection(t1.getText());
            FXHierarchy.getHierarchy().setShowRoot(true);

        });
    }

    @Override
    public EditorObject getObjectWithComponent(ObjectComponent objectComponent) {
        for (EditorObject EditorObject : level) {
            if (List.of(EditorObject.getObjectComponents()).contains(objectComponent)) return EditorObject;
        }
        for (EditorObject EditorObject : scene) {
            if (List.of(EditorObject.getObjectComponents()).contains(objectComponent)) return EditorObject;
        }
        for (EditorObject EditorObject : resrc) {
            if (List.of(EditorObject.getObjectComponents()).contains(objectComponent)) return EditorObject;
        }
        for (EditorObject EditorObject : getAddin()) {
            if (List.of(EditorObject.getObjectComponents()).contains(objectComponent)) return EditorObject;
        }
        for (EditorObject EditorObject : text) {
            if (List.of(EditorObject.getObjectComponents()).contains(objectComponent)) return EditorObject;
        }
        return null;
    }

    @Override
    public Asset clone(String name) {
        ArrayList<EditorObject> sceneList = new ArrayList<>();
        ArrayList<EditorObject> levelList = new ArrayList<>();
        ArrayList<EditorObject> resourcesList = new ArrayList<>();
        ArrayList<EditorObject> addinList = new ArrayList<>();
        ArrayList<EditorObject> textList = new ArrayList<>();

        FileManager.supremeAddToList(resourcesList, ObjectUtil.deepClone(getResrcObject(), null));
        FileManager.supremeAddToList(sceneList, ObjectUtil.deepClone(getSceneObject(), null));
        FileManager.supremeAddToList(levelList, ObjectUtil.deepClone(getLevelObject(), null));
        // Generate new addin object. idk why cloning it doesn't work, but this is arguably better anyway
        FileManager.supremeAddToList(addinList, BlankObjectGenerator.generateBlankAddinObject(name, getVersion()));
        FileManager.supremeAddToList(textList, ObjectUtil.deepClone(getTextObject(), null));

        String oldLevelName = AssetManager.getAsset().getLevelName();
        WOG1Level level = new WOG1Level(sceneList, levelList, resourcesList, addinList, textList, getVersion());

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
        FXPropertiesView.getPropertiesView().setRoot(FXPropertiesView.makePropertiesViewTreeItem(new EditorObject[]{level.getSceneObject()}));

        return level;

    }

    @Override
    public void addItem(EditorObject _item, int row) {
        EditorObject absoluteParent = AssetManager.getAsset().getSelected().length == 0 ? null : AssetManager.getAsset().getSelected()[0];
        if (absoluteParent == null) absoluteParent = ObjectCreator.getDefaultParent(_item.getType());
        else while (absoluteParent.getParent() != null) absoluteParent = absoluteParent.getParent();

        if (absoluteParent instanceof Scene) scene.add(_item);
        if (absoluteParent instanceof Level) level.add(_item);

        if (_item instanceof BallInstance ballInstance) {

            String id = ballInstance.getAttribute("id").stringValue();

            for (EditorObject EditorObject : level)
                if (EditorObject instanceof Strand strand) {

                    String gb1 = strand.getAttribute("gb1").stringValue();
                    if (gb1.equals(id)) {
                        strand.setGoo1(ballInstance);
                        strand.update();
                    }

                    String gb2 = strand.getAttribute("gb2").stringValue();
                    if (gb2.equals(id)) {
                        strand.setGoo2(ballInstance);
                        strand.update();
                    }

                }

        } else if (_item instanceof Vertex vertex) vertex.getParent().update();
    }

    @Override
    public void removeItem(EditorObject _item, boolean parentDeleted) {
        scene.remove(_item);
        level.remove(_item);
        resrc.remove(_item);
        getAddin().remove(_item);
        text.remove(_item);

        if (!parentDeleted) {
            _item.getParent().getChildren().remove(_item);
            _item.getParent().getTreeItem().getChildren().remove(_item.getTreeItem());
        }

        if (_item instanceof BallInstance ballInstance) {

            String id = ballInstance.getAttribute("id").stringValue();

            for (EditorObject EditorObject : level)
                if (EditorObject instanceof Strand strand) {

                    String gb1 = strand.getAttribute("gb1").stringValue();
                    if (gb1.equals(id)) {
                        strand.setGoo1(null);
                        strand.update();
                    }

                    String gb2 = strand.getAttribute("gb2").stringValue();
                    if (gb2.equals(id)) {
                        strand.setGoo2(null);
                        strand.update();
                    }

                }

        } else if (_item instanceof Vertex vertex) vertex.getParent().update();
    }

    @Override
    public void updateSelectedTab() {
        EditorObject rootObject = switch (getCurrentlySelectedSection()) {
            case "Scene" -> getSceneObject();
            case "Level" -> getLevelObject();
            case "Resrc" -> getResrcObject();
            case "Text" -> getTextObject();
            case "Addin" -> getAddinObject();
            default -> null;
        };
        if (rootObject == null) return;
        FXHierarchy.getHierarchy().setRoot(rootObject.getTreeItem());

        int i = switch (getCurrentlySelectedSection()) {
            case "Scene" -> 0;
            case "Level" -> 1;
            case "Resrc" -> 2;
            case "Text" -> 3;
            case "Addin" -> 4;
            default -> -1;
        };
        FXHierarchySwitcherButtons.getHierarchySwitcherButtons().getSelectionModel().select(i);

    }

    @Override
    public void load() {

        for (EditorObject object : scene) {
            object.update();
            object.onLoaded();
        }

        for (EditorObject object : level) {
            object.update();
            object.onLoaded();
        }

        for (EditorObject object : resrc) {
            object.update();
            object.onLoaded();
        }

        // Put everything in the hierarchy
        getSceneObject().getTreeItem().setExpanded(true);
        FXHierarchy.getHierarchy().setRoot(getSceneObject().getTreeItem());

        // Add items from the Scene to it
        FXPropertiesView.getPropertiesView().setRoot(FXPropertiesView.makePropertiesViewTreeItem(new EditorObject[]{getSceneObject()}));

    }

    @Override
    public boolean isBaseGame() {
        return BaseGameResources.LEVELS.get(getVersion()).contains(getLevelName());
    }

}
