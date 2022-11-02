-- Jan Coleen S. Estilo
-- CMSC 132 - ST7L

entity xnorgate_2in is
    port (i0, i1: in bit; xnorresult : out bit);
end entity;

architecture rtl of xnorgate_2in is
begin
    xnorresult <= (i0 xnor i1);
end architecture;