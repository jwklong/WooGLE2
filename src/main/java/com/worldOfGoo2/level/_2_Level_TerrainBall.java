package com.worldOfGoo2.level;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.editorObjects.attributes.MetaEditorAttribute;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Level_TerrainBall extends EditorObject {

    public _2_Level_TerrainBall(EditorObject parent) {
        super(parent, "TerrainBall", GameVersion.VERSION_WOG2);

        addAttribute("group", InputField._2_TERRAIN_GROUP);

        setMetaAttributes(MetaEditorAttribute.parse("group,"));

    }

}
