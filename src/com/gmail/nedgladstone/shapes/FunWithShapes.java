package com.gmail.nedgladstone.shapes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FunWithShapes {

    public static void main(String[] args) {
        if (args.length < 1) {
            throw new RuntimeException("Must provide the name of a file containing rectangles");
        }
        processRectangleFileWithCountLine(args[0]);
    }

    private static void processRectangleFileWithCountLine(String filename) {
        // Process rectangle definitions from a file whose first line specifies the number of rectangles that follow
        try (FileReader fr = new FileReader(filename);
             BufferedReader reader = new BufferedReader(fr)) {
            String line = reader.readLine(); // Get the count of lines
            if (line == null) {
                // Empty file; do nothing
                return;
            }
            int numLines = Integer.parseInt(line); // throws NumberFormatException
            System.out.println("Processing " + numLines + " rectangles from file " + filename);
            for (int i = 0; i < numLines; ++i) {
                line = reader.readLine();
                if (line == null) {
                    throw new RuntimeException("File contains " + i + " rectangles, but promised " + numLines);
                }
                try {
                    Rectangle rect = stringToRectangle(line);
                    processRectangle(rect);
                } catch (IllegalArgumentException e) {
                    System.err.println("Invalid rectangle description " + line + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading " + filename + ": " + e);
        }
    }

    private static Rectangle stringToRectangle(String rep) {
        String[] tokens = rep.split(" ");
        if (tokens.length != 2) {
            throw new RuntimeException("File contains malformed rectangle " + rep);
        }

        return new Rectangle(Double.parseDouble(tokens[0]), Double.parseDouble(tokens[1])); // throws NumberFormatException
    }

    private static void processRectangle(Rectangle rectangle) {
        double area = rectangle.getArea();
        System.out.println(rectangle.toString() + " has area " + area);
    }

    // Just out of curiosity - here's what all of the processing looks like in a single method and without any error handling
    private static void processRectangleFileWithNoErrorHandling(String filename) throws Exception {
        // Warning: This implementation lacks vital error handling
        // and combines functionality that should be factored out into separate methods
        FileReader fr = new FileReader(filename);
        BufferedReader reader = new BufferedReader(fr);
        String line = reader.readLine(); // Get the count of lines
        int numLines = Integer.parseInt(line);
        System.out.println("Processing " + numLines + " rectangles from file " + filename);
        for (int i = 0; i < numLines; ++i) {
            line = reader.readLine();
            String[] tokens = line.split(" ");
            Rectangle rectangle = new Rectangle(Double.parseDouble(tokens[0]), Double.parseDouble(tokens[1]));
            double area = rectangle.getArea();
            System.out.println(rectangle.toString() + " has area " + area);
        }
    }

    // Just out of curiosity - here's how much simpler this would be if the input files didn't have a count line
    private static void processRectangleFileWithoutCountLine(String filename) {
        // Process rectangle definitions from a file that has no count line
        try (FileReader fr = new FileReader(filename);
             BufferedReader reader = new BufferedReader(fr)) {
            System.out.println("Processing rectangles from file " + filename);
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Rectangle rect = stringToRectangle(line);
                    processRectangle(rect);
                } catch (IllegalArgumentException e) {
                    System.err.println("Invalid rectangle description " + line + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading " + filename + ": " + e);
        }
    }
}
