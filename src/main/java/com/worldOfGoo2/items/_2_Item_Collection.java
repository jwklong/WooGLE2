package com.worldOfGoo2.items;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Item_Collection extends EditorObject {

    public _2_Item_Collection(EditorObject parent) {
        super(parent, "ItemCollection", GameVersion.VERSION_WOG2);

        addAttribute("items", InputField._2_STRING);

        putAttributeChildAlias("items", "_2_Item");

    }

}
