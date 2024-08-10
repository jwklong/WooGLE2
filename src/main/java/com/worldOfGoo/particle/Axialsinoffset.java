package com.worldOfGoo.particle;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class Axialsinoffset extends EditorObject {

    public Axialsinoffset(EditorObject _parent, GameVersion version) {
        super(_parent, "axialsinoffset", version);

        addAttribute("amp",        InputField._1_RANGE).setDefaultValue("0").assertRequired();
        addAttribute("freq",       InputField._1_RANGE).setDefaultValue("0").assertRequired();
        addAttribute("phaseshift", InputField._1_RANGE).setDefaultValue("0").assertRequired();
        addAttribute("axis",       InputField._1_STRING)  .setDefaultValue("x").assertRequired();

    }

}
