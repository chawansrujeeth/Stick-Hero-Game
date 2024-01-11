package com.example.recc;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.scene.paint.Color;
import java.awt.Toolkit;


public class HelloController implements Initializable {

    @FXML
    private AnchorPane plane;

    @FXML
    private Button butt;

    @FXML
    private Rectangle rectangle1;

    @FXML
    private Rectangle rectangle2;

    @FXML
    private ImageView square;

    private ImageView cherryprev;
    private ImageView cherrynext;
    @FXML
    private Text count_final;
    private int cherry_count= 0;
    @FXML
    private Text cherries_final;
    private int flag = 0;

    private boolean mousePressed = false;

    private Timeline timeline;
    private double tempp;

    private List<Rectangle> rectangles = new ArrayList<>();
    private  int countt=0;
    private int count2;
    private int collbigrectanlge =0;
    private int cherry = 0;

    private static int position = 1;
    @FXML
    private Line myline;
    @FXML
    private Line myline1;

    private double test1;

    public double getTest1() {
        return test1;
    }

    //
    public  void rotateLineBy90Degrees() {
        double startX = myline.getStartX();
        double startY = myline.getStartY();

        // Create a Timeline for the rotation
        Timeline timeline = new Timeline();

        // Add KeyFrames to the Timeline, each rotating by 1 degree after 0.5 seconds
        for (int i = 0; i <= 90; i++) {
            int finalI = i;
            KeyFrame keyFrame = new KeyFrame(
                    Duration.seconds(i * 0.01), // Time of the KeyFrame
                    event -> {
                        Rotate rotate = new Rotate(finalI, startX, startY);
                        myline.getTransforms().clear(); // Clear existing transforms
                        myline.getTransforms().add(rotate);
                    }
            );

            timeline.getKeyFrames().add(keyFrame);
        }

    }

