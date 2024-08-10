package com.worldOfGoo2.items;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Item_Geometry extends EditorObject {

    public _2_Item_Geometry(EditorObject parent) {
        super(parent, "Geometry", GameVersion.VERSION_WOG2);

        addAttribute("type", InputField._2_NUMBER).assertRequired();
        addAttribute("width", InputField._2_NUMBER).assertRequired();
        addAttribute("height", InputField._2_NUMBER).assertRequired();
        addAttribute("radius", InputField._2_NUMBER).assertRequired();
        addAttribute("density", InputField._2_NUMBER).assertRequired();

        addAttribute("offset", InputField._2_CHILD).assertRequired();
        putAttributeChildAlias("offset", "_2_Point");

        addAttribute("rotation", InputField._2_NUMBER).assertRequired();

        addAttribute("normal", InputField._2_CHILD).assertRequired();
        putAttributeChildAlias("normal", "_2_Point");

        addAttribute("isSensor", InputField._2_BOOLEAN).assertRequired();
        addAttribute("friction", InputField._2_NUMBER).assertRequired();
        addAttribute("bounciness", InputField._2_NUMBER).assertRequired();

        addAttribute("points", InputField._2_LIST_CHILD).assertRequired();
        putAttributeChildAlias("points", "_2_Point");

    }

}
