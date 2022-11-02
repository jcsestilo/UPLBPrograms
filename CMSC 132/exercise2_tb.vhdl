-- Jan Coleen S. Estilo
-- CMSC 132 - ST7L

entity exercise2_tb is
end exercise2_tb;

architecture behav of exercise2_tb is
    component exercise2
        port (temp, co, moisture, gl : in bit; fix, evac : out bit);
    end component;
    for exercise2_0: exercise2 use entity work.exercise2;
    signal inputtemp, inputco, inputmoisture, inputgl, outputfix, outputevac : bit;
begin
    exercise2_0: exercise2 port map (temp => inputtemp, co => inputco, moisture => inputmoisture, gl => inputgl, fix => outputfix, evac => outputevac);


    process
        type pattern_type is record
            inputtemp, inputco, inputmoisture, inputgl : bit;

            outputfix, outputevac : bit;
        end record;

        type pattern_array is array (natural range <>) of pattern_type;
        constant patterns : pattern_array :=
           (('0', '0', '0', '0', '0', '0'),
            ('0', '0', '0', '1', '0', '1'),
            ('0', '0', '1', '0', '0', '0'),
            ('0', '0', '1', '1', '0', '1'),
            ('0', '1', '0', '0', '0', '1'),
            ('0', '1', '0', '1', '0', '1'),
            ('0', '1', '1', '0', '0', '1'),
            ('0', '1', '1', '1', '0', '1'),
            ('1', '0', '0', '0', '0', '0'),
            ('1', '0', '0', '1', '0', '1'),
            ('1', '0', '1', '0', '1', '0'),
            ('1', '0', '1', '1', '1', '1'),
            ('1', '1', '0', '0', '0', '1'),
            ('1', '1', '0', '1', '0', '1'),
            ('1', '1', '1', '0', '1', '1'),
            ('1', '1', '1', '1', '1', '1'));

    begin
        for i in patterns'range loop
            inputtemp <= patterns(i).inputtemp;
            inputco <= patterns(i).inputco;
            inputmoisture <= patterns(i).inputmoisture;
            inputgl <= patterns(i).inputgl;

            wait for 1 ns;

            assert outputfix = patterns(i).outputfix
                report "bad and value" severity error;
            
            assert outputevac = patterns(i).outputevac
                report "bad and or value" severity error;
        end loop;

        assert false report "end of test" severity note;

        wait;
    end process;
end behav;