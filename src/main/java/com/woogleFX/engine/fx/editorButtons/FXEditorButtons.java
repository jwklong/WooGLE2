package com.woogleFX.engine.fx.editorButtons;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.objectCreators.ObjectCreator;
import com.woogleFX.engine.AssetManager;
import com.woogleFX.engine.fx.FXContainers;
import com.woogleFX.engine.fx.menu.FXMenu;
import com.woogleFX.gameData.ball.*;
import com.woogleFX.file.FileManager;
import com.woogleFX.file.resourceManagers.ResourceManager;
import com.woogleFX.editorObjects.objectCreators.ObjectAdder;
import com.woogleFX.engine.gui.PaletteReconfigurator;
import com.woogleFX.gameData.items.ItemManager;
import com.woogleFX.gameData.level.*;
import com.worldOfGoo.ball.Part;
import com.worldOfGoo2.ball._2_Ball_Image;
import com.worldOfGoo2.ball._2_Ball_Part;
import com.worldOfGoo2.items._2_Item;
import com.worldOfGoo2.level._2_Level_BallInstance;
import com.worldOfGoo2.level._2_Level_Item;
import com.worldOfGoo2.util.BallInstanceHelper;
import com.worldOfGoo2.util.ItemHelper;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class FXEditorButtons {

    public static abstract class EditorButton extends Button {

        public abstract void updateDisabled();

        public void setIcon(String pathString) {
            setGraphic(new ImageView(FileManager.getIcon(pathString)));
        }

    }


    public static abstract class EditorMenuButton extends MenuButton {

        public EditorMenuButton() {
            super();
            setStyle("-fx-background-insets: 0,0,0; -fx-padding: -4 -8 -4 -8;");
        }

        public abstract void updateDisabled();

        public void setIcon(String pathString) {
            setGraphic(new ImageView(FileManager.getIcon(pathString)));
        }

    }


    public static class DelayedTooltip extends Tooltip {
        // Tooltip with a shorter delay than the default
        public DelayedTooltip(String text) {
            super(text);
            setShowDelay(javafx.util.Duration.millis(150));
        }
    }


    private static ToolBar functionsToolbar;


    private static ToolBar oldGooballsToolbar;
    public static ToolBar getOldGooballsToolbar() {
        return oldGooballsToolbar;
    }


    private static ToolBar newGooballsToolbar;
    public static ToolBar getNewGooballsToolbar() {
        return newGooballsToolbar;
    }


    private static ToolBar sequelGooballsToolbar;
    public static ToolBar getSequelGooballsToolbar() {
        return sequelGooballsToolbar;
    }


    private static ToolBar nullGooballsToolbar;
    public static ToolBar getNullGooballsToolbar() {
        return nullGooballsToolbar;
    }


    private static ToolBar addObjectsToolbar;


    private static ToolBar newAddObjectsToolbar;


    public static Button createTemplateForBall(int size, _Ball ball) {

        double minX = 0;
        double minY = 0;
        double maxX = 0;
        double maxY = 0;

        for (EditorObject EditorObject : ball.getObjects()) {
            String state = "standing";

            if (EditorObject instanceof Part part) {

                boolean ok = false;

                if (part.getAttribute("state").stringValue().isEmpty()) {
                    ok = true;
                } else {
                    String word = part.getAttribute("state").stringValue();
                    while (word.contains(",")) {
                        if (word.substring(0, word.indexOf(",")).equals(state)) {
                            ok = true;
                            break;
                        }
                        word = word.substring(word.indexOf(",") + 1);
                    }
                    if (word.equals(state)) {
                        ok = true;
                    }
                }
                if (!ok) continue;

                double lowX;
                double highX;
                double lowY;
                double highY;

                String x = part.getAttribute("x").stringValue();
                String y = part.getAttribute("y").stringValue();

                double scale = Double.parseDouble(part.getAttribute("scale").stringValue());

                if (x.contains(",")) {
                    lowX = Double.parseDouble(x.substring(0, x.indexOf(",")));
                    highX = Double.parseDouble(x.substring(x.indexOf(",") + 1));
                } else {
                    lowX = Double.parseDouble(x);
                    highX = lowX;
                }
                if (y.contains(",")) {
                    lowY = Double.parseDouble(y.substring(0, y.indexOf(",")));
                    highY = Double.parseDouble(y.substring(y.indexOf(",") + 1));
                } else {
                    lowY = Double.parseDouble(y);
                    highY = lowY;
                }

                double myX = 0.5 * (highX - lowX) + lowX;
                double myY = 0.5 * (highY - lowY) + lowY;

                String[] imageStrings = part.getAttribute("image").listValue();

                if (imageStrings.length == 0) continue;

                String imageString = imageStrings[0];
                Image img;
                try {
                    img = ResourceManager.getImage(ball.getResources(), imageString, ball.getVersion());
                    if (img == null) continue;
                } catch (FileNotFoundException ignored) {
                    continue;
                }

                BufferedImage image = SwingFXUtils.fromFXImage(img, null);

                double iWidth = image.getWidth() * scale;
                double iHeight = image.getHeight() * scale;

                if (myX - iWidth / 2 < minX) minX = myX - iWidth / 2;
                if (-myY - iHeight / 2 < minY) minY = -myY - iHeight / 2;
                if (myX + iWidth / 2 > maxX) maxX = myX + iWidth / 2;
                if (-myY + iHeight / 2 > maxY) maxY = -myY + iHeight / 2;

            }
        }

        double width = maxX - minX;
        double height = maxY - minY;

        Button idk = new Button();
        if (width < 0 || height < 0) return idk;

        BufferedImage toWriteOn = new BufferedImage((int) width, (int) height, BufferedImage.TYPE_INT_ARGB);
        Graphics writeGraphics = toWriteOn.getGraphics();

        for (EditorObject EditorObject : ball.getObjects()) {

            String state = "standing";

            if (EditorObject instanceof Part part) {

                boolean ok = false;

                if (part.getAttribute("state").stringValue().isEmpty()) {
                    ok = true;
                } else {
                    String word = part.getAttribute("state").stringValue();
                    while (word.contains(",")) {
                        if (word.substring(0, word.indexOf(",")).equals(state)) {
                            ok = true;
                            break;
                        }
                        word = word.substring(word.indexOf(",") + 1);
                    }
                    if (word.equals(state)) {
                        ok = true;
                    }
                }
                if (!ok) continue;

                String[] imageStrings = part.getAttribute("image").listValue();

                if (imageStrings.length == 0) continue;
                String imageString = imageStrings[0];
                Image img;
                try {
                    img = ResourceManager.getImage(ball.getResources(), imageString, ball.getVersion());
                    if (img == null) continue;
                } catch (FileNotFoundException ignored) {
                    continue;
                }

                double scale = part.getAttribute("scale").doubleValue();

                double lowX;
                double highX;
                double lowY;
                double highY;

                String x = part.getAttribute("x").stringValue();
                String y = part.getAttribute("y").stringValue();

                if (x.contains(",")) {
                    lowX = Double.parseDouble(x.substring(0, x.indexOf(",")));
                    highX = Double.parseDouble(x.substring(x.indexOf(",") + 1));
                } else {
                    lowX = Double.parseDouble(x);
                    highX = lowX;
                }
                if (y.contains(",")) {
                    lowY = Double.parseDouble(y.substring(0, y.indexOf(",")));
                    highY = Double.parseDouble(y.substring(y.indexOf(",") + 1));
                } else {
                    lowY = Double.parseDouble(y);
                    highY = lowY;
                }

                double myX = 0.5 * (highX - lowX) + lowX;
                double myY = 0.5 * (highY - lowY) + lowY;

                if (myY == 0) {
                    myY = -0;
                }

                double screenX = myX + toWriteOn.getWidth() / 2.0 - img.getWidth() * scale / 2;
                double screenY = -myY + toWriteOn.getHeight() / 2.0 - img.getHeight() * scale / 2;

                writeGraphics.drawImage(SwingFXUtils.fromFXImage(img, null), (int) screenX, (int) screenY,
                        (int) (img.getWidth() * scale), (int) (img.getHeight() * scale), null);

                String[] pupilImageStrings = part.getAttribute("pupil").listValue();
                if (pupilImageStrings.length == 0) continue;

                String pupilImageString = pupilImageStrings[0];
                Image pupilImage;
                try {
                    pupilImage = ResourceManager.getImage(ball.getResources(), pupilImageString, ball.getVersion());
                    if (pupilImage == null) continue;
                } catch (FileNotFoundException ignored) {
                    continue;
                }

                double screenX2 = myX + toWriteOn.getWidth() / 2.0 - pupilImage.getWidth() * scale / 2;
                double screenY2 = -myY + toWriteOn.getHeight() / 2.0
                        - pupilImage.getHeight() * scale / 2;

                writeGraphics.drawImage(SwingFXUtils.fromFXImage(pupilImage, null), (int) screenX2,
                        (int) screenY2, (int) (pupilImage.getWidth() * scale),
                        (int) (pupilImage.getHeight() * scale), null);

            }

            double scaleFactor = (double) size / Math.max(toWriteOn.getWidth(), toWriteOn.getHeight());

            java.awt.Image tmp = toWriteOn.getScaledInstance((int) (toWriteOn.getWidth() * scaleFactor),
                    (int) (toWriteOn.getHeight() * scaleFactor), java.awt.Image.SCALE_SMOOTH);
            BufferedImage dimg = new BufferedImage((int) (toWriteOn.getWidth() * scaleFactor),
                    (int) (toWriteOn.getHeight() * scaleFactor), BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2d = dimg.createGraphics();
            g2d.drawImage(tmp, 0, 0, null);
            g2d.dispose();

            idk.setGraphic(new ImageView(SwingFXUtils.toFXImage(dimg, null)));
        }

        idk.setPrefSize(size, size);
        idk.setOnAction(e -> {

            String name = ball.getObjects().get(0).getAttribute("name").stringValue();

            EditorObject ballInstance = ObjectCreator.create("BallInstance", ((WOG1Level) AssetManager.getAsset()).getLevelObject(), ball.getVersion());
            assert ballInstance != null;
            ballInstance.setAttribute("type", name);

            ((WOG1Level) AssetManager.getAsset()).getLevel().add(ballInstance);

            ObjectAdder.addAnything(ballInstance);

        });
        return idk;
    }


    public static Button createTemplateFor2Ball(int size, _2Ball ball) {

        ArrayList<_2_Ball_Image> images = new ArrayList<>();
        for (EditorObject editorObject : ball.getObjects()) if (editorObject instanceof _2_Ball_Part && editorObject.getAttribute("name").stringValue().equals(ball.getObjects().get(0).getChildren("bodyPart").get(0).getAttribute("partName").stringValue())) for (EditorObject child : editorObject.getChildren())
            if (child instanceof _2_Ball_Image ball_image) images.add(ball_image);

        double _scaleX = 1;
        double _scaleY = 1;
        Button idk = new Button();
        if (!images.isEmpty()) {

            String imageString = images.get(0).getChildren().get(0).getAttribute("imageId").stringValue();

            BufferedImage image = AtlasManager.atlas.get(imageString);
            if (image == null) {
                try {
                    image = SwingFXUtils.fromFXImage(ResourceManager.getImage(ball.getResources(), imageString, GameVersion.VERSION_WOG2), null);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            if (image == null) return idk;

            int _width = image.getWidth();
            int _height = image.getHeight();

            double width = ball.getObjects().get(0).getAttribute("width").doubleValue();
            double height = ball.getObjects().get(0).getAttribute("height").doubleValue();

            _scaleX = width / _width;
            _scaleY = height / _height;

        }

        Image image = BallInstanceHelper.createBallImageWoG2(null, ball, _scaleX, _scaleY, new Random(0));

        if (image == null) return idk;
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(20);
        idk.setGraphic(imageView);

        idk.setPrefSize(size, size);
        idk.setOnAction(e -> {

            String name = ball.getObjects().get(0).getAttribute("name").stringValue();

            _2_Level_BallInstance ballInstance = (_2_Level_BallInstance)ObjectCreator.create2(
                _2_Level_BallInstance.class, ((WOG2Level) AssetManager.getAsset()).getLevel(), ball.getVersion());
            
            ballInstance.createPosition();
            ballInstance.setAttribute("type", name);
            ballInstance.setTypeID("balls");
            ballInstance.onLoaded();

            ((WOG2Level) AssetManager.getAsset()).getObjects().add(ballInstance);

            ObjectAdder.addAnything(ballInstance);

        });
        return idk;

    }


    public static void addBallsTo() {
        int size = 18;
        int i = 0;
        for (String paletteBall : PaletteManager.getPaletteBalls()) {

            GameVersion version = PaletteManager.getPaletteVersions().get(i);

            if (version == GameVersion.VERSION_WOG1_OLD || version == GameVersion.VERSION_WOG1_NEW) {

                _Ball ball = BallManager.getBall(paletteBall, version);
                if (ball == null) continue;

                Button button = createTemplateForBall(size, ball);
                button.setTooltip(new DelayedTooltip("Add " + ball.getObjects().get(0).getAttribute("name").stringValue()));
                if (ball.getVersion() == GameVersion.VERSION_WOG1_OLD) {
                    oldGooballsToolbar.getItems().add(button);
                } else if (ball.getVersion() == GameVersion.VERSION_WOG1_NEW) {
                    newGooballsToolbar.getItems().add(button);
                }
            } else {
                _2Ball ball = BallManager.get2Ball(paletteBall, version);
                if (ball == null) continue;

                Button button = createTemplateFor2Ball(size, ball);
                button.setTooltip(new DelayedTooltip("Add " + ball.getObjects().get(0).getAttribute("name").stringValue()));
                if (ball.getVersion() == GameVersion.VERSION_WOG1_OLD) {
                    oldGooballsToolbar.getItems().add(button);
                } else if (ball.getVersion() == GameVersion.VERSION_WOG1_NEW) {
                    newGooballsToolbar.getItems().add(button);
                }
                 else {
                    sequelGooballsToolbar.getItems().add(button);
                }
            }
            i++;
        }
    }


    public static void init() {

        VBox vBox = FXContainers.getvBox();

        functionsToolbar = new ToolBar();
        FXEditorButtons_Asset.asset(functionsToolbar);
        functionsToolbar.getItems().add(new Separator());
        FXEditorButtons_Edit.edit(functionsToolbar);
        functionsToolbar.getItems().add(new Separator());
        FXEditorButtons_Resources.resources(functionsToolbar);
        functionsToolbar.getItems().add(new Separator());
        FXEditorButtons_ShowHide.showHide(functionsToolbar);
        for (Node node : functionsToolbar.getItems()) node.setDisable(true);
        vBox.getChildren().add(1, functionsToolbar);

        oldGooballsToolbar = new ToolBar();
        oldGooballsToolbar.setMinHeight(27);
        oldGooballsToolbar.setOnMouseClicked(e -> showPaletteConfigurator(e, oldGooballsToolbar));
        newGooballsToolbar = new ToolBar();
        newGooballsToolbar.setMinHeight(27);
        newGooballsToolbar.setOnMouseClicked(e -> showPaletteConfigurator(e, newGooballsToolbar));
        sequelGooballsToolbar = new ToolBar();
        sequelGooballsToolbar.setMinHeight(27);
        sequelGooballsToolbar.setOnMouseClicked(e -> showPaletteConfigurator(e, sequelGooballsToolbar));
        nullGooballsToolbar = new ToolBar();
        nullGooballsToolbar.setMinHeight(27);
        nullGooballsToolbar.setOnMouseClicked(e -> showPaletteConfigurator(e, nullGooballsToolbar));
        // addBallsTo();
        vBox.getChildren().add(2, nullGooballsToolbar);

        addObjectsToolbar = new ToolBar();
        vBox.getChildren().add(3, addObjectsToolbar);

    }


    public static void updateAllButtons() {
        for (Node node : FXContainers.getvBox().getChildren()) {
            if (node instanceof ToolBar toolBar) for (Node child : toolBar.getItems()) {
                if (child instanceof EditorButton editorButton) {
                    editorButton.updateDisabled();
                }
                if (child instanceof EditorMenuButton editorButton) {
                    editorButton.updateDisabled();
                    for (MenuItem node1 : editorButton.getItems()) {
                        if (node1 instanceof Menu menu) {
                            for (MenuItem node2 : menu.getItems()) {
                                if (node2 instanceof FXMenu.EditorMenuItem editorMenuItem) {
                                    editorMenuItem.updateDisabled();
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    public static void showPaletteConfigurator(MouseEvent mouseEvent, ToolBar toolbar) {
        if (mouseEvent.getButton() == MouseButton.SECONDARY) {
            ContextMenu contextMenu = new ContextMenu();
            MenuItem menuItem = new MenuItem("Configure Palette...");
            menuItem.setOnAction(actionEvent -> new PaletteReconfigurator().start(new Stage()));
            contextMenu.getItems().add(menuItem);
            if (toolbar != null) {
                toolbar.setContextMenu(contextMenu);
            }
        }
    }

}
