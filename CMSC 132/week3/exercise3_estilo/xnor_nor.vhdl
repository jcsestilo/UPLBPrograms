-- Jan Coleen S. Estilo
-- CMSC 132 - ST7L

entity xnor_nor is
    port (i_0, i_1 : in bit; xnor_nor_result : out bit);
end entity;

architecture rtl of xnor_nor is
    signal temp: bit;
    component xnorgate_2in is
        port (i0, i1: in bit; xnorresult: out bit);
    end component;
    component norgate_2in is
        port (i0, i1: in bit; norresult: out bit);
    end component;

begin
    xnor_gate: xnorgate_2in port map(i0 => i_0, i1 => i_1, xnorresult => temp);
    nor_gate: norgate_2in port map(i0 => temp, i1 => i_1, norresult => xnor_nor_result);
end architecture;