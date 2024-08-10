package com.worldOfGoo2.items;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Item_Alternates extends EditorObject {

    public _2_Item_Alternates(EditorObject parent) {
        super(parent, "Alternates", GameVersion.VERSION_WOG2);

        addAttribute("stringValue", InputField._2_STRING).assertRequired();
        addAttribute("integerValue", InputField._2_NUMBER).assertRequired();

    }

}
