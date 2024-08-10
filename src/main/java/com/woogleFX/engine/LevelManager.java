package com.woogleFX.engine;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.engine.fx.*;
import com.woogleFX.engine.fx.hierarchy.FXHierarchy;
import com.woogleFX.engine.renderer.Renderer;
import com.woogleFX.gameData.level.GameVersion;
import com.woogleFX.gameData.level.WOG1Level;
import com.woogleFX.gameData.level.WOG2Level;
import com.woogleFX.gameData.level._Level;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Affine;

public class LevelManager {

    private static _Level level = null;
    public static _Level getLevel() {
        return level;
    }
    public static void setLevel(_Level _level) {
        level = _level;
        FXEditorButtons.updateAllButtons();
        FXMenu.updateAllButtons();

        if (level != null) {

            // Transform the canvas according to the updated translation and scale.
            Renderer.t = new Affine();
            Renderer.t.appendTranslation(level.getOffsetX(), level.getOffsetY());
            Renderer.t.appendScale(level.getZoom(), level.getZoom());

        }

        onSetLevel(level);
        Renderer.draw();
    }

    public static void onSetLevel(_Level level) {

        VBox vBox = FXContainers.getvBox();

        vBox.getChildren().remove(2);

        if (level == null) {
            FXStage.getStage().setTitle("World of Goo Anniversary Editor");
            vBox.getChildren().add(2, FXEditorButtons.getNullGooballsToolbar());
            return;
        }

        if (level.getVersion() == GameVersion.VERSION_WOG1_OLD) {
            vBox.getChildren().add(2, FXEditorButtons.getOldGooballsToolbar());
        } else if (level.getVersion() == GameVersion.VERSION_WOG1_NEW) {
            vBox.getChildren().add(2, FXEditorButtons.getNewGooballsToolbar());
        } else {
            vBox.getChildren().add(2, FXEditorButtons.getSequelGooballsToolbar());
        }

        if (level instanceof WOG1Level) {
            FXContainers.getViewPane().getChildren().remove(0);
            FXContainers.getViewPane().getChildren().add(0, FXHierarchy.getHierarchySwitcherButtons());
        } else if (level instanceof WOG2Level wog2Level) {
            FXContainers.getViewPane().getChildren().remove(0);
            FXContainers.getViewPane().getChildren().add(0, FXHierarchy.getNewHierarchySwitcherButtons());
            FXEditorButtons.updateTerrainGroupSelector(wog2Level);
        }

        String levelName = level.getLevelName() + " (version " + level.getVersion() + ")";
        FXStage.getStage().setTitle(levelName + " — World of Goo Anniversary Editor");

        level.getVisibilitySettings().updateButtons();

        // TODO: implement multiple-object handling in the properties view. good luck
        if (level.getSelected().length == 0) FXPropertiesView.changeTableView(new EditorObject[]{});
        else FXPropertiesView.changeTableView(level.getSelected());

        SelectionManager.goToSelectedInHierarchy();

    }

}
