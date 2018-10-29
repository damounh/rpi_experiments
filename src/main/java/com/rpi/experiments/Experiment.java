package com.rpi.experiments;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pi4j.io.gpio.RaspiPin;
import com.rpi.experiments.Actions.PinToggling;

public class Experiment {
    private static final Logger LOGGER = LoggerFactory.getLogger(Experiment.class);

    public static void main(String args[]) {
        final Experiment experimentInstance = new Experiment();
    }

    public Experiment() {
        performExperiment();
    }

    public void performExperiment() {
        LOGGER.info("Performing experiment");

        final PinToggling pingToggling = new PinToggling();

        try {
            pingToggling.performToggle(RaspiPin.GPIO_01, 1000);
        } catch(InterruptedException interruptedException) {
            LOGGER.error("Performing toggle was interrupted during sleep", interruptedException);
        }
    }
}
