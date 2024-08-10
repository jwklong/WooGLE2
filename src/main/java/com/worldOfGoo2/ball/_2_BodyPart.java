package com.worldOfGoo2.ball;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_BodyPart extends EditorObject {

    public _2_BodyPart(EditorObject parent) {
        super(parent, "BodyPart", GameVersion.VERSION_WOG2);

        addAttribute("partName", InputField._2_STRING);

    }

}