    private void rotateLine() {
        double startX = myline.getStartX();
        double startY = myline.getStartY();
        double endX = myline.getEndX();
        double endY = myline.getEndY();

        double deltaX = startY - endY;
        double deltaY = endY;

        myline.setEndX(startX + deltaX);
        myline.setEndY(startY);

    }


        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rectangles.add(rectangle1);
        rectangles.add(rectangle2);
        count2 = GameState.getPresent_score();
        cherry_count = GameState.getPresent_cherries();
        count_final.setText(String.valueOf(count2));
        cherries_final.setText("Cherries"+cherry_count);
        count2++;
        plane.setOnMousePressed(this::handleMousePress);
        plane.setOnMouseReleased(this::handleMouseRelease);
        plane.setOnKeyPressed(this::handledown);
        checkContinuousCollision();
//            plane.setOnKeyPressed(event -> {
//                if (event.getCode() == KeyCode.SPACE) {
//                    // Handle the spacebar key press
//                    System.out.println("Spacebar pressed!");
//                    // Add your logic here
//                }
//            });
//        plane.setOnSwipeDown(this::handledown);
//        plane.setOnSwipeUp(this::handleup);
    }
    @FXML
    private  void handledown(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            if(position>0){
                handledown1();
            }else{
                handledown2();
            }
        }
    }
    private void handleMousePress(MouseEvent event) {
        createLineOnRectangle(rectangles.get(countt));
        mousePressed = true;
        startIncreasingLine();
    }
    private void startIncreasingLine() {
        timeline = new Timeline(
                new KeyFrame(Duration.millis(10), event -> {
                    if (mousePressed) {
                        // Increase the length of the line
                        myline.setEndY(myline.getEndY() - 1); // Adjust the value as needed
                    }
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        timeline.setOnFinished(actionEvent -> {
            rotateLineBy90Degrees();
//            movefront();
        });
    }
    private void handleMouseRelease(MouseEvent event) {
        mousePressed = false;
        stopIncreasingLine();
//        Line line = createLineOnRectangle();
//        rotateLineBy90Degrees();
        rotateLine();
        movefront();

    }
    private void movefront() {
        Rectangle new1 = rectangles.get(countt);
        Rectangle new2 = rectangles.get(countt + 1);
        double lineStartX = rectangles.get(countt).getBoundsInParent().getMinX() + rectangles.get(countt).getWidth() - 25;
        double lineendX = rectangles.get(countt + 1).getBoundsInParent().getMinX() + rectangles.get(countt + 1).getWidth() - 25;
        TranslateTransition moveOut1 = new TranslateTransition(Duration.seconds(1), square);
        moveOut1.setToX(0);
        moveOut1.play();

        TranslateTransition moveOut = new TranslateTransition(Duration.seconds(2), square);
        if (myline.getEndX() > rectangles.get(countt + 1).getBoundsInParent().getMinX() && myline.getEndX() < rectangles.get(countt + 1).getBoundsInParent().getMaxX()) {
            System.out.println("babbbb");
            if(countt ==0){
                moveOut.setToX(rectangles.get(countt + 1).getBoundsInParent().getMaxX() - rectangles.get(countt).getBoundsInParent().getMaxX() +20);
                int a = count2+countt;
                count_final.setText(" "+ a+" ");

            }else {
                moveOut.setToX(rectangles.get(countt + 1).getBoundsInParent().getMaxX() - rectangles.get(countt).getBoundsInParent().getMaxX() - 20);
                int a = count2+countt;
                count_final.setText(" "+ a+" ");
            }

        } else {
            moveOut.setToX(myline.getEndX() - myline.getStartX());
            tempp = lineendX - lineStartX;
        }

        moveOut.play();
        moveOut.setOnFinished(event22 -> {
            if (myline.getEndX() > rectangles.get(countt + 1).getBoundsInParent().getMinX() && myline.getEndX() < rectangles.get(countt + 1).getBoundsInParent().getMaxX()) {
                System.out.println("babbbb");
                moveRectangles();
            } else {
                TranslateTransition moveOut122 = new TranslateTransition(Duration.seconds(3), square);
                moveOut122.setToY(1000);
                moveOut122.play();
                moveOut122.setOnFinished(event -> {
                    saveGame();
                    switchToExit();
                });
            }
        });

    }
//    private void moveback(int countt,List<Rectangle> rectangles){
//
//    }
//    private void rotateLine() {
//        double startX = myline.getStartX();
//        double startY = myline.getStartY();
//        double endX = myline.getEndX();
//        double endY = myline.getEndY();
//
//        double deltaX = startY - endY;
//        double deltaY = endY;
//
//        myline.setEndX(startX + deltaX);
//        myline.setEndY(startY);
//
//    }
    private void stopIncreasingLine() {
        if (timeline != null) {
            timeline.stop();
        }
    }



    private void createLineOnRectangle(Rectangle rectangle) {
        plane.getChildren().remove(myline1);
        double lineStartX = rectangle.getBoundsInParent().getMinX() + rectangle.getWidth()-15;
        double lineStartY = rectangle.getLayoutY() -2 ; // Center of the rectangle
        double lineEndX = lineStartX ; // Adjust the length of the line as needed
        double lineEndY = lineStartY ;

        myline = new Line(lineStartX, lineStartY, lineEndX, lineEndY);
        myline.setStroke(Color.DARKBLUE);
        myline.setStrokeLineCap(StrokeLineCap.ROUND);
        myline.setStrokeWidth(7);

        // Add the line to the scene
        plane.getChildren().add(myline);
    }

    private int rectangleCounter = 2; // Start with 2 since 0 and 1 are already in the ArrayList

    private Color getRandomColor() {
        this.flag = 0;
        return Color.BLACK;
    }
    @FXML
    private void moveRectangles() {
//        System.out.println(countt+" "+rectangles.size()+rectangles.get(countt).getX());

        // Create a new rectangle
        Random random = new Random();
        int randomNumber1 = random.nextInt(80) + 50;
        Rectangle newRectangle = new Rectangle(randomNumber1, 232,getRandomColor());
        newRectangle.setStrokeLineCap(StrokeLineCap.ROUND);
//        newRectangle.setFill(Color.GREEN);
        newRectangle.setLayoutX(800); // Start at the right side of the screen
        newRectangle.setLayoutY(rectangle1.getLayoutY());
        rectangles.add(newRectangle);
        if(countt >= 2){
            System.out.println("start------ "+countt);
            System.out.println("count "+rectangles.get(countt).getBoundsInParent().getMinX());
            System.out.println("count+1 "+rectangles.get(countt+1).getBoundsInParent().getMinX());
            System.out.println("count+2 "+rectangles.get(countt+2).getBoundsInParent().getMinX());
            System.out.println("line "+myline.getBoundsInParent().getMinX());
            System.out.println("square "+square.getBoundsInParent().getMinX());
            System.out.println("------ ");
            System.out.println("babblu");
            TranslateTransition moveOutFirst = new TranslateTransition(Duration.seconds(2), rectangles.get(countt));
            moveOutFirst.setToX(rectangles.get(countt).getBoundsInParent().getMinX() - 1000);
            TranslateTransition moveLeftSecond = new TranslateTransition(Duration.seconds(2), rectangles.get(countt+1));
            moveLeftSecond.setToX(-(800 ) -rectangles.get(countt+1).getWidth() +100);
            TranslateTransition moveInNew = new TranslateTransition(Duration.seconds(2), rectangles.get(countt+2));
            Random random11 = new Random();
            int randomNumber = random11.nextInt(100) + randomNumber1 + 100;
            moveInNew.setToX(-(rectangles.get(countt+2).getBoundsInParent().getMinX()) + randomNumber );
            TranslateTransition moveInNew2 = new TranslateTransition(Duration.seconds(2), myline);
//            moveInNew2.setToX(-randomNumber1 - (myline.getEndX()- myline.getStartX())+50);
            moveInNew2.setToX(-(myline.getEndX())+50);
            TranslateTransition moveOut = new TranslateTransition(Duration.seconds(2), square);
//            moveOut.setToX(-randomNumber +randomNumber1 +30);
            moveOut.setToX(-53);
//            moveOut.setToX(-(myline.getEndX())+50);
            ParallelTransition parallelTransition = new ParallelTransition(moveOutFirst, moveLeftSecond, moveInNew,moveInNew2,moveOut);
            plane.getChildren().remove(cherryprev);
            parallelTransition.play();
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> checkCollision()));
            plane.getChildren().add(newRectangle);
            parallelTransition.setOnFinished(event -> {
                System.out.println("start------ "+countt);
                System.out.println("count "+rectangles.get(countt).getBoundsInParent().getMinX());
                System.out.println("count+1 "+rectangles.get(countt+1).getBoundsInParent().getMinX());
                System.out.println("count+2 "+rectangles.get(countt+2).getBoundsInParent().getMinX());
                System.out.println("line "+myline.getBoundsInParent().getMinX());
                System.out.println("square "+square.getBoundsInParent().getMinX());
                System.out.println("------ ");
                if(random.nextInt(0,2) == 0){
                cherryprev = new ImageView();
                Image  image = new Image(getClass().getResourceAsStream("cherry.png"));
                cherryprev.setImage(image);
                cherryprev.setFitWidth(20);
                cherryprev.setFitHeight(20);
                cherryprev.setImage(image);
                Random ab = new Random();
                int aaa =  ab.nextInt(90,randomNumber-randomNumber1+20);
                cherryprev.setLayoutX(aaa);
                cherryprev.setLayoutY(367);

                plane.getChildren().add(cherryprev);
//                myline1 = myline;
                countt++;
            }});
        }else if(countt ==1){
            System.out.println("start------ "+countt);
            System.out.println("count "+rectangles.get(countt).getBoundsInParent().getMinX());
            System.out.println("count+1 "+rectangles.get(countt+1).getBoundsInParent().getMinX());
            System.out.println("count+2 "+rectangles.get(countt+2).getBoundsInParent().getMinX());
            System.out.println("line "+myline.getBoundsInParent().getMinX());
            System.out.println("square "+square.getBoundsInParent().getMinX());
            System.out.println("------ ");
            System.out.println("hifir");
            TranslateTransition moveOutFirst = new TranslateTransition(Duration.seconds(2), rectangles.get(countt));
            moveOutFirst.setToX(rectangles.get(countt).getBoundsInParent().getMinX() - 1000);
            TranslateTransition moveLeftSecond = new TranslateTransition(Duration.seconds(2), rectangles.get(countt+1));
            moveLeftSecond.setToX(-800 -rectangles.get(countt+1).getWidth() + 100);
            TranslateTransition moveInNew = new TranslateTransition(Duration.seconds(2), rectangles.get(countt+2));
            Random random12 = new Random();
            int randomNumber = random12.nextInt(100) + randomNumber1 + 100;
            moveInNew.setToX(-(rectangles.get(countt+2).getBoundsInParent().getMinX()) + randomNumber );
            TranslateTransition moveInNew2 = new TranslateTransition(Duration.seconds(2), myline);
//            moveInNew2.setToX(-randomNumber1 - (myline.getEndX()- myline.getStartX())+50);
            moveInNew2.setToX(-(myline.getEndX())+50);
            TranslateTransition moveOut = new TranslateTransition(Duration.seconds(2), square);
            moveOut.setToX(-53);
            ParallelTransition parallelTransition = new ParallelTransition(moveOutFirst, moveLeftSecond, moveInNew,moveInNew2,moveOut);
            plane.getChildren().remove(cherryprev);
            parallelTransition.play();
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> checkCollision()));
            plane.getChildren().add(newRectangle);
            parallelTransition.setOnFinished(event -> {
                System.out.println("start------ "+countt);
                System.out.println("count "+rectangles.get(countt).getBoundsInParent().getMinX());
                System.out.println("count+1 "+rectangles.get(countt+1).getBoundsInParent().getMinX());
                System.out.println("count+2 "+rectangles.get(countt+2).getBoundsInParent().getMinX());
                System.out.println("line "+myline.getBoundsInParent().getMinX());
                System.out.println("square "+square.getBoundsInParent().getMinX());
                System.out.println("------ ");
                cherryprev = new ImageView();
                Image  image = new Image(getClass().getResourceAsStream("cherry.png"));
                cherryprev.setImage(image);
                cherryprev.setFitWidth(20);
                cherryprev.setFitHeight(20);
                cherryprev.setImage(image);
                Random ab = new Random();
                int aaa =  ab.nextInt(90,randomNumber-randomNumber1+20);
                cherryprev.setLayoutX(aaa);
                cherryprev.setLayoutY(367);
                plane.getChildren().add(cherryprev);
//                myline1 = myline;
                countt++;
            });
        }
        else {
            System.out.println("start------ "+countt);
            System.out.println("count "+rectangles.get(countt).getBoundsInParent().getMinX());
            System.out.println("count+1 "+rectangles.get(countt+1).getBoundsInParent().getMinX());
            System.out.println("count+2 "+rectangles.get(countt+2).getBoundsInParent().getMinX());
            System.out.println("line "+myline.getBoundsInParent().getMinX());
            test1 =  myline.getBoundsInParent().getMinX();
            System.out.println("square "+square.getBoundsInParent().getMinX());
            System.out.println("------ ");
            TranslateTransition moveOutFirst = new TranslateTransition(Duration.seconds(2), rectangles.get(countt));
            moveOutFirst.setToX(rectangles.get(countt).getBoundsInParent().getMinX() - 300);
            TranslateTransition moveLeftSecond = new TranslateTransition(Duration.seconds(2), rectangles.get(countt + 1));
            double bcd = rectangles.get(countt + 1).getBoundsInParent().getMaxX();
            moveLeftSecond.setToX(-(bcd) +100);
            TranslateTransition moveInNew = new TranslateTransition(Duration.seconds(2), rectangles.get(countt + 2));
            int a = random.nextInt(150)+130;
            moveInNew.setToX(-(rectangles.get(countt + 2).getBoundsInParent().getMinX() - bcd) );
            TranslateTransition moveInNew2 = new TranslateTransition(Duration.seconds(2), myline);
            moveInNew2.setToX(-(myline.getEndX())+50);
            TranslateTransition moveOut = new TranslateTransition(Duration.seconds(2), square);
            moveOut.setToX(-(bcd) + 288);
            ParallelTransition parallelTransition = new ParallelTransition(moveOutFirst, moveLeftSecond, moveInNew,moveInNew2,moveOut);
            parallelTransition.play();
            plane.getChildren().add(newRectangle);
            parallelTransition.setOnFinished(event -> {
                System.out.println("start------ "+countt);
                System.out.println("count "+rectangles.get(countt).getBoundsInParent().getMinX());
                System.out.println("count+1 "+rectangles.get(countt+1).getBoundsInParent().getMinX());
                System.out.println("count+2 "+rectangles.get(countt+2).getBoundsInParent().getMinX());
                System.out.println("line "+myline.getBoundsInParent().getMinX());
                System.out.println("square "+square.getBoundsInParent().getMinX());
                System.out.println("------ ");
                System.out.println(square.getBoundsInParent().getMinX());
                countt++;
                cherryprev = new ImageView();
                Image  image = new Image(getClass().getResourceAsStream("cherry.png"));
                cherryprev.setImage(image);
                cherryprev.setFitWidth(20);
                cherryprev.setFitHeight(20);
                cherryprev.setImage(image);
                Random ab = new Random();
                int aaa =  ab.nextInt(100)+140;
                cherryprev.setLayoutX(aaa);
                cherryprev.setLayoutY(367);
                plane.getChildren().add(cherryprev);

            });
        }
    }
    private void checkCollision() {
        // Check for collision with cherry
        if (isColliding(square, cherryprev)) {
            System.out.println("Collision with cherry!");
            plane.getChildren().remove(cherryprev);
        }

    }
    private boolean isColliding(Node rect1, Node rect2) {
        Bounds bounds1 = rect1.getBoundsInParent();
        Bounds bounds2 = rect2.getBoundsInParent();
        return bounds1.intersects(bounds2);
    }
    @FXML
    private  void handledown1(){

            TranslateTransition moveOutFirst = new TranslateTransition(Duration.seconds(0.1), square);
            moveOutFirst.setToY(60);
            moveOutFirst.play();
            moveOutFirst.setOnFinished(actionEvent -> {
                position = -1;
                System.out.println("moved down");
            });
    }
    @FXML
    private  void handledown2() {
        TranslateTransition moveOutFirst = new TranslateTransition(Duration.seconds(0.1), square);
        moveOutFirst.setToY(0);
        moveOutFirst.play();
        moveOutFirst.setOnFinished(actionEvent -> {
            position = 1;
            System.out.println("moved up");
        });

    }

    private void CheckCherryCollision(){}

    private void checkContinuousCollision() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        // Run the collision-checking task every 100 milliseconds
        scheduler.scheduleAtFixedRate(this::checkCollision22, 0, 1, TimeUnit.MILLISECONDS);
    }
    private void checkCollision22() {
        if (isColliding22(rectangles.get(countt), square) && collbigrectanlge ==0) {
            collbigrectanlge =1;
//            collisionCount++;
            System.out.println("Collision detected! Count1: " +countt);
            TranslateTransition moveOutFirst = new TranslateTransition(Duration.seconds(2), square);
            moveOutFirst.setToY(500);
            moveOutFirst.play();
           moveOutFirst.setOnFinished(event -> {
               saveGame();
               switchToExit();});
            // Add your logic here when a collision is detected
        }

        if (isColliding22(rectangles.get(countt+1), square) && collbigrectanlge ==0) {
            collbigrectanlge =1;

//            collisionCount++;
            System.out.println("Collision detected! Count1: "+countt+1 );
            TranslateTransition moveOutFirst = new TranslateTransition(Duration.seconds(2), square);
            moveOutFirst.setToY(500);
            moveOutFirst.play();
            moveOutFirst.setOnFinished(event -> {
                saveGame();
                switchToExit();});
            // Add your logic here when a collision is detected
        }

        if(flag == 0){
            if(cherryprev != null){
                if(isColliding22(cherryprev,square)){
//                    SoundPlayer.playHit();
                    playBeepSound(1);
                    this.flag =1;
                    System.out.println("colisoned with cherry and incremented");
                    cherry_count++;
                    cherries_final.setText("Cherries:  "+ cherry_count);
                }
            }
        }


    }
    public static void playBeepSound(int numberOfTimes) {
        for (int i = 0; i < numberOfTimes; i++) {
            Toolkit.getDefaultToolkit().beep();
            try {
                // Pause for a short time between beeps
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isColliding22(Node rect1, Node rect2) {
        Bounds bounds1 = rect1.getBoundsInParent();
        Bounds bounds2 = rect2.getBoundsInParent();
        return bounds1.intersects(bounds2);
    }
    @FXML
    public void flip() {
        if (position == 1) {
            // Straighten the image
            RotateTransition rotateStraight = new RotateTransition(Duration.millis(10), square);
            rotateStraight.setToAngle(180);

            TranslateTransition moveDown = new TranslateTransition(Duration.millis(10), square);
            moveDown.setToY(40);
            ParallelTransition parallelTransition = new ParallelTransition(moveDown,rotateStraight);
            parallelTransition.play();

//            rotateStraight.play();
//            moveDown.play();

            position = -1;
        } else {
            // Flip the image
            RotateTransition rotateFlip = new RotateTransition(Duration.millis(10), square);
            rotateFlip.setToAngle(0);

            TranslateTransition moveUp = new TranslateTransition(Duration.millis(10), square);
            moveUp.setToY(0);

            ParallelTransition parallelTransition = new ParallelTransition(moveUp,rotateFlip);
            parallelTransition.play();
//
//            rotateFlip.play();
//            moveUp.play();

            position = 1;
        }
    }
    public void saveGame(){
        GameState.setPresent_score(countt+count2-1);
        GameState.setPresent_cherries(cherry_count);
        GameState.setHighest_score(countt+count2-1);
    }
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void a() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("exit.fxml"));
        Parent root = fxmlLoader.load();

    }
    public void switchToExit(){
        Platform.runLater(() -> {
            try {
                // Load the new FXML file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("exit.fxml"));
                Parent root = loader.load();

                // Create a new scene
                Scene scene = new Scene(root);

                // Get the stage information
                Stage stage = (Stage) plane.getScene().getWindow();

                // Set the new scene on the stage
                stage.setScene(scene);

                // Show the stage with the new scene
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }



}
