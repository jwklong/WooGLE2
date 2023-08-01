package com.WorldOfGoo.Level;

import com.WooGLEFX.Structures.EditorObject;
import com.WooGLEFX.File.FileManager;
import com.WooGLEFX.Structures.InputField;
import com.WooGLEFX.Structures.SimpleStructures.MetaEditorAttribute;

public class Pipe extends EditorObject {
    public Pipe(EditorObject _parent) {
        super(_parent);
        setRealName("pipe");
        addAttribute("type", "", InputField.ANY, false);
        addAttribute("id", "", InputField.ANY, true);
        addAttribute("depth", "0", InputField.ANY, true);
        setMetaAttributes(MetaEditorAttribute.parse("type,id,depth,"));
    }
}

