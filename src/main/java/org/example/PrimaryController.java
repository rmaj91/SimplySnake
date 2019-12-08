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
    private void switchToSecondary() throws IOException {
        setGraphicEnginesVAriables();
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
        xSizeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(4, 64, 6));
        ySizeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(4, 64, 6));
        cellSizeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 200, 40));
        gridWidthSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 2));
    }

    private void setGraphicEnginesVAriables() {
        GraphicTilesEngine.xBoardDimension = xSizeSpinner.getValue();
        GraphicTilesEngine.yBoardDimension = ySizeSpinner.getValue();
        GraphicTilesEngine.cellSize = cellSizeSpinner.getValue();
        GraphicTilesEngine.gridWidth = gridWidthSpinner.getValue();
    }
}
