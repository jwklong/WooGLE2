package com.worldOfGoo.ball;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class Sinanim extends EditorObject {

    public Sinanim(EditorObject _parent, GameVersion version) {
        super(_parent, "sinanim", version);

        addAttribute("part",  InputField._1_STRING).assertRequired();
        addAttribute("state", InputField._1_STRING).assertRequired();
        addAttribute("type",  InputField._1_STRING).assertRequired();
        addAttribute("axis",  InputField._1_STRING).assertRequired();
        addAttribute("freq",  InputField._1_STRING).assertRequired();
        addAttribute("amp",   InputField._1_STRING).assertRequired();
        addAttribute("shift", InputField._1_STRING).assertRequired();

    }

}
