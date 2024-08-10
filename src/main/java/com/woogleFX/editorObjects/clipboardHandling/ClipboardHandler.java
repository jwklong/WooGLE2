package com.woogleFX.editorObjects.clipboardHandling;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.EditorAttribute;
import com.woogleFX.editorObjects.objectCreators.ObjectCreator;
import com.woogleFX.engine.LevelManager;
import com.woogleFX.file.fileExport.GOOWriter;
import com.woogleFX.file.fileImport.ObjectGOOParser;
import com.woogleFX.gameData.level.GameVersion;
import com.woogleFX.gameData.level.WOG1Level;
import com.woogleFX.gameData.level.WOG2Level;
import com.woogleFX.gameData.level._Level;

import java.util.ArrayList;

public class ClipboardHandler {

    public static EditorObject[] importFromClipboardString(String clipboard) {

        //WOGEditor:circle<id=wheel;x=2;y=1024.9834;radius=120;material=machine;tag=mostlydeadly>

        EditorObject object = null;

        StringBuilder currentWord = new StringBuilder();
        String attributeName = "";
        boolean settingAttribute = false;

        EditorObject selected;
        EditorObject[] selectedList = LevelManager.getLevel().getSelected();
        if (selectedList.length == 1) selected = selectedList[0];
        else selected = null;

        ArrayList<EditorObject> selectionBuilder = new ArrayList<>();

        if (LevelManager.getLevel() instanceof WOG1Level) {

            for (int i = 0; i < clipboard.length(); i++) {
                char part = clipboard.charAt(i);

                if (settingAttribute) {
                    if (part == '=') {
                        attributeName = currentWord.toString();
                        currentWord = new StringBuilder();
                    } else if (part == ';') {
                        object.setAttribute(attributeName, currentWord.toString());
                        currentWord = new StringBuilder();
                    } else if (part == '>') {
                        settingAttribute = false;
                        object.setAttribute(attributeName, currentWord.toString());
                        selectionBuilder.add(object);
                        currentWord = new StringBuilder();
                    } else {
                        currentWord.append(part);
                    }
                } else {
                    if (part == ':') {
                        if (!currentWord.toString().equals("WOGEditor")) {
                            return null;
                        }
                        currentWord = new StringBuilder();
                    } else if (part == '<') {

                        _Level level = LevelManager.getLevel();

                        boolean okayToBeChild = selected != null && selected.getParent() != null;

                        if (okayToBeChild) {
                            okayToBeChild = false;
                            for (String possibleChild : selected.getParent().getPossibleChildren()) {
                                if (possibleChild.contentEquals(currentWord)) {
                                    okayToBeChild = true;
                                    break;
                                }
                            }
                        }

                        EditorObject parent = okayToBeChild ? selected.getParent() : null;
                        object = ObjectCreator.create(currentWord.toString(), parent, level.getVersion());
                        currentWord = new StringBuilder();
                        settingAttribute = true;
                    } else {
                        currentWord.append(part);
                    }
                }
            }

        } else if (LevelManager.getLevel() instanceof WOG2Level) {

            String type = clipboard.substring(10, clipboard.indexOf(";"));
            String type2 = clipboard.substring(clipboard.indexOf(";") + 1, clipboard.indexOf("<"));
            String content = clipboard.substring(clipboard.indexOf("<") + 1);
            selectionBuilder.add(ObjectGOOParser.read(type, content));
            selectionBuilder.get(0).setTypeID(type2);
            return selectionBuilder.toArray(new EditorObject[0]);

        }

        return selectionBuilder.toArray(new EditorObject[0]);

    }

    public static String exportToClipBoardString(EditorObject[] selectedList) {

        if (selectedList[0].getVersion() == GameVersion.VERSION_WOG2) {

            StringBuilder export = new StringBuilder();
            GOOWriter.recursiveGOOExport(export, selectedList[0], 0);
            return "WOGEditor:" + selectedList[0].getParent().getAttributeChildAlias().get(selectedList[0].getTypeID()) + ";" + selectedList[0].getTypeID() + "<" + export;

        } else {

            StringBuilder clipboard = new StringBuilder("WOGEditor:");

            for (EditorObject object : selectedList) {

                clipboard.append(object.getParent().getAttributeChildAlias().get(object.getTypeID()));

                clipboard.append("<");

                for (int i = 0; i < object.getAttributes().length; i++) {
                    EditorAttribute attribute = object.getAttributes()[i];
                    if (attribute.stringValue() != null && !attribute.stringValue().equals(attribute.getDefaultValue()) && !attribute.stringValue().isEmpty()) {
                        clipboard.append(attribute.getName()).append("=").append(attribute.stringValue());
                        clipboard.append(";");
                    }
                }

                clipboard.deleteCharAt(clipboard.length() - 1);

                clipboard.append(">");

            }

            return clipboard.toString();

        }

    }


}
