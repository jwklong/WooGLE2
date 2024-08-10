package com.worldOfGoo2.ball;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Ball_StrandType extends EditorObject {

    public _2_Ball_StrandType(EditorObject parent) {
        super(parent, "StrandType", GameVersion.VERSION_WOG2);

        addAttribute("strandType", InputField._2_STRAND_TYPE);

    }

}
