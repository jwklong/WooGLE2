package com.woogleFX.engine.fx.editorButtons;

import com.woogleFX.engine.AssetManager;
import com.woogleFX.engine.fx.menu.FXMenu;
import com.woogleFX.engine.gui.BallAssetSelector;
import com.woogleFX.engine.gui.LevelSelector;
import com.woogleFX.gameData.level.GameVersion;
import com.woogleFX.gameData.level.levelOpening.AssetLoader;
import com.woogleFX.gameData.level.levelSaving.AssetUpdater;
import javafx.scene.control.Menu;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;

public class FXEditorButtons_Asset {

    private static final FXEditorButtons.EditorMenuButton buttonNew = new FXEditorButtons.EditorMenuButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorMenuButton buttonOpen = new FXEditorButtons.EditorMenuButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };


    private static final FXMenu.EditorMenuItem buttonNewAnimationOld = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };
    private static final FXMenu.EditorMenuItem buttonNewBallOld = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };
    private static final FXMenu.EditorMenuItem buttonNewLevelOld = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXMenu.EditorMenuItem buttonNewParticleOld = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };

    private static final FXMenu.EditorMenuItem buttonNewAnimationNew = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };
    private static final FXMenu.EditorMenuItem buttonNewBallNew = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };
    private static final FXMenu.EditorMenuItem buttonNewLevelNew = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXMenu.EditorMenuItem buttonNewParticleNew = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };

    private static final FXMenu.EditorMenuItem buttonNewAnimation2 = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };
    private static final FXMenu.EditorMenuItem buttonNewBall2 = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXMenu.EditorMenuItem buttonNewEnvironment2 = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };
    private static final FXMenu.EditorMenuItem buttonNewItem2 = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };
    private static final FXMenu.EditorMenuItem buttonNewLevel2 = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXMenu.EditorMenuItem buttonNewParticle2 = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };
    private static final FXMenu.EditorMenuItem buttonNewTerrain2 = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };


    private static final FXMenu.EditorMenuItem buttonOpenAnimationOld = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };
    private static final FXMenu.EditorMenuItem buttonOpenBallOld = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };
    private static final FXMenu.EditorMenuItem buttonOpenLevelOld = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXMenu.EditorMenuItem buttonOpenParticleOld = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };

    private static final FXMenu.EditorMenuItem buttonOpenAnimationNew = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };
    private static final FXMenu.EditorMenuItem buttonOpenBallNew = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };
    private static final FXMenu.EditorMenuItem buttonOpenLevelNew = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXMenu.EditorMenuItem buttonOpenParticleNew = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };

    private static final FXMenu.EditorMenuItem buttonOpenAnimation2 = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };
    private static final FXMenu.EditorMenuItem buttonOpenBall2 = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXMenu.EditorMenuItem buttonOpenEnvironment2 = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };
    private static final FXMenu.EditorMenuItem buttonOpenItem2 = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };
    private static final FXMenu.EditorMenuItem buttonOpenLevel2 = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXMenu.EditorMenuItem buttonOpenParticle2 = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };
    private static final FXMenu.EditorMenuItem buttonOpenTerrain2 = new FXMenu.EditorMenuItem() {
        @Override
        public void updateDisabled() {
            setDisable(true);
        }
    };


    private static final FXEditorButtons.EditorButton buttonClone = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton buttonSave = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton buttonSaveAll = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton buttonSaveAndPlay = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton buttonExport = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton buttonDummyExport = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };


    public static void asset(ToolBar toolBar) {

        String prefix = "ButtonIcons/Level/";

        Menu newOldMenu = new Menu("World of Goo 1.3...");

        buttonNewAnimationOld.setIcon("ObjectIcons/level/camera.png");
        buttonNewAnimationOld.setOnAction(e -> AssetLoader.newAsset(GameVersion.VERSION_WOG1_OLD));
        buttonNewAnimationOld.setText("New Animation");
        newOldMenu.getItems().add(buttonNewAnimationOld);

        buttonNewBallOld.setIcon("ObjectIcons/assets/Ball.png");
        buttonNewBallOld.setOnAction(e -> AssetLoader.newAsset(GameVersion.VERSION_WOG1_OLD));
        buttonNewBallOld.setText("New Ball");
        newOldMenu.getItems().add(buttonNewBallOld);

        buttonNewLevelOld.setIcon("ObjectIcons/assets/Level.png");
        buttonNewLevelOld.setOnAction(e -> AssetLoader.newAsset(GameVersion.VERSION_WOG1_OLD));
        buttonNewLevelOld.setText("New Level");
        newOldMenu.getItems().add(buttonNewLevelOld);

        buttonNewParticleOld.setIcon("ObjectIcons/scene/particles.png");
        buttonNewParticleOld.setOnAction(e -> AssetLoader.newAsset(GameVersion.VERSION_WOG1_OLD));
        buttonNewParticleOld.setText("New Particle");
        newOldMenu.getItems().add(buttonNewParticleOld);

        buttonNew.getItems().add(newOldMenu);

        Menu newNewMenu = new Menu("World of Goo 1.5...");

        buttonNewAnimationNew.setIcon("ObjectIcons/level/camera.png");
        buttonNewAnimationNew.setOnAction(e -> AssetLoader.newAsset(GameVersion.VERSION_WOG1_NEW));
        buttonNewAnimationNew.setText("New Animation");
        newNewMenu.getItems().add(buttonNewAnimationNew);

        buttonNewBallNew.setIcon("ObjectIcons/assets/Ball.png");
        buttonNewBallNew.setOnAction(e -> AssetLoader.newAsset(GameVersion.VERSION_WOG1_NEW));
        buttonNewBallNew.setText("New Ball");
        newNewMenu.getItems().add(buttonNewBallNew);

        buttonNewLevelNew.setIcon("ObjectIcons/assets/Level.png");
        buttonNewLevelNew.setOnAction(e -> AssetLoader.newAsset(GameVersion.VERSION_WOG1_NEW));
        buttonNewLevelNew.setText("New Level");
        newNewMenu.getItems().add(buttonNewLevelNew);

        buttonNewParticleNew.setIcon("ObjectIcons/scene/particles.png");
        buttonNewParticleNew.setOnAction(e -> AssetLoader.newAsset(GameVersion.VERSION_WOG1_NEW));
        buttonNewParticleNew.setText("New Particle");
        newNewMenu.getItems().add(buttonNewParticleNew);

        buttonNew.getItems().add(newNewMenu);

        Menu new2Menu = new Menu("World of Goo 2...");

        buttonNewAnimation2.setIcon("ObjectIcons/level/camera.png");
        buttonNewAnimation2.setOnAction(e -> AssetLoader.newAsset(GameVersion.VERSION_WOG2));
        buttonNewAnimation2.setText("New Animation");
        new2Menu.getItems().add(buttonNewAnimation2);

        buttonNewBall2.setIcon("ObjectIcons/assets/Ball.png");
        buttonNewBall2.setOnAction(e -> AssetLoader.newAsset(GameVersion.VERSION_WOG2));
        buttonNewBall2.setText("New Ball");
        new2Menu.getItems().add(buttonNewBall2);

        buttonNewEnvironment2.setIcon("ObjectIcons/scene/SceneLayer.png");
        buttonNewEnvironment2.setOnAction(e -> AssetLoader.newAsset(GameVersion.VERSION_WOG2));
        buttonNewEnvironment2.setText("New Environment");
        new2Menu.getItems().add(buttonNewEnvironment2);

        buttonNewItem2.setIcon("ObjectIcons/scene/compositegeom.png");
        buttonNewItem2.setOnAction(e -> AssetLoader.newAsset(GameVersion.VERSION_WOG2));
        buttonNewItem2.setText("New Item");
        new2Menu.getItems().add(buttonNewItem2);

        buttonNewLevel2.setIcon("ObjectIcons/assets/Level.png");
        buttonNewLevel2.setOnAction(e -> AssetLoader.newAsset(GameVersion.VERSION_WOG2));
        buttonNewLevel2.setText("New Level");
        new2Menu.getItems().add(buttonNewLevel2);

        buttonNewParticle2.setIcon("ObjectIcons/scene/particles.png");
        buttonNewParticle2.setOnAction(e -> AssetLoader.newAsset(GameVersion.VERSION_WOG2));
        buttonNewParticle2.setText("New Particle");
        new2Menu.getItems().add(buttonNewParticle2);

        buttonNewTerrain2.setIcon("ObjectIcons/WoG2/TerrainBallInstance.png");
        buttonNewTerrain2.setOnAction(e -> AssetLoader.newAsset(GameVersion.VERSION_WOG2));
        buttonNewTerrain2.setText("New Terrain Type");
        new2Menu.getItems().add(buttonNewTerrain2);

        buttonNew.getItems().add(new2Menu);

        buttonNew.setIcon(prefix + "new_level_2.png");
        buttonNew.setTooltip(new FXEditorButtons.DelayedTooltip("New Asset"));
        toolBar.getItems().add(buttonNew);

        Menu openOldMenu = new Menu("World of Goo 1.3...");

        buttonOpenAnimationOld.setIcon("ObjectIcons/level/camera.png");
        buttonOpenAnimationOld.setOnAction(e -> AssetLoader.openAsset(GameVersion.VERSION_WOG1_OLD));
        buttonOpenAnimationOld.setText("Open Animation");
        openOldMenu.getItems().add(buttonOpenAnimationOld);

        buttonOpenBallOld.setIcon("ObjectIcons/assets/Ball.png");
        buttonOpenBallOld.setOnAction(e -> new BallAssetSelector(GameVersion.VERSION_WOG1_OLD).start(new Stage()));
        buttonOpenBallOld.setText("Open Ball");
        openOldMenu.getItems().add(buttonOpenBallOld);

        buttonOpenLevelOld.setIcon("ObjectIcons/assets/Level.png");
        buttonOpenLevelOld.setOnAction(e -> new LevelSelector(GameVersion.VERSION_WOG1_OLD).start(new Stage()));
        buttonOpenLevelOld.setText("Open Level");
        openOldMenu.getItems().add(buttonOpenLevelOld);

        buttonOpenParticleOld.setIcon("ObjectIcons/scene/particles.png");
        buttonOpenParticleOld.setOnAction(e -> AssetLoader.openAsset(GameVersion.VERSION_WOG1_OLD));
        buttonOpenParticleOld.setText("Open Particle");
        openOldMenu.getItems().add(buttonOpenParticleOld);

        buttonOpen.getItems().add(openOldMenu);

        Menu openNewMenu = new Menu("World of Goo 1.5...");

        buttonOpenAnimationNew.setIcon("ObjectIcons/level/camera.png");
        buttonOpenAnimationNew.setOnAction(e -> AssetLoader.openAsset(GameVersion.VERSION_WOG1_NEW));
        buttonOpenAnimationNew.setText("Open Animation");
        openNewMenu.getItems().add(buttonOpenAnimationNew);

        buttonOpenBallNew.setIcon("ObjectIcons/assets/Ball.png");
        buttonOpenBallNew.setOnAction(e -> new BallAssetSelector(GameVersion.VERSION_WOG1_NEW).start(new Stage()));
        buttonOpenBallNew.setText("Open Ball");
        openNewMenu.getItems().add(buttonOpenBallNew);

        buttonOpenLevelNew.setIcon("ObjectIcons/assets/Level.png");
        buttonOpenLevelNew.setOnAction(e -> new LevelSelector(GameVersion.VERSION_WOG1_NEW).start(new Stage()));
        buttonOpenLevelNew.setText("Open Level");
        openNewMenu.getItems().add(buttonOpenLevelNew);

        buttonOpenParticleNew.setIcon("ObjectIcons/scene/particles.png");
        buttonOpenParticleNew.setOnAction(e -> AssetLoader.openAsset(GameVersion.VERSION_WOG1_NEW));
        buttonOpenParticleNew.setText("Open Particle");
        openNewMenu.getItems().add(buttonOpenParticleNew);

        buttonOpen.getItems().add(openNewMenu);

        Menu open2Menu = new Menu("World of Goo 2...");

        buttonOpenAnimation2.setIcon("ObjectIcons/level/camera.png");
        buttonOpenAnimation2.setOnAction(e -> AssetLoader.openAsset(GameVersion.VERSION_WOG2));
        buttonOpenAnimation2.setText("Open Animation");
        open2Menu.getItems().add(buttonOpenAnimation2);

        buttonOpenBall2.setIcon("ObjectIcons/assets/Ball.png");
        buttonOpenBall2.setOnAction(e -> new BallAssetSelector(GameVersion.VERSION_WOG2).start(new Stage()));
        buttonOpenBall2.setText("Open Ball");
        open2Menu.getItems().add(buttonOpenBall2);

        buttonOpenEnvironment2.setIcon("ObjectIcons/scene/SceneLayer.png");
        buttonOpenEnvironment2.setOnAction(e -> AssetLoader.openAsset(GameVersion.VERSION_WOG2));
        buttonOpenEnvironment2.setText("Open Environment");
        open2Menu.getItems().add(buttonOpenEnvironment2);

        buttonOpenItem2.setIcon("ObjectIcons/scene/compositegeom.png");
        buttonOpenItem2.setOnAction(e -> AssetLoader.openAsset(GameVersion.VERSION_WOG2));
        buttonOpenItem2.setText("Open Item");
        open2Menu.getItems().add(buttonOpenItem2);

        buttonOpenLevel2.setIcon("ObjectIcons/assets/Level.png");
        buttonOpenLevel2.setOnAction(e -> new LevelSelector(GameVersion.VERSION_WOG2).start(new Stage()));
        buttonOpenLevel2.setText("Open Level");
        open2Menu.getItems().add(buttonOpenLevel2);

        buttonOpenParticle2.setIcon("ObjectIcons/scene/particles.png");
        buttonOpenParticle2.setOnAction(e -> AssetLoader.openAsset(GameVersion.VERSION_WOG2));
        buttonOpenParticle2.setText("Open Particle");
        open2Menu.getItems().add(buttonOpenParticle2);

        buttonOpenTerrain2.setIcon("ObjectIcons/WoG2/TerrainBallInstance.png");
        buttonOpenTerrain2.setOnAction(e -> AssetLoader.openAsset(GameVersion.VERSION_WOG2));
        buttonOpenTerrain2.setText("Open Terrain Type");
        open2Menu.getItems().add(buttonOpenTerrain2);

        buttonOpen.getItems().add(open2Menu);

        buttonOpen.setIcon(prefix + "open_lvl_2.png");
        buttonOpen.setTooltip(new FXEditorButtons.DelayedTooltip("Open Asset"));
        toolBar.getItems().add(buttonOpen);

        buttonClone.setIcon(prefix + "clone_lvl.png");
        buttonClone.setOnAction(e -> AssetLoader.cloneLevel());
        buttonClone.setTooltip(new FXEditorButtons.DelayedTooltip("Clone Asset"));
        toolBar.getItems().add(buttonClone);

        buttonSave.setIcon(prefix + "save.png");
        buttonSave.setOnAction(e -> AssetUpdater.saveAsset(AssetManager.getAsset()));
        buttonSave.setTooltip(new FXEditorButtons.DelayedTooltip("Save Asset"));
        toolBar.getItems().add(buttonSave);

        buttonSaveAll.setIcon(prefix + "save_all.png");
        buttonSaveAll.setOnAction(e -> AssetUpdater.saveAll());
        buttonSaveAll.setTooltip(new FXEditorButtons.DelayedTooltip("Save All Assets"));
        toolBar.getItems().add(buttonSaveAll);

        buttonSaveAndPlay.setIcon(prefix + "play.png");
        buttonSaveAndPlay.setOnAction(e -> AssetManager.getAsset().play());
        buttonSaveAndPlay.setTooltip(new FXEditorButtons.DelayedTooltip("Save and Play Asset"));
        toolBar.getItems().add(buttonSaveAndPlay);

        toolBar.getItems().add(new Separator());

        buttonExport.setIcon(prefix + "make_goomod.png");
        buttonExport.setOnAction(e -> AssetManager.getAsset().export(true));
        buttonExport.setTooltip(new FXEditorButtons.DelayedTooltip("Export Asset"));
        toolBar.getItems().add(buttonExport);

        buttonDummyExport.setIcon(prefix + "make_dummy_goomod.png");
        buttonDummyExport.setOnAction(e -> AssetManager.getAsset().export(false));
        buttonDummyExport.setTooltip(new FXEditorButtons.DelayedTooltip("Export Asset Without Addin Info"));
        toolBar.getItems().add(buttonDummyExport);

    }

}
