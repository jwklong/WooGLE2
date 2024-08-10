package com.woogleFX.file.resourceManagers;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.engine.gui.alarms.ErrorAlarm;
import com.woogleFX.engine.gui.alarms.LoadingResourcesAlarm;
import com.woogleFX.file.FileManager;
import com.woogleFX.file.fileImport.ObjectGOOParser;
import com.woogleFX.gameData.animation.AnimationManager;
import com.woogleFX.gameData.animation.AnimationReader;
import com.woogleFX.gameData.ball.AtlasManager;
import com.woogleFX.gameData.level.GameVersion;
import com.woogleFX.gameData.particle.ParticleManager;
import com.worldOfGoo.resrc.Material;
import com.worldOfGoo.particle._Particle;
import com.worldOfGoo.resrc.Font;
import com.worldOfGoo.resrc.ResrcImage;
import com.worldOfGoo.resrc.SetDefaults;
import com.worldOfGoo.resrc.Sound;
import com.worldOfGoo.text.TextString;
import com.worldOfGoo2.items._2_Item;
import com.worldOfGoo2.util.ItemHelper;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/** Stores global resources (those specified in properties/resources.xml). */
public class GlobalResourceManager {

    private static final ArrayList<EditorObject> oldResources = new ArrayList<>();
    public static ArrayList<EditorObject> getOldResources() {
        return oldResources;
    }


    private static final ArrayList<EditorObject> newResources = new ArrayList<>();
    public static ArrayList<EditorObject> getNewResources() {
        return newResources;
    }


    private static final ArrayList<EditorObject> sequelResources = new ArrayList<>();
    public static ArrayList<EditorObject> getSequelResources() {
        return sequelResources;
    }


    private static final ArrayList<String> allFailedResources = new ArrayList<>();


    public static void init() {

        allFailedResources.clear();

        oldResources.clear();
        if (!FileManager.getGameDir(GameVersion.VERSION_WOG1_OLD).isEmpty()) {
            openResources(GameVersion.VERSION_WOG1_OLD);
            openParticles(GameVersion.VERSION_WOG1_OLD);
            openAnimations(GameVersion.VERSION_WOG1_OLD);
            openText(GameVersion.VERSION_WOG1_OLD);
            openMaterials(GameVersion.VERSION_WOG1_OLD);
        }

        newResources.clear();
        if (!FileManager.getGameDir(GameVersion.VERSION_WOG1_NEW).isEmpty()) {
            openResources(GameVersion.VERSION_WOG1_NEW);
            openParticles(GameVersion.VERSION_WOG1_NEW);
            openAnimations(GameVersion.VERSION_WOG1_NEW);
            openText(GameVersion.VERSION_WOG1_NEW);
            openMaterials(GameVersion.VERSION_WOG1_NEW);
        }

        sequelResources.clear();
        if (!FileManager.getGameDir(GameVersion.VERSION_WOG2).isEmpty()) {
            openResources(GameVersion.VERSION_WOG2);
            openItems();
            AtlasManager.reloadAtlas();
            //openParticles(GameVersion.VERSION_WOG2);
            //openAnimations(GameVersion.VERSION_WOG2);
            //openText(GameVersion.VERSION_WOG2);
            //openMaterials(GameVersion.VERSION_WOG2);
        }

        // TODO

        // Load particle names, remove duplicates, and sort them alphabetically
        Set<String> particleNames = new HashSet<>();
        ParticleManager.getParticles().stream()
                .filter(particle -> particle.attributeExists("name"))
                .forEach(particle -> particleNames.add(particle.getAttribute("name").stringValue()));
        ParticleManager.getSortedParticleNames().clear();
        ParticleManager.getSortedParticleNames().addAll(particleNames);
        ParticleManager.getSortedParticleNames().sort(String::compareToIgnoreCase);

        if (!allFailedResources.isEmpty()) {
            StringBuilder fullError = new StringBuilder();
            for (String resource : allFailedResources) {
                fullError.append("\n").append(resource);
            }
            LoadingResourcesAlarm.showInitial(fullError.substring(1));
        }

    }


