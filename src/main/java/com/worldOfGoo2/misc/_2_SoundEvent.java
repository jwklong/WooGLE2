package com.worldOfGoo2.misc;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_SoundEvent extends EditorObject {

    public _2_SoundEvent(EditorObject parent) {
        super(parent, "SoundEvent", GameVersion.VERSION_WOG2);

        addAttribute("soundEvent", InputField._2_STRING).assertRequired();

    }

}
