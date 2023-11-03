package net.nextfight.socketserver.playerdata;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


@Getter
public class ClientPlayer {
    private final String uuid;
    @Setter @Accessors(chain = true) private String name;
    @Setter @Accessors(chain = true) private Rank rank;
    @Setter @Accessors(chain = true) private Cape[] unlockedCapes;
    @Setter @Accessors(chain = true) private Cape selectedCape;

    public ClientPlayer(String uuid) {
        this.uuid = uuid;
    }
}
