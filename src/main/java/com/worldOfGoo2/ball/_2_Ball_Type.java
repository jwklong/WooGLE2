package com.worldOfGoo2.ball;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Ball_Type extends EditorObject {

    public _2_Ball_Type(EditorObject parent) {
        super(parent, "Type", GameVersion.VERSION_WOG2);

        addAttribute("ballType", InputField._2_STRING);
        putAttributeChildAlias("ballType", "_2_Ball_BallType");

    }

}
