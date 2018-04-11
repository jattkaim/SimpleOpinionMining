package com.jattkaim;

import java.util.List;

/***************************************

 * This is lass that contains the attribute of an extracted word.

 ***************************************/

public class Extracted {

    List<Dict> adjFound;
    String conjunction;
    boolean not;

    Extracted(List<Dict> aFound, String con, boolean not){
        this.adjFound = aFound;
        this.conjunction = con;
        this.not = not;

    }

    public List<Dict> getAdjFound() {
        return adjFound;
    }

    public String getConjunction() {
        return conjunction;
    }

    public boolean isNot() {
        return not;
    }

    public void setAdjFound(List<Dict> adjFound) {
        this.adjFound = adjFound;
    }

    public void setConjunction(String conjunction) {
        this.conjunction = conjunction;
    }

    public void setNot(boolean not) {
        this.not = not;
    }
}
