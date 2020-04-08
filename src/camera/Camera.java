package camera;

import entities.FirstPersonPlayer;
import entities.Player;
import entities.ThirdPersonPlayer;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import static utils.Maths.restrict;

public class Camera{

    private float distanceFromPlayer = 20;
    private float angleAroundPlayer = 0;

    private Vector3f position = new Vector3f(0, 0, 0);
    private FocalPoint focalPoint = null;
    private ThirdPersonPlayer thirdPersonPlayer = null;
    private FirstPersonPlayer firstPersonPlayer = null;
    private float pitch;
    private float yaw;
    private float roll;

    //TODO restructure camera and how it behaves with settings

    public Camera(FocalPoint focalPoint){
        this.focalPoint = focalPoint;
        Mouse.setGrabbed(true);
    }

    public Camera(ThirdPersonPlayer thirdPersonPlayer){
        this.thirdPersonPlayer = thirdPersonPlayer;
    }

    public Camera(FirstPersonPlayer firstPersonPlayer){
        this.firstPersonPlayer = firstPersonPlayer;
        Mouse.setGrabbed(true);
    }

    public void update(){
        if(focalPoint != null) {
            position.x = focalPoint.getPosition().x;
            position.y = focalPoint.getPosition().y;
            position.z = focalPoint.getPosition().z + 20;
            pitch = restrict(pitch -= focalPoint.pitchChange, -90, 90);
            yaw += focalPoint.angleChange;
            if (yaw > 360 || yaw < -360) {
                yaw = 0;
            }
            focalPoint.setCameraPitch(pitch);
            focalPoint.setCameraYaw(yaw);
        }else if(thirdPersonPlayer != null){
            calculateZoom();
            calculatePitch();
            calculateAngleAroundPlayer();
            float horizontalDistance = calculateHorizontalDistance();
            float verticalDistance = calculateVerticalDistance();
            calculateCameraPosition(horizontalDistance, verticalDistance);
        }
    }

    private void calculateCameraPosition(float horizontalDist, float verticalDist) {
        float theta = thirdPersonPlayer.getRotY() + angleAroundPlayer;
        float offsetX = (float) (horizontalDist * Math.sin(Math.toRadians(theta)));
        float offsetZ = (float) (horizontalDist * Math.cos(Math.toRadians(theta)));
        position.x = thirdPersonPlayer.getPosition().x - offsetX;
        position.z = thirdPersonPlayer.getPosition().z - offsetZ;
        position.y = thirdPersonPlayer.getPosition().y + verticalDist + 10;
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
        if(thirdPersonPlayer != null){
            return thirdPersonPlayer;
        }else if(focalPoint != null){
            return focalPoint;
        }else if(firstPersonPlayer != null){
            return firstPersonPlayer;
        }
        return null;
    }
}
