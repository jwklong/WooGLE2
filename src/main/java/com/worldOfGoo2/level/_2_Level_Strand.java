package com.worldOfGoo2.level;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.AttributeAdapter;
import com.woogleFX.editorObjects.attributes.EditorAttribute;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.editorObjects.attributes.MetaEditorAttribute;
import com.woogleFX.editorObjects.objectComponents.ImageComponent;
import com.woogleFX.editorObjects.objectComponents.RectangleComponent;
import com.woogleFX.engine.AssetManager;
import com.woogleFX.engine.fx.editorButtons.FXEditorButtons;
import com.woogleFX.engine.fx.editorButtons.FXEditorButtons_ShowHide;
import com.woogleFX.engine.renderer.Renderer;
import com.woogleFX.engine.undoHandling.userActions.ObjectDestructionAction;
import com.woogleFX.file.resourceManagers.ResourceManager;
import com.woogleFX.gameData.ball.AtlasManager;
import com.woogleFX.gameData.ball.BallManager;
import com.woogleFX.gameData.ball._2Ball;
import com.woogleFX.gameData.ball._Ball;
import com.woogleFX.gameData.level.GameVersion;
import com.woogleFX.gameData.level.WOG2Level;
import com.worldOfGoo.ball.BallStrand;
import com.worldOfGoo2.util.BallInstanceHelper;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class _2_Level_Strand extends EditorObject {

    private Image strandImage;
    
    private _2_Level_BallInstance goo1 = null;
    private _2_Level_BallInstance goo2 = null;

    public _2_Level_Strand(EditorObject parent) {
        super(parent, "Strand", GameVersion.VERSION_WOG2);

        addAttribute("ball1UID", InputField._2_BALL_UID);
        addAttribute("ball2UID", InputField._2_BALL_UID);
        addAttribute("type", InputField._2_BALL_TYPE).setDefaultValue("1").assertRequired();
        addAttribute("filled", InputField._2_BOOLEAN);

        setMetaAttributes(MetaEditorAttribute.parse("ball1UID,ball2UID,type,filled,"));

        addAttributeAdapter("type", BallInstanceHelper.ballTypeAttributeAdapter(this, "type", "type", null));
        addAttributeAdapter("ball1UID", new AttributeAdapter("ball1UID") {
            private final EditorAttribute attribute = new EditorAttribute("ball1UID", InputField._2_NUMBER, _2_Level_Strand.this);
            
            @Override
            public EditorAttribute getValue() {
                attribute.setValue(getAttribute2("ball1UID").stringValue());
                return attribute;
            }

            @Override
            public void setValue(String value) {
                int newValue = Integer.parseInt(value);
                setAttribute2("ball1UID", newValue);
                
                ArrayList<EditorObject> gooballs = ((WOG2Level) AssetManager.getAsset()).getLevel().getChildren("balls");
                for (EditorObject ballInstance : gooballs) {
                    if (ballInstance.getAttribute("uid").intValue() == newValue) {
                        setGoo1((_2_Level_BallInstance)ballInstance);
                        update();
                        break;
                    }
                }
            }
        });
        
        addAttributeAdapter("ball2UID", new AttributeAdapter("ball2UID") {
            private final EditorAttribute attribute = new EditorAttribute("ball2UID", InputField._2_NUMBER, _2_Level_Strand.this);
            
            @Override
            public EditorAttribute getValue() {
                attribute.setValue(getAttribute2("ball2UID").stringValue());
                return attribute;
            }

            @Override
            public void setValue(String value) {
                int newValue = Integer.parseInt(value);
                setAttribute2("ball2UID", newValue);
                
                ArrayList<EditorObject> gooballs = ((WOG2Level) AssetManager.getAsset()).getLevel().getChildren("balls");
                for (EditorObject ballInstance : gooballs) {
                    if (ballInstance.getAttribute("uid").intValue() == newValue) {
                        setGoo2((_2_Level_BallInstance)ballInstance);
                        update();
                        break;
                    }
                }
            }
        });
    }


    @Override
    public String getName() {
        String gb1 = getAttribute("ball1UID").stringValue();
        String gb2 = getAttribute("ball2UID").stringValue();
        return gb1 + ", " + gb2;
    }


    private boolean setStrand(String type) {

        for (_Ball ball : BallManager.getImportedBalls()) {
            String ballType = ball.getObjects().get(0).getAttribute("name").stringValue();
            if (ballType.equals(type)) {
                for (EditorObject object : ball.getObjects()) if (object instanceof BallStrand strand2) {
                    //this.strand = strand2;
                    return true;
                }
            }
        }

        return false;

    }


    @Override
    public void onLoaded() {

        getAttribute2("type").addChangeListener((observable, oldValue, newValue) -> update());

        update();

    }


    @Override
    public void update() {

        if (AssetManager.getAsset() == null) return;

        if (goo1 != null && getAttribute("type").stringValue().equals("Terrain")) setAttribute("type", "10");

        for (EditorObject editorObject : ((WOG2Level) AssetManager.getAsset()).getLevel().getChildren("balls"))
            if (editorObject instanceof _2_Level_BallInstance ballInstance) {
                String uid = ballInstance.getAttribute("uid").stringValue();
                if (uid.equals(getAttribute("ball1UID").stringValue())) goo1 = ballInstance;
                if (uid.equals(getAttribute("ball2UID").stringValue())) goo2 = ballInstance;
            }

        if (goo1 != null) {
            if (!goo1.containsStrand(this)) {
                goo1.addStrand(this);
                goo1.updateTerrainGroup();
            }
        }
        if (goo2 != null) {
            if (!goo2.containsStrand(this)) {
                goo2.addStrand(this);
                goo2.updateTerrainGroup();
            }
        }
        
        try {
            _2Ball ball = BallManager.get2Ball(getAttribute("type").stringValue(), GameVersion.VERSION_WOG2);
            String imageString = ball.getObjects().get(0).getChildren("strandImageId").get(0).getAttribute("imageId").stringValue();
            try {
                strandImage = ResourceManager.getImage(ball.getResources(), imageString, GameVersion.VERSION_WOG2);
            } catch (FileNotFoundException e) {
                if (AtlasManager.atlas.containsKey(imageString))
                    strandImage = SwingFXUtils.toFXImage(AtlasManager.atlas.get(imageString), null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        addPartAsObjectPosition();

    }
    
    
    @Override
    public List<ObjectDestructionAction> onDelete() {
        if (goo1 != null) {
            goo1.removeStrand(this);
            goo1.updateTerrainGroup();
        }
        
        if (goo2 != null) {
            goo2.removeStrand(this);
            goo2.updateTerrainGroup();
        }
        
        return null;
    }


    private void addPartAsObjectPosition() {

        clearObjectComponents();

        if (goo1 == null || goo2 == null) return;

        //if (strandImage == null) {
        //    update();
        //}

        if (strandImage != null) addObjectComponent(new ImageComponent() {
            public double getX() {
                double x1 = goo1.getPosition().getX();
                double x2 = goo2.getPosition().getX();
                return (x1 + x2) / 2;
            }
            public double getY() {
                double y1 = -goo1.getPosition().getY();
                double y2 = -goo2.getPosition().getY();
                return (y1 + y2) / 2;
            }
            public double getRotation() {

                double x1 = goo1.getPosition().getX();
                double y1 = -goo1.getPosition().getY();

                double x2 = goo2.getPosition().getX();
                double y2 = -goo2.getPosition().getY();

                return Math.PI / 2 + Renderer.angleTo(new Point2D(x1, y1), new Point2D(x2, y2));

            }
            public double getScaleX() {
                if (strandImage.getWidth() == 0) return 0;
                if (true) return 0.3 / strandImage.getWidth();
                // if (goo2.getBall().getObjects().get(0).getAttribute("strandThickness").doubleValue() == 0) return goo1.getBall().getObjects().get(0).getAttribute("strandThickness").doubleValue() / strandImage.getWidth();
                if (goo1.getBall() == null || goo1.getBall().getObjects().get(0).getAttribute("strandThickness").doubleValue() <= 0) return goo2.getBall().getObjects().get(0).getAttribute("strandThickness").doubleValue() / strandImage.getWidth();
                else return goo1.getBall().getObjects().get(0).getAttribute("strandThickness").doubleValue() / strandImage.getWidth();
            }
            public double getScaleY() {

                double x1 = goo1.getPosition().getX();
                double y1 = -goo1.getPosition().getY();

                double x2 = goo2.getPosition().getX();
                double y2 = -goo2.getPosition().getY();

                return Math.hypot(x2 - x1, y2 - y1) / strandImage.getHeight();

            }
            public Image getImage() {
                return strandImage;
            }
            public double getDepth() {
                return 0.00000001;
            }
            public boolean isVisible() {
                return AssetManager.getAsset().getVisibilitySettings().getShowGoos() == 2;
            }
            public boolean isDraggable() {
                return false;
            }
            public boolean isResizable() {
                return false;
            }
            public boolean isRotatable() {
                return false;
            }
        });

        addObjectComponent(new RectangleComponent() {

            public double getX() {
                if (goo1 == null || goo2 == null) return 0;
                double x1 = goo1.getPosition().getX();
                double x2 = goo2.getPosition().getX();
                return (x1 + x2) / 2;
            }

            public double getY() {
                if (goo1 == null || goo2 == null) return 0;
                double y1 = -goo1.getPosition().getY();
                double y2 = -goo2.getPosition().getY();
                return (y1 + y2) / 2;
            }

            public double getRotation() {

                double x1 = goo1.getPosition().getX();
                double y1 = -goo1.getPosition().getY();

                double x2 = goo2.getPosition().getX();
                double y2 = -goo2.getPosition().getY();

                return Math.PI / 2 + Renderer.angleTo(new Point2D(x1, y1), new Point2D(x2, y2));

            }

            public double getWidth() {
                return 0.1;
            }

            public double getHeight() {
                if (goo1 == null || goo2 == null) return 0;
                double x1 = goo1.getPosition().getX();
                double y1 = -goo1.getPosition().getY();
                double x2 = goo2.getPosition().getX();
                double y2 = -goo2.getPosition().getY();
                double amt = (getAttribute("type").stringValue().equals("Terrain")) ? 0.1 : 0.35;
                return Math.hypot(y2 - y1, x2 - x1) - amt;

            }

            public double getEdgeSize() {
                return 0.025;
            }
            public boolean isEdgeOnly() {
                return false;
            }

            public double getDepth(){
                if (goo1 != null && getAttribute("type").stringValue().equals("Terrain")) return 999990;
                return -0.00000001;
            }

            public Paint getBorderColor() {if (goo1 != null && getAttribute("type").stringValue().equals("Terrain")) {
                    return new Color(1.0, 1.0, 1.0, 1.0);
                } else {
                    return new Color(0.0, 0.0, 0.0, 0.0);
                }
            }

            public Paint getColor() {

                double length = getHeight();

                double minSize = 0; // strand.getAttribute("minlen").doubleValue();
                double maxSize = 100000; // strand.getAttribute("maxlen2").doubleValue();

                if (length > maxSize) return new Color(1.0, 0.0, 0.0, 1.0);
                if (length < minSize) return new Color(0.0, 0.0, 1.0, 1.0);
                if (goo1 != null && getAttribute("type").stringValue().equals("Terrain") && FXEditorButtons_ShowHide.comboBoxSelected == goo1.getAttribute("terrainGroup").intValue() && FXEditorButtons_ShowHide.comboBoxSelected != -1) {
                    if (FXEditorButtons_ShowHide.comboBoxList.get(FXEditorButtons_ShowHide.comboBoxSelected)) {
                        return new Color(1.0 ,0.0, 1.0, 1);
                    } else {
                        return new Color(0.0 ,0.0, 1.0, 1);
                    }
                }
                if (goo1 != null && getAttribute("type").stringValue().equals("Terrain") && goo1.getAttribute("terrainGroup").intValue() > -1) {
                    return new Color(0.0, 0.0, 0.0, 1);
                } else {
                    return new Color(0.5, 0.5, 0.5, 1);
                }

            }

            public boolean isVisible() {
                if (getAttribute("type").stringValue().equals("Terrain")) {
                    return (AssetManager.getAsset().getVisibilitySettings().getShowGoos() == 1 || AssetManager.getAsset().getVisibilitySettings().getShowGoos() == 2 && goo1.visibilityFunction() && goo2.visibilityFunction()) || FXEditorButtons_ShowHide.comboBoxSelected == goo1.getAttribute("terrainGroup").intValue();
                }
                return AssetManager.getAsset().getVisibilitySettings().getShowGoos() == 1 && goo1.visibilityFunction() && goo2.visibilityFunction();
            }
            public boolean isDraggable() {
                return false;
            }
            public boolean isResizable() {
                return false;
            }
            public boolean isRotatable() {
                return false;
            }

        });

    }
    
    public _2_Level_BallInstance getGoo1() {
        return goo1;
    }
    
    public void setGoo1(_2_Level_BallInstance goo1) {
        if (this.goo1 != null) {
            this.goo1.removeStrand(this);
            this.goo1.updateTerrainGroup();
        }
        
        this.goo1 = goo1;
        if (goo1 != null) {
            goo1.addStrand(this);
            goo1.updateTerrainGroup();
        }
    }

    public _2_Level_BallInstance getGoo2() {
        return goo2;
    }
    
    public void setGoo2(_2_Level_BallInstance goo2) {
        if (this.goo2 != null) {
            this.goo2.removeStrand(this);
            this.goo2.updateTerrainGroup();
        }
        
        this.goo2 = goo2;
        if (goo2 != null) {
            goo2.addStrand(this);
            goo2.updateTerrainGroup();
        }
    }
    
}
