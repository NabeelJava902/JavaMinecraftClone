package render_engine;

import camera.Camera;
import entities.Entity;
import entities.Light;
import models.TexturedModel;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import shader_programs.DefaultShader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterRenderer {

    private static final float FOV = 70;
    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 1000;
    private Matrix4f projectionMatrix;

    private static final float RED = 0;
    private static final float GREEN = 255;
    private static final float BLUE = 255;

    private DefaultShader defShader = new DefaultShader();
    private DefaultRenderer defRenderer;

    private Map<TexturedModel, List<Entity>> entities = new HashMap<>();

    public void render(Camera camera, Light sun){
        prepare();
        defShader.start();
        defShader.loadLight(sun);
        defShader.loadViewMatrix(camera);
        defRenderer.render(entities);
        defShader.stop();
    }

    public void render(Camera camera){
        prepare();
        defShader.start();
        defShader.loadViewMatrix(camera);
        defRenderer.render(entities);
        defShader.stop();
    }

    public void processEntity(Entity entity){
        TexturedModel model = entity.getModel();
        List<Entity> batch = entities.get(model);
        if(batch != null){
            batch.add(entity);
        }else{
            List<Entity> newBatch = new ArrayList<>();
            newBatch.add(entity);
            entities.put(model, newBatch);
        }
    }

    public MasterRenderer(){
        createProjectionMatrix();
        defRenderer = new DefaultRenderer(defShader, projectionMatrix);
    }

    public void clean(){
        defShader.clean();
    }

    private void prepare(){
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(RED, GREEN, BLUE, 1);
    }

    private void createProjectionMatrix() {
        float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
        float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
        float x_scale = y_scale / aspectRatio;
        float frustum_length = FAR_PLANE - NEAR_PLANE;

        projectionMatrix = new Matrix4f();
        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
        projectionMatrix.m33 = 0;
    }
}
