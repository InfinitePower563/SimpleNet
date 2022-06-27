package io.github.infinitepower563.simplenet.jnet.nios;

import io.github.infinitepower563.simplenet.jnet.Packet;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Buffer {
    private final Queue<Packet> buffer;
    private final SocketPipeline pipeline;

    public Buffer(SocketPipeline pipeline) {
        buffer = new LinkedList<>();
        this.pipeline = pipeline;
    }

    public void write(Packet p) {
        buffer.offer(p);
    }
    public String[] flush() throws Exception {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Future<String[]> future = threadPool.submit(() -> pipeline.send(buffer));
        while (!future.isDone());
        String[] result = future.get();
        threadPool.shutdown();
        return result;
    }
}
