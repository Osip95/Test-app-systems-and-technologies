package com.example.energyresourcestestapp.shared;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AsyncRequester {
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    Future<?> future;
    public void startAsyncTask(Runnable runnable) {
        future = executor.submit(runnable);
    }
    public void stopAsyncTask(){
        if (future!=null) {
            future.cancel(true);
        }
    }
}
