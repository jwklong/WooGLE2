package com.worldOfGoo2.ball;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Ball_Image extends EditorObject {

    public _2_Ball_Image(EditorObject parent) {
        super(parent, "Image", GameVersion.VERSION_WOG2);

        addAttribute("imageId", InputField._2_CHILD).assertRequired();
        putAttributeChildAlias("imageId", "_2_ImageID");

        addAttribute("imageMaskId", InputField._2_CHILD).assertRequired();
        putAttributeChildAlias("imageMaskId", "_2_ImageID");

    }

}
