package com.worldOfGoo2.level;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects._2_Positionable;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.editorObjects.attributes.MetaEditorAttribute;
import com.woogleFX.editorObjects.objectComponents.RectangleComponent;
import com.woogleFX.engine.AssetManager;
import com.woogleFX.engine.renderer.Depth;
import com.woogleFX.gameData.level.GameVersion;
import com.worldOfGoo2.misc._2_Point;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class _2_Level_Pin extends _2_Positionable {

    public _2_Level_Pin(EditorObject parent) {
        super(parent, "Pin", GameVersion.VERSION_WOG2);

        addAttribute("uid", InputField._2_UID);
        addAttribute("pos", InputField._2_CHILD_HIDDEN).setChildAlias(_2_Point.class);
        addAttribute("damping", InputField._2_NUMBER);
        addAttribute("limitVelocity", InputField._2_BOOLEAN);
        addAttribute("maxVelocity", InputField._2_NUMBER);

        setMetaAttributes(MetaEditorAttribute.parse("uid,pos,damping,limitVelocity,maxVelocity,"));


        addObjectComponent(new RectangleComponent() {
            public double getX() {
                return getPosition().getX();
            }
            public void setX(double x) {
                setPosition(x, getPosition().getY());
            }
            public double getY() {
                return -getPosition().getY();
            }
            public void setY(double y) {
                setPosition(getPosition().getX(), -y);;
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
                return AssetManager.getAsset().getVisibilitySettings().getShowGeometry() != 0;
            }
            public boolean isResizable() {
                return false;
            }
            public boolean isRotatable() {
                return false;
            }
        });

    }

}
