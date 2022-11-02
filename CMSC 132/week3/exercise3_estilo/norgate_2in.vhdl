-- Jan Coleen S. Estilo
-- CMSC 132 - ST7L

entity norgate_2in is
    port (i0, i1: in bit; norresult: out bit);
end entity;

architecture rtl of norgate_2in is
begin
    norresult <= (i0 nor i1);
end architecture;