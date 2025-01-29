package org.example;

import java.io.IOException;

public interface IPostfixCalculator {

    void readFromFile(String path) throws IOException;
    int evaluateExpression() throws IllegalArgumentException;

}
