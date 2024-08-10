package com.worldOfGoo2.level;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.AttributeAdapter;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.editorObjects.attributes.MetaEditorAttribute;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Level_TerrainGroup extends EditorObject {

    public _2_Level_TerrainGroup(EditorObject parent) {
        super(parent, "TerrainGroup", GameVersion.VERSION_WOG2);

        addAttribute("textureOffset", InputField._2_CHILD_HIDDEN);
        putAttributeChildAlias("textureOffset", "_2_Point");
        addAttributeAdapter("textureOffset", AttributeAdapter.pointAttributeAdapter(this, "textureOffset", "textureOffset"));

        addAttribute("typeUuid", InputField._2_UUID);
        addAttribute("typeIndex", InputField._2_TERRAIN_GROUP_TYPE_INDEX);
        addAttribute("sortOffset", InputField._2_NUMBER);
        addAttribute("depth", InputField._2_NUMBER);
        addAttribute("foreground", InputField._2_BOOLEAN);
        addAttribute("collision", InputField._2_BOOLEAN);
        addAttribute("destructable", InputField._2_BOOLEAN);
        addAttribute("buildable", InputField._2_BOOLEAN);
        addAttribute("occluder", InputField._2_BOOLEAN);

        setMetaAttributes(MetaEditorAttribute.parse("textureOffset,typeUuid,typeIndex,sortOffset,depth,foreground,collision,destructable,buildable,occluder,"));

    }


    @Override
    public void onLoaded() {
        super.onLoaded();

        EditorObject textureOffset = getChildren("textureOffset").get(0);
        textureOffset.getAttribute("x").addChangeListener((observable, oldValue, newValue) ->
                setAttribute2("textureOffset", newValue + "," + getAttribute2("textureOffset").positionValue().getY()));
        textureOffset.getAttribute("y").addChangeListener((observable, oldValue, newValue) ->
                setAttribute2("textureOffset", getAttribute2("textureOffset").positionValue().getX() + "," + newValue));
        setAttribute2("textureOffset", textureOffset.getAttribute("x").stringValue() + "," + textureOffset.getAttribute("y").stringValue());

    }



}
