package com.woogleFX.editorObjects;

import java.io.InputStream;
import java.util.*;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.woogleFX.editorObjects.attributes.*;
import com.woogleFX.editorObjects.objectComponents.ObjectComponent;
import com.woogleFX.engine.undoHandling.userActions.ObjectDestructionAction;
import com.woogleFX.gameData.level.GameVersion;

import com.worldOfGoo2.level._2_Level_BallInstance;
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
    private String type;
    public final String getType() {
        return type;
    }
    public final void setType(String type) {
        this.type = type;
    }

    /** The version of this object. */
    private GameVersion version;
    public final GameVersion getVersion() {
        return version;
    }
    public final void setVersion(GameVersion version) {
        this.version = version;
    }


    private static final Map<Class<? extends EditorObject>, AttributeManifest> attributeManifestMap = new HashMap<>();


    public EditorObject(EditorObject parent, String type, GameVersion version) {
        this.parent = parent;
        this.type = type;
        this.version = version;

        try {
            AttributeManifest attributeManifest;
            if (attributeManifestMap.containsKey(getClass())) {
                attributeManifest = attributeManifestMap.get(getClass());
            } else {
                String attributeManifestPath = "/" + getClass().getName().replace('.', '/') + ".xml";
                InputStream inputStream = getClass().getResourceAsStream(attributeManifestPath);
                if (inputStream == null) return;
                attributeManifest = new XmlMapper().readValue(inputStream, AttributeManifest.class);
                if (attributeManifest == null) return;
                attributeManifestMap.put(getClass(), attributeManifest);
            }
            ArrayList<EditorAttribute> attributes1 = new ArrayList<>();
            for (EditorAttribute editorAttribute : attributeManifest.getAttributes()) {
                EditorAttribute realAttribute = new EditorAttribute();
                realAttribute.setDefaultValue(editorAttribute.getDefaultValue());
                realAttribute.setObject(this);
                realAttribute.setChildAlias(editorAttribute.getChildAlias());
                realAttribute.setValue(editorAttribute.stringValue());
                realAttribute.setName(editorAttribute.getName());
                realAttribute.setType(editorAttribute.getType());
                realAttribute.setRequired(editorAttribute.getRequired());
                attributes1.add(realAttribute);
            }
            long time = System.nanoTime();
            setAttributes(attributes1.toArray(new EditorAttribute[0]));
            if (attributeManifest.getMetaAttributes() == null) return;
            for (MetaEditorAttribute metaEditorAttribute : attributeManifest.getMetaAttributes()) {
                MetaEditorAttribute mine = new MetaEditorAttribute();
                mine.setName(metaEditorAttribute.getName());
                mine.setOpenByDefault(metaEditorAttribute.getOpenByDefault());
                mine.setChildren(new ArrayList<>());
                for (MetaEditorAttribute child : metaEditorAttribute.getChildren()) {
                    MetaEditorAttribute mine2 = new MetaEditorAttribute();
                    mine2.setName(child.getName());
                    mine2.setOpenByDefault(child.getOpenByDefault());
                    mine2.setChildren(new ArrayList<>());
                    mine.getChildren().add(mine2);
                    for (MetaEditorAttribute child2 : child.getChildren()) {
                        MetaEditorAttribute mine3 = new MetaEditorAttribute();
                        mine3.setName(child2.getName());
                        mine3.setOpenByDefault(child2.getOpenByDefault());
                        mine3.setChildren(new ArrayList<>());
                        mine2.getChildren().add(mine3);
                    }
                }
                metaAttributes.add(mine);
                // System.out.println(System.nanoTime() - time);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (this instanceof _2_Level_BallInstance)
                e.printStackTrace();
        }

    }

    public EditorObject(EditorObject parent) {
        this.parent = parent;

        try {
            AttributeManifest attributeManifest;
            if (attributeManifestMap.containsKey(getClass())) {
                attributeManifest = attributeManifestMap.get(getClass());
            } else {
                String attributeManifestPath = "/" + getClass().getName().replace('.', '/') + ".xml";
                InputStream inputStream = getClass().getResourceAsStream(attributeManifestPath);
                attributeManifest = new XmlMapper().readValue(inputStream, AttributeManifest.class);
                attributeManifestMap.put(getClass(), attributeManifest);
            }
            ArrayList<EditorAttribute> attributes1 = new ArrayList<>();
            for (EditorAttribute editorAttribute : attributeManifest.getAttributes()) {
                EditorAttribute realAttribute = new EditorAttribute();
                realAttribute.setDefaultValue(editorAttribute.getDefaultValue());
                realAttribute.setObject(this);
                realAttribute.setChildAlias(editorAttribute.getChildAlias());
                realAttribute.setValue(editorAttribute.stringValue());
                realAttribute.setName(editorAttribute.getName());
                realAttribute.setType(editorAttribute.getType());
                realAttribute.setRequired(editorAttribute.getRequired());
                attributes1.add(realAttribute);
            }
            setAttributes(attributes1.toArray(new EditorAttribute[0]));
            for (MetaEditorAttribute metaEditorAttribute : attributeManifest.getMetaAttributes()) {
                MetaEditorAttribute mine = new MetaEditorAttribute();
                mine.setName(metaEditorAttribute.getName());
                mine.setOpenByDefault(metaEditorAttribute.getOpenByDefault());
                mine.setChildren(new ArrayList<>());
                for (MetaEditorAttribute child : metaEditorAttribute.getChildren()) {
                    MetaEditorAttribute mine2 = new MetaEditorAttribute();
                    mine2.setName(child.getName());
                    mine2.setOpenByDefault(child.getOpenByDefault());
                    mine2.setChildren(new ArrayList<>());
                    mine.getChildren().add(mine2);
                    for (MetaEditorAttribute child2 : child.getChildren()) {
                        MetaEditorAttribute mine3 = new MetaEditorAttribute();
                        mine3.setName(child2.getName());
                        mine3.setOpenByDefault(child2.getOpenByDefault());
                        mine3.setChildren(new ArrayList<>());
                        mine2.getChildren().add(mine3);
                    }
                }
                metaAttributes.add(mine);
            }
        } catch (Exception e) {
            if (this instanceof _2_Level_BallInstance)
                e.printStackTrace();
        }
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
    
    public final void addObjectComponents(List<ObjectComponent> c) {
        objectComponents.addAll(c);
    }
    
    public final void removeObjectComponent(ObjectComponent c) {
        objectComponents.remove(c);
    }
    
    public final void clearObjectComponents() {
        objectComponents.clear();
    }
    
    public final boolean containsObjectComponent(ObjectComponent c) {
        return objectComponents.contains(c);
    }


    public String getName() {
        return "";
    }


    public void update() {
    }


    public Class<? extends EditorObject>[] getPossibleChildren() {
        return (Class<? extends EditorObject>[]) new Class[0];
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
        AttributeAdapter attributeAdapter2 = attributeAdapters.get(name);
        if (attributeAdapter2 != null) return attributeAdapter2.getValue();
        AttributeAdapter[] array = attributeAdapters.values().toArray(new AttributeAdapter[0]);
        for (AttributeAdapter attributeAdapter : array) if (attributeAdapter.name.equals(name))
        {
            return attributeAdapter.getValue();
        }
        for (EditorAttribute attribute : attributes) if (attribute.getName().equals(name)) return attribute;
        logger.error("Accessed invalid attribute " + name + " (for " + getType() + ")");
        Thread.dumpStack();
        return EditorAttribute.NULL;
    }
    public final EditorAttribute getAttribute2(String name) {
        for (EditorAttribute attribute : attributes) if (attribute.getName().equals(name)) return attribute;
        logger.error("Accessed invalid attribute " + name + " (for " + getType() + ")");
        Thread.dumpStack();
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
    public final void setAttributes(EditorAttribute[] attributes) {
        this.attributes = attributes;
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


    private final Map<String, AttributeAdapter> attributeAdapters = new HashMap<>();
    public final Map<String, AttributeAdapter> getAttributeAdapters() {
        return attributeAdapters;
    }
    public final void addAttributeAdapter(String name, AttributeAdapter attributeAdapter) {
        attributeAdapters.put(name, attributeAdapter);
    }

    public final EditorObject getChild(String attributeName) {
        for (EditorObject child : children) {
            if (child.getTypeID().equals(attributeName))
                return child;
        }
        
        return null;
    }
    
    public synchronized final ArrayList<EditorObject> getChildren(String attributeName) {
        ArrayList<EditorObject> children2 = new ArrayList<>();
        for (EditorObject child : children.toArray(new EditorObject[0])) if (child != null && child.getTypeID().equals(attributeName)) children2.add(child);
        return children2;
    }


    private String typeID = "";
    public final String getTypeID() {
        return typeID;
    }
    public final void setTypeID(String typeID) {
        this.typeID = typeID;
    }


    public void onLoaded() {

    }
    
    public void postInit() {}

    public void onCreate() {

    }
    
    /**
     * Gets called when the object is deleted.
     * Don't delete other objects in this, instead, create ObjectDestructionActions
     * and the ObjectManager will delete them automatically.
     * (This is done to preserve the object's original positions in the scene tree)
     */
    public List<ObjectDestructionAction> onDelete() {
        return null;
    }

}
