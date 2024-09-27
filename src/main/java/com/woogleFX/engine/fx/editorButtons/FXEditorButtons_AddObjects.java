package com.woogleFX.engine.fx.editorButtons;

import com.woogleFX.editorObjects.objectCreators.ObjectAdder;
import javafx.scene.control.*;

public class FXEditorButtons_AddObjects {

    private static final FXEditorButtons.EditorButton addLineButton = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton addRectangleButton = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton addCircleButton = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton addSceneLayerButton = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton addCompGeomButton = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton addHingeButton = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton autoPipeButton = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton addVertexButton = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton addFireButton = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton addLinearFFButton = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton addRadialFFButton = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton addParticlesButton = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton addSignpostButton = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };
    private static final FXEditorButtons.EditorButton addLabelButton = new FXEditorButtons.EditorButton() {
        @Override
        public void updateDisabled() {
            setDisable(false);
        }
    };

    public static void addObjects(ToolBar toolBar) {

        String prefix = "ButtonIcons/AddObject/";

        addLineButton.setIcon(prefix + "line.png");
        addLineButton.setOnAction(e -> ObjectAdder.addObject("line"));
        addLineButton.setTooltip(new FXEditorButtons.DelayedTooltip("Add Line"));
        toolBar.getItems().add(addLineButton);

        addRectangleButton.setIcon(prefix + "rectangle.png");
        addRectangleButton.setOnAction(e -> ObjectAdder.addObject("rectangle"));
        addRectangleButton.setTooltip(new FXEditorButtons.DelayedTooltip("Add Rectangle"));
        toolBar.getItems().add(addRectangleButton);

        addCircleButton.setIcon(prefix + "circle.png");
        addCircleButton.setOnAction(e -> ObjectAdder.addObject("circle"));
        addCircleButton.setTooltip(new FXEditorButtons.DelayedTooltip("Add Circle"));
        toolBar.getItems().add(addCircleButton);

        addSceneLayerButton.setIcon(prefix + "SceneLayer.png");
        addSceneLayerButton.setOnAction(e -> ObjectAdder.addObject("SceneLayer"));
        addSceneLayerButton.setTooltip(new FXEditorButtons.DelayedTooltip("Add Scene Layer"));
        toolBar.getItems().add(addSceneLayerButton);

        addCompGeomButton.setIcon(prefix + "compositegeom.png");
        addCompGeomButton.setOnAction(e -> ObjectAdder.addObject("compositegeom"));
        addCompGeomButton.setTooltip(new FXEditorButtons.DelayedTooltip("Add Composite Geometry"));
        toolBar.getItems().add(addCompGeomButton);

        addHingeButton.setIcon(prefix + "hinge.png");
        addHingeButton.setOnAction(e -> ObjectAdder.addObject("hinge"));
        addHingeButton.setTooltip(new FXEditorButtons.DelayedTooltip("Add Hinge"));
        toolBar.getItems().add(addHingeButton);

        toolBar.getItems().add(new Separator());

        autoPipeButton.setIcon(prefix + "pipe.png");
        autoPipeButton.setOnAction(e -> ObjectAdder.autoPipe());
        autoPipeButton.setTooltip(new FXEditorButtons.DelayedTooltip("Auto Pipe"));
        toolBar.getItems().add(autoPipeButton);

        addVertexButton.setIcon(prefix + "Vertex.png");
        addVertexButton.setOnAction(e -> ObjectAdder.addObject("Vertex"));
        addVertexButton.setTooltip(new FXEditorButtons.DelayedTooltip("Add Vertex"));
        toolBar.getItems().add(addVertexButton);

        toolBar.getItems().add(new Separator());

        addFireButton.setIcon(prefix + "fire.png");
        addFireButton.setOnAction(e -> ObjectAdder.addObject("fire"));
        addFireButton.setTooltip(new FXEditorButtons.DelayedTooltip("Add Fire"));
        toolBar.getItems().add(addFireButton);

        addLinearFFButton.setIcon(prefix + "linearforcefield.png");
        addLinearFFButton.setOnAction(e -> ObjectAdder.addObject("linearforcefield"));
        addLinearFFButton.setTooltip(new FXEditorButtons.DelayedTooltip("Add Linear Force Field"));
        toolBar.getItems().add(addLinearFFButton);

        addRadialFFButton.setIcon(prefix + "radialforcefield.png");
        addRadialFFButton.setOnAction(e -> ObjectAdder.addObject("radialforcefield"));
        addRadialFFButton.setTooltip(new FXEditorButtons.DelayedTooltip("Add Radial Force Field"));
        toolBar.getItems().add(addRadialFFButton);

        addParticlesButton.setIcon(prefix + "particles.png");
        addParticlesButton.setOnAction(e -> ObjectAdder.addObject("particles"));
        addParticlesButton.setTooltip(new FXEditorButtons.DelayedTooltip("Add Particles"));
        toolBar.getItems().add(addParticlesButton);

        toolBar.getItems().add(new Separator());

        addSignpostButton.setIcon(prefix + "signpost.png");
        addSignpostButton.setOnAction(e -> ObjectAdder.addObject("signpost"));
        addSignpostButton.setTooltip(new FXEditorButtons.DelayedTooltip("Add Signpost"));
        toolBar.getItems().add(addSignpostButton);

        addLabelButton.setIcon(prefix + "label.png");
        addLabelButton.setOnAction(e -> ObjectAdder.addObject("label"));
        addLabelButton.setTooltip(new FXEditorButtons.DelayedTooltip("Add Label"));
        toolBar.getItems().add(addLabelButton);

    }

}
