package com.worldOfGoo2.ball;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.AttributeAdapter;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class Ball extends EditorObject {

    public Ball(EditorObject parent) {
        super(parent, "Ball", GameVersion.VERSION_WOG2);

        addAttributeAdapter("shape", AttributeAdapter.childAttributeAdapter(this, "shape", "shape", InputField._2_NUMBER));
        addAttributeAdapter("blinkColor", AttributeAdapter.childAttributeAdapter(this, "blinkColor", "blinkColor", InputField._2_NUMBER));
        addAttributeAdapter("detonateParticleEffect", AttributeAdapter.childAttributeAdapter(this, "detonateParticleEffect", "detonateParticleEffect", InputField._2_PARTICLE_EFFECT_NAME));
        addAttributeAdapter("material", AttributeAdapter.childAttributeAdapter(this, "material", "material", InputField._2_STRING));
        addAttributeAdapter("popSoundId", AttributeAdapter.childAttributeAdapter(this, "popSoundId", "popSoundId", InputField._2_STRING));
        addAttributeAdapter("fireworksParticleEffect", AttributeAdapter.childAttributeAdapter(this, "fireworksParticleEffect", "fireworksParticleEffect", InputField._2_STRING));
        addAttributeAdapter("trailParticleEffect", AttributeAdapter.childAttributeAdapter(this, "trailParticleEffect", "trailParticleEffect", InputField._2_STRING));
        //spawnType
        //strandType
        //addAttributeAdapter("shadowImageId", AttributeAdapter.childAttributeAdapter(this, "shadowImageId", "shadowImageId", InputField._2_STRING));
        addAttributeAdapter("strandImageId", AttributeAdapter.childAttributeAdapter(this, "strandImageId", "strandImageId", InputField._2_STRING));
        addAttributeAdapter("strandInactiveImageId", AttributeAdapter.childAttributeAdapter(this, "strandInactiveImageId", "strandInactiveImageId", InputField._2_STRING));
        addAttributeAdapter("strandInactiveOverlayImageId", AttributeAdapter.childAttributeAdapter(this, "strandInactiveOverlayImageId", "strandInactiveOverlayImageId", InputField._2_STRING));
        addAttributeAdapter("strandBurntImageId", AttributeAdapter.childAttributeAdapter(this, "strandBurntImageId", "strandBurntImageId", InputField._2_STRING));
        addAttributeAdapter("strandBackgroundImageId", AttributeAdapter.childAttributeAdapter(this, "strandBackgroundImageId", "strandBackgroundImageId", InputField._2_STRING));
        //addAttributeAdapter("detachStrandImageId", AttributeAdapter.childAttributeAdapter(this, "detachStrandImageId", "detachStrandImageId", InputField._2_STRING));
        //addAttributeAdapter("dragMarkerImageId", AttributeAdapter.childAttributeAdapter(this, "dragMarkerImageId", "dragMarkerImageId", InputField._2_STRING));
        //addAttributeAdapter("detachMarkerImageId", AttributeAdapter.childAttributeAdapter(this, "detachMarkerImageId", "detachMarkerImageId", InputField._2_STRING));
        addAttributeAdapter("stainLiquidType", AttributeAdapter.childAttributeAdapter(this, "stainLiquidType", "stainLiquidType", InputField._2_STRING));
        //addAttributeAdapter("splatImageIds", AttributeAdapter.childAttributeAdapter(this, "splatImageIds", "splatImageIds", InputField._2_STRING));
        addAttributeAdapter("stableFluidsDensityFactor", AttributeAdapter.childAttributeAdapter(this, "stableFluidsDensityFactor", "stableFluidsDensityFactor", InputField._2_STRING));
        addAttributeAdapter("stableFluidsDensityRange", AttributeAdapter.childAttributeAdapter(this, "stableFluidsDensityRange", "stableFluidsDensityRange", InputField._2_STRING));
        //addAttributeAdapter("popSpawnItems", AttributeAdapter.childAttributeAdapter(this, "popSpawnItems", "popSpawnItems", InputField._2_STRING));
        addAttributeAdapter("popSpawnItemCountRange", AttributeAdapter.childAttributeAdapter(this, "popSpawnItemCountRange", "popSpawnItemCountRange", InputField._2_STRING));
        addAttributeAdapter("popSpawnItemRadiusRange", AttributeAdapter.childAttributeAdapter(this, "popSpawnItemRadiusRange", "popSpawnItemRadiusRange", InputField._2_STRING));
        addAttributeAdapter("popSpawnItemScaleRange", AttributeAdapter.childAttributeAdapter(this, "popSpawnItemScaleRange", "popSpawnItemScaleRange", InputField._2_STRING));
        addAttributeAdapter("strandShatterItem", AttributeAdapter.childAttributeAdapter(this, "strandShatterItem", "strandShatterItem", InputField._2_STRING));
        addAttributeAdapter("strandShatterParticleEffect", AttributeAdapter.childAttributeAdapter(this, "strandShatterParticleEffect", "strandShatterParticleEffect", InputField._2_STRING));
        addAttributeAdapter("thrusterStableFluidsImage", AttributeAdapter.childAttributeAdapter(this, "thrusterStableFluidsImage", "thrusterStableFluidsImage", InputField._2_STRING));
        addAttributeAdapter("markerColor", AttributeAdapter.childAttributeAdapter(this, "markerColor", "markerColor", InputField._2_STRING));
        //addAttributeAdapter("deathParticleEffect", AttributeAdapter.childAttributeAdapter(this, "deathParticleEffect", "deathParticleEffect", InputField._2_STRING));
        //addAttributeAdapter("laserGradientStart", AttributeAdapter.childAttributeAdapter(this, "laserGradientStart", "laserGradientStart", InputField._2_STRING));
        //addAttributeAdapter("laserGradientEnd", AttributeAdapter.childAttributeAdapter(this, "laserGradientEnd", "laserGradientEnd", InputField._2_STRING));
        //addAttributeAdapter("laserOverrideImage", AttributeAdapter.childAttributeAdapter(this, "laserOverrideImage", "laserOverrideImage", InputField._2_STRING));


    }

    @Override
    public Class<? extends EditorObject>[] getPossibleChildren() {
        return new Class[] { Part.class, StateAnimation.class, SoundEvent.class, ParticleEffect.class };
    }
}
