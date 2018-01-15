package a15008616.cfrotaru.com.sensorsinfo;

import android.hardware.Sensor;


import java.util.List;

/**
 * Created by cryss on 14-Jan-18.
 */

public class SensorInfo {

    //Method to format the sensor information so it can be easily used
    public String[][] sensors (List<Sensor> sensorsList){

        String[] sensorName = { "Accelerometer",
                "Gyroscope",
                "Magnetic field",
                "Magnetometer",
                "Orientation",
                "Temperature",
                "Proximity",
                "Light",
                "Pressure",
                "Humidity",
                "Gravity",
                "Linear Acceleration",
                "Orientation",
                "Rotation Vector"};

        int counter = 0;
        for (Sensor s : sensorsList) {
            boolean isKnownSensor = false;
            //checks if the sensor appears in the sensorName array
            //it is needed to set the size of the array that will keep the sensors information
            for (int i = 0; i < sensorName.length && !isKnownSensor; i++)
                if (s.toString().contains(sensorName[i])) {isKnownSensor = true;
                counter++;}}


        String modifier;
        String[][] splitStringsSensors = new String[counter][8];
        String[] splitted;

        int sensorCounter = 0;
        //Get just the information about sensors, without the identifiers
        for (Sensor s : sensorsList) {
            boolean isKnownSensor = false;
            //checks if the sensor appears in the sensorName array
            for (int i = 0; i < sensorName.length && !isKnownSensor; i++)
                if (s.toString().contains(sensorName[i])) isKnownSensor = true;

            if (isKnownSensor) {
                modifier = s.toString().replace("=", "").replace("\"", "").replace("{Sensor name", "")
                        .replace("vendor", "").replace("version", "").replace("type", "")
                        .replace("maxRange", "").replace("resolution", "").replace("power", "")
                        .replace("minDelay", "").replace("}", "").replace(", ", ",");

                splitted = modifier.split(",");
                splitStringsSensors[sensorCounter] = splitted;
                sensorCounter++;
            }
        }


        //Simplify the sensors name to be shorter and easy to understand
        for (int i = 0; i < splitStringsSensors.length; i++)
            for (int j = 0; j < sensorName.length; j++)
                if (splitStringsSensors[i][0].contains(sensorName[j]))
                    splitStringsSensors[i][0] = sensorName[j];


        splitStringsSensors = orderAlpha(splitStringsSensors);
        splitStringsSensors = deleteDuplicates(splitStringsSensors);
        //Returns the simplified Strings array in alphabetic order without the duplicates

        return splitStringsSensors;
    }

    //Method to order alphabetically the sensor array by sensor name
    public String[][] orderAlpha (String[][] string){
        String[] swap;
        for (int i = 0; i < string.length - 1; i++)
            for (int j = i + 1; j < string.length; j++) {
                if (string[i][0].compareTo(string[j][0]) > 0) {
                    swap = string[i];
                    string[i] = string[j];
                    string[j] = swap;
                }
                if (string[i][0].compareTo(string[j][0]) == 0)
                if (string[i][1].compareTo(string[j][1]) > 0){
                    swap = string[i];
                    string[i] = string[j];
                    string[j] = swap;
                }
            }


        return string;
    }
    //Method to delete the duplicates
    public String[][] deleteDuplicates (String[][] string){

        String[][] noDupString = new String[string.length][8];
        String[][] noDupStrFinal;

        int counter = 1;
        noDupString[0] = string[0];
        for (int i = 1; i < string.length; i++)
            //checks if the name or the vendor are different than the previous one
            if (noDupString[counter-1][0].compareTo(string[i][0]) !=0
                    || noDupString[counter-1][1].compareTo(string[i][1]) !=0){
            noDupString[counter] = string[i];
            counter++;
            }
            //String to be returned having the correct size
            noDupStrFinal = new String[counter][8];

            for (int i = 0; i < counter; i++)
                noDupStrFinal[i] = noDupString[i];




        return noDupStrFinal;
    }
}
