/*    */ package memory;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ 
/*  5 */ public class Internal extends Memory { public boolean isInternal() { return true; }
/*    */   
/*  7 */   public Internal(String paramString) { this.cmd = paramString; }
/*    */   
/*    */   public String cmd;
/* 10 */   public String toString() { if (this.cmd == null) {
/* 11 */       System.err.println("ERROR: Internal " + this.cmd + " not set");
/* 12 */       return null;
/*    */     }
/* 14 */     return "(_internal_:" + this.cmd + ")";
/*    */   }
/*    */ }


/* Location:              /Users/liuxinyuan/Desktop/kgi.jar!/memory/Internal.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */