package com.woogleFX.editorObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.woogleFX.editorObjects.attributes.AttributeAdapter;
import com.woogleFX.editorObjects.attributes.EditorAttribute;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.editorObjects.attributes.MetaEditorAttribute;
import com.woogleFX.editorObjects.objectComponents.ObjectComponent;
import com.woogleFX.editorObjects.objectCreators.ObjectCreator;
import com.woogleFX.gameData.level.GameVersion;

import javafx.scene.control.TreeItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EditorObject {

    public static final Logger logger = LoggerFactory.getLogger(EditorObject.class);

    /** The parent of this object.
     * Every object is required to have a parent except for level/scene/resrc/addin/text root objects. */
    private EditorObject parent;
    public final EditorObject getParent() {
        return parent;
    }
    public final void setParent(EditorObject parent, int row) {
        this.parent = parent;
        if (parent != null) {
            parent.getChildren().add(row, this);
            if (parent.attributeExists(typeID) &&
                    (parent.getAttribute(typeID).getType() == InputField._2_CHILD_HIDDEN
                    || parent.getAttribute(typeID).getType() == InputField._2_LIST_CHILD_HIDDEN)) return;
            parent.getTreeItem().getChildren().add(Math.min(row, parent.getTreeItem().getChildren().size()), getTreeItem());
        }
    }
    public final void setParent(EditorObject p) {
        setParent(p, p == null ? 0 : p.getChildren().size());
    }

    /** The children of this object. */
    private final ArrayList<EditorObject> children = new ArrayList<>();
    public final ArrayList<EditorObject> getChildren() {
        return children;
    }

    /** The name that World of Goo assigns to this object.
     * This is the same for every object of the same type. */
    private final String type;
    public final String getType() {
        return type;
    }


    /** The version of this object. */
    private final GameVersion version;
    public GameVersion getVersion() {
        return version;
    }


    public EditorObject(EditorObject parent, String type, GameVersion version) {
        this.parent = parent;
        this.type = type;
        this.version = version;
    }


    private final TreeItem<EditorObject> treeItem = new TreeItem<>(this);
    public final TreeItem<EditorObject> getTreeItem() {
        return treeItem;
    }


    private final ArrayList<ObjectComponent> objectComponents = new ArrayList<>();
    public final ObjectComponent[] getObjectComponents() {
        return objectComponents.toArray(new ObjectComponent[0]);
    }
    public final void addObjectComponent(ObjectComponent c) {
        objectComponents.add(c);
    }
    public final void removeObjectPosition(ObjectComponent c) {
        objectComponents.remove(c);
    }
    public final void clearObjectPositions() {
        objectComponents.clear();
    }
    public final boolean containsObjectPosition(ObjectComponent c) {
        return objectComponents.contains(c);
    }


    public String getName() {
        return "";
    }


    public void update() {
    }


    public String[] getPossibleChildren() {
        return new String[0];
    }


    public String[] getPossibleChildrenTypeIDs() {
        return new String[0];
    }


    /** The EditorAttributes for this object.
     * All of these are added when the object is created. */
    private EditorAttribute[] attributes = new EditorAttribute[0];
    public final EditorAttribute[] getAttributes(){
        return this.attributes;
    }
    public final boolean attributeExists(String name) {
        return Arrays.stream(attributes).anyMatch(e -> e.getName().equals(name));
    }
    public final EditorAttribute getAttribute(String name) {
        if (attributeAdapters.containsKey(name)) return attributeAdapters.get(name).getValue();
        for (AttributeAdapter attributeAdapter : attributeAdapters.values().toArray(new AttributeAdapter[0])) if (attributeAdapter.name.equals(name))
        {
            return attributeAdapter.getValue();
        }
        for (EditorAttribute attribute : attributes) if (attribute.getName().equals(name)) return attribute;
        logger.error("Accessed invalid attribute " + name + " (for " + getType() + ")");
        return EditorAttribute.NULL;
    }
    public final EditorAttribute getAttribute2(String name) {
        for (EditorAttribute attribute : attributes) if (attribute.getName().equals(name)) return attribute;
        logger.error("Accessed invalid attribute " + name + " (for " + getType() + ")");
        return EditorAttribute.NULL;
    }
    public final void setAttribute(String name, Object value) {
        for (AttributeAdapter attributeAdapter : attributeAdapters.values()) if (attributeAdapter.name.equals(name))
        {
            attributeAdapter.setValue(value.toString());
            return;
        }
        getAttribute(name).setValue(String.valueOf(value));
    }
    public final void setAttribute2(String name, Object value) {
        getAttribute2(name).setValue(String.valueOf(value));
    }
    public final EditorAttribute addAttribute(String name, InputField inputFieldType) {
        EditorAttribute[] newAttributes = new EditorAttribute[attributes.length + 1];
        System.arraycopy(attributes, 0, newAttributes, 0, attributes.length);
        EditorAttribute newAttribute = new EditorAttribute(name, inputFieldType, this);
        newAttributes[attributes.length] = newAttribute;
        attributes = newAttributes;
        return newAttribute;
    }

    /** The meta attributes of the object.
     * These control how attributes are displayed to the user. */
    private ArrayList<MetaEditorAttribute> metaAttributes = new ArrayList<>();
    public final ArrayList<MetaEditorAttribute> getMetaAttributes() {
        return metaAttributes;
    }
    public final void setMetaAttributes(ArrayList<MetaEditorAttribute> meta) {
        this.metaAttributes = meta;
    }

    private final Map<String, String> attributeChildAlias = new HashMap<>();
    public Map<String, String> getAttributeChildAlias() {
        return attributeChildAlias;
    }
    public void putAttributeChildAlias(String attributeName, String childType) {
        attributeChildAlias.put(attributeName, childType);
    }

    private final Map<String, AttributeAdapter> attributeAdapters = new HashMap<>();
    public void addAttributeAdapter(String name, AttributeAdapter attributeAdapter) {
        attributeAdapters.put(name, attributeAdapter);
    }

    public ArrayList<EditorObject> getChildren(String attributeName) {
        ArrayList<EditorObject> children2 = new ArrayList<>();
        for (EditorObject child : children) if (child.getTypeID().equals(attributeName)) children2.add(child);
        return children2;
    }


    private String typeID = "";
    public String getTypeID() {
        return typeID;
    }
    public void setTypeID(String typeID) {
        this.typeID = typeID;
        if (parent != null && parent.attributeExists(typeID) &&
                (parent.getAttribute(typeID).getType() == InputField._2_CHILD_HIDDEN
                        || parent.getAttribute(typeID).getType() == InputField._2_LIST_CHILD_HIDDEN)) parent.getTreeItem().getChildren().remove(getTreeItem());
    }


    public void onLoaded() {

    }

}
