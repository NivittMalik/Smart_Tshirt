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
   //Serial.print(a+b+c);   
   //bright = abc.toInt();
    
//    if(arr.length()>0){
//      matrix.fillScreen(0);
//    //Serial.println(arr); 
//    //ledon(arr,matrix.Color(0,0,0),matrix.Color(255,0,0));
//    arr="";
//    matrix.show(); // This sends the updated pixel colors to the hardware.
     
   }
    
     


void colorConverter(String hexValue,int x,int y)
{

long number = (long) strtol( &hexValue[1], NULL, 16);
    int r = number >> 16;
    int g = number >> 8 & 0xFF;
    int b = number & 0xFF;
    uint32_t c=matrix.Color(r,g,b);
    
    matrix.drawPixel(x,y,c);
    
   
}

//void ledon(String arr, uint32_t g, uint32_t r ){
////void ledon(uint32_t g, uint32_t r ){
// if(arr[0]=='1')
//    matrix.drawPixel(0,0,r);
// else 
//    matrix.drawPixel(0,0,g);
// if(arr[1]=='1')
//    matrix.drawPixel(0,1,r);
// else 
//    matrix.drawPixel(0,1,g);
// if(arr[2]=='1')
//    matrix.drawPixel(0,2,r);
// else 
//    matrix.drawPixel(0,2,g);
// if(arr[3]=='1')
//    matrix.drawPixel(0,3,r);
// else 
//    matrix.drawPixel(0,3,g);
// if(arr[4]=='1')
//    matrix.drawPixel(0,4,r);
// else 
//    matrix.drawPixel(0,4,g);
// if(arr[5]=='1')
//    matrix.drawPixel(0,5,r);
// else 
//    matrix.drawPixel(0,5,g);
// if(arr[6]=='1')
//    matrix.drawPixel(0,6,r);
// else 
//    matrix.drawPixel(0,6,g);
// if(arr[7]=='1')
//    matrix.drawPixel(0,7,r);
// else 
//    matrix.drawPixel(0,7,g);
// if(arr[8]=='1')
//    matrix.drawPixel(1,0,r);
// else 
//    matrix.drawPixel(1,0,g);
// if(arr[9]=='1')
//    matrix.drawPixel(1,1,r);
// else 
//    matrix.drawPixel(1,1,g);
// if(arr[10]=='1')
//    matrix.drawPixel(1,2,r);
// else 
//    matrix.drawPixel(1,2,g);
// if(arr[11]=='1')
//    matrix.drawPixel(1,3,r);
// else 
//    matrix.drawPixel(1,3,g);
// if(arr[12]=='1')
//    matrix.drawPixel(1,4,r);
// else 
//    matrix.drawPixel(1,4,g);
// if(arr[13]=='1')
//    matrix.drawPixel(1,5,r);
// else 
//    matrix.drawPixel(1,5,g);
// if(arr[14]=='1')
//    matrix.drawPixel(1,6,r);
// else 
//    matrix.drawPixel(1,6,g);
// if(arr[15]=='1')
//    matrix.drawPixel(1,7,r);
// else 
//    matrix.drawPixel(1,7,g);
// if(arr[16]=='1')
//    matrix.drawPixel(2,0,r);
// else 
//    matrix.drawPixel(2,0,g);
// if(arr[17]=='1')
//    matrix.drawPixel(2,1,r);
// else 
//    matrix.drawPixel(2,1,g);
// if(arr[18]=='1')
//    matrix.drawPixel(2,2,r);
// else 
//    matrix.drawPixel(2,2,g);
// if(arr[19]=='1')
//    matrix.drawPixel(2,3,r);
// else 
//    matrix.drawPixel(2,3,g);
// if(arr[20]=='1')
//    matrix.drawPixel(2,4,r);
// else 
//    matrix.drawPixel(2,4,g);
// if(arr[21]=='1')
//    matrix.drawPixel(2,5,r);
// else 
//    matrix.drawPixel(2,5,g);
// if(arr[22]=='1')
//    matrix.drawPixel(2,6,r);
// else 
//    matrix.drawPixel(2,6,g);
// if(arr[23]=='1')
//    matrix.drawPixel(2,7,r);
// else 
//    matrix.drawPixel(2,7,g);
// if(arr[24]=='1')
//    matrix.drawPixel(3,0,r);
// else 
//    matrix.drawPixel(3,0,g);
// if(arr[25]=='1')
//    matrix.drawPixel(3,1,r);
// else 
//    matrix.drawPixel(3,1,g);
// if(arr[26]=='1')
//    matrix.drawPixel(3,2,r);
// else 
//    matrix.drawPixel(3,2,g);
// if(arr[27]=='1')
//    matrix.drawPixel(3,3,r);
// else 
//    matrix.drawPixel(3,3,g);
// if(arr[28]=='1')
//    matrix.drawPixel(3,4,r);
// else 
//    matrix.drawPixel(3,4,g);
// if(arr[29]=='1')
//    matrix.drawPixel(3,5,r);
// else 
//    matrix.drawPixel(3,5,g);
// if(arr[30]=='1')
//    matrix.drawPixel(3,6,r);
// else 
//    matrix.drawPixel(3,6,g);
// if(arr[31]=='1')
//    matrix.drawPixel(3,7,r);
// else 
//    matrix.drawPixel(3,7,g);
// if(arr[32]=='1')
//    matrix.drawPixel(4,0,r);
// else 
//    matrix.drawPixel(4,0,g);
// if(arr[33]=='1')
//    matrix.drawPixel(4,1,r);
// else 
//    matrix.drawPixel(4,1,g);
// if(arr[34]=='1')
//    matrix.drawPixel(4,2,r);
// else 
//    matrix.drawPixel(4,2,g);
// if(arr[35]=='1')
//    matrix.drawPixel(4,3,r);
// else 
//    matrix.drawPixel(4,3,g);
// if(arr[36]=='1')
//    matrix.drawPixel(4,4,r);
// else 
//    matrix.drawPixel(4,4,g);
// if(arr[37]=='1')
//    matrix.drawPixel(4,5,r);
// else 
//    matrix.drawPixel(4,5,g);
// if(arr[38]=='1')
//    matrix.drawPixel(4,6,r);
// else 
//    matrix.drawPixel(4,6,g);
// if(arr[39]=='1')
//    matrix.drawPixel(4,7,r);
// else 
//    matrix.drawPixel(4,7,g);
// if(arr[40]=='1')
//    matrix.drawPixel(5,0,r);
// else 
//    matrix.drawPixel(5,0,g);
// if(arr[41]=='1')
//    matrix.drawPixel(5,1,r);
// else 
//    matrix.drawPixel(5,1,g);
// if(arr[42]=='1')
//    matrix.drawPixel(5,2,r);
// else 
//    matrix.drawPixel(5,2,g);
// if(arr[43]=='1')
//    matrix.drawPixel(5,3,r);
// else 
//    matrix.drawPixel(5,3,g);
// if(arr[44]=='1')
//    matrix.drawPixel(5,4,r);
// else 
//    matrix.drawPixel(5,4,g);
// if(arr[45]=='1')
//    matrix.drawPixel(5,5,r);
// else 
//    matrix.drawPixel(5,5,g);
// if(arr[46]=='1')
//    matrix.drawPixel(5,6,r);
// else 
//    matrix.drawPixel(5,6,g);
// if(arr[47]=='1')
//    matrix.drawPixel(5,7,r);
// else 
//    matrix.drawPixel(5,7,g);
// if(arr[48]=='1')
//    matrix.drawPixel(6,0,r);
// else 
//    matrix.drawPixel(6,0,g);
// if(arr[49]=='1')
//    matrix.drawPixel(6,1,r);
// else 
//    matrix.drawPixel(6,1,g);
// if(arr[50]=='1')
//    matrix.drawPixel(6,2,r);
// else 
//    matrix.drawPixel(6,2,g);
// if(arr[51]=='1')
//    matrix.drawPixel(6,3,r);
// else 
//    matrix.drawPixel(6,3,g);
// if(arr[52]=='1')
//    matrix.drawPixel(6,4,r);
// else 
//    matrix.drawPixel(6,4,g);
// if(arr[53]=='1')
//    matrix.drawPixel(6,5,r);
// else 
//    matrix.drawPixel(6,5,g);
// if(arr[54]=='1')
//    matrix.drawPixel(6,6,r);
// else 
//    matrix.drawPixel(6,6,g);
// if(arr[55]=='1')
//    matrix.drawPixel(6,7,r);
// else 
//    matrix.drawPixel(6,7,g);
// if(arr[56]=='1')
//    matrix.drawPixel(7,0,r);
// else 
//    matrix.drawPixel(7,0,g);
// if(arr[57]=='1')
//    matrix.drawPixel(7,1,r);
// else 
//    matrix.drawPixel(7,1,g);
// if(arr[58]=='1')
//    matrix.drawPixel(7,2,r);
// else 
//    matrix.drawPixel(7,2,g);
// if(arr[59]=='1')
//    matrix.drawPixel(7,3,r);
// else 
//    matrix.drawPixel(7,3,g);
// if(arr[60]=='1')
//    matrix.drawPixel(7,4,r);
// else 
//    matrix.drawPixel(7,4,g);
// if(arr[61]=='1')
//    matrix.drawPixel(7,5,r);
// else 
//    matrix.drawPixel(7,5,g);
// if(arr[62]=='1')
//    matrix.drawPixel(7,6,r);
// else 
//    matrix.drawPixel(7,6,g);
// if(arr[63]=='1')
//    matrix.drawPixel(7,7,r);
// else 
//    matrix.drawPixel(7,7,g); 
//  
//}




