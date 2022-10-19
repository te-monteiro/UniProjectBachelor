EXIT = 1
WRITE = 4
LINUX_SYSCALL = 0x80


.data
	vetor: .int -1, 5, 1, 1, 4
	
	len = (.- vetor) / 4
	soma: .int 0
.text
 .global _start
  _start: movl    $len, %eax
	  movl	  $vetor, %ebx
 ciclo: incl (%ebx)
	  add     %ebx, %ecx
	  
	  add     $4, %ebx
	  dec     %eax
	  jnz     ciclo
	  movl    %ecx, (soma)

	  mov     $0, %ebx
          movl    $EXIT, %eax
	  int 	  $LINUX_SYSCALL
