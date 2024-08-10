package com.worldOfGoo2.items;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Item_Body extends EditorObject {

    public _2_Item_Body(EditorObject parent) {
        super(parent, "Body", GameVersion.VERSION_WOG2);

        addAttribute("id", InputField._2_STRING).assertRequired();
        addAttribute("type", InputField._2_NUMBER).assertRequired();

        addAttribute("pos", InputField._2_CHILD).assertRequired();
        putAttributeChildAlias("pos", "_2_Point");

        addAttribute("rotation", InputField._2_NUMBER).assertRequired();
        addAttribute("collisionEnabled", InputField._2_BOOLEAN).assertRequired();
        addAttribute("materialName", InputField._2_STRING).assertRequired();
        addAttribute("tags", InputField._2_STRING).assertRequired();

        addAttribute("geoms", InputField._2_LIST_CHILD).assertRequired();
        putAttributeChildAlias("geoms", "_2_Item_Geometry");

        addAttribute("categoryBits", InputField._2_STRING).assertRequired();

    }

}
