package io.github.infinitepower563.simplenet.example;

import io.github.infinitepower563.simplenet.exception.HttpException;
import io.github.infinitepower563.simplenet.net.HttpRequest;
import io.github.infinitepower563.simplenet.net.RequestType;

/*
An example implementation of an HTTP request with HttpRequest.
A server is set up in localhost, the .json is in the repo.
 */
public class RequestExample {
    public static void main(String[] args) {
        /*
        Send an HTTP
        - GET request
        - To http://localhost:8000/test
        - With no input parameters
         */
        HttpRequest req = new HttpRequest(RequestType.GET,"http://localhost:8000/test",null);
        try {
            //We have no headers, set requestHeaders to 0, and we can pass literally anything to hKey and hVal
            System.out.println(req.performRequest(0,null,null));
        } catch (HttpException e) {
            e.printStackTrace();
        }
    }
}
