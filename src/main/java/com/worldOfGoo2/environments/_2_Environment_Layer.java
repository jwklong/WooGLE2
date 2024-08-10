package com.worldOfGoo2.environments;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Environment_Layer extends EditorObject {

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

        addAttribute("anchors", InputField._2_LIST_CHILD);
        putAttributeChildAlias("anchors", "_2_Point");

        addAttribute("anchorsTakeDepthIntoAccount", InputField._2_STRING);
        addAttribute("bloom", InputField._2_STRING);
        addAttribute("color", InputField._2_STRING);
        addAttribute("blendingType", InputField._2_STRING);
        addAttribute("flashAnimationName", InputField._2_STRING);
        addAttribute("isFlashAnimation", InputField._2_STRING);

        addAttribute("animScroll", InputField._2_STRING);
        putAttributeChildAlias("animScroll", "_2_Point");

        addAttribute("gradient", InputField._2_CHILD);
        putAttributeChildAlias("gradient", "_2_Environment_Gradient");

    }

}
