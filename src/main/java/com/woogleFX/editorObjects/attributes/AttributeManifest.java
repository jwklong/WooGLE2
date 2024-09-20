package com.woogleFX.editorObjects.attributes;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class AttributeManifest {

    @JacksonXmlProperty(isAttribute = true)
    private String localName;
    @JacksonXmlProperty
    public String getLocalName() {
        return localName;
    }
    @JacksonXmlProperty
    public void setLocalName(String localName) {
        this.localName = localName;
    }

    @JacksonXmlElementWrapper(localName = "attributes")
    @JacksonXmlProperty(localName = "EditorAttribute")
    private EditorAttribute[] attributes;
    @JacksonXmlProperty
    public EditorAttribute[] getAttributes() {
        return attributes;
    }
    @JacksonXmlProperty
    public void setAttributes(EditorAttribute[] attributes) {
        this.attributes = attributes;
    }

    @JacksonXmlElementWrapper(localName = "metaAttributes")
    @JacksonXmlProperty(localName = "MetaEditorAttribute")
    private MetaEditorAttribute[] metaAttributes;
    @JacksonXmlProperty
    public MetaEditorAttribute[] getMetaAttributes() {
        return metaAttributes;
    }
    @JacksonXmlProperty
    public void setMetaAttributes(MetaEditorAttribute[] metaAttributes) {
        this.metaAttributes = metaAttributes;
    }

}
