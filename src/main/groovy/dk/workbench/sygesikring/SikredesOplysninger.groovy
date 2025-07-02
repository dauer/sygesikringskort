package dk.workbench.sygesikring

import groovy.transform.CompileStatic

/**
 * Denne record indeholder alle den sikredes oplysninger
 */
@CompileStatic
record SikredesOplysninger(
    // --- Track 1
    String firstname,
    String lastname,
    String address,
    // https://teknik.bbr.dk/kodelister/0/1/0/Kommunekode
    String municipal,
    String postalcode,
    // --- Track 2
    String cardtype,
    // https://da.wikipedia.org/wiki/ISO_3166-1
    String nationality,
    String application,
    String issuer,
    String cpr,
    String group,
    // https://www.medcom.dk/opslag/koder-tabeller-ydere/yderelokationsnumre/laegepraksis-i-danmark
    String doctor,
    // https://da.wikipedia.org/wiki/ISO_3166-2:DK
    String region,
    String municipal2,
    String date) {

}
