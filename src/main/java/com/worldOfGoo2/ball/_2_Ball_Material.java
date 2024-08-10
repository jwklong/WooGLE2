package com.worldOfGoo2.ball;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Ball_Material extends EditorObject {

    public _2_Ball_Material(EditorObject parent) {
        super(parent, "Ball_Material", GameVersion.VERSION_WOG2);

        addAttribute("materialName", InputField._2_STRING).assertRequired();

    }

}
