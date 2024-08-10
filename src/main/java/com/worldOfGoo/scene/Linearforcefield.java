package com.worldOfGoo.scene;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.objectComponents.AnchorComponent;
import com.woogleFX.editorObjects.objectComponents.RectangleComponent;
import com.woogleFX.engine.renderer.Depth;
import com.woogleFX.engine.LevelManager;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;
import com.woogleFX.editorObjects.attributes.MetaEditorAttribute;
import javafx.scene.paint.*;

public class Linearforcefield extends EditorObject {

    public Linearforcefield(EditorObject _parent, GameVersion version) {
        super(_parent, "linearforcefield", version);

        addAttribute("id",               InputField._1_STRING)                                    .assertRequired();
        addAttribute("type",             InputField._1_STRING)       .setDefaultValue("gravity")  .assertRequired();
        addAttribute("center",           InputField._1_POSITION)  .setDefaultValue("0,0")      .assertRequired();
        addAttribute("width",            InputField._1_NUMBER)    .setDefaultValue("0");
        addAttribute("height",           InputField._1_NUMBER)    .setDefaultValue("0");
        addAttribute("force",            InputField._1_POSITION)  .setDefaultValue("0,-10")    .assertRequired();
        addAttribute("dampeningfactor",  InputField._1_NUMBER)    .setDefaultValue("0")        .assertRequired();
        addAttribute(
            "rotationaldampeningfactor", InputField._1_NUMBER);
        addAttribute("antigrav",         InputField._1_FLAG)      .setDefaultValue("false")    .assertRequired();
        addAttribute("geomonly",         InputField._1_FLAG)                                   .assertRequired();
        addAttribute("enabled",          InputField._1_FLAG)      .setDefaultValue("true")     .assertRequired();
        addAttribute("water",            InputField._1_FLAG)      .setDefaultValue("false");
        addAttribute("color",            InputField._1_COLOR);
        addAttribute("depth",            InputField._1_NUMBER);

        addObjectComponent(new RectangleComponent() {
            public double getX() {
                return getAttribute("center").positionValue().getX();
            }
            public void setX(double x) {
                setAttribute("center", x + "," + -getY());
            }
            public double getY() {
                return -getAttribute("center").positionValue().getY();
            }
            public void setY(double y) {
                setAttribute("center", getX() + "," + -y);
            }
            public double getWidth() {
                return Math.abs(getAttribute("width").doubleValue());
            }
            public void setWidth(double width) {
                setAttribute("width", width);
            }
            public double getHeight() {
                return Math.abs(getAttribute("height").doubleValue());
            }
            public void setHeight(double height) {
                setAttribute("height", height);
            }
            public double getEdgeSize() {
                return 4.5;
            }
            public boolean isEdgeOnly() {
                return true;
            }
            public double getDepth() {
                return Depth.FORCEFIELDS;
            }
            public Paint getBorderColor() {
                return new Color(1.0, 1.0, 0, 1.0);
            }
            public Paint getColor() {
                return new Color(1.0, 1.0, 0, 0.05);
            }
            public boolean isVisible() {
                return LevelManager.getLevel().getVisibilitySettings().isShowForcefields();
            }
            public boolean isRotatable() {
                return false;
            }
        });

        addObjectComponent(new AnchorComponent() {
            public double getX() {
                return getAttribute("center").positionValue().getX();
            }
            public void setX(double x) {
                setAttribute("center", x + "," + -getY());
            }
            public double getY() {
                return -getAttribute("center").positionValue().getY();
            }
            public void setY(double y) {
                setAttribute("center", getX() + "," + -y);
            }
            public double getAnchorX() {
                return getAttribute("force").positionValue().getX() * 20;
            }
            public double getAnchorY() {
                return -getAttribute("force").positionValue().getY() * 20;
            }
            public void setAnchor(double anchorX, double anchorY) {
                setAttribute("force", anchorX / 20 + "," + -anchorY / 20);
            }
            public double getLineWidth() {
                return 3;
            }
            public Paint getColor() {
                return new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                        new Stop(0, Color.valueOf("802000FF")), new Stop(1, Color.valueOf("FFC040FF")));
            }
            public double getDepth() {
                return Depth.FORCEFIELDS;
            }
            public boolean isVisible() {
                return LevelManager.getLevel().getVisibilitySettings().isShowForcefields();
            }
        });

        setMetaAttributes(MetaEditorAttribute.parse("id,center,width,height,Force Field<type,force,dampeningfactor,rotationaldampeningfactor,antigrav,geomonly,enabled>Water<water,color,depth>"));

    }


    @Override
    public String getName() {
        return getAttribute("id").stringValue();
    }

}
