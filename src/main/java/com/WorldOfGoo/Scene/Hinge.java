package com.WorldOfGoo.Scene;

import com.WooGLEFX.EditorObjects.objectcomponents.RectangleComponent;
import com.WooGLEFX.Engine.Renderer;
import com.WooGLEFX.Functions.LevelManager;
import com.WooGLEFX.EditorObjects.EditorObject;
import com.WooGLEFX.EditorObjects.InputField;
import com.WooGLEFX.Structures.SimpleStructures.MetaEditorAttribute;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Hinge extends EditorObject {

    public Hinge(EditorObject _parent) {
        super(_parent, "hinge", "scene\\hinge");

        addAttribute("body1", InputField.ANY)                             .assertRequired();
        addAttribute("body2", InputField.ANY);
        addAttribute("anchor", InputField.POSITION).setDefaultValue("0,0").assertRequired();
        addAttribute("lostop", InputField.NUMBER);
        addAttribute("histop", InputField.NUMBER);
        addAttribute("bounce", InputField.NUMBER);
        addAttribute("stopcfm", InputField.NUMBER);
        addAttribute("stoperp", InputField.NUMBER);

        addObjectComponent(new RectangleComponent() {
            public double getX() {
                return getAttribute("anchor").positionValue().getX();
            }
            public void setX(double x) {
                setAttribute("anchor", x + "," + -getY());
            }
            public double getY() {
                return -getAttribute("anchor").positionValue().getY();
            }
            public void setY(double y) {
                setAttribute("anchor", getX() + "," + -y);
            }
            public double getRotation() {
                return Math.toRadians(45);
            }
            public double getWidth() {
                return 15;
            }
            public double getHeight() {
                return 15;
            }
            public double getEdgeSize() {
                return 2;
            }
            public boolean isEdgeOnly() {
                return true;
            }
            public double getDepth(){
                return Renderer.GEOMETRY + 1;
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

        setMetaAttributes(MetaEditorAttribute.parse("anchor,body1,body2,?Hinge<bounce,histop,lostop,stopcfm,stoperp>"));

    }


    @Override
    public String getName() {
        String body1 = getAttribute("body1").stringValue();
        String body2 = getAttribute("body2").stringValue();
        return body1 + ", " + body2;
    }

}
