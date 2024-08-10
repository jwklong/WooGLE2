package com.worldOfGoo2.ball;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Ball_PartAnimation extends EditorObject {

    public _2_Ball_PartAnimation(EditorObject parent) {
        super(parent, "PartAnimation", GameVersion.VERSION_WOG2);

        addAttribute("parts", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("parts", "_2_Ball_BodyPart");

        addAttribute("axis", InputField._2_STRING);
        putAttributeChildAlias("axis", "_2_Ball_Axis");

        addAttribute("type", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("type", "_2_Ball_BallType");

        addAttribute("frequency", InputField._2_STRING).assertRequired();
        addAttribute("amplitude", InputField._2_STRING).assertRequired();
        addAttribute("phaseShift", InputField._2_STRING).assertRequired();
        addAttribute("localSpace", InputField._2_STRING);
        addAttribute("pivotX", InputField._2_STRING);

    }

}
