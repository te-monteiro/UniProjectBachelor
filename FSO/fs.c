#include "fs.h"
#include "disk.h"

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <errno.h>
#include <unistd.h>
#include <math.h>

#define FS_MAGIC           0xf0f03410
#define INODES_PER_BLOCK   64
#define POINTERS_PER_INODE 14

#define FALSE 0
#define TRUE 1

#define VALID 1
#define NON_VALID 0

#define FREE 0
#define NOT_FREE 1

struct fs_superblock {
	unsigned int magic;
	unsigned int nblocks;
	unsigned int ninodeblocks;
	unsigned int ninodes;
};
struct fs_superblock my_super;

struct fs_inode {
	unsigned int isvalid;
	unsigned int size;
	unsigned int direct[POINTERS_PER_INODE];
};

union fs_block {
	struct fs_superblock super;
	struct fs_inode inode[INODES_PER_BLOCK];
	char data[DISK_BLOCK_SIZE];
};

unsigned char * blockBitMap;

struct fs_inode inode;

int fs_format()
{
  union fs_block block;
  unsigned int i, nblocks, ninodeblocks;

  if(my_super.magic == FS_MAGIC){
    printf("Cannot format a mounted disk!\n");
    return -1;
  }
  nblocks = disk_size();
  block.super.magic = FS_MAGIC;
  block.super.nblocks = nblocks;
  block.super.ninodeblocks = (int)ceil((float)nblocks*0.1);
  block.super.ninodes = block.super.ninodeblocks * INODES_PER_BLOCK;
  
  printf("superblock:\n");
  printf("    %d blocks\n",block.super.nblocks);
  printf("    %d inode blocks\n",block.super.ninodeblocks);
  printf("    %d inodes\n",block.super.ninodes);

  /* escrita do superbloco */
  disk_write(0,block.data);
  ninodeblocks = block.super.ninodeblocks;

  /* preparacao da tabela de inodes */
  for( i = 0; i < INODES_PER_BLOCK; i++ )
    block.inode[i].isvalid = NON_VALID;

  /* escrita da tabela de inodes */
  for( i = 1; i <= ninodeblocks; i++)
    disk_write( i, block.data );

  return 0;
}

void fs_debug()
{
	union fs_block sBlock;
	union fs_block iBlock;
	unsigned int i, j, k;

	disk_read(0,sBlock.data);

	if(sBlock.super.magic != FS_MAGIC){
	  printf("disk unformatted !\n");
	  return;
	}
	printf("superblock:\n");
	printf("    %d blocks\n",sBlock.super.nblocks);
	printf("    %d inode blocks\n",sBlock.super.ninodeblocks);
	printf("    %d inodes\n",sBlock.super.ninodes);

	for( i = 1; i <= sBlock.super.ninodeblocks; i++){
	  disk_read( i, iBlock.data );
	  for( j = 0; j < INODES_PER_BLOCK; j++)
	    if( iBlock.inode[j].isvalid == VALID){
	      printf("-----\n inode: %d\n", (i-1)*INODES_PER_BLOCK + j);
	      printf("size: %d \n",iBlock.inode[j].size);
	      printf("blocks:");
	      for( k = 0; k < POINTERS_PER_INODE; k++)
			    if (iBlock.inode[j].direct[k]!=0)
				    printf("  %d",iBlock.inode[j].direct[k]);
		  printf("\n");
	    }
	}
}

int fs_mount()
{
  union fs_block block;
  int i, j, k;

  if(my_super.magic == FS_MAGIC){
    printf("disc already mounted!\n");
    return -1;
  }

  disk_read(0,block.data);
  if(block.super.magic != FS_MAGIC){
    printf("cannot mount an unformatted disc!\n");
    return -1;
  }
  if(block.super.nblocks != disk_size()){
    printf("file system size and disk size differ!\n");
    return -1;
  }

  /* CODIGO A FAZER */



  return 0;
}

int fs_create()
{
  int freeInode, inodeBlock;
  union fs_block block;
  int i, j;


  if(my_super.magic != FS_MAGIC){
    printf("disc not mounted\n");
    return -1;
  }
 
  /* CODIGO A FAZER */



  return freeInode;
}

void inode_load( int inumber, struct fs_inode *inode ){
  int inodeBlock;
  union fs_block block;

  if( inumber > my_super.ninodes ){
    printf("inode number too big \n");
    abort();
  }
  inodeBlock = 1 + (inumber/INODES_PER_BLOCK);
  disk_read( inodeBlock, block.data );
  *inode = block.inode[inumber % INODES_PER_BLOCK];
}

void inode_save( int inumber, struct fs_inode *inode ){
  int inodeBlock;
  union fs_block block;

  if( inumber > my_super.ninodes ){
    printf("inode number too big \n");
    abort();
  }
  inodeBlock = 1 + (inumber/INODES_PER_BLOCK);
  disk_read(inodeBlock, block.data );
  block.inode[inumber % INODES_PER_BLOCK] = *inode;
  disk_write( inodeBlock, block.data );
}

int fs_delete( int inumber )
{
  int i;

  if(my_super.magic != FS_MAGIC){
    printf("disc not mounted\n");
    return -1;
  }

  /* CODIGO A FAZER */
  
  return 0;
}

int fs_getsize( int inumber )
{

  if(my_super.magic != FS_MAGIC){
    printf("disc not mounted\n");
    return -1;
  }

  /* CODIGO A FAZER */

}


/**************************************************************/
int fs_read( int inumber, char *data, int length, int offset )
{
  int currentBlock, offsetCurrent, offsetInBlock;
  int bytesLeft, nCopy, bytesToRead;
  char *dst;
  union fs_block buff;

  if(my_super.magic != FS_MAGIC){
    printf("disc not mounted\n");
    return -1;
  }
  inode_load( inumber, &inode );
  if( inode.isvalid == NON_VALID ){
    printf("inode is not valid\n");
    return -1;
  }
  
  if( offset > inode.size ){
    printf("offset bigger that file size !\n");
    return -1;
  }

  /* CODIGO A FAZER */



  return bytesToRead;
}

/******************************************************************/
int getFreeBlock(){
  int i, found;

  i = 0;
  found = FALSE;
  do{
    if(blockBitMap[i] == FREE){
      found = TRUE;
      blockBitMap[i] = NOT_FREE;
    }
    else i++;
  }while((!found) && (i < my_super.nblocks));

  if(i == my_super.nblocks) return -1; /* nao ha' blocos livres */
  else return i;
}


int fs_write( int inumber, char *data, int length, int offset )
{
  int currentBlock, offsetCurrent, offsetInBlock;
  int bytesLeft, nCopy, bytesToWrite, newEntry;
  char *src;
  union fs_block buff;

  if(my_super.magic != FS_MAGIC){
    printf("disc not mounted\n");
    return -1;
  }
  inode_load( inumber, &inode );
  if( inode.isvalid == NON_VALID ){
    printf("inode is not valid\n");
    return -1;
  }

  if( offset > inode.size ){
    printf("starting to write after end of file\n");
    return -1;
  }

  /* CODIGO A FAZER */
 

  inode_save( inumber, &inode );
  return bytesToWrite;
}

