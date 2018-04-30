package com.example.nivitt.blinkingt;


import android.content.Context;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.annotation.ColorInt;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.pes.androidmaterialcolorpickerdialog.ColorPicker;
import com.pes.androidmaterialcolorpickerdialog.ColorPickerCallback;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.AsyncTask;
import java.io.IOException;
import java.util.UUID;

import at.markushi.ui.CircleButton;


public class ledControl extends AppCompatActivity implements View.OnClickListener{

    Button button1,button2,button3,button4;
    String string,ss="40";
    String r="255",g="0",b="0";
    int main = Color.RED;
    TextView t;
    String colormain = String.format("#%06X", (0xFFFFFF & 0xFF0000));
   // SeekBar seekBar;
    String arr[] = new String[64];

    Vibrator vibe;

    String bbb1="#000000",bbb2="#000000",bbb3="#000000",bbb4="#000000",bbb5="#000000",bbb6="#000000",bbb7="#000000",bbb8="#000000",bbb9="#000000",bbb10="#000000",bbb11="#000000",bbb12="#000000",bbb13="#000000",bbb14="#000000",bbb15="#000000",bbb16="#000000",bbb17="#000000",bbb18="#000000",bbb19="#000000",bbb20="#000000",bbb21="#000000";
    String bbb22="#000000",bbb23="#000000",bbb24="#000000",bbb25="#000000",bbb26="#000000",bbb27="#000000",bbb28="#000000",bbb29="#000000",bbb30="#000000",bbb31="#000000",bbb32="#000000",bbb33="#000000",bbb34="#000000",bbb35="#000000",bbb36="#000000",bbb37="#000000",bbb38="#000000",bbb39="#000000",bbb40="#000000",bbb41="#000000",bbb42="#000000";
    String bbb43="#000000",bbb44="#000000",bbb45="#000000",bbb46="#000000",bbb47="#000000",bbb48="#000000",bbb49="#000000",bbb50="#000000",bbb51="#000000",bbb52="#000000",bbb53="#000000",bbb54="#000000",bbb55="#000000",bbb56="#000000",bbb57="#000000",bbb58="#000000",bbb59="#000000",bbb60="#000000",bbb61="#000000",bbb62="#000000",bbb63="#000000",bbb64="#000000";

    CircleButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20,b21;
    CircleButton b22,b23,b24,b25,b26,b27,b28,b29,b30,b31,b32,b33,b34,b35,b36,b37,b38,b39,b40,b41,b42;
    CircleButton b43,b44,b45,b46,b47,b48,b49,b50,b51,b52,b53,b54,b55,b56,b57,b58,b59,b60,b61,b62,b63,b64;

    boolean bb1 =false,bb2 =false,bb3 =false,bb4 =false,bb5 =false,bb6 =false,bb7 =false,bb8 =false;
    boolean bb9 =false,bb10 =false,bb11 =false,bb12 =false,bb13 =false,bb14 =false,bb15 =false,bb16 =false;
    boolean bb17 =false,bb18 =false,bb19 =false,bb20 =false,bb21 =false,bb22 =false,bb23 =false,bb24 =false;
    boolean bb25 =false,bb26 =false,bb27 =false,bb28 =false,bb29 =false,bb30 =false,bb31 =false,bb32 =false;
    boolean bb33 =false,bb34 =false,bb35 =false,bb36 =false,bb37 =false,bb38 =false,bb39 =false,bb40 =false;
    boolean bb41 =false,bb42 =false,bb43 =false,bb44 =false,bb45 =false,bb46 =false,bb47 =false,bb48 =false;
    boolean bb49 =false,bb50 =false,bb51 =false,bb52 =false,bb53 =false,bb54 =false,bb55 =false,bb56 =false;
    boolean bb57 =false,bb58 =false,bb59 =false,bb60 =false,bb61 =false,bb62 =false,bb63 =false,bb64 =false;

