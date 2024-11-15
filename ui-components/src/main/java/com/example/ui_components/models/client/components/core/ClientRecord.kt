package com.example.ui_components.models.client.components.core

/*
* This is used a util class, it's function being:
* To create a pattern for reading and updating a 'ClientRecord'- (not a real object) comprised  of:
* - 'ClientInfo' (a real object)
* - 'EmergencyContactInfo' (a real object)
*/
enum class ClientInfoAndEmergencyContactFields {
    PHOTO_URL,
    FIRSTNAME,
    LASTNAME,
    SEX,
    BIRTH_DATE,
    BIRTH_PLACE,
    HEIGHT,
    WEIGHT,
    PRESENT_ADDRESS,
    OCCUPATION,
    PHONE_NUMBER,
    EMAIL_ADDRESS,
    EMERGENCY_CONTACT_NAME,
    EMERGENCY_CONTACT_PHONE_NUMBER,
    EMERGENCY_CONTACT_ADDRESS
}