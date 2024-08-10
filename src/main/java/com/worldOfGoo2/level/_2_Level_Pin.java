package com.worldOfGoo2.level;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.AttributeAdapter;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.editorObjects.attributes.MetaEditorAttribute;
import com.woogleFX.editorObjects.objectComponents.RectangleComponent;
import com.woogleFX.engine.LevelManager;
import com.woogleFX.engine.renderer.Depth;
import com.woogleFX.gameData.level.GameVersion;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class _2_Level_Pin extends EditorObject {

    public _2_Level_Pin(EditorObject parent) {
        super(parent, "Pin", GameVersion.VERSION_WOG2);

        addAttribute("uid", InputField._2_UID);

        addAttribute("pos", InputField._2_CHILD_HIDDEN);
        putAttributeChildAlias("pos", "_2_Point");
        addAttributeAdapter("pos", AttributeAdapter.pointAttributeAdapter(this, "pos", "pos"));

        addAttribute("damping", InputField._2_NUMBER);
        addAttribute("limitVelocity", InputField._2_BOOLEAN);
        addAttribute("maxVelocity", InputField._2_NUMBER);

        setMetaAttributes(MetaEditorAttribute.parse("uid,pos,damping,limitVelocity,maxVelocity,"));


        addObjectComponent(new RectangleComponent() {
            public double getX() {
                return getAttribute("pos").positionValue().getX();
            }
            public void setX(double x) {
                setAttribute("pos", x + "," + -getY());
            }
            public double getY() {
                return -getAttribute("pos").positionValue().getY();
            }
            public void setY(double y) {
                setAttribute("pos", getX() + "," + -y);
            }
            public double getRotation() {
                return Math.toRadians(45);
            }
            public double getWidth() {
                return 0.15;
            }
            public double getHeight() {
                return 0.15;
            }
            public double getEdgeSize() {
                return 0.04;
            }
            public boolean isEdgeOnly() {
                return true;
            }
            public double getDepth(){
                return Depth.MECHANICS + 10;
            }
            public Paint getBorderColor() {
                return new Color(1.0, 1.0, 0, 1.0);
            }
            public Paint getColor() {
                return new Color(1.0, 1.0, 0, 0.1);
            }
            public boolean isVisible() {
                return LevelManager.getLevel().getVisibilitySettings().getShowGeometry() != 0;
            }
            public boolean isResizable() {
                return false;
            }
            public boolean isRotatable() {
                return false;
            }
        });

    }


    @Override
    public void onLoaded() {
        super.onLoaded();

        EditorObject pos = getChildren("pos").get(0);
        setAttribute2("pos", pos.getAttribute("x").stringValue() + "," + pos.getAttribute("y").stringValue());
        pos.getAttribute("x").addChangeListener((observable, oldValue, newValue) ->
                setAttribute2("pos", newValue + "," + getAttribute2("pos").positionValue().getY()));
        pos.getAttribute("y").addChangeListener((observable, oldValue, newValue) ->
                setAttribute2("pos", getAttribute2("pos").positionValue().getX() + "," + newValue));

    }

}
