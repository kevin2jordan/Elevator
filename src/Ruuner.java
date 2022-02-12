import model.Elevator;
import model.Request;
import model.enums.Direction;
import model.enums.RequestType;

public class Ruuner {
    public static void main(String[] args) {
        Elevator elevator = new Elevator(0);

        Request upRequest1 = new Request(elevator.currentFloor, 5, Direction.UP, RequestType.INTERNAL);
        Request upRequest2 = new Request(elevator.currentFloor, 3, Direction.UP, RequestType.INTERNAL);

        Request downRequest1 = new Request(elevator.currentFloor, 1, Direction.DOWN, RequestType.INTERNAL);
        Request downRequest2 = new Request(elevator.currentFloor, 2, Direction.DOWN, RequestType.INTERNAL);
        Request downRequest3 = new Request(4, 0, Direction.DOWN, RequestType.EXTERNAL);

        // Two people inside of the elevator pressed button to go up to floor 5 and 3.
        elevator.sendUpRequest(upRequest1);
        elevator.sendUpRequest(upRequest2);

        // One person outside of the elevator at floor 4 pressed button to go down to floor 0
        elevator.sendDownRequest(downRequest3);

        // Two person inside of the elevator pressed button to go down to floor 1 and 2.
        elevator.sendDownRequest(downRequest1);
        elevator.sendDownRequest(downRequest2);

        elevator.startElevator();
    }
}
