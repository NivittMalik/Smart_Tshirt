#include <Adafruit_NeoPixel.h>

#include <Adafruit_NeoMatrix.h>
#include <gamma.h>

#include <Adafruit_GFX.h>
#include <Adafruit_SPITFT.h>
#include <Adafruit_SPITFT_Macros.h>
#include <gfxfont.h>

#include <SoftwareSerial.h>
#include<string.h>

#define FACTORYRESET_ENABLE     1

#define PIN                     6   // Which pin on the Arduino is connected to the NeoPixels?

// Example for NeoPixel 8x8 Matrix.  In this application we'd like to use it 
// with the back text positioned along the bottom edge.
// When held that way, the first pixel is at the top left, and
// lines are arranged in columns, zigzag order.  The 8x8 matrix uses
// 800 KHz (v2) pixels that expect GRB color data.
Adafruit_NeoMatrix matrix = Adafruit_NeoMatrix(8, 8, PIN,
  NEO_MATRIX_TOP     + NEO_MATRIX_LEFT +
  NEO_MATRIX_COLUMNS + NEO_MATRIX_ZIGZAG,
  NEO_GRB            + NEO_KHZ800);
              
String command;    
String arr="";
String a,b,c;
int bright;
int i,x=0,y=0;

void setup(){
  
  Serial.begin(9600);
  matrix.begin();
  matrix.setBrightness(200);
  matrix.fillScreen(0);
  matrix.show(); // This sends the updated pixel colors to the hardware.
  }
  
void loop(){
  matrix.fillScreen(0);
  while(Serial.available()>0){
   command = Serial.readString();
  // Serial.print(command);
   if(command == ';'){break;}
   Serial.flush();
   for(i=0;i<64;i++)
   {
    if(i!=0 && (i)%8==0){
      x++;
      y=0;
    }
    
      Serial.print(x);
      Serial.print(y);
      Serial.println(command.substring(7*i,7*i+7));
      colorConverter(command.substring(7*i,7*i+7),x,y); 
      matrix.show(); // This sends the updated pixel colors to the hardware.
      //delay(10);
      y++;
      
   }
 }
   x=0;y=0;
   command="";      
}
void colorConverter(String hexValue,int x,int y)    // converts HEX value to RGB
{

long number = (long) strtol( &hexValue[1], NULL, 16);
    int r = number >> 16;
    int g = number >> 8 & 0xFF;
    int b = number & 0xFF;
    uint32_t c=matrix.Color(r,g,b);
    
    matrix.drawPixel(x,y,c);
    
   
}





