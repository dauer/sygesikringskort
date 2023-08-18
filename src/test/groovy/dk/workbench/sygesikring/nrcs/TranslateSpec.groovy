package dk.workbench.sygesikring.nrcs

import groovy.transform.CompileDynamic
import spock.lang.Specification

/**
 * Test af klasse til indlæsning mapping imellem nationale NRCS tabeller.
 *
 * @author Thomas Rasmussen
 */
@CompileDynamic
@SuppressWarnings(['UnnecessaryBooleanExpression', 'MethodName'])
class TranslateSpec  extends Specification {

    /**
     * Dummmy class der implementere Translate trait'en
     */
    @SuppressWarnings(['EmptyClass'])
    class Dummy implements Translate { }

    void "Test dansk NRCS tabel"() {
        when:
        String oversat = Dummy.translate(tegn)

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

    void "Test at kun specialtegn oversættes"() {
        when:
        String oversat = Dummy.translate(tegn)

        then:
        oversat == forventet

        where:
        tegn                         || forventet
        'abcdefghijklmnopqrstuvxyz'  || 'abcdefghijklmnopqrstuvxyz'
        'ABCDEFGHIJKLMNOPQRSTUVXYZ'  || 'ABCDEFGHIJKLMNOPQRSTUVXYZ'
        '0123456789'                 || '0123456789'
    }

}
