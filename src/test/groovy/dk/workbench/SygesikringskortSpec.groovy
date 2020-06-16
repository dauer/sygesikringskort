package dk.workbench

import spock.lang.*

class SygesikringskortSpec extends Specification {

    def "Test gyldigt input"() {
        when:
        String tracks = /%DOE^JOHN JOHNNY                   ]L\KKE ALLE 7 3                   4615000?;9208100401017023451041173083461010814?/
        def fields = Sygesikringskort.parse(tracks)

        then:
        fields.firstname   == "JOHN JOHNNY"
        fields.lastname    == "DOE"
        fields.address     == "ÅLØKKE ALLE 7 3"
        fields.municipal   == "461"
        fields.postalcode  == "5000"
        fields.cardtype    == "9"
        fields.nationality == "208"
        fields.application == "1"
        fields.issuer      == "004"
        fields.cpr         == "0101702345"
        fields.group       == "1"
        fields.doctor      == "041173"
        fields.region      == "083"
        fields.municipal2  == "461"
        fields.date        == "010814"
    }

    def "Test ugyldigt input - for kort streng"() {
        when:
        String track = /%DOE^JOHN                           ]L\KKE ALLE 7 3                   4615000?;/
        Sygesikringskort.parse(track)

        then:
        thrown AssertionError
    }

    def "Test ugyldigt input - for lang streng"() {
        when:
        String track = /%DOE^JOHN JOHNNY JOHNSON                  ]L\KKE ALLE 7 3                   4615000?;9208100401017023451041173083461010814?/
        Sygesikringskort.parse(track)

        then:
        thrown AssertionError
    }

    def "Test dansk NRCS tabel"() {
        when:
        def oversat = Sygesikringskort.translate(tegn)

        then:
        oversat == forventet

        where:
        tegn || forventet
        '#'  || '#'
        '@'  || 'Ä'
        '['  || 'Æ'
        '\\' || 'Ø'
        ']'  || 'Å'
        '^'  || 'Ü'
        '_'  || '_'
        '`'  || 'ä'
        '{'  || 'æ'
        '|'  || 'ø'
        '}'  || 'å'
        '~'  || 'ü'
    }

    def "Test at kun specialtegn oversættes"() {
        when:
        String oversat = Sygesikringskort.translate(tegn)

        then:
        oversat == forventet

        where:
        tegn                         || forventet
        'abcdefghijklmnopqrstuvxyz'  || 'abcdefghijklmnopqrstuvxyz'
        'ABCDEFGHIJKLMNOPQRSTUVXYZ'  || 'ABCDEFGHIJKLMNOPQRSTUVXYZ'
        '0123456789'                 || '0123456789'
    }

}
