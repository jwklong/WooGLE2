package com.worldOfGoo.scene;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.font._Font;
import com.woogleFX.editorObjects.objectComponents.TextComponent;
import com.woogleFX.file.resourceManagers.ResourceManager;
import com.woogleFX.engine.LevelManager;
import com.woogleFX.gameData.level.GameVersion;
import com.woogleFX.editorObjects.attributes.MetaEditorAttribute;
import com.woogleFX.gameData.level.WOG1Level;
import com.worldOfGoo.text.TextString;

import java.io.FileNotFoundException;

public class Label extends EditorObject {

    public Label(EditorObject _parent, GameVersion version) {
        super(_parent, "label", version);

        addAttribute("id",          InputField._1_STRING)                             .assertRequired();
        addAttribute("depth",       InputField._1_NUMBER).setDefaultValue("10")    .assertRequired();
        addAttribute("x",           InputField._1_NUMBER).setDefaultValue("0")     .assertRequired();
        addAttribute("y",           InputField._1_NUMBER).setDefaultValue("0")     .assertRequired();
        addAttribute("align",       InputField._1_STRING)   .setDefaultValue("center").assertRequired();
        addAttribute("rotation",    InputField._1_NUMBER).setDefaultValue("0")     .assertRequired();
        addAttribute("scale",       InputField._1_NUMBER).setDefaultValue("1")     .assertRequired();
        addAttribute("overlay",     InputField._1_FLAG)  .setDefaultValue("true")  .assertRequired();
        addAttribute("screenspace", InputField._1_FLAG)  .setDefaultValue("true")  .assertRequired();
        addAttribute("font",        InputField._1_FONT)                            .assertRequired();
        addAttribute("text",        InputField._1_TEXT)                            .assertRequired();
        addAttribute("colorize", InputField._1_COLOR)    .setDefaultValue("255,255,255");

        addObjectComponent(new TextComponent() {

            @Override
            public _Font getFont() {
                try {
                    return ResourceManager.getFont(((WOG1Level)LevelManager.getLevel()).getResrc(), getAttribute("font").stringValue(), LevelManager.getLevel().getVersion());
                } catch (FileNotFoundException e) {
                    return null;
                }
            }

            @Override
            public String getText() {
                for (EditorObject EditorObject : ((WOG1Level)LevelManager.getLevel()).getText()) {
                    if (EditorObject instanceof TextString textString) {
                        if (textString.getAttribute("id").stringValue().equals(getAttribute("text").stringValue())) {
                            return textString.getAttribute("text").stringValue();
                        }
                    }
                }
                return null;
            }

            @Override
            public double getX() {
                return getAttribute("x").doubleValue();
            }

            @Override
            public double getY() {
                return -getAttribute("y").doubleValue();
            }

            @Override
            public double getDepth() {
                return 0;
            }

            @Override
            public double getScale() {
                return getAttribute("scale").doubleValue();
            }

            @Override
            public double getRotation() {
                return -Math.toRadians(getAttribute("rotation").doubleValue());
            }

            @Override
            public boolean isVisible() {
                return LevelManager.getLevel().getVisibilitySettings().isShowLabels();
            }

        });

        setMetaAttributes(MetaEditorAttribute.parse("id,x,y,rotation,scale,depth,colorize,overlay,screenspace,Text<text,font,align>"));

    }


    @Override
    public String getName() {
        return getAttribute("id").stringValue();
    }

}
