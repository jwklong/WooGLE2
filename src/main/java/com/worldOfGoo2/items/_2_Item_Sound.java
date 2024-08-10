package com.worldOfGoo2.items;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Item_Sound extends EditorObject {

    public _2_Item_Sound(EditorObject parent) {
        super(parent, "Item_Sound", GameVersion.VERSION_WOG2);

        addAttribute("collisionSounds", InputField._2_LIST_CHILD).assertRequired();
        putAttributeChildAlias("collisionSounds", "_2_Item_CollisionSound");

        addAttribute("minCollisionCosine", InputField._2_STRING).assertRequired();
        addAttribute("collisionTimeout", InputField._2_STRING).assertRequired();

        addAttribute("collisionLoopSound", InputField._2_CHILD).assertRequired();
        putAttributeChildAlias("collisionLoopSound", "_2_SoundID");

        addAttribute("minCollisionLoopAngularVelocity", InputField._2_STRING).assertRequired();
        addAttribute("maxCollisionLoopAngularVelocity", InputField._2_STRING).assertRequired();
        addAttribute("collisionLoopBlendSpeed", InputField._2_STRING).assertRequired();

    }

}
