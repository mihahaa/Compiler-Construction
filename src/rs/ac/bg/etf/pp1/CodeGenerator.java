package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class CodeGenerator extends VisitorAdaptor {
	public CodeGenerator() {
		Obj obj1 = Tab.find("len");
		obj1.setAdr(Code.pc);
		udji();
		Code.put(Code.arraylength);
		izadji();
		
		Obj obj2 = Tab.find("chr");
		obj2.setAdr(Code.pc);
		udji();
		izadji();
		
		Obj obj3 = Tab.find("ord");
		obj3.setAdr(Code.pc);
		udji();
		izadji();
	}
	
	void izadji()
	{
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	void udji()
	{
		Code.put(Code.enter);
		Code.put(1);
		Code.put(1);
		Code.put(Code.load_n);
	}
	
	Stack<Integer> termStack=new Stack<>();
	int kojiRelop;
	Stack<Integer> thenStack=new Stack<>();
	Stack<List<Integer>> elseStack=new Stack<>();
	Stack<Integer> elseStackpom=new Stack<>();
	
	Stack<Integer> mapStack=new Stack<>();
	Stack<Integer> mapStackEnd=new Stack<>();
	
	public int poc;
	
	@Override
	public void visit(StatementReturnWithout statementReturnWithout)
	{
		izadji();
		return;
	}
	
	@Override
	public void visit(RelopEq relopEq)
	{
		kojiRelop=Code.eq;
		return;
	}
	
	@Override
	public void visit(RelopNEq relopNEq)
	{
		kojiRelop=Code.ne;
		return;
	}
	
	@Override
	public void visit(RelopLess relopLess)
	{
		kojiRelop=Code.lt;
		return;
	}
	
	@Override
	public void visit(RelopELess relopELess)
	{
		kojiRelop=Code.le;
		return;
	}
	
	@Override
	public void visit(RelopGre relopGre)
	{
		kojiRelop=Code.gt;
		return;
	}
	
	@Override
	public void visit(RelopEGre relopEGre)
	{
		kojiRelop=Code.ge;
		return;
	}
	
	
	@Override
	public void visit(FactorNumber factorNumber)
	{
		Obj novi=Tab.insert(Obj.Con, "#const", Tab.intType);
		novi.setLevel(0);
		int vr=factorNumber.getN1();
		novi.setAdr(vr);
		Code.load(novi);
		return;
	}
	
	@Override
	public void visit(MapDummy mapDummy)
	{
		Code.load(mapDummy.getDesignator1().obj);
		Code.put(Code.arraylength);
		if(mapDummy.getDesignator().obj.getType().getElemType()==Tab.charType)
		{	
			noviarej(0);
		}
		else
		{
			noviarej(1);
		}
		Code.store(mapDummy.getDesignator().obj);
		Code.load(mapDummy.getDesignator1().obj);
		Code.loadConst(0);
		mapStackEnd.push(Code.pc);
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
		Code.put(Code.dup2);
		Code.put(Code.arraylength);
		mapStack.push(Code.pc+1);
		Code.putFalseJump(Code.ne, 0);
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
		Code.put(Code.dup2);
		Code.put(kojiLoad(mapDummy.getDesignator().obj.getType().getElemType()));
		Obj pom=mapDummy.obj;
		Code.store(pom);
		Code.put(Code.dup);
		return;
	}
	
	@Override 
	public void visit(MapEndDummy mapEndDummy)
	{
		StatementMap sm=(StatementMap)mapEndDummy.getParent();
		Code.load(sm.getMapDummy().getDesignator().obj);
		Code.put(Code.dup_x2);
		Code.put(Code.pop);
		Code.put(kojiStore(sm.getMapDummy().getDesignator().obj.getType().getElemType()));
		Code.loadConst(1);
		Code.put(Code.add);
		Code.putJump(mapStackEnd.pop());
		Code.fixup(mapStack.pop());
		Code.put(Code.pop);
		Code.put(Code.pop);
		while(!mapStackEnd.empty())
			mapStackEnd.pop();
		while(!mapStack.empty())
			mapStack.pop();
		return;
	}
	
	
	@Override 
	public void visit(MethodTypeName methodTypeName)
	{
		String m="main"; 
		int adr=0;
		if(!m.equals(methodTypeName.getMethodname()))
		{
			adr=Code.pc;
		}
		else
		{
			poc=Code.pc;
			adr=Code.pc;
		}
		methodTypeName.obj.setAdr(adr);
		Code.put(Code.enter);
		Code.put(methodTypeName.obj.getLevel());
		Code.put(methodTypeName.obj.getLocalSymbols().size());
		return;
	}
	
	@Override 
	public void visit(SingleTermNeg singleTermNeg)
	{
		Code.put(Code.neg);
		return;
	}
	
	@Override
	public void visit(StatementPrintWithout statementPrintWithout)
	{
		if(statementPrintWithout.getExpr().struct!=Tab.charType)
		{
			Code.loadConst(5);
			Code.put(Code.print);
			return;
		}
		Code.loadConst(1);
		Code.put(Code.bprint);
	
		return;
	}
	
	
	@Override 
	public void visit(CondFactWithout condFactWithout)
	{
		Code.loadConst(0);
		kojiRelop=Code.ne;
		return;
	}
	
	
	@Override 
	public void visit(DummyAnd dummyAnd)
	{
		int a=Code.pc+1;
		Code.putFalseJump(kojiRelop, 0);
		termStack.push(a);
		return;
	}
	
	@Override 
	public void visit(DummyOr dummyOr)
	{
		int a=Code.pc+1;
		Code.putFalseJump(Code.inverse[kojiRelop], 0);
		thenStack.push(a);
		
		while(!termStack.empty())
		{
			Code.fixup(termStack.pop());
		}
		
		return;
	}
	
	@Override 
	public void visit(MethodDecl methodDecl)
	{
		if (methodDecl.getMethodTypeName().obj.getType() != Tabela.voidType) {
			Code.put(Code.trap);
			Code.put(-1);
		} else {
			izadji();
		}
		return;
	}
	
	@Override
	public void visit(FactorChar factorChar)
	{
		Obj novi=Tab.insert(Obj.Con, "#const", Tab.charType);
		novi.setLevel(0);
		int vr=factorChar.getC1();
		novi.setAdr(vr);
		Code.load(novi);
		return;
	}
	
	@Override
	public void visit(TermMulopFactor termMulopFactor)
	{
		Mulop a=termMulopFactor.getMulop();
		if(a instanceof MulopMul)
		{
			Code.put(Code.mul);
		}
		else if(a instanceof MulopDiv)
		{
			Code.put(Code.div);
		}
		else
		{
			Code.put(Code.rem);
		}
	
		return;
	}
	
	@Override 
	public void visit(StatementRead statementRead)
	{
		if(Tab.charType!=statementRead.getDesignator().obj.getType())
		{
			Code.put(Code.read);
			if(statementRead.getDesignator().obj.getKind()==Obj.Fld)
			{
				Code.put(Code.astore);
			}
			else
			{
				Code.store(statementRead.getDesignator().obj);
			}
		}
		else
		{
			Code.put(Code.bread);
			if(statementRead.getDesignator().obj.getKind()==Obj.Fld)
			{
				Code.put(Code.bastore);
			}
			else
			{
				Code.store(statementRead.getDesignator().obj);
			}
		}
		
		return;
	}
	
	@Override
	public void visit(FactorBool factorBool)
	{
		Obj novi=Tab.insert(Obj.Con, "#const", Tabela.boolType);
		novi.setLevel(0);
		int vr=factorBool.getB1()?1:0;
		novi.setAdr(vr);
		Code.load(novi);
		return;
	}
	
	
	@Override
	public void visit(StatementReturnWith statementReturnWith)
	{
		izadji();
		return;
	}
	
	
	@Override
	public void visit(StatementPrintWith statementPrintWith)
	{
		if(statementPrintWith.getExpr().struct!=Tab.charType)
		{
			Code.loadConst(statementPrintWith.getPrintnum());
			Code.put(Code.print);
			return;
		}
		Code.loadConst(statementPrintWith.getPrintnum());
		Code.put(Code.bprint);
	
		return;
	}
	
	@Override
	public void visit(FactorDesignatorNo factorDesignatorNo)
	{
		if(factorDesignatorNo.getDesignator().obj.getKind()==Obj.Fld)
		{
			// do later
			return;
		}
		Code.load(factorDesignatorNo.getDesignator().obj);
		return;
	}
	
	@Override
	public void visit(ExprAddop exprAddop)
	{
		Addop a=exprAddop.getAddop();
		if(a instanceof AddopPlus)
		{
			Code.put(Code.add);
		}
		else
		{
			Code.put(Code.sub);
		}
	
		return;
	}
	
	void incdec(Obj o, boolean isInc)
	{
		if(o.getKind()==Obj.Var)
		{
			Code.load(o);
			Code.loadConst(1);
			if(isInc) Code.put(Code.add);
			else Code.put(Code.sub);
			Code.store(o);
			return;
		}
		if(o.getKind()==Obj.Elem)
		{
			Code.put(Code.dup2);
			Code.load(o);
			Code.loadConst(1);
			if(isInc) Code.put(Code.add);
			else Code.put(Code.sub);
			Code.store(o);
			return;
		}
		if(o.getKind()==Obj.Fld)
		{
			Code.put(Code.dup_x2);
			Code.put(Code.pop);
			Code.put(Code.aload);
			Code.put(Code.dup_x1);
			Code.put(Code.pop);
			Code.put(Code.dup2);
			Code.put(Code.aload);
			Code.loadConst(1);
			if(isInc) Code.put(Code.add);
			else Code.put(Code.sub);
			Code.put(Code.astore);
		}
		
		return;
	}
	
	Stack<Integer> pocetneStek=new Stack<>();
	
	@Override
	public void visit(Cond cond)
	{
		int a=Code.pc+1;
		Code.putFalseJump(kojiRelop, 0);
		elseStack.peek().add(a);
		
		while(!thenStack.empty())
		{
			Code.fixup(thenStack.pop());
		}
		
		return;
	}
	
	@Override
	public void visit(StatementBreak statementBreak)
	{
		int a=Code.pc+1;
		Code.putJump(0);
		elseStack.peek().add(a);
		return;
	}
	
	@Override
	public void visit(StatementIf statementIf)
	{	
		for(int i=0;i<elseStack.peek().size();i++)
		{
			Code.fixup(elseStack.peek().get(i));
		}
		elseStack.pop();	
		return;
	}
	
	@Override
	public void visit(StatementIfElse statementIfElse)
	{	
		Code.fixup(elseStackpom.pop());	
		return;
	}
	
	@Override
	public void visit(StatementCont statementCont)
	{	
		int a=pocetneStek.peek();
		Code.putJump(a);
		return;
	}
	
	@Override
	public void visit(WhileDummy whileDummy)
	{	
		pocetneStek.push(Code.pc);
		elseStack.push(new ArrayList<>());
		return;
	}
	
	@Override
	public void visit(ElseDummy elseDummy)
	{	
		int a=Code.pc+1;
		Code.putJump(0);
		elseStackpom.push(a);
		
		while(!termStack.empty())
		{
			Code.fixup(termStack.pop());
		}
		
		for(int i=0;i<elseStack.peek().size();i++)
		{
			Code.fixup(elseStack.peek().get(i));
		}
		elseStack.pop();	
		return;
	}
	
	@Override
	public void visit(NoElseDummy noElseDummy)
	{	
		while(!termStack.empty())
		{
			Code.fixup(termStack.pop());
		}
		return;
	}
	
	void callfunction(Obj o,boolean isDesig)
	{
		int offs=o.getAdr()-Code.pc;
		Code.put(Code.call);
		Code.put2(offs);
		if(isDesig)
		{
			if(o.getType()!=Tabela.voidType)
			{
				Code.put(Code.pop);
			}
		}
		return;
	}
	
	@Override
	public void visit(IfDummy ifDummy)
	{
		elseStack.push(new ArrayList<>());
		return;
	}
	
	@Override
	public void visit(StatementWhile statementWhile)
	{
		int a=pocetneStek.pop();
		Code.putJump(a);
		for(int i=0;i<elseStack.peek().size();i++)
		{
			Code.fixup(elseStack.peek().get(i));
		}
		elseStack.pop();
		return;
	}
	
	@Override
	public void visit(DesignatorStatementMethod designatorStatementMethod)
	{
		callfunction(designatorStatementMethod.getMethodName().obj, true);
		return;
	}
	
	@Override
	public void visit(DesignatorStatementDec designatorStatementDec)
	{
		incdec(designatorStatementDec.getDesignator().obj,false);
		return;
	}
	
	@Override
	public void visit(DesignatorAssignNoError DesignatorAssignNoError)
	{
		Obj o=DesignatorAssignNoError.getDesignator().obj;
		if(o.getKind()==Obj.Fld)
		{
			Code.put(kojiStore(DesignatorAssignNoError.getDesignator().obj.getType()));
		}
		else
		{
			Code.store(o);
		}
	
		return;
	}
	
	@Override
	public void visit(DesignatorStatementInc designatorStatementInc)
	{
		incdec(designatorStatementInc.getDesignator().obj,true);
		return;
	}
	
	@Override
	public void visit(DesignatorArray designatorArray)
	{
		return;
	}
	
	@Override
	public void visit(DesignatorName designatorName)
	{
		if(designatorName.getParent() instanceof DesignatorArray || designatorName.getParent() instanceof DesignatorMatrix)
		{
			Code.load(designatorName.obj);
		}
		return;
	}
	
	int kojiLoad(Struct s)
	{
		if(s==Tab.charType)
		{
			return Code.baload;
		}
		return Code.aload;
	}
	
	int kojiStore(Struct s)
	{
		if(s==Tab.charType)
		{
			return Code.bastore;
		}
		return Code.astore;
	}
	
	@Override
	public void visit(DesignatorMatrix DesignatorMatrix)
	{
		if(DesignatorMatrix.getParent() instanceof DesignatorStatementDec || DesignatorMatrix.getParent() instanceof DesignatorStatementInc)
		{
			// do nothing
		}
		else if(DesignatorMatrix.getParent() instanceof DesignatorAssignNoError || DesignatorMatrix.getParent() instanceof StatementRead)
		{
		Code.put(Code.dup_x2);
		Code.put(Code.pop);
		Code.put(kojiLoad(DesignatorMatrix.obj.getType()));
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
		}
		else
		{
			Code.put(Code.dup_x2);
			Code.put(Code.pop);
			Code.put(kojiLoad(DesignatorMatrix.obj.getType()));
			Code.put(Code.dup_x1);
			Code.put(Code.pop);
			Code.put(kojiLoad(DesignatorMatrix.obj.getType()));
		}
		return;
	}
	
	void noviarej(int x)
	{
		Code.put(Code.newarray);
		Code.put(x);
	}
	
	
	@Override
	public void visit(FactorNewExpr factorNewExpr)
	{
		if(factorNewExpr.struct.getElemType()!=Tab.charType)
		{
			noviarej(1);
			return;
		}
		noviarej(0);
		return;
	}
	
	@Override
	public void visit(FactorNewMatrixExpr FactorNewMatrixExpr)
	{
		DesignatorAssignNoError pom=(DesignatorAssignNoError)(((ExprTerm)((SingleTermNoNeg)((TermFactor)FactorNewMatrixExpr.getParent()).getParent()).getParent()).getParent());
		Obj pom2=pom.getDesignator().obj;
		
		Code.put(Code.dup2);
		Code.put(Code.pop);
		noviarej(1);
		Code.store(pom2);
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
		int b=Code.pc;
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.loadConst(0);
		Code.put(Code.dup2);
		int a=Code.pc+1;
		Code.putFalseJump(Code.ge,0);
		Code.put(Code.pop);
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
		Code.put(Code.dup);
		if(FactorNewMatrixExpr.getType().struct!=Tab.charType)
		{
			noviarej(1);
		}
		else noviarej(0);
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
		Code.put(Code.dup_x2);
		Code.put(Code.pop);
		Code.load(pom2);
		Code.put(Code.dup_x2);
		Code.put(Code.pop);
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
		Code.put(Code.dup_x2);
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
		Code.put(kojiStore(pom2.getType().getElemType().getElemType()));
		Code.putJump(b);
		Code.fixup(a);
		Code.put(Code.pop);
		Code.put(Code.pop);
		Code.put(Code.pop);
		Code.load(pom2);
		return;
	}
	
	@Override
	public void visit(FactorMethod factorMethod)
	{
		callfunction(factorMethod.getMethodName().obj, false);
		return;
	}
	
}
