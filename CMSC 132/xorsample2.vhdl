entity xorsample2 is
       port (i0, i1 : in bit; retval : out bit);
     end xorsample2;
     
     architecture rtl of xorsample2 is
     begin
        retval <= (i0 xor i1);
     end rtl;
