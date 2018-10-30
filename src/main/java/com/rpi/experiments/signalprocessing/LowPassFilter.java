package com.rpi.experiments.signalprocessing;

public class LowPassFilter implements Filter {
    private final float smoothing;
    private double smoothed = 0;
    private long lastUpdate;

    public LowPassFilter(final float smoothing) {
        this.smoothing = smoothing;
        this.lastUpdate = System.currentTimeMillis();
    }

    @Override
    public double filter(final double newValue) {
        long now = System.currentTimeMillis();
        long elapsedTime = now - lastUpdate;
        float smoothingFactor = elapsedTime / (smoothing + elapsedTime);
        smoothed = smoothed + smoothingFactor * (newValue - smoothed);
        return smoothed;
    }
}
