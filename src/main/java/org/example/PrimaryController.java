package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import org.example.engine.GraphicTilesEngine;

public class PrimaryController implements Initializable {



    @FXML
    private Spinner<Integer> xSizeSpinner;

    @FXML
    private Spinner<Integer> ySizeSpinner;

    @FXML
    private Spinner<Integer> cellSizeSpinner;

    @FXML
    private Spinner<Integer> gridWidthSpinner;
    @FXML
    private Spinner<Double> fpsSpinner;



    @FXML
    private void switchToSecondary() throws IOException {
        setGraphicEnginesVariables();
        App.setRoot("secondary");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setSpinnerFactories();
    }



    //=============================================================================================
    // Private Methods
    //=============================================================================================
    private void setSpinnerFactories() {
        xSizeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(4, 128, 20));
        ySizeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(4, 128, 20));
        cellSizeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 200, 40));
        gridWidthSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 2));
        fpsSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1,50,5,0.5));
    }

    private void setGraphicEnginesVariables() {
        GraphicTilesEngine.xBoardDimension = xSizeSpinner.getValue();
        GraphicTilesEngine.yBoardDimension = ySizeSpinner.getValue();
        GraphicTilesEngine.cellSize = cellSizeSpinner.getValue();
        GraphicTilesEngine.gridWidth = gridWidthSpinner.getValue();
        GraphicTilesEngine.fps = fpsSpinner.getValue();
    }
}
