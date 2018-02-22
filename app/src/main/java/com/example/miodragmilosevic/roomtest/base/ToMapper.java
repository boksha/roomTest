package com.example.miodragmilosevic.roomtest.base;

/**
 * Created by miodrag.milosevic on 2/14/2018.
 */

import java.util.ArrayList;
import java.util.List;
public abstract class ToMapper<To, From> {

    public abstract To map(From type);

    public List<To> mapList(List<From> typeList){

        List<To> list = new ArrayList<>();

        for( From type : typeList ){

            list.add( map( type ) );
        }
        return list;
    }
}