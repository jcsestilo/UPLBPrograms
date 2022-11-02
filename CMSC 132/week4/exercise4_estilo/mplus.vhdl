-- Jan Coleen S. Estilo
-- CMSC 132 - ST7L

library ieee;
use ieee.std_logic_1164.all;

entity mplus is
    port(clk, Min, Pin : in std_logic;
            Gout: out std_logic;
            Sout: out std_logic);
end mplus;

architecture behav of mplus is
    begin
        process(clk, Min, Pin)
            begin
            if(falling_edge(clk) and Pin = 'X') then
                if(Min = '0') then
                    Gout <= '0';
                    Sout <= '0';
                elsif(Min = '1') then
                    Gout <= '1';
                    Sout <= '1';
                end if;
            end if;
        end process;
end behav;