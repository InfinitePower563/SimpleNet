package io.github.infinitepower563.simplenet.net;

/**
 * HTTP Request Types.
 *
 * @since v1
 * @author InfinitePower563
 */
public enum RequestType {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    HEAD("HEAD"),
    DELETE("DELETE"),
    PATCH("PATCH"),
    OPTIONS("OPTIONS");
    private final String name;
    RequestType(String x) {
        name = x;
    }
    String str() {
        return name;
    }
}
