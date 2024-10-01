package com.woogleFX.gameData.level;

import com.woogleFX.editorObjects.Asset;
import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.objectComponents.ObjectComponent;
import com.woogleFX.editorObjects.objectCreators.BlankObjectGenerator;
import com.woogleFX.engine.AssetManager;
import com.woogleFX.engine.fx.FXContainers;
import com.woogleFX.engine.fx.editorButtons.FXEditorButtons;
import com.woogleFX.engine.fx.FXPropertiesView;
import com.woogleFX.engine.fx.assetSelectPane.FXAssetSelectPane;
import com.woogleFX.engine.fx.hierarchy.FXHierarchy;
import com.woogleFX.engine.fx.hierarchy.FXHierarchySwitcherButtons;
import com.woogleFX.engine.fx.menu.FXMenu;
import com.woogleFX.engine.gui.LoadingScreen;
import com.woogleFX.engine.gui.alarms.ErrorAlarm;
import com.woogleFX.file.FileManager;
import com.woogleFX.file.fileExport.GOOWriter;
import com.woogleFX.file.fileExport.Goo2modExporter;
import com.woogleFX.file.fileExport.XMLUtility;
import com.woogleFX.file.fileImport.ObjectGOOParser;
import com.woogleFX.file.resourceManagers.BaseGameResources;
import com.woogleFX.gameData.level.levelSaving.AssetVerifier;
import com.worldOfGoo2.level._2_Level;
import com.worldOfGoo2.level._2_Level_BallInstance;
import com.worldOfGoo2.level._2_Level_TerrainGroup;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/** A level from World of Goo 2.
 * What... is this? */
public class WOG2Level extends _Level {

    private static final Logger logger = LoggerFactory.getLogger(WOG2Level.class);

    private final _2_Level level;
    public _2_Level getLevel() {
        return level;
    }


    private final ArrayList<EditorObject> objects;
    public ArrayList<EditorObject> getObjects() {
        return objects;
    }

    public WOG2Level(ArrayList<EditorObject> objects, ArrayList<EditorObject> addin) {
        super(GameVersion.VERSION_WOG2, addin);

        this.objects = objects;

        this.level = (_2_Level)objects.get(0);

        setCurrentlySelectedSection("Terrain");

        AssetManager.setAsset(this);

        resetCamera();

    }

    @Override
    public void resetCamera() {
        EditorObject boundsBottomLeft = level.getChildren("boundsBottomLeft").get(0);
        EditorObject boundsTopRight = level.getChildren("boundsTopRight").get(0);

        double sceneWidth = boundsTopRight.getAttribute("x").doubleValue() - boundsBottomLeft.getAttribute("x").doubleValue();
        double sceneHeight = boundsTopRight.getAttribute("y").doubleValue() - boundsBottomLeft.getAttribute("y").doubleValue();
        double middleX = (boundsBottomLeft.getAttribute("x").doubleValue() + boundsTopRight.getAttribute("x").doubleValue()) / 2;
        double middleY = (boundsBottomLeft.getAttribute("y").doubleValue() + boundsTopRight.getAttribute("y").doubleValue()) / 2;

        double canvasWidth = FXContainers.getSplitPane().getDividers().get(0).getPosition() * FXContainers.getSplitPane().getWidth();
        double canvasHeight = FXContainers.getSplitPane().getHeight();

        setOffsetX(canvasWidth / 2 - middleX);
        setOffsetY(canvasHeight / 2 + middleY);

        double zoomX = canvasWidth / sceneWidth;
        double zoomY = canvasHeight / sceneHeight;

        setZoom(Math.min(Math.abs(zoomX), Math.abs(zoomY)));

        setOffsetX((getOffsetX() - canvasWidth / 2) * getZoom() + canvasWidth / 2);
        setOffsetY((getOffsetY() - canvasHeight / 2) * getZoom() + canvasHeight / 2);
    }

    @Override
    public boolean verifyAll() {
        return AssetVerifier.verifyAllObjects(objects);
    }

    @Override
    public boolean save() {

        StringBuilder export = new StringBuilder();
        GOOWriter.recursiveGOOExport(export, getLevel(), 0);
        EditorObject addinObject = getAddinObject();
        String addin = XMLUtility.fullAddinXMLExport("", addinObject, 0);

        try {
            Files.write(Path.of(FileManager.getGameDir(getVersion()) + "/res/levels/" + getLevelName() + ".addin.xml"), Collections.singleton(addin), StandardCharsets.UTF_8);
            Files.writeString(Path.of(FileManager.getGameDir(getVersion()) + "/res/levels/" + getLevelName() + ".wog2"), export.toString());
            return true;
        } catch (IOException e) {
            ErrorAlarm.show(e);
            return false;
        }
    }

