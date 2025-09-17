/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tvseriesapplication;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author RC_Student_lab
 */
public class TVSeriesApplication {

    private Scanner scanner = new Scanner(System.in);
    private ArrayList<SeriesModel> seriesList = new ArrayList<>();
    
    public static void main(String[] args) {
        TVSeriesApplication app = new TVSeriesApplication();
        app.startApplication();
    }
    
    public void startApplication() {
        while (true) {
            System.out.println("\nLATEST SERIES - 2025");
            System.out.println("******************************");
            System.out.println("Enter (1) to launch menu or any other key to exit");
            String input = scanner.nextLine();
            
            if (input.equals("1")) {
                showMenu();
            } else {
                System.out.println("Exiting program. Goodbye!");
                break;
            }
        }
    }
    
    private void showMenu() {
        while (true) {
            System.out.println("\nPlease select one of the following menu items:");
            System.out.println("(1) Capture a new series.");
            System.out.println("(2) Search for a series.");
            System.out.println("(3) Update series age restriction");
            System.out.println("(4) Delete a series.");
            System.out.println("(5) Print series report - 2025");
            System.out.println("(6) Exit Application.");
            System.out.print("Enter your choice: ");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    CaptureSeries();
                    break;
                case "2":
                    SearchSeries();
                    break;
                case "3":
                    UpdateSeries();
                    break;
                case "4":
                    DeleteSeries();
                    break;
                case "5":
                    SeriesReport();
                    break;
                case "6":
                    ExitSeriesApplication();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    
    public void CaptureSeries() {
        System.out.println("\nCAPTURE A NEW SERIES");
        System.out.println("***********************************************");
        
        System.out.print("Enter the series id: ");
        String seriesId = scanner.nextLine();
        
        System.out.print("Enter the series name: ");
        String seriesName = scanner.nextLine();
        
        String seriesAge;
        while (true) {
            System.out.print("Enter the series age restriction: ");
            seriesAge = scanner.nextLine();
            
            if (!seriesAge.matches("\\d+")) {
                System.out.println("You have entered an incorrect series age!!! Please re-enter the series age >>");
                continue;
            }
            
            int age = Integer.parseInt(seriesAge);
            if (age < 2 || age > 18) {
                System.out.println("You have entered an incorrect series age!!! Please re-enter the series age >>");
            } else {
                break;
            }
        }
        
        String seriesEpisodes;
        while (true) {
            System.out.print("Enter the number of episodes for " + seriesName + ": ");
            seriesEpisodes = scanner.nextLine();
            
            if (seriesEpisodes.matches("\\d+")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid number of episodes.");
            }
        }
        
        SeriesModel series = new SeriesModel(seriesId, seriesName, seriesAge, seriesEpisodes);
        seriesList.add(series);
        System.out.println("Series processed successfully!!!");
    }
    
    public void SearchSeries() {
        System.out.print("\nEnter the series id to search: ");
        String searchId = scanner.nextLine();
        
        boolean found = false;
        for (SeriesModel series : seriesList) {
            if (series.SeriesId.equals(searchId)) {
                System.out.println("---    ---");
                System.out.println("SERIES ID: " + series.SeriesId);
                System.out.println("SERIES NAME: " + series.SeriesName);
                System.out.println("SERIES AGE RESTRICTION: " + series.SeriesAge);
                System.out.println("SERIES NUMBER OF EPISODES: " + series.SeriesNumberOfEpisodes);
                System.out.println("---    ---");
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("---    ---");
            System.out.println("Series with Series Id: " + searchId + " was not found!");
            System.out.println("---    ---");
        }
    }
    
    public void UpdateSeries() {
        System.out.print("\nEnter the series id to update: ");
        String updateId = scanner.nextLine();
        
        boolean found = false;
        for (SeriesModel series : seriesList) {
            if (series.SeriesId.equals(updateId)) {
                System.out.print("Enter the series name: ");
                series.SeriesName = scanner.nextLine();
                
                String newAge;
                while (true) {
                    System.out.print("Enter the age restriction: ");
                    newAge = scanner.nextLine();
                    
                    if (!newAge.matches("\\d+")) {
                        System.out.println("You have entered an incorrect series age!!! Please re-enter the series age >>");
                        continue;
                    }
                    
                    int age = Integer.parseInt(newAge);
                    if (age < 2 || age > 18) {
                        System.out.println("You have entered an incorrect series age!!! Please re-enter the series age >>");
                    } else {
                        series.SeriesAge = newAge;
                        break;
                    }
                }
                
                String newEpisodes;
                while (true) {
                    System.out.print("Enter the number of episodes: ");
                    newEpisodes = scanner.nextLine();
                    
                    if (newEpisodes.matches("\\d+")) {
                        series.SeriesNumberOfEpisodes = newEpisodes;
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter a valid number of episodes.");
                    }
                }
                
                System.out.println("Series updated successfully!");
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("Series with Series Id: " + updateId + " was not found!");
        }
    }
    
    public void DeleteSeries() {
        System.out.print("\nEnter the series id to delete: ");
        String deleteId = scanner.nextLine();
        
        boolean found = false;
        for (int i = 0; i < seriesList.size(); i++) {
            SeriesModel series = seriesList.get(i);
            if (series.SeriesId.equals(deleteId)) {
                System.out.print("Are you sure you want to delete series " + deleteId + " from the system? Yes (y) to delete: ");
                String confirmation = scanner.nextLine();
                
                if (confirmation.equalsIgnoreCase("y")) {
                    seriesList.remove(i);
                    System.out.println("---Series with Series Id: " + deleteId + " WAS deleted!---");
                } else {
                    System.out.println("Deletion cancelled.");
                }
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("Series with Series Id: " + deleteId + " was not found!");
        }
    }
    
    public void SeriesReport() {
        System.out.println("\nSeries Report - 2025");
        System.out.println("====================");
        
        if (seriesList.isEmpty()) {
            System.out.println("No series available.");
            return;
        }
        
        for (int i = 0; i < seriesList.size(); i++) {
            SeriesModel series = seriesList.get(i);
            System.out.println("Series " + (i + 1));
            System.out.println("---");
            System.out.println("SERIES ID: " + series.SeriesId);
            System.out.println("SERIES NAME: " + series.SeriesName);
            System.out.println("SERIES AGE RESTRICTION: " + series.SeriesAge);
            System.out.println("NUMBER OF EPISODES: " + series.SeriesNumberOfEpisodes);
            System.out.println("---");
        }
    }
    
    public void ExitSeriesApplication() {
        System.out.println("Thank you for using the TV Series Management Application. Goodbye!");
        System.exit(0);
    }
}

class SeriesModel {
    public String SeriesId;
    public String SeriesName;
    public String SeriesAge;
    public String SeriesNumberOfEpisodes;
    
    public SeriesModel(String id, String name, String age, String episodes) {
        this.SeriesId = id;
        this.SeriesName = name;
        this.SeriesAge = age;
        this.SeriesNumberOfEpisodes = episodes;
    }
}

