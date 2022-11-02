--  A testbench has no ports.
     entity xorsample_tb2 is
     end xorsample_tb2;
     
     architecture behav of xorsample_tb2 is
        --  Declaration of the component that will be instantiated.
        component xorsample2
          port (i0, i1 : in bit; retval : out bit);
        end component;
        --  Specifies which entity is bound with the component.
        for xorsample2_0: xorsample2 use entity work.xorsample2;
        signal input0, input1, returnvalue : bit;
     begin
        --  Component instantiation.
        xorsample2_0: xorsample2 port map (i0 => input0, i1 => input1, retval => returnvalue);
     
        --  This process does the real job.
        process
           type pattern_type is record
              --  The inputs of the xorsample.
              input0, input1 : bit;
              --  The expected outputs of the xorsample.
              returnvalue : bit;
           end record;
           --  The patterns to apply.
           type pattern_array is array (natural range <>) of pattern_type;
           constant patterns : pattern_array :=
             (('0', '0', '0'),
              ('0', '1', '1'),
              ('1', '0', '1'),
              ('1', '1', '0'));
        begin
           --  Check each pattern.
           for i in patterns'range loop
              --  Set the inputs.
              input0 <= patterns(i).input0;
              input1 <= patterns(i).input1;
              --  Wait for the results.
              wait for 1 ns;
              --  Check the outputs.
              assert returnvalue = patterns(i).returnvalue
                 report "bad xor value" severity error;
           end loop;
           assert false report "end of test" severity note;
           --  Wait forever; this will finish the simulation.
           wait;
        end process;
     end behav;
