package com.woogleFX.editorObjects;

import com.woogleFX.editorObjects.objectComponents.ObjectComponent;
import com.woogleFX.engine.SelectionManager;
import com.woogleFX.engine.fx.AssetTab;
import com.woogleFX.engine.fx.hierarchy.FXHierarchy;
import com.woogleFX.engine.undoHandling.userActions.UserAction;
import com.woogleFX.gameData.level.GameVersion;
import com.woogleFX.gameData.level.VisibilitySettings;

import java.util.Arrays;
import java.util.Stack;

public abstract class Asset {

    public final Stack<UserAction[]> redoActions = new Stack<>();
    public final Stack<UserAction[]> undoActions = new Stack<>();


    private double offsetX = 0;
    public final double getOffsetX() {
        return offsetX;
    }
    public final void setOffsetX(double offsetX) {
        this.offsetX = offsetX;
    }


    private double offsetY = 0;
    public final double getOffsetY() {
        return offsetY;
    }
    public final void setOffsetY(double offsetY) {
        this.offsetY = offsetY;
    }


    private double zoom = 1;
    public final double getZoom() {
        return zoom;
    }
    public final void setZoom(double zoom) {
        this.zoom = zoom;
    }


    private final VisibilitySettings visibilitySettings = new VisibilitySettings();
    public final VisibilitySettings getVisibilitySettings() {
        return visibilitySettings;
    }


    private String levelName;
    public final String getLevelName() {
        return levelName;
    }
    public final void setLevelName(String levelName) {
        this.levelName = levelName;
    }


    private int editingStatus;
    public final int getEditingStatus() {
        return editingStatus;
    }
    public final void setEditingStatus(int editingStatus, boolean shouldSelect) {
        this.editingStatus = editingStatus;
        getAssetTab().update(editingStatus, shouldSelect);
    }


    private EditorObject[] selected = new EditorObject[]{};
    public final EditorObject[] getSelected() {
        return selected;
    }
    public final void setSelected(EditorObject[] selected) {
        this.selected = selected;
        SelectionManager.goToSelectedInHierarchy();
    }
    public final void clearSelection() {
        selected = new EditorObject[]{};
        FXHierarchy.getHierarchy().getSelectionModel().clearSelection();
    }
    public final boolean isSelected(EditorObject EditorObject) {
        return Arrays.stream(selected).anyMatch(e -> e == EditorObject);
    }


    private int lastSavedUndoPosition = 0;
    public final int getLastSavedUndoPosition() {
        return lastSavedUndoPosition;
    }
    public final void setLastSavedUndoPosition(int position) {
        this.lastSavedUndoPosition = position;
    }

    private AssetTab assetTab;
    public final AssetTab getAssetTab() {
        return assetTab;
    }
    public final void setAssetTab(AssetTab assetTab) {
        this.assetTab = assetTab;
    }


    private final GameVersion version;
    public final GameVersion getVersion() {
        return version;
    }

    public Asset(GameVersion version) {
        this.version = version;
    }


    private String currentlySelectedSection = "Scene";
    public final String getCurrentlySelectedSection() {
        return currentlySelectedSection;
    }
    public final void setCurrentlySelectedSection(String s) {
        this.currentlySelectedSection = s;
    }


    public abstract void resetCamera();

    public abstract boolean verifyAll();

    public abstract boolean save();

    public abstract void export(boolean includeAddinInfo);

    public abstract void play();

    public abstract void delete();

    public abstract boolean isScaleTooFar(double scaleX, double scaleY);

    public abstract void setUpTabs();

    public abstract EditorObject getObjectWithComponent(ObjectComponent objectComponent);

    public abstract Asset clone(String name);

    public abstract void addItem(EditorObject _item, int row);

    public abstract void removeItem(EditorObject _item, boolean parentDeleted);

    public abstract void updateSelectedTab();

    public abstract void load();

    public abstract boolean isBaseGame();

}
