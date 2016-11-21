/*    */ package memory;
/*    */ 
/*    */ import java.util.Vector;
/*    */ 
/*    */ public class Heap extends Memory {
/*  6 */   public boolean isHeap() { return true; }
/*  7 */   private int offset = 0;
/*    */   
/*    */   private Vector<Memory> mem;
/*    */   
/* 11 */   public Heap(Heap paramHeap) throws KangaHeapException { this.mem = paramHeap.mem; }
/*    */   
/*    */   public Heap(Heap paramHeap, int paramInt) throws KangaHeapException {
/* 14 */     this.mem = paramHeap.mem;
/* 15 */     if (this.offset % 4 != 0) {
/* 16 */       System.err.println("ERROR: offset " + this.offset + " is not divisible by 4");
/* 17 */       throw new KangaHeapException();
/*    */     }
/* 19 */     this.offset = (paramInt / 4);
/* 20 */     if ((this.offset < 0) || (this.offset > this.mem.size())) {
/* 21 */       System.err.println("ERROR: offset " + this.offset * 4 + " is out of bound with heap size " + this.mem.size() * 4);
/* 22 */       throw new KangaHeapException();
/*    */     }
/*    */   }
/*    */   
/* 26 */   public Heap(int paramInt) throws KangaHeapException { if ((paramInt < 0) || (paramInt % 4 != 0)) {
/* 27 */       System.err.println("ERROR: HALLOC must be 0-positive, divisible by 4. You tried " + paramInt + ".");
/* 28 */       throw new KangaHeapException();
/*    */     }
/* 30 */     this.mem = new Vector();
/* 31 */     for (int i = 0; i < paramInt / 4; i++) this.mem.add(null);
/*    */   }
/*    */   
/* 34 */   public int getSize() { return this.mem.size() * 4; }
/* 35 */   public int getOffset() { return this.offset * 4; }
/*    */   
/* 37 */   public Memory getContent(int paramInt) throws KangaHeapException { if ((paramInt + 4 * this.offset < 0) || (paramInt + 4 * this.offset >= this.mem.size() * 4) || (paramInt % 4 != 0)) {
/* 38 */       System.err.println("Illegal heap read value (" + paramInt + "+4*" + this.offset + "), must be 0-" + (4 * this.mem.size() - 4) + " and divisible by 4.");
/* 39 */       throw new KangaHeapException();
/*    */     }
/* 41 */     return (Memory)this.mem.get(paramInt / 4 + this.offset);
/*    */   }
/*    */   
/* 44 */   public void setContent(int paramInt, Memory paramMemory) throws KangaHeapException { if ((paramInt + 4 * this.offset < 0) || (paramInt + 4 * this.offset >= this.mem.size() * 4) || (paramInt % 4 != 0)) {
/* 45 */       System.err.println("Illegal heap write value (" + paramInt + "+4*" + this.offset + "), must be 0-" + (4 * this.mem.size() - 4) + " and divisible by 4.");
/* 46 */       throw new KangaHeapException();
/*    */     }
/* 48 */     this.mem.set(paramInt / 4 + this.offset, paramMemory);
/*    */   }
/*    */   
/* 51 */   public String toString() { if (addedToHistory()) return "(heap <recursive>)";
/* 52 */     StringBuffer localStringBuffer = new StringBuffer();
/* 53 */     for (int i = 0; i < this.mem.size(); i++) {
/* 54 */       if (this.offset == i) localStringBuffer.append("*");
/* 55 */       localStringBuffer.append(4 * i + ":" + this.mem.get(i));
/* 56 */       if (i + 1 != this.mem.size()) localStringBuffer.append(" ");
/*    */     }
/* 58 */     return "(heap " + localStringBuffer + ")";
/*    */   }
/*    */ }


/* Location:              /Users/liuxinyuan/Desktop/kgi.jar!/memory/Heap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */