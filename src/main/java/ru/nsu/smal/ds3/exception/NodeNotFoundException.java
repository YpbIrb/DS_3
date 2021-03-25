package ru.nsu.smal.ds3.exception;

import java.math.BigInteger;

public class NodeNotFoundException extends RuntimeException {
    private static final String EXCEPTION_MESSAGE = "Node with id %d wasn't found";

    public NodeNotFoundException(BigInteger nodeId){
        super(String.format(EXCEPTION_MESSAGE, nodeId));
    }

}
