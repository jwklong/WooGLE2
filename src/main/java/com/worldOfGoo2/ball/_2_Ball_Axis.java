package com.worldOfGoo2.ball;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Ball_Axis extends EditorObject {

    public _2_Ball_Axis(EditorObject parent) {
        super(parent, "Axis", GameVersion.VERSION_WOG2);

        addAttribute("axis", InputField._2_STRING);

    }

}

