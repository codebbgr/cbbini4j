/*
 * Copyright 2013-2020
 * codebb.gr
 */
/**
 * Changelog
 * =========
 * 03/04/2020 (gmoralis) - Initial commit from ini4j project
 */
package gr.codebb.cbbini4j.spi;

public class WinEscapeTool extends EscapeTool
{
    private static final int ANSI_HEX_DIGITS = 2;
    private static final int ANSI_OCTAL_DIGITS = 3;
    private static final int OCTAL_RADIX = 8;
    private static final WinEscapeTool INSTANCE = new WinEscapeTool();

    public static WinEscapeTool getInstance()
    {
        return INSTANCE;
    }

    @Override void escapeBinary(StringBuilder buff, char c)
    {
        buff.append("\\x");
        buff.append(HEX[(c >>> HEX_DIGIT_3_OFFSET) & HEX_DIGIT_MASK]);
        buff.append(HEX[c & HEX_DIGIT_MASK]);
    }

    @Override int unescapeBinary(StringBuilder buff, char escapeType, String line, int index)
    {
        int ret = index;

        if (escapeType == 'x')
        {
            try
            {
                buff.append((char) Integer.parseInt(line.substring(index, index + ANSI_HEX_DIGITS), HEX_RADIX));
                ret = index + ANSI_HEX_DIGITS;
            }
            catch (Exception x)
            {
                throw new IllegalArgumentException("Malformed \\xHH encoding.", x);
            }
        }
        else if (escapeType == 'o')
        {
            try
            {
                buff.append((char) Integer.parseInt(line.substring(index, index + ANSI_OCTAL_DIGITS), OCTAL_RADIX));
                ret = index + ANSI_OCTAL_DIGITS;
            }
            catch (Exception x)
            {
                throw new IllegalArgumentException("Malformed \\oOO encoding.", x);
            }
        }

        return ret;
    }
}
