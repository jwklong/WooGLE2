package com.worldOfGoo2.misc;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_ImageID extends EditorObject {

    public _2_ImageID(EditorObject parent) {
        super(parent, "ImageID", GameVersion.VERSION_WOG2);

        addAttribute("imageId", InputField._2_STRING);

    }

}
