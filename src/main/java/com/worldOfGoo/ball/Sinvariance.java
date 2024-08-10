package com.worldOfGoo.ball;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class Sinvariance extends EditorObject {

    public Sinvariance(EditorObject _parent, GameVersion version) {
        super(_parent, "sinvariance", version);

        addAttribute("freq", InputField._1_STRING).assertRequired();
        addAttribute("amp", InputField._1_STRING).assertRequired();
        addAttribute("shift", InputField._1_STRING).assertRequired();

    }

}
