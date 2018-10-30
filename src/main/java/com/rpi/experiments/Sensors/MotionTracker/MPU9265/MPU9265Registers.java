package com.rpi.experiments.Sensors.MotionTracker.MPU9265;

public class MPU9265Registers {
        /**
         * 8-bit unsigned value. The Sample Rate is determined by dividing the
         * gyroscope output rate by this value.
         */
        public static final byte MPU9265_RA_SMPLRT_DIV = 25;
        /**
         * Bit 7 and bit 6 are reserved. Parameters: EXT_SYNC_SET 3-bit unsigned
         * value. Configures the FSYNC pin sampling. DLPF_CFG 3-bit unsigned value.
         * Configures the DLPF setting.
         */
        public static final byte MPU9265_RA_CONFIG = 26;
        /**
         * Bits 2 through 0 are reserved. Parameters: XG_ST Setting this bit causes
         * the X axis gyroscope to perform self test. YG_ST Setting this bit causes
         * the Y axis gyroscope to perform self test. ZG_ST Setting this bit causes
         * the Z axis gyroscope to perform self test. FS_SEL 2-bit unsigned value.
         * Selects the full scale range of gyroscopes:0 ± 250 °/s, 1 ± 500 °/s, 2 ±
         * 1000 °/s, 3 ± 2000 °/s
         */
        public static final byte MPU9265_RA_GYRO_CONFIG = 27;
        /**
         * Parameters: XA_ST YA_ST ZA_ST AFS_SEL When set to 1, the X- Axis
         * accelerometer performs self test. When set to 1, the Y- Axis
         * accelerometer performs self test. When set to 1, the Z- Axis
         * accelerometer performs self test. 2-bit unsigned value. Selects the full
         * scale range of accelerometers: 0 ± 2g, 1 ± 4g, 2 ± 8g, 3 ± 16g
         */
        public static final byte MPU9265_RA_ACCEL_CONFIG = 28;
        /**
         * <strong>Order of bits from 7 to 0: TEMP_ XG_ YG_ ZG_ ACCEL SLV2 SLV1 SLV0
         * 23 35 FIFO_EN FIFO_EN FIFO_EN FIFO_EN _FIFO_EN _FIFO_EN _FIFO_EN
         * _FIFO_EN</strong>
         * <p>
         * Parameters: TEMP_FIFO_EN When set to 1, this bit enables TEMP_OUT_H and
         * TEMP_OUT_L (Registers 65 and 66) to be written into the FIFO buffer. XG_
         * FIFO_EN When set to 1, this bit enables GYRO_XOUT_H and GYRO_XOUT_L
         * (Registers 67 and 68) to be written into the FIFO buffer. YG_ FIFO_EN
         * When set to 1, this bit enables GYRO_YOUT_H and GYRO_YOUT_L (Registers 69
         * and 70) to be written into the FIFO buffer. ZG_ FIFO_EN When set to 1,
         * this bit enables GYRO_ZOUT_H and GYRO_ZOUT_L (Registers 71 and 72) to be
         * written into the FIFO buffer. ACCEL_ FIFO_EN When set to 1, this bit
         * enables ACCEL_XOUT_H, ACCEL_XOUT_L, ACCEL_YOUT_H, ACCEL_YOUT_L,
         * ACCEL_ZOUT_H, and ACCEL_ZOUT_L (Registers 59 to 64) to be written into
         * the FIFO buffer. SLV2_SLV1_SLV0_FIFO_EN FIFO_EN FIFO_EN When set to 1,
         * this bit enables EXT_SENS_DATA registers (Registers 73 to 96) associated
         * with Slave 2 to be written into the FIFO buffer. When set to 1, this bit
         * enables EXT_SENS_DATA registers (Registers 73 to 96) associated with
         * Slave 1 to be written into the FIFO buffer. When set to 1, this bit
         * enables EXT_SENS_DATA registers (Registers 73 to 96) associated with
         * Slave 0 to be written into the FIFO buffer.
         */
        public static final byte MPU9265_RA_FIFO_EN = 35;
        /**
         * Bits 2 and 1 are reserved. Parameters: FIFO_OFLOW_EN I2C_MST_INT_EN
         * DATA_RDY_EN When set to 1, this bit enables a FIFO buffer overflow to
         * generate an interrupt. 2 When set to 1, this bit enables any of the I C
         * Master interrupt sources to generate an interrupt. When set to 1, this
         * bit enables the Data Ready interrupt, which occurs each time a write
         * operation to all of the sensor registers has been completed.
         */
        public static final byte MPU9265_RA_INT_ENABLE = 56;
        /**
         * 16-bit 2’s complement value. Stores the most recent X axis accelerometer
         * measurement.
         */
        public static final byte MPU9265_RA_ACCEL_XOUT_H = 59;
        /**
         * 16-bit 2’s complement value. Stores the most recent X axis accelerometer
         * measurement.
         */
        public static final byte MPU9265_RA_ACCEL_XOUT_L = 60;
        /**
         * 16-bit 2’s complement value. Stores the most recent Y axis accelerometer
         * measurement.
         */
        public static final byte MPU9265_RA_ACCEL_YOUT_H = 61;
        /**
         * 16-bit 2’s complement value. Stores the most recent Y axis accelerometer
         * measurement.
         */
        public static final byte MPU9265_RA_ACCEL_YOUT_L = 62;
        /**
         * 16-bit 2’s complement value. Stores the most recent Z axis accelerometer
         * measurement.
         */
        public static final byte MPU9265_RA_ACCEL_ZOUT_H = 63;
        /**
         * 16-bit 2’s complement value. Stores the most recent Z axis accelerometer
         * measurement.
         */
        public static final byte MPU9265_RA_ACCEL_ZOUT_L = 64;
        /**
         * 16-bit signed value. Stores the most recent temperature sensor
         * measurement.
         */
        public static final byte MPU9265_RA_TEMP_OUT_H = 65;
        /**
         * 16-bit signed value. Stores the most recent temperature sensor
         * measurement.
         */
        public static final byte MPU9265_RA_TEMP_OUT_L = 66;
        /**
         * 16-bit 2’s complement value. Stores the most recent X axis gyroscope
         * measurement.
         */
        public static final byte MPU9265_RA_GYRO_XOUT_H = 67;
        /**
         * 16-bit 2’s complement value. Stores the most recent X axis gyroscope
         * measurement.
         */
        public static final byte MPU9265_RA_GYRO_XOUT_L = 68;
        /**
         * 16-bit 2’s complement value. Stores the most recent Y axis gyroscope
         * measurement.
         */
        public static final byte MPU9265_RA_GYRO_YOUT_H = 69;
        /**
         * 16-bit 2’s complement value. Stores the most recent Y axis gyroscope
         * measurement.
         */
        public static final byte MPU9265_RA_GYRO_YOUT_L = 70;
        /**
         * 16-bit 2’s complement value. Stores the most recent Z axis gyroscope
         * measurement.
         */
        public static final byte MPU9265_RA_GYRO_ZOUT_H = 71;
        /**
         * 16-bit 2’s complement value. Stores the most recent Z axis gyroscope
         * measurement.
         */
        public static final byte MPU9265_RA_GYRO_ZOUT_L = 72;
        /**
         * Bits 7 to 3 are reserved. Parameters: GYRO_RESET When set to 1, this bit
         * resets the gyroscope analog and digital signal paths. ACCEL_RESET When
         * set to 1, this bit resets the accelerometer analog and digital signal
         * paths. TEMP_RESET When set to 1, this bit resets the temperature sensor
         * analog and digital signal paths.
         */
        public static final byte MPU9265_RA_SIGNAL_PATH_RESET = 104;
        /**
         * Bit 4 is reserved. Parameters: DEVICE_RESET SLEEP CYCLE TEMP_DIS CLKSEL
         * When set to 1, this bit resets all internal registers to their default
         * values. The bit automatically clears to 0 once the reset is done. The
         * default values for each register can be found in Section 3. When set to
         * 1, this bit puts the MPU-60X0 into sleep mode. When this bit is set to 1
         * and SLEEP is disabled, the MPU-60X0 will cycle between sleep mode and
         * waking up to take a single sample of data from active sensors at a rate
         * determined by LP_WAKE_CTRL (register 108). When set to 1, this bit
         * disables the temperature sensor. 3-bit unsigned value. Specifies the
         * clock source of the device.
         */
        public static final byte MPU9265_RA_PWR_MGMT_1 = 107;
        /**
         * Bits 0 and 7 are reserved. (Hard coded to 0) Parameters: 2 WHO_AM_I
         * Contains the 6-bit I C address of the MPU-60X0. The Power-On-Reset value
         * of Bit6:Bit1 is 110 100.
         */
        public static final byte MPU9265_RA_WHO_AM_I = 117;
        /**
         * Parameters: LP_WAKE_CTRL 2-bit unsigned value. Specifies the frequency of
         * wake-ups during Accelerometer Only Low Power Mode. STBY_XA When set to 1,
         * this bit puts the X axis accelerometer into standby mode. STBY_YA When
         * set to 1, this bit puts the Y axis accelerometer into standby mode.
         * STBY_ZA When set to 1, this bit puts the Z axis accelerometer into
         * standby mode. STBY_XG When set to 1, this bit puts the X axis gyroscope
         * into standby mode. STBY_YG When set to 1, this bit puts the Y axis
         * gyroscope into standby mode. STBY_ZG When set to 1, this bit puts the Z
         * axis gyroscope into standby mode.
         */
        public static final byte MPU9265_RA_PWR_MGMT_2 = 108;
}
