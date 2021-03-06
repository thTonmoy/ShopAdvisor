package ui;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import data.*;
import javafx.stage.StageStyle;
import net.*;

/**
 * FXML Controller class
 *
 */
public class CustomerUIController implements Initializable {

    @FXML
    private BorderPane rootPane;
    @FXML
    private VBox leftPane;
    @FXML
    private Button hamburger_l;
    @FXML
    private Button hamburger_r;
    @FXML
    private Button signoutButton;
    @FXML
    private TableView<Product> table11;
    @FXML
    private TableColumn<Product, String> storeColumn11;
    @FXML
    private TableColumn<Product, Double> priceColumn11;
    @FXML
    private Button wishlistButton;
    @FXML
    private TableView<Product> table12;
    @FXML
    private TableColumn<Product, String> storeColumn12;
    @FXML
    private TableColumn<Product, Double> priceColumn12;
    @FXML
    private TableView<Product> table13;
    @FXML
    private TableColumn<Product, String> storeColumn13;
    @FXML
    private TableColumn<Product, Double> priceColumn13;
    @FXML
    private TableView<Product> table14;
    @FXML
    private TableColumn<Product, String> storeColumn14;
    @FXML
    private TableColumn<Product, Double> priceColumn14;
    @FXML
    private TableView<Product> table111;
    @FXML
    private TableColumn<Product, String> storeColumn111;
    @FXML
    private TableColumn<Product, Double> priceColumn111;
    @FXML
    private TableView<Product> table121;
    @FXML
    private TableColumn<Product, String> storeColumn121;
    @FXML
    private TableColumn<Product, Double> priceColumn121;
    @FXML
    private TableView<Product> table131;
    @FXML
    private TableColumn<Product, String> storeColumn131;
    @FXML
    private TableColumn<Product, Double> priceColumn131;
    @FXML
    private TableView<Product> table141;
    @FXML
    private TableColumn<Product, String> storeColumn141;
    @FXML
    private TableColumn<Product, Double> priceColumn141;
    @FXML
    private TableView<Product> wishlistTable;
    @FXML
    private TableColumn<Product, String> product_wishlist;
    @FXML
    private TableColumn<Product, String> store_wishlist;
    @FXML
    private TableColumn<Product, Double> price_wishlist;
    @FXML
    private Button orderButton;
    @FXML
    private Button hideWishlistButton;
    @FXML
    private AnchorPane rightPane;
    @FXML
    private Label totalPrice;
    ObservableList<Product> SelectedProducts = FXCollections.observableArrayList();
    User currentCustomer = CurrentState.getLoggedinUser();
    @FXML
    private Label customerLabel;
    @FXML
    private Button supportButton;
    @FXML
    private Button sellerDetailButton;
     @FXML
    private Button CfeedBackButton;
    @FXML
    private Button historyButton;
    
     @FXML
    void hideWishlist(ActionEvent event) throws IOException {
        rootPane.setRight(null);
        showPane(new ActionEvent());
    }
    @FXML
    private void showWishlistAction(ActionEvent event) throws IOException {
        rootPane.setRight(rightPane);
        hidePaneAction(new ActionEvent());
    }
    private void showWishlist(){
        rootPane.setRight(rightPane);
        hidePane();
    }
    @FXML
    void orderAction(ActionEvent event) {
        OrderClient oc = new OrderClient(SelectedProducts, currentCustomer, CurrentState.getServerIP() , 2222);
        oc.start();
        makewishlist();
    }
    @FXML
    private void hidePaneAction(ActionEvent event) throws IOException {
        rootPane.setLeft(null);
        hamburger_r.setVisible(true);
    }
    private void hidePane() {
        rootPane.setLeft(null);
        hamburger_r.setVisible(true);
    }
    
    private boolean isWishlistVisible(){
        if(rootPane.getRight() != null){
            return true;
        }
        return false;
    }

    @FXML
    private void showPane(ActionEvent event) throws IOException {
        hamburger_r.setVisible(false);
        rootPane.setLeft(leftPane);
        rootPane.setRight(null);
    } 
    @FXML
    void signoutButtonAction(ActionEvent event) throws IOException {
        if(CommonControll.ConfirmationDialogueOK("Continue?", "Signout", "You are about to be signed out")){
            CommonControll.changeScreen(FXMLLoader.load(getClass().getResource("login.fxml")), (Stage) ((Node) event.getSource()).getScene().getWindow());
        }
    }

