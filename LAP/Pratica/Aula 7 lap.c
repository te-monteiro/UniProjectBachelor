#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "LinkedList.h"

typedef struct Node {
    Data data;
    List next;
} Node;

static List newNode(Data val, List next)
{
    List n = malloc(sizeof(Node));
    if( n == NULL )
        return NULL;
    n->data = val;
    n->next = next;
    return n;
}

List listMakeRange(Data a, Data b)
{  // TECNICA ESSENCIAL: Ir fazendo crescer a lista no ultimo no'.
    if( a > b )
        return NULL;
    double i;
    List l = newNode(a, NULL), last = l;
    for( i = a + 1 ; i <= b ; i++ )
        last = last->next = newNode(i, NULL);

    return l;
}

/* Outra maneira, mais palavrosa, de escrever a funcao anterior:

List listMakeRange(Data a, Data b)
{
    if( a > b )
        return NULL;
    double i;
    List l = newNode(a, NULL);
    List last = l;
    for( i = a + 1 ; i <= b ; i++ ) {
        List q = newNode(i, NULL);
        last->next = q;
        last = q;
    }
    return l;
}
*/

int listLength(List l) {
    int count;
    for( count = 0 ; l != NULL ; l = l->next, count++ );
    return count;
}

bool listGet(List l, int idx, Data *res)
{
    int i;
    for( i = 0 ; i < idx && l != NULL ; i++, l = l->next );
    if( l == NULL )
        return false;
    else {
        *res = l->data;
        return true;
    }
}

List listPutAtHead(List l, Data val)
{
    return newNode(val, l);
}

List listPutAtEnd(List l, Data val)
{
    if( l == NULL )
        return newNode(val, NULL);
    else {
        List p;
        for( p = l ; p->next != NULL ; p = p->next ); // Stop at the last node
        p->next = newNode(val, NULL);  // Assign to the next of the last node
        return l;
    }
}

List listFilter(List l, BoolFun toKeep)
{  // TECNICA ESSENCIAL: Adicionar um no' auxiliar inicial para permitir tratamento uniforme.
      // Tente fazer sem o no' suplementar e veja como fica muito mais complicado.
    Node dummy;
    dummy.next = l;
    l = &dummy;
    while( l->next != NULL )
        if( toKeep(l->next->data) )
            l = l->next;
        else {
            List del = l->next;
            l->next = l->next->next;
            free(del);
        }
    return dummy.next;
}

void listPrint(List l)
{
    for( ; l != NULL ; l = l->next )
        printf("%lf\n", l->data);
}

static bool isEven(Data data) {
    return (int)data % 2 == 0;
}

static bool isOdd(Data data) {
    return (int)data % 2 != 0;
}

void listTest(void) {
    List l = listMakeRange(1.1, 7.8);
    printf("----------\n");
    listPrint(l);
    printf("----------\n");
    l = listFilter(l, isEven);
    listPrint(l);
    printf("----------\n");
    l = listFilter(l, isOdd);
    listPrint(l);
    printf("----------\n");
}

/*----------New Methods----------*/

List listClone(List l) {

	if(l == NULL)
		return NULL;
	
	List newList = newNode(l->data, NULL), res = newList;     /*Copia-se o primeiro nodo para a nova lista e guarda-se
                        				         esse nodo em res (guardar a cabeca)*/
	for (l = l->next; l!= NULL ; l = l->next) 
		newList = newList->next = newNode(l->data, NULL); /*Comeca-se o for na segunda posicao e vai-se adicionando
								 os nodos seguintes. (Mete-se NULL como parametro porque
								 este é atualizado ao mesmo tempo)*/
	return res;						 /*Retorno da cabeca porque está tudo ligado*/
}

/*Pomos o apontador para o proximo nodo, da cauda de l1, que se encontra a NULL, a apontar para a cabeça de l2*/
List listAppend (List l1, List l2) {
	
	if(l2==NULL)
		return l1;

	List c2 = listClone(l2), res = l1;	

	if(l1==NULL)
		return c2; 
	
	for( ; l1->next !=NULL ; l1 = l1->next);	// eu quero percorrer l1 ate a cauda, dps qd chegar a cauda do append 
	l1->next = c2;						// faco = c2
	
	
	return res;
}

/*Cria-se uma lista nova em que em vez de se adicionar no fim, adiciona-se no inicio*/
List listRev(List l) {
	
	List reverse = newNode(l->data, NULL);

	for(l = l->next; l!=NULL ; l = l->next) 		
		reverse = listPutAtHead(reverse, l->data);	

	return reverse;
}

/*RevInPlace implica utilizar os mesmos enderecos e nao criar nodos novos,
ou seja, inicialmente tudo aponta para a esquerda 1->2->3, basta rodar : 1<-2<-3*/
List listRevInPlace(List l) {

	List aux = NULL, aux2 = l;

	for( ; l != NULL ; l = aux2) { /*Ir de 2 em 2*/
		aux2 = l->next;
		l->next = aux;
		aux = l;
	}

	return aux;
	
}

/*Apagar todas os elementos repetidos*/
List listUniq(List l) {

	List head, l1, del;
	head = l;

	for( ; head != NULL; head = head->next) {  /*Para cada nodo, percorrer a lista toda a partir desse nodo*/
		l1 = head;
		while (l1->next != NULL) {
			if(head->data == l1->next->data) {
				 del = l1->next;
            			 l1->next = l1->next->next;
            			 free(del);      /*Avisa o sistema que pode usar este espaco de enderecamento*/
			}
			else
				l1 = l1->next; /*Caso nao seja igual da next (Esta no else porque se forem iguais da next dentro do if)*/
		}
	}

	return l;

}

/*Testes a toa, nao garanto que este codigo funcione devido aos comentarios kjdsfhhdsjk*/
int main() {

	List a = listMakeRange(1.1, 7.8); 
	List b = listMakeRange(8.7, 9.8);

	listPrint(a);
	printf("...............\n");
	listPrint(listUniq(a));

	return 0;

}	


