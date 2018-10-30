package com.rpi.experiments.Sensors.MotionTracker.MPU9265;

import static com.rpi.experiments.Sensors.MotionTracker.MPU9265.MPU9265Registers.MPU9265_RA_ACCEL_XOUT_H;
import static com.rpi.experiments.Sensors.MotionTracker.MPU9265.MPU9265Registers.MPU9265_RA_ACCEL_XOUT_L;
import static com.rpi.experiments.Sensors.MotionTracker.MPU9265.MPU9265Registers.MPU9265_RA_ACCEL_YOUT_H;
import static com.rpi.experiments.Sensors.MotionTracker.MPU9265.MPU9265Registers.MPU9265_RA_ACCEL_YOUT_L;
import static com.rpi.experiments.Sensors.MotionTracker.MPU9265.MPU9265Registers.MPU9265_RA_ACCEL_ZOUT_H;
import static com.rpi.experiments.Sensors.MotionTracker.MPU9265.MPU9265Registers.MPU9265_RA_ACCEL_ZOUT_L;
import static com.rpi.experiments.Sensors.MotionTracker.MPU9265.MPU9265Registers.MPU9265_RA_GYRO_XOUT_H;
import static com.rpi.experiments.Sensors.MotionTracker.MPU9265.MPU9265Registers.MPU9265_RA_GYRO_XOUT_L;
import static com.rpi.experiments.Sensors.MotionTracker.MPU9265.MPU9265Registers.MPU9265_RA_GYRO_YOUT_H;
import static com.rpi.experiments.Sensors.MotionTracker.MPU9265.MPU9265Registers.MPU9265_RA_GYRO_YOUT_L;
import static com.rpi.experiments.Sensors.MotionTracker.MPU9265.MPU9265Registers.MPU9265_RA_GYRO_ZOUT_H;
import static com.rpi.experiments.Sensors.MotionTracker.MPU9265.MPU9265Registers.MPU9265_RA_GYRO_ZOUT_L;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import com.rpi.experiments.Sensors.MotionTracker.Axis;
import com.rpi.experiments.Sensors.MotionTracker.MotionTrackers;
import com.rpi.experiments.Sensors.MotionTracker.MultiAxisMotionSensor;
import com.rpi.experiments.Sensors.MotionTracker.UnsupportedMotionTrackerException;

public class MPU9265MotionSensor implements MultiAxisMotionSensor {
    private static final Logger LOGGER = LoggerFactory.getLogger(MPU9265MotionSensor.class);
    private static final int DEVICE_LOCATION = 0x68;
    private static final int NUMBER_OF_REGISTERS = 120;
    private static final List<MotionTrackers> SUPPORTED_MOTION_TRACKERS = Arrays.asList(MotionTrackers.ACCELEROMETER, MotionTrackers.GYROSCOPE);

    private I2CBus bus;
    private I2CDevice mpu9265;


    @Override
    public byte readValue(final MotionTrackers motionTrackerType, final Axis axis) throws UnsupportedMotionTrackerException, IOException {
        readRegister(MPU9265_RA_ACCEL_ZOUT_H);
        final byte registerToRead;
        switch (motionTrackerType) {
        case ACCELEROMETER:
            if (Axis.X_AXIS_H.equals(axis)) {
                registerToRead = MPU9265_RA_ACCEL_XOUT_H;
            } else if (Axis.Y_AXIS_H.equals(axis)) {
                registerToRead = MPU9265_RA_ACCEL_YOUT_H;
            } else if (Axis.Z_AXIS_H.equals(axis)) {
                registerToRead = MPU9265_RA_ACCEL_ZOUT_H;
            } else if (Axis.X_AXIS_L.equals(axis)) {
                registerToRead = MPU9265_RA_ACCEL_XOUT_L;
            } else if (Axis.Y_AXIS_L.equals(axis)) {
                registerToRead = MPU9265_RA_ACCEL_YOUT_L;
            } else if (Axis.Z_AXIS_L.equals(axis)) {
                registerToRead = MPU9265_RA_ACCEL_ZOUT_L;
            } else {
                throw new UnsupportedMotionTrackerException(String.format("Invalid axis provided: {}", axis));
            }
            break;
        case GYROSCOPE:
            if (Axis.X_AXIS_H.equals(axis)) {
                registerToRead = MPU9265_RA_GYRO_XOUT_H;
            } else if (Axis.Y_AXIS_H.equals(axis)) {
                registerToRead = MPU9265_RA_GYRO_YOUT_H;
            } else if (Axis.Z_AXIS_H.equals(axis)) {
                registerToRead = MPU9265_RA_GYRO_ZOUT_H;
            } else if (Axis.X_AXIS_L.equals(axis)) {
                registerToRead = MPU9265_RA_GYRO_XOUT_L;
            } else if (Axis.Y_AXIS_L.equals(axis)) {
                registerToRead = MPU9265_RA_GYRO_YOUT_L;
            } else if (Axis.Z_AXIS_L.equals(axis)) {
                registerToRead = MPU9265_RA_GYRO_ZOUT_L;
            } else {
                throw new UnsupportedMotionTrackerException(String.format("Invalid axis provided: {}", axis));
            }
            break;
        case MAGNETOMETER:
            throw new UnsupportedMotionTrackerException("Magnetometer is not supported at this time");
        default:
            throw new UnsupportedMotionTrackerException(String.format("The provided motion tracker is not supported, motionTrackerType: {}", motionTrackerType));
        }

        return readRegister(registerToRead);
    }

