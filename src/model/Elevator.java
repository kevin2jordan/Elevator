package model;

import model.enums.Direction;
import model.enums.RequestType;

import java.util.PriorityQueue;

public class Elevator {
    public int currentFloor;
    Direction direction;
    PriorityQueue<Request> upQueue;
    PriorityQueue<Request> downQueue;

    public Elevator(int currentFloor) {
        this.currentFloor = currentFloor;
        this.direction = Direction.IDLE;
        this.upQueue = new PriorityQueue<>((x,y) -> x.desiredFloor - y.desiredFloor);
        this.downQueue = new PriorityQueue<>((x,y) -> y.desiredFloor - x.desiredFloor);
    }

    public void sendUpRequest(Request request) {
        if(request.requestType == RequestType.EXTERNAL) {
            upQueue.offer(new Request(
                    request.currentFloor,
                    request.currentFloor,
                    Direction.UP,
                    RequestType.EXTERNAL
            ));
            System.out.println("Adding upward request to go to floor " + request.currentFloor);
        }
        upQueue.offer(request);
        System.out.println("Adding upward request to go to floor " + request.desiredFloor);
    }

    public void sendDownRequest(Request request) {
        if(request.requestType == RequestType.EXTERNAL) {
            downQueue.offer(new Request(
                    request.currentFloor,
                    request.currentFloor,
                    Direction.DOWN,
                    RequestType.EXTERNAL
            ));
            System.out.println("Adding downward request to go to floor " + request.currentFloor);
        }
        downQueue.offer(request);
        System.out.println("Adding downward request to go to floor " + request.desiredFloor);
    }

    public void processRequest() {
        if(this.direction == Direction.UP || this.direction == Direction.IDLE) {
            processUpRequest();
            processDownRequest();
        } else {
            processDownRequest();
            processUpRequest();
        }
    }

    private void processUpRequest() {
        while (!upQueue.isEmpty()) {
            Request request = upQueue.poll();
            this.currentFloor = request.desiredFloor;
            System.out.println("Processing upward requests. Elevator stopped at floor " + this.currentFloor);
        }
        this.direction = !downQueue.isEmpty() ? Direction.DOWN : Direction.IDLE;
    }

    private void processDownRequest() {
        while (!downQueue.isEmpty()) {
            Request request = downQueue.poll();
            this.currentFloor = request.desiredFloor;
            System.out.println("Processing downward requests. Elevator stopped at floor " + this.currentFloor);
        }
        this.direction = !upQueue.isEmpty() ? Direction.UP : Direction.IDLE;
    }

    public void startElevator() {
        while (!upQueue.isEmpty() || !downQueue.isEmpty()) {
            processRequest();
        }
        System.out.println("Finished all the request");
        this.direction = Direction.IDLE;
    }
}
