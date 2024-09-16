package net.pitan76.mcpitanlib.core.command;

import net.minecraft.server.command.ServerCommandSource;

public class CommandResult {
    private boolean isSuccess = false;
    private String message = "";
    private int result = 0;
    private ErrorType errorType = ErrorType.NONE;
    private ServerCommandSource source;

    public CommandResult() {

    }

    public CommandResult(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public int getResult() {
        return result;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setSource(ServerCommandSource source) {
        this.source = source;
    }

    public ServerCommandSource getSource() {
        return source;
    }

    public boolean hasError() {
        return errorType != ErrorType.NONE;
    }

    public enum ErrorType {
        NONE,
        COMMAND_SYNTAX_ERROR,
        COMMAND_NOT_FOUND,
        COMMAND_PERMISSION_ERROR,
        COMMAND_UNKNOWN_ERROR,
        RUNTIME_ERROR
    }
}
