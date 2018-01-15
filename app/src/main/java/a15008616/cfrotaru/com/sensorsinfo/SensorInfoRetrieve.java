package a15008616.cfrotaru.com.sensorsinfo;

import android.content.Context;
import android.os.Environment;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;



/**
 * Created by cryss on 14-Jan-18.
 */

public class SensorInfoRetrieve {
    Context context;

    public SensorInfoRetrieve(Context context){
        this.context = context;
    }

    //Method to return a String[][] with the sensors information written in internal storage
    public String[][] getSensorInfo (){



        String[][] sensorInfo = new String[1][1];
        int sensorsNumber;
        SensorInfoWriteFile siwf = new SensorInfoWriteFile(context);

        if(siwf.internalFileExists()){
        try {


            BufferedReader br = new BufferedReader(new FileReader(siwf.sensorFileExternal()));
            sensorsNumber = Integer.parseInt(br.readLine());
            sensorInfo = new String[sensorsNumber][8];
            for (int i = 0; i < sensorsNumber; i++)
                sensorInfo[i] = br.readLine().split(",");

            br.close();

        }catch (Exception e){

        }}
        return sensorInfo;
    }

    public String getExternalPath (){
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()+"/sensors.txt";
        return path;
    }

    public String getInternalPath (){
        String path = context.getFilesDir().toString()+ "/sensors.txt";
        return path;
    }
}