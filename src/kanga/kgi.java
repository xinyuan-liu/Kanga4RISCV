package kanga;
/*    */ import java.io.PrintStream;
/*    */ import java.util.Hashtable;
/*    */ import syntaxtree.Goal;
/*    */ import syntaxtree.Node;
/*    */ import util.KangaRuntime;
/*    */ import visitor.MyTreeDumper;
/*    */ 
/*    */ public class kgi
/*    */ {
/* 10 */   private static boolean STDIN_ONLY = false;
/* 11 */   private static boolean kanga = false;
/*    */   
/*    */ 
/*    */ 
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 17 */     for (int i = 0; i < paramArrayOfString.length; i++) {
/* 18 */       if (paramArrayOfString[i].equals("-instructionOutput")) {
/* 19 */         util.Environment.instructionOutput = true;
/* 20 */       } else if (paramArrayOfString[i].equals("-instructionCount")) {
/* 21 */         util.Environment.instructionCount = true;
/* 22 */       } else if (paramArrayOfString[i].equals("-verbose")) {
/* 23 */         util.Environment.verbose = true;
/* 24 */       } else if (paramArrayOfString[i].equals("-allowDumps")) {
/* 25 */         util.Environment.allowDumps = true;
/*    */       } else {
/* 27 */         System.err.println("Argument " + paramArrayOfString[i] + " not recognized");
/* 28 */         System.exit(1);
/*    */       }
/*    */     }

/* 31 */     KangaParser localKangaParser = new KangaParser(System.in);

/*    */ 
/*    */     try
/*    */     {
/* 50 */       Goal localGoal = KangaParser.Goal();
/* 56 */       Hashtable localHashtable = new Hashtable();
/* 57 */       MyTreeDumper localMyTreeDumper = new MyTreeDumper(localHashtable);
/* 58 */       localGoal.accept(localMyTreeDumper);
/*    */       
/* 60 */       KangaRuntime localKangaRuntime = new KangaRuntime(localHashtable);
/* 61 */       localGoal.accept(new visitor.SetLabel(localKangaRuntime));
/* 64 */       localKangaRuntime.run();
/*    */     }
/*    */     catch (ParseException localParseException) {
/* 67 */       System.err.println(localParseException.getMessage());
/* 68 */       System.err.println("Encountered errors during parse.");
/*    */     }
/*    */   }
/*    */ }

