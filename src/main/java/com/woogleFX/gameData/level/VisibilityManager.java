package com.woogleFX.gameData.level;

import com.woogleFX.engine.AssetManager;

public class VisibilityManager {

    public static void showHideCameras() {
        AssetManager.getAsset().getVisibilitySettings().setShowCameras(
                !AssetManager.getAsset().getVisibilitySettings().isShowCameras());
    }

    public static void showHideForcefields() {
        AssetManager.getAsset().getVisibilitySettings().setShowForcefields(
                !AssetManager.getAsset().getVisibilitySettings().isShowForcefields());
    }

    public static void showHideGeometry() {
        AssetManager.getAsset().getVisibilitySettings().setShowGeometry(
                AssetManager.getAsset().getVisibilitySettings().getShowGeometry() - 1);
        if (AssetManager.getAsset().getVisibilitySettings().getShowGeometry() < 0) {
            AssetManager.getAsset().getVisibilitySettings().setShowGeometry(2);
        }
    }

    public static void showHideGraphics() {
        AssetManager.getAsset().getVisibilitySettings().setShowGraphics(
                !AssetManager.getAsset().getVisibilitySettings().isShowGraphics());
    }

    public static void showHideGoos() {
        AssetManager.getAsset().getVisibilitySettings().setShowGoos(
                AssetManager.getAsset().getVisibilitySettings().getShowGoos() - 1);
        if (AssetManager.getAsset().getVisibilitySettings().getShowGoos() < 0) {
            AssetManager.getAsset().getVisibilitySettings().setShowGoos(2);
        }
    }

    public static void showHideParticles() {
        AssetManager.getAsset().getVisibilitySettings().setShowParticles(
                !AssetManager.getAsset().getVisibilitySettings().isShowParticles());
    }

    public static void showHideLabels() {
        AssetManager.getAsset().getVisibilitySettings().setShowLabels(
                !AssetManager.getAsset().getVisibilitySettings().isShowLabels());
    }

    public static void showHideAnim() {
        AssetManager.getAsset().getVisibilitySettings().setShowAnimations(
                !AssetManager.getAsset().getVisibilitySettings().isShowAnimations());
    }

    public static void showHideSceneBGColor() {
        AssetManager.getAsset().getVisibilitySettings().setShowSceneBGColor(
                !AssetManager.getAsset().getVisibilitySettings().isShowSceneBGColor());
    }

}
