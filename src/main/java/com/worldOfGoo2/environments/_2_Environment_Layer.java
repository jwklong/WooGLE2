package com.worldOfGoo2.environments;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects._2_Positionable;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;
import com.worldOfGoo2.misc._2_Point;

public class _2_Environment_Layer extends _2_Positionable {

    public _2_Environment_Layer(EditorObject parent) {
        super(parent, "Environment_Layer", GameVersion.VERSION_WOG2);

        addAttribute("imageName", InputField._2_STRING);
        addAttribute("scale", InputField._2_STRING);
        addAttribute("depth", InputField._2_STRING);
        addAttribute("depthYOverride", InputField._2_STRING);
        addAttribute("fill", InputField._2_STRING);
        addAttribute("foreground", InputField._2_STRING);
        addAttribute("repeatX", InputField._2_STRING);
        addAttribute("repeatY", InputField._2_STRING);
        addAttribute("mirrorX", InputField._2_STRING);
        addAttribute("mirrorY", InputField._2_STRING);
        addAttribute("flipX", InputField._2_STRING);
        addAttribute("flipY", InputField._2_STRING);
        addAttribute("anchors", InputField._2_LIST_CHILD).setChildAlias(_2_Point.class);
        addAttribute("anchorsTakeDepthIntoAccount", InputField._2_STRING);
        addAttribute("bloom", InputField._2_STRING);
        addAttribute("color", InputField._2_NUMBER);
        addAttribute("blendingType", InputField._2_NUMBER);
        addAttribute("flashAnimationName", InputField._2_STRING);
        addAttribute("isFlashAnimation", InputField._2_STRING);
        addAttribute("animScroll", InputField._2_STRING).setChildAlias(_2_Point.class);
        addAttribute("gradient", InputField._2_CHILD).setChildAlias(_2_Environment_Gradient.class);

    }

}
