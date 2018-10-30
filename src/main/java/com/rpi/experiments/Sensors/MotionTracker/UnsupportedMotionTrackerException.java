package com.rpi.experiments.Sensors.MotionTracker;

public class UnsupportedMotionTrackerException extends Exception {
    private static final long serialVersionUID = 1L;

    public UnsupportedMotionTrackerException(final String message) {
        super(message);
    }
}
