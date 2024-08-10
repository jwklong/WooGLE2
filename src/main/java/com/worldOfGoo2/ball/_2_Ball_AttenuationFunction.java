package com.worldOfGoo2.ball;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Ball_AttenuationFunction extends EditorObject {

    public _2_Ball_AttenuationFunction(EditorObject parent) {
        super(parent, "AttenuationFunction", GameVersion.VERSION_WOG2);

        addAttribute("frames", InputField._2_NUMBER).assertRequired();
        addAttribute("totalLength", InputField._2_NUMBER).assertRequired();

    }

}
