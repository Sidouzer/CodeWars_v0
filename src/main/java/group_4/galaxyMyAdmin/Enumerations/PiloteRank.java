package group_4.galaxyMyAdmin.Enumerations;

public enum PiloteRank {
    _APPRENTICE("Apprentice"),
    _FLIGHT_OFFICER("Flight Officer"),
    _LIEUTENANT("Lieutenant"),
    _CAPTAIN("Captain"),
    _MAJOR("Major"),
    _COMMANDANT("Commandant"),
    _COLONEL("Colonel"),
    _GENERAL("General");

    String value;

    private PiloteRank(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
