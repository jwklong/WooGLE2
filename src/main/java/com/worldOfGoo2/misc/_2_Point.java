package com.worldOfGoo2.misc;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.editorObjects.attributes.MetaEditorAttribute;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Point extends EditorObject {

    public _2_Point(EditorObject parent) {
        super(parent, "Point", GameVersion.VERSION_WOG2);

        addAttribute("x", InputField._2_NUMBER);
        addAttribute("y", InputField._2_NUMBER);

        setMetaAttributes(MetaEditorAttribute.parse("x,y,"));

    }

}