    @Override
    public void export(boolean includeAddinInfo) {
        try {
            Goo2modExporter.exportGoo2mod(this, includeAddinInfo);
        } catch (IOException e) {
            logger.error("", e);
        }
    }

    @Override
    public void play() {
        // TODO figure something out to play in 1.5
        ErrorAlarm.show("Playing is only supported for 1.3. :(");
    }

    @Override
    public void delete() {
        try {
            Files.delete(Path.of(FileManager.getGameDir(GameVersion.VERSION_WOG2) + "/res/levels/" + getLevelName() + ".wog2"));
        } catch (IOException e) {
            ErrorAlarm.show(e);
        }
    }

    @Override
    public boolean isScaleTooFar(double scaleX, double scaleY) {
        return (scaleX < 3 || scaleX > 100000 || scaleY < 3 || scaleY > 100000);
    }

    @Override
    public void setUpTabs() {

        TabPane hierarchySwitcherButtons = FXHierarchySwitcherButtons.getHierarchySwitcherButtons();
        hierarchySwitcherButtons.getTabs().clear();

        // Create the three buttons.
        Tab terrain = new Tab("Terrain");
        Tab terrainGroups = new Tab("Terrain Groups");
        Tab balls = new Tab("Balls");
        Tab items = new Tab("Items");
        Tab pins = new Tab("Pins");
        Tab camera = new Tab("Camera");
        Tab addin = new Tab("Addin");

        hierarchySwitcherButtons.getTabs().addAll(terrain, terrainGroups, balls, items, pins, camera, addin);
        hierarchySwitcherButtons.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        hierarchySwitcherButtons.setMinHeight(30);
        hierarchySwitcherButtons.setMaxHeight(30);
        hierarchySwitcherButtons.setPrefHeight(30);
        hierarchySwitcherButtons.setPadding(new Insets(-6, -6, -6, -6));

        hierarchySwitcherButtons.getSelectionModel().selectedItemProperty().addListener((observableValue, tab, t1) -> {

            if (t1 == null) return;

            TreeItem<EditorObject> root = level.getTreeItem();
            FXHierarchy.getHierarchy().setRoot(root);

            root.getChildren().clear();

            for (EditorObject child : level.getChildren()) {

                if ((child.getType().equals("BallInstance") && child.getAttribute("type").stringValue().equals("Terrain")) && t1 == terrain) root.getChildren().add(child.getTreeItem());
                else if (child instanceof _2_Level_TerrainGroup && t1 == terrainGroups) root.getChildren().add(child.getTreeItem());
                else if (((child.getType().equals("BallInstance") && !child.getAttribute("type").stringValue().equals("Terrain")) || child.getType().equals("Strand")) && t1 == balls) root.getChildren().add(child.getTreeItem());
                else if (child.getType().equals("Item") && t1 == items) root.getChildren().add(child.getTreeItem());
                else if (child.getType().equals("Pin") && t1 == pins) root.getChildren().add(child.getTreeItem());
                else if (child.getType().equals("CameraKeyFrame") && t1 == camera) root.getChildren().add(child.getTreeItem());

            }

            if (t1 == addin) root.getChildren().add(getAddin().get(0).getTreeItem());

            FXHierarchy.getHierarchy().refresh();
            FXHierarchy.getHierarchy().getRoot().setExpanded(true);
            setCurrentlySelectedSection(t1.getText());
            FXHierarchy.getHierarchy().setShowRoot(true);

        });

    }

    @Override
    public EditorObject getObjectWithComponent(ObjectComponent objectComponent) {
        for (EditorObject editorObject : objects) {
            if (List.of(editorObject.getObjectComponents()).contains(objectComponent)) return editorObject;
        }
        return null;
    }

    @Override
    public Asset clone(String name) {
        StringBuilder levelExport = new StringBuilder();
        GOOWriter.recursiveGOOExport(levelExport, getLevel(), 0);
        EditorObject levelObject = ObjectGOOParser.read(_2_Level.class, levelExport.toString(), "level");
        ArrayList<EditorObject> objects = new ArrayList<>();
        Stack<EditorObject> toAdd = new Stack<>();
        toAdd.push(levelObject);
        while (!toAdd.isEmpty()) {
            EditorObject thisObject = toAdd.pop();
            objects.add(thisObject);
            for (EditorObject child : thisObject.getChildren()) {
                toAdd.push(child);
            }

        }

        ArrayList<EditorObject> addinList = new ArrayList<>();
        // Generate new addin object. IDK why cloning it doesn't work, but this is arguably better anyway
        FileManager.supremeAddToList(addinList, BlankObjectGenerator.generateBlankAddinObject(name, getVersion()));

        WOG2Level level = new WOG2Level(objects, addinList);

        for (EditorObject object : level.getObjects()) {
            object.update();
            object.onLoaded();
        }

        //FileManager.supremeAddToList(objectsList, ObjectUtil.deepClone(before.getLevel(), null));


        level.setLevelName(name);
        FXEditorButtons.updateAllButtons();
        FXMenu.updateAllButtons();

        // Put everything in the hierarchy
        level.getLevel().getTreeItem().setExpanded(true);
        FXHierarchy.getHierarchy().setRoot(level.getLevel().getTreeItem());
        // Add items from the Scene to it
        FXPropertiesView.getPropertiesView().setRoot(FXPropertiesView.makePropertiesViewTreeItem(new EditorObject[]{level.getLevel()}));

        return level;

    }

