/*    */ package memory;
/*    */ 
/*    */ import util.Environment;
/*    */ 
/*    */ public class Reg extends Memory {
/*  6 */   public boolean isReg() { return true; }
/*    */   
/*    */   public Reg(String paramString, Memory paramMemory)
/*    */   {
/* 10 */     this.name = paramString;this.val = paramMemory; }
/*    */   
/* 12 */   public boolean isNull() { return this.val == null; }
/*    */   
/*    */ 
/*    */ 
/*    */   private String name;
/*    */   
/*    */ 
/*    */   private Memory val;
/* 20 */   public Memory getVal() { return this.val; }
/*    */   
/* 22 */   public void setVal(Memory paramMemory) { this.val = paramMemory; }
/*    */   
/* 24 */   public String toString() { if (this.val == null) {
/* 25 */       System.err.println("ERROR: Register " + this.name + " not set");
/* 26 */       return null;
/*    */     }
/* 28 */     if (Environment.verbose) {
/* 29 */       return "(" + this.name + ":" + this.val.toString() + ")";
/*    */     }
/* 31 */     return this.val.toString();
/*    */   }
/*    */ }


/* Location:              /Users/liuxinyuan/Desktop/kgi.jar!/memory/Reg.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */