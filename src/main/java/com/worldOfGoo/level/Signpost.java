package com.worldOfGoo.level;

import com.woogleFX.editorObjects.ImageUtility;
import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.objectComponents.ImageComponent;
import com.woogleFX.gameData.level.WOG1Level;
import com.woogleFX.gameData.level.levelOpening.LevelLoader;
import com.woogleFX.engine.LevelManager;
import com.woogleFX.gameData.level.GameVersion;
import javafx.scene.image.Image;

import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.editorObjects.attributes.dataTypes.Color;
import com.woogleFX.editorObjects.attributes.MetaEditorAttribute;

public class Signpost extends EditorObject {

    private Image image;


    public Signpost(EditorObject _parent, GameVersion version) {
        super(_parent, "signpost", version);

        addAttribute("name",      InputField._1_STRING)                                  .assertRequired();
        addAttribute("depth",     InputField._1_NUMBER).setDefaultValue("0")          .assertRequired();
        addAttribute("x",         InputField._1_NUMBER).setDefaultValue("0")          .assertRequired();
        addAttribute("y",         InputField._1_NUMBER).setDefaultValue("0")          .assertRequired();
        addAttribute("scalex",    InputField._1_NUMBER).setDefaultValue("1")          .assertRequired();
        addAttribute("scaley",    InputField._1_NUMBER).setDefaultValue("1")          .assertRequired();
        addAttribute("rotation",  InputField._1_NUMBER).setDefaultValue("0")          .assertRequired();
        addAttribute("alpha",     InputField._1_NUMBER).setDefaultValue("1")          .assertRequired();
        addAttribute("colorize",  InputField._1_COLOR) .setDefaultValue("255,255,255").assertRequired();
        addAttribute("image",     InputField._1_IMAGE)                                .assertRequired();
        addAttribute("text",      InputField._1_TEXT)                                 .assertRequired();
        addAttribute("particles", InputField._1_PARTICLES);
        addAttribute("pulse",     InputField._1_STRING);

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

        setMetaAttributes(MetaEditorAttribute.parse("name,text,particles,pulse,Image<x,y,scalex,scaley,image,rotation,depth,alpha,colorize>"));

        getAttribute("image").addChangeListener((observable, oldValue, newValue) -> updateImage());
        getAttribute("colorize").addChangeListener((observable, oldValue, newValue) -> updateImage());

    }


    @Override
    public String getName() {
        return getAttribute("name").stringValue();
    }


    @Override
    public void update() {
        updateImage();
    }


    private void updateImage() {

        if (LevelManager.getLevel() == null) return;

        try {
            image = getAttribute("image").imageValue(((WOG1Level)LevelManager.getLevel()).getResrc(), getVersion());
            if (image == null) return;
            Color color = getAttribute("colorize").colorValue();
            image = ImageUtility.colorize(image, color);
        } catch (Exception e) {
            // TODO make this cleaner
            if (!LevelLoader.failedResources.contains("From signpost: \"" + getAttribute("image").stringValue() + "\" (version " + getVersion() + ")")) {
                LevelLoader.failedResources.add("From signpost: \"" + getAttribute("image").stringValue() + "\" (version " + getVersion() + ")");
            }
            image = null;
        }

    }

}
