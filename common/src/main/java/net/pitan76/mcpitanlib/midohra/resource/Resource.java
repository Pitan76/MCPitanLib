package net.pitan76.mcpitanlib.midohra.resource;

import net.pitan76.mcpitanlib.api.util.LoggerUtil;
import net.pitan76.mcpitanlib.api.util.ResourceUtil;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Resource {
    private final net.minecraft.resource.Resource resource;

    protected Resource(net.minecraft.resource.Resource resource) {
        this.resource = resource;
    }

    public static Resource of(net.minecraft.resource.Resource resource) {
        return new Resource(resource);
    }

    public net.minecraft.resource.Resource getRaw() {
        return resource;
    }

    public net.minecraft.resource.Resource toMinecraft() {
        return getRaw();
    }

    public BufferedReader getReader() throws IOException {
        return resource.getReader();
    }

    public String getPackId() {
        return resource.getResourcePackName();
    }

    public InputStream getInputStream() {
        try {
            return ResourceUtil.getInputStream(resource);
        } catch (IOException e) {
            LoggerUtil.error(LoggerUtil.getLogger(), "Failed to read resource: " + e.getMessage());
            return null;
        }
    }

    public void close() {
        try {
            ResourceUtil.close(resource);
        } catch (IOException e) {
            LoggerUtil.error(LoggerUtil.getLogger(), "Failed to close resource: " + e.getMessage());
        }
    }

    public String getContent() throws IOException {
        String content = IOUtils.toString(getInputStream(), StandardCharsets.UTF_8);
        close();
        return content;
    }

    public String getContent(String encoding) throws IOException {
        String content = IOUtils.toString(getInputStream(), encoding);
        close();
        return content;
    }

    public String getContent(Charset encoding) throws IOException {
        String content = IOUtils.toString(getInputStream(), encoding);
        close();
        return content;
    }
}
