package camera;

import entities.Player;
import models.TexturedModel;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;
import render_engine.DisplayManager;
import world.terrains.FlatTerrain;

import static utils.PlayerID.focalPoint;

public class FocalPoint extends Player {

    private Vector3f position;

    protected float angleChange = 0;
    protected float pitchChange = 0;

    private float cameraYaw = 0;
    private float cameraPitch = 0;

    private static final float RUN_SPEED = 10;

    private float current_speed = 0;

    public FocalPoint(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        super(model, position, rotX, rotY, rotZ, scale);
        this.position = position;
        this.playerID = focalPoint;
    }

    @Override
    public void move(FlatTerrain terrain){
        checkInputs();
        float distance = current_speed * DisplayManager.getFrameTimeSeconds();
        float dx = (float) (distance * Math.sin(Math.toRadians(-cameraYaw)));
        float dz = (float) (distance * Math.cos(Math.toRadians(-cameraYaw)));
        float dy = (float) (distance * Math.sin(Math.toRadians(cameraPitch)));
        increasePosition(dx, dy, dz);
    }

    @Override
    protected void checkInputs(){
        if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            current_speed = -RUN_SPEED;
        }else if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
            current_speed = RUN_SPEED;
        }else{
            current_speed *= 0.92;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
            Mouse.setGrabbed(false);
        }else if(Mouse.isButtonDown(0)){
            Mouse.setGrabbed(true);
        }
        float mouseX = Mouse.getDX();
        float mouseY = Mouse.getDY();
        angleChange = mouseX * 0.05f;
        pitchChange = mouseY * 0.15f;
    }

    @Override
    protected void checkCollisions() {}

    public Vector3f getPosition() {
        return position;
    }

    protected void setCameraYaw(float yaw){
        cameraYaw = yaw;
    }

    protected void setCameraPitch(float pitch){
        cameraPitch = pitch;
    }

    public void increasePosition(float dx, float dy, float dz){
        this.position.x += dx;
        this.position.y += dy;
        this.position.z += dz;
    }
}
