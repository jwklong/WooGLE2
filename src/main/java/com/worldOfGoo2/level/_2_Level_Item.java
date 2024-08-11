package com.worldOfGoo2.level;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.ImageUtility;
import com.woogleFX.editorObjects.ObjectUtil;
import com.woogleFX.editorObjects.attributes.AttributeAdapter;
import com.woogleFX.editorObjects.attributes.EditorAttribute;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.editorObjects.attributes.MetaEditorAttribute;
import com.woogleFX.editorObjects.attributes.dataTypes.Color;
import com.woogleFX.editorObjects.objectComponents.CircleComponent;
import com.woogleFX.editorObjects.objectComponents.ImageComponent;
import com.woogleFX.editorObjects.objectComponents.TextComponent;
import com.woogleFX.engine.LevelManager;
import com.woogleFX.engine.renderer.Depth;
import com.woogleFX.file.resourceManagers.GlobalResourceManager;
import com.woogleFX.file.resourceManagers.ResourceManager;
import com.woogleFX.gameData.animation.Keyframe;
import com.woogleFX.gameData.animation.WoGAnimation;
import com.woogleFX.gameData.font._Font;
import com.woogleFX.gameData.level.GameVersion;
import com.worldOfGoo2.items._2_Item;
import com.worldOfGoo2.items._2_Item_Object;
import com.worldOfGoo2.util.ItemHelper;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class _2_Level_Item extends EditorObject {

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


    public _2_Level_Item(EditorObject parent) {
        super(parent, "Item", GameVersion.VERSION_WOG2);

        addAttribute("id", InputField._2_STRING).assertRequired();
        addAttribute("type", InputField._2_ITEM_TYPE).assertRequired();
        addAttribute("localizedStringId", InputField._2_STRING).assertRequired();
        addAttribute("uid", InputField._2_UID).assertRequired();

        addAttribute("pos", InputField._2_CHILD_HIDDEN).assertRequired();
        putAttributeChildAlias("pos", "_2_Point");

        addAttribute("scale", InputField._2_CHILD_HIDDEN).assertRequired();
        putAttributeChildAlias("scale", "_2_Point");

        addAttribute("rotation", InputField._2_NUMBER).assertRequired();
        addAttribute("depth", InputField._2_NUMBER).assertRequired();
        addAttribute("flipHorizontal", InputField._2_BOOLEAN);
        addAttribute("flipVertical", InputField._2_BOOLEAN);
        addAttribute("rotSpeed", InputField._2_NUMBER).assertRequired();
        addAttribute("collisionGroup", InputField._2_COLLISION_GROUP);
        addAttribute("bodyUID", InputField._2_UID);
        addAttribute("seed", InputField._2_NUMBER).assertRequired();
        addAttribute("maxCount", InputField._2_NUMBER);
        addAttribute("ratePps", InputField._2_NUMBER);
        addAttribute("liquidType", InputField._2_LIQUID_TYPE).assertRequired();
        addAttribute("adapterBallId", InputField._2_BALL_UID).assertRequired(); // Maybe?
        addAttribute("invisible", InputField._2_BOOLEAN).assertRequired();
        addAttribute("forcedRandomizationIndex", InputField._2_NUMBER).assertRequired();
        addAttribute("particleEffectName", InputField._2_STRING).assertRequired();
        addAttribute("uid1", InputField._2_UID);
        addAttribute("uid2", InputField._2_UID);

        addAttribute("userVariables", InputField._2_LIST_CHILD_HIDDEN).assertRequired();
        putAttributeChildAlias("userVariables", "_2_Level_UserVariable");

        EditorObject obj = this;

        addAttributeAdapter("pos", new AttributeAdapter("pos") {

            @Override
            public EditorAttribute getValue() {
                return getAttribute2("pos");
            }

            @Override
            public void setValue(String value) {
                EditorObject pos = getChildren("pos").get(0);
                String x = value.substring(0, value.indexOf(","));
                String y = value.substring(value.indexOf(",") + 1);
                pos.setAttribute("x", x);
                pos.setAttribute("y", y);
            }

        });

        addAttributeAdapter("scale", new AttributeAdapter("scale") {

            @Override
            public EditorAttribute getValue() {
                return getAttribute2("scale");
            }

            @Override
            public void setValue(String value) {
                EditorObject scale = getChildren("scale").get(0);
                String x = value.substring(0, value.indexOf(","));
                String y = value.substring(value.indexOf(",") + 1);
                scale.setAttribute("x", x);
                scale.setAttribute("y", y);
            }

        });

        EditorAttribute temp = new EditorAttribute("type", InputField._2_ITEM_TYPE, obj);
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
                for (EditorObject resource : GlobalResourceManager.getSequelResources()) {
                    if (resource instanceof _2_Item) {
                        if (resource.getAttribute("name").stringValue().equals(value)) {
                            setAttribute2("type", resource.getAttribute("uuid").stringValue());
                            updateImage();
                            return;
                        }
                    }
                }
            }

        });

    }


    @Override
    public void onLoaded() {
        super.onLoaded();

        EditorObject pos = getChildren("pos").get(0);
        pos.getAttribute("x").addChangeListener((observable, oldValue, newValue) ->
                setAttribute2("pos", newValue + "," + getAttribute2("pos").positionValue().getY()));
        pos.getAttribute("y").addChangeListener((observable, oldValue, newValue) ->
                setAttribute2("pos", getAttribute2("pos").positionValue().getX() + "," + newValue));
        setAttribute2("pos", pos.getAttribute("x").stringValue() + "," + pos.getAttribute("y").stringValue());

        EditorObject scale = getChildren("scale").get(0);
        scale.getAttribute("x").addChangeListener((observable, oldValue, newValue) ->
                setAttribute2("scale", newValue + "," + getAttribute2("scale").positionValue().getY()));
        scale.getAttribute("y").addChangeListener((observable, oldValue, newValue) ->
                setAttribute2("scale", getAttribute2("scale").positionValue().getX() + "," + newValue));
        setAttribute2("scale", scale.getAttribute("x").stringValue() + "," + scale.getAttribute("y").stringValue());

        for (EditorObject object : getChildren("objects")) {
            int randGroup = object.getAttribute("randomizationGroup").intValue();
            randomizationIndices.merge(randGroup, 1, Integer::sum);
        }
        randomizationIndices.replaceAll((k, v) -> (int) (Math.random() * randomizationIndices.get(k)));

        String general = "id,type,localizedStringId,uid,pos,scale,rotation,depth,";
        String ids = "?IDs<bodyUID,seed,forcedRandomizationIndex,uid1,uid2>";
        String gameplay = "?Gameplay<rotSpeed,collisionGroup,maxCount,ratePps,liquidType,adapterBallId,invisible,flipHorizontal,flipVertical,particleEffectName>";
        StringBuilder userVariables = new StringBuilder("?User Variables<");
        EditorObject obj = this;
        boolean anyUserVariables = false;
        for (EditorObject child : getChildren("userVariables")) {
            anyUserVariables = true;

            addAttribute(child.getName(), InputField._2_CHILD);
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

            userVariables.append(child.getName()).append(",");
        }
        userVariables.deleteCharAt(userVariables.length() - 1);
        userVariables.append(">");
        if (!anyUserVariables) userVariables = new StringBuilder();
        setMetaAttributes(MetaEditorAttribute.parse(general + ids + gameplay + userVariables));

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

        try {
            if (!getAttribute2("type").stringValue().isEmpty()) {
                item = ResourceManager.getItem(null, getAttribute2("type").stringValue(), GameVersion.VERSION_WOG2);
                refreshObjectPositions();
            }
        } catch (FileNotFoundException ignored) {

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


        clearObjectPositions();

        boolean ok = false;
        if (item != null) {
            for (_2_Item_Object part : orderPartsByLayer(item.getChildren())) {
                if (addPartAsObjectPosition(part)) ok = true;
            }
        }

        boolean finalOk = ok;
        addObjectComponent(new TextComponent() {
            public _Font getFont() {
                return null;
            }
            public String getText() {
                return getAttribute("type").stringValue();
            }
            public double getX() {
                return getAttribute("pos").positionValue().getX() + 0.2;
            }
            public double getY() {
                return -getAttribute("pos").positionValue().getY() + 0.16875;
            }
            public double getDepth() {
                return Depth.ITEMS;
            }
            public boolean isVisible() {
                return !finalOk && shouldShow() && LevelManager.getLevel().getVisibilitySettings().isShowGraphics();
            }
            public boolean isResizable() {
                return false;
            }
            public boolean isRotatable() {
                return false;
            }
        });
        addObjectComponent(new CircleComponent() {
            public Paint getColor() {
                return new javafx.scene.paint.Color(1.0, 1.0, 1.0, 1.0);
            }
            public double getEdgeSize() {
                return 0.0125;
            }
            public Paint getBorderColor() {
                return new javafx.scene.paint.Color(0.0, 0.0, 0.0, 1.0);
            }
            public boolean isEdgeOnly() {
                return false;
            }
            public double getRadius() {
                return 0.1;
            }
            public double getX() {
                return getAttribute("pos").positionValue().getX();
            }
            public void setX(double x) {
                double y = getAttribute("pos").positionValue().getY();
                setAttribute("pos", x + "," + y);
            }
            public double getY() {
                return -getAttribute("pos").positionValue().getY();
            }
            public void setY(double y) {
                double x = getAttribute("pos").positionValue().getX();
                setAttribute("pos", x + "," + (-y));
            }
            public double getDepth() {
                return Depth.ITEMS;
            }
            public boolean isVisible() {
                return !finalOk && shouldShow() && LevelManager.getLevel().getVisibilitySettings().isShowGraphics();
            }
            public boolean isResizable() {
                return false;
            }
            public boolean isRotatable() {
                return false;
            }
        });
        addObjectComponent(new CircleComponent() {
            public Paint getColor() {
                return new javafx.scene.paint.Color(1.0, 1.0, 1.0, 1.0);
            }
            public double getEdgeSize() {
                return 0.1;
            }
            public Paint getBorderColor() {
                return new javafx.scene.paint.Color(1.0, 1.0, 1.0, 1.0);
            }
            public boolean isEdgeOnly() {
                return false;
            }
            public double getRadius() {
                return 0.1;
            }
            public double getX() {
                return getAttribute("pos").positionValue().getX();
            }
            public void setX(double x) {
                double y = getAttribute("pos").positionValue().getY();
                setAttribute("pos", x + "," + y);
            }
            public double getY() {
                return -getAttribute("pos").positionValue().getY();
            }
            public void setY(double y) {
                double x = getAttribute("pos").positionValue().getX();
                setAttribute("pos", x + "," + (-y));
            }
            public double getDepth() {
                return Depth.ITEMS;
            }
            public boolean isVisible() {
                return !finalOk && shouldShow() && LevelManager.getLevel().getVisibilitySettings().isShowGraphics();
            }
            public boolean isResizable() {
                return false;
            }
            public boolean isRotatable() {
                return false;
            }
        });

    }


    private boolean addPartAsObjectPosition(_2_Item_Object part) {

        double partX = 0; // part.getChildren("position").get(0).getAttribute("x").doubleValue();
        double partY = 0; // -part.getChildren("position").get(0).getAttribute("y").doubleValue();
        double partScaleX = part.getChildren("scale").get(0).getAttribute("x").doubleValue();
        double partScaleY = part.getChildren("scale").get(0).getAttribute("y").doubleValue();
        double partRotation = part.getAttribute("rotation").doubleValue();

        // TODO build hitbox based on entire bounds of parts

        Image img = part.getImage();

        if (img != null) {

            long color = Long.parseLong(part.getAttribute("color").stringValue());

            Image finalImg = ImageUtility.colorize(img, new Color((int)((color & 0xFF000000L) >> 24), (int)((color & 0xFF0000) >> 16), (int)((color & 0xFF00) >> 8), (int)(color & 0xFF)));

            addObjectComponent(new ImageComponent() {
                public double getX() {

                    double x = getAttribute("pos").positionValue().getX();
                    double y = -getAttribute("pos").positionValue().getY();
                    double scaleX = getAttribute("scale").positionValue().getX() * 0.01;
                    double scaleY = getAttribute("scale").positionValue().getY() * 0.01;
                    double angle = -getAttribute("rotation").doubleValue();

                    Point2D position = new Point2D(partX * scaleX, partY * scaleY);
                    position = ObjectUtil.rotate(position, angle, new Point2D(0, 0));
                    position = position.add(x, y);

                    return position.getX();

                }
                public void setX(double x) {
                    double y = -getAttribute("pos").positionValue().getY();
                    double scaleX = getAttribute("scale").positionValue().getX() * 0.01;
                    setAttribute("pos", (x - partX * scaleX) + "," + y);
                }
                public double getY() {

                    double x = getAttribute("pos").positionValue().getX();
                    double y = -getAttribute("pos").positionValue().getY();
                    double scaleX = getAttribute("scale").positionValue().getX() * 0.01;
                    double scaleY = getAttribute("scale").positionValue().getY() * 0.01;
                    double angle = -getAttribute("rotation").doubleValue();

                    Point2D position = new Point2D(partX * scaleX, partY * scaleY);
                    position = ObjectUtil.rotate(position, angle, new Point2D(0, 0));
                    position = position.add(x, y);

                    return position.getY();

                }
                public void setY(double y) {
                    double x = getAttribute("pos").positionValue().getX();
                    double scaleY = getAttribute("scale").positionValue().getY() * 0.01;
                    setAttribute("pos", x + "," + (-y + partY * scaleY));
                }
                public double getRotation() {
                    return -getAttribute("rotation").doubleValue() - partRotation;
                }
                public void setRotation(double rotation) {
                    setAttribute("rotation", -rotation + partRotation);
                }
                public double getScaleX() {
                    double scaleX = getAttribute("scale").positionValue().getX() * 0.01;
                    return partScaleX * scaleX;
                }
                public double getScaleY() {
                    double scaleY = getAttribute("scale").positionValue().getY() * 0.01;
                    return partScaleY * scaleY;
                }
                public void setScaleX(double _scaleX) {
                    double scaleY = getAttribute("scale").positionValue().getY();
                    setAttribute("scale", _scaleX * 100.0 / partScaleX + "," + scaleY);
                }
                public void setScaleY(double _scaleY) {
                    double scaleX = getAttribute("scale").positionValue().getX();
                    setAttribute("scale", scaleX * 100.0 + "," + _scaleY / partScaleY);
                }
                public double getDepth() {
                    return getAttribute("depth").doubleValue();
                }
                public double getAlpha() {
                    return part.getAttribute("imageAlpha").doubleValue() * (part.getAttribute("invisible").booleanValue() ? 0.5 : 1);
                }
                public Image getImage() {
                    return finalImg;
                }
                public boolean isVisible() {
                    if (!LevelManager.getLevel().getVisibilitySettings().isShowGraphics()) return false;
                    if (!shouldShow()) return false;
                    if (getAttribute("forcedRandomizationIndex").intValue() == -1) return true;
                    if (randomizationIndices.get(part.getAttribute("randomizationGroup").intValue()) == null) return false;
                    if (part.getAttribute("randomizationGroup").intValue() == getAttribute("forcedRandomizationIndex").intValue()) {
                        int index = 0;
                        for (EditorObject child : getChildren()) if (child instanceof _2_Item_Object && child.getAttribute("randomizationGroup").intValue() == getAttribute("forcedRandomizationIndex").intValue()) {
                            index++;
                            if (child == part) break;
                        }
                        return (index == randomizationIndices.get(part.getAttribute("randomizationGroup").intValue()));
                    }
                    return false;
                }
                public boolean isDraggable() {
                    return finalImg != null && finalImg.getWidth() > 2 && finalImg.getHeight() > 2;
                }
                public boolean isResizable() {
                    return finalImg != null && finalImg.getWidth() > 2 && finalImg.getHeight() > 2;
                }
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
