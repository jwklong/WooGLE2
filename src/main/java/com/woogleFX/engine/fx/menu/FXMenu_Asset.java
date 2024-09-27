package com.woogleFX.engine.fx.menu;

import com.woogleFX.engine.AssetManager;
import com.woogleFX.engine.gui.BallAssetSelector;
import com.woogleFX.engine.gui.LevelSelector;
import com.woogleFX.file.FileManager;
import com.woogleFX.gameData.level.GameVersion;
import com.woogleFX.gameData.level._Level;
import com.woogleFX.gameData.level.levelOpening.AssetLoader;
import com.woogleFX.gameData.level.levelSaving.AssetUpdater;
import javafx.scene.control.Menu;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FXMenu_Asset {

    private static final Menu levelMenu = new Menu();
    public static Menu getLevelMenu() {
        return levelMenu;
    }


    private static final FXMenu.EditorMenuItem newAnimationOldItem = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };
    private static final FXMenu.EditorMenuItem newBallOldItem = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };
    private static final FXMenu.EditorMenuItem newLevelOldItem = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXMenu.EditorMenuItem newParticleOldItem = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };

    private static final FXMenu.EditorMenuItem newAnimationNewItem = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };
    private static final FXMenu.EditorMenuItem newBallNewItem = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };
    private static final FXMenu.EditorMenuItem newLevelNewItem = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXMenu.EditorMenuItem newParticleNewItem = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };

    private static final FXMenu.EditorMenuItem newAnimation2Item = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };
    private static final FXMenu.EditorMenuItem newBall2Item = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXMenu.EditorMenuItem newEnvironment2Item = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };
    private static final FXMenu.EditorMenuItem newItem2Item = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };
    private static final FXMenu.EditorMenuItem newLevel2Item = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXMenu.EditorMenuItem newParticle2Item = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };
    private static final FXMenu.EditorMenuItem newTerrain2Item = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };


    private static final FXMenu.EditorMenuItem openAnimationOldItem = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };
    private static final FXMenu.EditorMenuItem openBallOldItem = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };
    private static final FXMenu.EditorMenuItem openLevelOldItem = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXMenu.EditorMenuItem openParticleOldItem = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };

    private static final FXMenu.EditorMenuItem openAnimationNewItem = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };
    private static final FXMenu.EditorMenuItem openBallNewItem = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };
    private static final FXMenu.EditorMenuItem openLevelNewItem = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXMenu.EditorMenuItem openParticleNewItem = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };

    private static final FXMenu.EditorMenuItem openAnimation2Item = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };
    private static final FXMenu.EditorMenuItem openBall2Item = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXMenu.EditorMenuItem openEnvironment2Item = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };
    private static final FXMenu.EditorMenuItem openItem2Item = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };
    private static final FXMenu.EditorMenuItem openLevel2Item = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXMenu.EditorMenuItem openParticle2Item = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };
    private static final FXMenu.EditorMenuItem openTerrain2Item = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
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

        Menu newMenu = new Menu("New Asset...");

        Menu newOldMenu = new Menu("World of Goo 1.3...");

        newAnimationOldItem.setIcon("ObjectIcons/level/camera.png");
        newAnimationOldItem.setOnAction(e -> AssetLoader.newAsset(GameVersion.VERSION_WOG1_OLD));
        newAnimationOldItem.setText("New Animation");
        newOldMenu.getItems().add(newAnimationOldItem);

        newBallOldItem.setIcon("ObjectIcons/assets/Ball.png");
        newBallOldItem.setOnAction(e -> AssetLoader.newAsset(GameVersion.VERSION_WOG1_OLD));
        newBallOldItem.setText("New Ball");
        newOldMenu.getItems().add(newBallOldItem);

        newLevelOldItem.setIcon("ObjectIcons/assets/Level.png");
        newLevelOldItem.setOnAction(e -> AssetLoader.newAsset(GameVersion.VERSION_WOG1_OLD));
        newLevelOldItem.setText("New Level");
        newOldMenu.getItems().add(newLevelOldItem);

        newParticleOldItem.setIcon("ObjectIcons/scene/particles.png");
        newParticleOldItem.setOnAction(e -> AssetLoader.newAsset(GameVersion.VERSION_WOG1_OLD));
        newParticleOldItem.setText("New Particle");
        newOldMenu.getItems().add(newParticleOldItem);

        newMenu.getItems().add(newOldMenu);

        Menu newNewMenu = new Menu("World of Goo 1.5...");

        newAnimationNewItem.setIcon("ObjectIcons/level/camera.png");
        newAnimationNewItem.setOnAction(e -> AssetLoader.newAsset(GameVersion.VERSION_WOG1_NEW));
        newAnimationNewItem.setText("New Animation");
        newNewMenu.getItems().add(newAnimationNewItem);

        newBallNewItem.setIcon("ObjectIcons/assets/Ball.png");
        newBallNewItem.setOnAction(e -> AssetLoader.newAsset(GameVersion.VERSION_WOG1_NEW));
        newBallNewItem.setText("New Ball");
        newNewMenu.getItems().add(newBallNewItem);

        newLevelNewItem.setIcon("ObjectIcons/assets/Level.png");
        newLevelNewItem.setOnAction(e -> AssetLoader.newAsset(GameVersion.VERSION_WOG1_NEW));
        newLevelNewItem.setText("New Level");
        newNewMenu.getItems().add(newLevelNewItem);

        newParticleNewItem.setIcon("ObjectIcons/scene/particles.png");
        newParticleNewItem.setOnAction(e -> AssetLoader.newAsset(GameVersion.VERSION_WOG1_NEW));
        newParticleNewItem.setText("New Particle");
        newNewMenu.getItems().add(newParticleNewItem);

        newMenu.getItems().add(newNewMenu);

        Menu new2Menu = new Menu("World of Goo 2...");

        newAnimation2Item.setIcon("ObjectIcons/level/camera.png");
        newAnimation2Item.setOnAction(e -> AssetLoader.newAsset(GameVersion.VERSION_WOG2));
        newAnimation2Item.setText("New Animation");
        new2Menu.getItems().add(newAnimation2Item);

        newBall2Item.setIcon("ObjectIcons/assets/Ball.png");
        newBall2Item.setOnAction(e -> AssetLoader.newAsset(GameVersion.VERSION_WOG2));
        newBall2Item.setText("New Ball");
        new2Menu.getItems().add(newBall2Item);

        newEnvironment2Item.setIcon("ObjectIcons/scene/SceneLayer.png");
        newEnvironment2Item.setOnAction(e -> AssetLoader.newAsset(GameVersion.VERSION_WOG2));
        newEnvironment2Item.setText("New Environment");
        new2Menu.getItems().add(newEnvironment2Item);

        newItem2Item.setIcon("ObjectIcons/scene/compositegeom.png");
        newItem2Item.setOnAction(e -> AssetLoader.newAsset(GameVersion.VERSION_WOG2));
        newItem2Item.setText("New Item");
        new2Menu.getItems().add(newItem2Item);

        newLevel2Item.setIcon("ObjectIcons/assets/Level.png");
        newLevel2Item.setOnAction(e -> AssetLoader.newAsset(GameVersion.VERSION_WOG2));
        newLevel2Item.setText("New Level");
        new2Menu.getItems().add(newLevel2Item);

        newParticle2Item.setIcon("ObjectIcons/scene/particles.png");
        newParticle2Item.setOnAction(e -> AssetLoader.newAsset(GameVersion.VERSION_WOG2));
        newParticle2Item.setText("New Particle");
        new2Menu.getItems().add(newParticle2Item);

        newTerrain2Item.setIcon("ObjectIcons/WoG2/TerrainBallInstance.png");
        newTerrain2Item.setOnAction(e -> AssetLoader.newAsset(GameVersion.VERSION_WOG2));
        newTerrain2Item.setText("New Terrain Type");
        new2Menu.getItems().add(newTerrain2Item);

        newMenu.getItems().add(new2Menu);

        newMenu.setGraphic(new ImageView(FileManager.getIcon(prefix + "new_level_2.png")));
        levelMenu.getItems().add(newMenu);

        Menu openMenu = new Menu("Open Asset...");

        Menu openOldMenu = new Menu("World of Goo 1.3...");

        openAnimationOldItem.setIcon("ObjectIcons/level/camera.png");
        openAnimationOldItem.setOnAction(e -> AssetLoader.openAsset(GameVersion.VERSION_WOG1_OLD));
        openAnimationOldItem.setText("Open Animation");
        openOldMenu.getItems().add(openAnimationOldItem);

        openBallOldItem.setIcon("ObjectIcons/assets/Ball.png");
        openBallOldItem.setOnAction(e -> new BallAssetSelector(GameVersion.VERSION_WOG1_OLD).start(new Stage()));
        openBallOldItem.setText("Open Ball");
        openOldMenu.getItems().add(openBallOldItem);

        openLevelOldItem.setIcon("ObjectIcons/assets/Level.png");
        openLevelOldItem.setOnAction(e -> new LevelSelector(GameVersion.VERSION_WOG1_OLD).start(new Stage()));
        openLevelOldItem.setText("Open Level");
        openOldMenu.getItems().add(openLevelOldItem);

        openParticleOldItem.setIcon("ObjectIcons/scene/particles.png");
        openParticleOldItem.setOnAction(e -> AssetLoader.openAsset(GameVersion.VERSION_WOG1_OLD));
        openParticleOldItem.setText("Open Particle");
        openOldMenu.getItems().add(openParticleOldItem);

        openMenu.getItems().add(openOldMenu);

        Menu openNewMenu = new Menu("World of Goo 1.5...");

        openAnimationNewItem.setIcon("ObjectIcons/level/camera.png");
        openAnimationNewItem.setOnAction(e -> AssetLoader.openAsset(GameVersion.VERSION_WOG1_NEW));
        openAnimationNewItem.setText("Open Animation");
        openNewMenu.getItems().add(openAnimationNewItem);

        openBallNewItem.setIcon("ObjectIcons/assets/Ball.png");
        openBallNewItem.setOnAction(e -> new BallAssetSelector(GameVersion.VERSION_WOG1_NEW).start(new Stage()));
        openBallNewItem.setText("Open Ball");
        openNewMenu.getItems().add(openBallNewItem);

        openLevelNewItem.setIcon("ObjectIcons/assets/Level.png");
        openLevelNewItem.setOnAction(e -> new LevelSelector(GameVersion.VERSION_WOG1_NEW).start(new Stage()));
        openLevelNewItem.setText("Open Level");
        openNewMenu.getItems().add(openLevelNewItem);

        openParticleNewItem.setIcon("ObjectIcons/scene/particles.png");
        openParticleNewItem.setOnAction(e -> AssetLoader.openAsset(GameVersion.VERSION_WOG1_NEW));
        openParticleNewItem.setText("Open Particle");
        openNewMenu.getItems().add(openParticleNewItem);

        openMenu.getItems().add(openNewMenu);

        Menu open2Menu = new Menu("World of Goo 2...");

        openAnimation2Item.setIcon("ObjectIcons/level/camera.png");
        openAnimation2Item.setOnAction(e -> AssetLoader.openAsset(GameVersion.VERSION_WOG2));
        openAnimation2Item.setText("Open Animation");
        open2Menu.getItems().add(openAnimation2Item);

        openBall2Item.setIcon("ObjectIcons/assets/Ball.png");
        openBall2Item.setOnAction(e -> new BallAssetSelector(GameVersion.VERSION_WOG2).start(new Stage()));
        openBall2Item.setText("Open Ball");
        open2Menu.getItems().add(openBall2Item);

        openEnvironment2Item.setIcon("ObjectIcons/scene/SceneLayer.png");
        openEnvironment2Item.setOnAction(e -> AssetLoader.openAsset(GameVersion.VERSION_WOG2));
        openEnvironment2Item.setText("Open Environment");
        open2Menu.getItems().add(openEnvironment2Item);

        openItem2Item.setIcon("ObjectIcons/scene/compositegeom.png");
        openItem2Item.setOnAction(e -> AssetLoader.openAsset(GameVersion.VERSION_WOG2));
        openItem2Item.setText("Open Item");
        open2Menu.getItems().add(openItem2Item);

        openLevel2Item.setIcon("ObjectIcons/assets/Level.png");
        openLevel2Item.setOnAction(e -> new LevelSelector(GameVersion.VERSION_WOG2).start(new Stage()));
        openLevel2Item.setText("Open Level");
        open2Menu.getItems().add(openLevel2Item);

        openParticle2Item.setIcon("ObjectIcons/scene/particles.png");
        openParticle2Item.setOnAction(e -> AssetLoader.openAsset(GameVersion.VERSION_WOG2));
        openParticle2Item.setText("Open Particle");
        open2Menu.getItems().add(openParticle2Item);

        openTerrain2Item.setIcon("ObjectIcons/WoG2/TerrainBallInstance.png");
        openTerrain2Item.setOnAction(e -> AssetLoader.openAsset(GameVersion.VERSION_WOG2));
        openTerrain2Item.setText("Open Terrain Type");
        open2Menu.getItems().add(openTerrain2Item);

        openMenu.getItems().add(open2Menu);

        openMenu.setGraphic(new ImageView(FileManager.getIcon("ButtonIcons/Level/open_lvl_2.png")));
        levelMenu.getItems().add(openMenu);

        cloneLevelItem.setText("Clone Asset...");
        cloneLevelItem.setIcon(prefix + "clone_lvl.png");
        cloneLevelItem.setOnAction(e -> AssetLoader.cloneLevel());
        levelMenu.getItems().add(cloneLevelItem);

        saveLevelItem.setText("Save Asset");
        saveLevelItem.setIcon(prefix + "save.png");
        saveLevelItem.setOnAction(e -> AssetUpdater.saveAsset(AssetManager.getAsset()));
        levelMenu.getItems().add(saveLevelItem);

        saveAllLevelsItem.setText("Save All Assets");
        saveAllLevelsItem.setIcon(prefix + "save_all.png");
        saveAllLevelsItem.setOnAction(e -> AssetUpdater.saveAll());
        levelMenu.getItems().add(saveAllLevelsItem);

        saveAndPlayLevelItem.setText("Save and Play Asset");
        saveAndPlayLevelItem.setIcon(prefix + "play.png");
        saveAndPlayLevelItem.setOnAction(e -> AssetManager.getAsset().play());
        levelMenu.getItems().add(saveAndPlayLevelItem);

        renameLevelItem.setText("Rename Asset");
        renameLevelItem.setIcon(prefix + "rename.png");
        renameLevelItem.setOnAction(e -> AssetUpdater.renameLevel((_Level) AssetManager.getAsset()));
        levelMenu.getItems().add(renameLevelItem);

        deleteLevelItem.setText("Delete Asset");
        deleteLevelItem.setIcon(prefix + "delete.png");
        deleteLevelItem.setOnAction(e -> AssetUpdater.deleteLevel((_Level) AssetManager.getAsset()));
        levelMenu.getItems().add(deleteLevelItem);

    }

}
