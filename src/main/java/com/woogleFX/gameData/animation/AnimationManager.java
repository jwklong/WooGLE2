package com.woogleFX.gameData.animation;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.engine.AssetManager;
import com.woogleFX.gameData.level.WOG1Level;
import com.worldOfGoo.scene.SceneLayer;

import java.util.ArrayList;

public class AnimationManager {

    private static final ArrayList<WoGAnimation> animations = new ArrayList<>();
    public static ArrayList<WoGAnimation> getAnimations() {
        return animations;
    }


    private static final ArrayList<SimpleBinAnimation> binAnimations = new ArrayList<>();
    public static ArrayList<SimpleBinAnimation> getBinAnimations() {
        return binAnimations;
    }
    public static SimpleBinAnimation getBinAnimation(String id) {
        for (SimpleBinAnimation binAnimation : binAnimations) {
            if (binAnimation.name.equals(id)) return binAnimation;
        }
        return null;
    }


    public static void updateAnimations(float timeElapsed) {
        if (AssetManager.getAsset() != null && AssetManager.getAsset() instanceof WOG1Level wog1Level) {
            for (EditorObject object : wog1Level.getScene()) {
                if (object instanceof SceneLayer sceneLayer) {
                    String anim = object.getAttribute("anim").stringValue();
                    if (!anim.isEmpty()) {
                        for (WoGAnimation animation : animations) {
                            if (animation.getName().equals(anim + ".anim.binuni")
                                    || animation.getName().equals(anim + ".anim.binltl")) {
                                sceneLayer.updateWithAnimation(animation, timeElapsed);
                            }
                        }
                    }
                }
            }
        }
    }

    public static boolean hasAnimation(String potential) {
        for (WoGAnimation animation : animations) {
            if (animation.getName().equals(potential + ".anim.binuni")
                    || animation.getName().equals(potential + ".anim.binltl")) {
                return true;
            }
        }
        return false;
    }

}
