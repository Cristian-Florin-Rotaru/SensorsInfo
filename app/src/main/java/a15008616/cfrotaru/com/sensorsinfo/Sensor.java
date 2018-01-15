package a15008616.cfrotaru.com.sensorsinfo;

import java.util.UUID;

/**
 * Created by cryss on 15-Jan-18.
 */

public class Sensor {

    private String sensorName;
    private String vendor;
    private String version;
    private String type;
    private String maxRange;
    private String resolution;
    private String power;
    private String minDelay;

    public Sensor() {
    }

    public Sensor(String sensorName, String vendor, String version, String type, String maxRange, String resolution, String power, String minDelay) {
        this.sensorName = sensorName;
        this.vendor = vendor;
        this.version = version;
        this.type = type;
        this.maxRange = maxRange;
        this.resolution = resolution;
        this.power = power;
        this.minDelay = minDelay;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(String maxRange) {
        this.maxRange = maxRange;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getMinDelay() {
        return minDelay;
    }

    public void setMinDelay(String minDelay) {
        this.minDelay = minDelay;
    }

}
