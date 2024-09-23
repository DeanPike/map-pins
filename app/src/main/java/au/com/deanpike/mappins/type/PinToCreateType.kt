package au.com.deanpike.mappins.type

enum class PinToCreateType(val description: String) {
    UNSOLD_PIN("Unsold"),
    UNSOLD_PIN_WITH_COUNT("Unsold with count"),
    UNSOLD_VIEWED_PIN("Unsold viewed"),
    UNSOLD_SHORTLIST_PIN("Unsold shortlist"),
    SOLD_PIN("Sold"),
    SOLD_SHORTLIST_PIN("Sold shortlist"),
    PRIMARY_SCHOOL_PIN("Primary school"),
    SECONDARY_SCHOOL_PIN("Secondary school"),
    UNKNOWN_SCHOOL_PIN("Unknown school")
}