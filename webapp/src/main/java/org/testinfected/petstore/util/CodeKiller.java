package org.testinfected.petstore.util;

import java.text.NumberFormat;

/**
 * Created by zapek on 26.06.14.
 */
public class CodeKiller {

    public static int calculate(String s) {
        if (s != null)
        {
            if (!s.isEmpty())
            {
                int sum = 0;
                String[] array = s.split(",");

                try
                {
                    for (String n : array) {
                        sum += Integer.parseInt(n);
                    }
                }
                catch (NumberFormatException e)
                {
                    throw new IllegalArgumentException("wrong input");
                }
                return (sum);
            }
        }
        return (0);
    }
}
