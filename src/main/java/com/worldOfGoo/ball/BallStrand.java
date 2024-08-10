package com.worldOfGoo.ball;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class BallStrand extends EditorObject {

    public BallStrand(EditorObject _parent, GameVersion version) {
        super(_parent, "strand", version);

        addAttribute("type",           InputField._1_STRING)                     .assertRequired();
        addAttribute("image",          InputField._1_IMAGE)                   .assertRequired();
        addAttribute("inactiveimage",  InputField._1_STRING)                     .assertRequired();
        addAttribute("mass",           InputField._1_NUMBER);
        addAttribute("springconstmin", InputField._1_STRING)                     .assertRequired();
        addAttribute("springconstmax", InputField._1_STRING)                     .assertRequired();
        addAttribute("dampfac",        InputField._1_STRING)                     .assertRequired();
        addAttribute("maxlen2",        InputField._1_STRING).setDefaultValue("0").assertRequired();
        addAttribute("maxlen1",        InputField._1_STRING);
        addAttribute("maxforce",       InputField._1_STRING)                     .assertRequired();
        addAttribute("minlen",         InputField._1_STRING)                     .assertRequired();
        addAttribute("walkable",       InputField._1_STRING);
        addAttribute("thickness",      InputField._1_NUMBER)                  .setDefaultValue("24");
        addAttribute("ignitedelay",    InputField._1_STRING);
        addAttribute("burnspeed",      InputField._1_STRING);
        addAttribute("fireparticles",  InputField._1_STRING);
        addAttribute("burntimage",     InputField._1_STRING);
        addAttribute("geom",           InputField._1_STRING);
        addAttribute("shrinklen",      InputField._1_STRING);
        addAttribute("rope",           InputField._1_STRING);

    }

}
