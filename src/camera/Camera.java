package camera;

import entities.Player;
import entities.ThirdPersonPlayer;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import static utils.PlayerID.thirdPersonPlayer;
import static utils.PlayerID.focalPoint;

import static utils.Maths.restrict;

public class Camera{

    private float distanceFromPlayer = 20;
    private float angleAroundPlayer = 0;

    private Vector3f position = new Vector3f(0, 0, 0);
    private FocalPoint focalPointObject = null;
    private ThirdPersonPlayer thirdPersonPlayerObject = null;
    private Player player = null;
    private float pitch;
    private float yaw;
    private float roll;

    public void update(){
        if(player.getPlayerID() == focalPoint) {
            focalPointObject = (FocalPoint) player;
            position.x = focalPointObject.getPosition().x;
            position.y = focalPointObject.getPosition().y;
            position.z = focalPointObject.getPosition().z + 20;
            pitch = restrict(pitch -= focalPointObject.pitchChange, -90, 90);
            yaw += focalPointObject.angleChange;
            if (yaw > 360 || yaw < -360) {
                yaw = 0;
            }
            focalPointObject.setCameraPitch(pitch);
            focalPointObject.setCameraYaw(yaw);
        }else if(player.getPlayerID() == thirdPersonPlayer){
            thirdPersonPlayerObject = (ThirdPersonPlayer) player;
            calculateZoom();
            calculatePitch();
            calculateAngleAroundPlayer();
            float horizontalDistance = calculateHorizontalDistance();
            float verticalDistance = calculateVerticalDistance();
            calculateCameraPosition(horizontalDistance, verticalDistance);
        }
    }

    private void calculateCameraPosition(float horizontalDist, float verticalDist) {
        float theta = thirdPersonPlayerObject.getRotY() + angleAroundPlayer;
        float offsetX = (float) (horizontalDist * Math.sin(Math.toRadians(theta)));
        float offsetZ = (float) (horizontalDist * Math.cos(Math.toRadians(theta)));
        position.x = thirdPersonPlayerObject.getPosition().x - offsetX;
        position.z = thirdPersonPlayerObject.getPosition().z - offsetZ;
        position.y = thirdPersonPlayerObject.getPosition().y + verticalDist + 10;
        yaw = 180 - theta;
    }

    private float calculateHorizontalDistance() {
        return (float) (distanceFromPlayer * Math.cos(Math.toRadians(pitch)));
    }

    private float calculateVerticalDistance() {
        return (float) (distanceFromPlayer * Math.sin(Math.toRadians(pitch)))-5f;
    }

    private void calculateZoom() {
        float zoomLevel = Mouse.getDWheel() * 0.1f;
        distanceFromPlayer -= zoomLevel;
        if(distanceFromPlayer < 15) {
            distanceFromPlayer = 15;
        }else if(distanceFromPlayer > 90) {
            distanceFromPlayer = 90;
        }
    }

    private void calculatePitch() {
        if(Mouse.isButtonDown(1)) {
            float pitchChange = Mouse.getDY() * 0.1f;
            pitch -= pitchChange;
        }
    }

    private void calculateAngleAroundPlayer() {
        if(Mouse.isButtonDown(0)) {
            float angleChange = Mouse.getDX() * 0.3f;
            angleAroundPlayer -= angleChange;
        }
    }

    public Vector3f getPosition() {
        return position;
    }
    public float getPitch() {
        return pitch;
    }
    public float getYaw() {
        return yaw;
    }
    public float getRoll() {
        return roll;
    }

    public Player getPlayer(){
        if(thirdPersonPlayerObject != null){
            return thirdPersonPlayerObject;
        }else if(focalPointObject != null){
            return focalPointObject;
        }
        return null;
    }

    public void setPlayer(Player player){
        this.player = player;
    }
}
