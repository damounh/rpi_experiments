package com.rpi.experiments.Sensors.MotionTracker;

import java.io.IOException;
import java.util.List;

import com.pi4j.io.i2c.I2CFactory;

public interface MultiAxisMotionSensor {
    void initializeSensor() throws IOException, I2CFactory.UnsupportedBusNumberException;

    void configureDeviceRegisters() throws IOException;

    List<MotionTrackers> getSupportedMotionTrackers();

    byte readValue(final MotionTrackers motionTrackerType, final Axis axis) throws UnsupportedMotionTrackerException, IOException;
}
