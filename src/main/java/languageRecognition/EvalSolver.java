package languageRecognition;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class EvalSolver {

    public static double run(double value, String expression) throws Exception {
        Locale.setDefault(Locale.US);

        expression = "x = %f\n%s\n".formatted(value, expression);

        InputStream is = new ByteArrayInputStream(expression
                .replaceAll("[{}]", "")
                .getBytes(StandardCharsets.UTF_8)
        );
        ANTLRInputStream input = new ANTLRInputStream(is);

        CalculatorLexer lexer = new CalculatorLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CalculatorParser parser = new CalculatorParser(tokens);
        ParseTree tree = parser.prog();

        EvalVisitor eval = new EvalVisitor();
        return eval.visit(tree);
    }
}