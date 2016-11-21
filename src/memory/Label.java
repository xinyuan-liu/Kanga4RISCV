/*    */ package memory;
/*    */ 
/*    */ public class Label extends Memory {
/*    */   private String label;
/*    */   private int pc;
/*    */   
/*  7 */   public boolean isLabel() { return true; }
/*  8 */   public Label(String paramString, int paramInt) { this.label = paramString;this.pc = paramInt; }
/*  9 */   public int getPC() { return this.pc; }
/* 10 */   public String getVal() { return this.label; }
/* 11 */   public String toString() { return "(Label " + this.label + "@" + this.pc + ")"; }
/*    */ }


/* Location:              /Users/liuxinyuan/Desktop/kgi.jar!/memory/Label.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */