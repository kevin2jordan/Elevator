package model;

import model.enums.Direction;
import model.enums.RequestType;

public class Request {
    int currentFloor;
    int desiredFloor;
    Direction direction;
    RequestType requestType;

    public Request(int currentFloor, int desiredFloor, Direction direction, RequestType requestType) {
        this.currentFloor = currentFloor;
        this.desiredFloor = desiredFloor;
        this.direction = direction;
        this.requestType = requestType;
    }
}
