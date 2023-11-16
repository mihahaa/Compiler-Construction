package rs.ac.bg.etf.pp1;

import java.util.Stack;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticAnalyzer extends VisitorAdaptor {

	int varDeclCount = 0;
	
	public void visit(VarDecl vardecl){
		varDeclCount++;
	}
	
	int printCallCount = 0;
	Obj currentMethod = null;
	Struct currentType=null;
	boolean returnFound = false;
	boolean errorDetected = false;
	int numberOfParameters=0;
	int numberOfWhiles=0;
	public int nVars=0;
	int hasMain=0;
	Stack<ArrayList<Struct>> stek=new Stack<>();
	
	Logger log = Logger.getLogger(getClass());

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}

	
	
	@Override 
	public void visit(CharConst charConst)
	{
		if(!checkConst(charConst.getCname(), Tab.charType, charConst))
		{
			return;
		}
		else
		{
			charConst.obj =Tab.insert(Obj.Con, charConst.getCname(), Tab.charType);
			int vr=charConst.getCval();
			charConst.obj.setAdr(vr);
			report_info("Nova konstanta: "+charConst.getCname()+" = "+charConst.getCval(), charConst);
		}
		return;
	}
	
	@Override 
	public void visit(BoolConst boolConst)
	{
		if(!checkConst(boolConst.getBname(), Tabela.boolType, boolConst))
		{
			return;
		}
		else
		{
			boolConst.obj =Tab.insert(Obj.Con, boolConst.getBname(), Tabela.boolType);
			int vr=boolConst.getBval()?1:0;
			boolConst.obj.setAdr(vr);
			report_info("Nova konstanta: "+boolConst.getBname()+" = "+(boolConst.getBval()?"true":"false"), boolConst);
		}
		return;
	}
	
	boolean checkConst(String name,Struct tip, SyntaxNode info)
	{
		
		if(Tab.find(name)!=Tab.noObj)
		{
			report_error("Simbol "+name+"je vec definisan", info);
			return false;
		}
		else if(!currentType.equals(tip))
		{
			report_error("Tipovi pri deklaraciji konstante se ne poklapaju", info);
			return false;
		}
		
		return true;
	}

	@Override 
	public void visit(NumberConst numberConst)
	{
		if(!checkConst(numberConst.getNname(), Tab.intType, numberConst))
		{
			return;
		}
		else
		{
			numberConst.obj =Tab.insert(Obj.Con, numberConst.getNname(), Tab.intType);
			int vr=numberConst.getNval();
			numberConst.obj.setAdr(vr);
			report_info("Nova konstanta: "+numberConst.getNname()+" = "+numberConst.getNval(), numberConst);
		}
		return;
	}
	
	@Override 
	public void visit(Type type)
	{
		Obj tip=Tab.find(type.getTip());
		
		if(tip==Tab.noObj)
		{
			type.struct=Tab.noType;
			currentType=type.struct;
			report_error("Tip"+type.getTip()+"nije pronadjen", null);
		}
		else
		{
			if(Obj.Type!=tip.getKind())
			{
				type.struct=Tab.noType;
				report_error("Identifikator sa imenom"+type.getTip()+"nije tip", null);
			}
			else
			{
				type.struct=tip.getType();
			}
			currentType=type.struct;
		}
		return;
	}
	
	@Override 
	public void visit(SomeVarWith someVarWith)
	{
		if(currentType.equals(Tab.noType))
		{
			report_error("Identifikator sa imenom"+currentType.toString()+"nije tip", someVarWith);
		}
		else if(Tab.currentScope.findSymbol(someVarWith.getWname())==Tab.noObj)
		{
			report_error("Simbol sa imenom"+someVarWith.getWname()+"je vec definisan", someVarWith);
		}
		else
		{
			Tab.insert(Obj.Var, someVarWith.getWname(), new Struct(Struct.Array,new Struct(Struct.Array,currentType)));
			report_info("Nova promenljiva: "+someVarWith.getWname()+" je deklarisana", someVarWith);
		}
		return;
	}
	
	@Override 
	public void visit(SomeVarWithout someVarWithout)
	{
		if(currentType.equals(Tab.noType))
		{
			report_error("Identifikator sa imenom"+currentType.toString()+"nije tip", someVarWithout);
		}
		else if(Tab.currentScope.findSymbol(someVarWithout.getWoname())==Tab.noObj)
		{
			report_error("Simbol sa imenom"+someVarWithout.getWoname()+"je vec definisan", someVarWithout);
		}
		else
		{
			Tab.insert(Obj.Var, someVarWithout.getWoname(), new Struct(Struct.Array,currentType));
			report_info("Nova promenljiva: "+someVarWithout.getWoname()+" je deklarisana", someVarWithout);
		}
		return;
	}
	
	@Override 
	public void visit(SomeVarEmpty someVarEmpty)
	{
		if(currentType.equals(Tab.noType))
		{
			report_error("Identifikator sa imenom"+currentType.toString()+"nije tip", someVarEmpty);
		}
		else if(Tab.currentScope.findSymbol(someVarEmpty.getEname())==Tab.noObj)
		{
			report_error("Simbol sa imenom"+someVarEmpty.getEname()+"je vec definisan", someVarEmpty);
		}
		else
		{
			Tab.insert(Obj.Var, someVarEmpty.getEname(), currentType);
			report_info("Nova promenljiva: "+someVarEmpty.getEname()+" je deklarisana", someVarEmpty);
		}
		return;
	}
	
	
	@Override
	public void visit(ProgramName programName) {
		programName.obj=Tab.insert(Obj.Prog, programName.getProgramName(), Tab.noType);
		Tab.openScope();
		return;
	}
	
	@Override
	public void visit(Program program) {
		
		nVars=Tab.currentScope.getnVars();
		if(hasMain==0)
		{
			report_error("Metod main() ne postoji", null);
		}
		Tab.chainLocalSymbols(program.getProgramName().obj);
		Tab.closeScope();
		return;
	}
	
	
	@Override
	public void visit(MethodTypeName methodTypeName) {
		if(Tab.find(methodTypeName.getMethodname())!=Tab.noObj || methodTypeName.getReturnType().struct==Tab.noType)
		{
			if(Tab.find(methodTypeName.getMethodname())!=Tab.noObj)
			{
				report_error("Simbol "+methodTypeName.getMethodname()+" je vec definisan", methodTypeName);
			}
			if(methodTypeName.getReturnType().struct==Tab.noType)
			{
				report_error("Povratni tip funkcije nije validan", methodTypeName);
				methodTypeName.obj=Tab.noObj;
				currentMethod=Tab.noObj;
				Tab.openScope();
			}
		}
		else
		{
			currentMethod=Tab.insert(Obj.Meth, methodTypeName.getMethodname(), methodTypeName.getReturnType().struct);
			methodTypeName.obj=currentMethod;
			report_info("Nova funkcija: "+methodTypeName.getMethodname(), methodTypeName);
			Tab.openScope();
		}
		returnFound=false;
		return;
	}
	
	@Override
	public void visit(ReturnTypeType returnTypeType) {
		returnTypeType.struct=currentType;
		return;
	}
	
	@Override
	public void visit(MethodDecl methodDecl) {
		
		if(numberOfParameters!=0 && currentMethod.getName().equals("main"))
		{
			report_error("Main funkcija ima parametre sto nije dozvoljeno", null);
		}
		else hasMain=1;
		
		currentMethod.setLevel(numberOfParameters);
		
		numberOfParameters=0;
		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();
		returnFound = false;
		currentMethod = null;
		
			
			
		return;
	}
	
	@Override
	public void visit(ReturnTypeVoid returnTypeVoid) {
		returnTypeVoid.struct = Tabela.voidType;
		currentType=Tab.noType;
		return;
	}
	
	@Override 
	public void visit(CondFactWithout condFactWithout)
	{
		condFactWithout.struct=condFactWithout.getExpr().struct;
		return;
	}
	
	@Override 
	public void visit(CondFactWith condFactWith)
	{
		if(!condFactWith.getExpr().struct.compatibleWith(condFactWith.getExpr1().struct))
		{
			report_error("Izrazi koje uporedjujete nisu kompatibilni", condFactWith);
		}
		if(!(condFactWith.getRelop() instanceof RelopEq || condFactWith.getRelop() instanceof RelopNEq) && (condFactWith.getExpr().struct.getKind()==Struct.Array))
		{
			report_error("Nizovi i matrice mogu samo da se uporedjuju pomocu == ili !=", condFactWith);
		}
		condFactWith.struct=Tabela.boolType;
		return;
	}
	
	
	
	int checkSomeForm(String y,SyntaxNode z)
	{
		if(currentType==Tab.noType)
		{
			report_error("Identifikator sa imenom"+currentType.toString()+"nije tip", z);
		}
		else if(Tab.find(y)!=Tab.noObj)
		{
			if(Tab.currentScope.findSymbol(y)!=null)
			{
				report_error("Simbol sa imenom "+y+" je vec definisan", z);
				return 0;
			}
		}
		return 1;
	}
	
	@Override 
	public void visit(SomeFormWith someFormWith)
	{
		numberOfParameters=numberOfParameters+1;
		if(checkSomeForm(someFormWith.getFwname(),someFormWith)==0)
		{
			// do nothing
		}
		else
		{
			Tab.insert(Obj.Var, someFormWith.getFwname(), new Struct(Struct.Array,new Struct(Struct.Array,currentType)));
		}
		return;
	}
	
	@Override 
	public void visit(WhileDummy whileDummy)
	{
		numberOfWhiles=numberOfWhiles+1;
		return;
	}
	
	@Override 
	public void visit(StatementWhile statementWhile)
	{
		numberOfWhiles=numberOfWhiles-1;
		return;
	}
	
	@Override 
	public void visit(StatementBreak statementBreak)
	{
		if(numberOfWhiles>0)
		{
			// do nothing
		}
		else
		{
			report_error("Break mora da se pozove iz while bloka", statementBreak);
		}
		return;
	}
	
	@Override 
	public void visit(StatementCont statementCont)
	{
		if(numberOfWhiles>0)
		{
			// do nothing
		}
		else
		{
			report_error("Continue mora da se pozove iz while bloka", statementCont);
		}
		return;
	}
	
	@Override 
	public void visit(StatementReturnWithout statementReturnWithout)
	{
		return;
	}
	
	@Override 
	public void visit(StatementReturnWith statementReturnWith)
	{
		
		if(currentMethod==Tab.noObj)
		{
			report_error("Return mora biti unutar metode", statementReturnWith);
			return;
		}
		returnFound=true;
		if(currentMethod.getType().equals(statementReturnWith.getExpr().struct))
		{
			// do nothing
		}
		else
		{
			report_error("Tip povratne vrednosti funkcije mora biti isti kao tip koji se prosledjuje return naredbi", statementReturnWith);
		}
		return;
	}
	
	@Override 
	public void visit(SomeFormWithout someFormWithout)
	{
		numberOfParameters=numberOfParameters+1;
		if(checkSomeForm(someFormWithout.getFwoname(),someFormWithout)==0)
		{
			// do nothing
		}
		else
		{
			Tab.insert(Obj.Var, someFormWithout.getFwoname(), new Struct(Struct.Array,currentType));
		}
		return;
	}
	
	@Override 
	public void visit(CondTermEmpty condTermEmpty)
	{
		condTermEmpty.struct=condTermEmpty.getCondFact().struct;
		return;
	}
	
	@Override 
	public void visit(CondTermNoEmpty condTermNoEmpty)
	{
		condTermNoEmpty.struct=condTermNoEmpty.getCondFact().struct;
		return;
	}
	
	@Override 
	public void visit(SomeFormEmpty someFormEmpty)
	{
		numberOfParameters=numberOfParameters+1;
		if(checkSomeForm(someFormEmpty.getFename(),someFormEmpty)==0)
		{
			// do nothing
		}
		else
		{
			Tab.insert(Obj.Var, someFormEmpty.getFename(), currentType);
		}
		return;
	}
	
	@Override 
	public void visit(StatementRead statementRead)
	{
		if(statementRead.getDesignator().obj.getKind()==Obj.Elem || statementRead.getDesignator().obj.getKind()==Obj.Fld || 
				statementRead.getDesignator().obj.getKind()==Obj.Var)
		{
			if(statementRead.getDesignator().obj.getType()==Tab.intType || statementRead.getDesignator().obj.getType()==Tab.intType
					|| statementRead.getDesignator().obj.getType()==Tab.intType)
			{
				// do nothing
			}
			else
			{
				report_error("Tip za koji se poziva read naredba mora da bude int, char ili bool", statementRead);
			}
		}
		else
		{
			report_error("Moze da se ucita promenljiva, element niza ili element matrice", statementRead);
			if(statementRead.getDesignator().obj.getType()==Tab.intType || statementRead.getDesignator().obj.getType()==Tab.intType
					|| statementRead.getDesignator().obj.getType()==Tab.intType)
			{
				// do nothing
			}
			else
			{
				report_error("Tip za koji se poziva print naredba mora da bude int, char ili bool", statementRead);
			}
		}
		return;
	}
	
	
	
	
	@Override 
	public void visit(SingleTermNeg singleTermNeg)
	{
		Struct temp=singleTermNeg.getTerm().struct;
		if(temp==Tab.intType)
		{
			singleTermNeg.struct=Tab.intType;
		}
		else
		{
			report_error("Izraz koji negirate mora da bude int tipa", singleTermNeg);
		}
		return;
	}	
	
	@Override 
	public void visit(SingleTermNoNeg singleTermNoNeg)
	{
		singleTermNoNeg.struct=singleTermNoNeg.getTerm().struct;
		return;
	}
	
	@Override 
	public void visit(ConditionNoEmpty conditionNoEmpty)
	{
		conditionNoEmpty.struct=conditionNoEmpty.getCondTerm().struct;
		if(conditionNoEmpty.struct!=Tabela.boolType)
		{
			report_error("Uslov nije boolean tipa", conditionNoEmpty);
		}
		return;
	}
	
	@Override 
	public void visit(ConditionEmpty ConditionEmpty)
	{
		ConditionEmpty.struct=ConditionEmpty.getCondTerm().struct;
		if(ConditionEmpty.struct!=Tabela.boolType)
		{
			report_error("Uslov nije boolean tipa", ConditionEmpty);
		}
		return;
	}
	
	
	@Override 
	public void visit(ExprTerm exprTerm)
	{
		exprTerm.struct=exprTerm.getSingleTerm().struct;
		return;
	}
	
	@Override 
	public void visit(ExprAddop exprAddop)
	{
		if (exprAddop.getTerm().struct.equals(exprAddop.getExpr().struct) && exprAddop.getTerm().struct == Tab.intType) {
			exprAddop.struct = Tab.intType;
		}
		else {
			report_error("Izrazi koje sabirate moraju biti tipa int", exprAddop);
			exprAddop.struct = Tab.noType;
		}
		return;
	}
	
	@Override 
	public void visit(DesignatorAssignNoError designatorAssignNoError)
	{
		if(designatorAssignNoError.getDesignator().obj.getKind()==Obj.Elem || 
				designatorAssignNoError.getDesignator().obj.getKind()==Obj.Fld || 
						designatorAssignNoError.getDesignator().obj.getKind()==Obj.Var)
		{
			// do nothing
		}
		else
		{
			report_error("Leva strana dodele mora da bude promenljiva, element niza ili element matrice", designatorAssignNoError);
		}
		if(designatorAssignNoError.getExpr().struct.assignableTo(designatorAssignNoError.getDesignator().obj.getType()))
		{
			// do nothing
		}
		else
		{
			report_error("Leva strana nije kompatibilna desnoj", designatorAssignNoError);
		}
		return;
	}
	
	@Override 
	public void visit(DesignatorStatementInc designatorStatementInc)
	{
		if(designatorStatementInc.getDesignator().obj.getKind()==Obj.Elem || 
				designatorStatementInc.getDesignator().obj.getKind()==Obj.Fld || 
						designatorStatementInc.getDesignator().obj.getKind()==Obj.Var)
		{
			// do nothing
		}
		else
		{
			report_error("Leva strana dodele mora da bude promenljiva, element niza ili element matrice", designatorStatementInc);
		}
		if(designatorStatementInc.getDesignator().obj.getType()==Tab.intType)
		{
			// do nothing
		}
		else
		{
			report_error("Ne moze se inkrementirati vrednost koja nije tipa int", designatorStatementInc);
		}
		return;
	}
	
	@Override 
	public void visit(DesignatorStatementDec designatorStatementDec)
	{
		if(designatorStatementDec.getDesignator().obj.getKind()==Obj.Elem || 
				designatorStatementDec.getDesignator().obj.getKind()==Obj.Fld || 
						designatorStatementDec.getDesignator().obj.getKind()==Obj.Var)
		{
			// do nothing
		}
		else
		{
			report_error("Leva strana dodele mora da bude promenljiva, element niza ili element matrice", designatorStatementDec);
		}
		if(designatorStatementDec.getDesignator().obj.getType()==Tab.intType)
		{
			// do nothing
		}
		else
		{
			report_error("Ne moze se dekrementirati vrednost koja nije tipa int", designatorStatementDec);
		}
		return;
	}
	
	@Override 
	public void visit(TermFactor termFactor)
	{
		termFactor.struct=termFactor.getFactor().struct;
		return;
	}
	
	@Override 
	public void visit(ActParsExprListNoEmpty actParsExprListNoEmpty)
	{
		stek.peek().add(actParsExprListNoEmpty.getExpr().struct);
		return;
	}
	
	@Override 
	public void visit(ActParsExprListEmpty actParsExprListEmpty)
	{
		stek.peek().add(actParsExprListEmpty.getExpr().struct);
		return;
	}
	
	@Override 
	public void visit(FactorBool factorBool)
	{
		factorBool.struct=Tabela.boolType;
		return;
	}
	
	@Override 
	public void visit(DesignatorStatementMethod designatorStatementMethod)
	{
		Obj designator = designatorStatementMethod.getMethodName().getDesignator().obj;
		ArrayList<Struct> actstek = stek.pop();
		if(designator.getKind()==Obj.Meth)
		{
			Collection<Obj> formalstek=designator.getLocalSymbols();
			int formalsteksize=designator.getLevel();
			Iterator<Obj> formalIter = formalstek.iterator();
			Iterator<Struct> actIter = actstek.iterator();
			int i;
			
			for(i=formalsteksize;i>0;i--)
			{
				if(!actIter.hasNext()) break;
				if(!actIter.next().assignableTo(formalIter.next().getType()))
				{
					report_error("Parametri u pozivu funkcije i oni u njenoj definiciji nisu kompatibilni", designatorStatementMethod);
				}
			}
			if(!actIter.hasNext() && i==0)
			{
				// do nothing
			}
			else
			{
				report_error("Prosledjen broj argumenata nije validan", designatorStatementMethod);
			}
		}
		else
		{
			report_error("Ime pozvanog metoda nije validno", designatorStatementMethod);
			return;
		}
		
		return;
	}
	
	@Override 
	public void visit(MethodName methodName)
	{
		stek.add(new ArrayList<Struct>());
		methodName.obj=methodName.getDesignator().obj;
		return;
	}
	
	
	@Override 
	public void visit(StatementPrintWith statementPrintWith)
	{
		if(statementPrintWith.getExpr().struct==Tab.intType || statementPrintWith.getExpr().struct==Tab.intType
				|| statementPrintWith.getExpr().struct==Tab.intType)
		{
			// do nothing
		}
		else
		{
			report_error("Tip za koji se poziva print naredba mora da bude int, char ili bool", statementPrintWith);
		}
		return;
	}
	
	@Override 
	public void visit(TermMulopFactor termMulopFactor)
	{
		if (termMulopFactor.getFactor().struct.equals(termMulopFactor.getTerm().struct) && termMulopFactor.getFactor().struct == Tab.intType) {
			termMulopFactor.struct = Tab.intType;
		}
		else {
			report_error("Izrazi koje mnozite moraju biti tipa int", termMulopFactor);
			termMulopFactor.struct = Tab.noType;
		}
		return;
	}
	
	@Override 
	public void visit(DesignatorMatrix designatorMatrix)
	{
		if(designatorMatrix.getDesignatorName().obj.getType().getKind()!=Struct.Array || designatorMatrix.getDesignatorName().obj.getType().getElemType().getKind()!=Struct.Array)
		{
			report_error("Promenljiva "+designatorMatrix.getDesignatorName().obj.getName()+" mora da bude matrica", designatorMatrix);
			designatorMatrix.obj=Tab.noObj;
			return;
		}
		if(designatorMatrix.getExpr().struct==Tab.intType && designatorMatrix.getExpr1().struct==Tab.intType)
		{
			// do nothing
		}
		else
		{
			report_error("Indeksi matrice moraju biti int tipa", designatorMatrix);
			designatorMatrix.obj=Tab.noObj;
			return;
		}
		designatorMatrix.obj=new Obj(Obj.Fld, designatorMatrix.getDesignatorName().obj.getName(), designatorMatrix.getDesignatorName().obj.getType().getElemType().getElemType());
		return;
	}
	
	@Override 
	public void visit(DesignatorError designatorError)
	{
		report_error("Promenljiva ne moze da ima vise od 2 dimenzije", designatorError);
		return;
	}
	
	@Override 
	public void visit(StatementPrintWithout statementPrintWithout)
	{
		if(statementPrintWithout.getExpr().struct==Tab.intType || statementPrintWithout.getExpr().struct==Tabela.boolType
				|| statementPrintWithout.getExpr().struct==Tab.charType)
		{
			// do nothing
		}
		else
		{
			report_error("Tip za koji se poziva print naredba mora da bude int, char ili bool", statementPrintWithout);
		}
		return;
	}
	
	void isDesignatorInTab(String x,SyntaxNode y)
	{
		if(Tab.find(x)==Tab.noObj)
		{
			report_error("Designator "+x+ " nije u tabeli simbola", y);
		}
	}
	
	@Override 
	public void visit(DesignatorSingle designatorSingle)
	{
		designatorSingle.obj=designatorSingle.getDesignatorName().obj;
		return;
	}
	
	@Override 
	public void visit(DesignatorName designatorName)
	{
		isDesignatorInTab(designatorName.getI1(),designatorName);
		report_info(designatorName.getI1(), designatorName);
		designatorName.obj=Tab.find(designatorName.getI1());
		return;
	}
	
	@Override 
	public void visit(FactorNumber factorNumber)
	{
		factorNumber.struct=Tab.intType;
		return;
	}
	
	@Override 
	public void visit(DesignatorArray designatorArray)
	{
		if(designatorArray.getDesignatorName().obj.getType().getKind()!=Struct.Array)
		{
			report_error("Promenljiva "+designatorArray.getDesignatorName().obj.getName()+" mora da bude niz", designatorArray);
			designatorArray.obj=Tab.noObj;
			return;
		}
		if(designatorArray.getExpr().struct==Tab.intType)
		{
			// do nothing
		}
		else
		{
			report_error("Indeks nize mora da bude int tipa", designatorArray);
			designatorArray.obj=Tab.noObj;
			return;
		}
		designatorArray.obj=new Obj(Obj.Elem, designatorArray.getDesignatorName().obj.getName(), designatorArray.getDesignatorName().obj.getType().getElemType());
		return;
	}
	
	@Override 
	public void visit(FactorChar factorChar)
	{
		factorChar.struct=Tab.charType;
		return;
	}
	
	@Override 
	public void visit(FactorExpr factorExpr)
	{
		factorExpr.struct=factorExpr.getExpr().struct;
		return;
	}
	
	@Override 
	public void visit(FactorNewExpr factorNewExpr)
	{
		if(factorNewExpr.getExpr().struct==Tab.intType)
		{
			// do nothing
		}
		else
		{
			factorNewExpr.struct=Tab.noType;
			report_error("Velicina niza mora da bude tipa int", factorNewExpr);
			return;
		}
		factorNewExpr.struct=new Struct(Struct.Array,factorNewExpr.getType().struct);
		return;
	}
	
	@Override 
	public void visit(FactorNewMatrixExpr factorNewMatrixExpr)
	{
		if(factorNewMatrixExpr.getExpr().struct==Tab.intType && factorNewMatrixExpr.getExpr1().struct==Tab.intType)
		{
			// do nothing
		}
		else
		{
			factorNewMatrixExpr.struct=Tab.noType;
			report_error("Velicina dimenzija matrice moraju da bude tipa int", factorNewMatrixExpr);
			return;
		}
		factorNewMatrixExpr.struct=new Struct(Struct.Array,new Struct(Struct.Array,factorNewMatrixExpr.getType().struct));
		return;
	}
	
	@Override 
	public void visit(FactorMethod factorMethod)
	{
		Obj designator = factorMethod.getMethodName().getDesignator().obj;
		ArrayList<Struct> actstek = stek.pop();
		factorMethod.struct=Tab.noType;
		if(designator.getKind()==Obj.Meth)
		{
			Collection<Obj> formalstek=designator.getLocalSymbols();
			int formalsteksize=designator.getLevel();
			Iterator<Obj> formalIter = formalstek.iterator();
			Iterator<Struct> actIter = actstek.iterator();
			factorMethod.struct=designator.getType();
			int i;
			
			for(i=formalsteksize;i>0;i--)
			{
				if(!actIter.hasNext()) break;
				if(!actIter.next().assignableTo(formalIter.next().getType()))
				{
					report_error("Parametri u pozivu funkcije i oni u njenoj definiciji nisu kompatibilni", factorMethod);
					return;
				}
			}
			if(!actIter.hasNext() && i==0)
			{
				// do nothing
			}
			else
			{
				report_error("Prosledjen broj argumenata nije validan", factorMethod);
			}
		}
		else
		{
			report_error("Ime pozvanog metoda nije validno", factorMethod);
			return;
		}
		
		return;
	}
	
	@Override 
	public void visit(FactorDesignatorNo factorDesignatorNo)
	{
		factorDesignatorNo.struct=factorDesignatorNo.getDesignator().obj.getType();
		return;
	}
	
	public void visit(StatementMap statementMap)
	{
		if(Tab.find(statementMap.getMapDummy().getMapname()).getType().equals(statementMap.getExpr().struct))
		{
			// do nothing
		}
		else
		{
			report_error("Promenljiva "+statementMap.getMapDummy().getMapname()+" koja sluzi za iteraciju nije istog tipa kao izraz po kom se mapira", statementMap);
		}
	}
	
	@Override 
	public void visit(MapDummy mapDummy)
	{			
		mapDummy.obj=Tab.find(mapDummy.getMapname());
		if(!Tab.find(mapDummy.getMapname()).getType().equals(mapDummy.getDesignator1().obj.getType().getElemType()))
		{
			report_error("Promenljiva "+mapDummy.getMapname()+" koja sluzi za iteraciju kroz niz nije istog tipa kao elementi niza", mapDummy);
		}
		if(Tab.find(mapDummy.getMapname())==Tab.noObj)
		{
			report_error("Promenljiva "+mapDummy.getMapname()+" koja sluzi za iteraciju kroz niz nije deklarisana", mapDummy);
		}
		if(!(mapDummy.getDesignator1().obj.getType().getKind()==Struct.Array && 
		(mapDummy.getDesignator1().obj.getType().getElemType()==Tab.intType || 
				mapDummy.getDesignator1().obj.getType().getElemType()==Tab.charType ||
						mapDummy.getDesignator1().obj.getType().getElemType()==Tabela.boolType)))
		{
			report_error("Tip niza "+mapDummy.getDesignator().obj.getName()+" koji mapiramo mora biti int, char ili bool", mapDummy);
		}
		if(Tab.find(mapDummy.getDesignator().obj.getName())==Tab.noObj)
		{
			report_error("Niz "+mapDummy.getDesignator().obj.getName()+" kome se dodeljuje vrednost nije deklarisan", mapDummy);
		}
		return;
	}
	
	public boolean passed(){
    	return !errorDetected;
    }
}