    private static void openItems() {

        new Thread(() -> {
            for (File itemFile : new File(FileManager.getGameDir(GameVersion.VERSION_WOG2) + "\\res\\items").listFiles())
                if (itemFile.getName().endsWith(".wog2"))
                    ItemHelper.getItemActualName(itemFile.getName().substring(0, itemFile.getName().length() - 5));
        }).start();



    }


    private static void openResources(GameVersion version) {

        ArrayList<EditorObject> toAddTo = switch (version) {
            case VERSION_WOG1_OLD -> oldResources;
            case VERSION_WOG1_NEW -> newResources;
            case VERSION_WOG2 -> sequelResources;
        };

        ArrayList<EditorObject> resources;
        try {
            resources = FileManager.openResources(version);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            ErrorAlarm.show(e);
            return;
        }

        SetDefaults currentSetDefaults = null;

        for (EditorObject EditorObject : resources) {

            if (EditorObject instanceof SetDefaults setDefaults) {
                currentSetDefaults = setDefaults;
            }

            else if (EditorObject instanceof ResrcImage resrcImage) {
                resrcImage.setSetDefaults(currentSetDefaults);
                toAddTo.add(resrcImage);
            } else if (EditorObject instanceof Sound sound) {
                sound.setSetDefaults(currentSetDefaults);
                toAddTo.add(sound);
            } else if (EditorObject instanceof Font font) {
                font.setSetDefaults(currentSetDefaults);
                toAddTo.add(font);
            }

        }

    }


    private static void openParticles(GameVersion version) {
        ArrayList<EditorObject> particles2;
        try {
            particles2 = FileManager.openParticles(version);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            ErrorAlarm.show(e);
            return;
        }

        ParticleManager.getParticles().addAll(particles2);
        for (EditorObject particle : particles2) {
            try {
                if (particle instanceof _Particle _particle) {
                    _particle.update(version);
                } else {
                    particle.update();
                }
            } catch (Exception e) {
                allFailedResources.add("Particle: " + particle.getParent().getAttribute("name").stringValue() + " (version " + version + ")");
            }
        }

    }


    private static void openAnimations(GameVersion version) {

        File animationsDirectory = new File(FileManager.getGameDir(version) + "\\res\\anim");
        File[] animationsArray = animationsDirectory.listFiles();
        if (animationsArray == null) return;

        for (File second : animationsArray) {
            if (version == GameVersion.VERSION_WOG1_NEW || !second.getName().substring(second.getName().lastIndexOf(".")).equals(".binltl64")) {
                try (FileInputStream test2 = new FileInputStream(second)) {
                    byte[] allBytes = test2.readAllBytes();
                    if (version == GameVersion.VERSION_WOG1_OLD) {
                        AnimationManager.getAnimations().add(AnimationReader.readBinltl(allBytes, second.getName()));
                    } else if (version == GameVersion.VERSION_WOG1_NEW) {
                        AnimationManager.getAnimations().add(AnimationReader.readBinuni(allBytes, second.getName()));
                    }
                } catch (Exception e) {
                    allFailedResources.add("Animation: " + second.getName() + " (version " + version + ")");
                }
            }
        }

    }


    private static void openText(GameVersion version) {

        ArrayList<EditorObject> toAddTo = version == GameVersion.VERSION_WOG1_OLD ? oldResources : newResources;

        ArrayList<EditorObject> textList;
        try {
            textList = FileManager.openText(version);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            ErrorAlarm.show(e);
            return;
        }

        for (EditorObject text : textList) {
            if (text instanceof TextString) {
                toAddTo.add(text);
            }
        }

    }


    private static void openMaterials(GameVersion version) {

        ArrayList<EditorObject> toAddTo = version == GameVersion.VERSION_WOG1_OLD ? oldResources : newResources;

        ArrayList<EditorObject> materialList;
        try {
            materialList = FileManager.openMaterials(version);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            ErrorAlarm.show(e);
            return;
        }

        for (EditorObject material : materialList) {
            if (material instanceof Material) {
                toAddTo.add(material);
            }
        }

    }

}
