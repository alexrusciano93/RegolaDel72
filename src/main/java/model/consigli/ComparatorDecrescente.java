package model.consigli;

import model.calciatore.Calciatore;

import java.util.*;

public class ComparatorDecrescente implements Comparator<Calciatore>{

    @Override
    public int compare(Calciatore c1, Calciatore c2) {
        if (Double.compare(c1.getMedia(), c2.getMedia())==0)
            return 0;
        if (Double.compare(c1.getMedia(), c2.getMedia())==-1)
            return 1;
        else
            return -1;
    }

}