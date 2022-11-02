entity bitwise_or is
   port (x0, x1, x2, y0, y1, y2: in bit; r0, r1, r2 : out bit);
end entity;

architecture rtl of bitwise_or is
component orgate is
   port (i0, i1: in bit; orresult : out bit);
end component;
begin
	orgate_0: orgate port map(i0 => x0, i1 => y0, orresult => r0);
	orgate_1: orgate port map(i0 => x1, i1 => y1, orresult => r1);
	orgate_2: orgate port map(i0 => x2, i1 => y2, orresult => r2);
end architecture;