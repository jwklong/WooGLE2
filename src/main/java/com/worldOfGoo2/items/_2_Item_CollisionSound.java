package com.worldOfGoo2.items;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Item_CollisionSound extends EditorObject {

    public _2_Item_CollisionSound(EditorObject parent) {
        super(parent, "CollisionSound", GameVersion.VERSION_WOG2);

        addAttribute("materials", InputField._2_STRING).assertRequired();

        addAttribute("contactSound", InputField._2_CHILD).assertRequired();
        putAttributeChildAlias("contactSound", "_2_SoundID");

        addAttribute("contactParticleEffect", InputField._2_CHILD).assertRequired();
        putAttributeChildAlias("contactParticleEffect", "_2_UUID");

        addAttribute("minContactVelocity", InputField._2_STRING).assertRequired();
        addAttribute("maxContactVelocity", InputField._2_STRING).assertRequired();
        addAttribute("contactProbability", InputField._2_STRING).assertRequired();

    }

}
