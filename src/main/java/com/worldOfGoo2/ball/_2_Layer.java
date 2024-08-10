package com.worldOfGoo2.ball;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Layer extends EditorObject {

    public _2_Layer(EditorObject parent) {
        super(parent, "Layer", GameVersion.VERSION_WOG2);

        addAttribute("layer", InputField._2_STRING).assertRequired();

    }

}
