package com.worldOfGoo2.misc;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_UUID extends EditorObject {

    public _2_UUID(EditorObject parent) {
        super(parent, "UUID", GameVersion.VERSION_WOG2);

        addAttribute("uuid", InputField._2_STRING);

    }

}
