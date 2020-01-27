// This file is automatically generated by the Open Roberta Lab.

#include <Arduino.h>
#include <math.h>
#include<ArduinoSTL/src/ArduinoSTL.h>
#include<list>
#include <RobertaFunctions/NEPODefs.h>


std::list<double> ___item;
std::list<bool> ___item2;
std::list<String> ___item3;
std::list<unsigned int> ___item4;
int _led_L = 13;
void setup()
{
    Serial.begin(9600); 
    pinMode(_led_L, OUTPUT);
    ___item = _createListRepeat(5,(double)5);
    ___item2= _createListRepeat(5,true);
    ___item3= _createListRepeat(5,(String)"123");
    ___item4= _createListRepeat(5,RGB(0x33,0xcc,0x00));
}

void loop()
{
}
