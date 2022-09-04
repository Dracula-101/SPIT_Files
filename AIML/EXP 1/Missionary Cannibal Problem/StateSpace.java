import java.util.ArrayList;
import java.util.List;

class StateSpace {
    private List<State> nextStates = new ArrayList<>();

    public StateSpace(State initialState) {
        findSpace(initialState);
    }

    private void findSpace(State state) {
        if (state.boat == 0) {
            makeTrips(
                    State.oneMissionaryRight(state),
                    State.oneCannibalRight(state),
                    State.oneMissionaryOneCannibalRight(state),
                    State.twoMissionariesRight(state),
                    State.twoCannibalsRight(state));
        } else {
            makeTrips(
                    State.oneMissionaryLeft(state),
                    State.oneCannibalLeft(state),
                    State.oneMissionaryOneCannibalLeft(state),
                    State.twoMissionariesLeft(state),
                    State.twoCannibalsLeft(state));
        }
    }

    public void makeTrips(State... states) {
        for (State state : states) {
            if (state.isValid()) {
                nextStates.add(state);
            }
        }
    }

    public List<State> getNextStates() {
        return nextStates;
    }
}