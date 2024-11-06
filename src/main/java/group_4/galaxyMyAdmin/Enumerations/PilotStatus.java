package group_4.galaxyMyAdmin.Enumerations;

public enum PilotStatus {
    _OPE("Operational"),
    _INJ("Injured"),
    _DEC("Deceased");

    String value;

    private PilotStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
