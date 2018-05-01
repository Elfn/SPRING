package org.di.dispring.services;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
/**
 *@Primary is used the primary classe when we have severals classes implementing one interface
 *
 * */
@Service
@Primary
@Profile({"en","default"})
public class PrimaryProcessImpl implements  TextProcess{

    @Override
    public String removeAInFirst2Positions(String input) {
        String first2 = input.substring(0,2);
        String minusFirst2 = input.substring(2);
        String res = input.length() <=2 ? input.replaceAll("A"," ") : first2.replaceAll("A","")+minusFirst2;
        return res+" - Primary";
    }

    @Override
    public boolean isPalindrome(String input) {
        return false;
    }
}
