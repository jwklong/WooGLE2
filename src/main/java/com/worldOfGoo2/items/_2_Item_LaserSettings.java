package com.worldOfGoo2.items;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Item_LaserSettings extends EditorObject {

    public _2_Item_LaserSettings(EditorObject parent) {
        super(parent, "LaserSettings", GameVersion.VERSION_WOG2);

        addAttribute("enabled", InputField._2_STRING).assertRequired();
        addAttribute("count", InputField._2_STRING).assertRequired();
        addAttribute("timeScale", InputField._2_STRING).assertRequired();
        addAttribute("waveScale", InputField._2_STRING).assertRequired();
        addAttribute("moveTimeScale", InputField._2_STRING).assertRequired();
        addAttribute("gravityScale", InputField._2_STRING).assertRequired();
        addAttribute("rotationStep", InputField._2_STRING).assertRequired();
        addAttribute("scaleStep", InputField._2_STRING).assertRequired();
        addAttribute("positionStep", InputField._2_STRING).assertRequired();
        addAttribute("centerFactor", InputField._2_STRING).assertRequired();

        addAttribute("gradientStart", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("gradientStart", "_2_Color");

        addAttribute("gradientEnd", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("gradientEnd", "_2_Color");

        addAttribute("image", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("image", "_2_ImageID");

    }

}
