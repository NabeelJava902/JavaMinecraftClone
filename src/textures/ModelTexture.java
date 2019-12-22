package textures;

public class ModelTexture {

    private int textureID;
    private boolean hasTransparency = false;

    protected ModelTexture(int textureID) {
        this.textureID = textureID;
    }

    public int getTextureID() {
        return textureID;
    }

    public void setHasTransparency(boolean hasTransparency) {
        this.hasTransparency = hasTransparency;
    }

    public boolean isHasTransparency() {
        return hasTransparency;
    }
}
