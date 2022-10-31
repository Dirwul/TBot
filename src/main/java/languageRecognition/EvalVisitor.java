package languageRecognition;

import java.util.HashMap;
import java.util.Map;

public class EvalVisitor extends CalculatorBaseVisitor<Double> {
    /** "memory" for our calculator; variable/value pairs go here */
    Map<String, Double> memory = new HashMap<String, Double>();

    public EvalVisitor(double value) {
        memory.put("x", value);
    }

    public EvalVisitor(double xValue, double yValue) {
        memory.put("x", xValue);
        memory.put("y", yValue);
    }

    @Override
    public Double visitAssign(CalculatorParser.AssignContext ctx) {
        String id = ctx.VARIABLE().getText();  // id is left-hand side of '='
        double value = visit(ctx.expr());   // compute value of expression on right
        memory.put(id, value);           // store it in our memory
        return value;
    }

    /** expr NEWLINE */
    @Override
    public Double visitPrintExpr(CalculatorParser.PrintExprContext ctx) {
        return visit(ctx.expr()); // evaluate the expr child
    }


    /** Power */
    @Override
    public Double visitPower(CalculatorParser.PowerContext ctx) {
        double left = visit(ctx.expr(0));  // get value of left subexpression
        double right = visit(ctx.expr(1)); // get value of right subexpression
        return Math.pow(left,right);
    }

    /** expr op=('*'|'/') expr */
    @Override
    public Double visitMulDiv(CalculatorParser.MulDivContext ctx) {
        double left = visit(ctx.expr(0));  // get value of left subexpression
        double right = visit(ctx.expr(1)); // get value of right subexpression
        if ( ctx.op.getType() == CalculatorParser.TIMES ) return left * right;
        return left / right;
    }

    /** expr op=('+'|'-') expr */
    @Override
    public Double visitAddSub(CalculatorParser.AddSubContext ctx) {
        double left = visit(ctx.expr(0));  // get value of left subexpression
        double right = visit(ctx.expr(1)); // get value of right subexpression
        if ( ctx.op.getType() == CalculatorParser.PLUS ) return left + right;
        return left - right;
    }

    /** Signed */
    @Override
    public Double visitSigned(CalculatorParser.SignedContext ctx) {
        double value = visit(ctx.atom());  // get value of subexpression
        if ( ctx.op.getType() == CalculatorParser.PLUS ) return value;
        return (-1) * value;
    }

    /** Double */
    @Override
    public Double visitDouble(CalculatorParser.DoubleContext ctx) {
        return Double.valueOf(ctx.DOUBLE().getText());
    }

    /** Integer **/
    @Override
    public Double visitInteger(CalculatorParser.IntegerContext ctx) {
        return Double.valueOf(ctx.INT().getText());
    }

    /** ConstantPI **/
    @Override
    public Double visitConstantPI(CalculatorParser.ConstantPIContext ctx) {
        return Math.PI;
    }

    /** ConstantE **/
    @Override
    public Double visitConstantE(CalculatorParser.ConstantEContext ctx) {
        return Math.E;
    }

    /** Variable */
    @Override
    public Double visitVariable(CalculatorParser.VariableContext ctx) {
        String id = ctx.VARIABLE().getText();
        if (memory.containsKey(id) && id.equals("x")) {
            return memory.get(id);
        } else {
            memory.put(id, 0.0);
            return 0.0;
        }
    }

    /** Braces */
    @Override
    public Double visitBraces(CalculatorParser.BracesContext ctx) {
        return visit(ctx.expr()); // return child expr's value
    }

    /** LOG'('expr',' expr')' */
    @Override
    public Double visitLogarithm(CalculatorParser.LogarithmContext ctx) {
        double a = visit(ctx.expr(0));  // get value of 1st subexpression
        double b = visit(ctx.expr(1)); // get value of 2nd subexpression
        return Math.log(b)/Math.log(a);
    }

    /** LN'('expr')' */
    @Override
    public Double visitNaturalLogarithm(CalculatorParser.NaturalLogarithmContext ctx) {
        double a = visit(ctx.expr());  // get value of the subexpression
        return Math.log(a);
    }


    /** SQRT'('expr')' */
    @Override
    public Double visitSquareRoot(CalculatorParser.SquareRootContext ctx) {
        double value = visit(ctx.expr());  // get value of the subexpression
        return Math.sqrt(value);
    }

    /** SIN'('expr')' */
    @Override
    public Double visitSine(CalculatorParser.SineContext ctx) {
        double value = visit(ctx.expr());  // get value of the subexpression
        return Math.sin(value);
    }

    /** ASIN'('expr')' */
    @Override
    public Double visitASine(CalculatorParser.ASineContext ctx) {
        double value = visit(ctx.expr());  // get value of the subexpression
        return Math.asin(value);
    }

    /** COS'('expr')' */
    @Override
    public Double visitCosine(CalculatorParser.CosineContext ctx) {
        double value = visit(ctx.expr());  // get value of the subexpression
        return Math.cos(value);
    }

    /** ACOS'('expr')' */
    @Override
    public Double visitACosine(CalculatorParser.ACosineContext ctx) {
        double value = visit(ctx.expr());  // get value of the subexpression
        return Math.acos(value);
    }

    /** TAN'('expr')' */
    @Override
    public Double visitTangent(CalculatorParser.TangentContext ctx) {
        double value = visit(ctx.expr());  // get value of the subexpression
        return Math.tan(value);
    }

    /** ATAN'('expr')' */
    @Override
    public Double visitATangent(CalculatorParser.ATangentContext ctx) {
        double value = visit(ctx.expr());  // get value of the subexpression
        return Math.atan(value);
    }
}
        

   