    @Override
    public void addItem(EditorObject _item, int row) {

        objects.add(_item);

        updateSelectedTab();

        FXHierarchy.getHierarchy().getSelectionModel().clearSelection();
        FXHierarchy.getHierarchy().getSelectionModel().select(_item.getTreeItem());

    }

    @Override
    public void removeItem(EditorObject _item, boolean parentDeleted) {
        objects.remove(_item);

        if (!parentDeleted) {
            _item.getParent().getChildren().remove(_item);
            _item.getParent().getTreeItem().getChildren().remove(_item.getTreeItem());
        }
    }

    @Override
    public void updateSelectedTab() {
        int i = switch (getCurrentlySelectedSection()) {
            case "Terrain" -> 0;
            case "Terrain Groups" -> 1;
            case "Balls" -> 2;
            case "Items" -> 3;
            case "Pins" -> 4;
            case "Camera" -> 5;
            case "Addin" -> 6;
            default -> -1;
        };
        FXHierarchySwitcherButtons.getHierarchySwitcherButtons().getSelectionModel().select((i + 1) % 7);
        FXHierarchySwitcherButtons.getHierarchySwitcherButtons().getSelectionModel().select(i);

    }

    @Override
    public void load() {
        System.out.println("I STARTED LOADING!!");
        LoadingScreen loadingScreen = new LoadingScreen();

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {

                long count = objects.size();

                long i = 0;
                try {
                    for (EditorObject object : objects.toArray(new EditorObject[0])) {
                        object.onLoaded();
                        object.update();
                        i++;
                        updateProgress(i, count);
                    }
                } catch (Exception e) {
                    logger.error("", e);
                }

                for (EditorObject object : objects) {
                    object.postInit();
                }

                System.out.println("Performance 1:");

                for (EditorObject ball : getLevel().getChildren("balls")) {
                    EditorObject terrainBall = getLevel().getChildren("terrainBalls").remove(0);
                    getObjects().remove(terrainBall);
                    getLevel().getChildren().remove(terrainBall);
                    ball.setAttribute("terrainGroup", terrainBall.getAttribute("group").stringValue());
                }

                System.out.println("Finished.");

                return null;
            }
        };

        loadingScreen.setAssetName("Level");
        loadingScreen.setTask(task);
        Stage stage = new Stage();
        loadingScreen.start(stage);
        task.setOnSucceeded(event -> {
            FXHierarchySwitcherButtons.getHierarchySwitcherButtons().getSelectionModel().select(1);
            FXHierarchySwitcherButtons.getHierarchySwitcherButtons().getSelectionModel().select(0);
            stage.close();
        });
        task.setOnCancelled(event -> {
            FXHierarchySwitcherButtons.getHierarchySwitcherButtons().getSelectionModel().select(1);
            FXHierarchySwitcherButtons.getHierarchySwitcherButtons().getSelectionModel().select(0);
            stage.close();
        });
        task.setOnFailed(event -> {
            FXHierarchySwitcherButtons.getHierarchySwitcherButtons().getSelectionModel().select(1);
            FXHierarchySwitcherButtons.getHierarchySwitcherButtons().getSelectionModel().select(0);
            stage.close();
        });
        new Thread(task).start();

        stage.setOnCloseRequest(event -> {
            task.cancel();
            FXAssetSelectPane.getAssetSelectPane().getTabs().remove(getAssetTab());
        });

        level.getTreeItem().setExpanded(true);
        FXHierarchy.getHierarchy().setRoot(level.getTreeItem());

        FXPropertiesView.getPropertiesView().setRoot(FXPropertiesView.makePropertiesViewTreeItem(new EditorObject[]{level}));

        FXHierarchySwitcherButtons.getHierarchySwitcherButtons().getSelectionModel().select(0);
        System.out.println("I FINISHED LOADING!!");

    }

    @Override
    public boolean isBaseGame() {
        return BaseGameResources.LEVELS.get(getVersion()).contains(getLevelName());
    }

}