    String address = null;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    //SPP UUID. Look for it
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Intent newint = getIntent();
        address = newint.getStringExtra(DeviceList.EXTRA_ADDRESS); //receive the address of the bluetooth device

        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        //view of the ledControl
        setContentView(R.layout.activity_led_control);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);

        //call the widgtes
        button1 = (Button) findViewById(R.id.button4);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button5);

        t = (TextView) findViewById(R.id.textView2);

        //seekBar = (SeekBar)findViewById(R.id.seekBar);

        new ConnectBT().execute(); //Call the class to connect

      /*  seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                ss = String.valueOf(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
*/
        final ColorPicker colorPicker = new ColorPicker(
                ledControl.this, // Context
                66, // Default Red value
                155, // Default Green value
                255 // Default Blue value
        );
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorPicker.show();
            }
        });
        colorPicker.setCallback(new ColorPickerCallback() {
            @Override
            public void onColorChosen(@ColorInt int color) {
                r = Integer.toString(Color.red(color));
                g = Integer.toString(Color.green(color));
                b = Integer.toString(Color.blue(color));
                main = colorPicker.getColor();
                colorPicker.dismiss();
                //t.setText(String.format("#%06X", (0xFFFFFF & color)));
                // t.setText(r+g+b);
                colormain = String.format("#%06X", (0xFFFFFF & color));

            }
        });



        b1= (CircleButton) findViewById(R.id.b1);
        b2= (CircleButton) findViewById(R.id.b2);
        b3= (CircleButton) findViewById(R.id.b3);
        b4= (CircleButton) findViewById(R.id.b4);
        b5= (CircleButton) findViewById(R.id.b5);
        b6= (CircleButton) findViewById(R.id.b6);
        b7= (CircleButton) findViewById(R.id.b7);
        b8= (CircleButton) findViewById(R.id.b8);
        b9= (CircleButton) findViewById(R.id.b9);
        b10= (CircleButton) findViewById(R.id.b10);
        b11= (CircleButton) findViewById(R.id.b11);
        b12= (CircleButton) findViewById(R.id.b12);
        b13= (CircleButton) findViewById(R.id.b13);
        b14= (CircleButton) findViewById(R.id.b14);
        b15= (CircleButton) findViewById(R.id.b15);
        b16= (CircleButton) findViewById(R.id.b16);
        b17= (CircleButton) findViewById(R.id.b17);
        b18= (CircleButton) findViewById(R.id.b18);
        b19= (CircleButton) findViewById(R.id.b19);
        b20= (CircleButton) findViewById(R.id.b20);
        b21= (CircleButton) findViewById(R.id.b21);
        b22= (CircleButton) findViewById(R.id.b22);
        b23= (CircleButton) findViewById(R.id.b23);
        b24= (CircleButton) findViewById(R.id.b24);
        b25= (CircleButton) findViewById(R.id.b25);
        b26= (CircleButton) findViewById(R.id.b26);
        b27= (CircleButton) findViewById(R.id.b27);
        b28= (CircleButton) findViewById(R.id.b28);
        b29= (CircleButton) findViewById(R.id.b29);
        b30= (CircleButton) findViewById(R.id.b30);
        b31= (CircleButton) findViewById(R.id.b31);
        b32= (CircleButton) findViewById(R.id.b32);
        b33= (CircleButton) findViewById(R.id.b33);
        b34= (CircleButton) findViewById(R.id.b34);
        b35= (CircleButton) findViewById(R.id.b35);
        b36= (CircleButton) findViewById(R.id.b36);
        b37= (CircleButton) findViewById(R.id.b37);
        b38= (CircleButton) findViewById(R.id.b38);
        b39= (CircleButton) findViewById(R.id.b39);
        b40= (CircleButton) findViewById(R.id.b40);
        b41= (CircleButton) findViewById(R.id.b41);
        b42= (CircleButton) findViewById(R.id.b42);
        b43= (CircleButton) findViewById(R.id.b43);
        b44= (CircleButton) findViewById(R.id.b44);
        b45= (CircleButton) findViewById(R.id.b45);
        b46= (CircleButton) findViewById(R.id.b46);
        b47= (CircleButton) findViewById(R.id.b47);
        b48= (CircleButton) findViewById(R.id.b48);
        b49= (CircleButton) findViewById(R.id.b49);
        b50= (CircleButton) findViewById(R.id.b50);
        b51= (CircleButton) findViewById(R.id.b51);
        b52= (CircleButton) findViewById(R.id.b52);
        b53= (CircleButton) findViewById(R.id.b53);
        b54= (CircleButton) findViewById(R.id.b54);
        b55= (CircleButton) findViewById(R.id.b55);
        b56= (CircleButton) findViewById(R.id.b56);
        b57= (CircleButton) findViewById(R.id.b57);
        b58= (CircleButton) findViewById(R.id.b58);
        b59= (CircleButton) findViewById(R.id.b59);
        b60= (CircleButton) findViewById(R.id.b60);
        b61= (CircleButton) findViewById(R.id.b61);
        b62= (CircleButton) findViewById(R.id.b62);
        b63= (CircleButton) findViewById(R.id.b63);
        b64= (CircleButton) findViewById(R.id.b64);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        b10.setOnClickListener(this);
        b11.setOnClickListener(this);
        b12.setOnClickListener(this);
        b13.setOnClickListener(this);
        b14.setOnClickListener(this);
        b15.setOnClickListener(this);
        b16.setOnClickListener(this);
        b17.setOnClickListener(this);
        b18.setOnClickListener(this);
        b19.setOnClickListener(this);
        b20.setOnClickListener(this);
        b21.setOnClickListener(this);
        b22.setOnClickListener(this);
        b23.setOnClickListener(this);
        b24.setOnClickListener(this);
        b25.setOnClickListener(this);
        b26.setOnClickListener(this);
        b27.setOnClickListener(this);
        b28.setOnClickListener(this);
        b29.setOnClickListener(this);
        b30.setOnClickListener(this);
        b31.setOnClickListener(this);
        b32.setOnClickListener(this);
        b33.setOnClickListener(this);
        b34.setOnClickListener(this);
        b35.setOnClickListener(this);
        b36.setOnClickListener(this);
        b37.setOnClickListener(this);
        b38.setOnClickListener(this);
        b39.setOnClickListener(this);
        b40.setOnClickListener(this);
        b41.setOnClickListener(this);
        b42.setOnClickListener(this);
        b43.setOnClickListener(this);
        b44.setOnClickListener(this);
        b45.setOnClickListener(this);
        b46.setOnClickListener(this);
        b47.setOnClickListener(this);
        b48.setOnClickListener(this);
        b49.setOnClickListener(this);
        b50.setOnClickListener(this);
        b51.setOnClickListener(this);
        b52.setOnClickListener(this);
        b53.setOnClickListener(this);
        b54.setOnClickListener(this);
        b55.setOnClickListener(this);
        b56.setOnClickListener(this);
        b57.setOnClickListener(this);
        b58.setOnClickListener(this);
        b59.setOnClickListener(this);
        b60.setOnClickListener(this);
        b61.setOnClickListener(this);
        b62.setOnClickListener(this);
        b63.setOnClickListener(this);
        b64.setOnClickListener(this);
        //commands to be sent to bluetooth


        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                turnOnLed();
                vibe.vibrate(100);
                //method to turn on
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                vibe.vibrate(100);
                turnOffLed();
                //method to turn off
            }
        });

        button3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Disconnect(); //close connection
            }
        });
    }
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.b1:
                if(!bb1){
                    b1.setColor(main);
                    bbb1=colormain;
                    arr[0]="1";
                    bb1=true;
                }else{
                    b1.setColor(Color.BLACK);
                    bbb1="#000000";
                    arr[0]="0";
                    bb1=false;
                }
                b1.clearAnimation();
                break;

            case R.id.b2:
                b2.clearAnimation();

                if(!bb2){
                    b2.setColor(main);
                    bbb2=colormain;
                    arr[1]="1";
                    bb2=true;
                }else{
                    b2.setColor(Color.BLACK);
                    bbb2="#000000";
                    arr[1]="0";
                    bb2=false;
                }
                break;
            case R.id.b3:
                b3.clearAnimation();

                if(!bb3){
                    b3.setColor(main);
                    bbb3=colormain;
                    arr[2]="1";
                    bb3=true;
                }else{
                    b3.setColor(Color.BLACK);
                    bbb3="#000000";
                    arr[2]="0";
                    bb3=false;
                }
                break;
            case R.id.b4:
                b4.clearAnimation();

                if(!bb4){
                    b4.setColor(main);
                    bbb4=colormain;
                    arr[3]="1";
                    bb4=true;
                }else{
                    b4.setColor(Color.BLACK);
                    bbb4="#000000";
                    arr[3]="0";
                    bb4=false;
                }
                break;
            case R.id.b5:
                if(!bb5){
                    b5.setColor(main);
                    bbb5=colormain;
                    arr[4]="1";
                    bb5=true;
                }else{
                    b5.setColor(Color.BLACK);
                    bbb5="#000000";
                    arr[4]="0";
                    bb5=false;
                }
                break;
            case R.id.b6:
                if(!bb6){
                    b6.setColor(main);
                    arr[5]="1";
                    bbb6=colormain;
                    bb6=true;
                }else{
                    b6.setColor(Color.BLACK);
                    bbb6="#000000";
                    arr[5]="0";
                    bb6=false;
                }
                break;
            case R.id.b7:
                if(!bb7){
                    b7.setColor(main);
                    bbb7=colormain;
                    arr[6]="1";
                    bb7=true;
                }else{
                    b7.setColor(Color.BLACK);
                    bbb7="#000000";
                    arr[6]="0";
                    bb7=false;
                }
                break;
            case R.id.b8:
                if(!bb8){
                    b8.setColor(main);
                    bbb8=colormain;
                    arr[7]="1";
                    bb8=true;
                }else{
                    b8.setColor(Color.BLACK);
                    bbb8="#000000";
                    arr[7]="0";
                    bb8=false;
                }
                break;
            case R.id.b9:
                if(!bb9){
                    b9.setColor(main);
                    bbb9=colormain;
                    arr[15]="1";
                    bb9=true;
                }else{
                    b9.setColor(Color.BLACK);
                    bbb9="#000000";
                    arr[15]="0";
                    bb9=false;
                }
                break;
            case R.id.b10:
                if(!bb10){
                    b10.setColor(main);
                    bbb10=colormain;
                    arr[14]="1";
                    bb10=true;
                }else{
                    b10.setColor(Color.BLACK);
                    bbb10="#000000";
                    arr[14]="0";
                    bb10=false;
                }
                break;
            case R.id.b11:
                if(!bb11){
                    b11.setColor(main);
                    bbb11=colormain;
                    arr[13]="1";
                    bb11=true;
                }else{
                    b11.setColor(Color.BLACK);
                    bbb11="#000000";
                    arr[13]="0";
                    bb11=false;
                }
                break;
            case R.id.b12:
                if(!bb12){
                    b12.setColor(main);
                    bbb12=colormain;
                    arr[12]="1";
                    bb12=true;
                }else{
                    b12.setColor(Color.BLACK);
                    bbb12="#000000";
                    arr[12]="0";
                    bb12=false;
                }
                break;
            case R.id.b13:
                if(!bb13){
                    b13.setColor(main);
                    bbb13=colormain;
                    arr[11]="1";
                    bb13=true;
                }else{
                    b13.setColor(Color.BLACK);
                    bbb13="#000000";
                    arr[11]="0";
                    bb13=false;
                }
                break;
            case R.id.b14:
                if(!bb14){
                    b14.setColor(main);
                    bbb14=colormain;
                    arr[10]="1";
                    bb14=true;
                }else{
                    b14.setColor(Color.BLACK);
                    bbb14="#000000";
                    arr[10]="0";
                    bb14=false;
                }
                break;
            case R.id.b15:
                if(!bb15){
                    b15.setColor(main);
                    bbb15=colormain;
                    arr[9]="1";
                    bb15=true;
                }else{
                    b15.setColor(Color.BLACK);
                    bbb15="#000000";
                    arr[9]="0";
                    bb15=false;
                }
                break;
            case R.id.b16:
                if(!bb16){
                    b16.setColor(main);
                    bbb16=colormain;
                    arr[8]="1";
                    bb16=true;
                }else{
                    b16.setColor(Color.BLACK);
                    bbb16="#000000";
                    arr[8]="0";
                    bb16=false;
                }
                break;
            case R.id.b17:
                if(!bb17){
                    b17.setColor(main);
                    bbb17=colormain;
                    arr[16]="1";
                    bb17=true;
                }else{
                    b17.setColor(Color.BLACK);
                    bbb17="#000000";
                    arr[16]="0";
                    bb17=false;
                }
                break;
            case R.id.b18:
                if(!bb18){
                    b18.setColor(main);
                    bbb18=colormain;
                    arr[17]="1";
                    bb18=true;
                }else{
                    b18.setColor(Color.BLACK);
                    bbb18="#000000";
                    arr[17]="0";
                    bb18=false;
                }
                break;
            case R.id.b19:
                if(!bb19){
                    b19.setColor(main);
                    bbb19=colormain;
                    arr[18]="1";
                    bb19=true;
                }else{
                    b19.setColor(Color.BLACK);
                    bbb19="#000000";
                    arr[18]="0";
                    bb19=false;
                }
                break;
            case R.id.b20:
                if(!bb20){
                    b20.setColor(main);
                    bbb20=colormain;
                    arr[19]="1";
                    bb20=true;
                }else{
                    b20.setColor(Color.BLACK);
                    bbb20="#000000";
                    arr[19]="0";
                    bb20=false;
                }
                break;
            case R.id.b21:
                if(!bb21){
                    b21.setColor(main);
                    bbb21=colormain;
                    arr[20]="1";
                    bb21=true;
                }else{
                    b21.setColor(Color.BLACK);
                    bbb21="#000000";
                    arr[20]="0";
                    bb21=false;
                }
                break;
            case R.id.b22:
                if(!bb22){
                    b22.setColor(main);
                    bbb22=colormain;
                    arr[21]="1";
                    bb22=true;
                }else{
                    b22.setColor(Color.BLACK);
                    bbb22="#000000";
                    arr[21]="0";
                    bb22=false;
                }
                break;
            case R.id.b23:
                if(!bb23){
                    b23.setColor(main);
                    bbb23=colormain;
                    arr[22]="1";
                    bb23=true;
                }else{
                    b23.setColor(Color.BLACK);
                    bbb23="#000000";
                    arr[22]="0";
                    bb23=false;
                }
                break;
            case R.id.b24:
                if(!bb24){
                    b24.setColor(main);
                    bbb24=colormain;
                    arr[23]="1";
                    bb24=true;
                }else{
                    b24.setColor(Color.BLACK);
                    bbb24="#000000";
                    arr[23]="0";
                    bb24=false;
                }
                break;
            case R.id.b25:
                if(!bb25){
                    b25.setColor(main);
                    bbb25=colormain;
                    arr[31]="1";
                    bb25=true;
                }else{
                    b25.setColor(Color.BLACK);
                    bbb25="#000000";
                    arr[31]="0";
                    bb25=false;
                }
                break;
            case R.id.b26:
                if(!bb26){
                    b26.setColor(main);
                    bbb26=colormain;
                    arr[30]="1";
                    bb26=true;
                }else{
                    b26.setColor(Color.BLACK);
                    bbb26="#000000";
                    arr[30]="0";
                    bb26=false;
                }
                break;
            case R.id.b27:
                if(!bb27){
                    b27.setColor(main);
                    bbb27=colormain;
                    arr[29]="1";
                    bb27=true;
                }else{
                    b27.setColor(Color.BLACK);
                    bbb27="#000000";
                    arr[29]="0";
                    bb27=false;
                }
                break;
            case R.id.b28:
                if(!bb28){
                    b28.setColor(main);
                    bbb28=colormain;
                    arr[28]="1";
                    bb28=true;
                }else{
                    b28.setColor(Color.BLACK);
                    bbb28="#000000";
                    arr[28]="0";
                    bb28=false;
                }
                break;
            case R.id.b29:
                if(!bb29){
                    b29.setColor(main);
                    bbb29=colormain;
                    arr[27]="1";
                    bb29=true;
                }else{
                    b29.setColor(Color.BLACK);
                    bbb29="#000000";
                    arr[27]="0";
                    bb29=false;
                }
                break;
            case R.id.b30:
                if(!bb30){
                    b30.setColor(main);
                    bbb30=colormain;
                    arr[26]="1";
                    bb30=true;
                }else{
                    b30.setColor(Color.BLACK);
                    bbb30="#000000";
                    arr[26]="0";
                    bb30=false;
                }
                break;
            case R.id.b31:
                if(!bb31){
                    b31.setColor(main);
                    bbb31=colormain;
                    arr[25]="1";
                    bb31=true;
                }else{
                    b31.setColor(Color.BLACK);
                    bbb31="#000000";
                    arr[25]="0";
                    bb31=false;
                }
                break;
            case R.id.b32:
                if(!bb32){
                    b32.setColor(main);
                    bbb32=colormain;
                    arr[24]="1";
                    bb32=true;
                }else{
                    b32.setColor(Color.BLACK);
                    bbb32="#000000";
                    arr[24]="0";
                    bb32=false;
                }
                break;
            case R.id.b33:
                if(!bb33){
                    b33.setColor(main);
                    bbb33=colormain;
                    arr[31]="1";
                    bb33=true;
                }else{
                    b33.setColor(Color.BLACK);
                    bbb33="#000000";
                    arr[31]="0";
                    bb33=false;
                }
                break;
            case R.id.b34:
                if(!bb34){
                    b34.setColor(main);
                    bbb34=colormain;
                    arr[32]="1";
                    bb34=true;
                }else{
                    b34.setColor(Color.BLACK);
                    bbb34="#000000";
                    arr[15]="0";
                    bb34=false;
                }
                break;
            case R.id.b35:
                if(!bb35){
                    b35.setColor(main);
                    bbb35=colormain;
                    arr[34]="1";
                    bb35=true;
                }else{
                    b35.setColor(Color.BLACK);
                    bbb35="#000000";
                    arr[15]="0";
                    bb35=false;
                }
                break;
            case R.id.b36:
                if(!bb36){
                    b36.setColor(main);
                    bbb36=colormain;
                    arr[35]="1";
                    bb36=true;
                }else{
                    b36.setColor(Color.BLACK);
                    bbb36="#000000";
                    arr[15]="0";
                    bb36=false;
                }
                break;
            case R.id.b37:
                if(!bb37){
                    b37.setColor(main);
                    bbb37=colormain;
                    arr[36]="1";
                    bb37=true;
                }else{
                    b37.setColor(Color.BLACK);
                    bbb37="#000000";
                    arr[15]="0";
                    bb37=false;
                }
                break;
            case R.id.b38:
                if(!bb38){
                    b38.setColor(main);
                    bbb38=colormain;
                    arr[37]="1";
                    bb38=true;
                }else{
                    b38.setColor(Color.BLACK);
                    bbb38="#000000";
                    arr[15]="0";
                    bb38=false;
                }
                break;
            case R.id.b39:
                if(!bb39){
                    b39.setColor(main);
                    bbb39=colormain;
                    arr[38]="1";
                    bb39=true;
                }else{
                    b39.setColor(Color.BLACK);
                    bbb39="#000000";
                    arr[15]="0";
                    bb39=false;
                }
                break;
            case R.id.b40:
                if(!bb40){
                    b40.setColor(main);
                    bbb40=colormain;
                    arr[39]="1";
                    bb40=true;
                }else{
                    b40.setColor(Color.BLACK);
                    bbb40="#000000";
                    arr[15]="0";
                    bb40=false;
                }
                break;
            case R.id.b41:
                if(!bb41){
                    b41.setColor(main);
                    bbb41=colormain;
                    arr[47]="1";
                    bb41=true;
                }else{
                    b41.setColor(Color.BLACK);
                    bbb41="#000000";
                    arr[15]="0";
                    bb41=false;
                }
                break;
            case R.id.b42:
                if(!bb42){
                    b42.setColor(main);
                    bbb42=colormain;
                    arr[46]="1";
                    bb42=true;
                }else{
                    b42.setColor(Color.BLACK);
                    bbb42="#000000";
                    arr[15]="0";
                    bb42=false;
                }
                break;
            case R.id.b43:
                if(!bb43){
                    b43.setColor(main);
                    bbb43=colormain;
                    arr[45]="1";
                    bb43=true;
                }else{
                    b43.setColor(Color.BLACK);
                    bbb43="#000000";
                    arr[15]="0";
                    bb43=false;
                }
                break;
            case R.id.b44:
                if(!bb44){
                    b44.setColor(main);
                    bbb44=colormain;
                    arr[44]="1";
                    bb44=true;
                }else{
                    b44.setColor(Color.BLACK);
                    bbb44="#000000";
                    arr[15]="0";
                    bb44=false;
                }
                break;
            case R.id.b45:
                if(!bb45){
                    b45.setColor(main);
                    bbb45=colormain;
                    arr[43]="1";
                    bb45=true;
                }else{
                    b45.setColor(Color.BLACK);
                    bbb45="#000000";
                    arr[15]="0";
                    bb45=false;
                }
                break;
            case R.id.b46:
                if(!bb46){
                    b46.setColor(main);
                    bbb46=colormain;
                    arr[42]="1";
                    bb46=true;
                }else{
                    b46.setColor(Color.BLACK);
                    bbb46="#000000";
                    arr[15]="0";
                    bb46=false;
                }
                break;
            case R.id.b47:
                if(!bb47){
                    b47.setColor(main);
                    bbb47=colormain;
                    arr[41]="1";
                    bb47=true;
                }else{
                    b47.setColor(Color.BLACK);
                    bbb47="#000000";
                    arr[15]="0";
                    bb47=false;
                }
                break;
            case R.id.b48:
                if(!bb48){
                    b48.setColor(main);
                    bbb48=colormain;
                    arr[40]="1";
                    bb48=true;
                }else{
                    b48.setColor(Color.BLACK);
                    bbb48="#000000";
                    arr[15]="0";
                    bb48=false;
                }
                break;
            case R.id.b49:
                if(!bb49){
                    b49.setColor(main);
                    bbb49=colormain;
                    arr[48]="1";
                    bb49=true;
                }else{
                    b49.setColor(Color.BLACK);
                    bbb49="#000000";
                    arr[15]="0";
                    bb49=false;
                }
                break;
            case R.id.b50:
                if(!bb50){
                    b50.setColor(main);
                    bbb50=colormain;
                    arr[49]="1";
                    bb50=true;
                }else{
                    b50.setColor(Color.BLACK);
                    bbb50="#000000";
                    arr[15]="0";
                    bb50=false;
                }
                break;
            case R.id.b51:
                if(!bb51){
                    b51.setColor(main);
                    bbb51=colormain;
                    arr[50]="1";
                    bb51=true;
                }else{
                    b51.setColor(Color.BLACK);
                    bbb51="#000000";
                    arr[15]="0";
                    bb51=false;
                }
                break;
            case R.id.b52:
                if(!bb52){
                    b52.setColor(main);
                    bbb52=colormain;
                    arr[51]="1";
                    bb52=true;
                }else{
                    b52.setColor(Color.BLACK);
                    bbb52="#000000";
                    arr[15]="0";
                    bb52=false;
                }
                break;
            case R.id.b53:
                if(!bb53){
                    b53.setColor(main);
                    bbb53=colormain;
                    arr[52]="1";
                    bb53=true;
                }else{
                    b53.setColor(Color.BLACK);
                    bbb53="#000000";
                    arr[15]="0";
                    bb53=false;
                }
                break;
            case R.id.b54:
                if(!bb54){
                    b54.setColor(main);
                    bbb54=colormain;
                    arr[53]="1";
                    bb54=true;
                }else{
                    b54.setColor(Color.BLACK);
                    bbb54="#000000";
                    arr[15]="0";
                    bb54=false;
                }
                break;
            case R.id.b55:
                if(!bb55){
                    b55.setColor(main);
                    bbb55=colormain;
                    arr[54]="1";
                    bb55=true;
                }else{
                    b55.setColor(Color.BLACK);
                    bbb55="#000000";
                    arr[15]="0";
                    bb55=false;
                }
                break;
            case R.id.b56:
                if(!bb56){
                    b56.setColor(main);
                    bbb56=colormain;
                    arr[55]="1";
                    bb56=true;
                }else{
                    b56.setColor(Color.BLACK);
                    bbb56="#000000";
                    arr[15]="0";
                    bb56=false;
                }
                break;
            case R.id.b57:
                if(!bb57){
                    b57.setColor(main);
                    bbb57=colormain;
                    arr[63]="1";
                    bb57=true;
                }else{
                    b57.setColor(Color.BLACK);
                    bbb57="#000000";
                    arr[15]="0";
                    bb57=false;
                }
                break;
            case R.id.b58:
                if(!bb58){
                    b58.setColor(main);
                    bbb58=colormain;
                    arr[62]="1";
                    bb58=true;
                }else{
                    b58.setColor(Color.BLACK);
                    bbb58="#000000";
                    arr[15]="0";
                    bb58=false;
                }
                break;
            case R.id.b59:
                if(!bb59){
                    b59.setColor(main);
                    bbb59=colormain;
                    arr[61]="1";
                    bb59=true;
                }else{
                    b59.setColor(Color.BLACK);
                    bbb59="#000000";
                    arr[15]="0";
                    bb59=false;
                }
                break;
            case R.id.b60:
                if(!bb60){
                    b60.setColor(main);
                    bbb60=colormain;
                    arr[60]="1";
                    bb60=true;
                }else{
                    b60.setColor(Color.BLACK);
                    bbb60="#000000";
                    arr[15]="0";
                    bb60=false;
                }
                break;
            case R.id.b61:
                if(!bb61){
                    b61.setColor(main);
                    bbb61=colormain;
                    arr[59]="1";
                    bb61=true;
                }else{
                    b61.setColor(Color.BLACK);
                    bbb61="#000000";
                    arr[15]="0";
                    bb61=false;
                }
                break;
            case R.id.b62:
                if(!bb62){
                    b62.setColor(main);
                    bbb62=colormain;
                    arr[58]="1";
                    bb62=true;
                }else{
                    b62.setColor(Color.BLACK);
                    bbb62="#000000";
                    arr[15]="0";
                    bb62=false;
                }
                break;
            case R.id.b63:
                if(!bb63){
                    b63.setColor(main);
                    bbb63=colormain;
                    arr[57]="1";
                    bb63=true;
                }else{
                    b63.setColor(Color.BLACK);
                    bbb63="#000000";
                    arr[15]="0";
                    bb63=false;
                }
                break;
            case R.id.b64:
                if(!bb64){
                    b64.setColor(main);
                    bbb64=colormain;
                    arr[56]="1";
                    bb64=true;
                }else{
                    b64.setColor(Color.BLACK);
                    bbb64="#000000";
                    arr[15]="0";
                    bb64=false;
                }
                break;
        }

    }

    private void Disconnect()
    {
        if (btSocket!=null) //If the btSocket is busy
        {
            try
            {
                btSocket.close(); //close connection
            }
            catch (IOException e)
            { msg("Error");}
        }
        finish(); //return to the first layout

    }

    private void turnOffLed()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("TF".toString().getBytes());
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }
        b1.setColor(Color.BLACK);b11.setColor(Color.BLACK);b21.setColor(Color.BLACK);
        b2.setColor(Color.BLACK);b12.setColor(Color.BLACK);b22.setColor(Color.BLACK);
        b3.setColor(Color.BLACK);b13.setColor(Color.BLACK);b23.setColor(Color.BLACK);
        b4.setColor(Color.BLACK);b14.setColor(Color.BLACK);b24.setColor(Color.BLACK);
        b5.setColor(Color.BLACK);b15.setColor(Color.BLACK);b25.setColor(Color.BLACK);
        b6.setColor(Color.BLACK);b16.setColor(Color.BLACK);b26.setColor(Color.BLACK);
        b7.setColor(Color.BLACK);b17.setColor(Color.BLACK);b27.setColor(Color.BLACK);
        b8.setColor(Color.BLACK);b18.setColor(Color.BLACK);b28.setColor(Color.BLACK);
        b9.setColor(Color.BLACK);b19.setColor(Color.BLACK);b29.setColor(Color.BLACK);
        b10.setColor(Color.BLACK);b20.setColor(Color.BLACK);b30.setColor(Color.BLACK);
        b31.setColor(Color.BLACK);b41.setColor(Color.BLACK);b51.setColor(Color.BLACK);
        b32.setColor(Color.BLACK);b42.setColor(Color.BLACK);b52.setColor(Color.BLACK);
        b33.setColor(Color.BLACK);b43.setColor(Color.BLACK);b53.setColor(Color.BLACK);
        b34.setColor(Color.BLACK);b44.setColor(Color.BLACK);b54.setColor(Color.BLACK);
        b35.setColor(Color.BLACK);b45.setColor(Color.BLACK);b55.setColor(Color.BLACK);
        b36.setColor(Color.BLACK);b46.setColor(Color.BLACK);b56.setColor(Color.BLACK);
        b37.setColor(Color.BLACK);b47.setColor(Color.BLACK);b57.setColor(Color.BLACK);
        b38.setColor(Color.BLACK);b48.setColor(Color.BLACK);b58.setColor(Color.BLACK);
        b39.setColor(Color.BLACK);b49.setColor(Color.BLACK);b59.setColor(Color.BLACK);
        b40.setColor(Color.BLACK);b50.setColor(Color.BLACK);b60.setColor(Color.BLACK);
        b61.setColor(Color.BLACK);b62.setColor(Color.BLACK);b63.setColor(Color.BLACK);
        b64.setColor(Color.BLACK);
        bb1=false;bb2=false;bb3=false;bb4=false;bb5=false;bb6=false;bb7=false;bb8=false;
        bb9=false;bb10=false;bb11=false;bb12=false;bb13=false;bb14=false;bb15=false;bb16=false;
        bb17=false;bb18=false;bb19=false;bb20=false;bb21=false;bb22=false;bb23=false;bb24=false;
        bb25=false;bb26=false;bb27=false;bb28=false;bb29=false;bb30=false;bb31=false;bb32=false;
        bb33=false;bb34=false;bb35=false;bb36=false;bb37=false;bb38=false;bb39=false;bb40=false;
        bb41=false;bb42=false;bb43=false;bb44=false;bb45=false;bb46=false;bb47=false;bb48=false;
        bb49=false;bb50=false;bb51=false;bb52=false;bb53=false;bb54=false;bb55=false;bb56=false;
        bb57=false;bb58=false;bb59=false;bb60=false;bb61=false;bb62=false;bb63=false;bb64=false;
        bbb1="#000000";bbb2="#000000";bbb3="#000000";bbb4="#000000";bbb5="#000000";bbb6="#000000";bbb7="#000000";bbb8="#000000";bbb9="#000000";bbb10="#000000";
        bbb11="#000000";bbb12="#000000";bbb13="#000000";bbb14="#000000";bbb15="#000000";bbb16="#000000";bbb17="#000000";bbb18="#000000";bbb19="#000000";bbb20="#000000";
        bbb21="#000000";bbb22="#000000";bbb23="#000000";bbb24="#000000";bbb25="#000000";bbb26="#000000";bbb27="#000000";bbb28="#000000";bbb29="#000000";bbb30="#000000";
        bbb31="#000000";bbb32="#000000";bbb33="#000000";bbb34="#000000";bbb35="#000000";bbb36="#000000";bbb37="#000000";bbb38="#000000";bbb39="#000000";bbb40="#000000";
        bbb41="#000000";bbb42="#000000";bbb43="#000000";bbb44="#000000";bbb45="#000000";bbb46="#000000";bbb47="#000000";bbb48="#000000";bbb49="#000000";bbb50="#000000";
        bbb51="#000000";bbb52="#000000";bbb53="#000000";bbb54="#000000";bbb55="#000000";bbb56="#000000";bbb57="#000000";bbb58="#000000";bbb59="#000000";bbb60="#000000";
        bbb61="#000000";bbb62="#000000";bbb63="#000000";bbb64="#000000";
    }

    private void turnOnLed()
    {

        /*if(ss.length()==1){
            ss="00"+ss;
            string = bbb1 + bbb2 + bbb3 + bbb4 + bbb5 + bbb6 + bbb7 + bbb8 + bbb16 + bbb15 + bbb14 + bbb13 + bbb12 + bbb11 + bbb10 + bbb9 +
                    bbb17 + bbb18 + bbb19 + bbb20 + bbb21 + bbb22 + bbb23 + bbb24 + bbb32 + bbb31 + bbb30 + bbb29 + bbb28 + bbb27 + bbb26 + bbb25 +
                    bbb33 + bbb34 + bbb35 + bbb36 + bbb37 + bbb38 + bbb39 + bbb40 + bbb48 + bbb47 + bbb46 + bbb45 + bbb44 + bbb43 + bbb42 + bbb41 +
                    bbb49 + bbb50 + bbb51 + bbb52 + bbb53 + bbb54 + bbb55 + bbb56 + bbb64 + bbb63 + bbb62 + bbb61 + bbb60 + bbb59 + bbb58 + bbb57 + ss;
            t.setText(ss);
        }
        else if(ss.length()==2){
            ss="0"+ss;
            string = bbb1 + bbb2 + bbb3 + bbb4 + bbb5 + bbb6 + bbb7 + bbb8 + bbb16 + bbb15 + bbb14 + bbb13 + bbb12 + bbb11 + bbb10 + bbb9 +
                    bbb17 + bbb18 + bbb19 + bbb20 + bbb21 + bbb22 + bbb23 + bbb24 + bbb32 + bbb31 + bbb30 + bbb29 + bbb28 + bbb27 + bbb26 + bbb25 +
                    bbb33 + bbb34 + bbb35 + bbb36 + bbb37 + bbb38 + bbb39 + bbb40 + bbb48 + bbb47 + bbb46 + bbb45 + bbb44 + bbb43 + bbb42 + bbb41 +
                    bbb49 + bbb50 + bbb51 + bbb52 + bbb53 + bbb54 + bbb55 + bbb56 + bbb64 + bbb63 + bbb62 + bbb61 + bbb60 + bbb59 + bbb58 + bbb57 + ss;
            t.setText(ss);
        }
         else if(ss.length()==3) {
            string = bbb1 + bbb2 + bbb3 + bbb4 + bbb5 + bbb6 + bbb7 + bbb8 + bbb16 + bbb15 + bbb14 + bbb13 + bbb12 + bbb11 + bbb10 + bbb9 +
                    bbb17 + bbb18 + bbb19 + bbb20 + bbb21 + bbb22 + bbb23 + bbb24 + bbb32 + bbb31 + bbb30 + bbb29 + bbb28 + bbb27 + bbb26 + bbb25 +
                    bbb33 + bbb34 + bbb35 + bbb36 + bbb37 + bbb38 + bbb39 + bbb40 + bbb48 + bbb47 + bbb46 + bbb45 + bbb44 + bbb43 + bbb42 + bbb41 +
                    bbb49 + bbb50 + bbb51 + bbb52 + bbb53 + bbb54 + bbb55 + bbb56 + bbb64 + bbb63 + bbb62 + bbb61 + bbb60 + bbb59 + bbb58 + bbb57 + ss;
            t.setText(ss);
        }*/
        string = bbb1 + bbb2 + bbb3 + bbb4 + bbb5 + bbb6 + bbb7 + bbb8 + bbb16 + bbb15 + bbb14 + bbb13 + bbb12 + bbb11 + bbb10 + bbb9 +
                bbb17 + bbb18 + bbb19 + bbb20 + bbb21 + bbb22 + bbb23 + bbb24 + bbb32 + bbb31 + bbb30 + bbb29 + bbb28 + bbb27 + bbb26 + bbb25 +
                bbb33 + bbb34 + bbb35 + bbb36 + bbb37 + bbb38 + bbb39 + bbb40 + bbb48 + bbb47 + bbb46 + bbb45 + bbb44 + bbb43 + bbb42 + bbb41 +
                bbb49 + bbb50 + bbb51 + bbb52 + bbb53 + bbb54 + bbb55 + bbb56 + bbb64 + bbb63 + bbb62 + bbb61 + bbb60 + bbb59 + bbb58 + bbb57;

        if (btSocket!=null)
        {   byte[] msgBuffer = string.getBytes();
            try
            {
                btSocket.getOutputStream().write(msgBuffer);
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }
    }

    // fast way to call Toast
    private void msg(String s)
    {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }
    private class ConnectBT extends AsyncTask<Void, Void, Void>  // UI thread
    {
        private boolean ConnectSuccess = true; //if it's here, it's almost connected

        @Override
        protected void onPreExecute()
        {
            progress = ProgressDialog.show(ledControl.this, "Connecting...", "Please wait!!!");  //show a progress dialog
        }

        @Override
        protected Void doInBackground(Void... devices) //while the progress dialog is shown, the connection is done in background
        {
            try
            {
                if (btSocket == null || !isBtConnected)
                {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();//start connection
                }
            }
            catch (IOException e)
            {
                ConnectSuccess = false;//if the try failed, you can check the exception here
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) //after the doInBackground, it checks if everything went fine
        {
            super.onPostExecute(result);

            if (!ConnectSuccess)
            {
                msg("Connection Failed. Is it a SPP Bluetooth? Try again.");
                finish();
            }
            else
            {
                msg("Connected.");
                isBtConnected = true;
            }
            progress.dismiss();
        }
    }
}
