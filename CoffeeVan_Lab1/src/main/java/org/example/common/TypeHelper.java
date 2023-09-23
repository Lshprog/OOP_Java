package org.example.common;

import org.example.common.enums.*;

import java.util.ArrayList;
import java.util.List;

public class TypeHelper {

    public static Object convertToList(List<String> inputList, String datatype) {

        switch (datatype){
            case "Double" -> {
                return Double.parseDouble(inputList.get(0));
            }

            case "Long" -> {
                List<Long> ids = new ArrayList<>();

                for (String par : inputList){
                    ids.add(Long.parseLong(par));
                }

                return ids;
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

        return inputList;


    }

    public static boolean validateCombinationClasses(String enumstr, String classname) {

        if(enumstr == "RoastLevel"){
            return true;
        }
        else if(enumstr == "Flavor" || enumstr == "Dissolvability"){
            if(classname == "InstantCoffee"){
                return true;
            }
            else {
                return false;
            }
        }
        else if(enumstr == "GrindType" || enumstr == "Intensity"){
            if(classname == "GroundCoffee"){
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return true;
        }

    }


}
