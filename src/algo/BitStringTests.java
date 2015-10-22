package algo;

import java.util.Random;


/**
 * Some tests for BitString.
 */
public class BitStringTests {
    private static char flip(char that) {
        if (that == '0') {
            return '1';
        } else if (that == '1') {
            return '0';
        } else {
            throw new IllegalArgumentException("Unexpected bit char '" + that + "'");
        }
    }

    private static String flip(String what, int from, int until) {
        StringBuilder sb = new StringBuilder(what.length());
        for (int i = 0; i < from; ++i) {
            sb.append(what.charAt(i));
        }
        for (int i = from; i < until; ++i) {
            sb.append(flip(what.charAt(i)));
        }
        for (int i = until; i < what.length(); ++i) {
            sb.append(what.charAt(i));
        }
        return sb.toString();
    }

    private static boolean and(String what, int from, int until) {
        boolean rv = true;
        for (int i = from; i < until; ++i) {
            rv &= what.charAt(i) == '1';
        }
        return rv;
    }

    private static boolean or(String what, int from, int until) {
        boolean rv = false;
        for (int i = from; i < until; ++i) {
            rv |= what.charAt(i) == '1';
        }
        return rv;
    }

    private static String noneBitsSet(int length) {
        StringBuilder rv = new StringBuilder(length);
        for (int i = 0; i < length; ++i) {
            rv.append('0');
        }
        return rv.toString();
    }

    private static int bitCount(String what) {
        int rv = 0;
        for (int i = 0; i < what.length(); ++i) {
            rv += what.charAt(i) - '0';
        }
        return rv;
    }

	private static int leadingOnes(String what) {
		int rv = 0;
		for (int i = 0; i < what.length(); ++i) {
			if (what.charAt(i) == '1') {
				++rv;
			} else {
				break;
			}
		}
		return rv;
	}

    static class Both {
        final String string;
        final BitString bitString;

        private Both(String string, BitString bitString) {
            this.string = string;
            this.bitString = bitString;

            String bsf = bitString.toString();
            if (!string.equals(bsf)) {
                throw new AssertionError("'" + string + "' expected, '" + bsf + "' found");
            }
            if (leadingOnes(string) != bitString.leadingOnes()) {
                throw new AssertionError("LeadingOnes: " + leadingOnes(string) + " expected, " + bitString.leadingOnes() + " found");
            }

            for (int from = 0; from < string.length(); ++from) {
                for (int until = from; until < string.length(); ++until) {
                    boolean strAnd = and(string, from, until);
                    boolean bsAnd = bitString.and(from, until);
                    if (strAnd != bsAnd) {
                        throw new AssertionError(string + " and(" + from + ", " + until + ") expected " + strAnd + " found " + bsAnd);
                    }

                    boolean strOr = or(string, from, until);
                    boolean bsOr = bitString.or(from, until);
                    if (strOr != bsOr) {
                        throw new AssertionError(string + " or(" + from + ", " + until + ") expected " + strOr + " found " + bsOr);
                    }
                }
            }
        }

        public Both(int length) {
            this(noneBitsSet(length), BitString.noneBitsSet(length));
        }

        public Both flip(int from, int until) {
            BitString clone = bitString.clone();
            clone.flip(from, until);
            try {
                return new Both(BitStringTests.flip(string, from, until), clone);
            } catch (AssertionError th) {
                throw new AssertionError(th.getMessage() + " at flip(" + from + ", " + until + ")");
            }
        }

        public int bitCount() {
            int sbc = BitStringTests.bitCount(string);
            int bsbc = bitString.bitCount();
            if (sbc != bsbc) {
                throw new AssertionError("Expected " + sbc + " found " + bsbc);
            }
            return sbc;
        }
    }

    public static void main(String[] args) {
        Random random = new Random();

        for (int length : new int[] {10, 63, 64, 65, 127, 128, 129, 255, 256, 257}) {
            System.out.println("Running for length = " + length);
            Both curr = new Both(length);
            for (int times = 0; times < 10; ++times) {
                System.out.print("+");
                while (curr.bitCount() < length) {
                    int i1 = random.nextInt(length);
                    int i2 = i1 + random.nextInt(Math.min(length + 1 - i1, 3));
                    Both next = curr.flip(i1, i2);
                    if (next.bitCount() >= curr.bitCount()) {
                        curr = next;
                    }
                }
                System.out.print("-");
                while (curr.bitCount() > 0) {
                    int i1 = random.nextInt(length);
                    int i2 = i1 + random.nextInt(Math.min(length + 1 - i1, 3));
                    Both next = curr.flip(i1, i2);
                    if (next.bitCount() <= curr.bitCount()) {
                        curr = next;
                    }
                }
            }
            System.out.println();
        }
    }
}
