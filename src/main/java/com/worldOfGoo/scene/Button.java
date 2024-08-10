package com.worldOfGoo.scene;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.ImageUtility;
import com.woogleFX.editorObjects.objectComponents.ImageComponent;
import com.woogleFX.engine.LevelManager;

import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;
import com.woogleFX.editorObjects.attributes.MetaEditorAttribute;

import com.woogleFX.gameData.level.WOG1Level;
import javafx.scene.image.Image;

import java.io.FileNotFoundException;

public class Button extends EditorObject {

    private Image image;


    public Button(EditorObject _parent, GameVersion version) {
        super(_parent, "button", version);

        addAttribute("id",                    InputField._1_STRING)                                  .assertRequired();
        addAttribute("depth",                 InputField._1_NUMBER).setDefaultValue("0")          .assertRequired();
        addAttribute("x",                     InputField._1_NUMBER)                               .assertRequired();
        addAttribute("y",                     InputField._1_NUMBER).setDefaultValue("0")          .assertRequired();
        addAttribute("scalex",                InputField._1_NUMBER).setDefaultValue("1")          .assertRequired();
        addAttribute("scaley",                InputField._1_NUMBER).setDefaultValue("1")          .assertRequired();
        addAttribute("rotation",              InputField._1_NUMBER).setDefaultValue("0")          .assertRequired();
        addAttribute("anchor",                InputField._1_STRING);
        addAttribute("alpha",                 InputField._1_STRING)   .setDefaultValue("1")          .assertRequired();
        addAttribute("colorize",              InputField._1_STRING)   .setDefaultValue("255,255,255").assertRequired();
        addAttribute("up",                    InputField._1_IMAGE);
        addAttribute("over",                  InputField._1_IMAGE);
        addAttribute("down",                  InputField._1_IMAGE);
        addAttribute("downover",              InputField._1_IMAGE);
        addAttribute("armed",                 InputField._1_IMAGE);
        addAttribute("downarmed",             InputField._1_IMAGE);
        addAttribute("disabled",              InputField._1_IMAGE);
        addAttribute("downdisabled",          InputField._1_IMAGE);
        addAttribute("latch",                 InputField._1_FLAG);
        addAttribute("font",                  InputField._1_STRING);
        addAttribute("text",                  InputField._1_STRING);
        addAttribute("onclick",               InputField._1_STRING);
        addAttribute("onmouseenter",          InputField._1_STRING);
        addAttribute("onmouseexit",           InputField._1_STRING);
        addAttribute("textcolorup",           InputField._1_STRING);
        addAttribute("textcolorupover",       InputField._1_STRING);
        addAttribute("textcoloruparmed",      InputField._1_STRING);
        addAttribute("textcolorupdisabled",   InputField._1_STRING);
        addAttribute("textcolordown",         InputField._1_STRING);
        addAttribute("textcolordownover",     InputField._1_STRING);
        addAttribute("textcolordownarmed",    InputField._1_STRING);
        addAttribute("textcolordowndisabled", InputField._1_STRING);
        addAttribute("tooltip",               InputField._1_STRING);
        addAttribute("overlay",               InputField._1_STRING);
        addAttribute("screenspace",           InputField._1_STRING);
        addAttribute("context",               InputField._1_STRING);

        addObjectComponent(new ImageComponent() {
            public double getX() {
                return getAttribute("x").doubleValue();
            }
            public void setX(double x) {
                setAttribute("x", x);
            }
            public double getY() {
                return -getAttribute("y").doubleValue();
            }
            public void setY(double y) {
                setAttribute("y", -y);
            }
            public double getRotation() {
                return -Math.toRadians(getAttribute("rotation").doubleValue());
            }
            public void setRotation(double rotation) {
                setAttribute("rotation", -Math.toDegrees(rotation));
            }
            public double getScaleX() {
                return getAttribute("scalex").doubleValue();
            }
            public void setScaleX(double scaleX) {
                setAttribute("scalex", scaleX);
            }
            public double getScaleY() {
                return getAttribute("scaley").doubleValue();
            }
            public void setScaleY(double scaleY) {
                setAttribute("scaley", scaleY);
            }
            public double getDepth() {
                return getAttribute("depth").doubleValue();
            }
            public Image getImage() {
                return image;
            }
            public boolean isVisible() {
                return LevelManager.getLevel().getVisibilitySettings().isShowGraphics();
            }
        });

        setMetaAttributes(MetaEditorAttribute.parse("id,x,y,anchor,depth,alpha,rotation,scalex,scaley,colorize,?Graphics<up,over,down,downover,armed,downarmed,disabled,downdisabled>latch,?Events<onclick,onmouseenter,onmouseexit>?Text<font,text,tooltip,textcolorup,textcolorupover,textcoloruparmed,textcolorupdisabled,textcolordown,textcolordownover,textcolordownarmed,textcolordowndisabled>overlay,screenspace,context,"));

        getAttribute("up").addChangeListener((observable, oldValue, newValue) -> updateImage());
        getAttribute("colorize").addChangeListener((observable, oldValue, newValue) -> updateImage());

    }


    @Override
    public String getName() {
        return getAttribute("id").stringValue();
    }


    @Override
    public void update() {
        updateImage();
    }


    private void updateImage() {

        if (LevelManager.getLevel() == null) return;

        try {
            if (!getAttribute("up").stringValue().isEmpty()) {
                image = getAttribute("up").imageValue(((WOG1Level)LevelManager.getLevel()).getResrc(), getVersion());
                image = ImageUtility.colorize(image, getAttribute("colorize").colorValue());
            }
        } catch (FileNotFoundException ignored) {

        }

    }

}
