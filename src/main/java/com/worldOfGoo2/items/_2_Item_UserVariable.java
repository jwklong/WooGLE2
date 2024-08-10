package com.worldOfGoo2.items;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Item_UserVariable extends EditorObject {

    public _2_Item_UserVariable(EditorObject parent) {
        super(parent, "Item_UserVariable", GameVersion.VERSION_WOG2);

        addAttribute("name", InputField._2_STRING).assertRequired();
        addAttribute("defaultValue", InputField._2_STRING).assertRequired();
        addAttribute("minValue", InputField._2_STRING).assertRequired();
        addAttribute("maxValue", InputField._2_STRING).assertRequired();
        addAttribute("orderIndex", InputField._2_STRING).assertRequired();
        addAttribute("enabled", InputField._2_STRING).assertRequired();
        addAttribute("type", InputField._2_STRING).assertRequired();
        addAttribute("stringValue", InputField._2_STRING);

    }

}
