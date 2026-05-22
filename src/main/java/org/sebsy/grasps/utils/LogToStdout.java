package org.sebsy.grasps.utils;

import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

public final class LogToStdout {

    private static boolean initialized = false;

    public static synchronized void init() {
        if (initialized) return;
        initialized = true;

        Logger root = Logger.getLogger("");
        root.setUseParentHandlers(false);
        for (Handler h : root.getHandlers()) {
            root.removeHandler(h);
        }

        StreamHandler handler = new StreamHandler(System.out, new SimpleFormatter()) {
            @Override
            public synchronized void publish(java.util.logging.LogRecord record) {
                super.publish(record);
                flush();
            }
        };
        handler.setLevel(java.util.logging.Level.ALL);
        root.addHandler(handler);
    }
}
