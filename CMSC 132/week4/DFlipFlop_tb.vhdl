library ieee;
use ieee.std_logic_1164.all;

entity DFlipFlop_tb is
end entity;

architecture rtl of DFlipFlop_tb is
	signal Din : std_logic:='1';
	signal Q : std_logic;
	signal Qnot : std_logic;
	signal clk: std_logic := '1';
	component DFlipFlop is
		port(clk,Din : in std_logic; Q: out std_logic; Qnot : out std_logic);
	end component;

begin
	dff: DFlipFlop port map(clk=>clk, Din=>Din, Q=>Q, Qnot=>Qnot);	
	process 
	begin 
		for i in 1 to 10 loop
			clk <= not clk; 
			wait for 3 ns;
			if(i mod 3 =0) then
				Din <= not Din;
			end if;
			wait for 1.65 ns;
			if(i mod 2 = 0) then
				wait for 2.88 ns;
			end if;
		end loop;
		wait;
	end process;  	
end architecture;