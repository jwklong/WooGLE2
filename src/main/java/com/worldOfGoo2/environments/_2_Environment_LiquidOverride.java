package com.worldOfGoo2.environments;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Environment_LiquidOverride extends EditorObject {

    public _2_Environment_LiquidOverride(EditorObject parent) {
        super(parent, "LiquidOverride", GameVersion.VERSION_WOG2);

        addAttribute("startColor", InputField._2_STRING);
        addAttribute("endColor", InputField._2_STRING);
        addAttribute("insideParticleStartColor", InputField._2_STRING);
        addAttribute("insideParticleEndColor", InputField._2_STRING);
        addAttribute("bloomFactor", InputField._2_STRING);
        addAttribute("dynamicLightingFactor", InputField._2_STRING);
        addAttribute("burnOverride", InputField._2_STRING);
        addAttribute("canBurnOverride", InputField._2_STRING);

    }

}
