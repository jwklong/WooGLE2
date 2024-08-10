package com.worldOfGoo2.items;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Item_Limit extends EditorObject {

    public _2_Item_Limit(EditorObject parent) {
        super(parent, "Limit", GameVersion.VERSION_WOG2);

        addAttribute("minScale", InputField._2_NUMBER);
        addAttribute("maxScale", InputField._2_NUMBER);

    }

}
