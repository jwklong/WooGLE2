package com.worldOfGoo2.level;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.ImageUtility;
import com.woogleFX.editorObjects._2_Positionable;
import com.woogleFX.editorObjects.attributes.AttributeAdapter;
import com.woogleFX.editorObjects.attributes.EditorAttribute;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.editorObjects.attributes.MetaEditorAttribute;
import com.woogleFX.editorObjects.attributes.dataTypes.Color;
import com.woogleFX.editorObjects.objectComponents.CircleComponent;
import com.woogleFX.editorObjects.objectComponents.ImageComponent;
import com.woogleFX.editorObjects.objectComponents.TextComponent;
import com.woogleFX.editorObjects.objectCreators.ObjectCreator;
import com.woogleFX.engine.LevelManager;
import com.woogleFX.engine.fx.FXPropertiesView;
import com.woogleFX.engine.renderer.Depth;
import com.woogleFX.file.resourceManagers.ResourceManager;
import com.woogleFX.gameData.animation.AnimationManager;
import com.woogleFX.gameData.animation.Keyframe;
import com.woogleFX.gameData.animation.SimpleBinAnimation;
import com.woogleFX.gameData.animation.WoGAnimation;
import com.woogleFX.gameData.font._Font;
import com.woogleFX.gameData.items.ItemManager;
import com.woogleFX.gameData.level.GameVersion;
import com.worldOfGoo2.items._2_Item;
import com.worldOfGoo2.items._2_Item_Object;
import com.worldOfGoo2.util.BallInstanceHelper;
import com.worldOfGoo2.util.BinAnimationHelper;
import com.worldOfGoo2.util.ItemHelper;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class _2_Level_Item extends _2_Positionable {

    private _2_Item item;
    public _2_Item getItem() {
        return item;
    }


    private final Map<Integer, Integer> randomizationIndices = new HashMap<>();


    public EditorAttribute getUserVariable(String name) {
        for (EditorObject child : getChildren())
            if (child instanceof _2_Level_UserVariable && child.getName().equals(name))
                return child.getAttribute("value");
        return null;
    }


    private final AttributeAdapter[] attributeAdapters2;


    public _2_Level_Item(EditorObject parent) {
        super(parent, "Item", GameVersion.VERSION_WOG2);

        addAttributeAdapter("scale", AttributeAdapter.pointAttributeAdapter(this, "scale", "scale"));

        EditorAttribute temp = new EditorAttribute("type", InputField._2_ITEM_TYPE, this).assertRequired();
        addAttributeAdapter("type", new AttributeAdapter("type") {

            @Override
            public EditorAttribute getValue() {

                if (getAttribute2("type").stringValue().isEmpty()) return temp;
                temp.setValue(ItemHelper.getItemActualName(getAttribute2("type").stringValue()));
                return temp;

            }

            @Override
            public void setValue(String value) {
                temp.setValue(value);
                _2_Item item1 = ItemManager.getItem(value);
                if (item1 == null) return;
                setAttribute2("type", item1.getAttribute("uuid").stringValue());
                updateImage();
                refreshUserVariables();
                if (LevelManager.getLevel().getSelected().length > 0 && _2_Level_Item.this == LevelManager.getLevel().getSelected()[0]) {
                    FXPropertiesView.changeTableView(LevelManager.getLevel().getSelected());
                }
            }

        });

        attributeAdapters2 = getAttributeAdapters().values().toArray(new AttributeAdapter[0]);

    }


    @Override
    public void onLoaded() {
        super.onLoaded();

        EditorObject scale = getChildren("scale").get(0);
        scale.getAttribute("x").addChangeListener((observable, oldValue, newValue) ->
                setAttribute2("scale", newValue + "," + getAttribute2("scale").positionValue().getY()));
        scale.getAttribute("y").addChangeListener((observable, oldValue, newValue) ->
                setAttribute2("scale", getAttribute2("scale").positionValue().getX() + "," + newValue));
        setAttribute2("scale", scale.getAttribute("x").stringValue() + "," + scale.getAttribute("y").stringValue());

        randomizationIndices.put(-1, 0);
        for (EditorObject object : getChildren("objects")) {
            int randGroup = object.getAttribute("randomizationGroup").intValue();
            randomizationIndices.merge(randGroup, 1, Integer::sum);
            randomizationIndices.merge(-1, 1, Integer::sum);
        }
        randomizationIndices.replaceAll((k, v) -> (int) (Math.random() * randomizationIndices.get(k)));

        updateImage();
        refreshUserVariables();

    }


    private void refreshUserVariables() {
        EditorObject obj = this;

        EditorAttribute[] attributes = new EditorAttribute[24];
        System.arraycopy(getAttributes(), 0, attributes, 0, 24);
        setAttributes(attributes);

        getAttributeAdapters().clear();
        addAttributeAdapter("pos", attributeAdapters2[0]);
        addAttributeAdapter("scale", attributeAdapters2[1]);
        addAttributeAdapter("type", attributeAdapters2[2]);

        ArrayList<String> values = new ArrayList<>();
        for (EditorObject userVariable : getChildren("userVariables")) {
            values.add(userVariable.getAttribute("value").stringValue());
            getChildren().remove(userVariable);
        }

        MetaEditorAttribute userVariablesAttribute = getMetaAttributes().get(10);
        userVariablesAttribute.getChildren().clear();

        if (item == null) return;

        int i = 0;
        for (EditorObject ignored : item.getChildren("userVariables")) {
            EditorObject userVariable2 = ObjectCreator.create2(_2_Level_UserVariable.class, this, GameVersion.VERSION_WOG2);
            if (i < values.size()) userVariable2.setAttribute("value", values.get(i));
            i++;
            userVariable2.setTypeID("userVariables");
        }

        ArrayList<EditorObject> userVariables = getItem().getChildren("userVariables");
        
        i = 0;
        for (EditorObject child : getChildren("userVariables")) {
            
            addAttribute(child.getName(), InputField._2_CHILD_HIDDEN);
            
            if (userVariables.get(i).getAttribute("type").intValue() == 4) {
                EditorAttribute attribute = new EditorAttribute(child.getName(), InputField._2_BALL_TYPE_USERVAR, child);
                addAttributeAdapter(child.getName(),
                    BallInstanceHelper.ballTypeAttributeAdapter(child, child.getName(), "value", attribute));
                
                child.addAttributeAdapter(child.getName(),
                    BallInstanceHelper.ballTypeAttributeAdapter(child, child.getName(), "value", attribute));
                
                attribute.addChangeListener((observable, oldValue, newValue) -> {
                    setAttribute(child.getName(), newValue);
                });
            } else {
                addAttributeAdapter(child.getName(), new AttributeAdapter(child.getName()) {
                    @Override
                    public EditorAttribute getValue() {
                        EditorAttribute editorAttribute = new EditorAttribute(child.getName(), InputField._2_STRING, obj);
                        editorAttribute.setValue(child.getAttribute("value").stringValue());
                        return editorAttribute;
                    }
    
                    @Override
                    public void setValue(String value) {
                        child.setAttribute("value", value);
                    }
                });
            }

            MetaEditorAttribute userVariableAttribute = new MetaEditorAttribute();
            userVariableAttribute.setName(child.getName());
            userVariablesAttribute.getChildren().add(userVariableAttribute);
            i++;
        }

    }


    @Override
    public String getName() {
        return getAttribute("id").stringValue();
    }


    private static double lerp(double a, double b, double c) {
        return a + (b - a) * c;
    }


    private static float reverseInterpolate(float a, float b, float c) {
        if (b > a) {
            return (c - a) / (b - a);
        } else if (b == a) {
            return a;
        } else {
            return (c - b) / (a - b);
        }
    }


    public void updateWithAnimation(WoGAnimation animation, float timer) {
        double animspeed = getAttribute("animspeed").doubleValue();
        double animdelay = getAttribute("animdelay").doubleValue();
        double goodTimer = (timer * animspeed - animdelay);
        if (goodTimer >= 0) {
            goodTimer %= animation.getFrameTimes()[animation.getFrameTimes().length - 1];
        } else {
            while (goodTimer < 0){
                goodTimer += animation.getFrameTimes()[animation.getFrameTimes().length - 1];
            }
        }
        for (int i2 : new int[]{ 0, 1, 2 }) {
            if (animation.getTransformFrames()[i2].length == 0) continue;
            int i = 0;
            for (int i3 = 0; i3 < animation.getFrameTimes().length; i3++) {
                if (goodTimer < animation.getFrameTimes()[i3] && animation.getTransformFrames()[i2][i3] != null) {
                    break;
                } else if (animation.getTransformFrames()[i2][i3] != null) {
                    i = i3;
                }
            }
            Keyframe currentFrame = animation.getTransformFrames()[i2][i];
            Keyframe nextFrame;
            int nextIndex = currentFrame.getNextFrameIndex();
            if (currentFrame.getNextFrameIndex() == -1){
                nextIndex = 0;
                nextFrame = currentFrame;
            } else {
                nextFrame = animation.getTransformFrames()[i2][currentFrame.getNextFrameIndex()];
            }
            float timerInterpolateValue = reverseInterpolate(animation.getFrameTimes()[i], animation.getFrameTimes()[nextIndex], (float)goodTimer);
            //if (i2 == 0) {
            //    animscalex = lerp(currentFrame.getX(), nextFrame.getX(), timerInterpolateValue);
            //    animscaley = lerp(currentFrame.getY(), nextFrame.getY(), timerInterpolateValue);
            //} else if (i2 == 1) {
            //    animrotation = lerp(currentFrame.getAngle(), nextFrame.getAngle(), timerInterpolateValue);
            //} else {
            //    animx = lerp(currentFrame.getX(), nextFrame.getX(), timerInterpolateValue);
            //    animy = lerp(currentFrame.getY(), nextFrame.getY(), timerInterpolateValue);
            //}
        }
    }


    @Override
    public void update() {
        updateImage();
    }


    private void updateImage() {

        if (LevelManager.getLevel() == null) return;

        if (!getAttribute2("type").stringValue().isEmpty()) {
            item = ItemManager.getItem(getAttribute("type").stringValue());
            refreshObjectPositions();
        }

    }


    private static ArrayList<_2_Item_Object> orderPartsByLayer(ArrayList<EditorObject> objects) {

        ArrayList<_2_Item_Object> orderedParts = new ArrayList<>();

        for (EditorObject EditorObject : objects) {

            if (EditorObject instanceof _2_Item_Object part) {

                double layer = part.getAttribute("depthOffset").doubleValue();
                int i = 0;

                while (i < orderedParts.size() && orderedParts.get(i).getAttribute("depthOffset").doubleValue() <= layer) i++;

                orderedParts.add(i, part);

            }

        }

        return orderedParts;

    }


    public void refreshObjectPositions() {


        clearObjectComponents();

        boolean ok = false;
        if (item != null) {
            for (_2_Item_Object part : orderPartsByLayer(item.getChildren())) {
                if (addPartAsObjectPosition(part)) ok = true;
            }
        }

        if (!ok) {

            addObjectComponent(new TextComponent() {
                @Override
                public _Font getFont() {
                    return null;
                }

                @Override
                public Font getOtherFont() {
                    return new Font("Consolas", 0.5);
                }

                @Override
                public String getText() {
                    return getAttribute("type").stringValue();
                }

                @Override
                public double getX() {
                    return getPosition().getX() + 0.2;
                }

                @Override
                public double getY() {
                    return -getPosition().getY() + 0.16875;
                }

                @Override
                public double getDepth() {
                    return Depth.ITEMS;
                }

                @Override
                public boolean isVisible() {
                    return shouldShow() && LevelManager.getLevel().getVisibilitySettings().isShowGraphics();
                }

                @Override
                public boolean isResizable() {
                    return false;
                }

                @Override
                public boolean isRotatable() {
                    return false;
                }
            });
            addObjectComponent(new CircleComponent() {
                @Override
                public Paint getColor() {
                    return new javafx.scene.paint.Color(1.0, 1.0, 1.0, 1.0);
                }

                @Override
                public double getEdgeSize() {
                    return 0.1;
                }

                @Override
                public Paint getBorderColor() {
                    return new javafx.scene.paint.Color(1.0, 1.0, 1.0, 1.0);
                }

                @Override
                public boolean isEdgeOnly() {
                    return false;
                }

                @Override
                public double getRadius() {
                    return 0.1;
                }

                @Override
                public double getX() {
                    return getPosition().getX();
                }

                @Override
                public void setX(double x) {
                    setPosition(x, getPosition().getY());
                }

                @Override
                public double getY() {
                    return -getPosition().getY();
                }

                @Override
                public void setY(double y) {
                    setPosition(getPosition().getX(), -y);
                }

                @Override
                public double getDepth() {
                    return Depth.ITEMS;
                }

                @Override
                public boolean isVisible() {
                    return shouldShow() && LevelManager.getLevel().getVisibilitySettings().isShowGraphics();
                }

                @Override
                public boolean isResizable() {
                    return false;
                }

                @Override
                public boolean isRotatable() {
                    return false;
                }
            });
            addObjectComponent(new CircleComponent() {
                @Override
                public Paint getColor() {
                    return new javafx.scene.paint.Color(1.0, 1.0, 1.0, 1.0);
                }

                @Override
                public double getEdgeSize() {
                    return 0.0125;
                }

                @Override
                public Paint getBorderColor() {
                    return new javafx.scene.paint.Color(0.0, 0.0, 0.0, 1.0);
                }

                @Override
                public boolean isEdgeOnly() {
                    return false;
                }

                @Override
                public double getRadius() {
                    return 0.1;
                }

                @Override
                public double getX() {
                    return getPosition().getX();
                }

                @Override
                public void setX(double x) {
                    setPosition(x, getPosition().getY());
                }

                @Override
                public double getY() {
                    return -getPosition().getY();
                }

                @Override
                public void setY(double y) {
                    setPosition(getPosition().getX(), -y);
                }

                @Override
                public double getDepth() {
                    return Depth.ITEMS;
                }

                @Override
                public boolean isVisible() {
                    return shouldShow() && LevelManager.getLevel().getVisibilitySettings().isShowGraphics();
                }

                @Override
                public boolean isResizable() {
                    return false;
                }

                @Override
                public boolean isRotatable() {
                    return false;
                }
            });

        }

        String animation = getItem().getAttribute("animationName").stringValue();
        if (!animation.isEmpty()) {
            try {
                SimpleBinAnimation flashAnim = ResourceManager.getFlashAnim(null, animation, GameVersion.VERSION_WOG2);
                BinAnimationHelper.addBinAnimationAsObjectPositions(this, flashAnim, getItem().getAttribute("animationAlias").stringValue());
            } catch (FileNotFoundException e) {
                logger.error("", e);
            }
        } else {
            String animationAlias = getItem().getAttribute("animationAlias").stringValue();
            if (!animationAlias.isEmpty()) {
                System.out.println(animationAlias);
                for (SimpleBinAnimation binAnimation : AnimationManager.getBinAnimations()) {
                    int i1 = 0;
                    for (SimpleBinAnimation.SimpleBinAnimationState ignored : binAnimation.states) {
                        if (binAnimation.stateAliasStringTableIndices.length <= i1) break;
                        StringBuilder stringBuilder = new StringBuilder();
                        int byteIndex = binAnimation.stringDefinitions[binAnimation.stateAliasStringTableIndices[i1]].stringTableIndex;
                        while (binAnimation.stringTable[byteIndex] != 0x00) {
                            stringBuilder.append((char)binAnimation.stringTable[byteIndex]);
                            byteIndex++;
                        }
                        if (stringBuilder.toString().equals(animationAlias)) {
                            BinAnimationHelper.addBinAnimationAsObjectPositions(this, binAnimation, animationAlias);
                            break;
                        }
                        i1++;
                    }
                }
            }
        }

    }


    private boolean addPartAsObjectPosition(_2_Item_Object part) {

        double partX = (item.getAttribute("variations").booleanValue()) ? 0 : part.getChildren("position").get(0).getAttribute("x").doubleValue();
        double partY = (item.getAttribute("variations").booleanValue()) ? 0 : -part.getChildren("position").get(0).getAttribute("y").doubleValue();
        double partScaleX = part.getChildren("scale").get(0).getAttribute("x").doubleValue();
        double partScaleY = part.getChildren("scale").get(0).getAttribute("y").doubleValue();
        double partRotation = part.getAttribute("rotation").doubleValue();

        // TODO build hitbox based on entire bounds of parts

        Image img = part.getImage();

        if (img != null) {
            double partPivotX = (part.getChildren("pivot").get(0).getAttribute("x").doubleValue() - 0.5) * img.getWidth() * 0.01;
            double partPivotY = (0.5 - part.getChildren("pivot").get(0).getAttribute("y").doubleValue()) * img.getHeight() * 0.01;

            long color = Long.parseLong(part.getAttribute("color").stringValue());

            Image finalImg = ImageUtility.colorize(img, new Color((int)((color & 0xFF000000L) >> 24), (int)((color & 0xFF0000) >> 16), (int)((color & 0xFF00) >> 8), (int)(color & 0xFF)));

            addObjectComponent(new ImageComponent() {
                @Override
                public double getX() {

                    double x = getPosition().getX();
                    double scaleX = getAttribute("scale").positionValue().getX();

                    return x + (partX - partPivotX * partScaleX) * scaleX;

                }
                @Override
                public void setX(double x) {
                    double y = getPosition().getY();
                    double scaleX = getAttribute("scale").positionValue().getX();
                    setPosition(x - (partX - partPivotX * partScaleX) * scaleX, y);
                }
                @Override
                public double getY() {

                    double y = -getPosition().getY();
                    double scaleY = getAttribute("scale").positionValue().getY();

                    return y + (partY - partPivotY * partScaleY) * scaleY;

                }
                @Override
                public void setY(double y) {
                    double x = getPosition().getX();
                    double scaleY = getAttribute("scale").positionValue().getY();
                    setPosition(x, -(y - (partY - partPivotY * partScaleY) * scaleY));
                }
                @Override
                public double getRotation() {
                    return -getAttribute("rotation").doubleValue() - partRotation;
                }
                @Override
                public void setRotation(double rotation) {
                    setAttribute("rotation", -rotation + partRotation);
                }
                @Override
                public double getScaleX() {
                    double scaleX = getAttribute("scale").positionValue().getX() * 0.01;
                    return partScaleX * scaleX;
                }
                @Override
                public double getScaleY() {
                    double scaleY = getAttribute("scale").positionValue().getY() * 0.01;
                    return partScaleY * scaleY;
                }
                @Override
                public void setScaleX(double _scaleX) {
                    double scaleY = getAttribute("scale").positionValue().getY();
                    setAttribute("scale", _scaleX * 100.0 / partScaleX + "," + scaleY);
                }
                @Override
                public void setScaleY(double _scaleY) {
                    double scaleX = getAttribute("scale").positionValue().getX();
                    setAttribute("scale", scaleX + "," + _scaleY * 100.0 / partScaleY);
                }
                @Override
                public double getDepth() {
                    return getAttribute("depth").doubleValue();
                }
                @Override
                public double getAlpha() {
                    return part.getAttribute("imageAlpha").doubleValue() * (part.getAttribute("invisible").booleanValue() ? 0.5 : 1);
                }
                @Override
                public Image getImage() {
                    return finalImg;
                }
                @Override
                public boolean isVisible() {
                    if (!LevelManager.getLevel().getVisibilitySettings().isShowGraphics()) return false;
                    if (!shouldShow()) return false;
                    if (getAttribute("forcedRandomizationIndex").intValue() == -1) {
                        return randomizationIndices.get(-1) == null || item.getChildren("objects").indexOf(part) == randomizationIndices.get(-1);
                    }
                    if (true) return item.getChildren("objects").indexOf(part) == getAttribute("forcedRandomizationIndex").intValue();
                    if (randomizationIndices.get(part.getAttribute("randomizationGroup").intValue()) == null) return false;
                    if (part.getAttribute("randomizationGroup").intValue() == getAttribute("forcedRandomizationIndex").intValue()) {
                        int index = 0;
                        for (EditorObject child : getChildren()) if (child instanceof _2_Item_Object && child.getAttribute("randomizationGroup").intValue() == getAttribute("forcedRandomizationIndex").intValue()) {
                            if (child == part) break;
                            index++;
                        }
                        return (index == randomizationIndices.get(part.getAttribute("randomizationGroup").intValue()));
                    }
                    return false;
                }
                @Override
                public boolean isDraggable() {
                    return finalImg != null && finalImg.getWidth() > 2 && finalImg.getHeight() > 2;
                }
                @Override
                public boolean isResizable() {
                    return finalImg != null && finalImg.getWidth() > 2 && finalImg.getHeight() > 2;
                }
                @Override
                public boolean isRotatable() {
                    return finalImg != null && finalImg.getWidth() > 2 && finalImg.getHeight() > 2;
                }
            });

            return finalImg != null && finalImg.getWidth() > 2 && finalImg.getHeight() > 2;

        }

        return false;

    }


    public boolean shouldShow() {

        String type = getAttribute("type").stringValue();
        if (type.equals("LinearForceField")) return LevelManager.getLevel().getVisibilitySettings().isShowForcefields();

        return true;

    }

}
