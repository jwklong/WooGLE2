package com.woogleFX.engine.fx.menu;

import com.woogleFX.engine.AssetManager;
import com.woogleFX.gameData.ball.ballOpening.BallLoader;
import com.woogleFX.gameData.level.GameVersion;
import com.woogleFX.gameData.level._Level;
import com.woogleFX.gameData.level.levelOpening.LevelLoader;
import com.woogleFX.gameData.level.levelSaving.LevelUpdater;
import javafx.scene.control.Menu;

public class FXMenu_Asset {

    private static final Menu levelMenu = new Menu();
    public static Menu getLevelMenu() {
        return levelMenu;
    }


    private static final FXMenu.EditorMenuItem newLevelOldItem = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXMenu.EditorMenuItem newLevelNewItem = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXMenu.EditorMenuItem newLevel2Item = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXMenu.EditorMenuItem openLevelOldItem = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXMenu.EditorMenuItem openLevelNewItem = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXMenu.EditorMenuItem openLevel2Item = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXMenu.EditorMenuItem openBall2Item = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXMenu.EditorMenuItem cloneLevelItem = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXMenu.EditorMenuItem saveLevelItem = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXMenu.EditorMenuItem saveAllLevelsItem = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXMenu.EditorMenuItem saveAndPlayLevelItem = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXMenu.EditorMenuItem renameLevelItem = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXMenu.EditorMenuItem deleteLevelItem = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };


    public static void init() {

        levelMenu.setText("Level");

        String prefix = "ButtonIcons/Level/";

        Menu oldMenu = new Menu("World of Goo 1.3...");

        newLevelOldItem.setText("New Level...");
        newLevelOldItem.setIcon(prefix + "new_lvl_old.png");
        newLevelOldItem.setOnAction(e -> LevelLoader.newLevel(GameVersion.VERSION_WOG1_OLD));
        oldMenu.getItems().add(newLevelOldItem);

        openLevelOldItem.setText("Open Level...");
        openLevelOldItem.setIcon(prefix + "open_lvl_old.png");
        openLevelOldItem.setOnAction(e -> LevelLoader.openLevel(GameVersion.VERSION_WOG1_OLD));
        oldMenu.getItems().add(openLevelOldItem);

        levelMenu.getItems().add(oldMenu);


        Menu newMenu = new Menu("World of Goo 1.5...");

        newLevelNewItem.setText("New Level...");
        newLevelNewItem.setIcon(prefix + "new_lvl_new.png");
        newLevelNewItem.setOnAction(e -> LevelLoader.newLevel(GameVersion.VERSION_WOG1_NEW));
        newMenu.getItems().add(newLevelNewItem);

        openLevelNewItem.setText("Open Level...");
        openLevelNewItem.setIcon(prefix + "open_lvl_new.png");
        openLevelNewItem.setOnAction(e -> LevelLoader.openLevel(GameVersion.VERSION_WOG1_NEW));
        newMenu.getItems().add(openLevelNewItem);

        levelMenu.getItems().add(newMenu);


        Menu sequelMenu = new Menu("World of Goo 2...");

        newLevel2Item.setText("New Level...");
        newLevel2Item.setIcon(prefix + "new_level_2.png");
        newLevel2Item.setOnAction(e -> LevelLoader.newLevel(GameVersion.VERSION_WOG2));
        sequelMenu.getItems().add(newLevel2Item);

        openLevel2Item.setText("Open Level...");
        openLevel2Item.setIcon(prefix + "open_lvl_new.png");
        openLevel2Item.setOnAction(e -> LevelLoader.openLevel(GameVersion.VERSION_WOG2));
        sequelMenu.getItems().add(openLevel2Item);

        openBall2Item.setText("Open Ball...");
        openBall2Item.setIcon(prefix + "open_lvl_new.png");
        openBall2Item.setOnAction(e -> BallLoader.openBall(GameVersion.VERSION_WOG2));
        sequelMenu.getItems().add(openBall2Item);

        levelMenu.getItems().add(sequelMenu);


        cloneLevelItem.setText("Clone Level...");
        cloneLevelItem.setIcon(prefix + "clone_lvl.png");
        cloneLevelItem.setOnAction(e -> LevelLoader.cloneLevel());
        levelMenu.getItems().add(cloneLevelItem);

        saveLevelItem.setText("Save Level");
        saveLevelItem.setIcon(prefix + "save.png");
        saveLevelItem.setOnAction(e -> LevelUpdater.saveLevel(AssetManager.getAsset()));
        levelMenu.getItems().add(saveLevelItem);

        saveAllLevelsItem.setText("Save All Levels");
        saveAllLevelsItem.setIcon(prefix + "save_all.png");
        saveAllLevelsItem.setOnAction(e -> LevelUpdater.saveAll());
        levelMenu.getItems().add(saveAllLevelsItem);

        saveAndPlayLevelItem.setText("Save and Play Level");
        saveAndPlayLevelItem.setIcon(prefix + "play.png");
        saveAndPlayLevelItem.setOnAction(e -> AssetManager.getAsset().play());
        levelMenu.getItems().add(saveAndPlayLevelItem);

        renameLevelItem.setText("Rename Level");
        renameLevelItem.setIcon(prefix + "rename.png");
        renameLevelItem.setOnAction(e -> LevelUpdater.renameLevel((_Level) AssetManager.getAsset()));
        levelMenu.getItems().add(renameLevelItem);

        deleteLevelItem.setText("Delete Level");
        deleteLevelItem.setIcon(prefix + "delete.png");
        deleteLevelItem.setOnAction(e -> LevelUpdater.deleteLevel((_Level) AssetManager.getAsset()));
        levelMenu.getItems().add(deleteLevelItem);

    }

}
