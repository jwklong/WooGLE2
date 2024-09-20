package com.woogleFX.gameData.ball;

import com.woogleFX.editorObjects.Asset;
import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.objectComponents.ObjectComponent;
import com.woogleFX.engine.fx.hierarchy.FXHierarchy;
import com.woogleFX.engine.fx.hierarchy.FXHierarchySwitcherButtons;
import com.woogleFX.engine.gui.alarms.ErrorAlarm;
import com.woogleFX.file.FileManager;
import com.woogleFX.file.fileExport.GOOWriter;
import com.woogleFX.file.fileExport.XMLUtility;
import com.woogleFX.gameData.level.GameVersion;
import com.woogleFX.gameData.level.levelSaving.AssetVerifier;
import com.worldOfGoo.resrc.*;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _2Ball extends Asset {

    @Override
    public void resetCamera() {

        setOffsetX(0);
        setOffsetY(0);
        setZoom(100);

    }


    public final ArrayList<EditorObject> objects;
    public ArrayList<EditorObject> getObjects() {
        return objects;
    }


    public final ArrayList<EditorObject> resources;
    public ArrayList<EditorObject> getResources() {
        return resources;
    }


    private String shapeType;
    public String getShapeType() {
        return shapeType;
    }
    public void setShapeType(String shapeType) {
        this.shapeType = shapeType;
    }


    private double width;
    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }

    private double height;
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }

    private double sizeVariance;
    public double getSizeVariance() {
        return sizeVariance;
    }
    public void setSizeVariance(double sizeVariance) {
        this.sizeVariance = sizeVariance;
    }


    public _2Ball(ArrayList<EditorObject> _objects, ArrayList<EditorObject> resources) {
        super(GameVersion.VERSION_WOG2);

        this.objects = _objects;
        this.resources = resources;

        shapeType = "circle";

        width = _objects.get(0).getAttribute("width").doubleValue();
        height = _objects.get(0).getAttribute("height").doubleValue();
        sizeVariance = _objects.get(0).getAttribute("sizeVariance").doubleValue();

        SetDefaults currentSetDefaults = null;

        for (EditorObject EditorObject : resources) {

            if (EditorObject instanceof SetDefaults setDefaults) {
                currentSetDefaults = setDefaults;
            }

            else if (EditorObject instanceof FlashAnim flashAnim) {
                flashAnim.setSetDefaults(currentSetDefaults);
            } else if (EditorObject instanceof ResrcImage resrcImage) {
                resrcImage.setSetDefaults(currentSetDefaults);
            } else if (EditorObject instanceof Sound sound) {
                sound.setSetDefaults(currentSetDefaults);
            } else if (EditorObject instanceof Font font) {
                font.setSetDefaults(currentSetDefaults);
            }

        }

    }

    @Override
    public boolean verifyAll() {
        return AssetVerifier.verifyAllObjects(objects);
    }

    @Override
    public boolean save() {

        StringBuilder export = new StringBuilder();
        GOOWriter.recursiveGOOExport(export, getObjects().get(0), 0);

        try {
            Files.writeString(Path.of(FileManager.getGameDir(getVersion()) + "/res/balls/" + getLevelName() + "/ball.wog2"), export.toString());
            return true;
        } catch (IOException e) {
            ErrorAlarm.show(e);
            return false;
        }

    }

    @Override
    public void export(boolean includeAddinInfo) {
        System.out.println("Exporting ball");
    }

    @Override
    public void play() {
        System.out.println("Playing ball (?????)");
    }

    @Override
    public void delete() {

    }

    @Override
    public boolean isScaleTooFar(double scaleX, double scaleY) {
        return false;
    }

    @Override
    public void setUpTabs() {

        TabPane hierarchySwitcherButtons = FXHierarchySwitcherButtons.getHierarchySwitcherButtons();
        hierarchySwitcherButtons.getTabs().clear();

        Tab addin = new Tab("Addin");
        Tab addin2 = new Tab("Addin");

        hierarchySwitcherButtons.getTabs().addAll(addin, addin2);

        hierarchySwitcherButtons.getSelectionModel().selectedItemProperty().addListener((observableValue, tab, t1) -> {

            TreeItem<EditorObject> root = getObjects().get(0).getTreeItem();
            FXHierarchy.getHierarchy().setRoot(root);

            root.getChildren().clear();

            for (EditorObject child : getObjects()) {
                if (child != getObjects().get(0))
                    root.getChildren().add(child.getTreeItem());
            }

            FXHierarchy.getHierarchy().refresh();
            FXHierarchy.getHierarchy().getRoot().setExpanded(true);
            if (t1 != null) setCurrentlySelectedSection(t1.getText());
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
        return null;
    }

    @Override
    public void addItem(EditorObject _item, int row) {

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

    }

    @Override
    public void load() {

    }

}
