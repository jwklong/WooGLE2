package com.worldOfGoo.ball;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class Marker extends EditorObject {

    public Marker(EditorObject _parent, GameVersion version) {
        super(_parent, "marker", version);

        addAttribute("drag",     InputField._1_STRING).assertRequired();
        addAttribute("detach",   InputField._1_STRING).assertRequired();
        addAttribute("rotspeed", InputField._1_STRING).assertRequired();

    }

}
