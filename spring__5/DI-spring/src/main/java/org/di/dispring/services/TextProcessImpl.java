package org.di.dispring.services;

import org.springframework.stereotype.Service;

@Service
public class TextProcessImpl implements TextProcess{

    public static final boolean IMPL_RESULT_TRUE = true;
    public  static final
    boolean IMPL_RESULT_FALSE = false;

    public String removeAInFirst2Positions(String input)
    {
        String first2 = input.substring(0,2);
        String minusFirst2 = input.substring(2);
        String res = input.length() <=2 ? input.replaceAll("A"," ") : first2.replaceAll("A","")+minusFirst2;
        return res+" - Second";

    }

    public boolean isPalindrome(String input)
    {
        int l = input.length();

        String p1 = "";
        String p2 = "";
        String reverse="";
        int modulo = l%2;

        if(modulo != 0){

            p1 = input.substring(0,(l/2)+1);
            p2 = input.substring(l/2);

            for ( int i = p2.length() - 1 ; i >= 0 ; i-- )
                reverse = reverse + p2.charAt(i);

            return p1.equals(reverse)? IMPL_RESULT_TRUE: IMPL_RESULT_FALSE;

        }
        else
        {
            p1 = input.substring(0,(l/2));
            p2 = input.substring(l/2);
            for ( int i = p2.length() - 1 ; i >= 0 ; i-- )
                reverse = reverse + p2.charAt(i);

            return p1.equals(reverse)? IMPL_RESULT_TRUE : IMPL_RESULT_FALSE;
        }

    }


}

