--  A testbench has no ports.
entity andgate3_tb is
end andgate3_tb;

architecture behav of andgate3_tb is
   --  Declaration of the component that will be instantiated.
   component andgate3
      port (i_0, i_1, i_2 : in bit; and_result : out bit);
   end component;
   --  Specifies which entity is bound with the component.
   for andgate3_0: andgate3 use entity work.andgate3;
   signal i0, i1, i2, and_result : bit;
begin
   --  Component instantiation.
   andgate3_0: andgate3 port map (i_0 => i0, i_1 => i1, i_2 => i2,
                           and_result => and_result);

   --  This process does the real job.
   process
      type pattern_type is record
         --  The inputs of the and gate.
         i0, i1, i2 : bit;
         --  The expected outputs of the and gate.
         and_result : bit;
      end record;
      --  The patterns to apply.
      type pattern_array is array (natural range <>) of pattern_type;
      constant patterns : pattern_array :=
         (('0', '0', '0', '0'),
         ('0', '0', '1', '0'),
         ('0', '1', '0', '0'),
         ('0', '1', '1', '0'),
         ('1', '0', '0', '0'),
         ('1', '0', '1', '0'),
         ('1', '1', '0', '0'),
         ('1', '1', '1', '1'));
   begin
      --  Check each pattern.
      for i in patterns'range loop
         --  Set the inputs.
         i0 <= patterns(i).i0;
         i1 <= patterns(i).i1;
         i2 <= patterns(i).i2;
         --  Wait for the results.
         wait for 1 ns;
         --  Check the outputs.
         assert and_result = patterns(i).and_result
            report "bad and value" severity error;
      end loop;
      assert false report "end of test" severity note;
      --  Wait forever; this will finish the simulation.
      wait;
   end process;
end behav;
