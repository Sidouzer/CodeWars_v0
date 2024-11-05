package group_4.galaxyMyAdmin.Enumerations;

public enum PiloteStatus {
    _OPE("Operational"),
    _INJ("Injured"),
    _DEC("Deceased");

    String value;

    private PiloteStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
