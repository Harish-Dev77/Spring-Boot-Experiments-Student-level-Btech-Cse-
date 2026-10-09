package com.example.realtime;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

/*
 * EXPERIMENT 11: Real-time Notifications using Server-Sent Events (SSE)
 * 
 * SSE allows the server to push real-time updates to the client over a single HTTP connection.
 */
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    // Thread-safe list to hold all active client connections
    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    /*
     * 1. Client connects to this endpoint to listen for notifications.
     * Returns an SseEmitter object which keeps the HTTP connection open.
     */
    @GetMapping(value = "/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE); // Infinite timeout
        emitters.add(emitter);

        // Remove emitter from the list when client disconnects or times out
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        emitter.onError((e) -> emitters.remove(emitter));

        return emitter;
    }

    /*
     * 2. Another client or system calls this endpoint to broadcast a message
     * to ALL subscribed clients in real-time.
     */
    @PostMapping("/send")
    public String sendNotification(@RequestParam String message) {
        for (SseEmitter emitter : emitters) {
            try {
                // Send the event to the connected client
                emitter.send(SseEmitter.event().name("notification").data(message));
            } catch (IOException e) {
                // If sending fails (e.g., client disconnected suddenly), remove the dead emitter
                emitters.remove(emitter);
            }
        }
        return "Notification sent to " + emitters.size() + " subscribers.";
    }
}
