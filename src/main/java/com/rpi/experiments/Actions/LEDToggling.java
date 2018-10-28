package com.rpi.experiments.Actions;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;

public class LEDToggling {
    private static final int TIMES_TO_TOGGLE = 5;

    private final GpioController gpio;

    public LEDToggling() {
        gpio = GpioFactory.getInstance();
    }

    /**
     * Toggles the specified pin with the delay provided
     * @param pinToToggle
     * @param delayInMillis
     * @throws InterruptedException
     */
    public void performToggle(final Pin pinToToggle, final int delayInMillis) throws InterruptedException {
        final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(pinToToggle, "MyLED", PinState.HIGH);

        // set shutdown state for this pin
        pin.setShutdownOptions(true, PinState.LOW);

        System.out.println("--> GPIO state should be: ON");

        Thread.sleep(1000);

        // turn off gpio pin
        pin.low();
        System.out.println("--> GPIO state should be: OFF");
        for (int i = 0; i < TIMES_TO_TOGGLE;i++) {
            Thread.sleep(delayInMillis);

            // toggle the current state of gpio pin
            pin.toggle();
            System.out.println("--> GPIO state toggled");
        }

        gpio.shutdown();
        System.out.println("Exiting ControlGpioExample");
    }
}
