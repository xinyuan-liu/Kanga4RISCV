/*    */ package memory;
/*    */ 
/*    */ import util.Environment;
/*    */ 
/*    */ public class Int extends Memory {
/*  6 */   public boolean isInt() { return true; }
/*    */   
/*  8 */   public Int(int paramInt) { this.val = paramInt; }
/*  9 */   public Int(Integer paramInteger) { this.val = paramInteger.intValue(); }
/* 10 */   public Int(String paramString) { this.val = new Integer(paramString).intValue(); }
/* 11 */   public int getInt() { return this.val; }
/*    */   
/* 13 */   public String toString() { if (Environment.verbose) {
/* 14 */       return "(int:" + this.val + ")";
/*    */     }
/* 16 */     return "" + this.val;
/*    */   }
/*    */   
/*    */   private int val;
/*    */ }


/* Location:              /Users/liuxinyuan/Desktop/kgi.jar!/memory/Int.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */