/*    */ package memory;
/*    */ 
/*    */ import java.util.Set;
/*    */ 
/*    */ public abstract class Memory {
/*  6 */   public boolean isHeap() { return false; }
/*  7 */   public boolean isLabel() { return false; }
/*    */   
/*  9 */   public boolean isInt() { return false; }
/* 10 */   public boolean isReg() { return false; }
/* 11 */   public boolean isInternal() { return false; }
/* 12 */   public boolean equals(Memory paramMemory) { return false; }
/*    */   
/* 14 */   protected static Set<Memory> printHistory = new java.util.HashSet();
/*    */   
/* 16 */   protected boolean addedToHistory() { boolean bool = printHistory.contains(this);
/* 17 */     printHistory.add(this);
/* 18 */     return bool; }
/*    */   
/* 20 */   public static void resetPrintHistory() { printHistory.clear(); }
/*    */ }


/* Location:              /Users/liuxinyuan/Desktop/kgi.jar!/memory/Memory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */