public class State {
    private String name;

    public State() {
    }

    @Override
    public String toString() {
        return name;
    }

    public State(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
