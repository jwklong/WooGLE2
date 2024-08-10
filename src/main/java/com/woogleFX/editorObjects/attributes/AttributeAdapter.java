package com.woogleFX.editorObjects.attributes;

import com.woogleFX.editorObjects.EditorObject;

public abstract class AttributeAdapter {

    public final String name;
    public AttributeAdapter(String name) {
        this.name = name;
    }


    public abstract EditorAttribute getValue();

    public abstract void setValue(String value);


    public static AttributeAdapter pointAttributeAdapter(EditorObject object, String realName, String fakeName) {

        return new AttributeAdapter(fakeName) {

            @Override
            public EditorAttribute getValue() {
                return object.getAttribute2(realName);
            }

            @Override
            public void setValue(String value) {
                EditorObject pos = object.getChildren(realName).get(0);
                String x = value.substring(0, value.indexOf(","));
                String y = value.substring(value.indexOf(",") + 1);
                pos.setAttribute("x", x);
                pos.setAttribute("y", y);
            }

        };

    }

}
