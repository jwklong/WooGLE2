package com.worldOfGoo.ball;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class BallParticles extends EditorObject {

    public BallParticles(EditorObject _parent, GameVersion version) {
        super(_parent, "particles", version);

        addAttribute("id",       InputField._1_STRING).assertRequired();
        addAttribute("states",   InputField._1_STRING).assertRequired();
        addAttribute("overball", InputField._1_STRING).assertRequired();

    }

}
