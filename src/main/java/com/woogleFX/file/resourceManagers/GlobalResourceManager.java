package com.woogleFX.file.resourceManagers;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.engine.gui.alarms.ErrorAlarm;
import com.woogleFX.engine.gui.alarms.LoadingResourcesAlarm;
import com.woogleFX.file.FileManager;
import com.woogleFX.gameData.animation.AnimBinReader;
import com.woogleFX.gameData.animation.AnimationManager;
import com.woogleFX.gameData.animation.AnimationReader;
import com.woogleFX.gameData.ball.AtlasManager;
import com.woogleFX.gameData.ball.DefaultXmlOpener;
import com.woogleFX.gameData.ball.DefaultXmlOpener.Mode;
import com.woogleFX.gameData.level.GameVersion;
import com.woogleFX.gameData.particle.ParticleManager;
import com.worldOfGoo.resrc.*;
import com.worldOfGoo.particle._Particle;
import com.worldOfGoo.text.TextString;
import com.worldOfGoo2.util.BallInstanceHelper;
import com.worldOfGoo2.util.ItemHelper;
import com.worldOfGoo2.util.TerrainHelper;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.*;

/** Stores global resources (those specified in properties/resources.xml). */
public class GlobalResourceManager {

    private static final Map<String, EditorObject> oldResources = new HashMap<>();
    public static Map<String, EditorObject> getOldResources() {
        return oldResources;
    }


    private static final Map<String, EditorObject> newResources = new HashMap<>();
    public static Map<String, EditorObject> getNewResources() {
        return newResources;
    }


    private static final Map<String, EditorObject> sequelResources = new HashMap<>();
    public static Map<String, EditorObject> getSequelResources() {
        return sequelResources;
    }

    private static final Map<String, Sound> sequelMusic = new HashMap<>();
    public static Map<String, Sound> getSequelMusic() {
        return sequelMusic;
    }

