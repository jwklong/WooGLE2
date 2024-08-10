package com.worldOfGoo2.items;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Item_ParticleEffect extends EditorObject {

    public _2_Item_ParticleEffect(EditorObject parent) {
        super(parent, "ParticleEffect", GameVersion.VERSION_WOG2);

        addAttribute("name", InputField._2_STRING).assertRequired();

        addAttribute("position", InputField._2_CHILD).assertRequired();
        putAttributeChildAlias("position", "_2_Point");

        addAttribute("rotation", InputField._2_STRING).assertRequired();
        addAttribute("scale", InputField._2_STRING).assertRequired();
        addAttribute("depth", InputField._2_STRING).assertRequired();
        addAttribute("sortOffset", InputField._2_STRING).assertRequired();
        addAttribute("relativeDepthAndSort", InputField._2_STRING).assertRequired();
        addAttribute("enabled", InputField._2_STRING).assertRequired();

    }

}
