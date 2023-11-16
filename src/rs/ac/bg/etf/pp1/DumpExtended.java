package rs.ac.bg.etf.pp1;

import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.visitors.DumpSymbolTableVisitor;

public class DumpExtended extends DumpSymbolTableVisitor {

	@Override
	public void visitObjNode(Obj objToVisit) {
		int koji=objToVisit.getKind();
		if(koji==Obj.Con)
			output.append("Con ");
		else if(koji==Obj.Var)
			output.append("Var ");
		else if(koji==Obj.Type)
			output.append("Type ");
		else if(koji==Obj.Meth)
			output.append("Meth ");
		else if(koji==Obj.Fld)
			output.append("Fld ");
		else if(koji==Obj.Prog)
			output.append("Prog ");
		

		output.append(objToVisit.getName());
		output.append(": ");

		if ((Obj.Var == objToVisit.getKind())
				&& "this".equalsIgnoreCase(objToVisit.getName()))
			output.append("");
		else
			objToVisit.getType().accept(this);

		output.append(", ");
		output.append(objToVisit.getAdr());
		output.append(", ");
		output.append(objToVisit.getLevel() + " ");

		if (objToVisit.getKind() == Obj.Prog
				|| objToVisit.getKind() == Obj.Meth) {
			output.append("\n");
			nextIndentationLevel();
		}

		if (objToVisit.getType().getKind() == Struct.Enum) {
			output.append("\n");
			nextIndentationLevel();
		}

		for (Obj o : objToVisit.getLocalSymbols()) {
			output.append(currentIndent.toString());
			o.accept(this);
			output.append("\n");
		}

		if (objToVisit.getKind() == Obj.Meth) {
			for (Obj o : objToVisit.getType().getMembers()) {
				output.append(currentIndent.toString());
				o.accept(this);
				output.append("\n");
			}
		}

		if (objToVisit.getKind() == Obj.Prog
				|| objToVisit.getKind() == Obj.Meth) {
			previousIndentationLevel();
		}

		if (objToVisit.getType().getKind() == Struct.Enum) {
			previousIndentationLevel();
			// output.append(currentIndent.toString());
		}
		
		

		// output.append("]");

	}

	@Override
	public void visitStructNode(Struct structToVisit) {
		int koji=structToVisit.getKind();
		if (koji==Struct.Interface)
			output.append("void");
			
		else if(koji==Struct.Int)
			output.append("int");
		else if (koji==Struct.Char)
			output.append("char");
			
		else if(koji==Struct.Bool)
			output.append("bool");
		else if (koji==Struct.Array)
		{
			output.append("Arr of ");
			int koji2=structToVisit.getElemType().getKind();
			if(koji2==Struct.None)
				output.append("notype");
			else if(koji2==Struct.Int)
				output.append("int");
			else if(koji2==Struct.Char)
				output.append("char");
			else if(koji2==Struct.Bool)
				output.append("bool");
			else if(koji2==Struct.Array)
			{
				output.append("Arr of ");
				int koji3=structToVisit.getElemType().getElemType().getKind();
				if(koji3==Struct.None)
					output.append("notype");
				else if(koji3==Struct.Int)
					output.append("int");
				else if (koji3==Struct.Char)
					output.append("char");
				else if(koji3==Struct.Bool)
					output.append("bool");
			}
		}
	}
}
