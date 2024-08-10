package com.worldOfGoo2.misc;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_SoundID extends EditorObject {

    public _2_SoundID(EditorObject parent) {
        super(parent, "SoundID", GameVersion.VERSION_WOG2);

        addAttribute("soundId", InputField._2_STRING);

    }

}
