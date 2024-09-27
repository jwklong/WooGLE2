package com.woogleFX.engine;

import com.woogleFX.editorObjects.Asset;
import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.engine.fx.*;
import com.woogleFX.engine.fx.editorButtons.FXEditorButtons;
import com.woogleFX.engine.fx.editorButtons.FXEditorButtons_Resources;
import com.woogleFX.engine.fx.editorButtons.FXEditorButtons_ShowHide;
import com.woogleFX.engine.fx.menu.FXMenu;
import com.woogleFX.engine.renderer.Renderer;
import com.woogleFX.gameData.level.GameVersion;
import com.woogleFX.gameData.level.WOG2Level;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Affine;

/** Keeps track of the current asset. */
public class AssetManager {

    /** The current asset. */
    private static Asset asset = null;
    public static Asset getAsset() {
        return asset;
    }
    public static void setAsset(Asset asset) {
        AssetManager.asset = asset;
        FXEditorButtons.updateAllButtons();
        FXMenu.updateAllButtons();

        if (asset != null) {

            // Transform the canvas according to the updated translation and scale.
            Renderer.t = new Affine();
            Renderer.t.appendTranslation(asset.getOffsetX(), asset.getOffsetY());
            Renderer.t.appendScale(asset.getZoom(), asset.getZoom());

        }

        onSetAsset(asset);
        Renderer.draw();
    }


    public static void onSetAsset(Asset asset) {

        VBox vBox = FXContainers.getvBox();

        vBox.getChildren().remove(2);

        if (asset == null) {
            FXStage.getStage().setTitle("World of Goo Anniversary Editor");
            vBox.getChildren().add(2, FXEditorButtons.getNullGooballsToolbar());
            return;
        }

        if (asset.getVersion() == GameVersion.VERSION_WOG1_OLD) {
            vBox.getChildren().add(2, FXEditorButtons.getOldGooballsToolbar());
        } else if (asset.getVersion() == GameVersion.VERSION_WOG1_NEW) {
            vBox.getChildren().add(2, FXEditorButtons.getNewGooballsToolbar());
        } else {
            vBox.getChildren().add(2, FXEditorButtons.getSequelGooballsToolbar());
        }

        if (asset instanceof WOG2Level wog2Level) {
            FXEditorButtons_ShowHide.updateTerrainGroupSelector(wog2Level);
            FXEditorButtons_Resources.updateItemsSelector(wog2Level);
        }

        asset.setUpTabs();

        String levelName = asset.getLevelName() + " (version " + asset.getVersion() + ")";
        FXStage.getStage().setTitle(levelName + " — World of Goo Anniversary Editor");

        asset.getVisibilitySettings().updateButtons();

        // TODO: implement multiple-object handling in the properties view. good luck
        if (asset.getSelected().length == 0) FXPropertiesView.changeTableView(new EditorObject[]{});
        else FXPropertiesView.changeTableView(asset.getSelected());

        SelectionManager.goToSelectedInHierarchy();

    }

}
