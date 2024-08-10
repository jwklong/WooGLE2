package com.worldOfGoo2.ball;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Ball_SoundEvent extends EditorObject {

    public _2_Ball_SoundEvent(EditorObject parent) {
        super(parent, "SoundEvent", GameVersion.VERSION_WOG2);

        addAttribute("soundIds", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("soundIds", "_2_SoundID");

        addAttribute("soundEvent", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("soundEvent", "_2_SoundEvent");

        addAttribute("minTimeBetweenSounds", InputField._2_NUMBER);

    }

}
