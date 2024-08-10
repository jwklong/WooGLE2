package com.worldOfGoo2.items;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Item_ColorFX extends EditorObject {

    public _2_Item_ColorFX(EditorObject parent) {
        super(parent, "ColorFX", GameVersion.VERSION_WOG2);

        addAttribute("enabled", InputField._2_BOOLEAN);
        addAttribute("brightness", InputField._2_NUMBER);
        addAttribute("contrast", InputField._2_NUMBER);
        addAttribute("exposure", InputField._2_NUMBER);
        addAttribute("saturation", InputField._2_NUMBER);

    }

}
