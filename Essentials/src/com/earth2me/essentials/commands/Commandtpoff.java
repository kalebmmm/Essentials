package com.earth2me.essentials.commands;

import com.earth2me.essentials.User;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import static com.earth2me.essentials.I18n.tl;

public class Commandtpoff extends EssentialsCommand {

    public Commandtpoff() {
        super("tpoff");
    }

    @Override
    public void run(final Server server, final User user, final String commandLabel, final String[] args) throws Exception {
        switch (args.length) {
            case 0:
                throw new NotEnoughArgumentsException();

            default: // Any amount of arguments, we only care about arg 0
                final User target = getPlayer(server, args, 0, true, true);
                final Location logout = target.getLogoutLocation();

                if (user.getWorld() != logout.getWorld() && ess.getSettings().isWorldTeleportPermissions() && !user.isAuthorized("essentials.worlds." + logout.getWorld().getName())) {
                    throw new Exception(tl("noPerm", "essentials.worlds." + logout.getWorld().getName()));
                }

                user.sendMessage(tl("teleporting", logout.getWorld().getName(), logout.getBlockX(), logout.getBlockY(), logout.getBlockZ()));
                user.getTeleport().now(logout, false, TeleportCause.COMMAND);
        }
    }




}
