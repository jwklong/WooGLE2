package com.woogleFX.file.resourceManagers;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.engine.gui.alarms.ErrorAlarm;
import com.woogleFX.file.fileImport.ObjectGOOParser;
import com.woogleFX.gameData.font._Font;
import com.woogleFX.file.FileManager;
import com.woogleFX.gameData.font.FontReader;
import com.woogleFX.gameData.level.GameVersion;
import com.worldOfGoo.resrc.Material;
import com.worldOfGoo.resrc.Font;
import com.worldOfGoo.resrc.ResrcImage;
import com.worldOfGoo.resrc.Sound;
import com.worldOfGoo.text.TextString;
import com.worldOfGoo2.environments._2_Environment;
import com.worldOfGoo2.items._2_Item;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class ResourceManager {

    private static boolean checkSingleResource(EditorObject resource, String id) {

        if (resource instanceof ResrcImage resrcImage) return resrcImage.getAdjustedID().equals(id);
        else if (resource instanceof Sound sound) return sound.getAdjustedID().equals(id);
        else if (resource instanceof Font font) return font.getAdjustedID().equals(id);
        else if (resource instanceof TextString text) return text.getAttribute("id").stringValue().equals(id);
        else if (resource instanceof Material mat) return mat.getAttribute("id").stringValue().equals(id);
        else if (resource instanceof _2_Item item) return item.getAttribute("uuid").stringValue().equals(id);
        else return false;

    }

    /** Attempts to locate a resource with the given ID from the given resources.
     * Looks in the given level's resources first, then checks the global resources.
     * Returns null if there isn't any resource with the given ID. */
    private static EditorObject findResource(ArrayList<EditorObject> resources, String id, GameVersion version) {

        if (resources != null) for (EditorObject resource : resources)
            if (checkSingleResource(resource, id)) return resource;

        ArrayList<EditorObject> globalResources;
        if (version == GameVersion.VERSION_WOG1_OLD) globalResources = GlobalResourceManager.getOldResources();
        else if (version == GameVersion.VERSION_WOG1_NEW) globalResources = GlobalResourceManager.getNewResources();
        else globalResources = GlobalResourceManager.getSequelResources();

        for (EditorObject resource : globalResources.toArray(new EditorObject[0]))
            if (checkSingleResource(resource, id)) return resource;

        return null;

    }


    /** Returns an image corresponding with the given ID. */
    public static Image getImage(ArrayList<EditorObject> resources, String id, GameVersion version) throws FileNotFoundException {
        EditorObject resource = findResource(resources, id, version);
        if (resource instanceof ResrcImage resrcImage) {
            if (resrcImage.getImage() == null) updateResource(resrcImage, version);
            return resrcImage.getImage();
        }
        else throw new FileNotFoundException("Invalid image resource ID: \"" + id + "\" (version " + version + ")");
    }


    /** Returns a font corresponding with the given ID. */
    public static TextString getText(ArrayList<EditorObject> resources, String id, GameVersion version) throws FileNotFoundException {
        EditorObject resource = findResource(resources, id, version);
        if (resource instanceof TextString textString) return textString;
        else throw new FileNotFoundException("Invalid text resource ID: \"" + id + "\" (version " + version + ")");
    }


    /** Returns an item corresponding with the given ID. */
    public static _2_Item getItem(ArrayList<EditorObject> resources, String id, GameVersion version) throws FileNotFoundException {
        EditorObject resource = findResource(resources, id, version);
        if (resource == null) {
            try {
                File itemFile = new File(FileManager.getGameDir(GameVersion.VERSION_WOG2) + "\\res\\items\\" + id + ".wog2");
                ArrayList<EditorObject> items = ObjectGOOParser.read("_2_Item_Collection", Files.readString(itemFile.toPath())).getChildren();
                for (EditorObject item : items) {
                    // System.out.println(item.getAttribute("name").stringValue());
                    // System.out.println(item.getAttribute("uuid").stringValue());
                    item.update();
                    for (EditorObject child : item.getChildren()) {
                        child.update();
                        for (EditorObject child1 : child.getChildren()) {
                            child1.update();
                            for (EditorObject child2 : child1.getChildren()) {
                                child2.update();
                                for (EditorObject child3 : child2.getChildren()) {
                                    child3.update();
                                }
                            }
                        }

                    }
                    resource = item;
                }
                GlobalResourceManager.getSequelResources().addAll(items);
            } catch (IOException e) {
                ErrorAlarm.show(e);
            }
        }
        if (resource instanceof _2_Item item) return item;
        else throw new FileNotFoundException("Invalid item resource ID: \"" + id + "\" (version " + version + ")");
    }


    /** Returns an environment corresponding with the given ID. */
    public static _2_Environment getEnvironment(ArrayList<EditorObject> resources, String id, GameVersion version) throws FileNotFoundException {
        EditorObject resource = findResource(resources, id, version);
        if (resource == null) {
            try {
                File itemFile = new File(FileManager.getGameDir(GameVersion.VERSION_WOG2) + "\\res\\environments\\" + id + ".wog2");
                EditorObject item = ObjectGOOParser.read("_2_Environment", Files.readString(itemFile.toPath()));
                item.update();
                for (EditorObject child : item.getChildren()) {
                    child.update();
                    for (EditorObject child1 : child.getChildren()) {
                        child1.update();
                        for (EditorObject child2 : child1.getChildren()) {
                            child2.update();
                            for (EditorObject child3 : child2.getChildren()) {
                                child3.update();
                            }
                        }
                    }

                }
                resource = item;
                GlobalResourceManager.getSequelResources().add(item);
            } catch (IOException e) {
                ErrorAlarm.show(e);
            }
        }
        if (resource instanceof _2_Environment item) return item;
        else throw new FileNotFoundException("Invalid environment resource ID: \"" + id + "\" (version " + version + ")");
    }


    /** Returns a font corresponding with the given ID. */
    public static _Font getFont(ArrayList<EditorObject> resources, String id, GameVersion version) throws FileNotFoundException {
        EditorObject resource = findResource(resources, id, version);
        if (resource instanceof Font font) {
            if (font.getFont() == null) updateResource(font, version);
            return font.getFont();
        }
        else throw new FileNotFoundException("Invalid text resource ID: \"" + id + "\" (version " + version + ")");
    }


    /** Returns a material corresponding with the given ID. */
    public static Material getMaterial(ArrayList<EditorObject> resources, String id, GameVersion version) throws FileNotFoundException {
        EditorObject resource = findResource(resources, id, version);
        if (resource instanceof Material material) return material;
        else throw new FileNotFoundException("Invalid material resource ID: \"" + id + "\" (version " + version + ")");
    }


    public static boolean updateResource(EditorObject resource, GameVersion version) {
        String dir = FileManager.getGameDir(version);
        if (resource instanceof ResrcImage resrcImage) {
            try {
                resrcImage.setImage(FileManager.openImageFromFilePath(dir + "\\" + resrcImage.getAdjustedPath() + ".png"));
                return true;
            } catch (IOException ignored) {
                return false;
            }
        } else if (resource instanceof Sound sound) {
            return true;
        } else if (resource instanceof TextString textString) {
            //textResource.setText("");
            return true;
        } else if (resource instanceof Font font) {
            font.setFont(FontReader.read(font.getAdjustedPath(), version));
            return true;
        } else return false;
    }

}
