package com.worldOfGoo2.terrain;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Terrain_TerrainType extends EditorObject {

    public _2_Terrain_TerrainType(EditorObject parent) {
        super(parent, "TerrainType", GameVersion.VERSION_WOG2);

        addAttribute("baseSettings", InputField._2_CHILD).setChildAlias(BaseSettings.class);
    }

}
