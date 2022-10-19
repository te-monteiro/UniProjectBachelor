EXIT = 1
WRITE = 4
LINUX_SYSCALL = 0x80


.data
	var1:   .int 35
	var2:   .int 10
	result: .int 0
.text
 .global _start
  _start: movl    (var1), %ecx
          movl    (var2), %ebx
          add     %ecx, %ebx
          movl    %ebx, result

	  mov     $0, %ebx
          movl    $EXIT, %eax
	  int 	  $LINUX_SYSCALL
