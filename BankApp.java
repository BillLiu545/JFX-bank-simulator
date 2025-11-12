

import javafx.application.Application;
import javafx.scene.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.*;
import javafx.collections.*;
import javafx.scene.control.cell.*;
import java.util.*;

/**
 * Write a description of JavaFX class BankApp here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BankApp<T> extends Application
{
    // Create bank object
    private Bank bank = new Bank();
    private final ObservableList<BankAccount> list = bank.toObservableList();
    /**
     * The start method is the main entry point for every JavaFX application. 
     * It is called after the init() method has returned and after 
     * the system is ready for the application to begin running.
     *
     * @param  stage the primary stage for this application.
     */
    @Override
    public void start(Stage stage)
    {
        // Create a new border pane
        BorderPane root = new BorderPane();
        root.setStyle("-fx-font-size: 15;");

        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(root, 450,500);
        stage.setTitle("Banking App");
        stage.setScene(scene);
        
        // Set up the main layout
        VBox mainLayout = new VBox();
        mainLayout.setAlignment(Pos.CENTER);
        root.setCenter(mainLayout);
        
        // Create the table
        TableView<BankAccount> table = new TableView();
        mainLayout.getChildren().add(table);
        table.setItems(list);
        
        // ID Column
        TableColumn idCol = new TableColumn("ID");
        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        idCol.setMinWidth(80);
        table.getColumns().add(idCol);
        
        // Name Column
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        nameCol.setMinWidth(80);
        table.getColumns().add(nameCol);
        
        // Balance Column
        TableColumn balCol = new TableColumn("Balance");
        balCol.setCellValueFactory(new PropertyValueFactory("balance"));
        balCol.setMinWidth(80);
        table.getColumns().add(balCol);
        
        // remove extra columns/rows
        table.setMaxWidth(244);
        table.setMaxHeight(490);
        
        // Set the menu for functionality
        MenuBar topMenu = new MenuBar();
        root.setTop(topMenu);
        
        // File menu
        Menu fileMenu = new Menu("File...");
        topMenu.getMenus().add(fileMenu);
        
        // Menu item - create a new bank account
        MenuItem createItem = new MenuItem("Create New Account...");
        createItem.setOnAction((event->
        {
            Package g = event.getClass().getPackage();
            System.out.println(g);
            BankAccount acc = bank.createAccount();
            list.add(acc);
            table.refresh();
        }));
        // Menu item - remove an existing account
        MenuItem removeItem = new MenuItem("Remove Account...");
        removeItem.setOnAction((event->
        {
            event.getClass().getPackage();
            int id = bank.enterAccount();
            BankAccount acc = bank.removeAccount(id);
            if (acc == null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No Account Found");
                alert.setHeaderText("No Account Found");
                alert.setContentText("There is no account with the ID: " + id);
                alert.showAndWait();
            }
            else
            {
                list.remove(acc);
                table.refresh();
            }
        }));
        // Menu item - deposit to an existing account
        MenuItem depositItem = new MenuItem("Deposit to Account...");
        depositItem.setOnAction((event->
        {
            event.getClass().getPackage();
            int id = bank.enterAccount();
            BankAccount acc = bank.deposit(id);
            if (acc == null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No Account Found");
                alert.setHeaderText("No Account Found");
                alert.setContentText("There is no account with the ID: " + id);
                alert.showAndWait();
            }
            else
            {
                table.refresh();
            }
        }));
        // Menu item - withdraw from an existing account
        MenuItem withdrawItem = new MenuItem("Withdraw from Account...");
        withdrawItem.setOnAction((event->
        {
            event.getClass().getPackage();
            int id = bank.enterAccount();
            BankAccount acc = bank.withdraw(id);
            if (acc == null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No Account Found");
                alert.setHeaderText("No Account Found");
                alert.setContentText("There is no account with the ID: " + id);
                alert.showAndWait();
            }
            else
            {
                table.refresh();
            }
        }));
        // Menu item - Quit
        MenuItem quitItem = new MenuItem("Quit");
        quitItem.setOnAction((e->
        {
            e.getClass();
            stage.close();
        }));
        fileMenu.getItems().addAll(createItem, removeItem, depositItem, withdrawItem, quitItem);
        
        // Show the Stage (window)
        stage.show();
    }

    /**
     * Main method used to launch the application
     */
    public static void main (String [] args)
    {
        launch(args);
    }
}
