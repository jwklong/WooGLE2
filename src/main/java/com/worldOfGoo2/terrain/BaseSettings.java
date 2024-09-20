package com.worldOfGoo2.terrain;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;
import com.worldOfGoo2.misc._2_ImageID;

public class BaseSettings extends EditorObject {

    public BaseSettings(EditorObject parent) {
        super(parent, "BaseSettings", GameVersion.VERSION_WOG2);

        addAttribute("canBeStained", InputField._2_BOOLEAN);
        addAttribute("metersToUv", InputField._2_NUMBER);
        addAttribute("cliffDecorationValue", InputField._2_NUMBER);
        addAttribute("images", InputField._2_LIST_CHILD).setChildAlias(_2_ImageID.class);
    }

}
