package com.rpi.experiments;

import com.pi4j.io.gpio.RaspiPin;
import com.rpi.experiments.Actions.LEDToggling;

public class Experiment {
    public static void main(String args[]) {
        final Experiment experimentInstance = new Experiment();
    }

    public Experiment() {
        performExperiment();
    }

    public void performExperiment() {
        LEDToggling ledToggling = new LEDToggling();

        try {
            ledToggling.performToggle(RaspiPin.GPIO_01, 1000);
        } catch(InterruptedException interruptedException) {
            System.out.println(interruptedException);
        }
    }
}
