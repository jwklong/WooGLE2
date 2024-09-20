package com.worldOfGoo2.ball;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Ball_PartAnimation extends EditorObject {

    public _2_Ball_PartAnimation(EditorObject parent) {
        super(parent, "PartAnimation", GameVersion.VERSION_WOG2);

        addAttribute("parts", InputField._2_LIST_CHILD).setChildAlias(_2_BodyPart.class).assertRequired();
        addAttribute("axis", InputField._2_CHILD).setChildAlias(_2_Ball_Axis.class);
        addAttribute("type", InputField._2_CHILD).setChildAlias(_2_Ball_BallType.class).assertRequired();
        addAttribute("frequency", InputField._2_NUMBER).assertRequired();
        addAttribute("amplitude", InputField._2_NUMBER).assertRequired();
        addAttribute("phaseShift", InputField._2_NUMBER).assertRequired();
        addAttribute("localSpace", InputField._2_NUMBER);
        addAttribute("pivotX", InputField._2_NUMBER);

    }

}
