package com.worldOfGoo2.misc;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_LiquidType extends EditorObject {

    public _2_LiquidType(EditorObject parent) {
        super(parent, "LiquidType", GameVersion.VERSION_WOG2);

        addAttribute("liquidType", InputField._2_STRING);

    }

}

