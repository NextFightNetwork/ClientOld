package net.nextfight.socketserver.playerdata;

public enum Cape {
    NONE("", 0),
    BETA("beta.png", 0),
    TEAM("team.png", 0),
    CONTENT_PLUS("content_plus.png", 0),
    CONTENT("content.png", 0),
    MAXIMCLIENT("maximclient.png", 0);

    public final String fileName;
    public final int coinPrice;
    Cape(String fileName, int coinPrice) {
        this.fileName = fileName;
        this.coinPrice = coinPrice;
    }
}
