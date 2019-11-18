import entities.Camera;
import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import render_engine.DefaultRenderer;
import render_engine.DisplayManager;
import render_engine.Loader;
import shader_programs.DefaultShader;
import textures.ModelTexture;

public class TestLoop {

    public static void main(String[] args){
        DisplayManager.createDisplay();
        Loader loader = new Loader();
        DefaultShader shader = new DefaultShader();
        DefaultRenderer renderer = new DefaultRenderer(shader);


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
        Entity entity = new Entity(texturedModel, new Vector3f(0, 0, -2.5f), 0, 0, 0, 1);
        Camera camera = new Camera();

        while(!Display.isCloseRequested()){
            renderer.prepare();
            camera.move();

            shader.start();
            shader.loadViewMatrix(camera);
            renderer.render(entity, shader);
            shader.stop();

            DisplayManager.updateDisplay();
        }
        shader.clean();
        loader.clean();
        DisplayManager.closeDisplay();
    }
}
