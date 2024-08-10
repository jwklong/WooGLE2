package com.woogleFX.editorObjects.attributes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.dataTypes.Color;
import com.woogleFX.editorObjects.attributes.dataTypes.Position;
import com.woogleFX.file.resourceManagers.BaseGameResources;
import com.woogleFX.file.FileManager;
import com.woogleFX.gameData.animation.AnimationManager;
import com.woogleFX.file.resourceManagers.ResourceManager;
import com.woogleFX.engine.LevelManager;
import com.woogleFX.gameData.level.WOG1Level;
import com.woogleFX.gameData.particle.ParticleManager;
import com.worldOfGoo.level.BallInstance;
import com.worldOfGoo.particle.Ambientparticleeffect;
import com.worldOfGoo.particle.Particleeffect;
import com.worldOfGoo.resrc.ResrcImage;
import com.worldOfGoo.resrc.Sound;
import com.worldOfGoo.scene.Circle;
import com.worldOfGoo.scene.Compositegeom;
import com.worldOfGoo.scene.Rectangle;

public enum InputField {

    _1_STRING,
    _1_NUMBER,
    _1_NUMBER_NON_NEGATIVE,
    _1_NUMBER_POSITIVE,
    _1_POSITION,
    _1_IMAGE,
    _1_IMAGE_REQUIRED,
    _1_COLOR,
    _1_ANIMATION,
    _1_FLAG,
    _1_RANGE,
    _1_MATERIAL,
    _1_TAG,
    _1_GEOMETRY,
    _1_TEXT,
    _1_PARTICLES,
    _1_BALL,
    _1_OCD_TYPE,
    _1_IMAGE_TYPE,
    _1_GOOBALL_ID,
    _1_UNIQUE_GOOBALL_ID,
    _1_IMAGE_PATH,
    _1_SOUND_PATH,
    _1_FONT,
    _1_CONTEXT,

    _2_STRING,
    _2_NUMBER,
    _2_LEVEL_TYPE,
    _2_BOOLEAN,
    _2_UUID,
    _2_UID,
    _2_ISLAND_ID,
    _2_OBJECT,
    _2_LIST_STRING,
    _2_LIST_NUMBER,
    _2_BALL_TYPE,
    _2_TERRAIN_GROUP_TYPE_INDEX,
    _2_TERRAIN_GROUP,
    _2_ITEM_TYPE,
    _2_SKIN,
    _2_GAME_LEVEL,
    _2_SOUND_ID,
    _2_MUSIC_ID,
    _2_BALL_UID,
    _2_STRAND_TYPE,
    _2_ENVIRONMENT_ID,
    _2_BACKGROUND_ID,
    _2_LIQUID_TYPE,
    _2_PARTICLE_EFFECT_NAME,
    _2_COLLISION_GROUP,

    _2_CHILD,
    _2_CHILD_HIDDEN,
    _2_LIST_CHILD,
    _2_LIST_CHILD_HIDDEN,

    ;

    public static boolean verify(EditorObject object, InputField type, String potential) {

        if (type == null) return true;

        if (potential.isEmpty()) return !(type == _1_IMAGE_REQUIRED);

        switch (type) {

            case _1_STRING -> {
                return true;
            }

            case _1_NUMBER, _1_NUMBER_POSITIVE, _1_NUMBER_NON_NEGATIVE, _1_POSITION, _1_COLOR, _1_FLAG -> {
                return verifyDataType(type, potential);
            }

            case _1_GOOBALL_ID, _1_UNIQUE_GOOBALL_ID, _1_IMAGE, _1_IMAGE_REQUIRED, _1_GEOMETRY -> {
                return verifyLevelObject(object, type, potential);
            }

            case _1_ANIMATION, _1_TEXT, _1_BALL, _1_PARTICLES, _1_MATERIAL, _1_TAG, _1_FONT -> {
                return verifyResource(object, type, potential);
            }

            case _1_IMAGE_PATH, _1_SOUND_PATH -> {
                return verifyFilePath(object, type, potential);
            }

            case _1_OCD_TYPE, _1_CONTEXT -> {
                return verifyGameValue(type, potential);
            }

            default -> {
                return true;
            }

        }
    }


