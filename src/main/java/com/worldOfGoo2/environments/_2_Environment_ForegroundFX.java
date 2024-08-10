package com.worldOfGoo2.environments;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Environment_ForegroundFX extends EditorObject {

    public _2_Environment_ForegroundFX(EditorObject parent) {
        super(parent, "Environment_ForegroundFX", GameVersion.VERSION_WOG2);

        addAttribute("layerRed", InputField._2_STRING);
        addAttribute("layerGreen", InputField._2_STRING);
        addAttribute("layerBlue", InputField._2_STRING);
        addAttribute("layerOp", InputField._2_STRING);
        addAttribute("layerEnabled", InputField._2_STRING);
        addAttribute("layerK", InputField._2_STRING);
        addAttribute("brightness", InputField._2_STRING);
        addAttribute("contrast", InputField._2_STRING);
        addAttribute("exposure", InputField._2_STRING);
        addAttribute("saturation", InputField._2_STRING);
        addAttribute("levelBlack", InputField._2_STRING);
        addAttribute("levelWhite", InputField._2_STRING);
        addAttribute("mode", InputField._2_STRING);

    }

}
