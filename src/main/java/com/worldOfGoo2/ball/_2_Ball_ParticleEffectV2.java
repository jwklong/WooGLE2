package com.worldOfGoo2.ball;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Ball_ParticleEffectV2 extends EditorObject {

    public _2_Ball_ParticleEffectV2(EditorObject parent) {
        super(parent, "Ball_ParticleEffectV2", GameVersion.VERSION_WOG2);

        addAttribute("uuid", InputField._2_STRING);

    }

}
