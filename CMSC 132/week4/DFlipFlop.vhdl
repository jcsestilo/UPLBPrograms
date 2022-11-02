library ieee;
use ieee.std_logic_1164.all;
--example D Flip-flop
entity DFlipFlop is
    port (clk,Din : in std_logic;
             Q: out std_logic;
             Qnot : out std_logic);
 end DFlipFlop;
architecture behav of DFlipFlop is
    begin
        process (clk,Din)
            begin
            if(rising_edge(clk)) then
                Q <= Din;
                Qnot <= (not Din);
            end if;
        end process;
end behav;