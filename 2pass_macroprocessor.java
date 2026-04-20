import java.io.*;

class Macroprocessor {
    public static void main(String args[]) {
        String code[][] = { { "ADD", "A", "", "", "" },
                { "MACRO", "ADD1", "&ARG", "", "" },
                { "LOAD", "ARG", "", "", "" },
                { "MEND", "", "", "", "" },
                { "MACRO", "PQR", "&A", "&B", "&C" },
                { "ADD", "B", "", "", "" },
                { "READ", "C", "", "", "" },
                { "READ", "A", "", "", "" },
                { "MEND", "", "", "", "" },
                { "MACRO", "LMN", "", "", "" },
                { "LOAD", "C", "", "", "" },
                { "MEND", "", "", "", "" },
                { "LOAD", "B", "", "", "" },
                { "PQR", "5", "3", "2", "" },
                { "ADD1", "1", "", "", "" },
                { "LMN", "", "", "" },
                { "SUB", "C", "", "", "" },
                { "ENDP", "", "", "", "", }
        };
        String mn[] = new String[3], fpmn[] = new String[4], fp[] = new String[4], pp[] = new String[4];
        int parameter[] = new int[3], c = 0, d = 0, e = 0, l = 0;
        for (int i = 0; i < 18; i++) {
            if (code[i][0].equals("MACRO")) {
                mn[c] = code[i][1];
                for (int j = 2; j < 5; j++) {
                    if (code[i][j] != "") {
                        fpmn[e] = code[i][1];
                        fp[e] = code[i][j];
                        pp[e++] = "#" + (++d);
                    }
                }
                parameter[c++] = d;
                d = 0;
            }
        }
        String apmn[] = new String[4], ap[] = new String[4], app[] = new String[4];
        c = 1;
        d = 0;
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < mn.length; j++) {
                if (code[i][0].equals(mn[j]) && code[i][1] != "") {
                    while (code[i][c] != "") {
                        apmn[d] = code[i][0];
                        ap[d] = code[i][c];
                        app[d] = "#" + c;
                        c++;
                        d++;
                    }
                    c = 1;
                }
            }
        }
        System.out.println("macro name table");
        System.out.println("___________");
        System.out.println("macro name no. of parameter");
        System.out.println("___________________");
        for (int i = 0; i < mn.length; i++) {
            System.out.println(mn[i] + "\t\t" + parameter[i]);
        }
        System.out.println("-----------------------\n \n");
        System.out.println("macro definition table");
        System.out.println("-----------------------");
        System.out.println("index \t instruction");
        System.out.println("---------------------");
        int index = 1, i = 0;
        while (i < 18) {
            if (code[i][0].equals("MACRO")) {
                i++;
                while (code[i][0] != "MEND") {
                    for (int j = 0; j < fp.length; j++) {
                        if (("&" + code[i][1]).equals(fp[j])) {
                            System.out.println((index++) + "\t" + code[i][0] + " " + pp[j]);
                            break;
                        }
                    }
                    i++;
                }
                System.out.println((index++) + "\t MEND");
            } else {
                i++;
            }
        }
        System.out.println("-------------------\n \n");
        System.out.println("Formal Vs Positional Parameter list");
        System.out.println("---------------------------");
        System.out.println("Macro Name \t Formal parameter \t Positional Parameter");
        System.out.println("---------------------------------");
        for (i = 0; i < fpmn.length; i++) {
            System.out.println(fpmn[i] + "\t\t" + fp[i] + "\t\t\t" + pp[i]);
        }
        System.out.println("-------------------------------------");
        System.out.println("actual Vs positional parameter");
        System.out.println("------------------------------------");
        System.out.println("macro name\t actual parameter\tpositional parameter");
        System.out.println("------------------------------------");
        for (i = 0; i < apmn.length; i++) {
            System.out.println(apmn[i] + "\t\t" + ap[i] + "\t\t\t" + app[i]);
        }
        System.out.println("---------------------------------\n\n");
        String pvalue[][] = new String[4][2];
        for (i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (fpmn[i].equals(apmn[j]) & pp[i].equals(app[j])) {
                    pvalue[i][0] = fp[i];
                    pvalue[i][1] = ap[j];
                    break;
                }
            }
        }
        System.out.println("expanded code");
        System.out.println("------------------");
        System.out.println("instruction code");
        System.out.println("----------------");
        i = 0;
        while (i < 18) {
            if (code[i][0].equals("ADD") || code[i][0].equals("SUB") || code[i][0].equals("ENDP")
                    || code[i][0].equals("LOAD")) {
                System.out.println(code[i][0] + "" + code[i][1]);
                i++;
            } else if (code[i][0].equals("MACRO")) {
                i++;
                while (code[i][0] != "MEND") {
                    i++;
                }
                i++;
            } else {
                int k = 0;
                while (k < 18) {
                    if (code[k][1].equals(code[i][0])) {
                        k++;
                        while (code[k][0] != "MEND") {
                            for (l = 0; l < 4; l++)
                                if (("&" + code[k][l]).equals(pvalue[l][0]))
                                    System.out.println(code[k][0] + "" + pvalue[l][l]);
                        }
                        k++;
                    }
                    k++;
                }
                k++;
                i++;
            }
        }
    }
}