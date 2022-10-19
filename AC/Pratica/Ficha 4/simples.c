#include <stdio.h>
#include <stdlib.h>


// Este programa parece funcionar bem, mas na
// realidade tem erros! Localize-os e corrija-os...

int soma( int x[3] ) {
  return  x[0]+x[1]+x[2];
}

char *dupstr (char *str) {
  // find the size of the original string
  int size = 0;

  while (str[size] != '\0')
    size++;

  // allocate space for the new string
  char *newstr = malloc (size);

  // copy original string to new string 
  int i;

  for (i = 0; i < size; i++)
    newstr[i] = str[i];

  // add the terminator to the string
  newstr[size] = '\0';

  // return the new string
  return newstr;
}


char maior( char c ) {
  return c+ ('A'-'a');  // passa minuscula a maiuscula
}

void transform( char *str ) {
  int i = 0;
  while ( str[i] != '\0' ) {
    if ( str[i] >'a' && str[i]<'z' ) str[i] = maior( str[i] );
  }
}


int main (int argc, char *arvg[]) {
  char *s1 = "abcde0";
  char *s2 = dupstr (s1);
  int z[2];
  int x;

  printf("Original = '%s'\n   Copia = '%c'\n", s1, s2);
  transforma( s2 );
  z[0]=z[1]=z[2]=1;
  soma( z );
  printf( "nova= %s, X=%d\n", s2, x );
  return 0;
}

