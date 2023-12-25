package com.harshit.myvideo;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final int PICK_AUDIO = 1;
    Uri uri ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);

        Button button1 = findViewById(R.id.button2);
        button1.setOnClickListener((v)->{
            startActivity(new Intent(this, MyActivity.class).putExtra("url",uri.toString()));
        });
        button.setOnClickListener((v)->{
            Intent audio = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            audio.setType("audio/*");
            //     (or for video)     audio.setType("video/*");
            startActivityForResult(Intent.createChooser(audio, "Select Video"), PICK_AUDIO);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_AUDIO){
            if(data!= null && data.getData()!=null) {
                uri = data.getData();
            }
        }
    }
}