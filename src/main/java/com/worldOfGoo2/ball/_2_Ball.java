package com.worldOfGoo2.ball;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.AttributeAdapter;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Ball extends EditorObject {

    public _2_Ball(EditorObject parent) {
        super(parent, "Ball", GameVersion.VERSION_WOG2);

        addAttributeAdapter("shape", AttributeAdapter.childAttributeAdapter(this, "shape", "shape", InputField._2_NUMBER));
        addAttributeAdapter("blinkColor", AttributeAdapter.childAttributeAdapter(this, "blinkColor", "blinkColor", InputField._2_NUMBER));


        /*
                detonateParticleEffect
        popSoundId
                fireworksParticleEffect
        trailParticleEffect
                shadowImageId
        strandImageId
                strandInactiveImageId
        strandInactiveOverlayImageId
                strandBurntImageId
        strandBackgroundImageId
                detachStrandImageId
        dragMarkerImageId
                detachMarkerImageId
        stainLiquidType
                splatImageIds
        stableFluidsDensityFactor
                stableFluidsDensityRange
        popSpawnItems
                popSpawnItemCountRange
        popSpawnItemRadiusRange
                popSpawnItemScaleRange
        strandShatterItem
                strandShatterParticleEffect
        thrusterStableFluidsImage
                markerColor
        deathParticleEffect
                laserGradientStart
        laserGradientEnd
                laserOverrideImage

         */


    }

}
