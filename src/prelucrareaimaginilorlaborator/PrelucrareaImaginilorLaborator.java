/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prelucrareaimaginilorlaborator;

import java.awt.Color;
import java.awt.color.ColorSpace;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
/**
 *
 * @bogdan
 *
 *
API-urile JavaFX au fost portate direct în Java, bazându-se mai mult pe standarde Internet, cum ar fi CSS pentru stilizarea controalelor sau ARIA pentru specificări referitoare la accesibilitate.

Graful de Scene
Implementarea unei aplicații JavaFX implică proiectarea și dezvoltarea unui graf de scene (eng. Scene Graph),
structură ierarhică de noduri ce conţine elementele vizuale ale interfeţei grafice cu utilizatorul,
care poate trata diferite evenimente legate de acestea şi care poate fi redată.

Un element din graful de scene (= un nod) este identificat în mod unic, fiind caracterizat printr-o clasă de stil şi
un volum care îl delimitează.
Fiecare nod are un părinte (cu excepția nodului rădăcină), putând avea nici unul, unul sau mai mulţi copii.
De asemenea, pentru un astfel de element pot fi definite efecte (estompări sau umbre), opacitate, transformări,
mecanisme de tratare a diferitelor evenimente (care vizează interacţiunea cu utilizatorul) precum şi starea aplicaţiei.

Spre diferenţă de Swing sau AWT (Abstract Window Toolkit), JavaFX conţine pe lângă mecanisme de dispunere a conţinutului, controale, imagini sau obiecte multimedia şi
primitive pentru elemente grafice (ca fi texte sau figuri geometice cu care se pot crea animaţii, folosind metodele puse la dispoziţie de API-urile javafx.animation).

API-ul javafx.scene permite construirea următoarelor conţinuturi:

noduri: forme 2D şi 3D, imagini, conţinut multimedia şi conţinut Internet, text, controale pentru interacţiunea cu utilizatorul, grafice, containere;
stări: transformări (poziţionări şi orientări ale nodurilor), efecte vizuale;
efecte: obiecte care modifică aspectul nodurilor (mecanisme de estompare, umbre, reglarea culorilor).
Mecanisme de Dispunere a Conţinutului
Controalele din graful de scene pot fi grupate în containere sau panouri în mod flexibil, folosind mai multe mecanisme de dispunere a conținutului (eng. layout).

API-ul JavaFX defineşte mai multe clase de tip container pentru dispunerea elementelor, în pachetul javafx.scene.layout:

BorderPane dispune nodurile conţinute în regiunile de sus, jos, dreapta, stânga sau centru;
HBox îşi aranjează conţinutul orizontal pe un singur rând;
VBox îşi aranjează conţinutul vertical pe o singură coloană;
StackPane utilizează o stivă de noduri afişând elementele unele peste altele, din spate către față;
GridPane permite utilizatorului să îşi definească un tabel (format din rânduri şi coloane) în care să poată fi încadrate elementele conţinute;
FlowPane dispune elementele fie orizontal, fie vertical, în funcţie de limitele specificate de programator (lungime pentru dispunere orizontală, respectiv înălţime pentru dispunere verticală);
TilePane plasează nodurile conţinute în celule de dimensiuni uniforme;
AnchorPane oferă programatorilor posibilitatea de a defini noduri ancoră (referinţă) în funcţie de colţurile de jos / sus, din stânga / dreapta sau raportat la centrul containerului sau panoului.
Diferitele moduri de dispunere pot fi imbricate în cadrul unei aplicaţii JavaFX pentru a se obţine funcţionalitatea dorită.

 */
public class PrelucrareaImaginilorLaborator extends Application {
    BufferedImage bufferedImag;
    Label name;
    @Override
    public void start(Stage mainStage) {

        //Avem nevoie de o modalitate de a alege un fisier imagine
        //Vom folosi FileChooser
        //
        ImageView imageView = new ImageView();

        StackPane root = new StackPane();
        Scene scene = new Scene(root);
        Label valoarePixel = new Label();

        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("Fisiere");
        MenuItem menuItem_Open = new MenuItem("Alege Imagine");
        MenuItem menuItem_Save = new MenuItem("Salveaza Imagine");
        SeparatorMenuItem sep = new SeparatorMenuItem();
        MenuItem menuItem_Exit = new MenuItem("Iesire");
        menuFile.getItems().addAll(menuItem_Open,menuItem_Save,sep,menuItem_Exit);
        Menu menuChange = new Menu("Modificare");
        MenuItem menuItem_RGB = new MenuItem("RGB");
        MenuItem menuItem_Gri = new MenuItem("Gri");
        MenuItem menuItem_Crominanta = new MenuItem("Crominanta si luminanta");
        MenuItem menuItem_Etichetare = new MenuItem("Etichetare");
        MenuItem menuItem_HSV = new MenuItem("HSV");
        MenuItem menuItem_Histogram = new MenuItem("Histograma");
        menuItem_Etichetare.setDisable(true);
        menuItem_HSV.setDisable(true);
        menuItem_Gri.setDisable(true);
        menuItem_Crominanta.setDisable(true);
        menuItem_RGB.setDisable(true);
        menuChange.getItems().addAll(menuItem_RGB, menuItem_Gri,menuItem_Crominanta, menuItem_HSV,menuItem_Etichetare, menuItem_Histogram);
        Menu menuMediere = new Menu("Mediere");
        MenuItem Mediere = new MenuItem("Mediere");
        MenuItem Accentuare = new MenuItem("Accentuare");
        MenuItem FiltruMedian = new MenuItem("Filtru Median");
        MenuItem filterLaplacian = new MenuItem("FilterLaplacian");
        MenuItem Dilate_menuItem = new MenuItem("dilate");
        MenuItem EdgeDetect_menuItem = new MenuItem("Detectare margini");
        MenuItem menuItemTrece_Sus = new MenuItem("Trece_sus");
        MenuItem menuItemMin = new MenuItem("Minim");
        MenuItem menuItemMax = new MenuItem("Maxim");
        MenuItem menuItemEnhancer = new MenuItem("Enhancer");
        menuMediere.getItems().addAll(Mediere,Accentuare,FiltruMedian,filterLaplacian,Dilate_menuItem,EdgeDetect_menuItem, menuItemTrece_Sus,menuItemEnhancer,menuItemMax,menuItemMin);

        menuBar.getMenus().addAll(menuFile, menuChange, menuMediere);

        VBox vbox = new VBox(menuBar, valoarePixel);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(0, 10, 0, 10));

        ScrollPane sp = new ScrollPane();
        vbox.getChildren().add(sp);
        //alegere imagine
        menuItem_Open.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Afiseaza Imagine");

