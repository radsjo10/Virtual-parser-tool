import lr0.LR0Parser;
import util.Grammar;

import java.util.ArrayList;
import java.util.Arrays;

public class TestCli {
    public static void main(String[] args) {
        // Simple epsilon grammar:
        // S -> A
        // A -> epsilon | a A
        //
        // This grammar should accept the empty string and "a a a" etc.
        String grammarText = String.join("\n",
                "S -> A",
                "A -> epsilon | a A"
        );

        Grammar g = new Grammar(grammarText);
        LR0Parser p = new LR0Parser(g);
        boolean ok = p.parserSLR1();
        if (!ok) {
            System.out.println("Grammar is not SLR(1) per builder.");
            return;
        }

        System.out.println("Accepts empty: " + p.accept(new ArrayList<>(Arrays.asList())));
        System.out.println("Accepts 'a a a': " + p.accept(new ArrayList<>(Arrays.asList("a", "a", "a"))));
        System.out.println("Accepts 'b': " + p.accept(new ArrayList<>(Arrays.asList("b"))));
    }
}

