package org.example.common;

import org.example.common.enums.*;

import java.util.ArrayList;
import java.util.List;

public class TypeConverter {

    public static Object convertToList(List<String> inputList, String datatype) {

        switch (datatype){
            case "Double" -> {
                return Double.parseDouble(inputList.get(0));
            }

            case "RoastLevel" -> {

                List<RoastLevel> roastLevels = new ArrayList<>();

                for (String par : inputList){
                    roastLevels.add(RoastLevel.valueOf(par));
                }

                return roastLevels;


            }

            case "Dissolvability" -> {

                List<Dissolvability> roastLevels = new ArrayList<>();

                for (String par : inputList){
                    roastLevels.add(Dissolvability.valueOf(par));
                }

                return roastLevels;

            }

            case "Flavor" -> {
                List<Flavor> roastLevels = new ArrayList<>();

                for (String par : inputList){
                    roastLevels.add(Flavor.valueOf(par));
                }

                return roastLevels;
            }

            case "Intensity" -> {
                List<Intensity> roastLevels = new ArrayList<>();

                for (String par : inputList){
                    roastLevels.add(Intensity.valueOf(par));
                }

                return roastLevels;
            }

            case "GrindType" -> {
                List<GrindType> roastLevels = new ArrayList<>();

                for (String par : inputList){
                    roastLevels.add(GrindType.valueOf(par));
                }

                return roastLevels;
            }

        }

        return null;


    }
}
