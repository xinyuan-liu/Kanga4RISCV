package kanga;
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class KgCheckSyntax {
/*    */   public static void main(String[] paramArrayOfString) {
/*  5 */     KangaParser localKangaParser = new KangaParser(System.in);
/*    */     try {
/*  7 */       KangaParser.Goal();
/*  8 */       System.out.println("OK");
/*    */     }
/*    */     catch (ParseException localParseException) {
/* 11 */       System.err.println(localParseException.getMessage());
/* 12 */       System.exit(1);return;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/liuxinyuan/Desktop/kgi.jar!/KgCheckSyntax.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */