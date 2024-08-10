package com.worldOfGoo2.misc;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Color extends EditorObject {

    public _2_Color(EditorObject parent) {
        super(parent, "Color", GameVersion.VERSION_WOG2);

        addAttribute("color", InputField._2_STRING);

    }

}
