package group_4.galaxyMyAdmin.Enumerations;

public enum MissionStatus {
    _ONGOING("On-going"),
    _SUCCESS("Succeded"),
    _FAIL("Failure");

    String value;

    private MissionStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    
}
