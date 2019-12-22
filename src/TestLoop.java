import camera.Camera;
import camera.FocalPoint;
import models.CubeGenerator;
import models.TexturedModel;
import entities.Entity;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import render_engine.DisplayManager;
import render_engine.Loader;
import render_engine.MasterRenderer;
import static utils.CubeType.GRASS;
import static utils.CubeType.DIRT;
import static utils.CubeType.STONE;
import static utils.CubeType.IRON_ORE;
import static utils.CubeType.DIAMOND_ORE;
import static utils.CubeType.GOLD_ORE;
import static utils.CubeType.TREE_BARK;
import static utils.CubeType.TREE_LEAF;

public class TestLoop {

    public static void main(String[] args){
        DisplayManager.createDisplay();
        Loader loader = new Loader();
        MasterRenderer renderer = new MasterRenderer();
        FocalPoint focalPoint = new FocalPoint(new Vector3f(25, 25, 25));
        Camera camera = new Camera(focalPoint);
        CubeGenerator cubeGenerator = new CubeGenerator();

        Entity stoneCube = new Entity(cubeGenerator.addCube(STONE), new Vector3f(0, 0, 0), 0, 0, 0, 1);

        while(!Display.isCloseRequested()){
            focalPoint.move();
            camera.update();

            renderer.processEntity(stoneCube);

            renderer.render(camera);
            DisplayManager.updateDisplay();
        }
        renderer.clean();
        loader.clean();
        DisplayManager.closeDisplay();
    }
}
