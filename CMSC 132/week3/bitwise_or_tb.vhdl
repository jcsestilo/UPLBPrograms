--  A testbench has no ports.
     entity bitwise_or_tb is
     end bitwise_or_tb;
     
     architecture behav of bitwise_or_tb is
        --  Declaration of the component that will be instantiated.
        component bitwise_or
          port (x0, x1, x2, y0, y1, y2: in bit; r0, r1, r2 : out bit);
        end component;
        --  Specifies which entity is bound with the component.
        for bitwise_or_0: bitwise_or use entity work.bitwise_or;
        signal x0, x1, x2, y0, y1, y2, r0, r1, r2 : bit;
     begin
        --  Component instantiation.
        bitwise_or_0: bitwise_or port map (x0 => x0, x1 => x1, x2 => x2, y0 => y0, y1 => y1, y2 => y2,
         r0 => r0, r1 => r1, r2 => r2);
     
        --  This process does the real job.
        process
           type patterntype is record
              --  The inputs of the and gate.
              x0, x1, x2, y0, y1, y2 : bit;
              --  The expected outputs of the and gate.
              r0, r1, r2 : bit;
           end record;
           --  The patterns to apply.
           type patternarray is array (natural range <>) of patterntype;

           --note: this truth table is incomplete and needs to be extended
           constant patterns : patternarray :=
             (('0','0','0','0','0','0','0','0','0'),
              ('0','0','0','0','0','1','0','0','1'),
              ('0','0','0','0','1','0','0','1','0'),
              ('0','0','0','0','1','1','0','1','1'));
        begin
           --  Check each pattern.
           for i in patterns'range loop
              --  Set the inputs.
              x0 <= patterns(i).x0;
              x1 <= patterns(i).x1;
              x2 <= patterns(i).x2;
              y0 <= patterns(i).y0;
              y1 <= patterns(i).y1;
              y2 <= patterns(i).y2;              
              --  Wait for the results.
              wait for 1 ns;
              --  Check the outputs.
              assert r0 = patterns(i).r0
                 report "bad r0 value" severity error;
              assert r1 = patterns(i).r1
                 report "bad r0 value" severity error;
              assert r2 = patterns(i).r2
                 report "bad r0 value" severity error;                                  
           end loop;
           assert false report "end of test" severity note;
           --  Wait forever; this will finish the simulation.
           wait;
        end process;
     end behav;
