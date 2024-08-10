package com.worldOfGoo.ball;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class Part extends EditorObject {

    public Part(EditorObject _parent, GameVersion version) {
        super(_parent, "part", version);

        addAttribute("name",       InputField._1_STRING)                        .assertRequired();
        addAttribute("layer",      InputField._1_NUMBER)                     .assertRequired();
        addAttribute("x",          InputField._1_NUMBER)                     .assertRequired();
        addAttribute("y",          InputField._1_NUMBER)                     .assertRequired();
        addAttribute("image",      InputField._1_STRING)                        .assertRequired();
        addAttribute("scale",      InputField._1_NUMBER).setDefaultValue("1").assertRequired();
        addAttribute("rotate",     InputField._1_FLAG);
        addAttribute("stretch",    InputField._1_NUMBER);
        addAttribute("xrange",     InputField._1_POSITION);
        addAttribute("yrange",     InputField._1_POSITION);
        addAttribute("eye",        InputField._1_FLAG);
        addAttribute("pupil",      InputField._1_IMAGE);
        addAttribute("pupilinset", InputField._1_NUMBER);
        addAttribute("state",      InputField._1_STRING);

    }

}
