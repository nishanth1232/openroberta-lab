// This file is automatically generated by the Open Roberta Lab.

#include <Arduino.h>
#include <math.h>
#include <ArduinoSTL/src/ArduinoSTL.h>
#include <list>
#include <RobertaFunctions/NEPODefs.h>


std::list<double> ___item;
std::list<double> ___item2;
int _led_L = 13;
void setup()
{
    Serial.begin(9600); 
    pinMode(_led_L, OUTPUT);
    ___item = {0, 0, 0};
    ___item2 = {0, 0, 0};
}

void loop()
{
    ___item2 = _getSubList(___item, 0, 0);
    ___item2 = _getSubList(___item, 0, ___item.size() - 1 - 0);
    ___item2 = _getSubList(___item, 0, ___item.size() - 1);
    ___item2 = _getSubList(___item, ___item.size() - 1 - 0, 0);
    ___item2 = _getSubList(___item, ___item.size() - 1 - 0, ___item.size() - 1 - 0);
    ___item2 = _getSubList(___item, ___item.size() - 1 - 0, ___item.size() - 1);
    ___item2 = _getSubList(___item, 0, 0);
    ___item2 = _getSubList(___item, 0, ___item.size() - 1 - 0);
    ___item2 = _getSubList(___item, 0, ___item.size() - 1);
}
