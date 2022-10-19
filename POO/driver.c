
#include <stdio.h>
#include <unistd.h>

#include "hw.h"
#include "driver.h"



void sendByte( unsigned char c ) {
    // not implemented
}

unsigned char recvByte( void ) { 
	while(((in(0x3FD) & 0x1) != 1) || ((in(0x3FD) & 0x2) != 0)); 
	return in(0x3f8);
}

void waitBusy(void) {
   // not implemented
}

