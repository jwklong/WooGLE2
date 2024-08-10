package com.worldOfGoo.resrc;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class Material extends EditorObject {

    public Material(EditorObject _parent, GameVersion version) {
        super(_parent, "material", version);

        addAttribute("id", InputField._1_STRING);
        addAttribute("friction", InputField._1_NUMBER);
        addAttribute("bounce", InputField._1_NUMBER);
        addAttribute("minbouncevel", InputField._1_NUMBER);
        addAttribute("stickiness", InputField._1_NUMBER);

    }

}
