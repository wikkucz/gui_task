import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    private static List<Ingredient> list1 = new ArrayList<>();
    private static List<Ingredient> list2 = new ArrayList<>();
    private static List<Ingredient> list3 = new ArrayList<>();
    private static List<Ingredient> list4 = new ArrayList<>();
    private static List<Ingredient> list5 = new ArrayList<>();
    private static List<Recipe> recipeList = new ArrayList<>();

        @Override
        public void start(Stage stage) throws Exception {

            Menu recipeMenu = new Menu("Przepis");
            Menu ingredientMenu = new Menu("Składniki");
            MenuItem addRecipe = new MenuItem("dodaj przepis");
            MenuItem addIngredient = new MenuItem("dodaj składnik do bazy");
            recipeMenu.getItems().add(addRecipe);
            ingredientMenu.getItems().add(addIngredient);
            MenuBar menuBar = new MenuBar();
            ScrollPane scrollPane = new ScrollPane();
             ListView<Recipe> recipes = new ListView();
             Label recipeName = new Label();
            Label rate = new Label();
            Label ingredients = new Label();


            Ingredient flour = new Ingredient("mąka", Taste.BITTER);
            Ingredient banana = new Ingredient("banan",Taste.SWEET);
            Ingredient apple = new Ingredient("jabłko", Taste.SWEET);
            Ingredient cocoa = new Ingredient("kakao",Taste.BITTER);
            Ingredient egg = new Ingredient("jajko", Taste.NEUTRAL);
            Ingredient lemon = new Ingredient("cytryna",Taste.SOUR);
            Ingredient salt = new Ingredient("sól", Taste.SALTY);
            Ingredient milk = new Ingredient("mleko",Taste.NEUTRAL);
            Ingredient cinnamon = new Ingredient("cynamon",Taste.BITTER);
            Ingredient water = new Ingredient("woda", Taste.NEUTRAL);
            Ingredient mint = new Ingredient("mięta",Taste.BITTER);
            Ingredient honey = new Ingredient("miód",Taste.SWEET);
            Ingredient sugar = new Ingredient("cukier",Taste.SWEET);
            Ingredient raspberry = new Ingredient("maliny",Taste.SOUR);


            list1.add(flour); list1.add(milk); list1.add(egg); list1.add(banana);
            list2.add(flour); list2.add(milk); list2.add(egg); list2.add(apple); list2.add(cinnamon);
            list3.add(water); list3.add(lemon); list3.add(honey); list3.add(mint); list3.add(raspberry);

            Recipe pancake = new Recipe("naleśniki", list1);
            Recipe applePie = new Recipe("szarlotka", list2);
            Recipe lemonade = new Recipe("lemoniada", list3);
            recipeList.add(pancake); recipeList.add(applePie); recipeList.add(lemonade);

            recipes.getItems().addAll(recipeList);

            recipes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observableValue, Object o, Object t1) {


                    Recipe currentRecipe = recipes.getSelectionModel().getSelectedItem();
                    recipeName.setText("Przepis na: " + currentRecipe.toString());

                    rate.setText("Ocena dania: " + currentRecipe.autoRate());
                    ingredients.setText("SKŁADNIKI: " + currentRecipe.getIngredient().toString());
                }
            });



            addIngredient.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("Dodaj nowy składnik do bazy");
                    dialog.setHeaderText("Aktualna baza: " + Ingredient.ingredientList.toString());
                    dialog.setContentText("Mój nowy składnik to: ");

                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent()){

                        Taste taste;
                        List<String> choices = new ArrayList<>();
                        choices.add(Taste.BITTER.getDescription());
                        choices.add( Taste.NEUTRAL.getDescription());
                        choices.add(Taste.SWEET.getDescription());
                        choices.add(Taste.SOUR.getDescription());
                        choices.add(Taste.SALTY.getDescription());

                        ChoiceDialog<String> choiceDialog = new ChoiceDialog<>(Taste.NEUTRAL.getDescription(), choices);
                        choiceDialog.setTitle("Smaki");
                        choiceDialog.setHeaderText(null);
                        choiceDialog.setContentText("Wybierz smak dla składnika");


                        Optional<String> choiceResult = choiceDialog.showAndWait();
                        if (choiceResult.isPresent()){
                            Ingredient ing = new Ingredient(result.get(), Taste.NEUTRAL);
                        }
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle(null);
                        alert.setHeaderText(null);
                        alert.setContentText("Dodano składnik: " + result.get());
                        alert.showAndWait();
                    }
                }
            });


            menuBar.getMenus().addAll(recipeMenu, ingredientMenu);
            SplitPane splitPane = new SplitPane();
            VBox leftControl  = new VBox(recipes);
            VBox rightControl = new VBox(recipeName,ingredients, rate);
            scrollPane.setContent(leftControl);

            splitPane.getItems().addAll(leftControl, rightControl);
            VBox window  = new VBox(menuBar, splitPane);


            Scene scene = new Scene(window, 800, 400);
            stage.setScene(scene);
            stage.setTitle("Przepisy");

            addRecipe.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    //NewRecipe newRecipe = new NewRecipe();

                    Label name = new Label("Wprowadż nazwę nowego przepisu");
                    TextField recipeTextField = new TextField();
                    List<Ingredient> ingList = new ArrayList<>();
                    Label newIng = new Label("Wybierz składniki: ");
                    VBox vBox = new VBox();
                    FlowPane flowPane = new FlowPane();

                    vBox.getChildren().addAll(name, recipeTextField, newIng);



                    for (Ingredient ing : Ingredient.ingredientList) {

                        CheckBox checkBox = new CheckBox(ing.toString());
                        //vBox.getChildren().add(checkBox);
                        flowPane.getChildren().add(checkBox);
                        //checkBox.setIndeterminate(true);

                        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {

                            public void handle(ActionEvent e) {
                                if (checkBox.isSelected())
                                    ingList.add(ing);
                            }
                        };
                        checkBox.setOnAction(event);
                    }

                    HBox hBox = new HBox();
                    Button buttonOK = new Button("Dodaj przepis");
                    Button buttonNO = new Button("Nie dodawaj przepisu");
                    hBox.getChildren().addAll(buttonOK, buttonNO);
                    vBox.getChildren().addAll(flowPane, hBox);


                    Scene sceneR = new Scene(vBox, 400, 200);
                    stage.setScene(sceneR);

                    buttonOK.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            recipeList.add(new Recipe(recipeTextField.getText(), ingList));
                            recipes.getItems().add(new Recipe(recipeTextField.getText(), ingList));
                            stage.setScene(scene);
                        }
                    });

                    buttonNO.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            stage.setScene(scene);
                        }
                    });
                }
            });

            stage.show();
    }
}
