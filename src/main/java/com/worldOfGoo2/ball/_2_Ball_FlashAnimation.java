package com.worldOfGoo2.ball;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Ball_FlashAnimation extends EditorObject {

    public _2_Ball_FlashAnimation(EditorObject parent) {
        super(parent, "FlashAnimation", GameVersion.VERSION_WOG2);

        addAttribute("flashAnimationId", InputField._2_STRING);

    }

}
