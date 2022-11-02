entity andgate2 is
   port (i0, i1: in bit; andresult : out bit);
end entity;

architecture rtl of andgate2 is
begin
	andresult <= (i0 and i1);
end architecture;