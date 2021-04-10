package com.example.controladorandroid;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    private Button upBtn,downBtn,leftBtn,rightBtn,fireBtn;
    private BufferedWriter bwriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        upBtn = findViewById(R.id.upBtn);
        downBtn = findViewById(R.id.downBtn);
        leftBtn = findViewById(R.id.leftBtn);
        rightBtn = findViewById(R.id.rightBtn);
        fireBtn = findViewById(R.id.fireBtn);


        //Metodo de suscripcion
        upBtn.setOnTouchListener(this);
        downBtn.setOnTouchListener(this);
        leftBtn.setOnTouchListener(this);
        rightBtn.setOnTouchListener(this);
        disparar();


        new Thread(

                () -> {

                    try {
                        Socket socket = new Socket("192.168.1.5", 5000);

                        OutputStream os = socket.getOutputStream();
                        OutputStreamWriter osw = new OutputStreamWriter(os);
                        bwriter = new BufferedWriter(osw);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

        ).start();


    }

    public void disparar(){

        fireBtn.setOnClickListener(

                v -> {
                    Gson gson = new Gson();
                    Coordenada fire = new Coordenada("FIRE");
                    String jsonfire = gson.toJson(fire);
                    enviar(jsonfire);
                    Toast.makeText(this, "FIRE", Toast.LENGTH_SHORT).show();

                }

        );
    }

    public void enviar(String msg){
        new Thread(() -> {
            try {
                bwriter.write(msg + "\n");
                bwriter.flush();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Gson gson = new Gson();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                switch (v.getId()){
                    case R.id.upBtn:
                        Coordenada upstart = new Coordenada("UPSTART");
                        String jsonupstart = gson.toJson(upstart);
                        enviar(jsonupstart);
                        break;

                    case R.id.downBtn:
                        Coordenada downstart = new Coordenada("DOWNSTART");
                        String jsondownstart = gson.toJson(downstart);
                        enviar(jsondownstart);
                        break;

                    case R.id.leftBtn:
                        Coordenada leftstart = new Coordenada("LEFTSTART");
                        String jsonleftstart = gson.toJson(leftstart);
                        enviar(jsonleftstart);
                        break;
                    case R.id.rightBtn:
                        Coordenada rightstart = new Coordenada("RIGHTSTART");
                        String jsonrightstart = gson.toJson(rightstart);
                        enviar(jsonrightstart);
                        break;
                }
                break;


            case MotionEvent.ACTION_UP:

                switch (v.getId()){
                    case R.id.upBtn:
                        Coordenada upstop = new Coordenada("UPSTOP");
                        String jsonupstop = gson.toJson(upstop);
                        enviar(jsonupstop);
                        break;

                    case R.id.downBtn:
                        Coordenada downstop = new Coordenada("DOWNSTOP");
                        String jsondownstop = gson.toJson(downstop);
                        enviar(jsondownstop);
                        break;

                    case R.id.leftBtn:
                        Coordenada leftstop = new Coordenada("LEFTSTOP");
                        String jsonleftstop = gson.toJson(leftstop);
                        enviar(jsonleftstop);
                        break;
                    case R.id.rightBtn:
                        Coordenada rightstop = new Coordenada("RIGHTSTOP");
                        String jsonrightstop = gson.toJson(rightstop);
                        enviar(jsonrightstop);
                        break;
                }
                break;
        }

        return false;
    }
}