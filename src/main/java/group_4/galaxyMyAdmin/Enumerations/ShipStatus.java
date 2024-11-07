package group_4.galaxyMyAdmin.Enumerations;

public enum ShipStatus {
    _OPE("Operational"),
    _DAMAGED("Maintenance"),
    _DESTROYED("Destroyed");

    String value;

    private ShipStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }    
}
