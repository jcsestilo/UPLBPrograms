-- Jan Coleen S. Estilo
-- CMSC 132 ST7L

library ieee;
use ieee.std_logic_1164.all;

entity mplus_tb is
end entity;

architecture rtl of mplus_tb is
    signal Min : std_logic:='0';
    signal Pin : std_logic:='X';
    signal Gout : std_logic;
    signal Sout : std_logic;
    signal clk : std_logic := '0';
    component mplus is
        port(clk, Min, Pin : in std_logic; Gout: out std_logic; Sout: out std_logic);
    end component;

begin
    mp: mplus port map(clk=>clk, Min=>Min, Pin=>Pin, Gout=>Gout, Sout=>Sout);
    process
    begin
        for i in 1 to 10 loop
            clk <= not clk;
            wait for 3 ns;
            if(i mod 3 = 0) then
                if(i>5) then
                    Min <= '0';
                    Pin <= 'X';
                elsif(i<=5) then
                    Min <= '1';
                    Pin <= 'X';
                end if;
            end if;
            wait for 1.65 ns;
            if(i mod 2 = 0) then
                if(i>5) then
                    Pin <= '1';
                    Min <= 'X';
                elsif(i<=5) then
                    Pin <= '0';
                    Min <= 'X';
                end if;
                wait for 2.88 ns;
            end if;
        end loop;
        wait;
    end process;
end architecture;