    @Override
    public void initializeSensor() throws IOException, I2CFactory.UnsupportedBusNumberException {
        LOGGER.info("Creating I2C bus");
        bus = I2CFactory.getInstance(I2CBus.BUS_1);
        LOGGER.info("Creating I2C device");
        mpu9265 = bus.getDevice(DEVICE_LOCATION);
    }

    @Override
    public List<MotionTrackers> getSupportedMotionTrackers() {
        return SUPPORTED_MOTION_TRACKERS;
    }

    @Override
    public void configureDeviceRegisters() throws IOException {
        //1 Waking the device up
        writeConfigRegisterAndValidate(
                "Waking up device",
                "Wake-up config succcessfully written: ",
                MPU9265Registers.MPU9265_RA_PWR_MGMT_1,
                MPU9265RegisterValues.MPU9265_RA_PWR_MGMT_1);

        //2 Configure sample rate
        writeConfigRegisterAndValidate(
                "Configuring sample rate",
                "Sample rate succcessfully written: ",
                MPU9265Registers.MPU9265_RA_SMPLRT_DIV,
                MPU9265RegisterValues.MPU9265_RA_SMPLRT_DIV);

        //3 Setting global config
        writeConfigRegisterAndValidate(
                "Setting global config (digital low pass filter)",
                "Global config succcessfully written: ",
                MPU9265Registers.MPU9265_RA_CONFIG,
                MPU9265RegisterValues.MPU9265_RA_CONFIG);

        //4 Configure Gyroscope
        writeConfigRegisterAndValidate(
                "Configuring gyroscope",
                "Gyroscope config successfully written: ",
                MPU9265Registers.MPU9265_RA_GYRO_CONFIG,
                MPU9265RegisterValues.MPU9265_RA_GYRO_CONFIG);

        //5 Configure Accelerometer
        writeConfigRegisterAndValidate(
                "Configuring accelerometer",
                "Accelerometer config successfully written: ",
                MPU9265Registers.MPU9265_RA_ACCEL_CONFIG,
                MPU9265RegisterValues.MPU9265_RA_ACCEL_CONFIG);

        //6 Configure interrupts
        writeConfigRegisterAndValidate(
                "Configuring interrupts",
                "Interrupt config successfully written: ",
                MPU9265Registers.MPU9265_RA_INT_ENABLE,
                MPU9265RegisterValues.MPU9265_RA_INT_ENABLE);

        //7 Configure low power operations
        writeConfigRegisterAndValidate(
                "Configuring low power operations",
                "Low power operation config successfully written: ",
                MPU9265Registers.MPU9265_RA_PWR_MGMT_2,
                MPU9265RegisterValues.MPU9265_RA_PWR_MGMT_2);

        LOGGER.debug("MPU9265 Configured with the following values:");
        for (byte i = 1; i <= NUMBER_OF_REGISTERS; i++) {
            byte registerData = readRegister(i);
            LOGGER.info("Register {} configured with {}", i, registerData);
        }
    }

    private void writeRegister(final byte register, final byte data) throws IOException {
        mpu9265.write(register, data);
    }

    private byte readRegister(final byte register) throws IOException {
        int data = mpu9265.read(register);
        return (byte) data;
    }

    private void writeConfigRegisterAndValidate(final String initialText, final String successText, final byte register, final byte registerData) throws IOException {
        LOGGER.info(initialText);
        writeRegister(register, registerData);
        byte returnedRegisterData = readRegister(register);
        if (returnedRegisterData == registerData) {
            LOGGER.info(successText + returnedRegisterData);
        } else {
            throw new RuntimeException("Tried to write " + registerData + " to "
                    + register + ", but validating value returned " + returnedRegisterData);
        }
    }
}
