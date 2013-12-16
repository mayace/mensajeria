
basedir		= .
srcdir		= $(basedir)/src
libdir 		= $(basedir)/lib
cuplib 		= $(libdir)/java-cup-11a.jar
jflexlib	= $(libdir)/JFlex.jar


servidor: $(srcdir)/servidor/scanner.jflex $(srcdir)/servidor/parser.cup
	java -jar $(jflexlib) $(srcdir)/servidor/scanner.jflex
	java -jar $(cuplib) -parser Parser -symbols Sym $(srcdir)/servidor/parser.cup
	mv Sym.java ./servidor
	mv Parser.java  ./servidor
	mv $(srcdir)/servidor/Scanner.java ./servidor

servidorf: $(srcdir)/servidor/scanner_file.jflex $(srcdir)/servidor/parser_file.cup
	java -jar $(jflexlib) $(srcdir)/servidor/scanner_file.jflex
	java -jar $(cuplib) -parser Parser_file -symbols Sym_file $(srcdir)/servidor/parser_file.cup
	mv Sym_file.java ./servidor
	mv Parser_file.java  ./servidor
	mv $(srcdir)/servidor/Scanner_file.java ./servidor


proem_cup: $(srcdir)/proem/parser.cup
	java -jar $(cuplib) -parser Parser -symbols Sym $(srcdir)/proem/parser.cup
	mv Sym.java ./proem
	mv Parser.java  ./proem

proem_jflex: $(srcdir)/proem/scanner.jflex
	java -jar $(jflexlib) $(srcdir)/proem/scanner.jflex
	mv $(srcdir)/proem/Scanner.java ./proem

proem: proem_jflex proem_cup
	echo "proem compiled..."