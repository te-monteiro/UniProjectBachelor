EXIT = 1
WRITE = 4
LINUX_SYSCALL = 0x80


.data
 msg: .ascii "Hello, world!\n"
 msglen = (. - msg) 
 tam1 = 'a'-'A'                 #tam1 representa o que falta para ser maiuscula
.text
 .global _start
_start: movl    $msglen, %edx    
        movl    $msg, %ecx
	mov     (%ecx), %al
 ciclo:
	cmp     $'a', %al 
	jb      maiuscula
	
 maiuscula:
	sub     $tam1, %al    
	

        movl    $1, %ebx
        movl    $WRITE, %eax
        int     $LINUX_SYSCALL

        movl    $0, %ebx
        movl    $EXIT, %eax
        int     $LINUX_SYSCALL

