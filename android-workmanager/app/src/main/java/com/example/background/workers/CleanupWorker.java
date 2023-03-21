package com.example.background.workers;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.background.Constants;

import java.io.File;

public class CleanupWorker extends Worker {
    public CleanupWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    public static final String TAG=CleanupWorker.class.getSimpleName();
    @NonNull
    @Override
    public Result doWork(){
        Context applicationContext=getApplicationContext();

        WorkerUtils.makeStatusNotification("Cleaning up old temporary files",applicationContext);
        WorkerUtils.sleep();

        try{
            File ouputDirectory =new File(applicationContext.getFilesDir(), Constants.OUTPUT_PATH);
            if(ouputDirectory.exists()){
                File[] entries=ouputDirectory.listFiles();
                if(entries!=null && entries.length>0){
                    for(File entry:entries){
                        String name= entry.getName();
                        if(!TextUtils.isEmpty(name)&&name.endsWith(".png")){
                            boolean deleted=entry.delete();
                            Log.i(TAG,String.format("Deleted %s - %s",name,deleted));
                        }
                    }
                }
            }
            return Worker.Result.success();

        }
        catch (Exception e){
            Log.e(TAG, "Error Cleaning up",e );
        return Worker.Result.failure();
        }
    }
}
