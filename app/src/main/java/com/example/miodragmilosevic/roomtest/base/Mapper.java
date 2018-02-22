package com.example.miodragmilosevic.roomtest.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miodrag.milosevic on 2/7/2018.
 */

public abstract class Mapper<To, From> {

    public abstract To map(From type);

    public abstract From reverseMap(To type);

    public List<To> mapList(List<From> typeList){

        List<To> list = new ArrayList<>();

        for( From type : typeList ){

            list.add( map( type ) );

        }

        return list;

    }

    public List<From> reverseMapList(List<To> typeList){

        List<From> list = new ArrayList<>();

        for( To type : typeList ){

            list.add( reverseMap( type ) );

        }

        return list;

    }

}
