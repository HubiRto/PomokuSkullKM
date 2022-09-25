package pl.pomoku.pomokuczaszki.menusystem;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlayerMenuUtility {
    private Player owner;

    private OfflinePlayer playerWithSkull;

    public OfflinePlayer getPlayerWithSkull() {
        return playerWithSkull;
    }

    public void setPlayerWithSkull(OfflinePlayer playerWithSkull) {
        this.playerWithSkull = playerWithSkull;
    }

    public PlayerMenuUtility(Player owner){
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
