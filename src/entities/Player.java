package entities;

import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;
import utils.PlayerID;
import world.terrains.FlatTerrain;

public abstract class Player extends Entity{

    protected PlayerID playerID = null;

    public Player(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        super(model, position, rotX, rotY, rotZ, scale);
    }

    public abstract void move(FlatTerrain terrain);

    protected abstract void checkInputs();

    protected abstract void checkCollisions();

    public void teleportBack(FlatTerrain terrain){
        this.setPosition(new Vector3f(terrain.getOriginX(), terrain.getOriginY()+this.getScale(), terrain.getOriginZ()));
    }

    public PlayerID getPlayerID(){
        return playerID;
    }
}
