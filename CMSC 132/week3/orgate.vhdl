entity orgate is
   port (i0, i1: in bit; orresult : out bit);
end entity;

architecture rtl of orgate is
begin
	orresult <= (i0 or i1);
end architecture;