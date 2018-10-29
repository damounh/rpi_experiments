# rpi_experiments
A java sandbox to experiment with the raspberry pi. Will use this repo to play with gpio pins and sensors bought from https://www.amazon.com/gp/product/B016D5L5KE/ref=oh_aui_search_detailpage?ie=UTF8&psc=1

## Building and Running project

To run this project install wiringpi, this is used by pi4j to talk to the pins.
```
sudo apt-get install wiringpi
```

gradle to build and run application. Installed using SDKMan: https://sdkman.io/install
```
sdk install gradle
```

all done, can now build and run the application using:
```
gradle clean && gradle build
gradle run
```

Logs will be outputted to console as well as application logs, path for application logs can be found: https://github.com/damounh/rpi_experiments/blob/master/src/main/resources/log4j2.xml#L4
