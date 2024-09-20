package com.worldOfGoo2.ball;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Ball_StateAnimation extends EditorObject {

    public _2_Ball_StateAnimation(EditorObject parent) {
        super(parent, "StateAnimation", GameVersion.VERSION_WOG2);

        addAttribute("partAnimations", InputField._2_LIST_CHILD).setChildAlias(_2_Ball_PartAnimation.class);
        addAttribute("states", InputField._2_LIST_CHILD).setChildAlias(_2_Ball_State.class);
        addAttribute("frequenceyVarianceMagnitude", InputField._2_NUMBER);
        addAttribute("amplitudeVarianceMagnitude", InputField._2_NUMBER);
        addAttribute("phaseShiftVarianceMagnitude", InputField._2_NUMBER);

    }

}

