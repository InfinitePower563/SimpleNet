package io.github.infinitepower563.simplenet.example;

import io.github.infinitepower563.simplenet.exception.HttpException;
import io.github.infinitepower563.simplenet.net.HttpRequest;
import io.github.infinitepower563.simplenet.net.RequestType;
import org.json.JSONObject;

/*
An example implementation of an HTTP request returning a JSON object.
A server is set up in localhost, the .json is in the repo.
 */
public class JSONRequestExample {
    public static void main(String[] args) {
        try {
            HttpRequest req = new HttpRequest(RequestType.GET,"http://localhost:8000/test",null);
            JSONObject result = req.performJSONRequest(0,null,null);
            System.out.println(result);
            System.out.println(result.getString("result"));
        } catch (HttpException e) {
            e.printStackTrace();
        }
    }
}
