package dev.chicle.amongsteves.gamemanager.player;

public enum PlayerColor {
    BLACK("", ""),
    BLUE("", ""),
    BROWN("", ""),
    CYAN("", ""),
    GREEN("", ""),
    LIME("", ""),
    ORANGE("", ""),
    PINK("", ""),
    PURPLE("", ""),
    RED("", ""),
    WHITE("", ""),
    YELLOW("", "");


    private final String texture;
    private final String signature;

    PlayerColor(String texture, String signature) {
        this.texture = texture;
        this.signature = signature;
    }

    public String getTexture() {
        return texture;
    }

    public String getSignature() {
        return signature;
    }
}
