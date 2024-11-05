package group_4.galaxyMyAdmin.Enumerations;

public enum MissionType {
    _BATTLE("Battle"),
    _TRAINING("Training");

    String value;

    private MissionType(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    
}
