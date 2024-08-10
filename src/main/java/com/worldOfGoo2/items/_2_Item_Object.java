package com.worldOfGoo2.items;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.AttributeAdapter;
import com.woogleFX.editorObjects.attributes.EditorAttribute;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.file.resourceManagers.ResourceManager;
import com.woogleFX.gameData.level.GameVersion;
import javafx.scene.image.Image;

import java.io.FileNotFoundException;
import java.io.IOException;

public class _2_Item_Object extends EditorObject {

    private Image image;

    public Image getImage() {
        return image;
    }

    public _2_Item_Object(EditorObject parent) {
        super(parent, "Object", GameVersion.VERSION_WOG2);

        addAttribute("name", InputField._2_STRING).assertRequired();
        addAttribute("randomizationGroup", InputField._2_NUMBER).assertRequired();

        addAttribute("position", InputField._2_CHILD).assertRequired();
        putAttributeChildAlias("position", "_2_Point");

        addAttribute("rotation", InputField._2_NUMBER).assertRequired();

        addAttribute("scale", InputField._2_CHILD).assertRequired();
        putAttributeChildAlias("scale", "_2_Point");

        addAttribute("color", InputField._2_NUMBER).setDefaultValue("4294967295");
        addAttribute("depthOffset", InputField._2_NUMBER).assertRequired();
        addAttribute("sortOffset", InputField._2_NUMBER);
        addAttribute("imageBlendingType", InputField._2_NUMBER).assertRequired();

        addAttribute("pivot", InputField._2_CHILD).assertRequired();
        putAttributeChildAlias("pivot", "_2_Point");

        addAttribute("rotationSpeed", InputField._2_NUMBER).assertRequired();
        addAttribute("invisible", InputField._2_BOOLEAN).assertRequired();
        addAttribute("clickable", InputField._2_BOOLEAN);
        addAttribute("stableFluidsDensityFactor", InputField._2_NUMBER).assertRequired();
        addAttribute("dynamicLightingFactor", InputField._2_NUMBER).assertRequired();
        addAttribute("flipHorizontal", InputField._2_BOOLEAN).assertRequired();
        addAttribute("flipVertical", InputField._2_BOOLEAN).assertRequired();
        addAttribute("ignoreScale", InputField._2_BOOLEAN);
        addAttribute("shaderFactor", InputField._2_NUMBER).assertRequired();
        addAttribute("enableWind", InputField._2_BOOLEAN);

        addAttribute("windFactor", InputField._2_STRING);
        putAttributeChildAlias("windFactor", "_2_Point");

        addAttribute("stencilMode", InputField._2_NUMBER);
        addAttribute("stencilMask", InputField._2_NUMBER);
        addAttribute("alphaTestValue", InputField._2_NUMBER);

        addAttribute("body", InputField._2_CHILD).assertRequired();
        putAttributeChildAlias("body", "_2_Item_Body");

        addAttribute("colorFx", InputField._2_CHILD);
        putAttributeChildAlias("colorFx", "_2_Item_ColorFX");

        addAttribute("particleEffects", InputField._2_LIST_CHILD).assertRequired();
        putAttributeChildAlias("particleEffects", "_2_Item_ParticleEffect");

        addAttribute("points", InputField._2_LIST_CHILD).assertRequired();
        putAttributeChildAlias("points", "_2_Item_Point");

        addAttribute("imageAlpha", InputField._2_NUMBER).setDefaultValue("1");
        addAttribute("animationObjectName", InputField._2_STRING);

    }


    @Override
    public void update() {
        updateImage();
    }


    private void updateImage() {

        try {
            if (!getAttribute("name").stringValue().isEmpty()) {
                image = ResourceManager.getImage(null, getAttribute("name").stringValue(), GameVersion.VERSION_WOG2);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

}
