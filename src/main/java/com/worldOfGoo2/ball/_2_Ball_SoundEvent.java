package com.worldOfGoo2.ball;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;
import com.worldOfGoo2.misc._2_SoundEvent;
import com.worldOfGoo2.misc._2_SoundID;

public class _2_Ball_SoundEvent extends EditorObject {

    public _2_Ball_SoundEvent(EditorObject parent) {
        super(parent, "SoundEvent", GameVersion.VERSION_WOG2);

        addAttribute("soundIds", InputField._2_LIST_CHILD).setChildAlias(_2_SoundID.class).assertRequired();

        addAttribute("soundEvent", InputField._2_CHILD).setChildAlias(_2_SoundEvent.class).assertRequired();

        addAttribute("minTimeBetweenSounds", InputField._2_NUMBER);

    }

}
