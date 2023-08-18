package dk.workbench.sygesikring

import groovy.transform.CompileDynamic
import spock.lang.Specification

/**
 * Test af klasse til indlæsning af data fra sygesikringskort som de læses med magnetkortlæser.
 *
 * @author Thomas Rasmussen
 */
@CompileDynamic
@SuppressWarnings(['UnnecessaryBooleanExpression', 'JUnitTestMethodWithoutAssert', 'MethodName', 'LineLength'])
class SygesikringskortSpec extends Specification {

    void "Test gyldigt input"() {
        when:
        String tracks = /%DOE^JOHN JOHNNY                   ]L\KKE ALLE 7 3                   4615000?;9208100401017023451041173083461010814?/
        Map<String, String> fields = Sygesikringskort.parse(tracks)

        then:
        with(fields) {
            firstname == 'JOHN JOHNNY'
            lastname == 'DOE'
            address == 'ÅLØKKE ALLE 7 3'
            municipal == '461'
            postalcode == '5000'
            cardtype == '9'
            nationality == '208'
            application == '1'
            issuer == '004'
            cpr == '0101702345'
            group == '1'
            doctor == '041173'
            region == '083'
            municipal2 == '461'
            date == '010814'
        }
    }

    void "Test ugyldigt input - for kort streng"() {
        when:
        String track = /%DOE^JOHN                           ]L\KKE ALLE 7 3                   4615000?;/
        Sygesikringskort.parse(track)

        then:
        thrown AssertionError
    }

    void "Test ugyldigt input - for lang streng"() {
        when:
        String track = /%DOE^JOHN JOHNNY JOHNSON                  ]L\KKE ALLE 7 3                   4615000?;9208100401017023451041173083461010814?/
        Sygesikringskort.parse(track)

        then:
        thrown AssertionError
    }

}
