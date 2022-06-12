package io.github.infinitepower563.simplenet.net;

import io.github.infinitepower563.simplenet.exception.HttpException;
import io.github.infinitepower563.simplenet.util.JsonMapper;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A simple class to store an HTTP request.
 *
 * @author InfinitePower563
 * @since v1
 */
public class HttpRequest {
    /**
     * Encodes a parameter correctly. Source: <a href="https://www.baeldung.com/java-http-request">https://www.baeldung.com/java-http-request</a>
     * @author baeldung
     * @since v1
     */
    static class ParameterStringBuilder {
        public static String getParamsString(Map<String, String> params) {
            StringBuilder result = new StringBuilder();

            for (Map.Entry<String, String> entry : params.entrySet()) {
                result.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
                result.append("&");
            }

            String resultString = result.toString();
            return resultString.length() > 0
                    ? resultString.substring(0, resultString.length() - 1)
                    : resultString;
        }
    }
    private RequestType type;
    private String target;
    private Map<String,String> parameters;

    public HttpRequest(RequestType type, String target, Map<String,String> parameters) {
        this.type = type;
        this.target = target;
        this.parameters = parameters;
    }

    /**
     *
     * @param requestHeaders The number of request headers that you want to use. Set to 0 if you do not want any request headers.
     * @param hKey The request headers' key values.
     * @param hVal The request headers' value values.
     * @return The result of the request. Excludes response headers.
     * @throws HttpException If an error occurs during the operation.
     */
    public String performRequest(int requestHeaders, String[] hKey, String[] hVal) throws HttpException {
        if (type == null) throw new HttpException("An error occurred while performing an HTTP request.", new NullPointerException("Field \"type\" is null"));
        if (target == null) throw new HttpException("An error occurred while performing an HTTP request.", new NullPointerException("Field \"target\" is null"));
        try {
            HttpURLConnection request = (HttpURLConnection) new URL(target).openConnection();
            request.setRequestMethod(type.str());
            request.connect();

            for (int i = 0; i < requestHeaders; i++) {
                request.setRequestProperty(hKey[i], hVal[i]);
            }
            if (!((parameters == null) || (parameters.size() > 0))) {
                request.setDoOutput(true);
                DataOutputStream dout = new DataOutputStream(request.getOutputStream());
                dout.writeBytes(ParameterStringBuilder.getParamsString(parameters));
                dout.writeUTF("==sn-close-str==");
                dout.flush();
                dout.close();
            }

            int status = request.getResponseCode();
            InputStreamReader istream;
            if (status >= 200 && status < 300) {
                istream = new InputStreamReader(request.getInputStream());
            } else {
                istream = new InputStreamReader(request.getErrorStream());
            }
            return (new BufferedReader(istream)).lines().collect(Collectors.joining("\n"));
        } catch (Exception e) {
            throw new HttpException("An error occurred while performing an HTTP request.", e);
        }
    }
    /**
     * Performs an HTTP request, but returns the value as a {@link JSONObject}.
     * @param requestHeaders The number of request headers that you want to use. Set to 0 if you do not want any request headers.
     * @param hKey The request headers' key values.
     * @param hVal The request headers' value values.
     * @return The result of the request. Excludes response headers.
     * @throws HttpException If an error occurs during the operation.
     */
    public JSONObject performJSONRequest(int requestHeaders, String[] hKey, String[] hVal) throws HttpException {
        if (type == null) throw new HttpException("An error occurred while performing an HTTP request.", new NullPointerException("Field \"type\" is null"));
        if (target == null) throw new HttpException("An error occurred while performing an HTTP request.", new NullPointerException("Field \"target\" is null"));
        try {
            HttpURLConnection request = (HttpURLConnection) new URL(target).openConnection();
            for (int i = 0; i < requestHeaders; i++) {
                request.setRequestProperty(hKey[i], hVal[i]);
            }
            if (!((parameters == null) || (parameters.size() > 0))) {
                request.setDoOutput(true);
                DataOutputStream dout = new DataOutputStream(request.getOutputStream());
                dout.writeBytes(ParameterStringBuilder.getParamsString(parameters));
                dout.flush();
                dout.close();
            }
            request.setRequestMethod(type.str());
            int status = request.getResponseCode();
            InputStreamReader istream;
            if (status >= 200 && status < 300) {
                istream = new InputStreamReader(request.getInputStream());
            } else {
                istream = new InputStreamReader(request.getErrorStream());
            }
            return JsonMapper.mapResultToJson((new BufferedReader(istream)).lines().collect(Collectors.joining("\n")));
        } catch (Exception e) {
            throw new HttpException("An error occurred while performing an HTTP request.", e);
        }
    }
    public HttpRequest() {}
    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
    public void setTarget(String target) {
        this.target = target;
    }
    public void setType(RequestType type) {
        this.type = type;
    }
    public Map<String, String> getParameters() {
        return parameters;
    }
    public RequestType getType() {
        return type;
    }
    public String getTarget() {
        return target;
    }
}
