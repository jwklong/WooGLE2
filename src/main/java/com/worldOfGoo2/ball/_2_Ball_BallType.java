package com.worldOfGoo2.ball;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Ball_BallType extends EditorObject {

    public _2_Ball_BallType(EditorObject parent) {
        super(parent, "BallType", GameVersion.VERSION_WOG2);

        addAttribute("type", InputField._2_STRING);

    }

}

