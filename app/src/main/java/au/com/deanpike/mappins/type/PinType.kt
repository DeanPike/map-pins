package au.com.deanpike.mappins.type

enum class PinType(val description: String) {
    UNSOLD_PIN("Unsold"),
    SOLD_PIN("Sold"),
    PRIMARY_SCHOOL_PIN("Primary school"),
    SECONDARY_SCHOOL_PIN("Secondary school"),
    UNKNOWN_SCHOOL_PIN("Unknown school")
}