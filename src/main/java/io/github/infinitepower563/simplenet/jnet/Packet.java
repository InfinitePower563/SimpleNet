package io.github.infinitepower563.simplenet.jnet;

public class Packet {
    String[] headers;
    String data;
    public Packet(String[] headers, String data) {
        this.headers = headers;
        this.data = data;
    }
    public String fromData() {
        StringBuilder sb = new StringBuilder();
        if (headers != null) {
            for (String header : headers) {
                sb.append(header);
                sb.append(";");
            }
        }
        sb.append(":::").append(data).append("\n");
        return sb.toString();
    }
    public Packet(String data) {
        if (!data.contains(":::") || !data.contains(";")) {
        } else {
            String[] split = data.split(":::");
            this.headers = split[0].split(";");
            this.data = split[1];
        }
    }
}
