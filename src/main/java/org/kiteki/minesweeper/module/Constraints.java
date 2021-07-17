package org.kiteki.minesweeper.module;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class Config {
    public int gridWidth;
    public int gridHeight;
    public int fieldWidth;
    public int fieldHeight;
    public int totalMines;
}

public class Constraints {
    YamlReader yamlReader;
    Config config;

    public Constraints() {
        // instantiate yaml reader and writer
        // read config file.
        initialize();
    }

    void initialize() {
        try {
            updateData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            loadDefaultConfig();
            writeData();
            initialize();
        } catch (YamlException e) {
            e.printStackTrace();
        }
    }

    void loadDefaultConfig() {
        config = new Config();
        config.gridWidth = 20;
        config.gridHeight = 20;
        config.fieldWidth = 20;
        config.fieldHeight = 20;
        config.totalMines = 60;
    }

    public int getGridHeight() {
        return config.gridHeight;
    }

    public int getGridWidth() {
        return config.gridWidth;
    }

    public int getFieldHeight() {
        return config.fieldHeight;
    }

    public int getFieldWidth() {
        return config.fieldWidth;
    }

    public int getFieldPaneWidth() {
        return getFieldWidth() * getGridWidth();
    }

    public int getFieldPaneHeight() {
        return getFieldHeight() * getGridHeight();
    }

    public int getTotalMines() {
        return config.totalMines;
    }

    public int getMaxIndex() {
        return getFieldWidth() * getFieldHeight() - 1;
    }

    public void setGridHeight(int gridHeight) {
        config.gridHeight = gridHeight;
    }

    public void setGridWidth(int gridWidth) {
        config.gridWidth = gridWidth;
    }

    public void setFieldHeight(int fieldHeight) {
        config.fieldHeight = fieldHeight;
    }

    public void setFieldWidth(int fieldWidth) {
        config.fieldWidth = fieldWidth;
    }

    public void setTotalMines(int totalMines) {
        config.totalMines = totalMines;
    }

    public void updateData() throws FileNotFoundException, YamlException {
        yamlReader = new YamlReader(new FileReader("./config.yml"));
        config = yamlReader.read(Config.class);
    }

    public void writeData() {
        try {
            YamlWriter yamlWriter = new YamlWriter(new FileWriter("./config.yml"));
            yamlWriter.write(config);
            yamlWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
