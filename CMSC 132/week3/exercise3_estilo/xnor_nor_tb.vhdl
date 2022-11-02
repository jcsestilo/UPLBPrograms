-- Jan Coleen S. Estilo
-- CMSC 132 - ST7L

entity xnor_nor_tb is
end xnor_nor_tb;

architecture behav of xnor_nor_tb is
    component xnor_nor
        port (i_0, i_1 : in bit; xnor_nor_result : out bit);
    end component;

    for xnor_nor_0: xnor_nor use entity work.xnor_nor;
    signal i0, i1, xnor_nor_result : bit;

begin
    xnor_nor_0: xnor_nor port map (i_0 => i0, i_1 => i1,
    xnor_nor_result => xnor_nor_result);

    process
        type pattern_type is record
            i0, i1 : bit;
            xnor_nor_result : bit;
        end record;

        type pattern_array is array (natural range <>) of pattern_type;
        constant patterns : pattern_array :=
            (('0', '0', '0'),
            ('0', '1', '0'),
            ('1', '0', '1'),
            ('1', '1', '0'));
    begin
        for i in patterns'range loop
            i0 <= patterns(i).i0;
            i1 <= patterns(i).i1;

            wait for 1 ns;

            assert xnor_nor_result = patterns(i).xnor_nor_result
                report "bad xnor and nor value" severity error;
        end loop;
        assert false report "end of test" severity note;

        wait;
    end process;
end behav;