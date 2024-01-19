package util;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum MIMEType {
    HTML(".html", "text/html"),
    CSS(".css", "text/css"),
    JS(".js", "text/javascript"),
    PNG(".png", "image/png"),
    ICO(".ico", "image/x-icon"),
    SVG(".svg", "image/svg+xml"),
    WOFF(".woff", "font/woff"),
    TTF(".ttf", "font/ttf"),
    EOT(".eot", "font/eot"),
    WOFF2(".woff2", "font/woff2");

    private final String extension;
    private final String contentType;

    private static final Map<String, String> MIMETypeBundle = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(MIMEType::getExtension, MIMEType::getContentType))
    );

    MIMEType(String extension, String contentType) {
        this.extension = extension;
        this.contentType = contentType;
    }

    public String getExtension() {
        return this.extension;
    }

    public String getContentType() {
        return this.contentType;
    }

    public static String getMIMEType (String extension) {
        return MIMETypeBundle.get(extension);
    }
}
