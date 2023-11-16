package rs.ac.bg.etf.pp1;

import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class Tabela extends Tab {

	
	public static final Struct boolType = new Struct(Struct.Bool);
	
	public static void init() {
		Tab.init();
		Obj novi=new Obj(Obj.Type, "bool", boolType);
		currentScope.addToLocals(novi);
	}
	
	public static final Struct voidType = new Struct(Struct.Interface);
	
}