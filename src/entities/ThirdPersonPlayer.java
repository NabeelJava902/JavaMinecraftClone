package entities;

import models.TexturedModel;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;
import render_engine.DisplayManager;
import utils.PlayerID;
import world.terrains.FlatTerrain;

import static utils.PlayerID.thirdPersonPlayer;

public class ThirdPersonPlayer extends Player{

    private static final float RUN_SPEED = 20; //units per second
    private static final float TURN_SPEED = 160; //degrees per second
    private static final float FALL_GRAVITY = 0.05f;
    private static final float STATIC_GRAVITY = -60;
    private static final float JUMP_HEIGHT = 20;

    private float currentSpeed = 0;
    private float currentTurnSpeed = 0;
    private float upwardsSpeed = 0;

    boolean isInAir = false;
    boolean offTerrain = false;

    private FlatTerrain terrain;

    public ThirdPersonPlayer(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        super(model, position, rotX, rotY, rotZ, scale);
        this.playerID = thirdPersonPlayer;
    }

    @Override
    public void move(FlatTerrain terrain){
        this.terrain = terrain;
        checkInputs();
        super.increaseRotation(0, currentTurnSpeed * DisplayManager.getFrameTimeSeconds(), 0);
        float distance = currentSpeed * DisplayManager.getFrameTimeSeconds();
        float dx = (float) (distance * Math.sin(Math.toRadians(super.getRotY())));
        float dz = (float) (distance * Math.cos(Math.toRadians(super.getRotY())));
        super.increasePosition(dx, 0, dz);
        upwardsSpeed += STATIC_GRAVITY * DisplayManager.getFrameTimeSeconds();
        super.increasePosition(0, upwardsSpeed * DisplayManager.getFrameTimeSeconds(), 0);
        checkCollisions();
    }

    private void jump(){
        upwardsSpeed = JUMP_HEIGHT;
    }

    @Override
    protected void checkInputs(){
        if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            currentSpeed = RUN_SPEED;
            if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
                currentSpeed *= 2;
            }
        }else if(Keyboard.isKeyDown(Keyboard.KEY_S)){
            currentSpeed = -RUN_SPEED;
        }else{
            currentSpeed = 0;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_A)){
            currentTurnSpeed = TURN_SPEED;
        }else if(Keyboard.isKeyDown(Keyboard.KEY_D)){
            currentTurnSpeed = -TURN_SPEED;
        }else{
            currentTurnSpeed = 0;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
            if(!isInAir){
                jump();
            }
            isInAir = true;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_R)){
            teleportBack(terrain);
            offTerrain = false;
        }
    }

    @Override
    protected void checkCollisions(){
        float terrainHeight = terrain.getOriginY();
        if(!offTerrain) {
            if (getPosition().y < terrainHeight + this.getScale()) {
                if ((getPosition().x > terrain.getOriginX() + terrain.getLength()) || (getPosition().x < terrain.getOriginX())
                        || (getPosition().z > terrain.getOriginZ() + terrain.getWidth()) || (getPosition().z < terrain.getOriginZ())) {
                    offTerrain = true;
                } else {
                    upwardsSpeed = 0;
                    isInAir = false;
                    super.getPosition().y = terrainHeight + this.getScale();
                }
            }
        } upwardsSpeed -= 1f;
    }
}
