#include <stdio.h>

int main() {
	int result = 1;
	int counter = 10;
	
	printf("1\n");
	for(int i=1; i<=counter; i++){
		result = result*2;
		printf("%d\n", result);
		}
	}