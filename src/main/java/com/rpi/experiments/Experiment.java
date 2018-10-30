package com.rpi.experiments;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.i2c.I2CFactory;
import com.rpi.experiments.Actions.PinToggling;
import com.rpi.experiments.Sensors.MotionTracker.Axis;
import com.rpi.experiments.Sensors.MotionTracker.MPU9265.MPU9265MotionSensor;
import com.rpi.experiments.Sensors.MotionTracker.MotionTrackers;
import com.rpi.experiments.Sensors.MotionTracker.MultiAxisMotionSensor;
import com.rpi.experiments.Sensors.MotionTracker.UnsupportedMotionTrackerException;
import com.rpi.experiments.signalprocessing.Filter;
import com.rpi.experiments.signalprocessing.LowPassFilter;

public class Experiment {
    private static final Logger LOGGER = LoggerFactory.getLogger(Experiment.class);

    public static void main(String args[]) {
        final Experiment experimentInstance = new Experiment();
    }

    public Experiment() {
        performExperiment();
    }

    private void experimentMotionSensor() {
        try {
            LOGGER.info("Initializing motion sensor");
            MultiAxisMotionSensor multiAxisMotionSensor = new MPU9265MotionSensor();
            multiAxisMotionSensor.initializeSensor();
            multiAxisMotionSensor.configureDeviceRegisters();

            final Filter lowPassFilterXRotation = new LowPassFilter(0.2f);
            final Filter lowPassFilterYRotation = new LowPassFilter(0.2f);
            while(true) {
                int val_x = getVal(multiAxisMotionSensor, Axis.X_AXIS_H, Axis.X_AXIS_L);
                int val_y = getVal(multiAxisMotionSensor, Axis.Y_AXIS_H, Axis.Y_AXIS_L);
                int val_z = getVal(multiAxisMotionSensor, Axis.Z_AXIS_H, Axis.X_AXIS_L);

                float accel_xout_scaled = val_x / 16384.0f;
                float accel_yout_scaled = val_y / 16384.0f;
                float accel_zout_scaled = val_z / 16384.0f;

                double x_rotation = get_x_rotation(accel_xout_scaled,
                        accel_yout_scaled,
                        accel_zout_scaled);

                double y_rotation = get_y_rotation(accel_xout_scaled,
                        accel_yout_scaled,
                        accel_zout_scaled);

                x_rotation = lowPassFilterXRotation.filter(x_rotation);
                y_rotation = lowPassFilterYRotation.filter(y_rotation);

                LOGGER.info("x_rotation: " + x_rotation + ", y_rotation: " + y_rotation);
                Thread.sleep(5);
            }
        } catch (IOException ioe) {
            LOGGER.error("Problem with IO", ioe);
        } catch (I2CFactory.UnsupportedBusNumberException unsupportedBusNumberException) {
            LOGGER.error("The bus number is unsupported", unsupportedBusNumberException);
        } catch (InterruptedException ie) {
            LOGGER.error("Interrupted", ie);
        } catch (UnsupportedMotionTrackerException umte) {
            LOGGER.error("Requested motion tracker is not supported", umte);
        }
    }

    public double get_y_rotation(final float x, final float y, final float z) {
        final double dist = Math.sqrt((y*y) + (z*z));
        final double radians = Math.atan2(x, dist);
        return -Math.toDegrees(radians);
    }

    public double get_x_rotation(final float x, final float y, final float z) {
        final double dist = Math.sqrt((x*x) + (z*z));
        final double radians = Math.atan2(y, dist);
        return Math.toDegrees(radians);
    }

    public int getVal(final MultiAxisMotionSensor controller, final Axis highAxis, final Axis lowAxis) throws IOException, UnsupportedMotionTrackerException {
        final byte high = controller.readValue(MotionTrackers.ACCELEROMETER, highAxis);
        final byte low = controller.readValue(MotionTrackers.ACCELEROMETER, lowAxis);
        final int valRaw = (high << 8) + low;

        final int val;
        if (valRaw >= 0x8000) {
            val = -((65535-valRaw) + 1);
        } else {
            val = valRaw;
        }

        return val;
    }

    public void performExperiment() {
        final PinToggling pingToggling = new PinToggling();

        try {
            pingToggling.performToggle(RaspiPin.GPIO_01, 1000);
        } catch(InterruptedException interruptedException) {
            LOGGER.error("Performing toggle was interrupted during sleep", interruptedException);
        }

        experimentMotionSensor();
    }
}
