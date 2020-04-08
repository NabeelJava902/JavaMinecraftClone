package utils.exceptions;

import textures.ModelTexture;
import textures.TextureSource;

public class NullTexturedCubeException extends RuntimeException{

    public NullTexturedCubeException(String message){
        super(message);
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }

    public ModelTexture setDefaultTexture(){
        return TextureSource.grassTexture;
    }
}
