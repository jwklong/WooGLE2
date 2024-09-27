package com.woogleFX.engine.fx.editorButtons;

import com.woogleFX.editorObjects.objectCreators.ObjectAdder;
import com.woogleFX.engine.AssetManager;
import com.woogleFX.gameData.items.ItemManager;
import com.woogleFX.gameData.level.LevelResourceImporter;
import com.woogleFX.gameData.level.LevelResourceManager;
import com.woogleFX.gameData.level.WOG2Level;
import com.woogleFX.gameData.level._Level;
import com.worldOfGoo2.level._2_Level_Item;
import com.worldOfGoo2.util.ItemHelper;
import javafx.scene.control.*;

public class FXEditorButtons_Resources {

    private static final FXEditorButtons.EditorButton buttonUpdateLevelResources = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton buttonImportImages = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton buttonAddTextResource = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton buttonCleanResources = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton buttonSetMusic = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton buttonSetLoopsound = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton buttonAddItem = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };

    public static void resources(ToolBar toolBar) {

        String prefix = "ButtonIcons/Resources/";

        buttonUpdateLevelResources.setIcon(prefix + "update_level_resources.png");
        buttonUpdateLevelResources.setOnAction(e -> LevelResourceManager.updateLevelResources((_Level) AssetManager.getAsset()));
        buttonUpdateLevelResources.setTooltip(new FXEditorButtons.DelayedTooltip("Update Level Resources"));
        toolBar.getItems().add(buttonUpdateLevelResources);

        buttonImportImages.setIcon(prefix + "import_img.png");
        buttonImportImages.setOnAction(e -> LevelResourceImporter.importImages((_Level) AssetManager.getAsset()));
        buttonImportImages.setTooltip(new FXEditorButtons.DelayedTooltip("Import Images"));
        toolBar.getItems().add(buttonImportImages);

        buttonAddTextResource.setIcon(prefix + "add_text_resource.png");
        buttonAddTextResource.setOnAction(e -> LevelResourceManager.newTextResource((_Level) AssetManager.getAsset()));
        buttonAddTextResource.setTooltip(new FXEditorButtons.DelayedTooltip("Add Text Resource"));
        toolBar.getItems().add(buttonAddTextResource);

        MenuButton menuButton = new MenuButton("Add Items");
        buttonAddItem.setGraphic(menuButton);
        buttonAddItem.setTooltip(new FXEditorButtons.DelayedTooltip("Add Items"));
        toolBar.getItems().add(buttonAddItem);

        toolBar.getItems().add(new Separator());

        buttonCleanResources.setIcon(prefix + "clean_level_resources.png");
        buttonCleanResources.setOnAction(e -> LevelResourceManager.cleanLevelResources((_Level) AssetManager.getAsset()));
        buttonCleanResources.setTooltip(new FXEditorButtons.DelayedTooltip("Clean Level Resources"));
        toolBar.getItems().add(buttonCleanResources);

        toolBar.getItems().add(new Separator());

        buttonSetMusic.setIcon(prefix + "import_music.png");
        buttonSetMusic.setOnAction(e -> LevelResourceImporter.importMusic((_Level) AssetManager.getAsset()));
        buttonSetMusic.setTooltip(new FXEditorButtons.DelayedTooltip("Set Music"));
        toolBar.getItems().add(buttonSetMusic);

        buttonSetLoopsound.setIcon(prefix + "import_soundloop.png");
        buttonSetLoopsound.setOnAction(e -> LevelResourceImporter.importLoopsound((_Level) AssetManager.getAsset()));
        buttonSetLoopsound.setTooltip(new FXEditorButtons.DelayedTooltip("Set Loop Sound"));
        toolBar.getItems().add(buttonSetLoopsound);

    }


    public static void updateItemsSelector(WOG2Level wog2Level) {
        MenuButton content = (MenuButton) buttonAddItem.getGraphic();
        content.getItems().clear();
        for (var entry : ItemHelper.itemTypeMap.entrySet()) {
            Menu item = new Menu(entry.getValue());
            for (var loadedItemEntry : ItemManager.itemMap.entrySet()) {
                if (loadedItemEntry.getValue().getAttribute("type").intValue() == entry.getKey()) {
                    MenuItem sub = new MenuItem(loadedItemEntry.getKey());
                    sub.setOnAction(e -> {
                        var object = ObjectAdder.addObject2(_2_Level_Item.class, wog2Level.getLevel().getPossibleChildrenTypeIDs()[3], wog2Level.getLevel());
                        object.setAttribute("type", loadedItemEntry.getKey());
                    });
                    item.getItems().add(sub);
                }
            }
            content.getItems().add(item);
        }
    }

}
