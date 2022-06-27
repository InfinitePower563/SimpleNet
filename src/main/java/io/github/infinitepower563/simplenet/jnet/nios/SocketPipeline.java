package io.github.infinitepower563.simplenet.jnet.nios;

import io.github.infinitepower563.simplenet.jnet.JNetHttpRequest;
import io.github.infinitepower563.simplenet.jnet.Packet;

import java.io.IOException;
import java.util.Queue;

public record SocketPipeline(String server, int port) {

    public String[] send(Queue<Packet> p) throws IOException {
        int depth = p.size();
        Packet[] packets = new Packet[depth];
        for (int i = 0; i < depth; i++) {
            packets[i] = p.poll();
        }
        String[] results = new String[depth];
        for (int i = 0; i < depth; i++) {
            results[i] = new JNetHttpRequest(server, port).sendRequest(packets[i]);
        }
        return results;
    }
}