    private static boolean verifyDataType(InputField type, String potential) {

        switch (type) {

            case _1_NUMBER -> {
                try {
                    Double.parseDouble(potential);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }

            case _1_NUMBER_POSITIVE -> {
                try {
                    return Double.parseDouble(potential) > 0;
                } catch (NumberFormatException e) {
                    return false;
                }
            }

            case _1_NUMBER_NON_NEGATIVE -> {
                try {
                    return Double.parseDouble(potential) >= 0;
                } catch (NumberFormatException e) {
                    return false;
                }
            }

            case _1_POSITION -> {
                try {
                    Position.parse(potential);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }

            case _1_COLOR -> {
                try {
                    Color.parse(potential);
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }

            case _1_FLAG -> {
                return potential.equals("true") || potential.equals("false");
            }

            default -> {
                return false;
            }

        }

    }


    private static boolean verifyLevelObject(EditorObject object, InputField type, String potential) {

        switch (type) {

            case _1_GOOBALL_ID -> {
                WOG1Level level = (WOG1Level)LevelManager.getLevel();
                for (EditorObject ball : level.getLevel())
                    if (ball instanceof BallInstance &&
                            ball.getAttribute("id").stringValue().equals(potential)) return true;
                return false;
            }

            case _1_UNIQUE_GOOBALL_ID -> {
                WOG1Level level = (WOG1Level)LevelManager.getLevel();
                for (EditorObject ball : level.getLevel())
                    if (ball instanceof BallInstance && ball != object &&
                            ball.getAttribute("id").stringValue().equals(potential)) return false;
                return true;
            }

            case _1_IMAGE, _1_IMAGE_REQUIRED -> {
                WOG1Level level = (WOG1Level)LevelManager.getLevel();
                for (EditorObject resrc : level.getResrc())
                    if (resrc instanceof ResrcImage image &&
                            image.getAttribute("id").stringValue().equals(potential)) return true;
                return false;
            }

            case _1_GEOMETRY -> {
                WOG1Level level = (WOG1Level)LevelManager.getLevel();
                for (EditorObject EditorObject : level.getScene()) {
                    if (EditorObject instanceof Rectangle ||
                        EditorObject instanceof Circle ||
                        EditorObject instanceof Compositegeom) {
                        if (EditorObject.getAttribute("id").stringValue().equals(potential)) {
                            return true;
                        }
                    }
                }
                return false;
            }

            default -> {
                return false;
            }

        }

    }


    private static boolean verifyResource(EditorObject object, InputField type, String potential) {

        switch (type) {

            case _1_ANIMATION -> {
                return AnimationManager.hasAnimation(potential);
            }

            case _1_BALL -> {
                String dir = FileManager.getGameDir(object.getVersion());
                File[] ballFiles = new File(dir + "\\res\\balls").listFiles();
                if (ballFiles == null) return false;
                for (File ballFile : ballFiles) {
                    if (ballFile.getName().equals(potential)) {
                        return true;
                    }
                }
                return false;
            }

            case _1_TEXT -> {
                try {
                    ResourceManager.getText(null, potential, object.getVersion());
                    return true;
                } catch (FileNotFoundException ignored) {
                    return false;
                }
            }

            case _1_PARTICLES -> {
                for (EditorObject particle : ParticleManager.getParticles()) {
                    if ((particle instanceof Particleeffect || particle instanceof Ambientparticleeffect) &&
                            particle.getAttribute("name").stringValue().equals(potential)) {
                        return true;
                    }
                }
                return false;
            }

            case _1_MATERIAL -> {
                try {
                    ResourceManager.getMaterial(null, potential, object.getVersion());
                    return true;
                } catch (FileNotFoundException ignored) {
                    return false;
                }
            }

            case _1_TAG -> {
                return Arrays.stream(potential.split(",")).allMatch(BaseGameResources.TAGS::contains);
            }

            case _1_FONT -> {
                try {
                    ResourceManager.getFont(null, potential, object.getVersion());
                    return true;
                } catch (FileNotFoundException ignored) {
                    return false;
                }
            }

            default -> {
                return false;
            }

        }

    }


    private static boolean verifyFilePath(EditorObject object, InputField type, String potential) {

        switch (type) {

            case _1_IMAGE_PATH -> {
                if (object instanceof ResrcImage resrcImage) {
                    String path = resrcImage.getAttribute("path").stringValue();
                    String adjustedPath = resrcImage.getAdjustedPath();
                    String setDefaultsPart = adjustedPath.substring(0, adjustedPath.length() - path.length());
                    String dir = FileManager.getGameDir(resrcImage.getVersion());
                    return new File(dir + "\\" + setDefaultsPart + potential + ".png").exists();
                } else return false;
            }

            case _1_SOUND_PATH -> {
                if (object instanceof Sound sound) {
                    String path = sound.getAttribute("path").stringValue();
                    String adjustedPath = sound.getAdjustedPath();
                    String setDefaultsPart = adjustedPath.substring(0, adjustedPath.length() - path.length());
                    String dir = FileManager.getGameDir(sound.getVersion());
                    return new File(dir + "\\" + setDefaultsPart + potential + ".ogg").exists();
                } else return false;
            }

            default -> {
                return false;
            }

        }

    }


    private static boolean verifyGameValue(InputField type, String potential) {

        switch (type) {

            case _1_OCD_TYPE -> {
                return potential.equals("balls") || potential.equals("moves") || potential.equals("time");
            }

            case _1_CONTEXT -> {
                return potential.equals("screen");
            }

            default -> {
                return false;
            }

        }

    }


    public static double getRange(String range, double randomPercentage) {
        double min;
        double max;
        if (range.contains(",")){
            min = Double.parseDouble(range.substring(0, range.indexOf(",")));
            max = Double.parseDouble(range.substring(range.indexOf(",") + 1));
        } else {
            min = Double.parseDouble(range);
            max = min;
        }
        return (max - min) * randomPercentage + min;
    }

}
