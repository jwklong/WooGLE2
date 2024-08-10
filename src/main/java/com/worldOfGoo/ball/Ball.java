package com.worldOfGoo.ball;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class Ball extends EditorObject {

    public Ball(EditorObject _parent, GameVersion version) {
        super(_parent, "ball", version);

        addAttribute("name",                 InputField._1_STRING)                                  .assertRequired();
        addAttribute("static",               InputField._1_STRING);
        addAttribute("shape",                InputField._1_STRING).setDefaultValue("circle,36,0.25").assertRequired();
        addAttribute("mass",                 InputField._1_STRING)                                  .assertRequired();
        addAttribute("towermass",            InputField._1_STRING);
        addAttribute("strands",              InputField._1_STRING)                                  .assertRequired();
        addAttribute("walkspeed",            InputField._1_STRING)                                  .assertRequired();
        addAttribute("climbspeed",           InputField._1_STRING);
        addAttribute("speedvariance",        InputField._1_STRING)                                  .assertRequired();
        addAttribute("draggable",            InputField._1_STRING);
        addAttribute("detachable",           InputField._1_STRING);
        addAttribute("autobounds",           InputField._1_STRING);
        addAttribute("grumpy",               InputField._1_STRING);
        addAttribute("suckable",             InputField._1_STRING);
        addAttribute("sticky",               InputField._1_STRING);
        addAttribute("stuckattachment",      InputField._1_STRING);
        addAttribute("invulnerable",         InputField._1_STRING);
        addAttribute("antigrav",             InputField._1_STRING);
        addAttribute("dampening",            InputField._1_STRING);
        addAttribute("collideattached",      InputField._1_STRING);
        addAttribute("statescales",          InputField._1_STRING);
        addAttribute("jump",                 InputField._1_STRING);
        addAttribute("maxattachspeed",       InputField._1_STRING);
        addAttribute("jumponwakeup",         InputField._1_STRING);
        addAttribute("staticwhensleeping",   InputField._1_STRING);
        addAttribute("collidewithattached",  InputField._1_STRING);
        addAttribute("climber",              InputField._1_STRING);
        addAttribute("material",             InputField._1_STRING);
        addAttribute("contains",             InputField._1_STRING);
        addAttribute("popsound",             InputField._1_STRING);
        addAttribute("popduration",          InputField._1_STRING);
        addAttribute("popparticles",         InputField._1_STRING);
        addAttribute("isbehindstrands",      InputField._1_STRING);
        addAttribute("wakedist",             InputField._1_STRING);
        addAttribute("blinkcolor",           InputField._1_STRING);
        addAttribute("hideeyes",             InputField._1_STRING);
        addAttribute("alwayslookatmouse",    InputField._1_STRING);
        addAttribute("autoboundsunattached", InputField._1_STRING);
        addAttribute("burntime",             InputField._1_STRING);
        addAttribute("detonateforce",        InputField._1_STRING);
        addAttribute("detonateradius",       InputField._1_STRING);
        addAttribute("walkforce",            InputField._1_STRING);
        addAttribute("fling",                InputField._1_STRING);
        addAttribute("explosionparticles",   InputField._1_STRING);
        addAttribute("dragmass",             InputField._1_STRING);
        addAttribute("autodisable",          InputField._1_STRING);
        addAttribute("hingedrag",            InputField._1_STRING);
        addAttribute("attenuationselect",    InputField._1_STRING);
        addAttribute("attenuationdeselect",  InputField._1_STRING);
        addAttribute("attenuationdrop",      InputField._1_STRING);
        addAttribute("attenuationdrag",      InputField._1_STRING);
        addAttribute("stacking",             InputField._1_STRING);
        addAttribute("stickyattached",       InputField._1_STRING);
        addAttribute("stickyunattached",     InputField._1_STRING);
        addAttribute("fallingattachment",    InputField._1_STRING);
        addAttribute("spawn",                InputField._1_STRING);
        addAttribute("autoattach",           InputField._1_STRING);
        addAttribute("isantigravattached",   InputField._1_FLAG);
        addAttribute("isantigravunattached", InputField._1_FLAG);
        addAttribute("popdelay",             InputField._1_STRING);
        addAttribute("distantsounds",        InputField._1_STRING);
        addAttribute("flammable",            InputField._1_STRING);

    }

}
