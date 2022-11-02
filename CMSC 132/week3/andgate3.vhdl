entity andgate3 is
   port (i_0, i_1, i_2 : in bit; and_result : out bit);
end entity;

architecture rtl of andgate3 is
	signal temp: bit;
	component andgate2 is
		port (i0, i1 : in bit; andresult : out bit);
	end component;
begin
	and_gate_0: andgate2 port map(i0 => i_0, i1 => i_1, andresult => temp);
	and_gate_1: andgate2 port map(i0 => temp, i1 => i_2, andresult => and_result);
end architecture;