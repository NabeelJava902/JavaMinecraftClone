package render_engine;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

public class DisplayManager {

    private static ContextAttribs attribs = new ContextAttribs().withForwardCompatible(true);
    private static PixelFormat format = new PixelFormat();

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final int FPS_CAP = 120;

    //TODO set up vsync and other various opengl features for display

    public static void createDisplay(){
        try {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.create(format, attribs);
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
        GL11.glViewport(0, 0, WIDTH, HEIGHT);
        Display.setTitle("Minecraft Clone!");
    }

    public static void updateDisplay(){
        Display.sync(FPS_CAP);
        Display.update();
    }

    public static void closeDisplay(){
        Display.destroy();
    }
}
