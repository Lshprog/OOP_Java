import common.TriDiagonalM;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<List<Double>> coefs1 = new ArrayList<>();
        List<Double> row1 = new ArrayList<>(List.of(0.0, 8.0, 2.0, 1.5)); // replace with your coefficients
        List<Double> row2 = new ArrayList<>(List.of(4.0, 18.0, 5.0, 1.75)); // replace with your coefficients
        List<Double> row3 = new ArrayList<>(List.of(8.0, 2.0, 1.5, 0.0)); // replace with your coefficients
        List<Double> row4 = new ArrayList<>(List.of(8.0, 18.0, 0.5, -1.75)); // replace with your coefficients
//        ArrayList<Double> row1 = new ArrayList<>(List.of(1.0, 2.0, 3.0, 4.0)); // Create an ArrayList from an existing list
//        ArrayList<Double> row2 = new ArrayList<>(List.of(5.0, 6.0, 7.0, 8.0)); // Create an ArrayList from an existing list
//        ArrayList<Double> row3 = new ArrayList<>(List.of(9.0, 10.0, 11.0, 12.0)); // Create an ArrayList from an existing list
//        ArrayList<Double> row4 = new ArrayList<>(List.of(13.0, 14.0, 15.0, 16.0)); // Create an ArrayList from an existing list

        coefs1.add(row1);
        coefs1.add(row2);
        coefs1.add(row3);
        coefs1.add(row4);
        TriDiagonalM matrix1 = new TriDiagonalM(coefs1, 4);

        List<Double> expected1 = List.of(0.0, 1.0, 0.0, -1.0); // replace with your expected results
        //List<Double> expected1 = List.of(1.0, 2.0, 3.0, 4.0);
        List<Double> result1 = matrix1.findSolution();
        System.out.println(expected1);
        System.out.println(result1);

//        // Test case 2: Another scenario
//        List<List<Double>> coefs2 = new ArrayList<>();
//        // Define coefs2 similar to coefs1
//        TriDiagonalM matrix2 = new TriDiagonalM(coefs2, 4);
//
//        List<Double> expected2 = List.of(...); // Define expected results
//        List<Double> result2 = matrix2.findSolution();
//        assertEquals(expected2, result2);

    }
}