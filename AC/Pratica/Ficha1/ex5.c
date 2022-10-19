#include <stdio.h>

int main(){

	unsigned long C = sizeof(char);
	printf("tamanho %lu\n", C);

	unsigned long S = sizeof(short);
	printf("tamanho %lu\n", S);

	unsigned long I = sizeof(int);
	printf("tamanho %lu\n", I);

	unsigned long UI = sizeof(unsigned int);
	printf("tamanho %lu\n", UI);

	unsigned long L = sizeof(long);
	printf("tamanho %lu\n", L);

	unsigned long UL = sizeof(unsigned long);
	printf("tamanho %lu\n", UL);

	unsigned long ULL = sizeof(unsigned long long);
	printf("tamanho %lu\n", ULL);

	unsigned long F = sizeof(float);
	printf("tamanho %lu\n", F);

	unsigned long D = sizeof(double);
	printf("tamanho %lu\n", D);
}
