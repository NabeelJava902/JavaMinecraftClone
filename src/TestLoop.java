import camera.Camera;
import camera.FocalPoint;
import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import render_engine.DisplayManager;
import render_engine.Loader;
import render_engine.MasterRenderer;
import textures.ModelTexture;

import java.util.Random;

public class TestLoop {

    public static void main(String[] args){
        DisplayManager.createDisplay();
        Loader loader = new Loader();
        MasterRenderer renderer = new MasterRenderer();
        Random rand = new Random();

        float[] vertices = {
                -0.5f, 0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f, 0.5f, 0f,
        };

        int[] indices = {
                0,1,3,//top left triangle (v0, v1, v3)
                3,1,2//bottom right triangle (v3, v1, v2)
        };

        float[] textureCoords = {
                0, 0,
                1, 0,
                1, 1,
                0, 1
        };

        RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
        ModelTexture texture = new ModelTexture(loader.loadTexture("grass"));
        TexturedModel texturedModel = new TexturedModel(model, texture);

        Entity[] entities = new Entity[60];
        for(int i=0; i<entities.length; i++){
            float x = (float) rand.nextInt(50);
            float y = (float) rand.nextInt(50);
            float z = (float) rand.nextInt(50);
            entities[i] = new Entity(texturedModel, new Vector3f(x, y, z), 0, 0, 0, 1);
        }

        FocalPoint focalPoint = new FocalPoint(new Vector3f(25, 25, 25));
        Camera camera = new Camera(focalPoint);

        while(!Display.isCloseRequested()){
            focalPoint.move();
            camera.update();

            for(Entity entity : entities){
                renderer.processEntity(entity);
            }

            renderer.render(camera);
            DisplayManager.updateDisplay();
        }
        renderer.clean();
        loader.clean();
        DisplayManager.closeDisplay();
    }
}
