package com.worldOfGoo2.ball;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Ball_State extends EditorObject {

    public _2_Ball_State(EditorObject parent) {
        super(parent, "BallState", GameVersion.VERSION_WOG2);

        addAttribute("ballState", InputField._2_STRING).assertRequired();

    }

}
