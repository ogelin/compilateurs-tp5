package analyzer.visitors;

import analyzer.ast.*;

import java.io.PrintWriter;
import java.io.IOException;

public class PrintOptimizedCodeVisitor implements ParserVisitor
{

    private final PrintWriter m_writer;

    public PrintOptimizedCodeVisitor(PrintWriter writer) {
        m_writer = writer;
    }

    // Paramètre data: On ne transmet rien aux enfants
    // Valeur de retour: On ne retourne rien aux parents
    public Object visit(SimpleNode node, Object data) {
        return null;
    }

    // Paramètre data: On ne transmet rien aux enfants
    // Valeur de retour: On ne retourne rien aux parents
    public Object visit(ASTProgram node, Object data) {
        // Visiter les enfants
        node.childrenAccept(this, null);

        m_writer.close();
        return null;
    }

    // Paramètre data: On ne transmet rien aux enfants
    // Valeur de retour: On ne retourne rien aux parents
    public Object visit(ASTBlock node, Object data) {
        node.childrenAccept(this, null);
        return null;
    }

    // Paramètre data: On ne transmet rien aux enfants
    // Valeur de retour: On ne retourne rien aux parents
    public Object visit(ASTLive node, Object data) {
        node.childrenAccept(this, null);
        return null;
    }

    // Paramètre data: On ne transmet rien aux enfants
    // Valeur de retour: On ne retourne rien aux parents
    public Object visit(ASTStmt node, Object data) {
        node.childrenAccept(this, null);
        return null;
    }

    // Paramètre data: On ne transmet rien aux enfants
    // Valeur de retour: On ne retourne rien aux parents
    public Object visit(ASTAssignStmt node, Object data) {

        // TODO:: Présentement on imprime seulement le code non-optimizé, comment corriger la situation?

        // On ne visite pas les enfants puisque l'on va manuellement chercher leurs valeurs

        // On n'a rien a transférer aux enfants
        String assigned = (String)node.jjtGetChild(0).jjtAccept(this, null);
        String left = (String)node.jjtGetChild(1).jjtAccept(this, null);
        String right = (String)node.jjtGetChild(2).jjtAccept(this, null);

        m_writer.println(assigned + " = " + left + " " + node.getOp() + " " + right);
        return null;
    }

    // Paramètre data: On ne transmet rien aux enfants
    // Valeur de retour: On ne retourne rien aux parents
    public Object visit(ASTAssignUnaryStmt node, Object data) {

        // TODO:: Présentement on imprime seulement le code non-optimizé, comment corriger la situation?

        // On ne visite pas les enfants puisque l'on va manuellement chercher leurs valeurs

        // On n'a rien a transférer aux enfants
        String assigned = (String)node.jjtGetChild(0).jjtAccept(this, null);
        String left = (String)node.jjtGetChild(1).jjtAccept(this, null);

        m_writer.println(assigned + " = minus " + left);
        return null;
    }

    // Paramètre data: On ne transmet rien aux enfants
    // Valeur de retour: On ne retourne rien aux parents
    public Object visit(ASTAssignDirectStmt node, Object data) {

        // TODO:: Présentement on imprime seulement le code non-optimizé, comment corriger la situation?

        // On ne visite pas les enfants puisque l'on va manuellement chercher leurs valeurs

        // On n'a rien a transférer aux enfants
        String assigned = (String)node.jjtGetChild(0).jjtAccept(this, null);
        String left = (String)node.jjtGetChild(1).jjtAccept(this, null);

        m_writer.println(assigned + " = " + left);
        return null;
    }

    // Paramètre data: On ne transmet rien aux enfants
    // Valeur de retour: on retourne son identifiant, qui est celui de l'enfant
    public Object visit(ASTExpr node, Object data) {
        return node.jjtGetChild(0).jjtAccept(this, null);
    }

    // Paramètre data: On ne transmet rien aux enfants
    // Valeur de retour: On retourne l'indentifiant du noeud
    public Object visit(ASTIntValue node, Object data) {
        return String.valueOf(node.getValue());
    }

    // Paramètre data: On ne transmet rien aux enfants
    // Valeur de retour: On retourne l'indentifiant du noeud
    public Object visit(ASTIdentifier node, Object data) {
        return node.getValue();
    }
}
