package a15008616.cfrotaru.com.sensorsinfo;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Created by cryss on 14-Jan-18.
 */

public class SensorInfoWriteFile {
    Context context;

    public SensorInfoWriteFile(Context context) {
        this.context = context;
    }


    public void writeFile (String[][] splitStringsSensors){



        //if the file exists, there is no need to create another one
        //as the phone sensors are not removable/interchangeable
        if (!internalFileExists())
            try {

                // Create a file to export the sensor info.
                File fp = sensorFileInternal(context);
                fp.createNewFile();
                PrintWriter pw = new PrintWriter(new FileWriter(fp, true));

                //pw.write(splitStringsSensors.length + "\n");
                //Write into file
                for (int i = 0; i < splitStringsSensors.length; i++){
                    for (int j = 0; j < 8; j++){
                        pw.write(splitStringsSensors[i][j]);
                        if (j < 7) pw.write(",");
                    }
                    if (i < splitStringsSensors.length - 1)pw.write("\n");
                }
                pw.close();
            } catch (Exception e){

            }
    }

    public void writeFileExternal (String[][] splitStringsSensors){



        //if the file exists, there is no need to create another one
        //as the phone sensors are not removable/interchangeable
        if (internalFileExists())
            try {

                // Create a file to export the sensor info.
                File fp = sensorFileInternal(context);
                fp.createNewFile();
                PrintWriter pw = new PrintWriter(new FileWriter(fp, true));

                //pw.write(splitStringsSensors.length + "\n");
                for (int i = 0; i < splitStringsSensors.length; i++){
                    for (int j = 0; j < 8; j++){
                        pw.write(splitStringsSensors[i][j]);
                        if (j < 7) pw.write(",");
                    }
                    if (i < splitStringsSensors.length - 1)pw.write("\n");
                }
                pw.close();
            } catch (Exception e){
                Log.d("Exception12342", "found12321");
            }
    }
    public File sensorFileInternal(Context context){

        String internalPath = context.getFilesDir().toString();
        File file = new File(internalPath + "/sensors.txt");

        return file;
    }

    public File sensorFileExternal(){
        String externalPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(externalPath + "/sensors.txt");

        return file;
    }


    public boolean internalFileExists(){

        return sensorFileInternal(context).exists();
    }
    public boolean externalFileExists(){

        return sensorFileExternal().exists();
    }
}
