package app;

import java.util.Scanner;
import java.util.Random;

/*
Programming Techniques 1, Semester 1 2020
Student: Tessa Podbury
Student number: s3775931
*/

/** 
 * A1 class will run and evaluate either a calculator or lotto checker.
   A1 will then display the results as requested by the user.
   A1 will be called by the Driver Class.
    */

public class A1 {

    /*
    The menu method will prompt user to choose between basic calculator or lotto checker.
    It will request three numbers as user input.
    The menu will call on other methods using these three numbers to evaluate result of application.
    It will then print the output for either basic calculator or lotto checker.
    */

    public void menu(){
        
        System.out.println("Would you like to run \"Basic Calculator\" or \"Lotto Checker\"?"); 
        System.out.println("Choose 1 for Basic Calculator or 2 for Lotto Checker.");

        
        Scanner parameters = new Scanner(System.in);
        int chosenMethod = parameters.nextInt();

        if (chosenMethod == 1){
            
            System.out.println("Please choose two numbers >= 0 two complete a Maths question on. Press enter after each number.");
            int firstChosen = parameters.nextInt();
            int secondChosen = parameters.nextInt();
            System.out.println("Please choose 0 for addition, 1 for subtraction, 2 for multiplication and 3 for division. Press enter after the number.");
            int thirdChosen = parameters.nextInt();

            double calculateInput = calculate(firstChosen, secondChosen, thirdChosen);

            //Both a subtraction equalling -1 and invalid input can trigger this.
            //This error could be fixed by having non number assignments for errors.
            //This also goes for -2 and -3.
            if (calculateInput == -1){
                System.out.println("Operation supplied is invalid");
            }
            else if (calculateInput == -2){
                System.out.println("Divide by zero error");
            }
            else if(calculateInput == -3){
                System.out.println("Invalid number chosen");
            }
            else{
                System.out.println("Your answer is: " + calculateInput);
            }
        }
        else if (chosenMethod == 2){
            System.out.println("Please choose three numbers 1 - 24 pressing enter after each number");
            
            int firstChosen = parameters.nextInt();
            int secondChosen = parameters.nextInt();
            int thirdChosen = parameters.nextInt();

            System.out.println(checkLotto(firstChosen, secondChosen, thirdChosen));
        }
        else{
            System.out.println("The input was not correct!");
        }
        parameters.close();
    }

    /*
    Calculate method takes three numbers. First two numbers will be the numbers the operation is performed upon,
    third number is the operation used (addition(0), subtraction(1), multiplication(2), division(3)).
    The method returns the result of the operation.
    */

    private double calculate(final double number1, final double number2, final int operation) {

        double outcome;
        
        if (number1 >= 0 && number2 >= 0){
            if (operation == 0){
                outcome = number1 + number2;
            }
            else if (operation == 1){
                outcome = number1 - number2;
            }
            else if (operation == 2){
                outcome = number1 * number2;
            }
            else if (operation == 3){
                //Can't divide by 0, error is returned
                if (number2 == 0){
                    outcome = -2;
                }
                else{
                    outcome = number1 / number2;
                }
            }
            else{
                //Operation chosen must be between 0 - 3, error is returned
                outcome = -1;
            }
        }
        else{
            //Numbers 1 & 2 have to be positive
            outcome = -3;
        }
        return outcome;
    }

    /*
    The lottoGenerator method will generate random numbers for the Lotto.
    The normal Random function is inclusive of the number zero, lottoGenerator allows input to modify the range
    of random numbers.
    */

    private int lottoGenerator(int minimumNumber, int maximumNumber){

        Random random = new Random();

        int randomNumber = random.nextInt((maximumNumber - minimumNumber) + 1) + minimumNumber;

        return randomNumber;
    }

    /*
    The checkLotto method will compare the numbers chosen by the user (chosen1, chosen2, chosen3)
    with the lotto numbers that are generated when checkLotto calls lottoGenerator.
    The method will return the drawn numbers from lottoGenerator, the user's numbers, the correct guesses
    and the number of correct guesses in a string form.
    */


    private String checkLotto(final int chosen1, final int chosen2, final int chosen3) {

        String converted1;
        
        if (chosen1 < 1 || chosen1 > 24){
            converted1 = "Invalid Choice";
        }
        else{
            converted1 = Integer.toString(chosen1);
        }
        
        String converted2;
        
        if (chosen2 < 1 || chosen2 > 24){
            converted2 = "Invalid Choice";
        }
        else{
            converted2 = Integer.toString(chosen2);
        }
        
        String converted3;
        
        if (chosen3 < 1 || chosen3 > 24){
            converted3 = "Invalid Choice";
        }
        else{
            converted3 = Integer.toString(chosen3);
        }
        
        //A string concatenation of the drawn 8 lotto numbers
        String lottoNumbers = " " + lottoGenerator(1, 24) + " " + lottoGenerator(1, 24) +
                              " " + lottoGenerator(1, 24) + " " + lottoGenerator(1, 24) +
                              " " + lottoGenerator(1, 24) + " " + lottoGenerator(1, 24) +
                              " " + lottoGenerator(1, 24) + " " + lottoGenerator(1, 24) + " ";

        String correctGuesses = "";
        int numberCorrect = 0;

        if (lottoNumbers.contains(" " + converted1 + " ")){
            correctGuesses = correctGuesses + converted1 + " ";
            numberCorrect++;
        }

        if (lottoNumbers.contains(" " + converted2 + " ")){
            correctGuesses = correctGuesses + converted2 + " ";
            numberCorrect++;
        }

        if (lottoNumbers.contains(" " + converted3 + " ")){
            correctGuesses = correctGuesses + converted3;
            numberCorrect++;
        }

        if (correctGuesses.isEmpty()){
            correctGuesses = "none";
        }

        String finalAnswer = "Drawn numbers:" + lottoNumbers + 
                             "\n" + "User's numbers: " + converted1 + " " + converted2 + " " + converted3 +
                             "\n" + "Correct guesses: " + correctGuesses +
                             "\n" + "Number of correct guesses: " + numberCorrect;
        
        return finalAnswer;
    }
}