package com.worldOfGoo.level;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;
import com.woogleFX.editorObjects.attributes.MetaEditorAttribute;

public class Level extends EditorObject {

    public Level(EditorObject _parent, GameVersion version) {
        super(_parent, "level", version);

        addAttribute("ballsrequired",      InputField._1_NUMBER)                               .assertRequired();
        addAttribute("letterboxed",        InputField._1_FLAG)  .setDefaultValue("false")      .assertRequired();
        addAttribute("visualdebug",        InputField._1_FLAG)  .setDefaultValue("false")      .assertRequired();
        addAttribute("autobounds",         InputField._1_FLAG)  .setDefaultValue("false")      .assertRequired();
        addAttribute("textcolor",          InputField._1_COLOR) .setDefaultValue("255,255,255").assertRequired();
        addAttribute("texteffects",        InputField._1_FLAG);
        addAttribute("timebugprobability", InputField._1_NUMBER)                               .assertRequired();
        addAttribute("strandgeom",         InputField._1_FLAG)  .setDefaultValue("false");
        addAttribute("allowskip",          InputField._1_FLAG)  .setDefaultValue("true");
        addAttribute("cursor1color",       InputField._1_COLOR);
        addAttribute("cursor2color",       InputField._1_COLOR);
        addAttribute("cursor3color",       InputField._1_COLOR);
        addAttribute("cursor4color",       InputField._1_COLOR);
        addAttribute("retrytime",          InputField._1_NUMBER);
        addAttribute("zoomoutlimit",       InputField._1_NUMBER);

        setMetaAttributes(MetaEditorAttribute.parse("ballsrequired,timebugprobability,textcolor,retrytime,zoomoutlimit,?Flags<letterboxed,visualdebug,autobounds,strandgeom,allowskip,texteffects>?Cursor Colors<cursor1color,cursor2color,cursor3color,cursor4color>"));

    }


    @Override
    public String[] getPossibleChildren() {
        return new String[]{"BallInstance", "Strand", "camera", "endoncollision", "endonmessage", "endonnogeom", "fire", "levelexit", "loopsound", "music", "pipe", "signpost", "targetheight"};
    }

}
