module com.example.WOGAnniversaryEditor {

  requires javafx.controls;
  requires java.desktop;
  requires javafx.swing;
  requires javafx.graphics;
  requires org.bouncycastle.provider;
  requires org.apache.commons.compress;
  requires java.xml;
  requires org.slf4j;
  requires com.github.luben.zstd_jni;
  requires com.fasterxml.jackson.databind;
  requires com.fasterxml.jackson.dataformat.xml;
  requires org.lwjgl.opengl;
  requires org.lwjgl.glfw;
  requires org.lwjgl.jawt;


  exports com.worldOfGoo.scene;
  exports com.worldOfGoo.level;
  exports com.worldOfGoo.resrc;
  exports com.worldOfGoo.ball;
  exports com.worldOfGoo.particle;
  exports com.worldOfGoo.text;

  exports com.worldOfGoo2.level;
  exports com.worldOfGoo2.ball;
  exports com.worldOfGoo2.util;
  exports com.worldOfGoo2.items;
  exports com.worldOfGoo2.misc;
  exports com.worldOfGoo2.environments;

  exports com.woogleFX.engine.gui;
  exports com.woogleFX.file;
  exports com.woogleFX.editorObjects;
  exports com.woogleFX.engine.undoHandling.userActions;
  exports com.woogleFX.engine;
  exports com.woogleFX.engine.fx;
  exports com.woogleFX.file.fileExport;
  exports com.woogleFX.editorObjects.objectCreators;
  exports com.woogleFX.file.fileImport;
  exports com.woogleFX.file.resourceManagers;
  exports com.woogleFX.editorObjects.objectComponents;
  exports com.woogleFX.gameData.animation;
  exports com.woogleFX.gameData.font;
  exports com.woogleFX.gameData.ball;
  exports com.woogleFX.gameData.level;
  exports com.woogleFX.gameData.particle;
  exports com.woogleFX.editorObjects.attributes;
  exports com.woogleFX.editorObjects.attributes.dataTypes;
  exports com.woogleFX.engine.renderer;
  exports com.woogleFX.file.aesEncryption;
  exports com.woogleFX.editorObjects.clipboardHandling;
  exports com.woogleFX.engine.fx.hierarchy;
    exports com.woogleFX.engine.fx.menu;
    exports com.woogleFX.engine.fx.assetSelectPane;
    exports com.woogleFX.engine.fx.editorButtons;

}
