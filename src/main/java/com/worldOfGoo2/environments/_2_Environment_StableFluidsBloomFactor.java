package com.worldOfGoo2.environments;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Environment_StableFluidsBloomFactor extends EditorObject {

    public _2_Environment_StableFluidsBloomFactor(EditorObject parent) {
        super(parent, "StableFluidsBloomFactor", GameVersion.VERSION_WOG2);

        addAttribute("x", InputField._2_STRING).assertRequired();
        addAttribute("y", InputField._2_STRING).assertRequired();
        addAttribute("z", InputField._2_STRING).assertRequired();
        addAttribute("w", InputField._2_STRING).assertRequired();

    }

}
