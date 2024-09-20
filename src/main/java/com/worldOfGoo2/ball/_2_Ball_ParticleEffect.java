package com.worldOfGoo2.ball;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Ball_ParticleEffect extends EditorObject {

    public _2_Ball_ParticleEffect(EditorObject parent) {
        super(parent, "ParticleEffect", GameVersion.VERSION_WOG2);

        addAttribute("particleEffectId", InputField._2_STRING).assertRequired();
        addAttribute("states", InputField._2_LIST_NUMBER).assertRequired();
        addAttribute("sleeping", InputField._2_BOOLEAN).assertRequired();
        addAttribute("onFire", InputField._2_BOOLEAN).assertRequired();
        addAttribute("thruster", InputField._2_BOOLEAN).assertRequired();
        addAttribute("depth", InputField._2_NUMBER).assertRequired();

        addAttribute("particleEffectV2Id", InputField._2_STRING).setChildAlias(_2_Ball_ParticleEffectV2.class);

    }

}