    Comparator<? super Product> priceComparator = new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    if(o1.getPrice()> o2.getPrice()){
                        return 1;
                    }
                    return -1;
                }
            };
    
    private void initializeColumns(TableColumn<Product, String> storeColumn,TableColumn<Product, Double> priceColumn) {
        storeColumn.setCellValueFactory(new PropertyValueFactory<>("store"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        storeColumn.setSortable(false);
    }

    private void initializeData(TableView<Product> table, String productFile) {
        ObservableList<Product> tableContent;
        try {
            tableContent = ProductTableMaker.getProduct(productFile);
            tableContent.sort(priceComparator);
            table.setItems(tableContent);
        } catch (IOException ex) {
            System.err.println("IOException" + ex);
        }
    }
    private void initializePanes(){
        hamburger_r.setVisible(false);
        rootPane.setRight(null);
        
    }
    public void addSelecteditem(TableView<Product> table){
        Product p = table.getSelectionModel().getSelectedItem();
        if(p != null){
           SelectedProducts.add(p);
        }
    }
    public void makewishlist(){
        wishlistTable.setItems(SelectedProducts);
        initializeColumns(store_wishlist,price_wishlist);
        product_wishlist.setCellValueFactory(new PropertyValueFactory<>("Name"));
        getTotal();
    }
        @FXML
    void addToWishListAction11(ActionEvent event) {
        addSelecteditem(table11);
        makewishlist();
        if(!isWishlistVisible())
            showWishlist();
    }
        @FXML
    void addToWishListAction12(ActionEvent event) {
        addSelecteditem(table12);
        makewishlist();
        if(!isWishlistVisible())
            showWishlist();
    }
        @FXML
    void addToWishListAction13(ActionEvent event) {
        addSelecteditem(table13);
        makewishlist();
        if(!isWishlistVisible())
            showWishlist();
    }
        @FXML
    void addToWishListAction14(ActionEvent event) {
        addSelecteditem(table14);
        makewishlist();
        if(!isWishlistVisible())
            showWishlist();
    }@FXML
    void addToWishListAction111(ActionEvent event) {
        addSelecteditem(table111);
        makewishlist();
        if(!isWishlistVisible())
            showWishlist();
    }
        @FXML
    void addToWishListAction121(ActionEvent event) {
        addSelecteditem(table121);
        makewishlist();
        if(!isWishlistVisible())
            showWishlist();
    }
        @FXML
    void addToWishListAction131(ActionEvent event) {
        addSelecteditem(table131);
        makewishlist();
        if(!isWishlistVisible())
            showWishlist();
    }
        @FXML
    void addToWishListAction141(ActionEvent event) {
        addSelecteditem(table141);
        makewishlist();
        if(!isWishlistVisible())
            showWishlist();
    }
    void initializeTables(){
        //Category 1
        initializeData(table11,"burger");
        initializeColumns(storeColumn11,priceColumn11);    
        initializeData(table12,"sandwitch");
        initializeColumns(storeColumn12,priceColumn12);   
        initializeData(table13,"taco");
        initializeColumns(storeColumn13,priceColumn13);
        initializeData(table14,"fried_chicken");
        initializeColumns(storeColumn14,priceColumn14);
        //Category 2
        initializeData(table111,"Pendrives");
        initializeColumns(storeColumn111,priceColumn111);
        initializeData(table121,"HDD");
        initializeColumns(storeColumn121,priceColumn121);
        initializeData(table131,"Joystick");
        initializeColumns(storeColumn131,priceColumn131);
        initializeData(table141,"UPS");
        initializeColumns(storeColumn141,priceColumn141);  
    }
    void getTotal(){
        double total = 0.0;
        for(Product p : SelectedProducts){
            total+= p.getPrice();
        }
        //System.out.println("Total " + total);
        totalPrice.setText("\tTotal:\t" + total + "$");
    }
    
    @FXML
    private void supportButtonAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("webview.fxml"));
        Stage webstage = new Stage();
        webstage.initStyle(StageStyle.UTILITY);
        webstage.initOwner((Stage)((Node) event.getSource()).getScene().getWindow());
        WebviewController.url = "https://sites.google.com/view/shopadvisor/home";
        CommonControll.changeScreen(fxmlLoader.load(), webstage);
    }
    
     @FXML
    private void sellerDetailButtonAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("webview.fxml"));
        Stage webstage = new Stage();
        webstage.initStyle(StageStyle.UTILITY);
        webstage.initOwner((Stage)((Node) event.getSource()).getScene().getWindow());
        WebviewController.url = "https://sites.google.com/view/shopadvisor/seller-information?authuser=0";
        CommonControll.changeScreen(fxmlLoader.load(), webstage);
    }
    
    @FXML
    void showFeedbackScreen(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FeedBack.fxml"));
        CommonControll.changeScreen(fxmlLoader.load(), new Stage());
                
    }
    
    @FXML
    private void showHistoryScreen(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("historyUI.fxml"));
        Stage historystage = new Stage();
        historystage.initStyle(StageStyle.UTILITY);
        historystage.initOwner((Stage)((Node) event.getSource()).getScene().getWindow());
        CommonControll.changeScreen(fxmlLoader.load(), historystage);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializePanes();
        initializeTables();
        customerLabel.setText(currentCustomer.getFullName() + "\n" + currentCustomer.getAddress() + "\n");
    }
}