    private static final Map<String, EditorObject> sequelAmbience = new HashMap<>();
    public static Map<String, EditorObject> getSequelAmbience() {
        return sequelAmbience;
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
            openBallTable();
            new Thread(() -> {
                try {
                    ResourceManager.findTerrainTypes(null, GameVersion.VERSION_WOG2);
                    TerrainHelper.buildImageMap();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }).start();
            //openParticles(GameVersion.VERSION_WOG2);
            openAnimations(GameVersion.VERSION_WOG2);
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
            for (File itemFile : new File(FileManager.getGameDir(GameVersion.VERSION_WOG2) + "/res/items").listFiles())
                if (itemFile.getName().endsWith(".wog2"))
                    ItemHelper.getItemActualName(itemFile.getName().substring(0, itemFile.getName().length() - 5));
        }).start();

    }
    
    private static void openBallTable() {

        new Thread(() -> {  
            File ballTableFile = new File(FileManager.getGameDir(GameVersion.VERSION_WOG2) + "/fisty/ballTable.ini");
            if (ballTableFile.exists()) {
                try {
                    String ballTable = Files.readString(ballTableFile.toPath());
                    parseBallTable(ballTable);
                } catch (IOException e) {
                    // TODO: do something (?)
                } catch (ParseException e) {
                    
                }
            }
        }).start();

    }
    
    private static void parseBallTable(String ballTable) throws ParseException {
        String[] lines = ballTable.split("\n");
        Map<Integer, String> typeEnumToTypeMap = new HashMap<>();
        
        int lineNumber = -1;
        for (String line : lines) {
            lineNumber++;
            
            int lineCommentStart = line.indexOf(';');
            if (lineCommentStart != -1)
                line = line.substring(0, lineCommentStart);
            
            if (line.isBlank())
                continue;
            
            int equalsSignPos = line.indexOf('=');
            
            if (equalsSignPos == -1)
                throw new ParseException(null, lineNumber);
            
            String keyStr = line.substring(0, equalsSignPos).trim();
            String value = line.substring(equalsSignPos + 1).trim();
            
            try {
                int key = Integer.parseInt(keyStr);
                typeEnumToTypeMap.put(key, value);
            } catch (NumberFormatException e) {
                throw new ParseException(null, lineNumber);
            }
        }
        
        BallInstanceHelper.setTypeEnumMaps(typeEnumToTypeMap);
    }


    private static void openResources(GameVersion version) {

        Map<String, EditorObject> toAddTo = switch (version) {
            case VERSION_WOG1_OLD -> oldResources;
            case VERSION_WOG1_NEW -> newResources;
            case VERSION_WOG2 -> sequelResources;
        };

        ArrayList<EditorObject> resources;
        try {
            resources = FileManager.openResources(version);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            ErrorAlarm.show(e);
            return;
        }

        SetDefaults currentSetDefaults = null;

        for (EditorObject EditorObject : resources) {

            if (EditorObject instanceof SetDefaults setDefaults) {
                currentSetDefaults = setDefaults;
            }
            else if (EditorObject instanceof FlashAnim flashAnim) {
                //flashAnim.setSetDefaults(currentSetDefaults);
                toAddTo.put(flashAnim.getAdjustedID(), flashAnim);
            } else if (EditorObject instanceof ResrcImage resrcImage) {
                resrcImage.setSetDefaults(currentSetDefaults);
                toAddTo.put(resrcImage.getAdjustedID(), resrcImage);
            } else if (EditorObject instanceof Sound sound) {
                sound.setSetDefaults(currentSetDefaults);
                toAddTo.put(sound.getAdjustedID(), sound);
            } else if (EditorObject instanceof Font font) {
                font.setSetDefaults(currentSetDefaults);
                toAddTo.put(font.getAdjustedID(), font);
            }

        }
        
        if (version == GameVersion.VERSION_WOG2) {
            try {

                Set<String> globalResourceDirs = new HashSet<>();
                BaseGameResources.loadFileIntoSet(FileManager.getEditorLocation() + "/BaseGameResources/2/GlobalResourceDirs.txt", globalResourceDirs);

                for (String dir : globalResourceDirs) {
                    currentSetDefaults = null;
                    ArrayList<EditorObject> ambience = FileManager.openWog2ResourceFile("/" + dir);

                    for (EditorObject editorObject : ambience) {
                        if (editorObject instanceof SetDefaults setDefaults) {
                            currentSetDefaults = setDefaults;
                        } else if (editorObject instanceof FlashAnim flashAnim) {
                            flashAnim.setSetDefaults(currentSetDefaults);
                            sequelResources.put(flashAnim.getAdjustedID(), editorObject);
                        } else if (editorObject instanceof ResrcImage resrcImage) {
                            resrcImage.setSetDefaults(currentSetDefaults);
                            sequelResources.put(resrcImage.getAdjustedID(), editorObject);
                        } else if (editorObject instanceof Sound sound) {
                            sound.setSetDefaults(currentSetDefaults);
                            sequelResources.put(sound.getAdjustedID(), editorObject);
                        } else if (editorObject instanceof Font font) {
                            font.setSetDefaults(currentSetDefaults);
                            sequelResources.put(font.getAdjustedID(), editorObject);
                        }
                    }
                }

            } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
                ErrorAlarm.show(e);
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

        File animationsDirectory = new File(FileManager.getGameDir(version) + "/res/anim");
        File[] animationsArray = animationsDirectory.listFiles();
        if (animationsArray == null) return;

        for (File second : animationsArray) {

            if (version == GameVersion.VERSION_WOG2) {

                if (second.isDirectory()) for (File fourth : second.listFiles()) {
                    if (fourth.isDirectory()) {
                        for (File third : fourth.listFiles())
                            if (third.getName().endsWith(".anim.bin")) {

                                openAnimationFile(third, GameVersion.VERSION_WOG2);

                            }
                    } else if (fourth.getName().endsWith(".anim.bin") && !(fourth.getName().contains("Scene"))) {

                        openAnimationFile(fourth, GameVersion.VERSION_WOG2);

                    }

                }

            } else if (version == GameVersion.VERSION_WOG1_NEW || !second.getName().substring(second.getName().lastIndexOf(".")).equals(".binltl64")) {
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

        if (version == GameVersion.VERSION_WOG2) {

            for (File file : new File(FileManager.getGameDir(GameVersion.VERSION_WOG2) + "/res/ui/buttons").listFiles()) {
                File anim = new File(file.getPath() + "/" + file.getName() + ".anim.bin");
                if (anim.exists()) openAnimationFile(anim, GameVersion.VERSION_WOG2);
            }

        }


    }


    private static void openAnimationFile(File third, GameVersion version) {
        String text = "";
        try {
            AnimationManager.getBinAnimations().add(AnimBinReader.readSimpleBinAnimation(third.toPath(), third.getName()));

            ArrayList<EditorObject> resources = new ArrayList<>();

            DefaultXmlOpener defaultHandler = new DefaultXmlOpener(null, resources, GameVersion.VERSION_WOG1_NEW);

            File ballFileR = new File(third.getParent() + "/manifest.resrc");
            defaultHandler.setMode(Mode.RESOURCE);

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            text = Files.readString(ballFileR.toPath());
            if (text.indexOf("?") == 2) text = text.substring(1);
            if (!text.contains("<Resources id=\"")) {
                text = text.substring(0, text.indexOf("<Resources id=") + 14)
                        + '"' + text.substring(text.indexOf("<Resources id=") + 14, text.indexOf(">", text.indexOf("<Resources id=") + 14)) + '"'
                        + text.substring( text.indexOf(">", text.indexOf("<Resources id=") + 14));
            }
            saxParser.parse(new InputSource(new ByteArrayInputStream(text.getBytes())), defaultHandler);

            for (EditorObject EditorObject : resources) {
                if (EditorObject instanceof FlashAnim flashAnim) {
                    sequelResources.put(flashAnim.getAdjustedID(), flashAnim);
                } else if (EditorObject instanceof ResrcImage resrcImage) {
                    sequelResources.put(resrcImage.getAdjustedID(), resrcImage);
                } else if (EditorObject instanceof Sound sound) {
                    sequelResources.put(sound.getAdjustedID(), sound);
                } else if (EditorObject instanceof Font font) {
                    sequelResources.put(font.getAdjustedID(), font);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            allFailedResources.add("Animation: " + third.getName() + " (version " + version + ")");
        }
    }


    private static void openText(GameVersion version) {

        Map<String, EditorObject> toAddTo = version == GameVersion.VERSION_WOG1_OLD ? oldResources : newResources;

        ArrayList<EditorObject> textList;
        try {
            textList = FileManager.openText(version);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            ErrorAlarm.show(e);
            return;
        }

        for (EditorObject text : textList) {
            if (text instanceof TextString textString) {
                toAddTo.put("", textString);
            }
        }

    }


    private static void openMaterials(GameVersion version) {

        Map<String, EditorObject> toAddTo = version == GameVersion.VERSION_WOG1_OLD ? oldResources : newResources;

        ArrayList<EditorObject> materialList;
        try {
            materialList = FileManager.openMaterials(version);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            ErrorAlarm.show(e);
            return;
        }

        for (EditorObject editorObject : materialList) {
            if (editorObject instanceof Material material) {
                toAddTo.put("", material);
            }
        }

    }

}
