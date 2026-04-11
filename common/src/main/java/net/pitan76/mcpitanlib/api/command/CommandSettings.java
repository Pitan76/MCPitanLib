package net.pitan76.mcpitanlib.api.command;

import net.minecraft.server.permissions.Permission;
import net.minecraft.server.permissions.PermissionLevel;
import net.minecraft.commands.CommandSourceStack;

public class CommandSettings {
    private int permissionLevel = -1;
    private ICustom iCustom = null;

    private Permission.HasCommandLevel _level = null;

    public boolean requires(CommandSourceStack source) {
        if (customRequires(source)) {
            if (permissionLevel == -1) return true;
            if (_level == null)
                _level = new Permission.HasCommandLevel(PermissionLevel.byId(permissionLevel));

            return source.permissions().hasPermission(_level);
        }
        return false;
    }

    private boolean customRequires(CommandSourceStack source) {
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
        boolean custom(CommandSourceStack source);
    }
}
