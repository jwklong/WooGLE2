package com.worldOfGoo2.ball;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Ball_StructureFillingLayer extends EditorObject {

    public _2_Ball_StructureFillingLayer(EditorObject parent) {
        super(parent, "StructureFillingLayer", GameVersion.VERSION_WOG2);

        addAttribute("renderLayer", InputField._2_STRING);
        putAttributeChildAlias("renderLayer", "_2_Layer");

        addAttribute("textureId", InputField._2_STRING);
        putAttributeChildAlias("textureId", "_2_ImageID");

        addAttribute("alpha", InputField._2_STRING);
        addAttribute("textureScale", InputField._2_STRING);

        addAttribute("animationTextureId", InputField._2_STRING);
        putAttributeChildAlias("animationTextureId", "_2_ImageID");

        addAttribute("animationDirectionUp", InputField._2_STRING);
        addAttribute("radialUvs", InputField._2_STRING);
        addAttribute("delay", InputField._2_STRING);
        addAttribute("speed", InputField._2_STRING);
        addAttribute("scaleAdjustOffset", InputField._2_STRING);
        addAttribute("borderAlphaBlendOffset", InputField._2_STRING);
        addAttribute("borderAlphaBlendWidth", InputField._2_STRING);

    }

}
