grammar Calculator;

prog:   stat+ ;

stat:   expr NEWLINE                # printExpr
    |   VARIABLE '=' expr NEWLINE   # assign
    |   NEWLINE                     # blank
    ;

expr:   expr op='^' expr            # Power    // Put this first
    |   expr op=('*'|'/') expr      # MulDiv
    |   expr op=('+'|'-') expr      # AddSub
    |   op=('+'|'-') atom           # Signed
    |   atom                        # Atoms
    |   LOG'('expr',' expr')'       # Logarithm
    |   LN'('expr')'	            # NaturalLogarithm
    |   SQRT'('expr')'	            # SquareRoot
    |   SIN'('expr')'		    # Sine
    |   ASIN'('expr')'		    # ASine
    |   COS'('expr')'		    # Cosine
    |   ACOS'('expr')'		    # ACosine
    |   TAN'('expr')'		    # Tangent
    |   ATAN'('expr')'	            # ATangent
 // | I     #ConstantI
    ;

atom
    :   INT                         # Integer
    |   DOUBLE		            # Double
    |   PI                          # ConstantPI
    |   EULER                       # ConstantE
    |   SCIENTIFIC_NUMBER           # Scientific
    |   VARIABLE                    # Variable
    |   LPAREN expr RPAREN          # Braces
    ;

INT  : [0-9]+;

DOUBLE : [0-9]+'.'[0-9]+;


SCIENTIFIC_NUMBER
   : INT+ ((E1 | E2) SIGN? NUMBER)
   ;

COS
   : 'cos'
   ;


SIN
   : 'sin'
   ;


TAN
   : 'tan'
   ;


ACOS
   : 'acos'
   ;


ASIN
   : 'asin'
   ;


ATAN
   : 'atan'
   ;


LN
   : 'ln'
   ;


LOG
   : 'log'
   ;


SQRT
   : 'sqrt'
   ;


LPAREN
   : '('
   ;


RPAREN
   : ')'
   ;


PLUS
   : '+'
   ;


MINUS
   : '-'
   ;


TIMES
   : '*'
   ;


DIV
   : '/'
   ;


GT
   : '>'
   ;


LT
   : '<'
   ;


EQ
   : '='
   ;


COMMA
   : ','
   ;


POINT
   : '.'
   ;


POW
   : '^'
   ;


PI
   : 'pi'
   ;


EULER
   : E2
   ;


I
   : 'i'
   ;


VARIABLE
   : VALID_ID_START VALID_ID_CHAR*
   ;


fragment VALID_ID_START
   : ('a' .. 'z') | ('A' .. 'Z') | '_'
   ;


fragment VALID_ID_CHAR
   : VALID_ID_START | ('0' .. '9')
   ;


NUMBER
   : ('0' .. '9') + ('.' ('0' .. '9') +)?
   ;


fragment E1
   : 'E'
   ;


fragment E2
   : 'e'
   ;


fragment SIGN
   : ('+' | '-')
   ;

NEWLINE:'\r'? '\n' ;     // return newlines to parser (is end-statement signal)

WS
   : [ \r\n\t] + -> skip
   ;
