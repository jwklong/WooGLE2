package com.worldOfGoo2.items;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Item extends EditorObject {

    public _2_Item(EditorObject parent) {
        super(parent, "Item", GameVersion.VERSION_WOG2);

        addAttribute("name", InputField._2_STRING).assertRequired();
        addAttribute("uuid", InputField._2_UUID).assertRequired();
        addAttribute("type", InputField._2_ITEM_TYPE).assertRequired();
        addAttribute("category", InputField._2_STRING).assertRequired();
        addAttribute("layer", InputField._2_NUMBER);
        addAttribute("minDepth", InputField._2_NUMBER).assertRequired();
        addAttribute("maxDepth", InputField._2_NUMBER).assertRequired();
        addAttribute("sortOffset", InputField._2_NUMBER);
        addAttribute("radiusOverride", InputField._2_NUMBER).assertRequired();
        addAttribute("canRotate", InputField._2_BOOLEAN).assertRequired();
        addAttribute("uniformScale", InputField._2_BOOLEAN);
        addAttribute("canBeCopied", InputField._2_BOOLEAN);
        addAttribute("canTriggerEndOfLevel", InputField._2_BOOLEAN);
        addAttribute("variations", InputField._2_BOOLEAN);
        addAttribute("useLighting", InputField._2_BOOLEAN).assertRequired();
        addAttribute("castShadow", InputField._2_BOOLEAN);
        addAttribute("checkVisibility", InputField._2_BOOLEAN);
        addAttribute("color", InputField._2_NUMBER);
        addAttribute("animationName", InputField._2_STRING);
        addAttribute("animationAlias", InputField._2_STRING);
        addAttribute("animationSpeed", InputField._2_NUMBER).assertRequired();
        addAttribute("animationInNormalizedTime", InputField._2_NUMBER);

        addAttribute("animationPlayNormalizedTimeRange", InputField._2_CHILD);
        putAttributeChildAlias("animationPlayNormalizedTimeRange", "_2_Point");

        addAttribute("animationLocalPosition", InputField._2_CHILD);
        putAttributeChildAlias("animationLocalPosition", "_2_Point");

        addAttribute("animationLocalScale", InputField._2_CHILD);
        putAttributeChildAlias("animationLocalScale", "_2_Point");

        addAttribute("animationRotation", InputField._2_NUMBER);
        addAttribute("userValue1", InputField._2_NUMBER).assertRequired();
        addAttribute("userValue2", InputField._2_NUMBER).assertRequired();
        addAttribute("snapObjectIndex", InputField._2_NUMBER);
        addAttribute("destroyForce", InputField._2_NUMBER);

        addAttribute("objects", InputField._2_CHILD).assertRequired();
        putAttributeChildAlias("objects", "_2_Item_Object");

        addAttribute("alternates", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("alternates", "_2_Item_Alternates");

        addAttribute("userVariables", InputField._2_CHILD).assertRequired();
        putAttributeChildAlias("userVariables", "_2_Item_UserVariable");

        addAttribute("snapPoints", InputField._2_LIST_CHILD);
        putAttributeChildAlias("snapPoints", "_2_Point");

        addAttribute("limits", InputField._2_CHILD).assertRequired();
        putAttributeChildAlias("limits", "_2_Item_Limit");

        addAttribute("sound", InputField._2_CHILD);
        putAttributeChildAlias("sound", "_2_Item_Sound");

        addAttribute("laserSettings", InputField._2_CHILD);
        putAttributeChildAlias("laserSettings", "_2_Item_LaserSettings");

        addAttribute("buoyant", InputField._2_STRING);
        addAttribute("animation", InputField._2_STRING);
        addAttribute("animationLoop", InputField._2_STRING);
        addAttribute("canExplode", InputField._2_STRING);

        addAttribute("destructionSound", InputField._2_CHILD);
        putAttributeChildAlias("destructionSound", "_2_SoundID");

        addAttribute("destructionEffect", InputField._2_STRING);


    }

}
