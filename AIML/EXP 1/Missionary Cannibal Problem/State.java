
public class State {
    int missionaryLeft, cannibalLeft, missionaryRight, cannibalRight, boat = 0;
    String path = "";

    public State(int ml, int cl, int mr, int cr, int b, String p) {
        this.missionaryLeft = ml;
        this.cannibalLeft = cl;
        this.missionaryRight = mr;
        this.cannibalRight = cr;
        this.boat = b;
        this.path = p;
    }

    public State(State state) {
        this.missionaryLeft = state.missionaryLeft;
        this.cannibalLeft = state.cannibalLeft;
        this.missionaryRight = state.missionaryRight;
        this.cannibalRight = state.cannibalRight;
        this.boat = state.boat;
    }

    public boolean isGoal() {
        return missionaryLeft == 0 && cannibalLeft == 0 && missionaryRight == 3 && cannibalRight == 3;
    }

    public boolean isValid() {
        int sum = missionaryLeft + missionaryRight + cannibalLeft + cannibalRight;
        int sumLeft = missionaryLeft + cannibalLeft;
        int sumRight = missionaryRight + cannibalRight;

        return sum <= 6
                && missionaryLeft >= 0 && missionaryLeft <= 3
                && missionaryRight >= 0 && missionaryRight <= 3
                && (missionaryLeft == 0 || missionaryLeft >= cannibalLeft)
                && (missionaryRight == 0 || missionaryRight >= cannibalRight)
                && ((boat == 0 && sumLeft > 0)
                        && (boat == 1 && sumRight > 0));
    }

    // getters
    public int getMissionaryLeft() {
        return missionaryLeft;
    }

    public int getCannibalLeft() {
        return cannibalLeft;
    }

    public int getMissionaryRight() {
        return missionaryRight;
    }

    public int getCannibalRight() {
        return cannibalRight;
    }

    public int getBoat() {
        return boat;
    }

    public static State oneMissionaryLeft(State state) {
        return new State(
                state.missionaryLeft + 1,
                state.cannibalLeft,
                state.missionaryRight - 1,
                state.cannibalRight,
                0, pathGenerator(state));
    }

    public static State oneCannibalLeft(State state) {
        return new State(
                state.missionaryLeft,
                state.cannibalLeft + 1,
                state.missionaryRight,
                state.cannibalRight - 1,
                0, pathGenerator(state));
    }

    public static State oneMissionaryOneCannibalLeft(State state) {
        return new State(
                state.missionaryLeft + 1,
                state.cannibalLeft + 1,
                state.missionaryRight - 1,
                state.cannibalRight - 1,
                0, pathGenerator(state));
    }

    public static State twoMissionariesLeft(State state) {
        return new State(
                state.missionaryLeft + 2,
                state.cannibalLeft,
                state.missionaryRight - 2,
                state.cannibalRight,
                0, pathGenerator(state));
    }

    public static State twoCannibalsLeft(State state) {
        return new State(
                state.missionaryLeft,
                state.cannibalLeft + 2,
                state.missionaryRight,
                state.cannibalRight - 2,
                0, pathGenerator(state));
    }

    public static State oneMissionaryRight(State state) {
        return new State(
                state.missionaryLeft - 1,
                state.cannibalLeft,
                state.missionaryRight + 1,
                state.cannibalRight,
                1, pathGenerator(state));
    }

    public static State oneCannibalRight(State state) {
        return new State(
                state.missionaryLeft,
                state.cannibalLeft - 1,
                state.missionaryRight,
                state.cannibalRight + 1,
                1, pathGenerator(state));
    }

    public static State oneMissionaryOneCannibalRight(State state) {
        return new State(
                state.missionaryLeft - 1,
                state.cannibalLeft - 1,
                state.missionaryRight + 1,
                state.cannibalRight + 1,
                1, pathGenerator(state));
    }

    public static State twoMissionariesRight(State state) {
        return new State(
                state.missionaryLeft - 2,
                state.cannibalLeft,
                state.missionaryRight + 2,
                state.cannibalRight,
                1, pathGenerator(state));
    }

    public static State twoCannibalsRight(State state) {
        return new State(
                state.missionaryLeft,
                state.cannibalLeft - 2,
                state.missionaryRight,
                state.cannibalRight + 2,
                1, pathGenerator(state));
    }

    public static String pathGenerator(State state) {
        return state.path + "[ " + state.missionaryLeft + " " + state.cannibalLeft + " | "
                + (state.boat == 0 ? "->" : "<-") + " | "
                + state.missionaryRight + " "
                + state.cannibalRight + " ]\n";
    }

}
