--@author reg recario


--this is how you import a library in vhdl
library ieee;
use ieee.std_logic_1164.all;


entity simple_clock is
end entity;

architecture rtl of simple_clock is
  constant num_cycles : integer := 640; -- the number of cycles for the clock
  signal clock: std_logic := '1';
begin
	process
		begin
			for i in 1 to num_cycles loop --loop from 1 to the declared num_cycles
				clock <= not clock; --pulse from 1 to 0 or 0 to 1
				wait for 1 ns; --as stated, wait for 1ns
			end loop;
		wait; 
	end process;
end architecture;