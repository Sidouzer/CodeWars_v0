package group_4.galaxyMyAdmin.Enumerations;

public enum Race {
    _CHALACT("Chalactéens"),
    _CHISS("Chiss"),
    _HUMAN("Humains"),
    _ITHORIAN("Ithoriens"),
    _MIRIALAN("Mirialans"),
    _KELDOR("Kel'Dor"),
    _KIFFAR("Kiffars"),
    _MIRALUKA("Miralukas"),
    _NAGAIS("Nagais"),
    _NEIMOIDIEN("Neimoidiens"),
    _NIKTO("Niktos"),
    _NOGHRI("Noghris"),
    _ONGREE("Ongrees"),
    _PAUANS("Pau'ans"),
    _QUERMIEN("Quermiens"),
    _RAKATA("Rakata"),
    _RODIEN("Rodiens"),
    _THISSPAS("Thisspasiens"),
    _TOGRUTA("Togrutas"),
    _WOOKIE("Wookies"),
    _WRONIAN("Wronians"),
    _ZABRAK("Zabraks");

    private String value;

    private Race(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    
}
