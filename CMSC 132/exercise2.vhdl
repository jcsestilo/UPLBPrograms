-- Jan Coleen S. Estilo
-- CMSC 132 - ST7L

entity exercise2 is
    port (temp, co, moisture, gl : in bit; fix, evac : out bit);
end exercise2;

architecture rtl of exercise2 is
    begin
        fix <= (temp and moisture);
        evac <= ((co or gl) or (gl and temp));
end rtl;