package com.worldOfGoo2.ball;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Ball_Part extends EditorObject {


    public _2_Ball_Part(EditorObject parent) {
        super(parent, "Ball_Part", GameVersion.VERSION_WOG2);

        addAttribute("name", InputField._2_STRING).assertRequired();

        addAttribute("images", InputField._2_STRING).assertRequired();

        addAttribute("imageBackgroundIds", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("imageBackgroundIds", "_2_ImageID");

        addAttribute("layer", InputField._2_NUMBER).assertRequired();
        addAttribute("drawWhenAttached", InputField._2_BOOLEAN);
        addAttribute("drawWhenNotAttached", InputField._2_BOOLEAN);
        addAttribute("minX", InputField._2_NUMBER).assertRequired();
        addAttribute("maxX", InputField._2_NUMBER).assertRequired();
        addAttribute("minY", InputField._2_NUMBER).assertRequired();
        addAttribute("maxY", InputField._2_NUMBER).assertRequired();
        addAttribute("minRangeX", InputField._2_NUMBER).assertRequired();
        addAttribute("maxRangeX", InputField._2_NUMBER).assertRequired();
        addAttribute("minRangeY", InputField._2_NUMBER).assertRequired();
        addAttribute("maxRangeY", InputField._2_NUMBER).assertRequired();

        addAttribute("states", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("states", "_2_Ball_State");

        addAttribute("isActiveWhenUndiscovered", InputField._2_BOOLEAN).assertRequired();
        addAttribute("scale", InputField._2_NUMBER).assertRequired();
        addAttribute("scaleIsRelative", InputField._2_BOOLEAN).assertRequired();
        addAttribute("rotation", InputField._2_NUMBER).assertRequired();
        addAttribute("isEye", InputField._2_BOOLEAN).assertRequired();

        addAttribute("pupilImageIds", InputField._2_STRING).assertRequired();

        addAttribute("pupilInset", InputField._2_NUMBER).assertRequired();
        addAttribute("pupilScale", InputField._2_NUMBER);
        addAttribute("isRotating", InputField._2_BOOLEAN).assertRequired();
        addAttribute("stretchMaxSpeed", InputField._2_NUMBER).assertRequired();
        addAttribute("stretchParallel", InputField._2_NUMBER).assertRequired();
        addAttribute("stretchPerpendicular", InputField._2_NUMBER).assertRequired();

        addAttribute("color", InputField._2_STRING);
        putAttributeChildAlias("color", "_2_Color");

        addAttribute("stretchFactorFromStrandForce", InputField._2_NUMBER).assertRequired();


        putAttributeChildAlias("images", "_2_Ball_Image");
        putAttributeChildAlias("pupilImageIds", "_2_ImageID");

    }


    @Override
    public String[] getPossibleChildren() {
        return new String[]{ "images", "imageBackgroundIds", "states", "pupilImageIds", "color" };
    }

}
