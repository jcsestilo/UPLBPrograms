-- Jan Coleen S. Estilo
-- CMSC 132 - ST7L

library ieee;
use ieee.std_logic_1164.all;

entity clock is
end entity;

architecture rtl of clock is
    constant num_cycles : integer := 640;
    signal clock: std_logic := '0';
begin
    process
        begin
            for i in 1 to num_cycles loop
                clock <= not clock;
                wait for 1 ns;
            end loop;
        wait;
    end process;
end architecture;