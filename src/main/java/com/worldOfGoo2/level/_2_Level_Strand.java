package com.worldOfGoo2.level;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.ObjectUtil;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.editorObjects.attributes.MetaEditorAttribute;
import com.woogleFX.editorObjects.attributes.dataTypes.Position;
import com.woogleFX.editorObjects.objectComponents.ImageComponent;
import com.woogleFX.editorObjects.objectComponents.RectangleComponent;
import com.woogleFX.engine.LevelManager;
import com.woogleFX.engine.renderer.Renderer;
import com.woogleFX.gameData.ball.AtlasManager;
import com.woogleFX.gameData.ball.BallManager;
import com.woogleFX.gameData.ball._Ball;
import com.woogleFX.gameData.level.GameVersion;
import com.woogleFX.gameData.level.WOG1Level;
import com.woogleFX.gameData.level.WOG2Level;
import com.woogleFX.gameData.level.levelOpening.LevelLoader;
import com.worldOfGoo.ball.BallStrand;
import com.worldOfGoo.level.BallInstance;
import com.worldOfGoo2.misc._2_ImageID;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class _2_Level_Strand extends EditorObject {

    //private BallStrand strand;
    //public void setStrand(BallStrand strand) {
    //    this.strand = strand;
    //}


    private Image strandImage;


    private _2_Level_BallInstance goo1 = null;
    public void setGoo1(_2_Level_BallInstance goo1) {
        this.goo1 = goo1;
    }


    private _2_Level_BallInstance goo2 = null;
    public void setGoo2(_2_Level_BallInstance goo2) {
        this.goo2 = goo2;
    }


    //private int strandBallID = 2;

    public _2_Level_Strand(EditorObject parent) {
        super(parent, "Strand", GameVersion.VERSION_WOG2);

        addAttribute("ball1UID", InputField._2_BALL_UID);
        addAttribute("ball2UID", InputField._2_BALL_UID);
        addAttribute("type", InputField._2_STRAND_TYPE);
        addAttribute("filled", InputField._2_BOOLEAN);

        setMetaAttributes(MetaEditorAttribute.parse("ball1UID,ball2UID,type,filled,"));



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
    public void update() {

        if (LevelManager.getLevel() == null) return;

        for (EditorObject obj : ((WOG2Level)LevelManager.getLevel()).getObjects()) if (obj instanceof _2_Level_BallInstance ballInstance) {

            String id = ballInstance.getAttribute("uid").stringValue();
            String gb1 = getAttribute("ball1UID").stringValue();
            String gb2 = getAttribute("ball2UID").stringValue();

            if (id.equals(gb1)) {

                goo1 = ballInstance;

                //if (strand == null) {

                //    String type = ballInstance.getAttribute("type").stringValue();
                //    if (setStrand(type)) strandBallID = 1;

                //}

            }

            else if (id.equals(gb2)) {

                goo2 = ballInstance;

                //String type = ballInstance.getAttribute("type").stringValue();
                //if (setStrand(type)) strandBallID = 2;

            }

        }

        if (strandImage == null) {
            try {
                String imageString = "";
                if (goo2 == null || goo2.getBall() == null || goo2.getBall().getObjects().get(0).getChildren("strandImageId").get(0).getAttribute("imageId").stringValue().isEmpty()) {
                    if (goo1.getBall() == null) return;
                    for (EditorObject editorObject : goo1.getBall().getObjects())
                        if (editorObject instanceof _2_ImageID ball_image && editorObject.getTypeID().equals("strandImageId"))
                            if (!ball_image.getAttribute("imageId").stringValue().isEmpty())
                                imageString = ball_image.getAttribute("imageId").stringValue();
                } else {
                    for (EditorObject editorObject : goo2.getBall().getObjects())
                        if (editorObject instanceof _2_ImageID ball_image && editorObject.getTypeID().equals("strandImageId"))
                            if (!ball_image.getAttribute("imageId").stringValue().isEmpty())
                                imageString = ball_image.getAttribute("imageId").stringValue();
                }

                if (AtlasManager.atlas.containsKey(imageString))
                    strandImage = SwingFXUtils.toFXImage(AtlasManager.atlas.get(imageString), null);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //if (strandImage != null) addPartAsObjectPosition();

        addPartAsObjectPosition();

    }


    private static Point2D lineLineSegmentIntersection(double x1, double y1, double theta, double x2, double y2,
                                                       double x3, double y3) {
        // logger.info(x1 + ", " + y1 + ", " + theta + ", " + x2 + ", " + y2 + ",
        // " + x3 + ", " + y3);
        if (y3 == y2) {
            y3 += 0.00001;
        }
        if (x3 == x2) {
            x3 += 0.00001;
        }
        double m = (y3 - y2) / (x3 - x2);
        double x = (y2 - x2 * m + x1 * Math.tan(theta) - y1) / (Math.tan(theta) - m);
        double y = (x - x1) * Math.tan(theta) + y1;

        double bruh = 0.01;
        // logger.info(x + ", " + y);
        // logger.info(y + ", " + ((x - x2) * m + y2));
        // 385.94690307546693, 682.9469030754669
        if (x > Math.min(x2, x3) - bruh && x < Math.max(x2, x3) + bruh && y > Math.min(y2, y3) - bruh
                && y < Math.max(y2, y3) + bruh) {
            // logger.info("e");
            return new Point2D(x, y);
        } else {
            return null;
        }
    }

    private static Point2D lineBoxIntersection(double x1, double y1, double theta, double x2, double y2, double sizeX,
                                               double sizeY, double rotation) {

        Point2D topLeft = ObjectUtil.rotate(new Point2D(x2 - sizeX / 2, y2 - sizeY / 2), rotation,
                new Point2D(x2, y2));
        Point2D topRight = ObjectUtil.rotate(new Point2D(x2 + sizeX / 2, y2 - sizeY / 2), rotation,
                new Point2D(x2, y2));
        Point2D bottomLeft = ObjectUtil.rotate(new Point2D(x2 - sizeX / 2, y2 + sizeY / 2), rotation,
                new Point2D(x2, y2));
        Point2D bottomRight = ObjectUtil.rotate(new Point2D(x2 + sizeX / 2, y2 + sizeY / 2), rotation,
                new Point2D(x2, y2));

        Point2D top = lineLineSegmentIntersection(x1, y1, theta, topLeft.getX(), topLeft.getY(), topRight.getX(),
                topRight.getY());
        Point2D bottom = lineLineSegmentIntersection(x1, y1, theta, bottomLeft.getX(), bottomLeft.getY(),
                bottomRight.getX(), bottomRight.getY());
        Point2D left = lineLineSegmentIntersection(x1, y1, theta, topLeft.getX(), topLeft.getY(), bottomLeft.getX(),
                bottomLeft.getY());
        Point2D right = lineLineSegmentIntersection(x1, y1, theta, topRight.getX(), topRight.getY(),
                bottomRight.getX(), bottomRight.getY());

        Point2D origin = new Point2D(x1, y1);

        double topDistance = top == null ? 100000000 : top.distance(origin);
        double bottomDistance = bottom == null ? 100000000 : bottom.distance(origin);
        double leftDistance = left == null ? 100000000 : left.distance(origin);
        double rightDistance = right == null ? 100000000 : right.distance(origin);

        if (topDistance < bottomDistance && topDistance < leftDistance && topDistance < rightDistance) {
            return top;
        }

        if (bottomDistance < leftDistance && bottomDistance < rightDistance) {
            return bottom;
        }

        if (leftDistance < rightDistance) {
            return left;
        }

        if (right == null) {
            return new Point2D(0, 0);
        }
        return right;
    }


    private void addPartAsObjectPosition() {

        clearObjectPositions();

        if (goo1 == null || goo2 == null) return;

        //if (strandImage == null) {
        //    update();
        //}

        if (strandImage != null) addObjectComponent(new ImageComponent() {
            public double getX() {
                double x1 = goo1.getAttribute("pos").positionValue().getX();
                double x2 = goo2.getAttribute("pos").positionValue().getX();
                return (x1 + x2) / 2;
            }
            public double getY() {
                double y1 = -goo1.getAttribute("pos").positionValue().getY();
                double y2 = -goo2.getAttribute("pos").positionValue().getY();
                return (y1 + y2) / 2;
            }
            public double getRotation() {

                double x1 = goo1.getAttribute("pos").positionValue().getX();
                double y1 = -goo1.getAttribute("pos").positionValue().getY();

                double x2 = goo2.getAttribute("pos").positionValue().getX();
                double y2 = -goo2.getAttribute("pos").positionValue().getY();

                return Math.PI / 2 + Renderer.angleTo(new Point2D(x1, y1), new Point2D(x2, y2));

            }
            public double getScaleX() {
                if (strandImage.getWidth() == 0) return 0;
                // if (goo2.getBall().getObjects().get(0).getAttribute("strandThickness").doubleValue() == 0) return goo1.getBall().getObjects().get(0).getAttribute("strandThickness").doubleValue() / strandImage.getWidth();
                if (goo1.getBall() == null || goo1.getBall().getObjects().get(0).getAttribute("strandThickness").doubleValue() <= 0) return goo2.getBall().getObjects().get(0).getAttribute("strandThickness").doubleValue() / strandImage.getWidth();
                else return goo1.getBall().getObjects().get(0).getAttribute("strandThickness").doubleValue() / strandImage.getWidth();
            }
            public double getScaleY() {

                double x1 = goo1.getAttribute("pos").positionValue().getX();
                double y1 = -goo1.getAttribute("pos").positionValue().getY();

                double x2 = goo2.getAttribute("pos").positionValue().getX();
                double y2 = -goo2.getAttribute("pos").positionValue().getY();

                return Math.hypot(x2 - x1, y2 - y1) / strandImage.getHeight();

            }
            public Image getImage() {
                return strandImage;
            }
            public double getDepth() {
                return 0.00000001;
            }
            public boolean isVisible() {
                return LevelManager.getLevel().getVisibilitySettings().getShowGoos() == 2;
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

                double x1 = goo1.getAttribute("pos").positionValue().getX();
                double y1 = -goo1.getAttribute("pos").positionValue().getY();
                double rotation1 = -Math.toRadians(goo1.getAttribute("angle").doubleValue());

                double x2 = goo2.getAttribute("pos").positionValue().getX();
                double y2 = -goo2.getAttribute("pos").positionValue().getY();
                double rotation2 = -Math.toRadians(goo2.getAttribute("angle").doubleValue());

                Position size1 = new Position(0.4, 0.4); // goo1.getBall() == null ? new Position(30, 30) : new Position(goo1.getBall().getShapeSize(), goo1.getBall().getShapeSize2());
                boolean circle1 = true; // goo1.getBall() == null || goo1.getBall().getShapeType().equals("circle");

                Position size2 = new Position(0.4, 0.4); // goo2.getBall() == null ? new Position(30, 30) : new Position(goo2.getBall().getShapeSize(), goo2.getBall().getShapeSize2());
                boolean circle2 = true; // goo2.getBall() == null || goo2.getBall().getShapeType().equals("circle");

                double theta = Renderer.angleTo(new Point2D(x1, y1), new Point2D(x2, y2));

                Point2D hit1;
                Point2D hit2;

                if (circle1) {
                    double r1 = size1.getX() / 2;
                    hit1 = new Point2D(x1 + r1 * Math.cos(theta), y1 + r1 * Math.sin(theta));
                } else {
                    hit1 = lineBoxIntersection(x2, y2, theta - Math.PI, x1, y1, size1.getX(), size1.getY(), rotation1);
                }

                if (circle2) {
                    double r2 = size2.getX() / 2;
                    hit2 = new Point2D(x2 - r2 * Math.cos(theta), y2 - r2 * Math.sin(theta));
                } else {
                    hit2 = lineBoxIntersection(x1, y1, theta, x2, y2, size2.getX(), size2.getY(), rotation2);
                }

                return (hit1.getX() + hit2.getX()) / 2;

            }

            public double getY() {

                if (goo1 == null || goo2 == null) return 0;

                double x1 = goo1.getAttribute("pos").positionValue().getX();
                double y1 = -goo1.getAttribute("pos").positionValue().getY();
                double rotation1 = -Math.toRadians(goo1.getAttribute("angle").doubleValue());

                double x2 = goo2.getAttribute("pos").positionValue().getX();
                double y2 = -goo2.getAttribute("pos").positionValue().getY();
                double rotation2 = -Math.toRadians(goo2.getAttribute("angle").doubleValue());

                Position size1 = new Position(0.1, 0.1); // goo1.getBall() == null ? new Position(30, 30) : new Position(goo1.getBall().getShapeSize(), goo1.getBall().getShapeSize2());
                boolean circle1 = true; // goo1.getBall() == null || goo1.getBall().getShapeType().equals("circle");

                Position size2 = new Position(0.1, 0.1); // goo2.getBall() == null ? new Position(30, 30) : new Position(goo2.getBall().getShapeSize(), goo2.getBall().getShapeSize2());
                boolean circle2 = true; // goo2.getBall() == null || goo2.getBall().getShapeType().equals("circle");

                double theta = Renderer.angleTo(new Point2D(x1, y1), new Point2D(x2, y2));

                Point2D hit1;
                Point2D hit2;
                if (circle1) {
                    double r1 = size1.getX() / 2;
                    hit1 = new Point2D(x1 + r1 * Math.cos(theta), y1 + r1 * Math.sin(theta));
                } else {
                    hit1 = lineBoxIntersection(x2, y2, theta - Math.PI, x1, y1, size1.getX(), size1.getY(), rotation1);
                }

                if (circle2) {
                    double r2 = size2.getX() / 2;
                    hit2 = new Point2D(x2 - r2 * Math.cos(theta), y2 - r2 * Math.sin(theta));
                } else {
                    hit2 = lineBoxIntersection(x1, y1, theta, x2, y2, size2.getX(), size2.getY(), rotation2);
                }

                return (hit1.getY() + hit2.getY()) / 2;

            }

            public double getRotation() {

                double x1 = goo1.getAttribute("pos").positionValue().getX();
                double y1 = -goo1.getAttribute("pos").positionValue().getY();

                double x2 = goo2.getAttribute("pos").positionValue().getX();
                double y2 = -goo2.getAttribute("pos").positionValue().getY();

                return Math.PI / 2 + Renderer.angleTo(new Point2D(x1, y1), new Point2D(x2, y2));

            }

            public double getWidth() {
                return 0.1;
            }

            public double getHeight() {

                if (goo1 == null || goo2 == null) return 0;

                double x1 = goo1.getAttribute("pos").positionValue().getX();
                double y1 = -goo1.getAttribute("pos").positionValue().getY();
                double rotation1 = -Math.toRadians(goo1.getAttribute("angle").doubleValue());

                double x2 = goo2.getAttribute("pos").positionValue().getX();
                double y2 = -goo2.getAttribute("pos").positionValue().getY();
                double rotation2 = -Math.toRadians(goo2.getAttribute("angle").doubleValue());

                Position size1 = new Position(0.1, 0.1); // goo1.getBall() == null ? new Position(30, 30) : new Position(goo1.getBall().getShapeSize(), goo1.getBall().getShapeSize2());
                boolean circle1 = true; // goo1.getBall() == null || goo1.getBall().getShapeType().equals("circle");

                Position size2 = new Position(0.1, 0.1); // goo2.getBall() == null ? new Position(30, 30) : new Position(goo2.getBall().getShapeSize(), goo2.getBall().getShapeSize2());
                boolean circle2 = true; // goo2.getBall() == null || goo2.getBall().getShapeType().equals("circle");

                double theta = Renderer.angleTo(new Point2D(x1, y1), new Point2D(x2, y2));

                Point2D hit1;
                Point2D hit2;

                if (circle1) {
                    double r1 = size1.getX() / 2;
                    hit1 = new Point2D(x1 + r1 * Math.cos(theta), y1 + r1 * Math.sin(theta));
                } else {
                    hit1 = lineBoxIntersection(x2, y2, theta - Math.PI, x1, y1, size1.getX(), size1.getY(), rotation1);
                }

                if (circle2) {
                    double r2 = size2.getX() / 2;
                    hit2 = new Point2D(x2 - r2 * Math.cos(theta), y2 - r2 * Math.sin(theta));
                } else {
                    hit2 = lineBoxIntersection(x1, y1, theta, x2, y2, size2.getX(), size2.getY(), rotation2);
                }

                return Math.hypot(hit2.getY() - hit1.getY(), hit2.getX() - hit1.getX()) - 0.2;

            }

            public double getEdgeSize() {
                return 0;
            }
            public boolean isEdgeOnly() {
                return false;
            }

            public double getDepth(){
                return -0.00000001;
            }

            public Paint getBorderColor() {
                return new Color(0.0, 0.0, 0.0, 0.0);
            }

            public Paint getColor() {

                double length = getHeight();

                double minSize = 0; // strand.getAttribute("minlen").doubleValue();
                double maxSize = 100000; // strand.getAttribute("maxlen2").doubleValue();

                if (length > maxSize) return new Color(1.0, 0.0, 0.0, 1.0);
                if (length < minSize) return new Color(0.0, 0.0, 1.0, 1.0);
                else return new Color(0.5, 0.5, 0.5, 1.0);

            }

            public boolean isVisible() {
                return LevelManager.getLevel().getVisibilitySettings().getShowGoos() > 0 && goo1.visibilityFunction() && goo2.visibilityFunction();
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

}
