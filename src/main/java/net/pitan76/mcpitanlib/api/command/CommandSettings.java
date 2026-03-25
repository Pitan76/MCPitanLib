package net.pitan76.mcpitanlib.api.command;

import net.minecraft.command.permission.Permission;
import net.minecraft.command.permission.PermissionLevel;
import net.minecraft.server.command.ServerCommandSource;

public class CommandSettings {
    private int permissionLevel = -1;
    private ICustom iCustom = null;

    private Permission.Level _level = null;

    public boolean requires(ServerCommandSource source) {
        if (customRequires(source)) {
            if (permissionLevel == -1) return true;
            if (_level == null)
                _level = new Permission.Level(PermissionLevel.fromLevel(permissionLevel));

            return source.getPermissions().hasPermission(_level);
        }
        return false;
    }

    private boolean customRequires(ServerCommandSource source) {
        if (iCustom == null) return true;
        return iCustom.custom(source);
    }

    public CommandSettings permissionLevel(int level) {
        this.permissionLevel = level;
        return this;
    }

    public CommandSettings custom(ICustom iCustom) {
        this.iCustom = iCustom;
        return this;
    }

    @FunctionalInterface
    public interface ICustom {
        boolean custom(ServerCommandSource source);
    }
}
