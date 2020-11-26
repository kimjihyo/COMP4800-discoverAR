package com.example.discoverar.rendering;

import android.content.Context;

import com.example.discoverar.ScanActivity;
import com.example.discoverar.models.Discovery;
import com.google.ar.core.Anchor;
import com.google.ar.core.AugmentedImage;
import com.google.ar.core.Pose;
import com.example.discoverar.common.rendering.ObjectRenderer;
import com.example.discoverar.common.rendering.ObjectRenderer.BlendMode;
import java.io.IOException;
import java.util.ArrayList;

public class AugmentedImageRenderer {
    private static final String TAG = "AugmentedImageRenderer";

    private static final float TINT_INTENSITY = 0.1f;
    private static final float TINT_ALPHA = 1.0f;
    private static final int[] TINT_COLORS_HEX = {
            0x000000, 0xF44336, 0xE91E63, 0x9C27B0, 0x673AB7, 0x3F51B5, 0x2196F3, 0x03A9F4, 0x00BCD4,
            0x009688, 0x4CAF50, 0x8BC34A, 0xCDDC39, 0xFFEB3B, 0xFFC107, 0xFF9800,
    };


    private final ObjectRenderer imageFrameUpperLeft = new ObjectRenderer();
    private final ObjectRenderer imageFrameUpperRight = new ObjectRenderer();
    private final ObjectRenderer imageFrameLowerLeft = new ObjectRenderer();
    private final ObjectRenderer imageFrameLowerRight = new ObjectRenderer();

    private Discovery[] discoveries;
    private ArrayList<ObjectRenderer> shapes = new ArrayList<>();
    private ArrayList<Float> xCoordinates = new ArrayList<>();
    private ArrayList<Float> yCoordinates = new ArrayList<>();
    private int baseX;
    private int baseY;

    public AugmentedImageRenderer() {}

    public void createOnGlThread(Context context) throws IOException {
        discoveries = ((ScanActivity)context).getCurrentDiscoveries();

        for (Discovery discovery : discoveries) {
            String type = discovery.getType();
            String shape = discovery.getShape();
            if (type.equals("TRIGGER_IMAGE")) {
                // store the base coordinates as floats
                baseX = discovery.getX() + 150;
                baseY = discovery.getY() + 150;
            } else {
                if (shape.equals("TRIANGLE")) {
                    // CREATE A TRIANGLE ON GL THREAD
                    ObjectRenderer triangle = new ObjectRenderer();
                    triangle.createOnGlThread(context, "models/shape_triangle.obj", "models/shapebase_orange.png");
                    triangle.setMaterialProperties(0.0f, 3.5f, 1.0f, 6.0f);
                    triangle.setBlendMode(BlendMode.AlphaBlending);
                    shapes.add(triangle);
                    xCoordinates.add((float) discovery.getX());
                    yCoordinates.add((float) discovery.getY());
                } else if (shape.equals("RECTANGLE")) {
                    // CREATE A RECTANGLE ON GL THREAD
                    ObjectRenderer rectangle = new ObjectRenderer();
                    rectangle.createOnGlThread(context, "models/shape_rectangle.obj", "models/shapebase_orange.png");
                    rectangle.setMaterialProperties(0.0f, 3.5f, 1.0f, 6.0f);
                    rectangle.setBlendMode(BlendMode.AlphaBlending);
                    shapes.add(rectangle);
                    xCoordinates.add((float) discovery.getX() + 100);
                    yCoordinates.add((float) discovery.getY() + 37.5f);
                } else if (shape.equals("CIRCLE")) {
                    // CREATE A CIRCLE ON GL THREAD
                    ObjectRenderer circle = new ObjectRenderer();
                    circle.createOnGlThread(context, "models/shape_circle.obj", "models/shapebase_orange.png");
                    circle.setMaterialProperties(0.0f, 3.5f, 1.0f, 6.0f);
                    circle.setBlendMode(BlendMode.AlphaBlending);
                    shapes.add(circle);
                    xCoordinates.add((float) discovery.getX());
                    yCoordinates.add((float) discovery.getY());
                }
            }
        }
    }

    public void draw(
            float[] viewMatrix,
            float[] projectionMatrix,
            AugmentedImage augmentedImage,
            Anchor centerAnchor,
            float[] colorCorrectionRgba) {
        float[] tintColor =
                convertHexToColor(TINT_COLORS_HEX[augmentedImage.getIndex() % TINT_COLORS_HEX.length]);

        ArrayList<Pose> localBoundaries = new ArrayList<>();

        for (int i = 0; i < shapes.size(); i++) {
            float x = (float) xCoordinates.get(i);
            float y = (float) yCoordinates.get(i);
            Pose rotation = Pose.makeRotation(1.5708f / 2, 0f,-0.0f,centerAnchor.getPose().qw());
            Pose pose = Pose.makeTranslation(
                    ((x - baseX) / 300) * augmentedImage.getExtentX(),
                    0.0f,
                    ((y - baseY) / 220) * augmentedImage.getExtentZ());
            pose.compose(rotation);
            localBoundaries.add(pose);
        }

        Pose anchorPose = centerAnchor.getPose();
        int numPoses = localBoundaries.size();
        Pose[] worldBoundaryPoses = new Pose[numPoses];
        for (int i = 0; i < numPoses; ++i) {
            worldBoundaryPoses[i] = anchorPose.compose(localBoundaries.get(i));
        }

        float scaleFactor = 0.35f;
        float rotationAngle = 270f;
        float[] modelMatrix = new float[16];

        for (int i = 0; i < numPoses; i++) {
            worldBoundaryPoses[i].toMatrix(modelMatrix, 0);
            shapes.get(i).updateModelMatrix(modelMatrix, scaleFactor, rotationAngle);
            shapes.get(i).draw(viewMatrix, projectionMatrix, colorCorrectionRgba, tintColor);
            System.out.println("-------------------" + i + "-----------------");

        }
    }

    private static float[] convertHexToColor(int colorHex) {
        // colorHex is in 0xRRGGBB format
        float red = ((colorHex & 0xFF0000) >> 16) / 255.0f * TINT_INTENSITY;
        float green = ((colorHex & 0x00FF00) >> 8) / 255.0f * TINT_INTENSITY;
        float blue = (colorHex & 0x0000FF) / 255.0f * TINT_INTENSITY;
        return new float[] {red, green, blue, TINT_ALPHA};
    }
}
