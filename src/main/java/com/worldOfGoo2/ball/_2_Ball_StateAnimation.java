package com.worldOfGoo2.ball;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Ball_StateAnimation extends EditorObject {

    public _2_Ball_StateAnimation(EditorObject parent) {
        super(parent, "StateAnimation", GameVersion.VERSION_WOG2);

        addAttribute("partAnimations", InputField._2_STRING);
        putAttributeChildAlias("partAnimations", "_2_Ball_PartAnimation");

        addAttribute("states", InputField._2_STRING);
        putAttributeChildAlias("states", "_2_Ball_State");

        addAttribute("frequenceyVarianceMagnitude", InputField._2_NUMBER);
        addAttribute("amplitudeVarianceMagnitude", InputField._2_NUMBER);
        addAttribute("phaseShiftVarianceMagnitude", InputField._2_NUMBER);

    }

}

