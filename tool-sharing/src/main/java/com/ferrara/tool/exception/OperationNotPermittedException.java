package com.ferrara.tool.exception;

public class OperationNotPermittedException extends RuntimeException{
    /*
        This will be handled in the Global Exception Handler
    */
    public OperationNotPermittedException(String msg){
        super(msg);
    }
}
