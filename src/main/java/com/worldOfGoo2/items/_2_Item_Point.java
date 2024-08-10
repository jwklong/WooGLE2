package com.worldOfGoo2.items;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Item_Point extends EditorObject {

    public _2_Item_Point(EditorObject parent) {
        super(parent, "Item_Point", GameVersion.VERSION_WOG2);

        addAttribute("position", InputField._2_CHILD).assertRequired();
        putAttributeChildAlias("position", "_2_Point");

        addAttribute("type", InputField._2_STRING);
        addAttribute("rotation", InputField._2_STRING);
        addAttribute("radius", InputField._2_STRING);
        addAttribute("userValue", InputField._2_STRING);
        addAttribute("x", InputField._2_STRING);
        addAttribute("y", InputField._2_STRING);

    }

}
