package com.worldOfGoo2.ball;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Ball extends EditorObject {

    public _2_Ball(EditorObject parent) {
        super(parent, "Ball", GameVersion.VERSION_WOG2);

        addAttribute("name", InputField._2_STRING).assertRequired();
        addAttribute("width", InputField._2_STRING).assertRequired();
        addAttribute("height", InputField._2_STRING).assertRequired();

        addAttribute("shape", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("shape", "_2_Ball_BallShape");

        addAttribute("sizeVariance", InputField._2_STRING).assertRequired();
        addAttribute("mass", InputField._2_STRING).assertRequired();
        addAttribute("towerMass", InputField._2_STRING).assertRequired();
        addAttribute("dragMass", InputField._2_STRING).assertRequired();
        addAttribute("destroyForce", InputField._2_STRING).assertRequired();
        addAttribute("maxDropDistance", InputField._2_STRING).assertRequired();
        addAttribute("maxStrands", InputField._2_STRING).assertRequired();
        addAttribute("walkSpeed", InputField._2_STRING).assertRequired();
        addAttribute("climbSpeed", InputField._2_STRING).assertRequired();
        addAttribute("speedVariance", InputField._2_STRING).assertRequired();
        addAttribute("antiGravFactor", InputField._2_STRING).assertRequired();
        addAttribute("isAntiGravAttached", InputField._2_STRING).assertRequired();
        addAttribute("isAntiGravUnattached", InputField._2_STRING).assertRequired();
        addAttribute("dampening", InputField._2_STRING).assertRequired();
        addAttribute("isEditable", InputField._2_STRING).assertRequired();
        addAttribute("isDraggable", InputField._2_STRING).assertRequired();
        addAttribute("isDetachable", InputField._2_STRING).assertRequired();
        addAttribute("diesOnDetach", InputField._2_STRING).assertRequired();
        addAttribute("isInvulnerable", InputField._2_STRING).assertRequired();
        addAttribute("isInvulnerableToLava", InputField._2_STRING);
        addAttribute("isStickyAttached", InputField._2_STRING).assertRequired();
        addAttribute("isStickyUnattached", InputField._2_STRING).assertRequired();
        addAttribute("attachToSameTypeOnly", InputField._2_STRING).assertRequired();
        addAttribute("isGrumpy", InputField._2_STRING).assertRequired();
        addAttribute("isStatic", InputField._2_STRING).assertRequired();
        addAttribute("isStaticWhenSleeping", InputField._2_STRING).assertRequired();
        addAttribute("selectionMarkerRadius", InputField._2_STRING);
        addAttribute("attachedMaginSS", InputField._2_STRING);
        addAttribute("unattachedMarginSS", InputField._2_STRING);
        addAttribute("health", InputField._2_STRING).assertRequired();

        addAttribute("blinkColor", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("blinkColor", "_2_Color");

        addAttribute("jumpMultiplierMin", InputField._2_STRING).assertRequired();
        addAttribute("jumpMultiplierMax", InputField._2_STRING).assertRequired();
        addAttribute("collideAttached", InputField._2_STRING).assertRequired();
        addAttribute("collideWithAttached", InputField._2_STRING).assertRequired();
        addAttribute("allowAttachmentsWhenStuck", InputField._2_STRING).assertRequired();
        addAttribute("allowAttachmentsWhenFalling", InputField._2_STRING).assertRequired();
        addAttribute("motorMaxForce", InputField._2_STRING).assertRequired();
        addAttribute("motorMaxForceUndiscovered", InputField._2_STRING);
        addAttribute("isSuckable", InputField._2_STRING).assertRequired();
        addAttribute("opensExitUnattached", InputField._2_STRING);
        addAttribute("affectsAutoboundsAttached", InputField._2_STRING).assertRequired();
        addAttribute("affectsAutoboundsUnattached", InputField._2_STRING).assertRequired();
        addAttribute("burnTime", InputField._2_STRING).assertRequired();
        addAttribute("detonateForce", InputField._2_STRING).assertRequired();
        addAttribute("detonateRadius", InputField._2_STRING).assertRequired();
        addAttribute("detonateKillBalls", InputField._2_STRING);
        addAttribute("detonateKillItems", InputField._2_STRING);
        addAttribute("detonateKillTerrain", InputField._2_STRING);

        addAttribute("detonateParticleEffect", InputField._2_STRING);
        putAttributeChildAlias("detonateParticleEffect", "_2_UUID");

        addAttribute("autoAttach", InputField._2_STRING).assertRequired();
        addAttribute("isClimber", InputField._2_STRING).assertRequired();
        addAttribute("attachedParticleBarrierFactor", InputField._2_STRING).assertRequired();

        addAttribute("material", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("material", "_2_Ball_Material");

        addAttribute("contains", InputField._2_STRING).assertRequired();

        addAttribute("popSoundId", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("popSoundId", "_2_SoundID");

        addAttribute("popParticlesId", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("popParticlesId", "_2_UUID");

        addAttribute("fireworksParticleEffect", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("fireworksParticleEffect", "_2_UUID");

        addAttribute("trailEffectId", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("trailEffectId", "_2_UUID");

        addAttribute("trailParticleEffect", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("trailParticleEffect", "_2_UUID");

        addAttribute("trailEffectEnabled", InputField._2_STRING).assertRequired();
        addAttribute("popDuration", InputField._2_STRING).assertRequired();
        addAttribute("popDelayMin", InputField._2_STRING).assertRequired();
        addAttribute("popDelayMax", InputField._2_STRING).assertRequired();
        addAttribute("hideEyes", InputField._2_STRING).assertRequired();
        addAttribute("botoxEyesWhenAttached", InputField._2_STRING);
        addAttribute("isBehindStrands", InputField._2_STRING).assertRequired();
        addAttribute("wakeOtherBallsAtDistance", InputField._2_STRING).assertRequired();

        addAttribute("spawnType", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("spawnType", "_2_Ball_Type");

        addAttribute("decay", InputField._2_STRING).assertRequired();
        addAttribute("flingForceFactor", InputField._2_STRING).assertRequired();
        addAttribute("flingStrandMaxLength", InputField._2_STRING).assertRequired();
        addAttribute("autoDisable", InputField._2_STRING).assertRequired();
        addAttribute("isStacking", InputField._2_STRING);
        addAttribute("maxDragForce", InputField._2_STRING).assertRequired();
        addAttribute("dragDampeningFactor", InputField._2_STRING).assertRequired();
        addAttribute("alwaysLookAtMouse", InputField._2_STRING).assertRequired();
        addAttribute("hingeDrag", InputField._2_STRING).assertRequired();
        addAttribute("maxAttachSpeed", InputField._2_STRING).assertRequired();
        addAttribute("jumpOnWakeup", InputField._2_STRING).assertRequired();
        addAttribute("thrust", InputField._2_STRING).assertRequired();
        addAttribute("useDistantSounds", InputField._2_STRING).assertRequired();
        addAttribute("canBeRotatedByHand", InputField._2_STRING).assertRequired();
        addAttribute("stencil", InputField._2_STRING).assertRequired();
        addAttribute("stencilWhenAttached", InputField._2_STRING);
        addAttribute("canSuckLiquidExternally", InputField._2_STRING).assertRequired();
        addAttribute("canSuckLiquidFromPipe", InputField._2_STRING).assertRequired();
        addAttribute("liquidParticlesPerSecond", InputField._2_STRING).assertRequired();
        addAttribute("initialBallLiquidAmount", InputField._2_STRING).assertRequired();
        addAttribute("maxBallLiquidAmount", InputField._2_STRING).assertRequired();
        addAttribute("maxStrandLiquidAmount", InputField._2_STRING).assertRequired();
        addAttribute("strandSuckLiquidParticlesPerSecond", InputField._2_STRING).assertRequired();
        addAttribute("hitVelocityAccumulationLimit", InputField._2_STRING).assertRequired();

        addAttribute("shadowImageId", InputField._2_STRING);
        putAttributeChildAlias("shadowImageId", "_2_ImageID");

        addAttribute("shadowImageIsAdditive", InputField._2_STRING);

        addAttribute("strandType", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("strandType", "_2_Ball_StrandType");

        addAttribute("springConstMin", InputField._2_STRING).assertRequired();
        addAttribute("springConstMax", InputField._2_STRING).assertRequired();
        addAttribute("strandDampening", InputField._2_STRING).assertRequired();
        addAttribute("maxReplacementStrandLength", InputField._2_STRING).assertRequired();
        addAttribute("maxNormalStrandLength", InputField._2_STRING).assertRequired();
        addAttribute("minStrandLength", InputField._2_STRING).assertRequired();
        addAttribute("strandShrinkLength", InputField._2_STRING).assertRequired();
        addAttribute("maxStretchForce", InputField._2_STRING).assertRequired();
        addAttribute("maxCompressForce", InputField._2_STRING).assertRequired();
        addAttribute("strandDensity", InputField._2_STRING).assertRequired();
        addAttribute("strandThickness", InputField._2_STRING).assertRequired();
        addAttribute("isSingleStrandAllowed", InputField._2_STRING).assertRequired();
        addAttribute("isSingleStrandAllowedEvenForOneStrand", InputField._2_STRING);
        addAttribute("allowBallToStrandConversion", InputField._2_STRING).assertRequired();
        addAttribute("swallowBall", InputField._2_STRING);
        addAttribute("useStrandConnect", InputField._2_STRING).assertRequired();
        addAttribute("isStrandWalkable", InputField._2_STRING).assertRequired();
        addAttribute("canShrinkStrand", InputField._2_STRING).assertRequired();

        addAttribute("strandImageId", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("strandImageId", "_2_ImageID");

        addAttribute("strandInactiveImageId", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("strandInactiveImageId", "_2_ImageID");

        addAttribute("strandInactiveOverlayImageId", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("strandInactiveOverlayImageId", "_2_ImageID");

        addAttribute("strandIgniteDelay", InputField._2_STRING).assertRequired();
        addAttribute("strandBurnSpeed", InputField._2_STRING).assertRequired();
        addAttribute("strandFireParticlesId", InputField._2_STRING).assertRequired();

        addAttribute("strandBurntImageId", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("strandBurntImageId", "_2_ImageID");

        addAttribute("strandBackgroundImageId", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("strandBackgroundImageId", "_2_ImageID");

        addAttribute("detachStrandImageId", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("detachStrandImageId", "_2_ImageID");

        addAttribute("detachStrandMaxLength", InputField._2_STRING).assertRequired();

        addAttribute("dragMarkerImageId", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("dragMarkerImageId", "_2_ImageID");

        addAttribute("detachMarkerImageId", InputField._2_STRING);
        putAttributeChildAlias("detachMarkerImageId", "_2_ImageID");

        addAttribute("markerRotSpeed", InputField._2_STRING).assertRequired();

        addAttribute("stainLiquidType", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("stainLiquidType", "_2_LiquidType");

        addAttribute("deselectAttenuationFunc", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("deselectAttenuationFunc", "_2_Ball_AttenuationFunction");

        addAttribute("selectAttenuationFunc", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("selectAttenuationFunc", "_2_Ball_AttenuationFunction");

        addAttribute("dropAttenuationFunc", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("dropAttenuationFunc", "_2_Ball_AttenuationFunction");

        addAttribute("dragAttenuationFunc", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("dragAttenuationFunc", "_2_Ball_AttenuationFunction");

        addAttribute("spawnAttenuationFunc", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("spawnAttenuationFunc", "_2_Ball_AttenuationFunction");

        addAttribute("ballParts", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("ballParts", "_2_Ball_Part");

        addAttribute("bodyPart", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("bodyPart", "_2_Ball_BodyPart");

        addAttribute("stateAnimations", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("stateAnimations", "_2_Ball_StateAnimation");

        addAttribute("splatImageIds", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("splatImageIds", "_2_ImageID");

        addAttribute("soundEvents", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("soundEvents", "_2_Ball_SoundEvent");

        addAttribute("flashAnimation", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("flashAnimation", "_2_Ball_FlashAnimation");

        addAttribute("stateScales", InputField._2_STRING).assertRequired();

        addAttribute("particleEffects", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("particleEffects", "_2_Ball_ParticleEffect");

        addAttribute("stableFluidsDensityFactor", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("stableFluidsDensityFactor", "_2_Color");

        addAttribute("stableFluidsMinTrailVelocity", InputField._2_STRING).assertRequired();

        addAttribute("stableFluidsDensityRange", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("stableFluidsDensityRange", "_2_Point");

        addAttribute("isLauncher", InputField._2_STRING).assertRequired();
        addAttribute("canBeSuckedByLauncher", InputField._2_STRING).assertRequired();
        addAttribute("whenAttachedRenderOnTop", InputField._2_STRING).assertRequired();
        addAttribute("lighting", InputField._2_STRING).assertRequired();

        addAttribute("popSpawnItems", InputField._2_STRING);
        putAttributeChildAlias("popSpawnItems", "_2_UUID");

        addAttribute("popSpawnItemProbability", InputField._2_STRING);

        addAttribute("popSpawnItemCountRange", InputField._2_STRING);
        putAttributeChildAlias("popSpawnItemCountRange", "_2_Point");

        addAttribute("popSpawnItemRadiusRange", InputField._2_STRING);
        putAttributeChildAlias("popSpawnItemRadiusRange", "_2_Point");

        addAttribute("popSpawnItemScaleRange", InputField._2_STRING);
        putAttributeChildAlias("popSpawnItemScaleRange", "_2_Point");

        addAttribute("strandShatterItem", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("strandShatterItem", "_2_UUID");

        addAttribute("strandShatterItemProbability", InputField._2_STRING).assertRequired();

        addAttribute("strandShatterParticleEffect", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("strandShatterParticleEffect", "_2_UUID");

        addAttribute("strandShatterParticleEffectProbability", InputField._2_STRING).assertRequired();
        addAttribute("strandShatterFragmentSize", InputField._2_STRING).assertRequired();
        addAttribute("unwalkableTime", InputField._2_STRING).assertRequired();
        addAttribute("dynamicLightingFactor", InputField._2_STRING).assertRequired();
        addAttribute("attachedDynamicLightingFactor", InputField._2_STRING);
        addAttribute("undiscoveredDynamicLightingFactor", InputField._2_STRING);
        addAttribute("shadow", InputField._2_STRING).assertRequired();
        addAttribute("neededLighting", InputField._2_STRING).assertRequired();
        addAttribute("sortOffset", InputField._2_STRING);
        addAttribute("canPinToJelly", InputField._2_STRING);
        addAttribute("hideWhenPinnedToJelly", InputField._2_STRING);
        addAttribute("popOnUnpinFromJelly", InputField._2_STRING);
        addAttribute("canStrandCutJelly", InputField._2_STRING);
        addAttribute("inactiveStrandCollideWithJelly", InputField._2_STRING);
        addAttribute("renderInJellyBackground", InputField._2_STRING);

        addAttribute("thrusterStableFluidsImage", InputField._2_STRING);
        putAttributeChildAlias("thrusterStableFluidsImage", "_2_ImageID");

        addAttribute("markerColor", InputField._2_STRING).assertRequired();
        putAttributeChildAlias("markerColor", "_2_Color");

        addAttribute("zoomFactorAttached", InputField._2_STRING);
        addAttribute("zoomFactorUnattached", InputField._2_STRING);
        addAttribute("zoomSpeed", InputField._2_STRING);
        addAttribute("liquidParticlesRadiusScale", InputField._2_STRING);

        addAttribute("structureFillingLayers", InputField._2_STRING);
        putAttributeChildAlias("structureFillingLayers", "_2_Ball_StructureFillingLayer");

        addAttribute("ropeOffsetRadiusScale", InputField._2_STRING);
        addAttribute("ropeStiffness", InputField._2_STRING);
        addAttribute("ropeDamping", InputField._2_STRING);

        addAttribute("collideWithParticlesAttached", InputField._2_STRING);
        addAttribute("liquidSinkOffset", InputField._2_STRING);
        addAttribute("strandGrowMultiplier", InputField._2_STRING);
        addAttribute("renderInPlayMode", InputField._2_STRING);
        addAttribute("canActOnBalls", InputField._2_STRING);

        addAttribute("visibilityScale", InputField._2_STRING);
        addAttribute("rotationalDampening", InputField._2_STRING);
        addAttribute("collisionGroup", InputField._2_STRING);
        addAttribute("despawnTriggersFullDeath", InputField._2_STRING);

        addAttribute("deathParticleEffect", InputField._2_CHILD);
        putAttributeChildAlias("deathParticleEffect", "_2_UUID");

        addAttribute("flashAnimationPreLiquidLayerTextureIndices", InputField._2_STRING);
        addAttribute("inputPipeForceVariation", InputField._2_STRING);
        addAttribute("laserCount", InputField._2_STRING);

        addAttribute("laserGradientStart", InputField._2_STRING);
        putAttributeChildAlias("laserGradientStart", "_2_Color");

        addAttribute("laserGradientEnd", InputField._2_STRING);
        putAttributeChildAlias("laserGradientEnd", "_2_Color");

        addAttribute("laserOverrideImage", InputField._2_STRING);
        putAttributeChildAlias("laserOverrideImage", "_2_ImageID");

        addAttribute("countMoveOnUnatachedRelease", InputField._2_STRING);
        addAttribute("maxStrandAngle", InputField._2_STRING);
        addAttribute("maxStrandSeparation", InputField._2_STRING);

    }

}
