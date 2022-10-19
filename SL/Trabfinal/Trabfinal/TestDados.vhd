-- Vhdl test bench created from schematic C:\Users\Xilinx\Desktop\Trabfinal\Dados.sch - Wed Nov 23 19:34:04 2016
--
-- Notes: 
-- 1) This testbench template has been automatically generated using types
-- std_logic and std_logic_vector for the ports of the unit under test.
-- Xilinx recommends that these types always be used for the top-level
-- I/O of a design in order to guarantee that the testbench will bind
-- correctly to the timing (post-route) simulation model.
-- 2) To use this template as your testbench, change the filename to any
-- name of your choice with the extension .vhd, and use the "Source->Add"
-- menu in Project Navigator to import the testbench. Then
-- edit the user defined section below, adding code to generate the 
-- stimulus for your design.
--
LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
USE ieee.numeric_std.ALL;
LIBRARY UNISIM;
USE UNISIM.Vcomponents.ALL;
ENTITY Dados_Dados_sch_tb IS
END Dados_Dados_sch_tb;
ARCHITECTURE behavioral OF Dados_Dados_sch_tb IS 

   COMPONENT Dados
   PORT( CLK	:	IN	STD_LOGIC; 
          LOADTEMP	:	IN	STD_LOGIC; 
          CLEARTEMP	:	IN	STD_LOGIC; 
          ZERO	:	OUT	STD_LOGIC; 
          RES	:	OUT	STD_LOGIC_VECTOR (7 DOWNTO 0); 
          ANL	:	IN	STD_LOGIC; 
          ADD	:	IN	STD_LOGIC; 
          DEC	:	IN	STD_LOGIC; 
          PASSTHROUGH	:	IN	STD_LOGIC; 
          LOADC	:	IN	STD_LOGIC; 
          CLEARC	:	IN	STD_LOGIC; 
          CPLC	:	IN	STD_LOGIC; 
          SEL	:	IN	STD_LOGIC; 
          OPER1	:	IN	STD_LOGIC_VECTOR (7 DOWNTO 0); 
          OPER2	:	IN	STD_LOGIC_VECTOR (7 DOWNTO 0));
   END COMPONENT;

   SIGNAL CLK	:	STD_LOGIC;
   SIGNAL LOADTEMP	:	STD_LOGIC;
   SIGNAL CLEARTEMP	:	STD_LOGIC;
   SIGNAL ZERO	:	STD_LOGIC;
   SIGNAL RES	:	STD_LOGIC_VECTOR (7 DOWNTO 0);
   SIGNAL ANL	:	STD_LOGIC;
   SIGNAL ADD	:	STD_LOGIC;
   SIGNAL DEC	:	STD_LOGIC;
   SIGNAL PASSTHROUGH	:	STD_LOGIC;
   SIGNAL LOADC	:	STD_LOGIC;
   SIGNAL CLEARC	:	STD_LOGIC;
   SIGNAL CPLC	:	STD_LOGIC;
   SIGNAL SEL	:	STD_LOGIC;
   SIGNAL OPER1	:	STD_LOGIC_VECTOR (7 DOWNTO 0);
   SIGNAL OPER2	:	STD_LOGIC_VECTOR (7 DOWNTO 0);

BEGIN

   UUT: Dados PORT MAP(
		CLK => CLK, 
		LOADTEMP => LOADTEMP, 
		CLEARTEMP => CLEARTEMP, 
		ZERO => ZERO, 
		RES => RES, 
		ANL => ANL, 
		ADD => ADD, 
		DEC => DEC, 
		PASSTHROUGH => PASSTHROUGH, 
		LOADC => LOADC, 
		CLEARC => CLEARC, 
		CPLC => CPLC, 
		SEL => SEL, 
		OPER1 => OPER1, 
		OPER2 => OPER2
   );

-- *** Test Bench - User Defined Section ***
   tb : PROCESS
   BEGIN
		SEL <= '0';
		CLEARTEMP <= '0'; LOADTEMP <= '0';
		LOADC <= '0'; CLEARC <= '0'; CPLC <= '0';
		PASSTHROUGH <= '0'; DEC <= '0'; ADD <= '0'; ANL <= '0';
		OPER1(7) <= '0'; OPER1(6) <= '0'; OPER1(5) <= '0'; OPER1(4) <= '0'; OPER1(3)<= '0'; OPER1(2) <= '1'; OPER1(1) <= '1'; OPER1(0) <= '1';
		OPER2(7) <= '0'; OPER2(6) <= '0'; OPER2(5) <= '0'; OPER2(4) <= '0'; OPER2(3)<= '0'; OPER2(2) <= '0'; OPER2(1) <= '0'; OPER2(0) <= '0';
		CLK <= '0'; wait for 50 ns;
		CLEARTEMP <= '1'; CLEARC <= '1';
		CLK <= '1'; wait for 50ns;
		CLEARTEMP <= '0'; PASSTHROUGH <= '1'; LOADTEMP <= '1';
		CLK <= '0'; wait for 50ns; CLK <= '1'; wait for 50ns;
		PASSTHROUGH <= '0'; DEC <= '1'; LOADTEMP <= '1';
		CLK <= '0'; wait for 50ns; CLK <= '1'; wait for 50ns;
		DEC <= '0'; ADD <= '1'; LOADTEMP <= '1'; LOADC <= '1';
		CLK <= '0'; wait for 50ns; CLK <= '1'; wait for 50ns;
		ADD <= '0'; ANL <= '1'; LOADTEMP <= '1'; LOADC <= '0';
		CLK <= '0'; wait for 50ns; CLK <= '1';
      WAIT; -- will wait forever
   END PROCESS;
-- *** End Test Bench - User Defined Section ***

END;
