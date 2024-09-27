package com.woogleFX.engine.fx.editorButtons;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.engine.AssetManager;
import com.woogleFX.gameData.level.VisibilityManager;
import com.woogleFX.gameData.level.WOG2Level;
import javafx.event.EventType;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class FXEditorButtons_ShowHide {

    private static final FXEditorButtons.EditorButton buttonShowHideCamera = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton buttonShowHideForcefields = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton buttonShowHideGeometry = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton buttonShowHideGraphics = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton buttonShowHideGoos = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton buttonShowHideParticles = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton buttonShowHideLabels = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton buttonShowHideAnim = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton buttonShowHideSceneBGColor = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    public static final FXEditorButtons.EditorButton buttonViewTerrainGroup = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton buttonResetCamera = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    public static void cameraGraphic(Image image) {
        buttonShowHideCamera.setGraphic(new ImageView(image));
    }
    public static void forcefieldsGraphic(Image image) {
        buttonShowHideForcefields.setGraphic(new ImageView(image));
    }
    public static void geometryGraphic(Image image) {
        buttonShowHideGeometry.setGraphic(new ImageView(image));
    }
    public static void graphicsGraphic(Image image) {
        buttonShowHideGraphics.setGraphic(new ImageView(image));
    }
    public static void goosGraphic(Image image) {
        buttonShowHideGoos.setGraphic(new ImageView(image));
    }
    public static void particlesGraphic(Image image) {
        buttonShowHideParticles.setGraphic(new ImageView(image));
    }
    public static void labelsGraphic(Image image) {
        buttonShowHideLabels.setGraphic(new ImageView(image));
    }
    public static void animGraphic(Image image) {
        buttonShowHideAnim.setGraphic(new ImageView(image));
    }
    public static void sceneBGGraphic(Image image) {
        buttonShowHideSceneBGColor.setGraphic(new ImageView(image));
    }


    public static void showHide(ToolBar toolBar) {

        String prefix = "ButtonIcons/ShowHide/";

        buttonShowHideCamera.setIcon(prefix + "showhide_cam.png");
        buttonShowHideCamera.setOnAction(e -> VisibilityManager.showHideCameras());
        buttonShowHideCamera.setTooltip(new FXEditorButtons.DelayedTooltip("Show/Hide Camera"));
        toolBar.getItems().add(buttonShowHideCamera);

        buttonShowHideForcefields.setIcon(prefix + "showhide_forcefields.png");
        buttonShowHideForcefields.setOnAction(e -> VisibilityManager.showHideForcefields());
        buttonShowHideForcefields.setTooltip(new FXEditorButtons.DelayedTooltip("Show/Hide Force Fields"));
        toolBar.getItems().add(buttonShowHideForcefields);

        buttonShowHideGeometry.setIcon(prefix + "showhide_geometry.png");
        buttonShowHideGeometry.setOnAction(e -> VisibilityManager.showHideGeometry());
        buttonShowHideGeometry.setTooltip(new FXEditorButtons.DelayedTooltip("Show/Hide Geometry"));
        toolBar.getItems().add(buttonShowHideGeometry);

        buttonShowHideGraphics.setIcon(prefix + "showhide_images.png");
        buttonShowHideGraphics.setOnAction(e -> VisibilityManager.showHideGraphics());
        buttonShowHideGraphics.setTooltip(new FXEditorButtons.DelayedTooltip("Show/Hide Graphics"));
        toolBar.getItems().add(buttonShowHideGraphics);

        buttonShowHideGoos.setIcon(prefix + "showhide_goobs.png");
        buttonShowHideGoos.setOnAction(e -> VisibilityManager.showHideGoos());
        buttonShowHideGoos.setTooltip(new FXEditorButtons.DelayedTooltip("Show/Hide Goo Balls"));
        toolBar.getItems().add(buttonShowHideGoos);

        buttonShowHideParticles.setIcon(prefix + "showhide_particles.png");
        buttonShowHideParticles.setOnAction(e -> VisibilityManager.showHideParticles());
        buttonShowHideParticles.setTooltip(new FXEditorButtons.DelayedTooltip("Show/Hide Particles"));
        toolBar.getItems().add(buttonShowHideParticles);

        buttonShowHideLabels.setIcon(prefix + "showhide_labels.png");
        buttonShowHideLabels.setOnAction(e -> VisibilityManager.showHideLabels());
        buttonShowHideLabels.setTooltip(new FXEditorButtons.DelayedTooltip("Show/Hide Labels"));
        toolBar.getItems().add(buttonShowHideLabels);

        buttonShowHideAnim.setIcon(prefix + "showhide_anim.png");
        buttonShowHideAnim.setOnAction(e -> VisibilityManager.showHideAnim());
        buttonShowHideAnim.setTooltip(new FXEditorButtons.DelayedTooltip("Show/Hide Animations"));
        toolBar.getItems().add(buttonShowHideAnim);

        buttonShowHideSceneBGColor.setIcon(prefix + "showhide_scenebgcolor.png");
        buttonShowHideSceneBGColor.setOnAction(e -> VisibilityManager.showHideSceneBGColor());
        buttonShowHideSceneBGColor.setTooltip(new FXEditorButtons.DelayedTooltip("Show/Hide Scene Background Color"));
        toolBar.getItems().add(buttonShowHideSceneBGColor);

        MenuButton menuButton = new MenuButton("Terrain Groups");
        buttonViewTerrainGroup.setGraphic(menuButton);
        buttonViewTerrainGroup.setTooltip(new FXEditorButtons.DelayedTooltip("Show/Hide Scene Background Color"));
        toolBar.getItems().add(buttonViewTerrainGroup);

        buttonResetCamera.setIcon(prefix + "showhide_cam.png");
        buttonResetCamera.setOnAction(e -> AssetManager.getAsset().resetCamera());
        buttonResetCamera.setTooltip(new FXEditorButtons.DelayedTooltip("Reset Camera"));
        toolBar.getItems().add(buttonResetCamera);

    }


    public static ArrayList<Boolean> comboBoxList = new ArrayList<>();
    public static int comboBoxSelected = -1;

    public static void updateTerrainGroupSelector(WOG2Level level) {

        MenuButton content = (MenuButton) buttonViewTerrainGroup.getGraphic();
        content.getItems().clear();
        comboBoxList.clear();
        int i = 0;
        for (EditorObject terrainGroup : level.getLevel().getChildren("terrainGroups")) {

            CheckBox checkBox = new CheckBox(i + (terrainGroup.getAttribute("foreground").booleanValue() ? "" : "*"));
            checkBox.setSelected(true);
            int finalI = i;
            checkBox.selectedProperty().addListener((observable, oldValue, newValue) ->
                    comboBoxList.set(finalI, newValue));

            CustomMenuItem menuItem = new CustomMenuItem(checkBox);

            menuItem.setHideOnClick(false);

            checkBox.addEventHandler(EventType.ROOT, event -> {
                if (event instanceof MouseEvent mouseEvent && mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED) {
                    comboBoxSelected = finalI;
                } else if (event instanceof MouseEvent mouseEvent && mouseEvent.getEventType() == MouseEvent.MOUSE_EXITED) {
                    comboBoxSelected = -1;
                }
                if (event instanceof MouseEvent mouseEvent && event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                    if (mouseEvent.isControlDown()) {
                        for (MenuItem menuItem1 : content.getItems()) {
                            CheckBox checkBox1 = (CheckBox) ((CustomMenuItem)menuItem1).getContent();
                            checkBox1.setSelected(mouseEvent.isShiftDown());
                        }
                        checkBox.setSelected(!mouseEvent.isShiftDown());
                    }
                }
            });

            content.getItems().add(menuItem);
            comboBoxList.add(true);

            i++;

        }

    }

}
