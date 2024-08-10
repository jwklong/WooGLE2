package com.worldOfGoo.ball;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class Shadow extends EditorObject {

    public Shadow(EditorObject _parent, GameVersion version) {
        super(_parent, "shadow", version);

        addAttribute("image",    InputField._1_STRING)  .assertRequired();
        addAttribute("additive", InputField._1_STRING);

    }

}
