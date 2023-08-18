package dk.workbench.nrcs

import groovy.transform.CompileStatic

/**
 * En Groovy klasse til at oversætte imellem national replacement character sets (NRCS)
 *
 * @author Thomas Rasmussen
 */
@SuppressWarnings(['SpaceAroundMapEntryColon'])
@CompileStatic
trait Translate {

    /**
     * Denne metode oversætter fra ascii for DEC VT220 terminal til nationale tegnsæt
     * https://en.wikipedia.org/wiki/National_Replacement_Character_Set
     *
     * @param str streng der skal oversættes
     * @param enc hvilket format skal der oversættes til, understøtter kun dansk
     * @return streng oversat fra ascii til det valgte nationale tegnsæt
     */
    static String translate(String str, String enc = 'da') {
        String out = ''
        Map<String, List<String>> table = [
                ascii : ['#', '@', '[', '\\', ']', '^', '_', '`', '{', '|', '}', '~'],
                da    : ['#', 'Ä', 'Æ', 'Ø',  'Å', 'Ü', '_', 'ä', 'æ', 'ø', 'å', 'ü', null],
        ]
        Closure<Integer> tr = { x -> table['ascii'].findIndexOf { String c -> c == x } }
        str.each { String s ->
            out += table[enc][tr(s)] ?: s
        }

        return out
    }

}
