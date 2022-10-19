#include <stdio.h>

int main(){
	
	unsigned char b1 = 127;
	
	
	unsigned char a =  b1|1;

	unsigned char b = b1 & 0X0F;

	unsigned char c = b1 & 0xFD;

	unsigned char d = b1 & 1;

	unsigned int e = b1 << 4;

	unsigned int f = b1 >> 2;

	unsigned int g = (b1 + b1 + b1) << 2;

	printf("%d\n", b1);
	printf("%d\n", a);
	printf("%d\n", b);
	printf("%d\n", c);
	printf("%d\n", d);
	printf("%d\n", e);
	printf("%d\n", f);
	printf("%d\n", g);

}
