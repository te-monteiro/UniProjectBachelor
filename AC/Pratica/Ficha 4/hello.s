EXIT = 1
WRITE = 4
LINUX_SYSCALL = 0x80


.data
 msg: .ascii "Ola, world!\n"
 msglen = (. - msg) # ponto e a memoria corrente, ou seja o inicio menos o fim, que corresponde ao msg, vai dar o numero de caracteres que #queremos escrever 


.text
 .global _start
_start: movl    $msglen, %edx    #esta a espera de que mensagens tem que escrever e qts caracteres queremos escrever
        movl    $msg, %ecx
        movl    $1, %ebx
        movl    $WRITE, %eax
        int     $LINUX_SYSCALL

        movl    $0, %ebx
        movl    $EXIT, %eax
        int     $LINUX_SYSCALL