            File file = fileChooser.showOpenDialog(mainStage);
            if (file != null) {
                try {
                    menuItem_Histogram.setDisable(false);
                    menuItem_Etichetare.setDisable(false);
                    menuItem_Gri.setDisable(false);
                    menuItem_Crominanta.setDisable(false);
                    menuItem_HSV.setDisable(false);
                    VBox vbOpen = new VBox();
                    name = new Label(file.getAbsolutePath());
                    bufferedImag = ImageIO.read(file);

                    BufferedImage imageN = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_ARGB);

                    for (int y = 0; y < bufferedImag.getHeight(); y++) {
                        for (int x = 0; x < bufferedImag.getWidth(); x++) {
                            //Retrieving contents of a pixel
//                           setPixel(bufferedImag.getRGB(x, y));
                            int pixel = bufferedImag.getRGB(x, y);
                            Color color = new Color(pixel, true);

                            //Retrieving the R G B values
                            int alpha = color.getAlpha();
                            int red = color.getRed();
                            int green = color.getGreen();
                            int blue = color.getBlue();
                            Color myWhite = new Color(red, green, blue, alpha);
                            imageN.setRGB(x, y, myWhite.getRGB());
                        }
                    }

                    Image image = SwingFXUtils.toFXImage(imageN, null);
                    vbOpen.getChildren().addAll(name,imageView);

                    imageView.setFitHeight(400);
                    imageView.setPreserveRatio(true);
                    imageView.setImage(image);
                    imageView.setSmooth(true);
                    imageView.setCache(true);

                    sp.setContent(vbOpen);

                    menuBar.getMenus().get(1).getItems().get(0).setDisable(false);

                } catch (IOException ex) {
                    Logger.getLogger(PrelucrareaImaginilorLaborator.class.getName()).log(Level.SEVERE, null, ex);
                }
                imageView.setOnMouseClicked(e -> {
                    double mouseX = e.getX();
                    double mouseY = e.getY();
                    int x = (int) mouseX;
                    int y = (int) mouseY;
                    int pixel = bufferedImag.getRGB(x, y);
                    Color color = new Color(pixel, true);
                    valoarePixel.setText("Valoarea pixelului la " + x + ", " + y + " este: " + color + " / " + pixel);
                });
            }
        });



        menuItem_Save.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Salvare Imagine");

            File file = fileChooser.showSaveDialog(mainStage);
            if (file != null) {
                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(imageView.getImage(),null), "png", file);
                } catch (IOException ex) {
                    Logger.getLogger(PrelucrareaImaginilorLaborator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });


        menuItem_HSV.setOnAction((ActionEvent)->{
            ScrollPane scrHSV = new ScrollPane();
            BufferedImage bufferedHSV = new BufferedImage(bufferedImag.getWidth(),bufferedImag.getHeight(),BufferedImage.TYPE_INT_RGB);
            for (int y = 0; y < bufferedImag.getHeight(); y++) {
                for (int x = 0; x < bufferedImag.getWidth(); x++) {
                    //Retrieving contents of a pixel
                    int pixel = bufferedImag.getRGB(x,y);
                    //Creating a Color object from pixel value
                    Color color = new Color(pixel, true);
                    float R = color.getRed();
                    float G = color.getGreen();
                    float B = color.getBlue();

                    float r = R/255; // r : componenta R normalizată

                    float g = G/255; // g : componenta G normalizată

                    float b = B/255; // b : componenta B normalizată

                    float maxRGB = Math.max(Math.max(r, g), b);
                    float minRGB = Math.min(Math.min(r, g), b);

                    float C = maxRGB - minRGB;
                    //value - V
                    float V = maxRGB;

                    float S, H = 0;
                    //Saturation - S
                    if (V != 0){
                        S = C - V;
                    }
                    else{
                        S = 0;
                    }
                    //Hue - H
                    if(C != 0){
                        if (maxRGB == r){
                            H = 60* (g-b)/C;
                        } else if (maxRGB == b) {
                            H = 120 + 60*(b-r)/C;
                        } else if (maxRGB == g) {
                            H = 240 + 60 * (r - g)/C;
                        }
                    }
                    else {
                        H = 0;
                    }

                    if(H < 0){
                        H = H + 360;
                    }
                    float H_norm = H*255/360;
                    float S_norm = S*255;
                    float V_norm=V*255;

                    Color HSV = Color.getHSBColor(H_norm, S_norm, V_norm);
                    bufferedHSV.setRGB(x, y, HSV.getRGB());
                }
            }
            VBox vbHSV = new VBox();
            Image imgHSV = SwingFXUtils.toFXImage(bufferedHSV, null);
            ImageView imageViewHSV = new ImageView();
            imageViewHSV.setFitHeight(400);
            imageViewHSV.setPreserveRatio(true);
            imageViewHSV.setImage(imgHSV);
            imageViewHSV.setSmooth(true);
            imageViewHSV.setCache(true);
            scrHSV.setContent(imageViewHSV);
            vbHSV.getChildren().addAll(imageViewHSV);

            Stage fifthStage = new Stage();
            Scene sceneHSV = new Scene(new HBox(), 1700, 800);
            ((HBox)sceneHSV.getRoot()).getChildren().addAll(scrHSV);
            fifthStage.setTitle(name.getText());
            fifthStage.setScene(sceneHSV);
            fifthStage.show();
        });

        menuItem_Gri.setOnAction((ActionEvent)->{
            ScrollPane scrGri1 = new ScrollPane();
            ScrollPane scrGri2 = new ScrollPane();
            ScrollPane scrGri3 = new ScrollPane();


            BufferedImage Gri1 = new BufferedImage(bufferedImag.getWidth(),bufferedImag.getHeight(),BufferedImage.TYPE_INT_RGB);
            BufferedImage Gri2 = new BufferedImage(bufferedImag.getWidth(),bufferedImag.getHeight(),BufferedImage.TYPE_INT_RGB);
            BufferedImage Gri3 = new BufferedImage(bufferedImag.getWidth(),bufferedImag.getHeight(),BufferedImage.TYPE_INT_RGB);

            for (int y = 0; y < bufferedImag.getHeight(); y++) {
                for (int x = 0; x < bufferedImag.getWidth(); x++) {
                    //Retrieving contents of a pixel
                    int pixel = bufferedImag.getRGB(x,y);
                    //Creating a Color object from pixel value
                    Color color = new Color(pixel, true);
                    //Retrieving the R G B values
                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();
                    int gray1 = (red + green + blue)/3;
                    int gray2 = (int)(0.299 * red + 0.587 * green + 0.114* blue);
                    Color myGray1 = new Color(gray1,gray1,gray1);
                    Color myGray2 = new Color(gray2,gray2,gray2);
                    int maxRGB = Math.max(Math.max(red, green), blue);
                    int minRGB = Math.min(Math.min(red, green), blue);
                    int gray3 = (maxRGB + minRGB) / 2;
                    Color grayV3 = new Color(gray3, gray3, gray3);
                    Gri1.setRGB(x,y,myGray1.getRGB());
                    Gri2.setRGB(x,y,myGray2.getRGB());
                    Gri3.setRGB(x, y, grayV3.getRGB());
                }
            }


            VBox vbGray1 = new VBox();
            Image imgGray1 = SwingFXUtils.toFXImage(Gri1, null);
            ImageView imageViewGray1 = new ImageView();
            imageViewGray1.setFitHeight(400);
            imageViewGray1.setPreserveRatio(true);
            imageViewGray1.setImage(imgGray1);
            imageViewGray1.setSmooth(true);
            imageViewGray1.setCache(true);
            scrGri1.setContent(imageViewGray1);
            vbGray1.getChildren().addAll(imageViewGray1);


            VBox vbGray2 = new VBox();
            Image imgGray2 = SwingFXUtils.toFXImage(Gri2, null);
            ImageView imageViewGray2 = new ImageView();
            imageViewGray2.setFitHeight(400);
            imageViewGray2.setPreserveRatio(true);
            imageViewGray2.setImage(imgGray2);
            imageViewGray2.setSmooth(true);
            imageViewGray2.setCache(true);
            scrGri2.setContent(imageViewGray2);

            vbGray2.getChildren().addAll(imageViewGray2);

            VBox vbGray3 = new VBox();
            Image imgGray3 = SwingFXUtils.toFXImage(Gri3, null);
            ImageView imageViewGray3 = new ImageView();
            imageViewGray3.setFitHeight(400);
            imageViewGray3.setPreserveRatio(true);
            imageViewGray3.setImage(imgGray3);
            imageViewGray3.setSmooth(true);
            imageViewGray3.setCache(true);
            scrGri3.setContent(imageViewGray3);

            vbGray3.getChildren().addAll(imageViewGray3);


            Stage thirdStage = new Stage();
            Scene sceneGray = new Scene(new HBox(), 1700, 800);
            ((HBox)sceneGray.getRoot()).getChildren().addAll(scrGri1, scrGri2, scrGri3);
            thirdStage.setTitle(name.getText());
            thirdStage.setScene(sceneGray);
            thirdStage.show();


        });

        menuItem_Crominanta.setOnAction((ActionEvent)->{
            ScrollPane scrCrom = new ScrollPane();
            ScrollPane scrCrom2 = new ScrollPane();
            BufferedImage Crom = new BufferedImage(bufferedImag.getWidth(),bufferedImag.getHeight(),BufferedImage.TYPE_INT_RGB);
            BufferedImage Crom2 = new BufferedImage(bufferedImag.getWidth(),bufferedImag.getHeight(),BufferedImage.TYPE_INT_RGB);

            for (int y = 0; y < bufferedImag.getHeight(); y++) {
                for (int x = 0; x < bufferedImag.getWidth(); x++) {
                    //Retrieving contents of a pixel
                    int pixel = bufferedImag.getRGB(x,y);
                    //Creating a Color object from pixel value
                    Color color = new Color(pixel, true);
                    //Retrieving the R G B value
                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();
                    int Y = (int) (0.3*red + 0.6*green + 0.1 * blue);
                    int U = (int)(0.74*(red-Y)+0.26*(blue-Y));
                    int V = (int) (0.48*(red-Y)+0.41*(blue-Y));

                    int Yg= (int) (0.299*red + 0.587*green + 0.114*blue);
                    int Cb = (int) ((int)(-0.1687*red)-(0.3313*green)+(0.498*blue)+128);
                    int Cr = (int) ((int) (0.498*red)-(0.4187*green)-(0.0813*blue)+128);

//                    int r2 = (int) (Yg + 1.402 * (Cr - 128));
//                    int g2 = (int) (Yg - 0.34414 * (Cb - 128) - 0.71414 * (Cr - 128));
//                    int b2 = (int) (Yg + 1.772 * (Cb - 128));

// Clamp RGB values to the range [0, 255]
                    Y = Math.min(Math.max(Y, 0), 255);
                    U = Math.min(Math.max(U, 0), 255);
                    V= Math.min(Math.max(V, 0), 255);

                    Yg = Math.min(Math.max(Yg, 0), 255);
                    Cb = Math.min(Math.max(Cb, 0), 255);
                    Cr = Math.min(Math.max(Cr, 0), 255);



// Create the Color object with RGB values
                    Color rgbColor = new Color(Y,U,V);
                    Color rgbColor2 = new Color(Yg,Cb,Cr);


                    Crom.setRGB(x,y,rgbColor.getRGB());
                    Crom2.setRGB(x,y,rgbColor2.getRGB());
                }
            }


            VBox vbCrom1 = new VBox();
            VBox vbCrom2 = new VBox();
            Image imgCrom2 = SwingFXUtils.toFXImage(Crom2,null);
            Image imgCrom1 = SwingFXUtils.toFXImage(Crom, null);
            ImageView imageViewCrom1 = new ImageView();
            ImageView imageViewCrom2 = new ImageView();
            imageViewCrom1.setFitHeight(400);
            imageViewCrom2.setFitHeight(400);
            imageViewCrom1.setPreserveRatio(true);
            imageViewCrom2.setPreserveRatio(true);
            imageViewCrom1.setImage(imgCrom1);
            imageViewCrom2.setImage(imgCrom2);
            imageViewCrom1.setSmooth(true);
            imageViewCrom2.setSmooth(true);
            imageViewCrom1.setCache(true);
            imageViewCrom2.setCache(true);
            scrCrom.setContent(imageViewCrom1);
            scrCrom2.setContent(imageViewCrom2);
            vbCrom1.getChildren().addAll(imageViewCrom1);
            vbCrom2.getChildren().addAll(imageViewCrom2);


            Stage fourthStage = new Stage();

            Scene sceneCrom = new Scene(new HBox(), 1700, 800);
            ((HBox)sceneCrom.getRoot()).getChildren().addAll(scrCrom,scrCrom2);

            fourthStage.setTitle(name.getText());
            fourthStage.setScene(sceneCrom);
            fourthStage.show();

        });

        menuItem_RGB.setOnAction((ActionEvent event) -> {
            ScrollPane spR = new ScrollPane();
            ScrollPane spG = new ScrollPane();
            ScrollPane spB = new ScrollPane();
            ScrollPane spRGB = new ScrollPane();

            BufferedImage imageR = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);
            BufferedImage imageG = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);
            BufferedImage imageB = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);
            BufferedImage imageRGB = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);

            for (int y = 0; y < bufferedImag.getHeight(); y++) {
                for (int x = 0; x < bufferedImag.getWidth(); x++) {
                    //Retrieving contents of a pixel
                    int pixel = bufferedImag.getRGB(x,y);
                    //Creating a Color object from pixel value
                    Color color = new Color(pixel, true);
                    //Retrieving the R G B values
                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();
                    Color myR = new Color(red, 0, 0);
                    Color myG = new Color(0, green, 0);
                    Color myB = new Color(0, 0, blue);
                    imageR.setRGB(x, y, myR.getRGB());
                    imageG.setRGB(x, y, myG.getRGB());
                    imageB.setRGB(x, y, myB.getRGB());
                    imageRGB.setRGB(x, y, pixel);
                }
            }


            VBox vbR = new VBox();
            Image imgR = SwingFXUtils.toFXImage(imageR, null);
            ImageView imageViewR = new ImageView();
            imageViewR.setFitHeight(400);
            imageViewR.setPreserveRatio(true);
            imageViewR.setImage(imgR);
            imageViewR.setSmooth(true);
            imageViewR.setCache(true);
            spR.setContent(imageViewR);
            Slider sliderR = new Slider();
            sliderR.setMin(0);
            sliderR.setMax(255);
            sliderR.setValue(0);
            sliderR.setShowTickLabels(true);
            sliderR.setShowTickMarks(true);
            sliderR.setMajorTickUnit(50);
            sliderR.setMinorTickCount(5);
            sliderR.setBlockIncrement(1);
            sliderR.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                int dif = new_val.intValue();// - old_val.intValue();
                for (int y = 0; y < bufferedImag.getHeight(); y++) {
                    for (int x = 0; x < bufferedImag.getWidth(); x++) {
                        int pixel = bufferedImag.getRGB(x, y);
                        Color color = new Color(pixel, true);
                        //Retrieving the R G B values
                        int red = this.adjustColor(color.getRed(), dif);

                        Color myR = new Color(red, 0, 0);


                        imageR.setRGB(x, y, myR.getRGB());
                    }
                }
                imageViewR.setImage(SwingFXUtils.toFXImage(imageR, null));
            });




            VBox vbG = new VBox();
            Image imgG = SwingFXUtils.toFXImage(imageG, null);
            ImageView imageViewG = new ImageView();
            imageViewG.setFitHeight(400);
            imageViewG.setPreserveRatio(true);
            imageViewG.setImage(imgG);
            imageViewG.setSmooth(true);
            imageViewG.setCache(true);
            spG.setContent(imageViewG);
            Slider sliderG = new Slider();
            sliderG.setMin(0);
            sliderG.setMax(255);
            sliderG.setValue(0);
            sliderG.setShowTickLabels(true);
            sliderG.setShowTickMarks(true);
            sliderG.setMajorTickUnit(50);
            sliderG.setMinorTickCount(5);
            sliderG.setBlockIncrement(1);
            sliderG.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                int dif = new_val.intValue();// - old_val.intValue();
                for (int y = 0; y < bufferedImag.getHeight(); y++) {
                    for (int x = 0; x < bufferedImag.getWidth(); x++) {
                        int pixel = bufferedImag.getRGB(x, y);
                        Color color = new Color(pixel, true);
                        //Retrieving the R G B values
                        int green = this.adjustColor(color.getGreen(), dif);

                        Color myG = new Color(0, green, 0);


                        imageG.setRGB(x, y, myG.getRGB());
                    }
                }
                imageViewG.setImage(SwingFXUtils.toFXImage(imageG, null));
            });



            VBox vbB = new VBox();
            Image imgB = SwingFXUtils.toFXImage(imageB, null);
            ImageView imageViewB = new ImageView();
            imageViewB.setFitHeight(400);
            imageViewB.setPreserveRatio(true);
            imageViewB.setImage(imgB);
            imageViewB.setSmooth(true);
            imageViewB.setCache(true);
            spB.setContent(imageViewB);
            Slider sliderB = new Slider();
            sliderB.setMin(0);
            sliderB.setMax(255);
            sliderB.setValue(0);
            sliderB.setShowTickLabels(true);
            sliderB.setShowTickMarks(true);
            sliderB.setMajorTickUnit(50);
            sliderB.setMinorTickCount(5);
            sliderB.setBlockIncrement(1);
            sliderB.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                int dif = new_val.intValue();// - old_val.intValue();
                for (int y = 0; y < bufferedImag.getHeight(); y++) {
                    for (int x = 0; x < bufferedImag.getWidth(); x++) {
                        int pixel = bufferedImag.getRGB(x, y);
                        Color color = new Color(pixel, true);
                        //Retrieving the R G B values
                        int blue = this.adjustColor(color.getBlue(), dif);
                        Color myB = new Color(0, 0, blue);
                        imageB.setRGB(x, y, myB.getRGB());
                    }
                }
                imageViewB.setImage(SwingFXUtils.toFXImage(imageB, null));
            });
            VBox vbRGB = new VBox();

            Image imgRGB = SwingFXUtils.toFXImage(imageRGB, null);
            ImageView imageViewRGB = new ImageView();
            imageViewRGB.setFitHeight(400);
            imageViewRGB.setPreserveRatio(true);
            imageViewRGB.setImage(imgRGB);
            imageViewRGB.setSmooth(true);
            imageViewRGB.setCache(true);

            Slider sliderRGB = new Slider();
            sliderRGB.setMin(0);
            sliderRGB.setMax(255);
            sliderRGB.setValue(0);
            sliderRGB.setShowTickLabels(true);
            sliderRGB.setShowTickMarks(true);
            sliderRGB.setMajorTickUnit(50);
            sliderRGB.setMinorTickCount(5);
            sliderRGB.setBlockIncrement(1);
            sliderRGB.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                int dif = new_val.intValue();// - old_val.intValue();
                for (int y = 0; y < bufferedImag.getHeight(); y++) {
                    for (int x = 0; x < bufferedImag.getWidth(); x++) {
                        int pixel = bufferedImag.getRGB(x, y);
                        Color color = new Color(pixel, true);
                        //Retrieving the R G B values
                        int red = this.adjustColor( color.getRed(), dif);
                        int green  = this.adjustColor(color.getGreen(), dif);
                        int blue = this.adjustColor(color.getBlue(), dif);

                        Color myR = new Color(red, 0, 0);
                        Color myG = new Color(0, green, 0);
                        Color myB = new Color(0, 0, blue);
                        Color myRGB = new Color(red, green, blue);

                        imageRGB.setRGB(x, y, myRGB.getRGB());
                        imageR.setRGB(x, y, myR.getRGB());
                        imageG.setRGB(x, y, myG.getRGB());
                        imageB.setRGB(x, y, myB.getRGB());
                    }
                }
                imageViewR.setImage(SwingFXUtils.toFXImage(imageR, null));
                imageViewG.setImage(SwingFXUtils.toFXImage(imageG, null));
                imageViewB.setImage(SwingFXUtils.toFXImage(imageB, null));
                imageViewRGB.setImage(SwingFXUtils.toFXImage(imageRGB, null));
            });
            vbR.getChildren().addAll(imageViewR, sliderR);
            spR.setContent(vbR);
            vbG.getChildren().addAll(imageViewG, sliderG);
            spG.setContent(vbG);
            vbB.getChildren().addAll(imageViewB, sliderB);
            spB.setContent(vbB);
            vbRGB.getChildren().addAll(imageViewRGB, sliderRGB);
            spRGB.setContent(vbRGB);

            Stage secondStage = new Stage();

            Scene sceneRGB = new Scene(new HBox(), 1700, 800);
            ((HBox)sceneRGB.getRoot()).getChildren().addAll(spR, spG, spB, spRGB);

            secondStage.setTitle(name.getText());
            secondStage.setScene(sceneRGB);
            secondStage.show();

        });

        menuItem_Etichetare.setOnAction((ActionEvent event) -> {
            // Creează o imagine goală pentru etichetare
            BufferedImage imageEtichete = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);

            // Inițializează matricea de etichete și numărul de etichete
            int[][] labels = new int[bufferedImag.getHeight()][bufferedImag.getWidth()];
            int numLabels = 0;

            // Etichetare componentelor conectate folosind algoritmul BFS
            for (int i = 0; i < bufferedImag.getHeight(); ++i) {
                for (int j = 0; j < bufferedImag.getWidth(); ++j) {
                    if (labels[i][j] == 0 && isBlack(bufferedImag.getRGB(j, i))) {
                        numLabels++;
                        Queue<int[]> queue = new LinkedList<>();
                        labels[i][j] = numLabels;
                        queue.add(new int[]{i, j});

                        while (!queue.isEmpty()) {
                            int[] curr = queue.remove();
                            int currRow = curr[0];
                            int currCol = curr[1];

                            for (int k = -1; k <= 1; k++) {
                                for (int m = -1; m <= 1; m++) {
                                    int newRow = currRow + k;
                                    int newCol = currCol + m;
                                    if (isValidIndex(newRow, newCol, bufferedImag.getHeight(), bufferedImag.getWidth())
                                            && labels[newRow][newCol] == 0
                                            && isBlack(bufferedImag.getRGB(newCol, newRow))) {
                                        labels[newRow][newCol] = numLabels;
                                        queue.add(new int[]{newRow, newCol});
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Generează o culoare aleatoare unică pentru fiecare etichetă și setează pixelii corespunzători
            Map<Integer, Color> labelColors = new HashMap<>();
            for (int i = 1; i <= numLabels; i++) {
                labelColors.put(i, getRandomColor());
            }

            for (int i = 0; i < bufferedImag.getHeight(); ++i) {
                for (int j = 0; j < bufferedImag.getWidth(); ++j) {
                    int label = labels[i][j];
                    if (label != 0) {
                        imageEtichete.setRGB(j, i, labelColors.get(label).getRGB());
                    } else {
                        imageEtichete.setRGB(j, i, Color.WHITE.getRGB());
                    }
                }
            }

            // Afișează imaginea etichetată
            Image imgEtichete = SwingFXUtils.toFXImage(imageEtichete, null);
            ImageView imageViewEtichete = new ImageView(imgEtichete);
            imageViewEtichete.setPreserveRatio(true);

            ScrollPane spEtichete = new ScrollPane();
            spEtichete.setContent(imageViewEtichete);

            Stage secondStage = new Stage();
            Scene sceneEtichete = new Scene(spEtichete, 400, 350);
            secondStage.setTitle("Etichete");
            secondStage.setScene(sceneEtichete);
            secondStage.show();
        });
        menuItem_Exit.setOnAction((ActionEvent event) -> {
            Platform.exit();
            System.exit(0);
        });

        ((StackPane)scene.getRoot()).getChildren().addAll(vbox);

        mainStage.setTitle("Prelucrarea Imaginilor");
        mainStage.setScene(scene);
        mainStage.show();

        mainStage.setMaximized(true);

        menuItem_Histogram.setOnAction((ActionEvent event) -> {
            ScrollPane spH = new ScrollPane();
            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();
            BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
            BufferedImage imageGray = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);
            for (int y = 0; y < bufferedImag.getHeight(); y++) {
                for (int x = 0; x < bufferedImag.getWidth(); x++) {
                    //Retrieving contents of a pixel
                    int pixel = bufferedImag.getRGB(x,y);
                    //Creating a Color object from pixel value
                    Color color = new Color(pixel, true);
                    //Retrieving the R G B values
                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();
                    int gray = (red + green + blue) / 3;
                    Color grayV3 = new Color(gray, gray, gray);
                    imageGray.setRGB(x, y, grayV3.getRGB());
                }
            }
            try {
                int[] histogram = calculateHistogram(bufferedImag);

                // Crează un obiect BarChart

                // Creează o serie de date pentru histogramă
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                for (int i = 0; i < histogram.length; i++) {
                    series.getData().add(new XYChart.Data<>(String.valueOf(i), histogram[i]));
                }

                // Adaugă seria de date la graficul de bare
                barChart.getData().add(series);

                // Adaugă graficul de bare la ScrollPane
                spH.setContent(barChart);
            } catch (IOException e) {
                e.printStackTrace();
            }

            VBox vbHistogram = new VBox(barChart);
            Image imgHistogram = SwingFXUtils.toFXImage(imageGray, null);
            ImageView imageViewHistogram = new ImageView();
            imageViewHistogram.setFitHeight(400);
            imageViewHistogram.setPreserveRatio(true);
            imageViewHistogram.setImage(imgHistogram);
            imageViewHistogram.setSmooth(true);
            imageViewHistogram.setCache(true);

            vbHistogram.getChildren().addAll(imageViewHistogram);
            spH.setContent(vbHistogram);


            Stage HistogramStage = new Stage();

            Scene sceneHistogram = new Scene(new HBox(), 1700, 800);
            ((HBox)sceneHistogram.getRoot()).getChildren().addAll(spH);

            HistogramStage.setTitle(name.getText()); // Utilizarea etichetei pentru titlu
            HistogramStage.setScene(sceneHistogram);
            HistogramStage.show();


        });
        filterLaplacian.setOnAction((ActionEvent event) -> {
            ScrollPane spH = new ScrollPane();
            VBox vbLaplacian = new VBox();
            BufferedImage filterlaplacian = filterLaplacian(bufferedImag);
            Image imgHistogram = SwingFXUtils.toFXImage(filterlaplacian,null);
            ImageView imageViewLaplacian = new ImageView();
            imageViewLaplacian.setFitHeight(400);
            imageViewLaplacian.setPreserveRatio(true);
            imageViewLaplacian.setImage(imgHistogram);
            imageViewLaplacian.setSmooth(true);
            imageViewLaplacian.setCache(true);

            vbLaplacian.getChildren().addAll(vbLaplacian);
            spH.setContent(vbLaplacian);


            Stage HistogramStage = new Stage();

            Scene sceneHistogram = new Scene(new HBox(), 1700, 800);
            ((HBox)sceneHistogram.getRoot()).getChildren().addAll(spH);

            HistogramStage.setTitle(name.getText()); // Utilizarea etichetei pentru titlu
            HistogramStage.setScene(sceneHistogram);
            HistogramStage.show();


        });

        Accentuare.setOnAction((ActionEvent event) -> {
            ScrollPane spH = new ScrollPane();
            VBox vbHistogram = new VBox();
            BufferedImage accentuare = accentuare(bufferedImag);
            Image imgHistogram = SwingFXUtils.toFXImage(accentuare,null);
            ImageView imageViewHistogram = new ImageView();
            imageViewHistogram.setFitHeight(400);
            imageViewHistogram.setPreserveRatio(true);
            imageViewHistogram.setImage(imgHistogram);
            imageViewHistogram.setSmooth(true);
            imageViewHistogram.setCache(true);

            vbHistogram.getChildren().addAll(imageViewHistogram);
            spH.setContent(vbHistogram);


            Stage HistogramStage = new Stage();

            Scene sceneHistogram = new Scene(new HBox(), 1700, 800);
            ((HBox)sceneHistogram.getRoot()).getChildren().addAll(spH);

            HistogramStage.setTitle(name.getText()); // Utilizarea etichetei pentru titlu
            HistogramStage.setScene(sceneHistogram);
            HistogramStage.show();


        });

        Mediere.setOnAction((ActionEvent event) -> {
            ScrollPane spM = new ScrollPane();
            BufferedImage mediaton = mediere(bufferedImag);

            VBox vbMediation = new VBox();
            Image imgMediation = SwingFXUtils.toFXImage(mediaton, null);
            ImageView imageViewMediation = new ImageView();
            imageViewMediation.setFitHeight(400);
            imageViewMediation.setPreserveRatio(true);
            imageViewMediation.setImage(imgMediation);
            imageViewMediation.setSmooth(true);
            imageViewMediation.setCache(true);

            vbMediation.getChildren().addAll(imageViewMediation);
            spM.setContent(vbMediation);


            Stage MediationStage = new Stage();

            Scene sceneMediation = new Scene(new HBox(), 900, 500);
            ((HBox)sceneMediation.getRoot()).getChildren().addAll(spM);

            MediationStage.setTitle(name.getText()); // Utilizarea etichetei pentru titlu
            MediationStage.setScene(sceneMediation);
            MediationStage.show();


        });

        menuItemMin.setOnAction((ActionEvent event) -> {
            ScrollPane spM = new ScrollPane();
            BufferedImage mediaton = minim(bufferedImag);

            VBox vbMediation = new VBox();
            Image imgMediation = SwingFXUtils.toFXImage(mediaton, null);
            ImageView imageViewMediation = new ImageView();
            imageViewMediation.setFitHeight(400);
            imageViewMediation.setPreserveRatio(true);
            imageViewMediation.setImage(imgMediation);
            imageViewMediation.setSmooth(true);
            imageViewMediation.setCache(true);

            vbMediation.getChildren().addAll(imageViewMediation);
            spM.setContent(vbMediation);


            Stage MediationStage = new Stage();

            Scene sceneMediation = new Scene(new HBox(), 900, 500);
            ((HBox)sceneMediation.getRoot()).getChildren().addAll(spM);

            MediationStage.setTitle(name.getText()); // Utilizarea etichetei pentru titlu
            MediationStage.setScene(sceneMediation);
            MediationStage.show();


        });
        menuItemMax.setOnAction((ActionEvent event) -> {
            ScrollPane spM = new ScrollPane();
            BufferedImage mediaton = maxim(bufferedImag);

            VBox vbMediation = new VBox();
            Image imgMediation = SwingFXUtils.toFXImage(mediaton, null);
            ImageView imageViewMediation = new ImageView();
            imageViewMediation.setFitHeight(400);
            imageViewMediation.setPreserveRatio(true);
            imageViewMediation.setImage(imgMediation);
            imageViewMediation.setSmooth(true);
            imageViewMediation.setCache(true);

            vbMediation.getChildren().addAll(imageViewMediation);
            spM.setContent(vbMediation);


            Stage MediationStage = new Stage();

            Scene sceneMediation = new Scene(new HBox(), 900, 500);
            ((HBox)sceneMediation.getRoot()).getChildren().addAll(spM);

            MediationStage.setTitle(name.getText()); // Utilizarea etichetei pentru titlu
            MediationStage.setScene(sceneMediation);
            MediationStage.show();


        });
        menuItemEnhancer.setOnAction((ActionEvent event) -> {
            ScrollPane spE = new ScrollPane();
            BufferedImage enhancer = accentuare(bufferedImag);

            VBox vbEnhancer = new VBox();
            Image imgMediation = SwingFXUtils.toFXImage(enhancer, null);
            ImageView imageViewEnhance = new ImageView();
            imageViewEnhance.setFitHeight(400);
            imageViewEnhance.setPreserveRatio(true);
            imageViewEnhance.setImage(imgMediation);
            imageViewEnhance.setSmooth(true);
            imageViewEnhance.setCache(true);

            vbEnhancer.getChildren().addAll(imageViewEnhance);
            spE.setContent(vbEnhancer);


            Stage enhancerStage = new Stage();

            Scene sceneEnhancer = new Scene(new HBox(), 900, 500);
            ((HBox)sceneEnhancer.getRoot()).getChildren().addAll(spE);

            enhancerStage.setTitle(name.getText()); // Utilizarea etichetei pentru titlu
            enhancerStage.setScene(sceneEnhancer);
            enhancerStage.show();


        });


        FiltruMedian.setOnAction((ActionEvent event) -> {
            ScrollPane spMed = new ScrollPane();
            BufferedImage median = median(bufferedImag);

            VBox vbMedian = new VBox();
            Image imgMedian = SwingFXUtils.toFXImage(median, null);
            ImageView imageViewMedian = new ImageView();
            imageViewMedian.setFitHeight(400);
            imageViewMedian.setPreserveRatio(true);
            imageViewMedian.setImage(imgMedian);
            imageViewMedian.setSmooth(true);
            imageViewMedian.setCache(true);

            vbMedian.getChildren().addAll(imageViewMedian);
            spMed.setContent(vbMedian);


            Stage MedianStage = new Stage();

            Scene sceneMedian= new Scene(new HBox(), 900, 500);
            ((HBox)sceneMedian.getRoot()).getChildren().addAll(spMed);

            MedianStage.setTitle(name.getText()); // Utilizarea etichetei pentru titlu
            MedianStage.setScene(sceneMedian);
            MedianStage.show();


        });

        Dilate_menuItem.setOnAction((ActionEvent event) -> {
            Spinner<Integer> iterationsSpinner = new Spinner<>(1, 10, 1);
            ScrollPane spDilate = new ScrollPane();
            Button buttonFalse = new Button();
            buttonFalse.setText("Dilatare off");
            Button buttonTrue = new Button();
            buttonTrue.setText("Dilatare on");
            VBox vbDilate = new VBox(iterationsSpinner, buttonFalse, buttonTrue);
            Image imgDilate = SwingFXUtils.toFXImage(bufferedImag, null);
            ImageView imageViewDilate = new ImageView();
            imageViewDilate.setFitHeight(400);
            imageViewDilate.setPreserveRatio(true);
            imageViewDilate.setImage(imgDilate);
            imageViewDilate.setSmooth(true);
            imageViewDilate.setCache(true);
            vbDilate.getChildren().addAll(imageViewDilate);
            spDilate.setContent(vbDilate);
            iterationsSpinner.valueProperty().addListener((obs, oldValue, newValue) -> {
                int iterations = newValue;
                BufferedImage median = dilate(bufferedImag, getDilateBackGroundPixel(), iterations);
                imageViewDilate.setImage(SwingFXUtils.toFXImage(median, null));
            });
            buttonFalse.setOnAction((ActionEvent ev) -> {
                setDilateBackGroundPixel(false);
            });
            buttonTrue.setOnAction((ActionEvent ev) -> {
                setDilateBackGroundPixel(true);
            });
            Stage DilateStage = new Stage();
            Scene sceneDilate = new Scene(new HBox(),900, 500);
            ((HBox)sceneDilate.getRoot()).getChildren().addAll(spDilate);
            DilateStage.setTitle(name.getText()); // Utilizarea etichetei pentru titlu
            DilateStage.setScene(sceneDilate);
            DilateStage.show();
        });

        EdgeDetect_menuItem.setOnAction((ActionEvent event) -> {
            ScrollPane spH = new ScrollPane();
            VBox vbEdgeDetect = new VBox();
            BufferedImage filtru_detectare_margini = EdgeDetect(bufferedImag, 2);
            Image imgEdgeDetect = SwingFXUtils.toFXImage(filtru_detectare_margini,null);
            ImageView imageViewEdge_Detect = new ImageView();
            imageViewEdge_Detect.setFitHeight(400);
            imageViewEdge_Detect.setPreserveRatio(true);
            imageViewEdge_Detect.setImage(imgEdgeDetect);
            imageViewEdge_Detect.setSmooth(true);
            imageViewEdge_Detect.setCache(true);

            vbEdgeDetect.getChildren().addAll(imageViewEdge_Detect);
            spH.setContent(vbEdgeDetect);


            Stage HistogramStage = new Stage();

            Scene sceneEdge = new Scene(new HBox(), 1700, 800);
            ((HBox)sceneEdge.getRoot()).getChildren().addAll(spH);

            HistogramStage.setTitle(name.getText()); // Utilizarea etichetei pentru titlu
            HistogramStage.setScene(sceneEdge);
            HistogramStage.show();


        });

        menuItemTrece_Sus.setOnAction((ActionEvent event) -> {
            Spinner<Integer> iterationsSpinner = new Spinner<>(1, 10, 1);
            ScrollPane spDilate = new ScrollPane();
            VBox vbDilate = new VBox(iterationsSpinner);
            Image imgDilate = SwingFXUtils.toFXImage(bufferedImag, null);
            ImageView imageViewDilate = new ImageView();
            imageViewDilate.setFitHeight(400);
            imageViewDilate.setPreserveRatio(true);
            imageViewDilate.setImage(imgDilate);
            imageViewDilate.setSmooth(true);
            imageViewDilate.setCache(true);
            vbDilate.getChildren().addAll(imageViewDilate);
            spDilate.setContent(vbDilate);
            iterationsSpinner.valueProperty().addListener((obs, oldValue, newValue) -> {
                int iterations = newValue;
                BufferedImage median = EdgeDetect(bufferedImag, iterations);
                imageViewDilate.setImage(SwingFXUtils.toFXImage(median, null));
            });

            Stage DilateStage = new Stage();
            Scene sceneDilate = new Scene(new HBox(),900, 500);
            ((HBox)sceneDilate.getRoot()).getChildren().addAll(spDilate);
            DilateStage.setTitle(name.getText()); // Utilizarea etichetei pentru titlu
            DilateStage.setScene(sceneDilate);
            DilateStage.show();

        });



//        // Sau varianta nr 2
//        // Get current screen of the stage
//        ObservableList<Screen> screens = Screen.getScreensForRectangle(new Rectangle2D(mainStage.getX(), mainStage.getY(), mainStage.getWidth(), mainStage.getHeight()));
//
//        // Change stage properties
//        Rectangle2D bounds = screens.get(0).getVisualBounds();
//        mainStage.setX(bounds.getMinX());
//        mainStage.setY(bounds.getMinY());
//        mainStage.setWidth(bounds.getWidth());
//        mainStage.setHeight(bounds.getHeight());

    }
    private boolean isBlack(int rgb) {
        Color c = new Color(rgb);
        return c.getRed() == 0 && c.getGreen() == 0 && c.getBlue() == 0;
    }
    // Funcție auxiliară pentru verificarea dacă un index este valid
    private boolean isValidIndex(int row, int col, int height, int width) {
        return row >= 0 && row < height && col >= 0 && col < width;
    }
    private Color getRandomColor() {
        return new Color((int) (Math.random() * 0x1000000));
    }
    public static int[] calculateHistogram(BufferedImage image) throws IOException {

        // Dimensiunile imaginii

        int width = image.getWidth();

        int height = image.getHeight();

        // Inițializarea histogramă

        int[] histogram = new int[256]; // Histograma pentru tonuri de gri



        // Parcurgerea imaginii și calcularea histogramei

        for (int y = 0; y < height; y++) {

            for (int x = 0; x < width; x++) {

                int pixel = image.getRGB(x, y);

                // Extrage componentele R, G, B și calculează media pentru a obține tonul de gri

                int red = (pixel >> 16) & 0xff;

                int green = (pixel >> 8) & 0xff;

                int blue = pixel & 0xff;

                int gray = (red + green + blue) / 3;



                // Incrementarea corespunzătoare a valorii în histogramă

                histogram[gray]++;

            }

        }

        return histogram;

    }
    public BufferedImage mediere(BufferedImage src){

        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
        int i,j;
        int k,l;
        int w,h;
        double[][] v = new double[5][5];
        //coeficientii mastii de filtrare
        v[0][0]=1.0/9.0; v[0][1]=1.0/9.0; v[0][2]=1.0/9.0;
        v[1][0]=1.0/9.0; v[1][1]=1.0/9.0; v[1][2]=1.0/9.0;
        v[2][0]=1.0/9.0; v[2][1]=1.0/9.0; v[2][2]=1.0/9.0;
        v[3][0]=1.0/9.0; v[3][1]=1.0/9.0; v[3][2]=1.0/9.0; v[3][3] = 1.0/9.0;
        v[4][0]=1.0/9.0; v[4][1]=1.0/9.0; v[4][2]=1.0/9.0; v[4][3] = 1.0/9.0; v[4][4] = 1.0/9.0;

        w=src.getWidth();
        h=src.getHeight();
        for(i=1;i<w-1;i++){
            for(j=1;j<h-1;j++){
                //suma ponderata
                double sumr=0,sumg=0,sumb=0;
                for(k=-1;k<2;k++){
                    for(l=-1;l<2;l++){
                        int pixel = src.getRGB(i + k, j + l);
                        Color c = new Color(pixel, true);
                        sumr += v[k + 1][l + 1] * c.getRed();
                        sumg += v[k + 1][l + 1] * c.getGreen();
                        sumb += v[k + 1][l + 1] * c.getBlue();
                        Color nc = new Color((int) sumr, (int) sumg, (int) sumb);
                        dst.setRGB(i, j, nc.getRGB());
                    }
                }
            }
        }
        return dst;
    }
    BufferedImage median(BufferedImage src) {

        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
        int i,j;
        int w,h;
        int k,aux;
        int m,n;
        int med,med_min,med_max;
        int[] sir=new int[9];
        w=src.getWidth();
        h=src.getHeight();
        for(i=1;i<w-1;i++) {
            for(j=1;j<h-1;j++){
                //formarea unui sir din elementele vecinatatii 3x3
                k=0;
                for(m=-1;m<2;m++) {
                    for(n=-1;n<2;n++){
                        int pixel = src.getRGB(i+m,j+n);
                        Color c = new Color(pixel, true);
                        sir[k]=c.getRed();
                        k++;
                    }
                }
                //ordonarea crescatoare a valorilor pixelilor
                //metoda BUBBLESORT
                k=0;
                while(k==0){
                    k=1;
                    for(m=0;m<8;m++){
                        if(sir[m]>sir[m+1]){
                            aux=sir[m];
                            sir[m]=sir[m+1];
                            sir[m+1]=aux;
                            k=0;
                        }
                    }
                }
                //elementul median
                med=sir[2   ];
                med_min = sir[0];
                med_max = sir[8];
                Color nc = new Color(med,med,med);
                dst.setRGB(i, j, nc.getRGB());
            }
        }
        return dst;
    }
    BufferedImage accentuare(BufferedImage src) {

        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
        int i,j;
        int k,l;
        int w,h;
        double sumr,sumg,sumb;
        double[][] v=new double[3][3];
        //coeficientii mastii
        v[0][0]=0; v[0][1]=-1./4; v[0][2]=0;
        v[1][0]=-1./4; v[1][1]=1; v[1][2]=-1./4;
        v[2][0]=0; v[2][1]=-1./4; v[2][2]=0;
        w=src.getWidth();
        h=src.getHeight();
        for(i=1;i<w-1;i++){
            for(j=1;j<h-1;j++){
                sumr=0;
                sumg=0;
                sumb=0;
                for(k=-1;k<2;k++) {
                    for(l=-1;l<2;l++) {
                        int pixel = src.getRGB(i + k, j + l);
                        Color c = new Color(pixel, true);
                        sumr+=1.*v[k+1][l+1]*c.getRed();
                        sumg+=1.*v[k+1][l+1]*c.getGreen();
                        sumb+=1.*v[k+1][l+1]*c.getBlue();
                        int nivr=c.getRed();
                        //nivr=(int)(nivr+0.6*sumr);
                        int nivg=c.getGreen();
                        //nivg=(int)(nivg+0.6*sumg);
                        int nivb=c.getBlue();
                        //nivb=(int)(nivb+0.6*sumb);
                        Color nc = new Color((int) this.adjustColor(nivr, (int) (0.6*sumr)),
                                (int) this.adjustColor(nivg, (int) (0.6*sumg)),
                                (int) this.adjustColor(nivb, (int) (0.6*sumb)));
                        dst.setRGB(i, j, nc.getRGB());
                    }
                }
            }
        }
        return dst;
    }
    private BufferedImage getGrayscaleImage(BufferedImage src) {
        int width = src.getWidth();
        int height = src.getHeight();

        BufferedImage gray = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        // Convert color image to grayscale
        ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        op.filter(src, gray);

        return gray;
    }

    public BufferedImage filterLaplacian(BufferedImage src) {
        BufferedImage grayScale = getGrayscaleImage(src);
        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
        int width = src.getWidth();
        int height = src.getHeight();
        double[][] v = new double[3][3];
        //coeficientii mastii
        v[0][0] = -1;
        v[0][1] = -1;
        v[0][2] = -1;
        v[1][0] = -1;
        v[1][1] = 8;
        v[1][2] = -1;
        v[2][0] = -1;
        v[2][1] = -1;
        v[2][2] = -1;
        //33 Laplacian filter (-1,-1,-1), (-1,8,-1), (-1,-1,-1)
        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                int sum = 0;
                for (int m = -1; m < 2; m++) {
                    for (int n = -1; n < 2; n++) {
                        int pixel = src.getRGB(x + m, y + n);
                        sum += v[m+1][n+1] * (pixel & 0xff);
                    }
                }
                int a = ((grayScale.getRGB(x, y) >> 24) & 0xff);
                dst.setRGB(x, y, ((a << 24) | (sum << 16) | (sum << 8) | (sum)));
            }
        }
        return dst;
    }



    private BufferedImage dilate(BufferedImage src, boolean dilateBackgroundPixel, float iteratii) {
        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        int width = src.getWidth();
        int height = src.getHeight();
        // Aplicăm dilatarea de iteratii ori
        for (int i = 0; i < iteratii; i++) {
            int output[] = new int[width * height];
            int targetValue = (dilateBackgroundPixel == true) ? 0 : 255;
            int reverseValue = (targetValue == 255) ? 0 : 255;
            // Algoritmul de dilatare
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if ((new Color(src.getRGB(x, y))).getRed() == targetValue) {
                        boolean flag = false;

                        for (int ty = y - 1; ty <= y + 1 && !flag; ty++) {
                            for (int tx = x - 1; tx <= x + 1 && !flag; tx++) {
                                if (ty >= 0 && ty < height && tx >= 0 && tx < width) {
                                    if ((new Color(src.getRGB(tx, ty))).getRed() != targetValue) {
                                        flag = true;
                                        output[x + y * width] = reverseValue;
                                    }
                                }
                            }
                        }
                        if (!flag) {
                            output[x + y * width] = targetValue;
                        }
                    } else {
                        output[x + y * width] = reverseValue;
                    }
                }
            }
            // Salvăm rezultatul pentru următoarea iterație sau pentru imaginea finală
            for (int j = 0; j < width * height; j++) {
                dst.getRaster().setSample(j % width, j / width, 0, output[j]);
            }
            // Setăm imaginea dilatată ca sursă pentru următoarea iterație
            src = dst;
        }
        return dst;
    }
    public BufferedImage EdgeDetect(BufferedImage src, int filter_type) {

        String HORIZONTAL_FILTER = "Horizontal Filter";

        String VERTICAL_FILTER = "Vertical Filter";

        String SOBEL_FILTER_VERTICAL = "Sobel Vertical Filter";

        String SOBEL_FILTER_HORIZONTAL = "Sobel Horizontal Filter";

        String SCHARR_FILTER_VETICAL = "Scharr Vertical Filter";

        String SCHARR_FILTER_HORIZONTAL = "Scharr Horizontal Filter";

        double[][] FILTER_VERTICAL = {{1, 0, -1}, {1, 0, -1}, {1, 0, -1}};

        double[][] FILTER_HORIZONTAL = {{1, 1, 1}, {0, 0, 0}, {-1, -1, -1}};

        double[][] FILTER_SOBEL_V = {{1, 0, -1}, {2, 0, -2}, {1, 0, -1}};

        double[][] FILTER_SOBEL_H = {{1, 2, 1}, {0, 0, 0}, {-1, -2, -1}};

        double[][] FILTER_SCHARR_V = {{3, 0, -3}, {10, 0, -10}, {3, 0, -3}};

        double[][] FILTER_SCHARR_H = {{3, 10, 3}, {0, 0, 0}, {-3, -10, -3}};



        HashMap<String, double[][]> filterMap;



        int width = src.getWidth();

        int height = src.getHeight();

        double[][][] image = new double[3][height][width];

        for (int i = 0; i < height; i++) {

            for (int j = 0; j < width; j++) {

                Color color = new Color(src.getRGB(j, i));

                image[0][i][j] = color.getRed();

                image[1][i][j] = color.getGreen();

                image[2][i][j] = color.getBlue();

            }

        }



        double[][] filter = null;

        switch (filter_type) {

            case 1:

                filter = FILTER_VERTICAL;

            case 2:

                filter = FILTER_HORIZONTAL;

            case 3:

                filter = FILTER_SOBEL_V;

            case 4:

                filter = FILTER_SOBEL_H;

            case 5:

                filter = FILTER_SCHARR_V;

            case 6:

                filter = FILTER_SCHARR_H;

        }



        double[][] redConv = convolutionType2(image[0], height, width, filter, 3, 3, 1);

        double[][] greenConv = convolutionType2(image[1], height, width, filter, 3, 3, 1);

        double[][] blueConv = convolutionType2(image[2], height, width, filter, 3, 3, 1);

        double[][] convolvedPixels = new double[redConv.length][redConv[0].length];

        for (int i = 0; i < redConv.length; i++) {

            for (int j = 0; j < redConv[i].length; j++) {

                convolvedPixels[i][j] = redConv[i][j] + greenConv[i][j] + blueConv[i][j];

            }

        }



        BufferedImage writeBackImage = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < convolvedPixels.length; i++) {

            for (int j = 0; j < convolvedPixels[i].length; j++) {

                Color color = new Color(fixOutOfRangeRGBValues(convolvedPixels[i][j]), fixOutOfRangeRGBValues(convolvedPixels[i][j]), fixOutOfRangeRGBValues(convolvedPixels[i][j]));

                writeBackImage.setRGB(j, i, color.getRGB());

            }

        }

        return writeBackImage;



    }

    private boolean dilateBackgroundPixel;
    public boolean getDilateBackGroundPixel(){
        return dilateBackgroundPixel;
    }
    public void setDilateBackGroundPixel(boolean dilateBackgroundPixel){
        this.dilateBackgroundPixel = dilateBackgroundPixel;
    }
    public double[][] convolutionType2(double[][] input, int width, int height, double[][] kernel, int kernelWidth, int kernelHeight, int iterations) {

        double[][] newInput = (double[][]) input.clone();

        double[][] output = (double[][]) input.clone();



        for (int i = 0; i < iterations; ++i) {

            output = convolution2DPadded(newInput, width, height, kernel, kernelWidth, kernelHeight);

            newInput = (double[][]) output.clone();

        }

        return output;

    }



    public double[][] convolution2DPadded(double[][] input, int width, int height, double[][] kernel, int kernelWidth, int kernelHeight) {

        int smallWidth = width - kernelWidth + 1;

        int smallHeight = height - kernelHeight + 1;

        int top = kernelHeight / 2;

        int left = kernelWidth / 2;

        double small[][] = convolution2D(input, width, height, kernel, kernelWidth, kernelHeight);

        double large[][] = new double[width][height];

        for (int j = 0; j < height; ++j) {

            for (int i = 0; i < width; ++i) {

                large[i][j] = 0;

            }

        }

        for (int j = 0; j < smallHeight; ++j) {

            for (int i = 0; i < smallWidth; ++i) {

                large[i + left][j + top] = small[i][j];

            }

        }

        return large;

    }



    public double[][] convolution2D(double[][] input, int width, int height, double[][] kernel, int kernelWidth, int kernelHeight) {

        int smallWidth = width - kernelWidth + 1;

        int smallHeight = height - kernelHeight + 1;

        double[][] output = new double[smallWidth][smallHeight];

        for (int i = 0; i < smallWidth; ++i) {

            for (int j = 0; j < smallHeight; ++j) {

                output[i][j] = 0;

            }

        }

        for (int i = 0; i < smallWidth; ++i) {

            for (int j = 0; j < smallHeight; ++j) {

                output[i][j] = singlePixelConvolution(input, i, j, kernel, kernelWidth, kernelHeight);

            }

        }

        return output;

    }



    public double singlePixelConvolution(double[][] input, int x, int y, double[][] k, int kernelWidth, int kernelHeight) {

        double output = 0;

        for (int i = 0; i < kernelWidth; ++i) {

            for (int j = 0; j < kernelHeight; ++j) {

                output = output + (input[x + i][y + j] * k[i][j]);

            }

        }

        return output;

    }



    private int fixOutOfRangeRGBValues(double value) {

        if (value < 0.0) {

            value = -value;

        }

        if (value > 255) {

            return 255;

        } else {

            return (int) value;

        }

    }
    public BufferedImage minim(BufferedImage src) {
        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
        int i,j;
        int w,h;
        int k,aux;
        int m,n;
        int med;
        int[] sir=new int[9];
        w=src.getWidth();
        h=src.getHeight();
        for(i=1;i<w-1;i++) {
            for(j=1;j<h-1;j++){
                //formarea unui sir din elementele vecinatatii 3x3
                k=0;
                for(m=-1;m<2;m++) {
                    for(n=-1;n<2;n++){
                        int pixel = src.getRGB(i+m,j+n);
                        Color c = new Color(pixel, true);
                        sir[k]=c.getRed();
                        k++;
                    }
                }
                //ordonarea crescatoare a valorilor pixelilor
                //metoda BUBBLESORT
                k=0;
                while(k==0){
                    k=1;
                    for(m=0;m<8;m++){
                        if(sir[m]>sir[m+1]){
                            aux=sir[m];
                            sir[m]=sir[m+1];
                            sir[m+1]=aux;
                            k=0;
                        }
                    }
                }
                //elementul median
                med=sir[0];
                Color nc = new Color(med,med,med);
                dst.setRGB(i, j, nc.getRGB());
            }
        }
        return dst;
    }

    public BufferedImage maxim(BufferedImage src) {
        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
        int i,j;
        int w,h;
        int k,aux;
        int m,n;
        int med;
        int[] sir=new int[9];
        w=src.getWidth();
        h=src.getHeight();
        for(i=1;i<w-1;i++) {
            for(j=1;j<h-1;j++){
                //formarea unui sir din elementele vecinatatii 3x3
                k=0;
                for(m=-1;m<2;m++) {
                    for(n=-1;n<2;n++){
                        int pixel = src.getRGB(i+m,j+n);
                        Color c = new Color(pixel, true);
                        sir[k]=c.getRed();
                        k++;
                    }
                }
                k=0;
                while(k==0){
                    k=1;
                    for(m=0;m<8;m++){
                        if(sir[m]>sir[m+1]){
                            aux=sir[m];
                            sir[m]=sir[m+1];
                            sir[m+1]=aux;
                            k=0;
                        }
                    }
                }
                med=sir[8];
                Color nc = new Color(med,med,med);
                dst.setRGB(i, j, nc.getRGB());
            }
        }
        return dst;
    }


    private int adjustColor(int rgb, int dif) {
        int result;
        if (dif>0) {
            if (rgb+dif>255) {
                result = 255; //rgb + dif - 255;
            } else {
                result = rgb + dif;
            }
        } else {
            if (rgb+dif>0) {
                result = rgb + dif;
            } else {
                result = 0; //255 + rgb + dif;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        launch(args);
    }
}