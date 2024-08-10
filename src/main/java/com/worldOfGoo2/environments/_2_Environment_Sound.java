package com.worldOfGoo2.environments;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Environment_Sound extends EditorObject {

    public _2_Environment_Sound(EditorObject parent) {
        super(parent, "Environment_Sound", GameVersion.VERSION_WOG2);

        addAttribute("reverbWet", InputField._2_STRING);
        addAttribute("lowPassWet", InputField._2_STRING);

    }

}
